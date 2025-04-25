package com.emr.project.docOrder.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class OrderExecutionExpenseDetail {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date filingDate;
   private String chargeCode;
   private String chargeName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private String projectName;
   private String operatorName;
   private String accountingDepartmentName;
   private String executorDptName;
   private String performListNo;
   private String physicianDptName;
   private String physicianName;
   private String billsNo;
   private String billsNoOld;

   public String getBillsNoOld() {
      return this.billsNoOld;
   }

   public void setBillsNoOld(String billsNoOld) {
      this.billsNoOld = billsNoOld;
   }

   public String getBillsNo() {
      return this.billsNo;
   }

   public void setBillsNo(String billsNo) {
      this.billsNo = billsNo;
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName;
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
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

   public Date getFilingDate() {
      return this.filingDate;
   }

   public void setFilingDate(Date filingDate) {
      this.filingDate = filingDate;
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode;
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

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getAccountingDepartmentName() {
      return this.accountingDepartmentName;
   }

   public void setAccountingDepartmentName(String accountingDepartmentName) {
      this.accountingDepartmentName = accountingDepartmentName;
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName;
   }
}
