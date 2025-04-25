package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmRsDictData extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @ApiModelProperty(
      value = "字典类型编码",
      required = true
   )
   @Excel(
      name = "字典类型编码"
   )
   private String typeCode;
   @ApiModelProperty(
      value = "字典明细名称",
      required = true
   )
   @Excel(
      name = "字典明细名称"
   )
   private String dataName;
   @ApiModelProperty(
      value = "数据键值",
      required = true
   )
   @Excel(
      name = "数据键值"
   )
   private String dataVal;
   @ApiModelProperty("拼音码")
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @ApiModelProperty(
      value = "状态  0:启用 1：禁用",
      required = true
   )
   @Excel(
      name = "状态"
   )
   private String state;
   @ApiModelProperty("排序号")
   @Excel(
      name = "排序号"
   )
   private String sort;
   @Excel(
      name = "创建人"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "更新人"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "更新时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @ApiModelProperty(
      value = "上报数据定义id",
      required = true
   )
   @Excel(
      name = "上报数据定义id"
   )
   private Long defineId;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setDataName(String dataName) {
      this.dataName = dataName;
   }

   public String getDataName() {
      return this.dataName;
   }

   public void setDataVal(String dataVal) {
      this.dataVal = dataVal;
   }

   public String getDataVal() {
      return this.dataVal;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
   }

   public void setSort(String sort) {
      this.sort = sort;
   }

   public String getSort() {
      return this.sort;
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

   public void setDefineId(Long defineId) {
      this.defineId = defineId;
   }

   public Long getDefineId() {
      return this.defineId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("typeCode", this.getTypeCode()).append("dataName", this.getDataName()).append("dataVal", this.getDataVal()).append("inputstrphtc", this.getInputstrphtc()).append("state", this.getState()).append("sort", this.getSort()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("remark", this.getRemark()).append("defineId", this.getDefineId()).toString();
   }
}
