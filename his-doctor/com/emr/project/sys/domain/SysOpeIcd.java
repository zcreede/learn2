package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysOpeIcd extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "id"
   )
   private Long icdId;
   @Excel(
      name = "ICD-9-CM中ICD编码"
   )
   private String icdCd;
   @Excel(
      name = "ICD名称"
   )
   private String icdName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "版本号"
   )
   private String verNum;
   @Excel(
      name = "手术级别"
   )
   private String levelCode;
   @Excel(
      name = "在ICD-9-CM-3中，按照操作的目的，可以将操作分为诊断性操作和治疗性操作"
   )
   private String opePur;
   @Excel(
      name = "手术类型"
   )
   private String opeType;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
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
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private String sex;
   private String medicalInsuranceCode;
   private String medicalInsuranceName;

   public String getMedicalInsuranceCode() {
      return this.medicalInsuranceCode;
   }

   public void setMedicalInsuranceCode(String medicalInsuranceCode) {
      this.medicalInsuranceCode = medicalInsuranceCode;
   }

   public String getMedicalInsuranceName() {
      return this.medicalInsuranceName;
   }

   public void setMedicalInsuranceName(String medicalInsuranceName) {
      this.medicalInsuranceName = medicalInsuranceName;
   }

   public void setIcdId(Long icdId) {
      this.icdId = icdId;
   }

   public Long getIcdId() {
      return this.icdId;
   }

   public void setIcdCd(String icdCd) {
      this.icdCd = icdCd;
   }

   public String getIcdCd() {
      return this.icdCd;
   }

   public void setIcdName(String icdName) {
      this.icdName = icdName;
   }

   public String getIcdName() {
      return this.icdName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setVerNum(String verNum) {
      this.verNum = verNum;
   }

   public String getVerNum() {
      return this.verNum;
   }

   public void setLevelCode(String levelCode) {
      this.levelCode = levelCode;
   }

   public String getLevelCode() {
      return this.levelCode;
   }

   public void setOpePur(String opePur) {
      this.opePur = opePur;
   }

   public String getOpePur() {
      return this.opePur;
   }

   public void setOpeType(String opeType) {
      this.opeType = opeType;
   }

   public String getOpeType() {
      return this.opeType;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("icdId", this.getIcdId()).append("icdCd", this.getIcdCd()).append("icdName", this.getIcdName()).append("inputstrphtc", this.getInputstrphtc()).append("verNum", this.getVerNum()).append("levelCode", this.getLevelCode()).append("opePur", this.getOpePur()).append("opeType", this.getOpeType()).append("validFlag", this.getValidFlag()).append("remark", this.getRemark()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("sex", this.getSex()).toString();
   }
}
