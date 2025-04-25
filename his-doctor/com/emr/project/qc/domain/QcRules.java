package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRules extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String ruleName;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private Integer ruleType;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String ruleTypeName;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String ruleDesc;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private Date creDate;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String emrTypeName;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String emrTypeCode;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String elemFlag;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String ruleClass;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String defeLevel;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleType(Integer ruleType) {
      this.ruleType = ruleType;
   }

   public Integer getRuleType() {
      return this.ruleType;
   }

   public void setRuleTypeName(String ruleTypeName) {
      this.ruleTypeName = ruleTypeName;
   }

   public String getRuleTypeName() {
      return this.ruleTypeName;
   }

   public void setRuleDesc(String ruleDesc) {
      this.ruleDesc = ruleDesc;
   }

   public String getRuleDesc() {
      return this.ruleDesc;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setElemFlag(String elemFlag) {
      this.elemFlag = elemFlag;
   }

   public String getElemFlag() {
      return this.elemFlag;
   }

   public void setRuleClass(String ruleClass) {
      this.ruleClass = ruleClass;
   }

   public String getRuleClass() {
      return this.ruleClass;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleName", this.getRuleName()).append("ruleType", this.getRuleType()).append("ruleTypeName", this.getRuleTypeName()).append("ruleDesc", this.getRuleDesc()).append("creDate", this.getCreDate()).append("emrTypeName", this.getEmrTypeName()).append("emrTypeCode", this.getEmrTypeCode()).append("elemFlag", this.getElemFlag()).append("ruleClass", this.getRuleClass()).append("defeLevel", this.getDefeLevel()).toString();
   }
}
