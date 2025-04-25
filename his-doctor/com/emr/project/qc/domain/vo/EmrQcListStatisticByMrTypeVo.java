package com.emr.project.qc.domain.vo;

import java.math.BigDecimal;

public class EmrQcListStatisticByMrTypeVo {
   private String mrType;
   private String mrTypeName;
   private Integer flawTotal;
   private Integer flawSum;
   private Integer flawFileTotal;
   private Integer flawYxgTotal;
   private Integer flawYwTotal;
   private Integer flawWxgTotal;
   private String flawProportion;
   private Double flawProportionB;
   private String grandTotalProportion;
   private BigDecimal grandTotalProportionB;

   public BigDecimal getGrandTotalProportionB() {
      return this.grandTotalProportionB;
   }

   public void setGrandTotalProportionB(BigDecimal grandTotalProportionB) {
      this.grandTotalProportionB = grandTotalProportionB;
   }

   public Double getFlawProportionB() {
      return this.flawProportionB;
   }

   public void setFlawProportionB(Double flawProportionB) {
      this.flawProportionB = flawProportionB;
   }

   public Integer getFlawSum() {
      return this.flawSum;
   }

   public void setFlawSum(Integer flawSum) {
      this.flawSum = flawSum;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public Integer getFlawTotal() {
      return this.flawTotal;
   }

   public void setFlawTotal(Integer flawTotal) {
      this.flawTotal = flawTotal;
   }

   public Integer getFlawFileTotal() {
      return this.flawFileTotal;
   }

   public void setFlawFileTotal(Integer flawFileTotal) {
      this.flawFileTotal = flawFileTotal;
   }

   public Integer getFlawYxgTotal() {
      return this.flawYxgTotal;
   }

   public void setFlawYxgTotal(Integer flawYxgTotal) {
      this.flawYxgTotal = flawYxgTotal;
   }

   public Integer getFlawYwTotal() {
      return this.flawYwTotal;
   }

   public void setFlawYwTotal(Integer flawYwTotal) {
      this.flawYwTotal = flawYwTotal;
   }

   public Integer getFlawWxgTotal() {
      return this.flawWxgTotal;
   }

   public void setFlawWxgTotal(Integer flawWxgTotal) {
      this.flawWxgTotal = flawWxgTotal;
   }

   public String getFlawProportion() {
      return this.flawProportion;
   }

   public void setFlawProportion(String flawProportion) {
      this.flawProportion = flawProportion;
   }

   public String getGrandTotalProportion() {
      return this.grandTotalProportion;
   }

   public void setGrandTotalProportion(String grandTotalProportion) {
      this.grandTotalProportion = grandTotalProportion;
   }
}
