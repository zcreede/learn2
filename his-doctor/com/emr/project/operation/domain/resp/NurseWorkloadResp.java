package com.emr.project.operation.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class NurseWorkloadResp {
   private String admissionNo;
   private String caseNo;
   private String hospitalizedCount;
   private String name;
   private String chargeNo;
   private String chargeName;
   private String standard;
   private String unit;
   private BigDecimal dose;
   private BigDecimal price;
   private BigDecimal total;
   @JsonFormat(
      pattern = "yyyy.MM.dd HH:mm",
      timezone = "GMT+8"
   )
   private Date filingDate;
   private String operator;
   private String operatorName;
   private String executorDptName;
   private String accountingDepartmentName;

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

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
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

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName;
   }

   public String getAccountingDepartmentName() {
      return this.accountingDepartmentName;
   }

   public void setAccountingDepartmentName(String accountingDepartmentName) {
      this.accountingDepartmentName = accountingDepartmentName;
   }
}
