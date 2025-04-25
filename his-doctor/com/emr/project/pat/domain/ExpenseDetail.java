package com.emr.project.pat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class ExpenseDetail {
   private String hospitalCode;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String billsNo;
   private String prescription;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date filingDate;
   private String projectNo;
   private String projectName;
   private String threeLevelAccounting;
   private String threeLevelName;
   private String standardCode;
   private String standardName;
   private String chargeCode;
   private String chargeNo;
   private String chargeName;
   private String standard;
   private String insuranceCode;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private String copeNo;
   private String copeSortNumber;
   private String itemSortNumber;
   private String copeGroup;
   private String copeType;
   private String copeClass;
   private String itemCode;
   private String itemName;
   private String billsNoOld;
   private String payMark;
   private Date validity;
   private Date closingDate;
   private String middleSettlementMark;
   private String settleAccount;
   private String settleAccountNo;
   private String settleAccountName;
   private Date statisticsDate;
   private String cpCode;
   private String cpName;
   private String operationNo;
   private String operationName;
   private String babyFeeMark;
   private String uploadingMark;
   private String regNo;
   private Integer sortNumber;
   private String sourceProgram;
   private String performListNo;
   private String performListSortNumber;
   private String medicalInsuranceCode;
   private String medicalInsuranceName;
   private String ratioOwnExpense;
   private String projectGrade;
   private String drugStockNo;

   public String getMedicalInsuranceCode() {
      return this.medicalInsuranceCode;
   }

   public void setMedicalInsuranceCode(String medicalInsuranceCode) {
      this.medicalInsuranceCode = medicalInsuranceCode;
   }

   public String getMedicalInsuranceName() {
      return this.medicalInsuranceName;
   }

   public void setMedicalInsuranceName(String medicalInsuranceName) {
      this.medicalInsuranceName = medicalInsuranceName;
   }

   public String getRatioOwnExpense() {
      return this.ratioOwnExpense;
   }

   public void setRatioOwnExpense(String ratioOwnExpense) {
      this.ratioOwnExpense = ratioOwnExpense;
   }

   public String getProjectGrade() {
      return this.projectGrade;
   }

   public void setProjectGrade(String projectGrade) {
      this.projectGrade = projectGrade;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getBillsNo() {
      return this.billsNo;
   }

   public void setBillsNo(String billsNo) {
      this.billsNo = billsNo;
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

   public String getProjectNo() {
      return this.projectNo;
   }

   public void setProjectNo(String projectNo) {
      this.projectNo = projectNo;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public String getThreeLevelAccounting() {
      return this.threeLevelAccounting;
   }

   public void setThreeLevelAccounting(String threeLevelAccounting) {
      this.threeLevelAccounting = threeLevelAccounting;
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getInsuranceCode() {
      return this.insuranceCode;
   }

   public void setInsuranceCode(String insuranceCode) {
      this.insuranceCode = insuranceCode;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getCopeNo() {
      return this.copeNo;
   }

   public void setCopeNo(String copeNo) {
      this.copeNo = copeNo;
   }

   public String getCopeSortNumber() {
      return this.copeSortNumber;
   }

   public void setCopeSortNumber(String copeSortNumber) {
      this.copeSortNumber = copeSortNumber;
   }

   public String getItemSortNumber() {
      return this.itemSortNumber;
   }

   public void setItemSortNumber(String itemSortNumber) {
      this.itemSortNumber = itemSortNumber;
   }

   public String getCopeGroup() {
      return this.copeGroup;
   }

   public void setCopeGroup(String copeGroup) {
      this.copeGroup = copeGroup;
   }

   public String getCopeType() {
      return this.copeType;
   }

   public void setCopeType(String copeType) {
      this.copeType = copeType;
   }

   public String getCopeClass() {
      return this.copeClass;
   }

   public void setCopeClass(String copeClass) {
      this.copeClass = copeClass;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getBillsNoOld() {
      return this.billsNoOld;
   }

   public void setBillsNoOld(String billsNoOld) {
      this.billsNoOld = billsNoOld;
   }

   public String getPayMark() {
      return this.payMark;
   }

   public void setPayMark(String payMark) {
      this.payMark = payMark;
   }

   public Date getValidity() {
      return this.validity;
   }

   public void setValidity(Date validity) {
      this.validity = validity;
   }

   public Date getClosingDate() {
      return this.closingDate;
   }

   public void setClosingDate(Date closingDate) {
      this.closingDate = closingDate;
   }

   public String getMiddleSettlementMark() {
      return this.middleSettlementMark;
   }

   public void setMiddleSettlementMark(String middleSettlementMark) {
      this.middleSettlementMark = middleSettlementMark;
   }

   public String getSettleAccount() {
      return this.settleAccount;
   }

   public void setSettleAccount(String settleAccount) {
      this.settleAccount = settleAccount;
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

   public Date getStatisticsDate() {
      return this.statisticsDate;
   }

   public void setStatisticsDate(Date statisticsDate) {
      this.statisticsDate = statisticsDate;
   }

   public String getCpCode() {
      return this.cpCode;
   }

   public void setCpCode(String cpCode) {
      this.cpCode = cpCode;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getOperationNo() {
      return this.operationNo;
   }

   public void setOperationNo(String operationNo) {
      this.operationNo = operationNo;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

   public String getBabyFeeMark() {
      return this.babyFeeMark;
   }

   public void setBabyFeeMark(String babyFeeMark) {
      this.babyFeeMark = babyFeeMark;
   }

   public String getUploadingMark() {
      return this.uploadingMark;
   }

   public void setUploadingMark(String uploadingMark) {
      this.uploadingMark = uploadingMark;
   }

   public String getRegNo() {
      return this.regNo;
   }

   public void setRegNo(String regNo) {
      this.regNo = regNo;
   }

   public Integer getSortNumber() {
      return this.sortNumber;
   }

   public void setSortNumber(Integer sortNumber) {
      this.sortNumber = sortNumber;
   }

   public String getSourceProgram() {
      return this.sourceProgram;
   }

   public void setSourceProgram(String sourceProgram) {
      this.sourceProgram = sourceProgram;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }
}
