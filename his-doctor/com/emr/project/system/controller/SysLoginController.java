package com.emr.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.http.HttpUtils;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.security.BsLoginStaff;
import com.emr.framework.security.LoginBody;
import com.emr.framework.security.LoginCaBody;
import com.emr.framework.security.LoginOperationRoomBody;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.SysLoginService;
import com.emr.framework.security.service.SysPermissionService;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.mzInfo.domain.OutpatientInfoVO;
import com.emr.project.mzInfo.service.IOutpatientInfoService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysMenu;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsModule;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysMenuService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.system.service.ITmBsModuleService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/auth1"})
public class SysLoginController extends BaseController {
   @Autowired
   private SysLoginService loginService;
   @Autowired
   private ISysMenuService menuService;
   @Autowired
   private SysPermissionService permissionService;
   @Autowired
   private TokenService tokenService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private IOutpatientInfoService outpatientInfoService;
   @Autowired
   private ITmBsModuleService moduleService;
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private EMRConfig emrConfig;

   @PostMapping({"/login"})
   public AjaxResult login(@RequestBody LoginBody loginBody) {
      AjaxResult ajax = AjaxResult.success();
      String loginErrorKey = "login_error:" + loginBody.getUsername();

      try {
         String token = this.loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
         ajax.put("token", token);
         SysUser sysUser = this.sysUserService.selectUserByUserName(loginBody.getUsername());
         Date updateTime = sysUser.getUpdatePasswordTime();
         Date nowDate = new Date();
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(nowDate);
         calendar.add(2, -3);
         Date dBefore = calendar.getTime();
         if (updateTime == null || dBefore.compareTo(updateTime) > 0) {
            ajax.put("passWordMes", "密码设置已超过三个月，请及时修改密码！");
         }

         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(loginBody.getUsername());
         sysUser.setCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
         ajax.put("user", sysUser);
         String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
         ajax.put("hospital", dictStr);
         String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
         ajax.put("emrFlag", emrFlag);
         this.redisCache.deleteObject(loginErrorKey);
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
         ajax.put("loginErrorNum", loginErrorNum);
         this.log.error("登录出现异常，", e);
      }

      return ajax;
   }

   @PostMapping({"/calogin"})
   public AjaxResult caLogin(@RequestBody LoginCaBody loginCaBody) {
      AjaxResult ajax = AjaxResult.success();
      boolean flag = true;
      String loginErrorKey = "login_error:" + loginCaBody.getSn();

      try {
         if (flag && loginCaBody == null) {
            flag = false;
            ajax = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(loginCaBody.getSn())) {
            flag = false;
            ajax = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            String token = this.loginService.caLogin(loginCaBody.getSn());
            ajax.put("token", token);
            SysUser sysUser = this.sysUserService.selectUserByCertSn(loginCaBody.getSn());
            sysUser.setCertSn(loginCaBody.getSn());
            ajax.put("user", sysUser);
            String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
            ajax.put("hospital", dictStr);
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            ajax.put("emrFlag", emrFlag);
            this.redisCache.deleteObject(loginErrorKey);
         }
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
         ajax.put("loginErrorNum", loginErrorNum);
      }

      return ajax;
   }

   @PostMapping({"/orlogin"})
   public AjaxResult OperationRoomLogin(@RequestBody LoginOperationRoomBody loginOperationRoomBody) {
      AjaxResult ajax = AjaxResult.success();
      boolean flag = true;
      String loginErrorKey = "login_error:" + loginOperationRoomBody.getUsername();

      try {
         if (flag && loginOperationRoomBody == null) {
            flag = false;
            ajax = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getUsername())) {
            flag = false;
            ajax = AjaxResult.error("用户编码不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getInpNo())) {
            flag = false;
            ajax = AjaxResult.error("患者就诊号不能为空");
         }

         if (flag && (StringUtils.isBlank(loginOperationRoomBody.getJzType()) || StringUtils.isNotBlank(loginOperationRoomBody.getJzType()) && loginOperationRoomBody.getJzType().equals("zy")) && StringUtils.isBlank(loginOperationRoomBody.getDeptCd())) {
            flag = false;
            ajax = AjaxResult.error("科室不能为空");
         }

         if (StringUtils.isNotBlank(loginOperationRoomBody.getJzType()) && loginOperationRoomBody.getJzType().equals("mz")) {
            this.orloginMz(flag, loginOperationRoomBody, ajax, loginErrorKey);
         } else {
            this.orloginZy(flag, loginOperationRoomBody, ajax, loginErrorKey);
         }
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
         ajax.put("loginErrorNum", loginErrorNum);
         this.log.error("手术室登录出现异常：", e);
      }

      return ajax;
   }

   @PostMapping({"/autoLogin"})
   public AjaxResult AutoLogin(@RequestBody LoginOperationRoomBody loginOperationRoomBody) {
      AjaxResult ajax = AjaxResult.success();
      boolean flag = true;
      String loginErrorKey = "login_error:" + loginOperationRoomBody.getUsername();
      this.log.warn("----->自动登录！！！开始，科室：{}，登录人：{}", loginOperationRoomBody.getDeptCd(), loginOperationRoomBody.getUsername());

      try {
         if (flag && loginOperationRoomBody == null) {
            flag = false;
            ajax = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getUsername())) {
            flag = false;
            ajax = AjaxResult.error("用户编码不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getDeptCd())) {
            flag = false;
            ajax = AjaxResult.error("科室不能为空");
         }

         if (flag) {
            this.autoLogin(loginOperationRoomBody, ajax, loginErrorKey);
         }
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
         ajax.put("loginErrorNum", loginErrorNum);
         this.log.error("自动登录出现异常：", e);
      }

      this.log.warn("----->自动登录！！！结束，返回登录科室：{}", ajax.get("deptName"));
      return ajax;
   }

   private void autoLogin(LoginOperationRoomBody loginOperationRoomBody, AjaxResult ajax, String loginErrorKey) throws Exception {
      List<SysDept> deptList = this.deptService.selectDeptByEmployeeId(loginOperationRoomBody.getUsername());
      SysDept dept = null;
      if (!deptList.isEmpty()) {
         dept = (SysDept)deptList.stream().filter((t) -> t.getDeptCode().equals(loginOperationRoomBody.getDeptCd())).findFirst().orElse((Object)null);
      }

      String token = null;

      try {
         token = this.loginService.autoLogin(loginOperationRoomBody.getUsername());
      } catch (Exception e) {
         this.log.error("通过链接用户登录生成令牌出现异常：", e);
         throw new Exception(e);
      }

      ajax.put("token", token);
      this.redisCache.deleteObject(loginErrorKey);
      LoginUser loginUser = this.tokenService.getLoginUser(token);
      SysUser user = loginUser.getUser();
      BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(user.getUserName());
      user.setCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
      if (deptList != null && !deptList.isEmpty()) {
         SysOrg sysOrg = this.sysOrgService.getSysOrgByDept((SysDept)deptList.get(0));
         user.setHospital(sysOrg);
         user.setHospitalId(sysOrg.getId());
         user.setDept(dept != null ? dept : (SysDept)deptList.get(0));
         user.setDeptId(dept != null ? dept.getDeptId() : ((SysDept)deptList.get(0)).getDeptId());
         user.setDeptCode(dept != null ? dept.getDeptCode() : ((SysDept)deptList.get(0)).getDeptCode());
         TmBsModule module = null;
         List<TmBsModule> moduleList = this.moduleService.selectModuleListByUser(user.getUserName());
         if (CollectionUtils.isNotEmpty(moduleList)) {
            moduleList = (List)moduleList.stream().filter((t) -> t.getModuleCode().equals("0304")).collect(Collectors.toList());
            module = CollectionUtils.isNotEmpty(moduleList) ? (TmBsModule)moduleList.get(0) : null;
            user.setModule(module);
         }

         loginUser.setUser(user);
         this.tokenService.setLoginUser(loginUser);
      }

      ajax.put("deptName", dept != null ? dept.getDeptName() : null);
      ajax.put("user", user);
      String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
      ajax.put("hospital", dictStr);
      String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
      ajax.put("emrFlag", emrFlag);
      ajax.put("dept", dept);
   }

   private void orloginMz(boolean flag, LoginOperationRoomBody loginOperationRoomBody, AjaxResult ajax, String loginErrorKey) throws Exception {
      List<OutpatientInfoVO> outpatientInfoVOList = this.outpatientInfoService.selectOutPatientInfoByIdCard(loginOperationRoomBody.getInpNo(), loginOperationRoomBody.getIdcard());
      if (CollectionUtils.isEmpty(outpatientInfoVOList)) {
         flag = false;
         ajax = AjaxResult.error("没有当前患者的记录");
      }

      List<SysDept> deptList = flag ? this.deptService.selectDeptByEmployeeId(loginOperationRoomBody.getUsername()) : null;
      SysDept dept = CollectionUtils.isNotEmpty(deptList) ? (SysDept)deptList.get(0) : null;
      if (flag) {
         List<String> idCardList = (List)outpatientInfoVOList.stream().filter((t) -> StringUtils.isNotBlank(t.getIdCard())).map((t) -> t.getIdCard()).collect(Collectors.toList());
         String idCard = CollectionUtils.isNotEmpty(idCardList) ? (String)idCardList.get(0) : null;
         String token = null;

         try {
            token = this.loginService.orLogin(loginOperationRoomBody.getUsername());
         } catch (Exception e) {
            this.log.error("通过链接用户登录生成令牌出现异常：", e);
            throw new Exception(e);
         }

         ajax.put("token", token);
         this.redisCache.deleteObject(loginErrorKey);
         LoginUser loginUser = this.tokenService.getLoginUser(token);
         SysUser user = loginUser.getUser();
         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(user.getUserName());
         user.setCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
         if (deptList != null && !deptList.isEmpty()) {
            SysOrg sysOrg = this.sysOrgService.getSysOrgByDept((SysDept)deptList.get(0));
            user.setHospital(sysOrg);
            user.setHospitalId(sysOrg.getId());
            user.setDept((SysDept)deptList.get(0));
            user.setDeptId(((SysDept)deptList.get(0)).getDeptId());
            user.setHospital(sysOrg);
            user.setHospitalId(sysOrg.getId());
            user.setDept((SysDept)deptList.get(0));
            TmBsModule module = null;
            List<TmBsModule> moduleList = this.moduleService.selectModuleListByUser(user.getUserName());
            if (CollectionUtils.isNotEmpty(moduleList)) {
               moduleList = (List)moduleList.stream().filter((t) -> t.getModuleCode().equals("0304")).collect(Collectors.toList());
               module = CollectionUtils.isNotEmpty(moduleList) ? (TmBsModule)moduleList.get(0) : null;
               user.setModule(module);
            }

            loginUser.setUser(user);
            this.tokenService.setLoginUser(loginUser);
         }

         ajax.put("patientId", loginOperationRoomBody.getInpNo());
         ajax.put("patientMainId", idCard);
         ajax.put("inpNo", loginOperationRoomBody.getInpNo());
         ajax.put("deptName", dept != null ? dept.getDeptName() : null);
         ajax.put("user", user);
         String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
         ajax.put("hospital", dictStr);
         String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
         ajax.put("emrFlag", emrFlag);
         ajax.put("dept", dept);
      }

   }

   private void orloginZy(boolean flag, LoginOperationRoomBody loginOperationRoomBody, AjaxResult ajax, String loginErrorKey) throws Exception {
      Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoByInpNo(loginOperationRoomBody.getInpNo()) : null;
      Personalinfo personalinfo = new Personalinfo();
      if (flag) {
         if (visitinfo == null) {
            visitinfo = this.visitinfoService.selectHisPatByInpNo(loginOperationRoomBody.getInpNo());
            if (visitinfo == null) {
               flag = false;
               ajax = AjaxResult.error("没有当前患者的记录");
            } else {
               personalinfo.setPatientMainId(visitinfo.getRecordNo());
            }
         } else {
            personalinfo = this.personalinfoService.selectPersonalinfoById(visitinfo.getPatientId());
         }
      }

      SysDept dept = flag ? this.deptService.selectDeptByDeptCode(loginOperationRoomBody.getDeptCd(), (Long)null) : null;
      if (flag && dept == null) {
         flag = false;
         ajax = AjaxResult.error("没有此科室编码的记录");
      }

      if (flag) {
         String token = null;

         try {
            token = this.loginService.orLogin(loginOperationRoomBody.getUsername());
         } catch (Exception e) {
            this.log.error("通过链接用户登录生成令牌出现异常：", e);
            throw new Exception(e);
         }

         ajax.put("token", token);
         this.redisCache.deleteObject(loginErrorKey);
         LoginUser loginUser = this.tokenService.getLoginUser(token);
         SysUser user = loginUser.getUser();
         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(user.getUserName());
         user.setCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
         List<SysDept> deptList = Arrays.asList(dept);
         String deptCd = visitinfo.getDeptCd();
         if (!loginOperationRoomBody.getDeptCd().equals(deptCd)) {
            deptList = this.deptService.queryByDepCodeList(Arrays.asList(loginOperationRoomBody.getDeptCd(), deptCd));
         }

         List<SysDept> deptList1 = (List)deptList.stream().filter((t) -> t.getDeptCode().equals(deptCd)).collect(Collectors.toList());
         if (deptList != null && !deptList.isEmpty()) {
            SysOrg sysOrg = this.sysOrgService.getSysOrgByDept((SysDept)deptList1.get(0));
            user.setHospital(sysOrg);
            user.setHospitalId(sysOrg.getId());
            user.setDept(dept);
            user.setDeptId(dept.getDeptId());
            user.setHospital(sysOrg);
            user.setHospitalId(sysOrg.getId());
            TmBsModule module = null;
            List<TmBsModule> moduleList = this.moduleService.selectModuleListByUser(user.getUserName());
            if (CollectionUtils.isNotEmpty(moduleList)) {
               moduleList = (List)moduleList.stream().filter((t) -> t.getModuleCode().equals("0304")).collect(Collectors.toList());
               module = CollectionUtils.isNotEmpty(moduleList) ? (TmBsModule)moduleList.get(0) : null;
               user.setModule(module);
            }

            loginUser.setUser(user);
            this.tokenService.setLoginUser(loginUser);
         }

         ajax.put("patientId", visitinfo.getInpNo());
         ajax.put("patientMainId", personalinfo.getPatientMainId());
         ajax.put("inpNo", visitinfo.getInpNo());
         ajax.put("deptName", dept.getDeptName());
         ajax.put("user", user);
         String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
         ajax.put("hospital", dictStr);
         String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
         ajax.put("emrFlag", emrFlag);
         ajax.put("dept", dept);
         if (StringUtils.isBlank(emrFlag) || StringUtils.isNotBlank(emrFlag) && !emrFlag.equals("1")) {
            try {
               this.drugAndClinService.syncDrugAndClinAllToEsLogin(loginUser.getUser());
            } catch (Exception e) {
               this.log.error("手术室登录，异步生成当前用户的医嘱开立项目，出现异常：", e);
            }
         }
      }

   }

   @PostMapping({"/mainlogin"})
   public AjaxResult mainlogin(@RequestBody LoginOperationRoomBody loginOperationRoomBody) {
      AjaxResult ajax = AjaxResult.success();
      boolean flag = true;
      String loginErrorKey = "login_error:" + loginOperationRoomBody.getUsername();

      try {
         if (flag && loginOperationRoomBody == null) {
            flag = false;
            ajax = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getUsername())) {
            flag = false;
            ajax = AjaxResult.error("用户编码不能为空");
         }

         if (flag && StringUtils.isBlank(loginOperationRoomBody.getDeptCd())) {
            flag = false;
            ajax = AjaxResult.error("科室不能为空");
         }

         SysDept dept = flag ? this.deptService.selectDeptByDeptCode(loginOperationRoomBody.getDeptCd(), (Long)null) : null;
         if (flag && dept == null) {
            flag = false;
            ajax = AjaxResult.error("没有此科室编码的记录");
         }

         if (flag) {
            String token = this.loginService.orLogin(loginOperationRoomBody.getUsername());
            ajax.put("token", token);
            this.redisCache.deleteObject(loginErrorKey);
            LoginUser loginUser = this.tokenService.getLoginUser(token);
            SysUser user = loginUser.getUser();
            BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(user.getUserName());
            user.setCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
            BasEmployee basEmployee = user.getBasEmployee();
            List<SysDept> deptList = Arrays.asList(dept);
            if (deptList != null && !deptList.isEmpty()) {
               SysOrg sysOrg = this.sysOrgService.getSysOrgByDept((SysDept)deptList.get(0));
               user.setHospital(sysOrg);
               user.setHospitalId(sysOrg.getId());
               user.setDept((SysDept)deptList.get(0));
               user.setDeptId(((SysDept)deptList.get(0)).getDeptId());
               user.setHospital(sysOrg);
               user.setHospitalId(sysOrg.getId());
               user.setDept(dept);
               loginUser.setUser(user);
               this.tokenService.setLoginUser(loginUser);
            }

            ajax.put("user", user);
            String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0004");
            ajax.put("hospital", dictStr);
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            ajax.put("emrFlag", emrFlag);
            ajax.put("dept", dept);
         }
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         Integer loginErrorNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
         ajax.put("loginErrorNum", loginErrorNum);
         this.log.error("跳转首页登录出现异常：", e);
      }

      return ajax;
   }

   @GetMapping({"/getInfo"})
   public AjaxResult getInfo() {
      AjaxResult ajax = AjaxResult.success();

      try {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         SysUser user = loginUser.getUser();
         String deptCode = user.getDept() == null ? null : user.getDept().getDeptCode();
         user.setDeptCode(deptCode);
         Set<String> roles = this.permissionService.getRolePermission(user);
         Set<String> permissions = this.permissionService.getMenuPermission(user);
         List<SysRole> roleList = user.getRoles();
         List<SysRole> adminRoles = null;
         if (roleList != null && !roleList.isEmpty()) {
            adminRoles = (List)roleList.stream().filter((r) -> r.isAdmin()).collect(Collectors.toList());
         }

         if (SysUser.isAdmin(user.getUserId()) || adminRoles != null && !adminRoles.isEmpty()) {
            ajax.put("isAdmin", "1");
         }

         ajax.put("user", user);
         ajax.put("roles", roles);
         ajax.put("permissions", permissions);
         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
         String passFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0042");
         ajax.put("passFlag", passFlag);
         ajax.put("caFlag", caFlag);
         ajax.put("caType", caType);
         String ip = IPAddressUtil.getIPAddress(ServletUtils.getRequest());
         ajax.put("ip", ip);
         String systemName = this.sysEmrConfigService.selectSysEmrConfigByKey("0065");
         ajax.put("systemName", systemName);
         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(loginUser.getUsername());
         ajax.put("certSn", basCertInfo != null ? basCertInfo.getCertSn() : null);
         String timeLimit = this.sysEmrConfigService.selectSysEmrConfigByKey("0081");
         ajax.put("timeLimit", timeLimit);
         String silentPrint = this.sysEmrConfigService.selectSysEmrConfigByKey("0084");
         ajax.put("silentPrint", silentPrint);
         String lodopZcm = this.sysEmrConfigService.selectSysEmrConfigByKey("0085");
         ajax.put("lodopZcm", lodopZcm);
         String criticalManualLockFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("005901");
         ajax.put("criticalManualLockFlag", criticalManualLockFlag);
      } catch (Exception e) {
         ajax = AjaxResult.error(e.getMessage());
         this.log.error("查询失败", e);
      }

      return ajax;
   }

   @GetMapping({"getRouters"})
   public AjaxResult getRouters() {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      SysUser user = loginUser.getUser();
      List<SysMenu> menus = null;

      try {
         menus = this.menuService.selectMenuTreeByUserId(user.getUserId());
      } catch (Exception e) {
         this.log.error("获取路由信息出现异常：", e);
      }

      return AjaxResult.success((Object)this.menuService.buildMenus(menus));
   }

   @PostMapping({"/ssoGetInfo"})
   public AjaxResult ssoGetInfo(@RequestBody String token) {
      AjaxResult ajax = AjaxResult.success();

      try {
         String ssoUrl = this.emrConfig.getSsoUrl() + "getInfo";
         JSONObject tokenJson = JSON.parseObject(token);
         String userStr = HttpUtils.sendPost(ssoUrl, "token=" + tokenJson.get("token").toString(), "");
         JSONObject parseObject = JSON.parseObject(userStr);
         BsStaff bsStaff = (BsStaff)JSON.parseObject(parseObject.get("bsStaff").toString(), new TypeReference() {
         }, new Feature[0]);
         BsLoginStaff loginStall = (BsLoginStaff)JSON.parseObject(parseObject.get("bsLoginStaff").toString(), new TypeReference() {
         }, new Feature[0]);
         LoginUser loginUser = new LoginUser();
         this.setLoginUser(loginStall, loginUser);
         Set<String> roles = (Set)JSON.parseObject(parseObject.get("roles").toString(), new TypeReference() {
         }, new Feature[0]);
         Set<String> permissions = (Set)JSON.parseObject(parseObject.get("permissions").toString(), new TypeReference() {
         }, new Feature[0]);
         loginUser.setPermissions(permissions);
         this.tokenService.refreshToken(loginUser);
         ajax.put("token", tokenJson.get("token").toString());
         ajax.put("bsStaff", bsStaff);
         ajax.put("roles", roles);
         ajax.put("permissions", permissions);
      } catch (Exception e) {
         this.log.error("", e);
         ajax = AjaxResult.error("Qqq");
      }

      return ajax;
   }

   @GetMapping({"/ssoGetRouter"})
   public AjaxResult ssoGetRouter() {
      List<SysMenu> menus = null;

      try {
         menus = this.menuService.selectMenuTreeAll();
      } catch (Exception e) {
         this.log.error("获取路由信息出现异常：", e);
      }

      return AjaxResult.success((Object)this.menuService.buildMenus(menus));
   }

   public void setLoginUser(BsLoginStaff bsLoginStaff, LoginUser loginUser) {
      SysUser sysUser = new SysUser();
      BsStaff bsStaff = bsLoginStaff.getBsStaff();
      sysUser.setPassword(bsStaff.getPasswordHis());
      sysUser.setEmployeeId(bsStaff.getId());
      sysUser.setNickName(bsStaff.getStaffName());
      sysUser.setSex(bsStaff.getStaffSex());
      sysUser.setUserId(bsStaff.getId());
      loginUser.setBrowser(bsLoginStaff.getBrowser());
      loginUser.setExpireTime(bsLoginStaff.getExpireTime());
      loginUser.setIpaddr(bsLoginStaff.getIpaddr());
      loginUser.setLoginLocation(bsLoginStaff.getLoginLocation());
      loginUser.setLoginTime(bsLoginStaff.getLoginTime());
      loginUser.setOs(bsLoginStaff.getOs());
      loginUser.setToken(bsLoginStaff.getToken());
      loginUser.setUser(sysUser);
   }
}
