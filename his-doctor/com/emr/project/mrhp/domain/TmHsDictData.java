package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmHsDictData extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "字典类型编码"
   )
   private String dictType;
   @Excel(
      name = "字典明细名称"
   )
   private String dataName;
   @Excel(
      name = "数据键值"
   )
   private String dataVal;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "状态"
   )
   private String state;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("dictType", this.getDictType()).append("dataName", this.getDataName()).append("dataVal", this.getDataVal()).append("inputstrphtc", this.getInputstrphtc()).append("state", this.getState()).append("sort", this.getSort()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("remark", this.getRemark()).toString();
   }
}
