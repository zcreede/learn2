package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.SysPermissionService;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.SysUserRole;
import com.emr.project.system.service.ISysRoleService;
import com.emr.project.system.service.ISysUserService;
import java.util.List;
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

@RestController
@RequestMapping({"/system/role"})
public class SysRoleController extends BaseController {
   @Autowired
   private ISysRoleService roleService;
   @Autowired
   private TokenService tokenService;
   @Autowired
   private SysPermissionService permissionService;
   @Autowired
   private ISysUserService userService;

   @PreAuthorize("@ss.hasPermi('system:role:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysRole role) {
      this.startPage();
      List<SysRole> list = this.roleService.selectRoleList(role);
      return this.getDataTable(list);
   }

   @Log(
      title = "角色管理",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:role:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysRole role) {
      List<SysRole> list = this.roleService.selectRoleList(role);
      ExcelUtil<SysRole> util = new ExcelUtil(SysRole.class);
      return util.exportExcel(list, "角色数据");
   }

   @PreAuthorize("@ss.hasPermi('system:role:query')")
   @GetMapping({"/{roleId}"})
   public AjaxResult getInfo(@PathVariable Long roleId) {
      return AjaxResult.success((Object)this.roleService.selectRoleById(roleId));
   }

   @PreAuthorize("@ss.hasPermi('system:role:add')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysRole role) {
      if ("1".equals(this.roleService.checkRoleNameUnique(role))) {
         return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
      } else if ("1".equals(this.roleService.checkRoleKeyUnique(role))) {
         return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
      } else {
         role.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.roleService.insertRole(role));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysRole role) {
      this.roleService.checkRoleAllowed(role);
      if ("1".equals(this.roleService.checkRoleNameUnique(role))) {
         return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
      } else if ("1".equals(this.roleService.checkRoleKeyUnique(role))) {
         return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
      } else {
         role.setUpdateBy(SecurityUtils.getUsername());
         if (this.roleService.updateRole(role) > 0) {
            LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
               loginUser.setPermissions(this.permissionService.getMenuPermission(loginUser.getUser()));
               loginUser.setUser(this.userService.selectUserByUserName(loginUser.getUser().getUserName()));
               this.tokenService.setLoginUser(loginUser);
            }

            return AjaxResult.success();
         } else {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/dataScope"})
   public AjaxResult dataScope(@RequestBody SysRole role) {
      this.roleService.checkRoleAllowed(role);
      return this.toAjax(this.roleService.authDataScope(role));
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/changeStatus"})
   public AjaxResult changeStatus(@RequestBody SysRole role) {
      this.roleService.checkRoleAllowed(role);
      role.setUpdateBy(SecurityUtils.getUsername());
      return this.toAjax(this.roleService.updateRoleStatus(role));
   }

   @PreAuthorize("@ss.hasPermi('system:role:remove')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{roleIds}"})
   public AjaxResult remove(@PathVariable Long[] roleIds) {
      return this.toAjax(this.roleService.deleteRoleByIds(roleIds));
   }

   @PreAuthorize("@ss.hasPermi('system:role:query')")
   @GetMapping({"/optionselect"})
   public AjaxResult optionselect() {
      return AjaxResult.success((Object)this.roleService.selectRoleAll());
   }

   @PreAuthorize("@ss.hasPermi('system:role:list')")
   @GetMapping({"/authUser/allocatedList"})
   public TableDataInfo allocatedList(SysUser user) {
      this.startPage();
      List<SysUser> list = this.userService.selectAllocatedList(user);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:role:list')")
   @GetMapping({"/authUser/unallocatedList"})
   public TableDataInfo unallocatedList(SysUser user) {
      this.startPage();
      List<SysUser> list = this.userService.selectUnallocatedList(user);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.GRANT
   )
   @PutMapping({"/authUser/cancel"})
   public AjaxResult cancelAuthUser(@RequestBody SysUserRole userRole) {
      return this.toAjax(this.roleService.deleteAuthUser(userRole));
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.GRANT
   )
   @PutMapping({"/authUser/cancelAll"})
   public AjaxResult cancelAuthUserAll(Long roleId, Long[] userIds) {
      return this.toAjax(this.roleService.deleteAuthUsers(roleId, userIds));
   }

   @PreAuthorize("@ss.hasPermi('system:role:edit')")
   @Log(
      title = "角色管理",
      businessType = BusinessType.GRANT
   )
   @PutMapping({"/authUser/selectAll"})
   public AjaxResult selectAuthUserAll(Long roleId, Long[] userIds) {
      return this.toAjax(this.roleService.insertAuthUsers(roleId, userIds));
   }
}
