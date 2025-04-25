package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmRsDictTypt extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "字典来源名称"
   )
   private String sourceName;
   @Excel(
      name = "字典来源编码"
   )
   private String sourceCode;
   @Excel(
      name = "字典类型编码"
   )
   @ApiModelProperty(
      value = "字典类型编码",
      required = true
   )
   private String dictTypeCode;
   @Excel(
      name = "字典类型名称"
   )
   @ApiModelProperty(
      value = "字典类型名称",
      required = true
   )
   private String dictTypeName;
   @Excel(
      name = "字典类型状态"
   )
   @ApiModelProperty(
      value = "字典类型状态 0:启用 1：禁用",
      required = true
   )
   private String state;
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
      name = "创建人"
   )
   private String crePerName;
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
   private Date altPerDate;
   @Excel(
      name = "上报数据定义id"
   )
   @ApiModelProperty(
      value = "上报数据定义id",
      required = true
   )
   private Long defineId;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSourceName(String sourceName) {
      this.sourceName = sourceName;
   }

   public String getSourceName() {
      return this.sourceName;
   }

   public void setSourceCode(String sourceCode) {
      this.sourceCode = sourceCode;
   }

   public String getSourceCode() {
      return this.sourceCode;
   }

   public void setDictTypeCode(String dictTypeCode) {
      this.dictTypeCode = dictTypeCode;
   }

   public String getDictTypeCode() {
      return this.dictTypeCode;
   }

   public void setDictTypeName(String dictTypeName) {
      this.dictTypeName = dictTypeName;
   }

   public String getDictTypeName() {
      return this.dictTypeName;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerDate(Date altPerDate) {
      this.altPerDate = altPerDate;
   }

   public Date getAltPerDate() {
      return this.altPerDate;
   }

   public void setDefineId(Long defineId) {
      this.defineId = defineId;
   }

   public Long getDefineId() {
      return this.defineId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("sourceName", this.getSourceName()).append("sourceCode", this.getSourceCode()).append("dictTypeCode", this.getDictTypeCode()).append("dictTypeName", this.getDictTypeName()).append("state", this.getState()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altPerName", this.getAltPerName()).append("altPerDate", this.getAltPerDate()).append("remark", this.getRemark()).append("defineId", this.getDefineId()).toString();
   }
}
