package com.emr.project.operation.domain;

import java.math.BigDecimal;

public class TmPmDjcdDefine {
   private BigDecimal id;
   private BigDecimal djlx;
   private BigDecimal djLength;
   private String bz;

   public BigDecimal getId() {
      return this.id;
   }

   public void setId(BigDecimal id) {
      this.id = id;
   }

   public BigDecimal getDjlx() {
      return this.djlx;
   }

   public void setDjlx(BigDecimal djlx) {
      this.djlx = djlx;
   }

   public BigDecimal getDjLength() {
      return this.djLength;
   }

   public void setDjLength(BigDecimal djLength) {
      this.djLength = djLength;
   }

   public String getBz() {
      return this.bz;
   }

   public void setBz(String bz) {
      this.bz = bz == null ? null : bz.trim();
   }
}
