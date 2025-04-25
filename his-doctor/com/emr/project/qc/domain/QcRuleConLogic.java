package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcRuleConLogic extends BaseEntity {
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
      name = "严重程度 见字典"
   )
   private String defeLevel;
   @Excel(
      name = "添加方式  1 ：系统预置  2：人工添加"
   )
   private String addMeth;
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
   private String expres1;
   @Excel(
      name = "校验表达式描述"
   )
   private String expres1Desc;
   @Excel(
      name = "关联表达式脚本"
   )
   private String expres2;
   @Excel(
      name = "关联表达式描述"
   )
   private String expres2Desc;
   @Excel(
      name = "完整表达式脚本"
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

   public void setExpres1(String expres1) {
      this.expres1 = expres1;
   }

   public String getExpres1() {
      return this.expres1;
   }

   public void setExpres1Desc(String expres1Desc) {
      this.expres1Desc = expres1Desc;
   }

   public String getExpres1Desc() {
      return this.expres1Desc;
   }

   public void setExpres2(String expres2) {
      this.expres2 = expres2;
   }

   public String getExpres2() {
      return this.expres2;
   }

   public void setExpres2Desc(String expres2Desc) {
      this.expres2Desc = expres2Desc;
   }

   public String getExpres2Desc() {
      return this.expres2Desc;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleName", this.getRuleName()).append("emrTypeCode", this.getEmrTypeCode()).append("emrTypeName", this.getEmrTypeName()).append("defeLevel", this.getDefeLevel()).append("addMeth", this.getAddMeth()).append("ruleDesc", this.getRuleDesc()).append("validFlag", this.getValidFlag()).append("expres1", this.getExpres1()).append("expres1Desc", this.getExpres1Desc()).append("expres2", this.getExpres2()).append("expres2Desc", this.getExpres2Desc()).append("expres", this.getExpres()).append("delFlag", this.getDelFlag()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("ruleFile", this.getRuleFile()).toString();
   }
}
