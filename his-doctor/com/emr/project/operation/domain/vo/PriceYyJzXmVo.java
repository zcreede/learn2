package com.emr.project.operation.domain.vo;

import com.emr.project.operation.domain.PriceYy;
import java.math.BigDecimal;

public class PriceYyJzXmVo extends PriceYy {
   private String depName;
   private String hosUpperName;
   private String name;
   private String code;
   private BigDecimal orderDose;

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getDepName() {
      return this.depName;
   }

   public void setDepName(String depName) {
      this.depName = depName;
   }

   public String getHosUpperName() {
      return this.hosUpperName;
   }

   public void setHosUpperName(String hosUpperName) {
      this.hosUpperName = hosUpperName;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }
}
