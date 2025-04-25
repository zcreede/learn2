package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaApplyFormItem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String applyFormNo;
   @Excel(
      name = "组号"
   )
   private Integer orderGroupNo;
   private String itemSortNumber;
   @Excel(
      name = "临床项目编码"
   )
   private String itemCode;
   @Excel(
      name = "临床项目名称"
   )
   private String itemName;
   @Excel(
      name = "单价"
   )
   private BigDecimal price;
   @Excel(
      name = "数量"
   )
   private BigDecimal dose;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "检查部位代码"
   )
   private String examPartCd;
   @Excel(
      name = "检查部位名称"
   )
   private String examPartName;
   @Excel(
      name = "标本代码"
   )
   private String specCd;
   @Excel(
      name = "标本名称"
   )
   private String specName;
   @Excel(
      name = "申请单状态"
   )
   private String applyFormStatus;
   @Excel(
      name = "条码"
   )
   private String barcodeNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "报告日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date reportDate;
   @Excel(
      name = "报告人"
   )
   private String reportDoctor;
   private String standard;

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setItemSortNumber(String itemSortNumber) {
      this.itemSortNumber = itemSortNumber;
   }

   public String getItemSortNumber() {
      return this.itemSortNumber;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
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

   public String getUnit() {
      return this.unit;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setApplyFormStatus(String applyFormStatus) {
      this.applyFormStatus = applyFormStatus;
   }

   public String getApplyFormStatus() {
      return this.applyFormStatus;
   }

   public void setBarcodeNo(String barcodeNo) {
      this.barcodeNo = barcodeNo;
   }

   public String getBarcodeNo() {
      return this.barcodeNo;
   }

   public void setReportDate(Date reportDate) {
      this.reportDate = reportDate;
   }

   public Date getReportDate() {
      return this.reportDate;
   }

   public void setReportDoctor(String reportDoctor) {
      this.reportDoctor = reportDoctor;
   }

   public String getReportDoctor() {
      return this.reportDoctor;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("applyFormNo", this.getApplyFormNo()).append("orderGroupNo", this.getOrderGroupNo()).append("itemSortNumber", this.getItemSortNumber()).append("itemCode", this.getItemCode()).append("itemName", this.getItemName()).append("price", this.getPrice()).append("dose", this.getDose()).append("unit", this.getUnit()).append("examPartCd", this.getExamPartCd()).append("examPartName", this.getExamPartName()).append("specCd", this.getSpecCd()).append("specName", this.getSpecName()).append("applyFormStatus", this.getApplyFormStatus()).append("barcodeNo", this.getBarcodeNo()).append("reportDate", this.getReportDate()).append("reportDoctor", this.getReportDoctor()).toString();
   }
}
