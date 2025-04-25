package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.req.DeptDoctorsReq;
import com.emr.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息管理")
@RestController
@RequestMapping({"/system/user"})
public class SysUserMZController extends BaseController {
   @Autowired
   private ISysUserService userService;

   @PreAuthorize("@ss.hasAnyPermi('operation:pat:info,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/getStaffTypeBmy"})
   public AjaxResult getStaffTypeBmy(SysUser sysUser) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> StaffTypeBmy = new ArrayList();

      try {
         StaffTypeBmy = this.userService.getStaffTypeBmy(sysUser);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("StaffTypeBmy", StaffTypeBmy);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:user:doctors,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/doctors"})
   public AjaxResult queryDoctors(String staffName, String status) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> doctors = new ArrayList();

      try {
         doctors = this.userService.selectDoctorsByName((String)null, staffName, status);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:user:nurses,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/nurses"})
   public AjaxResult queryDeptNurses(DeptDoctorsReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> nurses = new ArrayList();

      try {
         String deptCode = req.getDeptCode();
         if (StringUtils.isBlank(deptCode)) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            deptCode = user.getDept().getDeptCode();
         }

         nurses = this.userService.selectNurses(deptCode, req.getStatus());
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("nurses", nurses);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:pat:info,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/doctors"})
   public AjaxResult queryDeptDoctors(DeptDoctorsReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> doctors = new ArrayList();

      try {
         String deptCode = req.getDeptCode();
         if (StringUtils.isBlank(deptCode)) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            deptCode = user.getDept().getDeptCode();
         }

         doctors = this.userService.selectDoctors(deptCode, req.getStatus());
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('query:index:doctorList,system:user:doctors,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/doctorsAndTechnician"})
   public AjaxResult doctorsAndTechnician(String staffName, String status) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> doctors = new ArrayList();

      try {
         doctors = this.userService.doctorsAndTechnician((String)null, staffName, status);
      } catch (Exception e) {
         this.log.error("查询医师和技师出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }
}
