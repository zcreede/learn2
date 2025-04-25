package com.emr.project.operation.domain.vo;

import java.math.BigDecimal;

public class PrintDrugListVo {
   private String caseNo;
   private String admissionNo;
   private String patientName;
   private String bedid;
   private String deptName;
   private String printName;
   private String dept;
   private String pharmacyNoName;
   private String drugName;
   private String orderStandard;
   private BigDecimal orderPrice;
   private BigDecimal orderDose;
   private String orderUnit;
   private BigDecimal orderTotal;
   private String applyNo;

   public String getApplyNo() {
      return this.applyNo;
   }

   public void setApplyNo(String applyNo) {
      this.applyNo = applyNo;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
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

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getPrintName() {
      return this.printName;
   }

   public void setPrintName(String printName) {
      this.printName = printName;
   }

   public String getDept() {
      return this.dept;
   }

   public void setDept(String dept) {
      this.dept = dept;
   }

   public String getPharmacyNoName() {
      return this.pharmacyNoName;
   }

   public void setPharmacyNoName(String pharmacyNoName) {
      this.pharmacyNoName = pharmacyNoName;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getOrderStandard() {
      return this.orderStandard;
   }

   public void setOrderStandard(String orderStandard) {
      this.orderStandard = orderStandard;
   }

   public BigDecimal getOrderPrice() {
      return this.orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
   }

   public String getOrderUnit() {
      return this.orderUnit;
   }

   public void setOrderUnit(String orderUnit) {
      this.orderUnit = orderUnit;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }
}
