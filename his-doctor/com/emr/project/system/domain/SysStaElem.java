package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysStaElem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "元素编码"
   )
   private String elemCd;
   @Excel(
      name = "元素名称"
   )
   private String elemName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "五笔码"
   )
   private String inputstrelse;
   @Excel(
      name = "分类ID"
   )
   private String clasId;
   @Excel(
      name = "分类名称"
   )
   private String clasName;
   @Excel(
      name = "数据元标识ID"
   )
   private String unitId;
   @Excel(
      name = "属性"
   )
   private String elemQua;
   @Excel(
      name = "控件类型：1：文体框，2：数字输入框，3：单选下拉，4：多选下拉，5：单选组合下拉框( 可编辑 )  6 多选组合下拉框( 可编辑 )   7：日期输入，8：复选框   9 弹出选择框  10 有无选择框 11 医师 签名  12 患者签名   A1:牙齿医学公式  A2:视野医学公式  A3:月经 A4: 文本 A5:瞳孔 A6: 光定位 A7: 胎动"
   )
   private String contType;
   @Excel(
      name = "元素类型标识(1: 数据元控件   2;数据组  3：区域   A：医学公式   "
   )
   private String typeFlag;
   @Excel(
      name = "来源标志",
      readConverterExp = "1=:标准；2：自定义"
   )
   private String sourFlag;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "备注信息"
   )
   private String remark;
   private List contTypeList;
   private String plcHdrColor;

   public String getPlcHdrColor() {
      return this.plcHdrColor;
   }

   public void setPlcHdrColor(String plcHdrColor) {
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public List getContTypeList() {
      return this.contTypeList;
   }

   public void setContTypeList(List contTypeList) {
      this.contTypeList = contTypeList;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrelse(String inputstrelse) {
      this.inputstrelse = inputstrelse;
   }

   public String getInputstrelse() {
      return this.inputstrelse;
   }

   public void setClasId(String clasId) {
      this.clasId = clasId;
   }

   public String getClasId() {
      return this.clasId;
   }

   public void setClasName(String clasName) {
      this.clasName = clasName;
   }

   public String getClasName() {
      return this.clasName;
   }

   public void setUnitId(String unitId) {
      this.unitId = unitId;
   }

   public String getUnitId() {
      return this.unitId;
   }

   public void setElemQua(String elemQua) {
      this.elemQua = elemQua;
   }

   public String getElemQua() {
      return this.elemQua;
   }

   public void setContType(String contType) {
      this.contType = contType;
   }

   public String getContType() {
      return this.contType;
   }

   public void setTypeFlag(String typeFlag) {
      this.typeFlag = typeFlag;
   }

   public String getTypeFlag() {
      return this.typeFlag;
   }

   public void setSourFlag(String sourFlag) {
      this.sourFlag = sourFlag;
   }

   public String getSourFlag() {
      return this.sourFlag;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("elemCd", this.getElemCd()).append("elemName", this.getElemName()).append("inputstrphtc", this.getInputstrphtc()).append("inputstrelse", this.getInputstrelse()).append("clasId", this.getClasId()).append("clasName", this.getClasName()).append("unitId", this.getUnitId()).append("elemQua", this.getElemQua()).append("contType", this.getContType()).append("typeFlag", this.getTypeFlag()).append("sourFlag", this.getSourFlag()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("remark", this.getRemark()).toString();
   }
}
