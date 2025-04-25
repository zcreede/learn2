package com.emr.project.operation.domain.req;

public class TakeDrugFeeReq {
   private String admissionNo;
   private String startTime;
   private String endTime;
   private String deptCode;
   private String drugName;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }
}
