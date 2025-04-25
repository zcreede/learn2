package com.emr.project.report.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class FeeDayDataVo {
   private String deptName;
   private String admissionNo;
   private String caseNo;
   private String bedid;
   private Integer bedOrder;
   private String patientName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   private String filingDate;
   private String expenseCategory;
   private BigDecimal totalDeposit;
   private BigDecimal totalAmount;
   private BigDecimal depositBalance;
   private String standardCode;
   private String standardName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private BigDecimal totalOwnRatio;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   private String medicalInsuranceCode;
   private String medicalInsuranceName;
   private String projectName;
   private String chargeCode;
   private String chargeNo;
   private String chargeName;
   private String threeLevelAccounting;

   public FeeDayDataVo() {
      this.totalDeposit = BigDecimal.ZERO;
      this.totalAmount = BigDecimal.ZERO;
      this.depositBalance = BigDecimal.ZERO;
   }

   public Integer getBedOrder() {
      return this.bedOrder;
   }

   public void setBedOrder(Integer bedOrder) {
      this.bedOrder = bedOrder;
   }

   public String getThreeLevelAccounting() {
      return this.threeLevelAccounting;
   }

   public void setThreeLevelAccounting(String threeLevelAccounting) {
      this.threeLevelAccounting = threeLevelAccounting;
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

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

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

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public String getFilingDate() {
      return this.filingDate;
   }

   public void setFilingDate(String filingDate) {
      this.filingDate = filingDate;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public BigDecimal getTotalDeposit() {
      return this.totalDeposit;
   }

   public void setTotalDeposit(BigDecimal totalDeposit) {
      this.totalDeposit = totalDeposit;
   }

   public BigDecimal getTotalAmount() {
      return this.totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public BigDecimal getDepositBalance() {
      return this.depositBalance;
   }

   public void setDepositBalance(BigDecimal depositBalance) {
      this.depositBalance = depositBalance;
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

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
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

   public BigDecimal getTotalOwnRatio() {
      return this.totalOwnRatio;
   }

   public void setTotalOwnRatio(BigDecimal totalOwnRatio) {
      this.totalOwnRatio = totalOwnRatio;
   }
}
