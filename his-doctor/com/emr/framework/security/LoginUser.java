package com.emr.framework.security;

import com.emr.project.system.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails {
   private static final long serialVersionUID = 1L;
   private Long staffId;
   private String staffCode;
   private String token;
   private Long loginTime;
   private Long expireTime;
   private String ipaddr;
   private String loginLocation;
   private String browser;
   private String os;
   private Set permissions;
   private SysUser user;
   private String healthCode;

   public String getHealthCode() {
      return this.healthCode;
   }

   public void setHealthCode(String healthCode) {
      this.healthCode = healthCode;
   }

   public SysUser getUser() {
      return this.user;
   }

   public void setUser(SysUser user) {
      this.user = user;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public LoginUser() {
   }

   public LoginUser(SysUser user, Set permissions) {
      this.user = user;
      this.permissions = permissions;
   }

   @JsonIgnore
   public String getPassword() {
      return this.user.getPassword();
   }

   public String getUsername() {
      return this.user.getUserName();
   }

   @JsonIgnore
   public boolean isAccountNonExpired() {
      return true;
   }

   @JsonIgnore
   public boolean isAccountNonLocked() {
      return true;
   }

   @JsonIgnore
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @JsonIgnore
   public boolean isEnabled() {
      return true;
   }

   public Long getLoginTime() {
      return this.loginTime;
   }

   public void setLoginTime(Long loginTime) {
      this.loginTime = loginTime;
   }

   public String getIpaddr() {
      return this.ipaddr;
   }

   public void setIpaddr(String ipaddr) {
      this.ipaddr = ipaddr;
   }

   public String getLoginLocation() {
      return this.loginLocation;
   }

   public void setLoginLocation(String loginLocation) {
      this.loginLocation = loginLocation;
   }

   public String getBrowser() {
      return this.browser;
   }

   public void setBrowser(String browser) {
      this.browser = browser;
   }

   public String getOs() {
      return this.os;
   }

   public void setOs(String os) {
      this.os = os;
   }

   public Long getExpireTime() {
      return this.expireTime;
   }

   public void setExpireTime(Long expireTime) {
      this.expireTime = expireTime;
   }

   public Set getPermissions() {
      return this.permissions;
   }

   public void setPermissions(Set permissions) {
      this.permissions = permissions;
   }

   public Collection getAuthorities() {
      return null;
   }
}
