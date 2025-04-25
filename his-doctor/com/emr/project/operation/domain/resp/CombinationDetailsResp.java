package com.emr.project.operation.domain.resp;

import java.math.BigDecimal;

public class CombinationDetailsResp {
   private Long id;
   private String deptCode;
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
   private String deptName;

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo;
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
      this.chargeCode = chargeCode;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getChargeNamePym() {
      return this.chargeNamePym;
   }

   public void setChargeNamePym(String chargeNamePym) {
      this.chargeNamePym = chargeNamePym;
   }

   public String getHosUpper() {
      return this.hosUpper;
   }

   public void setHosUpper(String hosUpper) {
      this.hosUpper = hosUpper;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
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
      this.depExecNo = depExecNo;
   }
}
