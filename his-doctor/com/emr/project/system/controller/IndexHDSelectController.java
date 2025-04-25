package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsModule;
import com.emr.project.system.domain.vo.SelectHDIndexVo;
import com.emr.project.system.domain.vo.SetHDToUserVo;
import com.emr.project.system.domain.vo.TmBsModuleVo;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.system.service.ITmBsModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户登录后选择院区科室信息管理")
@RestController
@RequestMapping({"/auth1/indexHDSelect"})
public class IndexHDSelectController extends BaseController {
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private ISysOrgService orgService;
   @Autowired
   private TokenService tokenService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITmBsModuleService moduleService;
   @Autowired
   private ISysUserService userService;

   @ApiOperation("查询用户对应的院区和科室信息")
   @ApiImplicitParam(
      name = "userId",
      value = "用户ID",
      required = true,
      dataType = "Long",
      paramType = "path"
   )
   @PreAuthorize("@ss.hasPermi('indexHDSelect:index')")
   @GetMapping({"/index/{userId}"})
   public AjaxResult index(@PathVariable("userId") Long userId) {
      AjaxResult ajax = AjaxResult.success();

      try {
         if (userId == null) {
            ajax = AjaxResult.error("用户ID不能为空");
         } else {
            List<SelectHDIndexVo> selectHDIndexVoList = this.deptService.queryUserDeptLeves(userId);
            ajax.put("selectHDIndexVoList", selectHDIndexVoList);
            SysUser user = this.userService.selectUserById(userId);
            List<TmBsModule> moduleList = this.moduleService.selectModuleListByUser(user.getUserName());
            List<TmBsModuleVo> moduleVoList = this.deptService.filterDeptByModule(selectHDIndexVoList, moduleList);
            ajax.put("moduleList", moduleVoList);
         }
      } catch (Exception e) {
         this.log.error("查询用户对应的院区和科室信息出现异常：", e);
      }

      return ajax;
   }

   @ApiOperation("设置用户对应的院区和科室信息")
   @PreAuthorize("@ss.hasPermi('indexHDSelect:index')")
   @PutMapping
   public AjaxResult setHDToUser(HttpServletRequest request, @RequestBody SetHDToUserVo setHDToUserVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      if (flag && setHDToUserVo == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数不能为空");
      }

      if (flag && setHDToUserVo.getHospital() == null) {
         flag = false;
         ajaxResult = AjaxResult.error("院区不能为空");
      }

      if (flag && setHDToUserVo.getDept() == null) {
         flag = false;
         ajaxResult = AjaxResult.error("部门不能为空");
      }

      LoginUser loginUser = this.tokenService.getLoginUser(request);
      SysUser sysUser = loginUser.getUser();
      String messageKey = "message_key:" + sysUser.getUserName();
      String lisPacsAlertKey = "lis_pacs_alert_key:" + sysUser.getUserName();
      SysDept sysDept = (SysDept)sysUser.getDepts().stream().filter((t) -> t.getDeptCode().equals(setHDToUserVo.getDept().getDeptCode())).findFirst().orElse(setHDToUserVo.getDept());

      try {
         if (flag) {
            sysUser.setDept(sysDept);
            sysUser.setDeptId(sysDept.getDeptId());
            SysOrg sysOrg = this.orgService.getSysOrgByDept(setHDToUserVo.getDept());
            sysUser.setHospital(sysOrg);
            sysUser.setHospitalId(sysOrg.getId());
            sysUser.setModule(setHDToUserVo.getModule());
            loginUser.setUser(sysUser);
            this.tokenService.setLoginUser(loginUser);
            ajaxResult.put("user", sysUser);
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (StringUtils.isBlank(emrFlag) || StringUtils.isNotBlank(emrFlag) && !emrFlag.equals("1")) {
               this.drugAndClinService.syncDrugAndClinAllToEsLogin(loginUser.getUser());
            }

            List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
            this.redisCache.deleteObject(messageKey);
            this.redisCache.deleteObject(lisPacsAlertKey);
            if (messageList != null && !messageList.isEmpty()) {
               this.redisCache.setCacheList(messageKey, messageList);
            }
         }
      } catch (Exception e) {
         this.log.error("设置用户对应的院区和科室信息出现异常：", e);
         this.redisCache.deleteObject(messageKey);
      }

      return ajaxResult;
   }
}
