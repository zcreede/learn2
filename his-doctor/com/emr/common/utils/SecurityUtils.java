package com.emr.common.utils;

import com.emr.common.exception.CustomException;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
   public static String getUsername() {
      try {
         return getLoginUser().getUsername();
      } catch (Exception var1) {
         throw new CustomException("获取用户账户异常", 401);
      }
   }

   public static LoginUser getLoginUser() {
      Authentication authentication = getAuthentication();
      if (authentication != null) {
         Object principal = authentication.getPrincipal();
         if (principal instanceof LoginUser) {
            return (LoginUser)principal;
         }
      }

      return null;
   }

   public static Authentication getAuthentication() {
      return SecurityContextHolder.getContext().getAuthentication();
   }

   public static String encryptPassword(String password) {
      Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
      return md5PasswordEncoder.encode(password);
   }

   public static boolean matchesPassword(String rawPassword, String encodedPassword) {
      Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
      return md5PasswordEncoder.matches(rawPassword, encodedPassword);
   }

   public static boolean isAdmin(Long userId) {
      return userId != null && 1L == userId;
   }

   public static boolean currentUserIsAdmin() {
      SysUser user = getLoginUser().getUser();
      List<SysRole> roleList = user.getRoles();
      List<SysRole> adminRoles = null;
      if (roleList != null && !roleList.isEmpty()) {
         adminRoles = (List)roleList.stream().filter((r) -> r.isAdmin()).collect(Collectors.toList());
      }

      return SysUser.isAdmin(user.getUserId()) || adminRoles != null && !adminRoles.isEmpty();
   }
}
