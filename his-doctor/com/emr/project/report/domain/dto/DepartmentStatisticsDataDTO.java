package com.emr.project.report.domain.dto;

import java.math.BigDecimal;

public class DepartmentStatisticsDataDTO {
   private String threeLevelAccounting;
   private String threeLevelName;
   private String firstCode;
   private String firsName;
   private BigDecimal total;

   public String getThreeLevelAccounting() {
      return this.threeLevelAccounting;
   }

   public void setThreeLevelAccounting(String threeLevelAccounting) {
      this.threeLevelAccounting = threeLevelAccounting;
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName;
   }

   public String getFirstCode() {
      return this.firstCode;
   }

   public void setFirstCode(String firstCode) {
      this.firstCode = firstCode;
   }

   public String getFirsName() {
      return this.firsName;
   }

   public void setFirsName(String firsName) {
      this.firsName = firsName;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }
}
