package com.emr.project.qc.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EmrQcListStatisticVo {
   private Long id;
   private Long qcId;
   private String deptCd;
   private String caseNo;
   private String deptName;
   private String patientId;
   private String patientName;
   private String inpNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date inDeptTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date outDeptTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date firstFileTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date feedbackQcTime;
   private String doctCd;
   private String qcSection;
   private String qcSectionName;
   private String doctName;
   private String mrType;
   private String mrState;
   private String mrTypeName;
   private String ruleId;
   private String ruleName;
   private String qcState;
   private String qcStateName;
   private String resDocCode;
   private String resDocName;
   private String flawDesc;
   private String inDeptTimeS;
   private String outDeptTimeS;
   private String feedbackQcTimeS;
   private String state;
   private String crePerName;
   private Double dedScoreSingle;
   private Long qcBillNo;

   public Long getQcBillNo() {
      return this.qcBillNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public void setQcBillNo(Long qcBillNo) {
      this.qcBillNo = qcBillNo;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public Long getQcId() {
      return this.qcId;
   }

   public void setQcId(Long qcId) {
      this.qcId = qcId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getQcSectionName() {
      return this.qcSectionName;
   }

   public void setQcSectionName(String qcSectionName) {
      this.qcSectionName = qcSectionName;
   }

   public String getQcStateName() {
      return this.qcStateName;
   }

   public void setQcStateName(String qcStateName) {
      this.qcStateName = qcStateName;
   }

   public String getFlawDesc() {
      return this.flawDesc;
   }

   public void setFlawDesc(String flawDesc) {
      this.flawDesc = flawDesc;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public String getResDocCode() {
      return this.resDocCode;
   }

   public void setResDocCode(String resDocCode) {
      this.resDocCode = resDocCode;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getInDeptTimeS() {
      return this.inDeptTimeS;
   }

   public void setInDeptTimeS(String inDeptTimeS) {
      this.inDeptTimeS = inDeptTimeS;
   }

   public String getOutDeptTimeS() {
      return this.outDeptTimeS;
   }

   public void setOutDeptTimeS(String outDeptTimeS) {
      this.outDeptTimeS = outDeptTimeS;
   }

   public String getFeedbackQcTimeS() {
      return this.feedbackQcTimeS;
   }

   public void setFeedbackQcTimeS(String feedbackQcTimeS) {
      this.feedbackQcTimeS = feedbackQcTimeS;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public Date getInDeptTime() {
      return this.inDeptTime;
   }

   public void setInDeptTime(Date inDeptTime) {
      this.inDeptTime = inDeptTime;
   }

   public Date getOutDeptTime() {
      return this.outDeptTime;
   }

   public void setOutDeptTime(Date outDeptTime) {
      this.outDeptTime = outDeptTime;
   }

   public Date getFirstFileTime() {
      return this.firstFileTime;
   }

   public void setFirstFileTime(Date firstFileTime) {
      this.firstFileTime = firstFileTime;
   }

   public Date getFeedbackQcTime() {
      return this.feedbackQcTime;
   }

   public void setFeedbackQcTime(Date feedbackQcTime) {
      this.feedbackQcTime = feedbackQcTime;
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(String ruleId) {
      this.ruleId = ruleId;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getQcState() {
      return this.qcState;
   }

   public void setQcState(String qcState) {
      this.qcState = qcState;
   }
}
