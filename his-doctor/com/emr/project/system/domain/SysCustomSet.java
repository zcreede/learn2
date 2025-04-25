package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel("内部数据集对象")
public class SysCustomSet extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @JsonSerialize(
      using = ToStringSerializer.class
   )
   private Long id;
   @Excel(
      name = "数据集名称"
   )
   @ApiModelProperty("数据集名称 - 列表查询参数 列表显示列 新增/修改必填")
   private String setName;
   @Excel(
      name = "数据集描述"
   )
   @ApiModelProperty("数据集描述 - 列表显示列 新增/修改必填")
   private String setDesc;
   @Excel(
      name = "拼音码"
   )
   @ApiModelProperty("拼音码 - 新增/修改必填")
   private String inputstrphtc;
   @Excel(
      name = "五笔码"
   )
   @ApiModelProperty("五笔码")
   private String inputstrelse;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   @ApiModelProperty("状态 0=：未启用；1：启用 - 列表显示列 新增/修改必填")
   private String validFlag;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
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
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
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
      name = "分类",
      readConverterExp = "1=:值域；0：数据集"
   )
   @ApiModelProperty("分类 1=:值域；0：数据集 - 列表查询参数 列表显示列 新增/修改必填")
   private String typeName;
   private String typeNameLabel;

   public String getTypeNameLabel() {
      return this.typeNameLabel;
   }

   public void setTypeNameLabel(String typeNameLabel) {
      this.typeNameLabel = typeNameLabel;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSetName(String setName) {
      this.setName = setName;
   }

   public String getSetName() {
      return this.setName;
   }

   public void setSetDesc(String setDesc) {
      this.setDesc = setDesc;
   }

   public String getSetDesc() {
      return this.setDesc;
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

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("setName", this.getSetName()).append("setDesc", this.getSetDesc()).append("inputstrphtc", this.getInputstrphtc()).append("inputstrelse", this.getInputstrelse()).append("remark", this.getRemark()).append("validFlag", this.getValidFlag()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("typeName", this.getTypeName()).toString();
   }
}
