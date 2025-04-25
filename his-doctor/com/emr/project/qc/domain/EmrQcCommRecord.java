package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcCommRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "质控明细记录ID"
   )
   private Long mainId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "病历文件索引ID，文档存文档ID，医嘱本存医嘱类型",
      readConverterExp = "临=时医嘱、长期医嘱、汤剂医嘱"
   )
   private String mrFileId;
   @Excel(
      name = "质控环节  01.实时质控;02.科室质控 ;03.抽查质控;04.离线质控05.终末质控; 5.质控组抽检;6.病案室病历抽检;7.病案室质控评分; 8.专家组归档病历抽检"
   )
   private String qcSection;
   @Excel(
      name = "病历类型"
   )
   private String mrType;
   @Excel(
      name = "病历类型名称"
   )
   private String mrTypeName;
   @Excel(
      name = "病历元素ID，用于定位元素位置"
   )
   private String emrEleId;
   @Excel(
      name = "病历元素名称，医嘱、体温单、病案首页存数据字段中文名称"
   )
   private String emrEleName;
   @Excel(
      name = "病历元素内容"
   )
   private String eleContext;
   @Excel(
      name = "缺陷描述"
   )
   private String flawDesc;
   @Excel(
      name = "反馈沟通内容"
   )
   private String fedbDesc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "反馈日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date fedbDate;
   @Excel(
      name = "反馈人员ID"
   )
   private String fedbPerId;
   @Excel(
      name = "反馈人员姓名"
   )
   private String fedbPerName;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
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
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "反馈人角色"
   )
   private String roleCode;

   public String getRoleCode() {
      return this.roleCode;
   }

   public void setRoleCode(String roleCode) {
      this.roleCode = roleCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setEmrEleId(String emrEleId) {
      this.emrEleId = emrEleId;
   }

   public String getEmrEleId() {
      return this.emrEleId;
   }

   public void setEmrEleName(String emrEleName) {
      this.emrEleName = emrEleName;
   }

   public String getEmrEleName() {
      return this.emrEleName;
   }

   public void setEleContext(String eleContext) {
      this.eleContext = eleContext;
   }

   public String getEleContext() {
      return this.eleContext;
   }

   public void setFlawDesc(String flawDesc) {
      this.flawDesc = flawDesc;
   }

   public String getFlawDesc() {
      return this.flawDesc;
   }

   public void setFedbDesc(String fedbDesc) {
      this.fedbDesc = fedbDesc;
   }

   public String getFedbDesc() {
      return this.fedbDesc;
   }

   public void setFedbDate(Date fedbDate) {
      this.fedbDate = fedbDate;
   }

   public Date getFedbDate() {
      return this.fedbDate;
   }

   public void setFedbPerId(String fedbPerId) {
      this.fedbPerId = fedbPerId;
   }

   public String getFedbPerId() {
      return this.fedbPerId;
   }

   public void setFedbPerName(String fedbPerName) {
      this.fedbPerName = fedbPerName;
   }

   public String getFedbPerName() {
      return this.fedbPerName;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mainId", this.getMainId()).append("patientId", this.getPatientId()).append("mrFileId", this.getMrFileId()).append("qcSection", this.getQcSection()).append("mrType", this.getMrType()).append("mrTypeName", this.getMrTypeName()).append("emrEleId", this.getEmrEleId()).append("emrEleName", this.getEmrEleName()).append("eleContext", this.getEleContext()).append("flawDesc", this.getFlawDesc()).append("fedbDesc", this.getFedbDesc()).append("fedbDate", this.getFedbDate()).append("fedbPerId", this.getFedbPerId()).append("fedbPerName", this.getFedbPerName()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
