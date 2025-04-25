package com.emr.project.system.domain;

import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysUserDept extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String userId;
   private String deptId;
   private String flag;

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String flag) {
      this.flag = flag;
   }

   public SysUserDept() {
   }

   public SysUserDept(String userId, String deptId) {
      this.userId = userId;
      this.deptId = deptId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String getDeptId() {
      return this.deptId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("userId", this.getUserId()).append("deptId", this.getDeptId()).toString();
   }
}
