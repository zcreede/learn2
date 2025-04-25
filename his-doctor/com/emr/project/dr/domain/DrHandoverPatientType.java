package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverPatientType extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long typeCode;
   @Excel(
      name = "患者标识名称"
   )
   private String typeName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "数据来源sql语句"
   )
   private String dataSql;
   @Excel(
      name = "对应的数据汇总字段(main表中的汇总字段）"
   )
   private String numColumn;
   @Excel(
      name = "对应的数据汇总字段名称"
   )
   private String numColumnName;
   @Excel(
      name = "排序",
      readConverterExp = "存=患者标识中序号最小的"
   )
   private Long sort;
   @Excel(
      name = "启用标志"
   )
   private String enabled;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;

   public void setTypeCode(Long typeCode) {
      this.typeCode = typeCode;
   }

   public Long getTypeCode() {
      return this.typeCode;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setDataSql(String dataSql) {
      this.dataSql = dataSql;
   }

   public String getDataSql() {
      return this.dataSql;
   }

   public void setNumColumn(String numColumn) {
      this.numColumn = numColumn;
   }

   public String getNumColumn() {
      return this.numColumn;
   }

   public String getNumColumnName() {
      return this.numColumnName;
   }

   public void setNumColumnName(String numColumnName) {
      this.numColumnName = numColumnName;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("typeCode", this.getTypeCode()).append("typeName", this.getTypeName()).append("inputstrphtc", this.getInputstrphtc()).append("dataSql", this.getDataSql()).append("numColumn", this.getNumColumn()).append("sort", this.getSort()).append("enabled", this.getEnabled()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
