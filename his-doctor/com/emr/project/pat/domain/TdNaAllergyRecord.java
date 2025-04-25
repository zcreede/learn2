package com.emr.project.pat.domain;

import java.util.Date;

public class TdNaAllergyRecord {
   private String hospitalCode;
   private String caseNo;
   private String admissionNo;
   private String hospitalizedCount;
   private String patientName;
   private String patientSex;
   private String patientAge;
   private String allergyCode;
   private String allergyName;
   private String allergyType;
   private Date operatorDate;
   private String operatorNo;
   private String operatorName;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientSex() {
      return this.patientSex;
   }

   public void setPatientSex(String patientSex) {
      this.patientSex = patientSex;
   }

   public String getPatientAge() {
      return this.patientAge;
   }

   public void setPatientAge(String patientAge) {
      this.patientAge = patientAge;
   }

   public String getAllergyCode() {
      return this.allergyCode;
   }

   public void setAllergyCode(String allergyCode) {
      this.allergyCode = allergyCode;
   }

   public String getAllergyName() {
      return this.allergyName;
   }

   public void setAllergyName(String allergyName) {
      this.allergyName = allergyName;
   }

   public String getAllergyType() {
      return this.allergyType;
   }

   public void setAllergyType(String allergyType) {
      this.allergyType = allergyType;
   }

   public Date getOperatorDate() {
      return this.operatorDate;
   }

   public void setOperatorDate(Date operatorDate) {
      this.operatorDate = operatorDate;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }
}
