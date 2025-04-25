package com.emr.project.pat.domain.vo;

import java.math.BigDecimal;

public class PatFeeVo {
   private String sqlStr;
   private String inpNo;
   private String recordNo;
   private String visitId;
   private BigDecimal total;
   private BigDecimal deposit;
   private BigDecimal depositRemaining;
   private String feeName;
   private Double feeAmount;
   private Double feeRatio;

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getVisitId() {
      return this.visitId;
   }

   public void setVisitId(String visitId) {
      this.visitId = visitId;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getDeposit() {
      return this.deposit;
   }

   public void setDeposit(BigDecimal deposit) {
      this.deposit = deposit;
   }

   public BigDecimal getDepositRemaining() {
      return this.depositRemaining;
   }

   public void setDepositRemaining(BigDecimal depositRemaining) {
      this.depositRemaining = depositRemaining;
   }

   public String getFeeName() {
      return this.feeName;
   }

   public void setFeeName(String feeName) {
      this.feeName = feeName;
   }

   public Double getFeeAmount() {
      return this.feeAmount;
   }

   public void setFeeAmount(Double feeAmount) {
      this.feeAmount = feeAmount;
   }

   public Double getFeeRatio() {
      return this.feeRatio;
   }

   public void setFeeRatio(Double feeRatio) {
      this.feeRatio = feeRatio;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }
}
