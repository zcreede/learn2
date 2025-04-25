package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaApplyFormDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String applyFormNo;
   private String itemSortNumber;
   @Excel(
      name = "序号"
   )
   private String orderGroupSortNumber;
   private String chargeNo;
   @Excel(
      name = "医院服务项目名称"
   )
   private String chargeName;
   @Excel(
      name = "标准项目编号"
   )
   private String standardCode;
   @Excel(
      name = "标准项目名称"
   )
   private String standardName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "单价"
   )
   private BigDecimal price;
   @Excel(
      name = "数量"
   )
   private Double dose;
   private String subFlag;
   private String remark;

   public String getSubFlag() {
      return this.subFlag;
   }

   public void setSubFlag(String subFlag) {
      this.subFlag = subFlag;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
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

   public void setItemSortNumber(String itemSortNumber) {
      this.itemSortNumber = itemSortNumber;
   }

   public String getItemSortNumber() {
      return this.itemSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getChargeName() {
      return this.chargeName;
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

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public Double getDose() {
      return this.dose;
   }

   public void setDose(Double dose) {
      this.dose = dose;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("applyFormNo", this.getApplyFormNo()).append("itemSortNumber", this.getItemSortNumber()).append("orderGroupSortNumber", this.getOrderGroupSortNumber()).append("chargeNo", this.getChargeNo()).append("chargeName", this.getChargeName()).append("standardCode", this.getStandardCode()).append("standardName", this.getStandardName()).append("standard", this.getStandard()).append("unit", this.getUnit()).append("price", this.getPrice()).append("dose", this.getDose()).toString();
   }
}
