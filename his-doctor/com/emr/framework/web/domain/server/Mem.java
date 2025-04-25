package com.emr.framework.web.domain.server;

import com.emr.common.utils.Arith;

public class Mem {
   private double total;
   private double used;
   private double free;

   public double getTotal() {
      return Arith.div(this.total, 1.073741824E9, 2);
   }

   public void setTotal(long total) {
      this.total = (double)total;
   }

   public double getUsed() {
      return Arith.div(this.used, 1.073741824E9, 2);
   }

   public void setUsed(long used) {
      this.used = (double)used;
   }

   public double getFree() {
      return Arith.div(this.free, 1.073741824E9, 2);
   }

   public void setFree(long free) {
      this.free = (double)free;
   }

   public double getUsage() {
      return Arith.mul(Arith.div(this.used, this.total, 4), (double)100.0F);
   }
}
