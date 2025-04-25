package com.emr.project.operation.domain;

public class MedicalinformationKey {
   private String admissionNo;
   private String hospitalCode;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo == null ? null : admissionNo.trim();
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }
}
