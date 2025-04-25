package com.emr.project.mrhp.domain.req;

public class MrHpRecordDipReq {
   private String orgCode;
   private String deptCode;
   private String patientId;
   private String userCode;
   private String qutyType;

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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getQutyType() {
      return this.qutyType;
   }

   public void setQutyType(String qutyType) {
      this.qutyType = qutyType;
   }
}
