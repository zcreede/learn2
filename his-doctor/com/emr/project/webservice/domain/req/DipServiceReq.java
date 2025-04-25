package com.emr.project.webservice.domain.req;

public class DipServiceReq {
   private String checkType;
   private String admissionNo;
   private Integer hospitalizedCount;

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getCheckType() {
      return this.checkType;
   }

   public void setCheckType(String checkType) {
      this.checkType = checkType;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }
}
