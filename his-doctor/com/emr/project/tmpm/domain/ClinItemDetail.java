package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClinItemDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "项目编码"
   )
   private String itemCd;
   @Excel(
      name = "排序序号"
   )
   private String sortSn;
   @Excel(
      name = "标准物价编码"
   )
   private String standardCode;
   @Excel(
      name = "标准物价名称"
   )
   private String standardName;
   @Excel(
      name = "医院物价编码"
   )
   private String chargeCode;
   @Excel(
      name = "医院物价名称"
   )
   private String chargeName;
   @Excel(
      name = "数量"
   )
   private Double amount;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "单价"
   )
   private BigDecimal price;
   @Excel(
      name = "主从标志",
      readConverterExp = "0=主项目；1从项目"
   )
   private String subFlag;
   @Excel(
      name = "记账时长"
   )
   private Long chargeHour;
   private String remark;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setSortSn(String sortSn) {
      this.sortSn = sortSn;
   }

   public String getSortSn() {
      return this.sortSn;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode;
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setSubFlag(String subFlag) {
      this.subFlag = subFlag;
   }

   public Double getAmount() {
      return this.amount;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
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

   public String getSubFlag() {
      return this.subFlag;
   }

   public void setChargeHour(Long chargeHour) {
      this.chargeHour = chargeHour;
   }

   public Long getChargeHour() {
      return this.chargeHour;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("itemCd", this.getItemCd()).append("sortSn", this.getSortSn()).append("standardCode", this.getStandardCode()).append("standardName", this.getStandardName()).append("chargeCode", this.getChargeCode()).append("chargeName", this.getChargeName()).append("amount", this.getAmount()).append("unit", this.getUnit()).append("price", this.getPrice()).append("subFlag", this.getSubFlag()).append("chargeHour", this.getChargeHour()).toString();
   }
}
