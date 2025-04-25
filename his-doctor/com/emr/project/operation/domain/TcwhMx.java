package com.emr.project.operation.domain;

import java.math.BigDecimal;

public class TcwhMx {
   private Long id;
   private String wardNo;
   private String packageNo;
   private Integer packageOrder;
   private String chargeCode;
   private String chargeNo;
   private String chargeName;
   private String chargeNamePym;
   private String hosUpper;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private String depExecNo;
   private String depName;

   public String getDepName() {
      return this.depName;
   }

   public void setDepName(String depName) {
      this.depName = depName;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo == null ? null : packageNo.trim();
   }

   public Integer getPackageOrder() {
      return this.packageOrder;
   }

   public void setPackageOrder(Integer packageOrder) {
      this.packageOrder = packageOrder;
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode == null ? null : chargeCode.trim();
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo == null ? null : chargeNo.trim();
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName == null ? null : chargeName.trim();
   }

   public String getHosUpper() {
      return this.hosUpper;
   }

   public void setHosUpper(String hosUpper) {
      this.hosUpper = hosUpper == null ? null : hosUpper.trim();
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard == null ? null : standard.trim();
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit == null ? null : unit.trim();
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getDepExecNo() {
      return this.depExecNo;
   }

   public void setDepExecNo(String depExecNo) {
      this.depExecNo = depExecNo == null ? null : depExecNo.trim();
   }

   public String getChargeNamePym() {
      return this.chargeNamePym;
   }

   public void setChargeNamePym(String chargeNamePym) {
      this.chargeNamePym = chargeNamePym;
   }
}
