package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmployeeRoleDeptService;
import com.emr.project.system.service.ISysPostService;
import com.emr.project.system.service.ISysRoleService;
import com.emr.project.system.service.ISysUserDeptService;
import com.emr.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api("用户信息管理")
@RestController
@RequestMapping({"/system1/user"})
public class SysUserController extends BaseController {
   @Autowired
   private ISysUserService userService;
   @Autowired
   private ISysRoleService roleService;
   @Autowired
   private ISysPostService postService;
   @Autowired
   private TokenService tokenService;
   @Autowired
   private ISysUserDeptService userDeptService;
   @Autowired
   private ISysEmployeeRoleDeptService userRoleDeptService;

   @PreAuthorize("@ss.hasPermi('system:user:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysUser user) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysUser> list = this.userService.selectUserList(user);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("获取用户列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "获取用户列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:user:listByDept,other:doctor:listByDept')")
   @GetMapping({"/listByDept"})
   public TableDataInfo listByDept(SysUser user) {
      List<SysUser> list = null;

      try {
         this.startPage();
         list = this.userService.selectUserListByDeptId(user);
      } catch (Exception e) {
         this.log.error("获取用户列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @Log(
      title = "用户管理",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:user:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysUser user) {
      List<SysUser> list = this.userService.selectUserList(user);
      ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
      return util.exportExcel(list, "用户数据");
   }

   @Log(
      title = "用户管理",
      businessType = BusinessType.IMPORT
   )
   @PreAuthorize("@ss.hasPermi('system:user:import')")
   @PostMapping({"/importData"})
   public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
      ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
      List<SysUser> userList = util.importExcel(file.getInputStream());
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      String operName = loginUser.getUsername();
      String message = this.userService.importUser(userList, updateSupport, operName);
      return AjaxResult.success(message);
   }

   @GetMapping({"/importTemplate"})
   public AjaxResult importTemplate() {
      ExcelUtil<SysUser> util = new ExcelUtil(SysUser.class);
      return util.importTemplateExcel("用户数据");
   }

   @PreAuthorize("@ss.hasPermi('system:user:query')")
   @GetMapping({"/", "/{userId}"})
   public AjaxResult getInfo(@PathVariable(value = "userId",required = false) Long userId) {
      AjaxResult ajax = AjaxResult.success();
      List<SysRole> roles = this.roleService.selectRoleAll();
      ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter((r) -> !r.isAdmin()).collect(Collectors.toList()));
      ajax.put("posts", this.postService.selectPostAll());
      if (StringUtils.isNotNull(userId)) {
         ajax.put("data", this.userService.selectUserById(userId));
         ajax.put("postIds", this.postService.selectPostListByUserId(userId));
         ajax.put("roleIds", this.roleService.selectRoleListByUserId(userId));
         ajax.put("userRoleDepts", this.userRoleDeptService.selectByUserId(userId));
      }

      return ajax;
   }

   @ApiOperation("新增用户")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "userId",
   value = "用户ID",
   dataType = "Long",
   required = true
), @ApiImplicitParam(
   name = "userName",
   value = "登录名称",
   dataType = "Long",
   required = true
), @ApiImplicitParam(
   name = "deptIds",
   value = "部门ID集合",
   dataType = "Array"
), @ApiImplicitParam(
   name = "roleIds",
   value = "角色ID集合",
   dataType = "Array"
), @ApiImplicitParam(
   name = "postIds",
   value = "岗位ID集合",
   dataType = "Array"
)})
   @PreAuthorize("@ss.hasPermi('system:user:add')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysUser user) {
      try {
         if ("1".equals(this.userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
         } else if (StringUtils.isNotEmpty(user.getPhonenumber()) && "1".equals(this.userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
         } else if (StringUtils.isNotEmpty(user.getEmail()) && "1".equals(this.userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
         } else {
            user.setCreateBy(SecurityUtils.getUsername());
            user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
            return this.toAjax(this.userService.insertUser(user));
         }
      } catch (Exception e) {
         this.log.error("新增用户出现异常", e);
         return AjaxResult.error("新增用户'" + user.getUserName() + "'出现异常,请联系管理员");
      }
   }

   @ApiOperation("修改用户")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "userId",
   value = "用户ID",
   dataType = "Long",
   required = true
), @ApiImplicitParam(
   name = "userName",
   value = "登录名称",
   dataType = "Long",
   required = true
), @ApiImplicitParam(
   name = "deptIds",
   value = "部门ID集合",
   dataType = "Array"
), @ApiImplicitParam(
   name = "roleIds",
   value = "角色ID集合",
   dataType = "Array"
), @ApiImplicitParam(
   name = "postIds",
   value = "岗位ID集合",
   dataType = "Array"
)})
   @PreAuthorize("@ss.hasPermi('system:user:edit')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysUser user) {
      try {
         this.userService.checkUserAllowed(user);
         if (StringUtils.isNotEmpty(user.getPhonenumber()) && "1".equals(this.userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
         } else if (StringUtils.isNotEmpty(user.getEmail()) && "1".equals(this.userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
         } else {
            user.setUpdateBy(SecurityUtils.getUsername());
            return this.toAjax(this.userService.updateUser(user));
         }
      } catch (Exception e) {
         this.log.error("修改用户出现异常", e);
         return AjaxResult.error("修改用户'" + user.getUserName() + "'出现异常,请联系管理员");
      }
   }

   @PreAuthorize("@ss.hasPermi('system:user:remove')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{userIds}"})
   public AjaxResult remove(@PathVariable Long[] userIds) {
      return this.toAjax(this.userService.deleteUserByIds(userIds));
   }

   @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/resetPwd"})
   public AjaxResult resetPwd(@RequestBody SysUser user) {
      this.userService.checkUserAllowed(user);
      user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
      user.setUpdateBy(SecurityUtils.getUsername());
      return this.toAjax(this.userService.resetPwd(user));
   }

   @PreAuthorize("@ss.hasPermi('system:user:edit')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/changeStatus"})
   public AjaxResult changeStatus(@RequestBody SysUser user) {
      this.userService.checkUserAllowed(user);
      user.setUpdateBy(SecurityUtils.getUsername());
      return this.toAjax(this.userService.updateUserStatus(user));
   }

   @PreAuthorize("@ss.hasPermi('system:user:query')")
   @GetMapping({"/authRole/{userId}"})
   public AjaxResult authRole(@PathVariable("userId") Long userId) {
      AjaxResult ajax = AjaxResult.success();
      SysUser user = this.userService.selectUserById(userId);
      List<SysRole> roles = this.roleService.selectRolesByUserId(userId);
      ajax.put("user", user);
      ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter((r) -> !r.isAdmin()).collect(Collectors.toList()));
      return ajax;
   }

   @PreAuthorize("@ss.hasPermi('system:user:edit')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.GRANT
   )
   @PutMapping({"/authRole"})
   public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
      this.userService.insertUserAuth(userId, roleIds);
      return this.success();
   }

   @PreAuthorize("@ss.hasPermi('system:user:queryUserDeptList')")
   @Log(
      title = "用户管理",
      businessType = BusinessType.GRANT
   )
   @GetMapping({"/queryUserDeptList"})
   public AjaxResult queryUserDeptList(Long userId) {
      return this.success();
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

   @PreAuthorize("@ss.hasAnyPermi('system:user:nurses,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/nurses"})
   public AjaxResult queryNurses(String status) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> nurses = new ArrayList();

      try {
         nurses = this.userService.selectNurses((String)null, status);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("nurses", nurses);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:user:nurses,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/nurses"})
   public AjaxResult queryDeptNurses(String status) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> nurses = new ArrayList();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         nurses = this.userService.selectNurses(user.getDept().getDeptCode(), status);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("nurses", nurses);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('operation:pat:info,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/dept/doctors"})
   public AjaxResult queryDeptDoctors(String status) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<SysUser> doctors = new ArrayList();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         doctors = this.userService.selectDoctors(user.getDept().getDeptCode(), status);
      } catch (Exception e) {
         this.log.error("查询某个角色的用户出现异常,", e);
      }

      ajaxResult.put("doctors", doctors);
      return ajaxResult;
   }

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
}
