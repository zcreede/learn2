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

@ApiModel("内部数据集字段对象")
public class SysCustomUnit extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @JsonSerialize(
      using = ToStringSerializer.class
   )
   private Long id;
   @Excel(
      name = "内部数据集标识ID"
   )
   private Long setId;
   @Excel(
      name = "数据元名称"
   )
   @ApiModelProperty("数据元英文名")
   private String fieldCd;
   @Excel(
      name = "数据元描述"
   )
   @ApiModelProperty("数据元中文名")
   private String fieldName;
   @Excel(
      name = "拼音码"
   )
   @ApiModelProperty("拼音码")
   private String inputstrphtc;
   @Excel(
      name = "五笔码"
   )
   private String inputstrelse;
   @Excel(
      name = "数据类型"
   )
   @ApiModelProperty("数据元类型")
   private String dataType;
   @Excel(
      name = "数据长度"
   )
   @ApiModelProperty("数据元长度")
   private Long dataLong;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   @ApiModelProperty("启用状态 0=：未启用；1：启用")
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSetId(Long setId) {
      this.setId = setId;
   }

   public Long getSetId() {
      return this.setId;
   }

   public void setFieldCd(String fieldCd) {
      this.fieldCd = fieldCd;
   }

   public String getFieldCd() {
      return this.fieldCd;
   }

   public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
   }

   public String getFieldName() {
      return this.fieldName;
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

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setDataLong(Long dataLong) {
      this.dataLong = dataLong;
   }

   public Long getDataLong() {
      return this.dataLong;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("setId", this.getSetId()).append("fieldCd", this.getFieldCd()).append("fieldName", this.getFieldName()).append("inputstrphtc", this.getInputstrphtc()).append("inputstrelse", this.getInputstrelse()).append("dataType", this.getDataType()).append("dataLong", this.getDataLong()).append("remark", this.getRemark()).append("validFlag", this.getValidFlag()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).toString();
   }
}
