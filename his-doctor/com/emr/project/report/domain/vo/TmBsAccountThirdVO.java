package com.emr.project.report.domain.vo;

import java.math.BigDecimal;

public class TmBsAccountThirdVO {
   private String code;
   private String Name;
   private BigDecimal total;

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return this.Name;
   }

   public void setName(String name) {
      this.Name = name;
   }
}
