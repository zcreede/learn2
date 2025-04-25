package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(
   description = "首页校验规则对象"
)
public class TmCmRules extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("主键id")
   private Long id;
   @ApiModelProperty("规则编码")
   @Excel(
      name = "规则编码"
   )
   private String ruleCode;
   @ApiModelProperty(
      value = "规则质控字段",
      required = true
   )
   @Excel(
      name = "规则质控字段"
   )
   private String ruleQcFieids;
   @ApiModelProperty(
      value = "规则定位字段",
      required = true
   )
   @Excel(
      name = "规则定位字段"
   )
   private String ruleLocationFieids;
   @Excel(
      name = "规则定位字段排序号"
   )
   private Long ruleLocationFieidsSort;
   @ApiModelProperty(
      value = "规则来源编码,字典值s109",
      required = true
   )
   @Excel(
      name = "规则来源编码"
   )
   private String ruleSourceCode;
   @Excel(
      name = "规则来源名称"
   )
   @ApiModelProperty(
      value = "规则来源名称,字典值s109",
      required = true
   )
   private String ruleSourceName;
   @Excel(
      name = "规则分类编码"
   )
   @ApiModelProperty(
      value = "规则分类编码,字典值s108",
      required = true
   )
   private String ruleTypeCode;
   @Excel(
      name = "规则分类名称"
   )
   @ApiModelProperty(
      value = "规则分类名称,字典值s108",
      required = true
   )
   private String ruleTypeName;
   @Excel(
      name = "规则类型"
   )
   @ApiModelProperty(
      value = "规则类型,TY:通用；  ZY：中医使用",
      required = true
   )
   private String ruleType;
   @ApiModelProperty(
      value = "规则名称, 取质控字段名称",
      required = true
   )
   @Excel(
      name = "规则名称"
   )
   private String ruleName;
   @ApiModelProperty(
      value = "规则描述,最多100个字段",
      required = true
   )
   @Excel(
      name = "规则描述"
   )
   private String ruleDesc;
   @ApiModelProperty(
      value = "规则脚本,最多2000个字段",
      required = true
   )
   @Excel(
      name = "规则脚本"
   )
   private String ruleScript;
   @ApiModelProperty(
      value = "规则状态 0:启用,1:禁用",
      required = true
   )
   @Excel(
      name = "规则状态"
   )
   private String ruleState;
   @ApiModelProperty(
      value = "强制类型 1:强制,0:非强制",
      required = true
   )
   @Excel(
      name = "强制类型"
   )
   private String forceType;
   @ApiModelProperty(
      value = "强制类型名称 1:强制,0:非强制",
      required = true
   )
   @Excel(
      name = "强制类型名称"
   )
   private String forceTypeName;
   private String delFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
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
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人姓名"
   )
   private String crePerName;

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

   public void setRuleQcFieids(String ruleQcFieids) {
      this.ruleQcFieids = ruleQcFieids;
   }

   public String getRuleQcFieids() {
      return this.ruleQcFieids;
   }

   public void setRuleLocationFieids(String ruleLocationFieids) {
      this.ruleLocationFieids = ruleLocationFieids;
   }

   public String getRuleLocationFieids() {
      return this.ruleLocationFieids;
   }

   public void setRuleLocationFieidsSort(Long ruleLocationFieidsSort) {
      this.ruleLocationFieidsSort = ruleLocationFieidsSort;
   }

   public Long getRuleLocationFieidsSort() {
      return this.ruleLocationFieidsSort;
   }

   public void setRuleSourceCode(String ruleSourceCode) {
      this.ruleSourceCode = ruleSourceCode;
   }

   public String getRuleSourceCode() {
      return this.ruleSourceCode;
   }

   public void setRuleSourceName(String ruleSourceName) {
      this.ruleSourceName = ruleSourceName;
   }

   public String getRuleSourceName() {
      return this.ruleSourceName;
   }

   public void setRuleTypeCode(String ruleTypeCode) {
      this.ruleTypeCode = ruleTypeCode;
   }

   public String getRuleTypeCode() {
      return this.ruleTypeCode;
   }

   public void setRuleTypeName(String ruleTypeName) {
      this.ruleTypeName = ruleTypeName;
   }

   public String getRuleTypeName() {
      return this.ruleTypeName;
   }

   public void setRuleType(String ruleType) {
      this.ruleType = ruleType;
   }

   public String getRuleType() {
      return this.ruleType;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleDesc(String ruleDesc) {
      this.ruleDesc = ruleDesc;
   }

   public String getRuleDesc() {
      return this.ruleDesc;
   }

   public void setRuleScript(String ruleScript) {
      this.ruleScript = ruleScript;
   }

   public String getRuleScript() {
      return this.ruleScript;
   }

   public void setRuleState(String ruleState) {
      this.ruleState = ruleState;
   }

   public String getRuleState() {
      return this.ruleState;
   }

   public void setForceType(String forceType) {
      this.forceType = forceType;
   }

   public String getForceType() {
      return this.forceType;
   }

   public void setForceTypeName(String forceTypeName) {
      this.forceTypeName = forceTypeName;
   }

   public String getForceTypeName() {
      return this.forceTypeName;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("ruleCode", this.getRuleCode()).append("ruleQcFieids", this.getRuleQcFieids()).append("ruleLocationFieids", this.getRuleLocationFieids()).append("ruleLocationFieidsSort", this.getRuleLocationFieidsSort()).append("ruleSourceCode", this.getRuleSourceCode()).append("ruleSourceName", this.getRuleSourceName()).append("ruleTypeCode", this.getRuleTypeCode()).append("ruleTypeName", this.getRuleTypeName()).append("ruleType", this.getRuleType()).append("ruleName", this.getRuleName()).append("ruleDesc", this.getRuleDesc()).append("ruleScript", this.getRuleScript()).append("ruleState", this.getRuleState()).append("forceType", this.getForceType()).append("forceTypeName", this.getForceTypeName()).append("delFlag", this.getDelFlag()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
