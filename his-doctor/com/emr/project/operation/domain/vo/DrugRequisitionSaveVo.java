package com.emr.project.operation.domain.vo;

import java.math.BigDecimal;

public class DrugRequisitionSaveVo {
   private String cpName;
   private String cpNo;
   private String standard;
   private String unit;
   private String orderDoctorCode;
   private String orderDoctorName;
   private String orderDeptCode;
   private String orderDeptName;
   private String performStaffCode;
   private String performStaffName;
   private BigDecimal total;
   private BigDecimal dose;
   private BigDecimal price;

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
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

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDeptCode() {
      return this.orderDeptCode;
   }

   public void setOrderDeptCode(String orderDeptCode) {
      this.orderDeptCode = orderDeptCode;
   }

   public String getOrderDeptName() {
      return this.orderDeptName;
   }

   public void setOrderDeptName(String orderDeptName) {
      this.orderDeptName = orderDeptName;
   }

   public String getPerformStaffCode() {
      return this.performStaffCode;
   }

   public void setPerformStaffCode(String performStaffCode) {
      this.performStaffCode = performStaffCode;
   }

   public String getPerformStaffName() {
      return this.performStaffName;
   }

   public void setPerformStaffName(String performStaffName) {
      this.performStaffName = performStaffName;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
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
}
