package com.emr.project.operation.domain.dto;

import com.emr.project.pat.domain.ExpenseDetail;
import java.math.BigDecimal;
import java.util.Date;

public class TakeDrugFeeDTO extends ExpenseDetail {
   private Date issueDate;
   private BigDecimal applyDose;
   private String caseNo;
   private String visitingStaffCode;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String wardNo;
   private String wardName;
   private BigDecimal returnedDose;
   private BigDecimal applyedDose;
   private String physicianName;
   private String patientName;
   private String patientId;
   private String drugStockNo;
   private String executorDptNo;
   private String pharmacyName;
   private String pharmacyNo;
   private String orderName;
   private String takeDrugWardNo;
   private String operatorDateTime;
   private String orderGroupSortNumber;
   private String operatorName;
   private String icon;
   private BigDecimal applyCount;
   private BigDecimal returnCount;

   public BigDecimal getApplyCount() {
      return this.applyCount;
   }

   public void setApplyCount(BigDecimal applyCount) {
      this.applyCount = applyCount;
   }

   public BigDecimal getReturnCount() {
      return this.returnCount;
   }

   public void setReturnCount(BigDecimal returnCount) {
      this.returnCount = returnCount;
   }

   public Date getIssueDate() {
      return this.issueDate;
   }

   public void setIssueDate(Date issueDate) {
      this.issueDate = issueDate;
   }

   public String getIcon() {
      return this.icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorDateTime() {
      return this.operatorDateTime;
   }

   public void setOperatorDateTime(String operatorDateTime) {
      this.operatorDateTime = operatorDateTime;
   }

   public String getTakeDrugWardNo() {
      return this.takeDrugWardNo;
   }

   public void setTakeDrugWardNo(String takeDrugWardNo) {
      this.takeDrugWardNo = takeDrugWardNo;
   }

   public TakeDrugFeeDTO() {
   }

   public String getOrderName() {
      return this.orderName;
   }

   public void setOrderName(String orderName) {
      this.orderName = orderName;
   }

   public TakeDrugFeeDTO(ExpenseDetail expenseDetail) {
      super.setHospitalCode(expenseDetail.getHospitalCode());
      super.setAdmissionNo(expenseDetail.getAdmissionNo());
      super.setHospitalizedCount(expenseDetail.getHospitalizedCount());
      super.setBillsNo(expenseDetail.getBillsNo());
      super.setPrescription(expenseDetail.getPerformListNo());
      super.setFilingDate(expenseDetail.getFilingDate());
      super.setProjectNo(expenseDetail.getProjectNo());
      super.setProjectName(expenseDetail.getProjectName());
      super.setThreeLevelAccounting(expenseDetail.getThreeLevelAccounting());
      super.setStandardCode(expenseDetail.getStandardCode());
      super.setStandardName(expenseDetail.getStandardName());
      super.setChargeCode(expenseDetail.getChargeCode());
      super.setChargeNo(expenseDetail.getChargeNo());
      super.setChargeName(expenseDetail.getChargeName());
      super.setStandard(expenseDetail.getStandard());
      super.setInsuranceCode(expenseDetail.getInsuranceCode());
      super.setUnit(expenseDetail.getUnit());
      super.setPrice(expenseDetail.getPrice());
      super.setDose(expenseDetail.getDose());
      super.setTotal(expenseDetail.getTotal());
      super.setCopeNo(expenseDetail.getCopeNo());
      super.setCopeSortNumber(expenseDetail.getCopeSortNumber());
      super.setItemSortNumber(expenseDetail.getItemSortNumber());
      super.setCopeGroup(expenseDetail.getCopeGroup());
      super.setCopeType(expenseDetail.getCopeType());
      super.setCopeClass(expenseDetail.getCopeClass());
      super.setItemCode(expenseDetail.getItemCode());
      super.setItemName(expenseDetail.getItemName());
      super.setBillsNoOld(expenseDetail.getBillsNoOld());
      super.setPayMark(expenseDetail.getPayMark());
      super.setValidity(expenseDetail.getValidity());
      super.setClosingDate(expenseDetail.getClosingDate());
      super.setMiddleSettlementMark(expenseDetail.getMiddleSettlementMark());
      super.setSettleAccount(expenseDetail.getSettleAccount());
      super.setSettleAccountNo(expenseDetail.getSettleAccountNo());
      super.setSettleAccountName(expenseDetail.getSettleAccountName());
      super.setStatisticsDate(expenseDetail.getStatisticsDate());
      super.setCpCode(expenseDetail.getCpCode());
      super.setCpName(expenseDetail.getCpName());
      super.setOperationNo(expenseDetail.getOperationNo());
      super.setOperationName(expenseDetail.getOperationName());
      super.setBabyFeeMark(expenseDetail.getBabyFeeMark());
      super.setUploadingMark(expenseDetail.getUploadingMark());
      super.setRegNo(expenseDetail.getRegNo());
      super.setSortNumber(expenseDetail.getSortNumber());
      super.setSourceProgram(expenseDetail.getSourceProgram());
      super.setPerformListNo(expenseDetail.getPerformListNo());
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public String getPharmacyNo() {
      return this.pharmacyNo;
   }

   public void setPharmacyNo(String pharmacyNo) {
      this.pharmacyNo = pharmacyNo;
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo;
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName;
   }

   public BigDecimal getReturnedDose() {
      return this.returnedDose;
   }

   public void setReturnedDose(BigDecimal returnedDose) {
      this.returnedDose = returnedDose;
   }

   public BigDecimal getApplyedDose() {
      return this.applyedDose;
   }

   public void setApplyedDose(BigDecimal applyedDose) {
      this.applyedDose = applyedDose;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getVisitingStaffCode() {
      return this.visitingStaffCode;
   }

   public void setVisitingStaffCode(String visitingStaffCode) {
      this.visitingStaffCode = visitingStaffCode;
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public BigDecimal getApplyDose() {
      return this.applyDose;
   }

   public void setApplyDose(BigDecimal applyDose) {
      this.applyDose = applyDose;
   }
}
