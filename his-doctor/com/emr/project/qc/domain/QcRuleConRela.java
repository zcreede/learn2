package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRuleConRela extends BaseEntity {
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
      name = "元素1ID"
   )
   private String eleId1;
   @Excel(
      name = "元素1名称"
   )
   private String eleName1;
   @Excel(
      name = "元素2ID"
   )
   private String eleId2;
   @Excel(
      name = "元素2名称"
   )
   private String eleName2;
   @Excel(
      name = "严重程度 见字典"
   )
   private String defeLevel;
   @Excel(
      name = "添加方式  1 ：系统预置  2：人工添加"
   )
   private String addMeth;
   @Excel(
      name = "运算符号"
   )
   private String comChar;
   @Excel(
      name = "规则描述"
   )
   private String ruleDesc;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "校验表达式脚本"
   )
   private String expres;
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
   @Excel(
      name = "规则文件"
   )
   private String ruleFile;

   public String getRuleFile() {
      return this.ruleFile;
   }

   public void setRuleFile(String ruleFile) {
      this.ruleFile = ruleFile;
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

   public void setEleId1(String eleId1) {
      this.eleId1 = eleId1;
   }

   public String getEleId1() {
      return this.eleId1;
   }

   public void setEleName1(String eleName1) {
      this.eleName1 = eleName1;
   }

   public String getEleName1() {
      return this.eleName1;
   }

   public void setEleId2(String eleId2) {
      this.eleId2 = eleId2;
   }

   public String getEleId2() {
      return this.eleId2;
   }

   public void setEleName2(String eleName2) {
      this.eleName2 = eleName2;
   }

   public String getEleName2() {
      return this.eleName2;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setAddMeth(String addMeth) {
      this.addMeth = addMeth;
   }

   public String getAddMeth() {
      return this.addMeth;
   }

   public void setComChar(String comChar) {
      this.comChar = comChar;
   }

   public String getComChar() {
      return this.comChar;
   }

   public void setRuleDesc(String ruleDesc) {
      this.ruleDesc = ruleDesc;
   }

   public String getRuleDesc() {
      return this.ruleDesc;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setExpres(String expres) {
      this.expres = expres;
   }

   public String getExpres() {
      return this.expres;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleName", this.getRuleName()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("eleId1", this.getEleId1()).append("eleName1", this.getEleName1()).append("eleId2", this.getEleId2()).append("eleName2", this.getEleName2()).append("defeLevel", this.getDefeLevel()).append("addMeth", this.getAddMeth()).append("comChar", this.getComChar()).append("ruleDesc", this.getRuleDesc()).append("validFlag", this.getValidFlag()).append("expres", this.getExpres()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("ruleFile", this.getRuleFile()).toString();
   }
}
