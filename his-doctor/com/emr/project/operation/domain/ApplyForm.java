package com.emr.project.operation.domain;

import java.util.Date;

public class ApplyForm {
   private String applyFormNo;
   private String applyFormClassCode;
   private String applyFormTypeCode;
   private String orderNo;
   private String orderSortNumber;
   private String caseNo;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String patientName;
   private String patientDepCode;
   private Date applyTime;
   private String orderDoctorCode;
   private String performDepCode;
   private String purposeExamination;
   private String examCategory;
   private String examPart;
   private String diagnosisCode;
   private String diagnosisName;
   private String sampleNo;
   private String sampleName;
   private String medicalRecordDigest;
   private String applyFormStatus;
   private String operatorCode;
   private String remark;
   private String urgentFlag;
   private Date settleAccountDate;
   private String settleAccountCode;
   private String settleAccountNo;
   private String settleAccountName;

   public ApplyForm() {
   }

   public ApplyForm(String applyFormNo, String applyFormStatus, Date settleAccountDate, String settleAccountCode, String settleAccountNo, String settleAccountName) {
      this.applyFormNo = applyFormNo;
      this.applyFormStatus = applyFormStatus;
      this.settleAccountDate = settleAccountDate;
      this.settleAccountCode = settleAccountCode;
      this.settleAccountNo = settleAccountNo;
      this.settleAccountName = settleAccountName;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo == null ? null : applyFormNo.trim();
   }

   public String getApplyFormClassCode() {
      return this.applyFormClassCode;
   }

   public void setApplyFormClassCode(String applyFormClassCode) {
      this.applyFormClassCode = applyFormClassCode == null ? null : applyFormClassCode.trim();
   }

   public String getApplyFormTypeCode() {
      return this.applyFormTypeCode;
   }

   public void setApplyFormTypeCode(String applyFormTypeCode) {
      this.applyFormTypeCode = applyFormTypeCode == null ? null : applyFormTypeCode.trim();
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo == null ? null : orderNo.trim();
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber == null ? null : orderSortNumber.trim();
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

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode == null ? null : patientDepCode.trim();
   }

   public Date getApplyTime() {
      return this.applyTime;
   }

   public void setApplyTime(Date applyTime) {
      this.applyTime = applyTime;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode == null ? null : orderDoctorCode.trim();
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode == null ? null : performDepCode.trim();
   }

   public String getPurposeExamination() {
      return this.purposeExamination;
   }

   public void setPurposeExamination(String purposeExamination) {
      this.purposeExamination = purposeExamination == null ? null : purposeExamination.trim();
   }

   public String getExamCategory() {
      return this.examCategory;
   }

   public void setExamCategory(String examCategory) {
      this.examCategory = examCategory == null ? null : examCategory.trim();
   }

   public String getExamPart() {
      return this.examPart;
   }

   public void setExamPart(String examPart) {
      this.examPart = examPart == null ? null : examPart.trim();
   }

   public String getDiagnosisCode() {
      return this.diagnosisCode;
   }

   public void setDiagnosisCode(String diagnosisCode) {
      this.diagnosisCode = diagnosisCode == null ? null : diagnosisCode.trim();
   }

   public String getDiagnosisName() {
      return this.diagnosisName;
   }

   public void setDiagnosisName(String diagnosisName) {
      this.diagnosisName = diagnosisName == null ? null : diagnosisName.trim();
   }

   public String getSampleNo() {
      return this.sampleNo;
   }

   public void setSampleNo(String sampleNo) {
      this.sampleNo = sampleNo == null ? null : sampleNo.trim();
   }

   public String getSampleName() {
      return this.sampleName;
   }

   public void setSampleName(String sampleName) {
      this.sampleName = sampleName == null ? null : sampleName.trim();
   }

   public String getMedicalRecordDigest() {
      return this.medicalRecordDigest;
   }

   public void setMedicalRecordDigest(String medicalRecordDigest) {
      this.medicalRecordDigest = medicalRecordDigest == null ? null : medicalRecordDigest.trim();
   }

   public String getApplyFormStatus() {
      return this.applyFormStatus;
   }

   public void setApplyFormStatus(String applyFormStatus) {
      this.applyFormStatus = applyFormStatus == null ? null : applyFormStatus.trim();
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode == null ? null : operatorCode.trim();
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark == null ? null : remark.trim();
   }

   public String getUrgentFlag() {
      return this.urgentFlag;
   }

   public void setUrgentFlag(String urgentFlag) {
      this.urgentFlag = urgentFlag == null ? null : urgentFlag.trim();
   }

   public Date getSettleAccountDate() {
      return this.settleAccountDate;
   }

   public void setSettleAccountDate(Date settleAccountDate) {
      this.settleAccountDate = settleAccountDate;
   }

   public String getSettleAccountCode() {
      return this.settleAccountCode;
   }

   public void setSettleAccountCode(String settleAccountCode) {
      this.settleAccountCode = settleAccountCode;
   }

   public String getSettleAccountNo() {
      return this.settleAccountNo;
   }

   public void setSettleAccountNo(String settleAccountNo) {
      this.settleAccountNo = settleAccountNo;
   }

   public String getSettleAccountName() {
      return this.settleAccountName;
   }

   public void setSettleAccountName(String settleAccountName) {
      this.settleAccountName = settleAccountName;
   }
}
