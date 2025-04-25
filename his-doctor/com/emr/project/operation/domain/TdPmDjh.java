package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TdPmDjh {
   private BigDecimal id;
   private Date rq;
   private BigDecimal djlx;

   public BigDecimal getId() {
      return this.id;
   }

   public void setId(BigDecimal id) {
      this.id = id;
   }

   public Date getRq() {
      return this.rq;
   }

   public void setRq(Date rq) {
      this.rq = rq;
   }

   public BigDecimal getDjlx() {
      return this.djlx;
   }

   public void setDjlx(BigDecimal djlx) {
      this.djlx = djlx;
   }
}
