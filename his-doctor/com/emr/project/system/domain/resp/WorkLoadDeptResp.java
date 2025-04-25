package com.emr.project.system.domain.resp;

import java.io.Serializable;

public class WorkLoadDeptResp implements Serializable {
   private String deptCode;
   private String deptName;

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }
}
