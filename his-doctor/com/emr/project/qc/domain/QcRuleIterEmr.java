package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRuleIterEmr extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病历类型编码"
   )
   private String emrTypeCode;
   @Excel(
      name = "病历类型名称"
   )
   private String emrTypeName;
   @Excel(
      name = "可替代病历类型编码"
   )
   private String iterTypeCode;
   @Excel(
      name = "可替代类型名称"
   )
   private String iterTypeName;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
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
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
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
      name = "启用标识",
      readConverterExp = "0=：未删除；1：删除"
   )
   private String delFlag;

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setIterTypeCode(String iterTypeCode) {
      this.iterTypeCode = iterTypeCode;
   }

   public String getIterTypeCode() {
      return this.iterTypeCode;
   }

   public void setIterTypeName(String iterTypeName) {
      this.iterTypeName = iterTypeName;
   }

   public String getIterTypeName() {
      return this.iterTypeName;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("iterTypeCode", this.getIterTypeCode()).append("iterTypeName", this.getIterTypeName()).append("validFlag", this.getValidFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("delFlag", this.getDelFlag()).toString();
   }
}
