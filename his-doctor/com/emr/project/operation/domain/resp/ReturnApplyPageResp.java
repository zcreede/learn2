package com.emr.project.operation.domain.resp;

import java.math.BigDecimal;

public class ReturnApplyPageResp {
   private String patientName;
   private String admissionNo;
   private String mark;
   private String operatorDateTime;
   private String operatorName;
   private String chargeName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal orderDose;
   private BigDecimal total;
   private String orderDoctorName;
   private String orderDeptName;
   private String operatorNewDateTime;
   private String operatorNewName;
   private String pharmacyName;

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getMark() {
      return this.mark;
   }

   public void setMark(String mark) {
      this.mark = mark;
   }

   public String getOperatorDateTime() {
      return this.operatorDateTime;
   }

   public void setOperatorDateTime(String operatorDateTime) {
      this.operatorDateTime = operatorDateTime;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
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

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDeptName() {
      return this.orderDeptName;
   }

   public void setOrderDeptName(String orderDeptName) {
      this.orderDeptName = orderDeptName;
   }

   public String getOperatorNewDateTime() {
      return this.operatorNewDateTime;
   }

   public void setOperatorNewDateTime(String operatorNewDateTime) {
      this.operatorNewDateTime = operatorNewDateTime;
   }

   public String getOperatorNewName() {
      return this.operatorNewName;
   }

   public void setOperatorNewName(String operatorNewName) {
      this.operatorNewName = operatorNewName;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }
}
