package com.emr.project.report.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class FeeItemVo {
   private String billDocCd;
   private String billDocName;
   private String patientName;
   private String inpNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date filingDate;
   private String feeItemName;
   private String feeItemCode;
   private String standard;
   private String unit;
   private String dose;
   private Double price;
   private Double total;
   private String execDeptName;
   private String billDeptName;

   public String getBillDocCd() {
      return this.billDocCd;
   }

   public void setBillDocCd(String billDocCd) {
      this.billDocCd = billDocCd;
   }

   public String getBillDocName() {
      return this.billDocName;
   }

   public void setBillDocName(String billDocName) {
      this.billDocName = billDocName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Date getFilingDate() {
      return this.filingDate;
   }

   public void setFilingDate(Date filingDate) {
      this.filingDate = filingDate;
   }

   public String getFeeItemName() {
      return this.feeItemName;
   }

   public void setFeeItemName(String feeItemName) {
      this.feeItemName = feeItemName;
   }

   public String getFeeItemCode() {
      return this.feeItemCode;
   }

   public void setFeeItemCode(String feeItemCode) {
      this.feeItemCode = feeItemCode;
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

   public String getDose() {
      return this.dose;
   }

   public void setDose(String dose) {
      this.dose = dose;
   }

   public Double getPrice() {
      return this.price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Double getTotal() {
      return this.total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

   public String getExecDeptName() {
      return this.execDeptName;
   }

   public void setExecDeptName(String execDeptName) {
      this.execDeptName = execDeptName;
   }

   public String getBillDeptName() {
      return this.billDeptName;
   }

   public void setBillDeptName(String billDeptName) {
      this.billDeptName = billDeptName;
   }
}
