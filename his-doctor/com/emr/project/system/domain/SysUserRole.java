package com.emr.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysUserRole {
   private String userId;
   private String roleId;

   public SysUserRole() {
   }

   public SysUserRole(String userId, String roleId) {
      this.userId = userId;
      this.roleId = roleId;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("userId", this.getUserId()).append("roleId", this.getRoleId()).toString();
   }
}
