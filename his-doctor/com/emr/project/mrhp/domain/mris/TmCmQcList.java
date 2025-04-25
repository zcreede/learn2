package com.emr.project.mrhp.domain.mris;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmCmQcList extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("质控问题id")
   private Long id;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "医院院区代码"
   )
   private String orgCd;
   @ApiModelProperty(
      value = "问题关联病案ID",
      required = true
   )
   @Excel(
      name = "问题关联病案ID"
   )
   private String recordId;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题编码"
   )
   private String proCode;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题关联规则ID"
   )
   private String proRuleId;
   @ApiModelProperty("关联字段")
   @Excel(
      name = "问题关联字段"
   )
   private String proCorrelaField;
   @ApiModelProperty("定位字段")
   @Excel(
      name = "问题定位字段"
   )
   private String proLocationField;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题关联字段排序号"
   )
   private String proCorrelaSort;
   @ApiModelProperty("问题名称")
   @Excel(
      name = "问题名称"
   )
   private String proName;
   @ApiModelProperty("问题错误提示")
   @Excel(
      name = "问题错误提示"
   )
   private String proErrorHint;
   @ApiModelProperty("问题处理状态 (0 未处理 1已解决 2忽略)")
   @Excel(
      name = "问题处理状态 (0 未处理 1已解决 2忽略)"
   )
   private String treatState;
   @ApiModelProperty("问题强制类型 0非强制 1强制")
   @Excel(
      name = "问题强制类型"
   )
   private String forceType;
   @ApiModelProperty("问题创建时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "问题创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date creDate;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题创建人编码"
   )
   private String crePerCode;
   @ApiModelProperty("问题创建人名称")
   @Excel(
      name = "问题创建人名称"
   )
   private String crePerName;
   @ApiModelProperty(
      hidden = true
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "问题修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题修改人编码"
   )
   private String altPerCode;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "问题修改人名称"
   )
   private String altPerName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setProCode(String proCode) {
      this.proCode = proCode;
   }

   public String getProCode() {
      return this.proCode;
   }

   public void setProRuleId(String proRuleId) {
      this.proRuleId = proRuleId;
   }

   public String getProRuleId() {
      return this.proRuleId;
   }

   public void setProCorrelaField(String proCorrelaField) {
      this.proCorrelaField = proCorrelaField;
   }

   public String getProCorrelaField() {
      return this.proCorrelaField;
   }

   public void setProLocationField(String proLocationField) {
      this.proLocationField = proLocationField;
   }

   public String getProLocationField() {
      return this.proLocationField;
   }

   public void setProCorrelaSort(String proCorrelaSort) {
      this.proCorrelaSort = proCorrelaSort;
   }

   public String getProCorrelaSort() {
      return this.proCorrelaSort;
   }

   public void setProName(String proName) {
      this.proName = proName;
   }

   public String getProName() {
      return this.proName;
   }

   public void setProErrorHint(String proErrorHint) {
      this.proErrorHint = proErrorHint;
   }

   public String getProErrorHint() {
      return this.proErrorHint;
   }

   public void setTreatState(String treatState) {
      this.treatState = treatState;
   }

   public String getTreatState() {
      return this.treatState;
   }

   public void setForceType(String forceType) {
      this.forceType = forceType;
   }

   public String getForceType() {
      return this.forceType;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("recordId", this.getRecordId()).append("proCode", this.getProCode()).append("proRuleId", this.getProRuleId()).append("proCorrelaField", this.getProCorrelaField()).append("proLocationField", this.getProLocationField()).append("proCorrelaSort", this.getProCorrelaSort()).append("proName", this.getProName()).append("proErrorHint", this.getProErrorHint()).append("treatState", this.getTreatState()).append("forceType", this.getForceType()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
