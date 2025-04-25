package com.emr.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysEmployeeRoleDept {
   private String employeeId;
   private String roleId;
   private String deptId;

   public String getEmployeeId() {
      return this.employeeId;
   }

   public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
   }

   public String getRoleId() {
      return this.roleId;
   }

   public void setRoleId(String roleId) {
      this.roleId = roleId;
   }

   public String getDeptId() {
      return this.deptId;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("employeeId", this.getEmployeeId()).append("roleId", this.getRoleId()).append("deptId", this.getDeptId()).toString();
   }
}
