package com.emr.framework.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.emr.project.system.domain.BsStaff;
import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;

public class BsLoginStaff implements UserDetails {
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
   private BsStaff bsStaff;

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public BsLoginStaff() {
   }

   public BsLoginStaff(Long staffId, String staffCode, BsStaff bsStaff) {
      this.staffId = staffId;
      this.staffCode = staffCode;
      this.bsStaff = bsStaff;
   }

   @JSONField(
      serialize = false
   )
   public String getPassword() {
      return this.bsStaff.getPassword();
   }

   public String getUsername() {
      return this.bsStaff.getStaffName();
   }

   @JSONField(
      serialize = false
   )
   public boolean isAccountNonExpired() {
      return true;
   }

   @JSONField(
      serialize = false
   )
   public boolean isAccountNonLocked() {
      return true;
   }

   @JSONField(
      serialize = false
   )
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @JSONField(
      serialize = false
   )
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

   public BsStaff getBsStaff() {
      return this.bsStaff;
   }

   public void setBsStaff(BsStaff bsStaff) {
      this.bsStaff = bsStaff;
   }

   public Long getStaffId() {
      return this.staffId;
   }

   public void setStaffId(Long staffId) {
      this.staffId = staffId;
   }

   public Collection getAuthorities() {
      return null;
   }
}
