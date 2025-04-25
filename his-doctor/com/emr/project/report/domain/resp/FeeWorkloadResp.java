package com.emr.project.report.domain.resp;

import java.io.Serializable;
import java.math.BigDecimal;

public class FeeWorkloadResp implements Serializable {
   private BigDecimal total;
   private String staffCode;
   private String staffName;
   private String threeLevelCode;
   private String threeLevelName;

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getThreeLevelCode() {
      return this.threeLevelCode;
   }

   public void setThreeLevelCode(String threeLevelCode) {
      this.threeLevelCode = threeLevelCode;
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName;
   }
}
