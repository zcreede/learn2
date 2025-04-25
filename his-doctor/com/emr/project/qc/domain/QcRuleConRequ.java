package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRuleConRequ extends BaseEntity {
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
      name = "规则类型 1 无条件必填   2 有条件必填"
   )
   private String ruleType;
   @Excel(
      name = "病历类型编码"
   )
   private String emrTypeCode;
   @Excel(
      name = "病历类型名称"
   )
   private String emrTypeName;
   @Excel(
      name = "元素ID"
   )
   private String eleId;
   @Excel(
      name = "元素名称"
   )
   private String eleName;
   @Excel(
      name = "严重程度 见字典"
   )
   private String defeLevel;
   @Excel(
      name = "条件元素ID"
   )
   private String eleConId;
   @Excel(
      name = "条件元素名称"
   )
   private String eleConName;
   @Excel(
      name = "条件元素内容"
   )
   private String eleConCont;
   @Excel(
      name = "添加方式  1 ：系统预置  2：人工添加"
   )
   private String addMeth;
   @Excel(
      name = "规则描述"
   )
   private String ruleDesc;
   @Excel(
      name = "校验表达式脚本"
   )
   private String expres;
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
   @Excel(
      name = "规则文件"
   )
   private String ruleFile;
   private String elemFlagName;

   public String getElemFlagName() {
      return this.elemFlagName;
   }

   public void setElemFlagName(String elemFlagName) {
      this.elemFlagName = elemFlagName;
   }

   public String getRuleFile() {
      return this.ruleFile;
   }

   public void setRuleFile(String ruleFile) {
      this.ruleFile = ruleFile;
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

   public void setRuleType(String ruleType) {
      this.ruleType = ruleType;
   }

   public String getRuleType() {
      return this.ruleType;
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

   public void setEleId(String eleId) {
      this.eleId = eleId;
   }

   public String getEleId() {
      return this.eleId;
   }

   public void setEleName(String eleName) {
      this.eleName = eleName;
   }

   public String getEleName() {
      return this.eleName;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setEleConId(String eleConId) {
      this.eleConId = eleConId;
   }

   public String getEleConId() {
      return this.eleConId;
   }

   public void setEleConName(String eleConName) {
      this.eleConName = eleConName;
   }

   public String getEleConName() {
      return this.eleConName;
   }

   public void setEleConCont(String eleConCont) {
      this.eleConCont = eleConCont;
   }

   public String getEleConCont() {
      return this.eleConCont;
   }

   public void setAddMeth(String addMeth) {
      this.addMeth = addMeth;
   }

   public String getAddMeth() {
      return this.addMeth;
   }

   public String getRuleDesc() {
      return this.ruleDesc;
   }

   public void setRuleDesc(String ruleDesc) {
      this.ruleDesc = ruleDesc;
   }

   public void setExpres(String expres) {
      this.expres = expres;
   }

   public String getExpres() {
      return this.expres;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleName", this.getRuleName()).append("ruleType", this.getRuleType()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("eleId", this.getEleId()).append("eleName", this.getEleName()).append("defeLevel", this.getDefeLevel()).append("eleConId", this.getEleConId()).append("eleConName", this.getEleConName()).append("eleConCont", this.getEleConCont()).append("addMeth", this.getAddMeth()).append("ruleDesc", this.getRuleDesc()).append("expres", this.getExpres()).append("validFlag", this.getValidFlag()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("elemFlag", this.getElemFlag()).append("ruleFile", this.getRuleFile()).append("elemFlagName", this.getElemFlagName()).toString();
   }
}
