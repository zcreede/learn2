package com.emr.framework.security.service;

import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysMenuService;
import com.emr.project.system.service.ISysRoleService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysPermissionService {
   @Autowired
   private ISysRoleService roleService;
   @Autowired
   private ISysMenuService menuService;
   @Autowired
   private IBasEmployeeService basEmployeeService;

   public Set getRolePermission(SysUser user) {
      Set<String> roles = new HashSet();
      if (user.isAdmin()) {
         roles.add("admin");
      } else {
         BasEmployee basEmployee = this.basEmployeeService.selectByEmplNumber(user.getBasEmployee().getEmplNumber());
         roles.addAll(this.roleService.selectRolePermissionByUserId(basEmployee.getId(), user.getDeptId()));
      }

      return roles;
   }

   public Set getMenuPermission(SysUser user) {
      Set<String> perms = new HashSet();
      if (user.isAdmin()) {
         perms.add("*:*:*");
      } else {
         BasEmployee basEmployee = this.basEmployeeService.selectByEmplNumber(user.getBasEmployee().getEmplNumber());
         perms.addAll(this.menuService.selectMenuPermsByUserId(basEmployee.getEmplNumber(), user.getDeptCode()));
      }

      return perms;
   }
}
