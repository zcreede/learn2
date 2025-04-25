package com.emr.project.tmpa.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPaOrderUsageFee extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "编号"
   )
   private Long no;
   @Excel(
      name = "医院项目名称"
   )
   private String itemName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "单价"
   )
   private Long price;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "数量"
   )
   private Long dose;
   @Excel(
      name = "用法编号"
   )
   private String yongfaBh;
   @Excel(
      name = "病区代码"
   )
   private String wardNo;
   @Excel(
      name = "标准服务编码"
   )
   private String standardCode;
   @Excel(
      name = "医院服务编码"
   )
   private String itemNo;
   @Excel(
      name = "1：是；0：否"
   )
   private String zfbz;
   @Excel(
      name = "1：成人；0：儿童"
   )
   private String crbz;
   @Excel(
      name = "1:第一瓶;2:加一瓶；3：普通；4：特殊；5：:避光器第一瓶；6：避光器加一瓶"
   )
   private String firstFlag;

   public void setNo(Long no) {
      this.no = no;
   }

   public Long getNo() {
      return this.no;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setPrice(Long price) {
      this.price = price;
   }

   public Long getPrice() {
      return this.price;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setDose(Long dose) {
      this.dose = dose;
   }

   public Long getDose() {
      return this.dose;
   }

   public void setYongfaBh(String yongfaBh) {
      this.yongfaBh = yongfaBh;
   }

   public String getYongfaBh() {
      return this.yongfaBh;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setItemNo(String itemNo) {
      this.itemNo = itemNo;
   }

   public String getItemNo() {
      return this.itemNo;
   }

   public void setZfbz(String zfbz) {
      this.zfbz = zfbz;
   }

   public String getZfbz() {
      return this.zfbz;
   }

   public void setCrbz(String crbz) {
      this.crbz = crbz;
   }

   public String getCrbz() {
      return this.crbz;
   }

   public void setFirstFlag(String firstFlag) {
      this.firstFlag = firstFlag;
   }

   public String getFirstFlag() {
      return this.firstFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("no", this.getNo()).append("itemName", this.getItemName()).append("standard", this.getStandard()).append("price", this.getPrice()).append("unit", this.getUnit()).append("dose", this.getDose()).append("yongfaBh", this.getYongfaBh()).append("wardNo", this.getWardNo()).append("standardCode", this.getStandardCode()).append("itemNo", this.getItemNo()).append("zfbz", this.getZfbz()).append("crbz", this.getCrbz()).append("firstFlag", this.getFirstFlag()).toString();
   }
}
