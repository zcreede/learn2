package com.emr.project.operation.domain.req;

public class RefundAccountListReq {
   private String admissionNo;
   private String startTime;
   private String endTime;
   private String threeLevelCode;
   private String chargeName;

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

   public String getThreeLevelCode() {
      return this.threeLevelCode;
   }

   public void setThreeLevelCode(String threeLevelCode) {
      this.threeLevelCode = threeLevelCode;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }
}
