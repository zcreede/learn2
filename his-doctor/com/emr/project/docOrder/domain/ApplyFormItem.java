package com.emr.project.docOrder.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ApplyFormItem extends ApplyFormItemKey {
   private String itemCode;
   private Integer orderGroupNo;
   private String itemName;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private Date reportDate;
   private String reportDoctor;
   private String applyFormStatus;
   private String barcodeNo;

   public ApplyFormItem() {
   }

   public ApplyFormItem(String applyFormNo, String itemSortNumber) {
      super.setApplyFormNo(applyFormNo);
      super.setItemSortNumber(itemSortNumber);
   }

   public ApplyFormItem(String applyFormNo, String itemSortNumber, String applyFormStatus) {
      super.setApplyFormNo(applyFormNo);
      super.setItemSortNumber(itemSortNumber);
      this.applyFormStatus = applyFormStatus;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode == null ? null : itemCode.trim();
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName == null ? null : itemName.trim();
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

   public Date getReportDate() {
      return this.reportDate;
   }

   public void setReportDate(Date reportDate) {
      this.reportDate = reportDate;
   }

   public String getReportDoctor() {
      return this.reportDoctor;
   }

   public void setReportDoctor(String reportDoctor) {
      this.reportDoctor = reportDoctor == null ? null : reportDoctor.trim();
   }

   public String getApplyFormStatus() {
      return this.applyFormStatus;
   }

   public void setApplyFormStatus(String applyFormStatus) {
      this.applyFormStatus = applyFormStatus == null ? null : applyFormStatus.trim();
   }

   public String getBarcodeNo() {
      return this.barcodeNo;
   }

   public void setBarcodeNo(String barcodeNo) {
      this.barcodeNo = barcodeNo == null ? null : barcodeNo.trim();
   }
}
