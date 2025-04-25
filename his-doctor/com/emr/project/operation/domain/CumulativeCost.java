package com.emr.project.operation.domain;

import java.math.BigDecimal;

public class CumulativeCost {
   private String admissionNo;
   private BigDecimal deposIt;
   private BigDecimal cumulativeCost;
   private BigDecimal amountGuaranteed;
   private Integer v;

   public CumulativeCost() {
   }

   public CumulativeCost(String admissionNo, BigDecimal cumulativeCost) {
      this.admissionNo = admissionNo;
      this.cumulativeCost = cumulativeCost;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo == null ? null : admissionNo.trim();
   }

   public BigDecimal getDeposIt() {
      return this.deposIt;
   }

   public void setDeposIt(BigDecimal deposIt) {
      this.deposIt = deposIt;
   }

   public BigDecimal getCumulativeCost() {
      return this.cumulativeCost;
   }

   public void setCumulativeCost(BigDecimal cumulativeCost) {
      this.cumulativeCost = cumulativeCost;
   }

   public BigDecimal getAmountGuaranteed() {
      return this.amountGuaranteed;
   }

   public void setAmountGuaranteed(BigDecimal amountGuaranteed) {
      this.amountGuaranteed = amountGuaranteed;
   }

   public Integer getV() {
      return this.v;
   }

   public void setV(Integer v) {
      this.v = v;
   }
}
