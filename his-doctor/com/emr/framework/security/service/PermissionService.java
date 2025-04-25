package com.emr.framework.security.service;

import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysRole;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service("ss")
public class PermissionService {
   private static final String ALL_PERMISSION = "*:*:*";
   private static final String SUPER_ADMIN = "admin";
   private static final String ROLE_DELIMETER = ",";
   private static final String PERMISSION_DELIMETER = ",";
   @Autowired
   private TokenService tokenService;

   public boolean hasPermi(String permission) {
      if (StringUtils.isEmpty(permission)) {
         return false;
      } else {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         return !StringUtils.isNull(loginUser) && !CollectionUtils.isEmpty(loginUser.getPermissions()) ? this.hasPermissions(loginUser.getPermissions(), permission) : false;
      }
   }

   public boolean lacksPermi(String permission) {
      return !this.hasPermi(permission);
   }

   public boolean hasAnyPermi(String permissions) {
      if (StringUtils.isEmpty(permissions)) {
         return false;
      } else {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         if (!StringUtils.isNull(loginUser) && !CollectionUtils.isEmpty(loginUser.getPermissions())) {
            Set<String> authorities = loginUser.getPermissions();

            for(String permission : permissions.split(",")) {
               if (permission != null && this.hasPermissions(authorities, permission)) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   public boolean hasRole(String role) {
      if (StringUtils.isEmpty(role)) {
         return false;
      } else {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         if (!StringUtils.isNull(loginUser) && !CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
            for(SysRole sysRole : loginUser.getUser().getRoles()) {
               String roleKey = sysRole.getRoleKey();
               if ("admin".equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   public boolean lacksRole(String role) {
      return !this.hasRole(role);
   }

   public boolean hasAnyRoles(String roles) {
      if (StringUtils.isEmpty(roles)) {
         return false;
      } else {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         if (!StringUtils.isNull(loginUser) && !CollectionUtils.isEmpty(loginUser.getUser().getRoles())) {
            for(String role : roles.split(",")) {
               if (this.hasRole(role)) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   private boolean hasPermissions(Set permissions, String permission) {
      return permissions.contains("*:*:*") || permissions.contains(StringUtils.trim(permission));
   }
}
