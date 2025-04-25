package com.emr.project.operation.domain;

import java.util.Date;

public class PatFee {
   private String hospitalCode;
   private String caseNo;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String babyNo;
   private String prescription;
   private Date filingDate;
   private String operatorCode;
   private String operator;
   private String operatorName;
   private String wardNo;
   private String wardName;
   private String departmentNo;
   private String departmentName;
   private String medicalGroupV;
   private String visitingStaffCode;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String medicalGroupP;
   private String physicianCode;
   private String physicianNo;
   private String physicianName;
   private String physicianDptNo;
   private String physicianDptName;
   private String nursingGroup;
   private String executorCode;
   private String executorNo;
   private String executorName;
   private String executorDptNo;
   private String executorDptName;
   private String accountingDepartmentNo;
   private String accountingDepartmentName;
   private String sourceProgram;

   public String getSourceProgram() {
      return this.sourceProgram;
   }

   public void setSourceProgram(String sourceProgram) {
      this.sourceProgram = sourceProgram;
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

   public String getBabyNo() {
      return this.babyNo;
   }

   public void setBabyNo(String babyNo) {
      this.babyNo = babyNo == null ? null : babyNo.trim();
   }

   public String getPrescription() {
      return this.prescription;
   }

   public void setPrescription(String prescription) {
      this.prescription = prescription;
   }

   public Date getFilingDate() {
      return this.filingDate;
   }

   public void setFilingDate(Date filingDate) {
      this.filingDate = filingDate;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode == null ? null : operatorCode.trim();
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator == null ? null : operator.trim();
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName == null ? null : operatorName.trim();
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName == null ? null : wardName.trim();
   }

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo == null ? null : departmentNo.trim();
   }

   public String getDepartmentName() {
      return this.departmentName;
   }

   public void setDepartmentName(String departmentName) {
      this.departmentName = departmentName == null ? null : departmentName.trim();
   }

   public String getMedicalGroupV() {
      return this.medicalGroupV;
   }

   public void setMedicalGroupV(String medicalGroupV) {
      this.medicalGroupV = medicalGroupV == null ? null : medicalGroupV.trim();
   }

   public String getVisitingStaffCode() {
      return this.visitingStaffCode;
   }

   public void setVisitingStaffCode(String visitingStaffCode) {
      this.visitingStaffCode = visitingStaffCode == null ? null : visitingStaffCode.trim();
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo == null ? null : visitingStaffNo.trim();
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName == null ? null : visitingStaffName.trim();
   }

   public String getMedicalGroupP() {
      return this.medicalGroupP;
   }

   public void setMedicalGroupP(String medicalGroupP) {
      this.medicalGroupP = medicalGroupP == null ? null : medicalGroupP.trim();
   }

   public String getPhysicianCode() {
      return this.physicianCode;
   }

   public void setPhysicianCode(String physicianCode) {
      this.physicianCode = physicianCode == null ? null : physicianCode.trim();
   }

   public String getPhysicianNo() {
      return this.physicianNo;
   }

   public void setPhysicianNo(String physicianNo) {
      this.physicianNo = physicianNo == null ? null : physicianNo.trim();
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName == null ? null : physicianName.trim();
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo == null ? null : physicianDptNo.trim();
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName == null ? null : physicianDptName.trim();
   }

   public String getNursingGroup() {
      return this.nursingGroup;
   }

   public void setNursingGroup(String nursingGroup) {
      this.nursingGroup = nursingGroup == null ? null : nursingGroup.trim();
   }

   public String getExecutorCode() {
      return this.executorCode;
   }

   public void setExecutorCode(String executorCode) {
      this.executorCode = executorCode == null ? null : executorCode.trim();
   }

   public String getExecutorNo() {
      return this.executorNo;
   }

   public void setExecutorNo(String executorNo) {
      this.executorNo = executorNo == null ? null : executorNo.trim();
   }

   public String getExecutorName() {
      return this.executorName;
   }

   public void setExecutorName(String executorName) {
      this.executorName = executorName == null ? null : executorName.trim();
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo == null ? null : executorDptNo.trim();
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName == null ? null : executorDptName.trim();
   }

   public String getAccountingDepartmentNo() {
      return this.accountingDepartmentNo;
   }

   public void setAccountingDepartmentNo(String accountingDepartmentNo) {
      this.accountingDepartmentNo = accountingDepartmentNo == null ? null : accountingDepartmentNo.trim();
   }

   public String getAccountingDepartmentName() {
      return this.accountingDepartmentName;
   }

   public void setAccountingDepartmentName(String accountingDepartmentName) {
      this.accountingDepartmentName = accountingDepartmentName == null ? null : accountingDepartmentName.trim();
   }
}
