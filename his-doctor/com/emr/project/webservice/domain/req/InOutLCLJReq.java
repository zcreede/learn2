package com.emr.project.webservice.domain.req;

public class InOutLCLJReq {
   private String projectCode;
   private String sign;
   private String admissionNo;
   private String inOutFlag;
   private String cpNo;
   private String cpName;

   public String getProjectCode() {
      return this.projectCode;
   }

   public void setProjectCode(String projectCode) {
      this.projectCode = projectCode;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getInOutFlag() {
      return this.inOutFlag;
   }

   public void setInOutFlag(String inOutFlag) {
      this.inOutFlag = inOutFlag;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }
}
