package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.system.domain.BasEmployee;
import java.util.Date;
import java.util.List;

public class EmrTaskInfoVo extends EmrTaskInfo {
   private Integer remainHours;
   private String remainTime;
   private String resDocName;
   private String evenName;
   private String emrTypeName;
   private String emrTypeCode;
   private String remainingTime;
   private Long applId;
   private String conState;
   private String dataType;
   private Long ruleId;
   private String ruleName;
   private String mrFileShowName;
   private String mrFileIdStr;
   private Long mainId;
   private String qcSection;
   private String patientMainId;
   private String messageStr;
   private String bedNo;
   private String sex;
   private String iterTypeCode;
   private String iterTypeName;
   private Date endDatetime;
   private String recordNo;
   private List basEmployeeList;

   public List getBasEmployeeList() {
      return this.basEmployeeList;
   }

   public void setBasEmployeeList(List basEmployeeList) {
      this.basEmployeeList = basEmployeeList;
   }

   public String getIterTypeCode() {
      return this.iterTypeCode;
   }

   public void setIterTypeCode(String iterTypeCode) {
      this.iterTypeCode = iterTypeCode;
   }

   public String getIterTypeName() {
      return this.iterTypeName;
   }

   public void setIterTypeName(String iterTypeName) {
      this.iterTypeName = iterTypeName;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public String getMrFileIdStr() {
      return this.mrFileIdStr;
   }

   public void setMrFileIdStr(String mrFileIdStr) {
      this.mrFileIdStr = mrFileIdStr;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public Long getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(Long ruleId) {
      this.ruleId = ruleId;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public Long getApplId() {
      return this.applId;
   }

   public void setApplId(Long applId) {
      this.applId = applId;
   }

   public String getConState() {
      return this.conState;
   }

   public void setConState(String conState) {
      this.conState = conState;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getRemainingTime() {
      return this.remainingTime;
   }

   public void setRemainingTime(String remainingTime) {
      this.remainingTime = remainingTime;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public String getEvenName() {
      return this.evenName;
   }

   public void setEvenName(String evenName) {
      this.evenName = evenName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getRemainTime() {
      return this.remainTime;
   }

   public void setRemainTime(String remainTime) {
      this.remainTime = remainTime;
   }

   public Integer getRemainHours() {
      return this.remainHours;
   }

   public void setRemainHours(Integer remainHours) {
      this.remainHours = remainHours;
   }

   public Date getEndDatetime() {
      return this.endDatetime;
   }

   public void setEndDatetime(Date endDatetime) {
      this.endDatetime = endDatetime;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }
}
