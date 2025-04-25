package com.emr.project.qc.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class EmrQcFlowStatisticVo {
   private Long id;
   private Long qcId;
   private String caseNo;
   private String deptCd;
   private String deptName;
   private String patientId;
   private String patientName;
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
   private Date caseFirstFileTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date termQcTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date fileTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operTime;
   private String operReason;
   private String termGradeNo;
   private String termGradeName;
   private String termScore;
   private String termScoreRes;
   private String resDocCd;
   private String resDocName;
   private String deptScore;
   private String deptGradeNo;
   private String deptGradeName;
   private String checkScore;
   private String checkGradeNo;
   private String checkGradeName;
   private Integer filingCount;
   private String mrState;
   private BigDecimal costTotal;
   private String inDeptTimeS;
   private String outDeptTimeS;
   private String firstFileTimeS;
   private String termQcTimeS;
   private String fileTimeS;
   private String operTimeS;
   private String outHospitalTotal;
   private String ygdTotal;
   private String filingProportion;
   private String twoFiling;
   private String twoProportion;
   private String threeFiling;
   private String threeProportion;
   private String sevenFiling;
   private String sevenProportion;
   private String greateSevenFiling;
   private String greateSevenProportion;
   private BigDecimal averageFiling;
   private String jjCount;
   private String jjProportion;
   private String yjCount;
   private String yjProportion;
   private String bjCount;
   private String bjProportion;
   private String fxlCount;
   private String fxlProportion;
   private Integer total;

   public BigDecimal getCostTotal() {
      return this.costTotal;
   }

   public void setCostTotal(BigDecimal costTotal) {
      this.costTotal = costTotal;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public Date getCaseFirstFileTime() {
      return this.caseFirstFileTime;
   }

   public void setCaseFirstFileTime(Date caseFirstFileTime) {
      this.caseFirstFileTime = caseFirstFileTime;
   }

   public Long getQcId() {
      return this.qcId;
   }

   public void setQcId(Long qcId) {
      this.qcId = qcId;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public String getOutHospitalTotal() {
      return this.outHospitalTotal;
   }

   public Date getFileTime() {
      return this.fileTime;
   }

   public void setFileTime(Date fileTime) {
      this.fileTime = fileTime;
   }

   public String getFileTimeS() {
      return this.fileTimeS;
   }

   public void setFileTimeS(String fileTimeS) {
      this.fileTimeS = fileTimeS;
   }

   public void setOutHospitalTotal(String outHospitalTotal) {
      this.outHospitalTotal = outHospitalTotal;
   }

   public String getYgdTotal() {
      return this.ygdTotal;
   }

   public void setYgdTotal(String ygdTotal) {
      this.ygdTotal = ygdTotal;
   }

   public String getFilingProportion() {
      return this.filingProportion;
   }

   public void setFilingProportion(String filingProportion) {
      this.filingProportion = filingProportion;
   }

   public String getTwoFiling() {
      return this.twoFiling;
   }

   public void setTwoFiling(String twoFiling) {
      this.twoFiling = twoFiling;
   }

   public String getTwoProportion() {
      return this.twoProportion;
   }

   public void setTwoProportion(String twoProportion) {
      this.twoProportion = twoProportion;
   }

   public String getThreeFiling() {
      return this.threeFiling;
   }

   public void setThreeFiling(String threeFiling) {
      this.threeFiling = threeFiling;
   }

   public String getThreeProportion() {
      return this.threeProportion;
   }

   public void setThreeProportion(String threeProportion) {
      this.threeProportion = threeProportion;
   }

   public String getSevenFiling() {
      return this.sevenFiling;
   }

   public void setSevenFiling(String sevenFiling) {
      this.sevenFiling = sevenFiling;
   }

   public String getSevenProportion() {
      return this.sevenProportion;
   }

   public void setSevenProportion(String sevenProportion) {
      this.sevenProportion = sevenProportion;
   }

   public String getGreateSevenFiling() {
      return this.greateSevenFiling;
   }

   public void setGreateSevenFiling(String greateSevenFiling) {
      this.greateSevenFiling = greateSevenFiling;
   }

   public String getGreateSevenProportion() {
      return this.greateSevenProportion;
   }

   public void setGreateSevenProportion(String greateSevenProportion) {
      this.greateSevenProportion = greateSevenProportion;
   }

   public BigDecimal getAverageFiling() {
      return this.averageFiling;
   }

   public void setAverageFiling(BigDecimal averageFiling) {
      this.averageFiling = averageFiling;
   }

   public String getJjCount() {
      return this.jjCount;
   }

   public void setJjCount(String jjCount) {
      this.jjCount = jjCount;
   }

   public String getJjProportion() {
      return this.jjProportion;
   }

   public void setJjProportion(String jjProportion) {
      this.jjProportion = jjProportion;
   }

   public String getYjCount() {
      return this.yjCount;
   }

   public void setYjCount(String yjCount) {
      this.yjCount = yjCount;
   }

   public String getYjProportion() {
      return this.yjProportion;
   }

   public void setYjProportion(String yjProportion) {
      this.yjProportion = yjProportion;
   }

   public String getBjCount() {
      return this.bjCount;
   }

   public void setBjCount(String bjCount) {
      this.bjCount = bjCount;
   }

   public String getBjProportion() {
      return this.bjProportion;
   }

   public void setBjProportion(String bjProportion) {
      this.bjProportion = bjProportion;
   }

   public String getFxlCount() {
      return this.fxlCount;
   }

   public void setFxlCount(String fxlCount) {
      this.fxlCount = fxlCount;
   }

   public String getFxlProportion() {
      return this.fxlProportion;
   }

   public void setFxlProportion(String fxlProportion) {
      this.fxlProportion = fxlProportion;
   }

   public Integer getFilingCount() {
      return this.filingCount;
   }

   public void setFilingCount(Integer filingCount) {
      this.filingCount = filingCount;
   }

   public String getDeptScore() {
      return this.deptScore;
   }

   public void setDeptScore(String deptScore) {
      this.deptScore = deptScore;
   }

   public String getDeptGradeNo() {
      return this.deptGradeNo;
   }

   public void setDeptGradeNo(String deptGradeNo) {
      this.deptGradeNo = deptGradeNo;
   }

   public String getDeptGradeName() {
      return this.deptGradeName;
   }

   public void setDeptGradeName(String deptGradeName) {
      this.deptGradeName = deptGradeName;
   }

   public String getCheckScore() {
      return this.checkScore;
   }

   public void setCheckScore(String checkScore) {
      this.checkScore = checkScore;
   }

   public String getCheckGradeNo() {
      return this.checkGradeNo;
   }

   public void setCheckGradeNo(String checkGradeNo) {
      this.checkGradeNo = checkGradeNo;
   }

   public String getCheckGradeName() {
      return this.checkGradeName;
   }

   public void setCheckGradeName(String checkGradeName) {
      this.checkGradeName = checkGradeName;
   }

   public String getInDeptTimeS() {
      return this.inDeptTimeS;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public String getFirstFileTimeS() {
      return this.firstFileTimeS;
   }

   public void setFirstFileTimeS(String firstFileTimeS) {
      this.firstFileTimeS = firstFileTimeS;
   }

   public String getTermQcTimeS() {
      return this.termQcTimeS;
   }

   public void setTermQcTimeS(String termQcTimeS) {
      this.termQcTimeS = termQcTimeS;
   }

   public String getOperTimeS() {
      return this.operTimeS;
   }

   public void setOperTimeS(String operTimeS) {
      this.operTimeS = operTimeS;
   }

   public String getTermScoreRes() {
      return this.termScoreRes;
   }

   public void setTermScoreRes(String termScoreRes) {
      this.termScoreRes = termScoreRes;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
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

   public Date getTermQcTime() {
      return this.termQcTime;
   }

   public void setTermQcTime(Date termQcTime) {
      this.termQcTime = termQcTime;
   }

   public Date getOperTime() {
      return this.operTime;
   }

   public void setOperTime(Date operTime) {
      this.operTime = operTime;
   }

   public String getOperReason() {
      return this.operReason;
   }

   public void setOperReason(String operReason) {
      this.operReason = operReason;
   }

   public String getTermGradeNo() {
      return this.termGradeNo;
   }

   public void setTermGradeNo(String termGradeNo) {
      this.termGradeNo = termGradeNo;
   }

   public String getTermGradeName() {
      return this.termGradeName;
   }

   public void setTermGradeName(String termGradeName) {
      this.termGradeName = termGradeName;
   }

   public String getTermScore() {
      return this.termScore;
   }

   public void setTermScore(String termScore) {
      this.termScore = termScore;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }
}
