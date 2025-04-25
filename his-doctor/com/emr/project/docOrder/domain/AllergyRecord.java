package com.emr.project.docOrder.domain;

import java.util.Date;

public class AllergyRecord {
   private Long id;
   private String hospitalCode;
   private String caseNo;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String patientName;
   private String patientSex;
   private String patientAge;
   private String allergyCode;
   private String allergyName;
   private String allergyType;
   private Date operatorDate;
   private String operatorNo;
   private String operatorName;
   private String operatorDateStr;
   private String batchNumber;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getBatchNumber() {
      return this.batchNumber;
   }

   public void setBatchNumber(String batchNumber) {
      this.batchNumber = batchNumber;
   }

   public String getOperatorDateStr() {
      return this.operatorDateStr;
   }

   public void setOperatorDateStr(String operatorDateStr) {
      this.operatorDateStr = operatorDateStr;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo == null ? null : caseNo.trim();
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo == null ? null : admissionNo.trim();
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName == null ? null : patientName.trim();
   }

   public String getPatientSex() {
      return this.patientSex;
   }

   public void setPatientSex(String patientSex) {
      this.patientSex = patientSex == null ? null : patientSex.trim();
   }

   public String getPatientAge() {
      return this.patientAge;
   }

   public void setPatientAge(String patientAge) {
      this.patientAge = patientAge == null ? null : patientAge.trim();
   }

   public String getAllergyCode() {
      return this.allergyCode;
   }

   public void setAllergyCode(String allergyCode) {
      this.allergyCode = allergyCode == null ? null : allergyCode.trim();
   }

   public String getAllergyName() {
      return this.allergyName;
   }

   public void setAllergyName(String allergyName) {
      this.allergyName = allergyName == null ? null : allergyName.trim();
   }

   public String getAllergyType() {
      return this.allergyType;
   }

   public void setAllergyType(String allergyType) {
      this.allergyType = allergyType == null ? null : allergyType.trim();
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
      this.operatorNo = operatorNo == null ? null : operatorNo.trim();
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName == null ? null : operatorName.trim();
   }
}
