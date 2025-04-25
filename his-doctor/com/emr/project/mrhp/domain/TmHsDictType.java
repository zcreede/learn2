package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmHsDictType extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("主键id")
   private Long id;
   @ApiModelProperty(
      value = "字典类型编码",
      required = true
   )
   @Excel(
      name = "字典类型编码"
   )
   private String dictType;
   @ApiModelProperty(
      value = "字典类型名称",
      required = true
   )
   @Excel(
      name = "字典类型名称"
   )
   private String dictName;
   @ApiModelProperty("字典类型状态")
   @Excel(
      name = "字典类型状态"
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
   private Date altDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setDictType(String dictType) {
      this.dictType = dictType;
   }

   public String getDictType() {
      return this.dictType;
   }

   public void setDictName(String dictName) {
      this.dictName = dictName;
   }

   public String getDictName() {
      return this.dictName;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("dictType", this.getDictType()).append("dictName", this.getDictName()).append("state", this.getState()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("remark", this.getRemark()).toString();
   }
}
