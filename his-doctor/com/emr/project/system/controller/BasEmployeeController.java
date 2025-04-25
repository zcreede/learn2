package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.BasEmployeeSearchVo;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysPostService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/system/employee"})
public class BasEmployeeController extends BaseController {
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysPostService postService;
   @Autowired
   private ISysDeptService sysDeptService;

   @PreAuthorize("@ss.hasAnyPermi('system:employee:list,system:user:add')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasEmployee basEmployee) {
      this.startPage();
      List<BasEmployeeVo> list = new ArrayList(1);

      try {
         list = this.basEmployeeService.selectBasEmployeeList(basEmployee);
      } catch (Exception e) {
         this.log.error("查询人员信息列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:employee:export')")
   @Log(
      title = "人员信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasEmployee basEmployee) {
      AjaxResult ajaxResult = AjaxResult.success();
      new ArrayList(1);

      try {
         List list = this.basEmployeeService.selectBasEmployeeList(basEmployee);
         ExcelUtil<BasEmployeeVo> util = new ExcelUtil(BasEmployeeVo.class);
         ajaxResult = util.exportExcel(list, "人员信息数据");
      } catch (Exception e) {
         this.log.error("导出人员信息列表出现异常，", e);
         ajaxResult = AjaxResult.error("导出人员信息列表出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:employee:import')")
   @Log(
      title = "人员信息",
      businessType = BusinessType.EXPORT
   )
   @PostMapping({"/importList"})
   public AjaxResult importList(MultipartFile file, boolean updateSupport) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         ExcelUtil<BasEmployee> util = new ExcelUtil(BasEmployee.class);
         util.importExcel(file.getInputStream());
      } catch (Exception e) {
         this.log.error("导入人员信息列表出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:employee:edit')")
   @GetMapping({"/info"})
   public AjaxResult getInfo(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("id不能为空");
         } else {
            BasEmployeeVo employee = this.basEmployeeService.selectBasEmployeeById(id);
            ajaxResult = AjaxResult.success((Object)employee);
            ajaxResult.put("posts", this.postService.selectPostAll());
            ajaxResult.put("postIds", this.postService.selectPostListByUserId(id));
         }
      } catch (Exception e) {
         this.log.error("获取人员信息详细信息出现异常，请联系管理员", e);
         ajaxResult = AjaxResult.error("获取人员信息详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:employee:add')")
   @Log(
      title = "人员信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasEmployeeVo basEmployeeVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && basEmployeeVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getEmplNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员编号不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getEmplName())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员姓名不能为空");
         }

         if (flag && (basEmployeeVo.getUserRoleDepts() == null || basEmployeeVo.getUserRoleDepts().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("人员所属科室不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getTitleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员职称不能为空");
         }

         if (flag) {
            BasEmployee temp = this.basEmployeeService.selectByEmplNumber(basEmployeeVo.getEmplNumber());
            if (temp != null) {
               flag = false;
               ajaxResult = AjaxResult.error("人员编号已存在，请重新设置");
            }
         }

         if (flag) {
            this.basEmployeeService.insertBasEmployee(basEmployeeVo);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("新增人员信息出现异常，请联系管理员");
         this.log.error("新增人员信息出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:employee:edit')")
   @Log(
      title = "人员信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasEmployeeVo basEmployeeVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && basEmployeeVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basEmployeeVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getEmplNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员编号不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getEmplName())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员姓名不能为空");
         }

         if (flag && (basEmployeeVo.getUserRoleDepts() == null || basEmployeeVo.getUserRoleDepts().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("人员所属科室不能为空");
         }

         if (flag && StringUtils.isBlank(basEmployeeVo.getTitleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("人员职称不能为空");
         }

         if (flag) {
            this.basEmployeeService.updateBasEmployee(basEmployeeVo);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("修改人员信息出现异常，请联系管理员");
         this.log.error("修改人员信息出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:employee:remove')")
   @Log(
      title = "人员信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && (ids == null || ids != null && ids.length == 0)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            this.basEmployeeService.deleteBasEmployeeByIds(ids);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("删除人员信息出现异常，请联系管理员");
         this.log.error("删除人员信息出现异常，", e);
      }

      return ajaxResult;
   }

   @GetMapping({"/listByType"})
   public TableDataInfo listByType(String searchType, String titleCode, String searchKey, String deptCd, String status) {
      List<BasEmployee> list = new ArrayList();
      boolean flag = true;

      try {
         if (flag && (StringUtils.isEmpty(searchType) || StringUtils.isBlank(searchType))) {
            flag = false;
         }

         if (flag) {
            List<String> titleCodeList = null;
            if (StringUtils.isNotEmpty(titleCode) && StringUtils.isNotBlank(titleCode)) {
               String attDocStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0012");
               String arcDocStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0013");
               if (titleCode.equals("attDoc")) {
                  titleCodeList = Arrays.asList(attDocStr.split(","));
               }

               if (titleCode.equals("arcDoc")) {
                  titleCodeList = Arrays.asList(arcDocStr.split(","));
               }
            }

            SysUser user = SecurityUtils.getLoginUser().getUser();
            BasEmployeeSearchVo searchParam = new BasEmployeeSearchVo();
            searchParam.setEmplName(searchKey);
            searchParam.setStatus(status);
            searchParam.setTitleCodeList(titleCodeList);
            switch (searchType) {
               case "1":
                  searchParam.setDeptId(user.getDept().getDeptCode());
                  searchParam.setDeptCode(user.getDept().getDeptCode());
                  searchParam.setEmplNumber(user.getUserName());
                  searchParam.setGroupId("1");
                  break;
               case "2":
                  searchParam.setDeptId(user.getDept().getDeptCode());
                  searchParam.setDeptCode(user.getDept().getDeptCode());
               case "3":
               default:
                  break;
               case "4":
                  SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(deptCd, (Long)null);
                  if (sysDept != null) {
                     searchParam.setDeptId(deptCd);
                     searchParam.setDeptCode(deptCd);
                     searchParam.setDeptName(sysDept.getDeptName());
                  }
            }

            this.startPage();
            list = this.basEmployeeService.searchList(searchParam);
         }
      } catch (Exception e) {
         this.log.error("根据类型查询患者信息出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @GetMapping({"/listByTypeAll"})
   public TableDataInfo listByTypeAll(String searchType, String titleCode, String searchKey, String deptCd, String status) {
      List<BasEmployee> list = new ArrayList();
      boolean flag = true;

      try {
         if (flag && (StringUtils.isEmpty(searchType) || StringUtils.isBlank(searchType))) {
            flag = false;
         }

         if (flag) {
            List<String> titleCodeList = null;
            if (StringUtils.isNotEmpty(titleCode) && StringUtils.isNotBlank(titleCode)) {
               String attDocStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0012");
               String arcDocStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0013");
               if (titleCode.equals("attDoc")) {
                  titleCodeList = Arrays.asList(attDocStr.split(","));
               }

               if (titleCode.equals("arcDoc")) {
                  titleCodeList = Arrays.asList(arcDocStr.split(","));
               }
            }

            SysUser user = SecurityUtils.getLoginUser().getUser();
            BasEmployeeSearchVo searchParam = new BasEmployeeSearchVo();
            searchParam.setEmplName(searchKey);
            searchParam.setStatus(status);
            searchParam.setTitleCodeList(titleCodeList);
            switch (searchType) {
               case "1":
                  searchParam.setDeptId(user.getDept().getDeptCode());
                  searchParam.setDeptCode(user.getDept().getDeptCode());
                  searchParam.setEmplNumber(user.getUserName());
                  searchParam.setGroupId("1");
                  break;
               case "2":
                  searchParam.setDeptId(user.getDept().getDeptCode());
                  searchParam.setDeptCode(user.getDept().getDeptCode());
               case "3":
               default:
                  break;
               case "4":
                  SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(deptCd, (Long)null);
                  if (sysDept != null) {
                     searchParam.setDeptId(deptCd);
                     searchParam.setDeptCode(deptCd);
                     searchParam.setDeptName(sysDept.getDeptName());
                  }
            }

            this.startPage();
            list = this.basEmployeeService.searchListAll(searchParam);
         }
      } catch (Exception e) {
         this.log.error("根据类型查询患者信息出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @GetMapping({"/importTemplate"})
   public AjaxResult importTemplate() {
      ExcelUtil<BasEmployee> util = new ExcelUtil(BasEmployee.class);
      return util.importTemplateExcel("人员数据");
   }

   @PreAuthorize("@ss.hasPermi('system:employee:authorizeList')")
   @GetMapping({"/authorizeList"})
   public TableDataInfo authorizeList(BasEmployeeVo basEmployeeVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BasEmployeeVo> list = this.basEmployeeService.selectAuthorizeList(basEmployeeVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询人员权限等级列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询人员权限等级列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:employee:doctors,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/doctors"})
   public AjaxResult queryDoctors() {
      AjaxResult ajaxResult = AjaxResult.success();
      List<BasEmployee> doctors = new ArrayList();

      try {
         doctors = this.basEmployeeService.selectDoctors(new BasEmployeeVo());
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:employee:doctors,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/doctors"})
   public AjaxResult queryDeptDoctors() {
      AjaxResult ajaxResult = AjaxResult.success();
      List<BasEmployee> doctors = new ArrayList();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         BasEmployeeVo basEmployeeVo = new BasEmployeeVo();
         basEmployeeVo.setDeptId(user.getDept().getDeptCode());
         doctors = this.basEmployeeService.selectDoctors(basEmployeeVo);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:employee:doctors,mrhp:hp:mrHpDetail,td:apply:add,qc:rt:checkpatientList,qc:flow:term,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/doctorList"})
   public AjaxResult selectDeptDoctors(BasEmployeeVo basEmployeeVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<BasEmployee> doctors = new ArrayList();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         basEmployeeVo.setStaffNo(user.getUserId().toString());
         doctors = this.basEmployeeService.selectDoctors(basEmployeeVo);
      } catch (Exception e) {
         this.log.error("查询某个科室的医师出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }
}
