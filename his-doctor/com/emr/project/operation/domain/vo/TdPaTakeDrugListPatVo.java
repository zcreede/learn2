package com.emr.project.operation.domain.vo;

import java.math.BigDecimal;

public class TdPaTakeDrugListPatVo {
   private BigDecimal orderDose;
   private String patientName;
   private String admissionNo;
   private String bedid;
   private String orderUnit;
   private BigDecimal orderPrice;
   private BigDecimal amount;

   public BigDecimal getOrderPrice() {
      return this.orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getOrderUnit() {
      return this.orderUnit;
   }

   public void setOrderUnit(String orderUnit) {
      this.orderUnit = orderUnit;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

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
}
