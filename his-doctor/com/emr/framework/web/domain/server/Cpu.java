package com.emr.framework.web.domain.server;

import com.emr.common.utils.Arith;

public class Cpu {
   private int cpuNum;
   private double total;
   private double sys;
   private double used;
   private double wait;
   private double free;

   public int getCpuNum() {
      return this.cpuNum;
   }

   public void setCpuNum(int cpuNum) {
      this.cpuNum = cpuNum;
   }

   public double getTotal() {
      return Arith.round(Arith.mul(this.total, (double)100.0F), 2);
   }

   public void setTotal(double total) {
      this.total = total;
   }

   public double getSys() {
      return Arith.round(Arith.mul(this.sys / this.total, (double)100.0F), 2);
   }

   public void setSys(double sys) {
      this.sys = sys;
   }

   public double getUsed() {
      return Arith.round(Arith.mul(this.used / this.total, (double)100.0F), 2);
   }

   public void setUsed(double used) {
      this.used = used;
   }

   public double getWait() {
      return Arith.round(Arith.mul(this.wait / this.total, (double)100.0F), 2);
   }

   public void setWait(double wait) {
      this.wait = wait;
   }

   public double getFree() {
      return Arith.round(Arith.mul(this.free / this.total, (double)100.0F), 2);
   }

   public void setFree(double free) {
      this.free = free;
   }
}
