package com.emr.project.operation.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmNaTcwhMx extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("ID")
   private Long id;
   @Excel(
      name = "病区编码"
   )
   @ApiModelProperty("病区编码")
   private String wardNo;
   @Excel(
      name = "套餐编码"
   )
   @ApiModelProperty(
      value = "套餐编码",
      required = true
   )
   private String packageNo;
   @Excel(
      name = "序号"
   )
   @ApiModelProperty("序号")
   private Long packageOrder;
   @Excel(
      name = "项目内码"
   )
   @ApiModelProperty("项目内码")
   private String chargeCode;
   @Excel(
      name = "项目编号"
   )
   @ApiModelProperty("项目编号")
   private String chargeNo;
   @Excel(
      name = "项目名称"
   )
   @ApiModelProperty("项目名称")
   private String chargeName;
   @Excel(
      name = "住院归属"
   )
   @ApiModelProperty("住院归属")
   private String hosUpper;
   @Excel(
      name = "规格"
   )
   @ApiModelProperty("规格")
   private String standard;
   @Excel(
      name = "单位"
   )
   @ApiModelProperty("单位")
   private String unit;
   @Excel(
      name = "单价"
   )
   @ApiModelProperty("单价")
   private BigDecimal price;
   @Excel(
      name = "数量"
   )
   @ApiModelProperty("数量")
   private BigDecimal dose;
   @Excel(
      name = "金额"
   )
   @ApiModelProperty("金额")
   private BigDecimal total;
   @Excel(
      name = "执行科室编码"
   )
   @ApiModelProperty("执行科室编码")
   private String depExecNo;
   @Excel(
      name = "项目名称拼音码"
   )
   @ApiModelProperty("项目名称拼音码")
   private String chargeNamePym;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo;
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageOrder(Long packageOrder) {
      this.packageOrder = packageOrder;
   }

   public Long getPackageOrder() {
      return this.packageOrder;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode;
   }

   public String getChargeCode() {
      return this.chargeCode;
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

   public void setHosUpper(String hosUpper) {
      this.hosUpper = hosUpper;
   }

   public String getHosUpper() {
      return this.hosUpper;
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

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setDepExecNo(String depExecNo) {
      this.depExecNo = depExecNo;
   }

   public String getDepExecNo() {
      return this.depExecNo;
   }

   public void setChargeNamePym(String chargeNamePym) {
      this.chargeNamePym = chargeNamePym;
   }

   public String getChargeNamePym() {
      return this.chargeNamePym;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("wardNo", this.getWardNo()).append("packageNo", this.getPackageNo()).append("packageOrder", this.getPackageOrder()).append("chargeCode", this.getChargeCode()).append("chargeNo", this.getChargeNo()).append("chargeName", this.getChargeName()).append("hosUpper", this.getHosUpper()).append("standard", this.getStandard()).append("unit", this.getUnit()).append("price", this.getPrice()).append("dose", this.getDose()).append("total", this.getTotal()).append("depExecNo", this.getDepExecNo()).append("chargeNamePym", this.getChargeNamePym()).toString();
   }
}
