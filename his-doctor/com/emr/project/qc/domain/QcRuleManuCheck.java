package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRuleManuCheck extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "规则编号"
   )
   private String ruleCode;
   @Excel(
      name = "规则名称"
   )
   private String ruleName;
   @Excel(
      name = "病历类型编码"
   )
   private String emrTypeCode;
   @Excel(
      name = "病历类型名称"
   )
   private String emrTypeName;
   @Excel(
      name = "严重程度"
   )
   private String defeLevel;
   @Excel(
      name = "规则描述"
   )
   private String ruleDesc;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   private String delFlag;
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
   private String elemFlag;
   private String ruleTypeCode;
   private String ruleTypeName;
   private String elemFlagName;

   public String getRuleTypeCode() {
      return this.ruleTypeCode;
   }

   public void setRuleTypeCode(String ruleTypeCode) {
      this.ruleTypeCode = ruleTypeCode;
   }

   public String getRuleTypeName() {
      return this.ruleTypeName;
   }

   public void setRuleTypeName(String ruleTypeName) {
      this.ruleTypeName = ruleTypeName;
   }

   public String getElemFlag() {
      return this.elemFlag;
   }

   public void setElemFlag(String elemFlag) {
      this.elemFlag = elemFlag;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setRuleCode(String ruleCode) {
      this.ruleCode = ruleCode;
   }

   public String getRuleCode() {
      return this.ruleCode;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
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

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getRuleDesc() {
      return this.ruleDesc;
   }

   public void setRuleDesc(String ruleDesc) {
      this.ruleDesc = ruleDesc;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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

   public String getElemFlagName() {
      return this.elemFlagName;
   }

   public void setElemFlagName(String elemFlagName) {
      this.elemFlagName = elemFlagName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleName", this.getRuleName()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("defeLevel", this.getDefeLevel()).append("ruleDesc", this.getRuleDesc()).append("validFlag", this.getValidFlag()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("elemFlag", this.getElemFlag()).append("ruleTypeCode", this.getRuleTypeCode()).append("ruleTypeName", this.getRuleTypeName()).append("elemFlagName", this.getElemFlagName()).toString();
   }
}
