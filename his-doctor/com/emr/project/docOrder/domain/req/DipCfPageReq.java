package com.emr.project.docOrder.domain.req;

public class DipCfPageReq {
   private String patientId;
   private String orgCode;
   private String deptCode;
   private String userCode;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }
}
