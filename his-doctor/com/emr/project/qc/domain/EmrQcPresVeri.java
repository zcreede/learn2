package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcPresVeri extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "校验时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date veriDate;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "作用病历类型名称"
   )
   private String mrTypeName;
   @Excel(
      name = "作用病历类型"
   )
   private String mrType;
   @Excel(
      name = "病历文件索引ID"
   )
   private String mrFileId;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "记录日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date recoDate;
   @Excel(
      name = "病历文件显示名称"
   )
   private String mrFileShowName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "病历创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date mrCreDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "提交签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date signTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "病历书写时效截止时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date deadline;
   @Excel(
      name = "病历超时校验结果",
      readConverterExp = "1=：超时,0=,未=超时,2=,未=校验"
   )
   private Integer veriResult;
   @Excel(
      name = "提交超时时间，单位小时"
   )
   private Double fsotHours;
   @Excel(
      name = "校验规则ID"
   )
   private Long veriRuleId;
   @Excel(
      name = "校验规则编号"
   )
   private String veriRuleCd;
   @Excel(
      name = "校验规则名称"
   )
   private String veriRuleName;
   @Excel(
      name = "校验依据事件类型编号"
   )
   private String ruleEventType;
   @Excel(
      name = "校验依据事件类型名称"
   )
   private String ruleEventTypeName;
   @Excel(
      name = "校验事件ID"
   )
   private Long eventId;
   @Excel(
      name = "校验事件描述信息",
      readConverterExp = "事=件开始时间、结束时间、责任医师、事件关联业务事件ID"
   )
   private String eventDesc;
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

   public void setVeriDate(Date veriDate) {
      this.veriDate = veriDate;
   }

   public Date getVeriDate() {
      return this.veriDate;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrCreDate(Date mrCreDate) {
      this.mrCreDate = mrCreDate;
   }

   public Date getMrCreDate() {
      return this.mrCreDate;
   }

   public void setSignTime(Date signTime) {
      this.signTime = signTime;
   }

   public Date getSignTime() {
      return this.signTime;
   }

   public void setDeadline(Date deadline) {
      this.deadline = deadline;
   }

   public Date getDeadline() {
      return this.deadline;
   }

   public void setVeriResult(Integer veriResult) {
      this.veriResult = veriResult;
   }

   public Integer getVeriResult() {
      return this.veriResult;
   }

   public void setFsotHours(Double fsotHours) {
      this.fsotHours = fsotHours;
   }

   public Double getFsotHours() {
      return this.fsotHours;
   }

   public void setRuleEventType(String ruleEventType) {
      this.ruleEventType = ruleEventType;
   }

   public String getRuleEventType() {
      return this.ruleEventType;
   }

   public void setRuleEventTypeName(String ruleEventTypeName) {
      this.ruleEventTypeName = ruleEventTypeName;
   }

   public String getRuleEventTypeName() {
      return this.ruleEventTypeName;
   }

   public void setEventId(Long eventId) {
      this.eventId = eventId;
   }

   public Long getEventId() {
      return this.eventId;
   }

   public void setEventDesc(String eventDesc) {
      this.eventDesc = eventDesc;
   }

   public String getEventDesc() {
      return this.eventDesc;
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

   public Long getVeriRuleId() {
      return this.veriRuleId;
   }

   public void setVeriRuleId(Long veriRuleId) {
      this.veriRuleId = veriRuleId;
   }

   public String getVeriRuleCd() {
      return this.veriRuleCd;
   }

   public void setVeriRuleCd(String veriRuleCd) {
      this.veriRuleCd = veriRuleCd;
   }

   public String getVeriRuleName() {
      return this.veriRuleName;
   }

   public void setVeriRuleName(String veriRuleName) {
      this.veriRuleName = veriRuleName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("veriDate", this.getVeriDate()).append("patientId", this.getPatientId()).append("mrTypeName", this.getMrTypeName()).append("mrType", this.getMrType()).append("mrFileId", this.getMrFileId()).append("recoDate", this.getRecoDate()).append("mrFileShowName", this.getMrFileShowName()).append("mrCreDate", this.getMrCreDate()).append("signTime", this.getSignTime()).append("deadline", this.getDeadline()).append("veriResult", this.getVeriResult()).append("fsotHours", this.getFsotHours()).append("veriRuleId", this.getVeriRuleId()).append("veriRuleCd", this.getVeriRuleCd()).append("veriRuleName", this.getVeriRuleName()).append("ruleEventType", this.getRuleEventType()).append("ruleEventTypeName", this.getRuleEventTypeName()).append("eventId", this.getEventId()).append("eventDesc", this.getEventDesc()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
