package com.emr.project.docOrder.domain;

import java.math.BigDecimal;

public class InpatientOrderUsageFee {
   private Long no;
   private String itemName;
   private String standard;
   private BigDecimal price;
   private String unit;
   private BigDecimal dose;
   private String yongfaBh;
   private String wardNo;
   private String standardCode;
   private String itemNo;
   private String zfbz;
   private String crbz;
   private String firstFlag;

   public InpatientOrderUsageFee() {
   }

   public InpatientOrderUsageFee(String yongfaBh, String wardNo, String crbz, String firstFlag) {
      this.yongfaBh = yongfaBh;
      this.wardNo = wardNo;
      this.crbz = crbz;
      this.firstFlag = firstFlag;
   }

   public Long getNo() {
      return this.no;
   }

   public void setNo(Long no) {
      this.no = no;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName == null ? null : itemName.trim();
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard == null ? null : standard.trim();
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit == null ? null : unit.trim();
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public String getYongfaBh() {
      return this.yongfaBh;
   }

   public void setYongfaBh(String yongfaBh) {
      this.yongfaBh = yongfaBh == null ? null : yongfaBh.trim();
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode == null ? null : standardCode.trim();
   }

   public String getItemNo() {
      return this.itemNo;
   }

   public void setItemNo(String itemNo) {
      this.itemNo = itemNo == null ? null : itemNo.trim();
   }

   public String getZfbz() {
      return this.zfbz;
   }

   public void setZfbz(String zfbz) {
      this.zfbz = zfbz == null ? null : zfbz.trim();
   }

   public String getCrbz() {
      return this.crbz;
   }

   public void setCrbz(String crbz) {
      this.crbz = crbz == null ? null : crbz.trim();
   }

   public String getFirstFlag() {
      return this.firstFlag;
   }

   public void setFirstFlag(String firstFlag) {
      this.firstFlag = firstFlag == null ? null : firstFlag.trim();
   }
}
