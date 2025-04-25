package com.emr.project.docOrder.domain.vo;

import java.math.BigDecimal;

public class InpatientOrderResultDetailDTO {
   private String patientName;
   private String admissionNo;
   private String caseNo;
   private String orderNo;
   private String orderGroupNo;
   private String chargeName;
   private String erroMsg;
   private String bedId;
   private BigDecimal price;
   private BigDecimal orderTotalDose;
   private BigDecimal total;

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
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

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getErroMsg() {
      return this.erroMsg;
   }

   public void setErroMsg(String erroMsg) {
      this.erroMsg = erroMsg;
   }

   public String getBedId() {
      return this.bedId;
   }

   public void setBedId(String bedId) {
      this.bedId = bedId;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }
}
