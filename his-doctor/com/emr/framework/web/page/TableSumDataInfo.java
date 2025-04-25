package com.emr.framework.web.page;

import java.math.BigDecimal;

public class TableSumDataInfo extends TableDataInfo {
   private int code;
   private String msg;
   private BigDecimal days;
   private BigDecimal drugFee;
   private BigDecimal totalFee;

   public BigDecimal getTotalFee() {
      return this.totalFee;
   }

   public void setTotalFee(BigDecimal totalFee) {
      this.totalFee = totalFee;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public BigDecimal getDays() {
      return this.days;
   }

   public void setDays(BigDecimal days) {
      this.days = days;
   }

   public BigDecimal getDrugFee() {
      return this.drugFee;
   }

   public TableSumDataInfo() {
   }

   public void setDrugFee(BigDecimal drugFee) {
      this.drugFee = drugFee;
   }

   public TableSumDataInfo(int code, String msg) {
      this.code = code;
      this.msg = msg;
   }
}
