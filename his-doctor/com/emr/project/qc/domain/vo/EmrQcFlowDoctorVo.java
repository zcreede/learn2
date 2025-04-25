package com.emr.project.qc.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EmrQcFlowDoctorVo extends BaseEntity {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date beginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date endTime;
   @Excel(
      name = "科室"
   )
   private String inDeptName;
   @Excel(
      name = "责任医师"
   )
   private String xAxisName;
   @Excel(
      name = "缺陷总数"
   )
   private Integer valueTotal;
   @Excel(
      name = "已修改缺陷数"
   )
   private Integer flow1;
   @Excel(
      name = "有误缺陷数"
   )
   private Integer flow2;
   @Excel(
      name = "未修改缺陷数"
   )
   private Integer flow3;
   private String flowRatio1;
   private String flowRatio2;
   private Integer flow4;
   private String xAxisID;
   private String qcSection;
   private String deptCd;
   private Double flowRatio;
   private Double addRatio;
   private String patientName;
   private String inDeptCd;
   private String mrType;
   private String qcState;
   private String doctCd;
   private String doctName;
   private String deptName;
   private Long dbEleId;
   private String[] qcStateList;
   private String resDocCd;
   private String resDocName;
   private String inpNo;
   private String remainingTime;
   private String emrClassName;
   private String mrTypeName;
   private String emrClassCode;
   private Double avgScore;
   private String termAvg;
   private String deptAvg;
   private String checkAvg;
   private String checkJ;
   private String checkY;
   private String checkB;
   private String deptJ;
   private String deptY;
   private String deptB;
   private String termJ;
   private String termY;
   private String termB;
   private String checkScore;
   private String checkDocName;
   private String deptScore;
   private String deptQcName;
   private String termScore;
   private String termQcName;
   private String gradeNo;
   private String scoreDoc;
   private Long repId;
   private String firstCode;
   private String firsName;
   private Date filingDate;
   private Double total;

   public Double getTotal() {
      return this.total;
   }

   public void setTotal(Double total) {
      this.total = total;
   }

   public Date getFilingDate() {
      return this.filingDate;
   }

   public void setFilingDate(Date filingDate) {
      this.filingDate = filingDate;
   }

   public String getFirstCode() {
      return this.firstCode;
   }

   public void setFirstCode(String firstCode) {
      this.firstCode = firstCode;
   }

   public String getFirsName() {
      return this.firsName;
   }

   public void setFirsName(String firsName) {
      this.firsName = firsName;
   }

   public String getFlowRatio1() {
      return this.flowRatio1;
   }

   public void setFlowRatio1(String flowRatio1) {
      this.flowRatio1 = flowRatio1;
   }

   public String getFlowRatio2() {
      return this.flowRatio2;
   }

   public void setFlowRatio2(String flowRatio2) {
      this.flowRatio2 = flowRatio2;
   }

   public Integer getFlow4() {
      return this.flow4;
   }

   public void setFlow4(Integer flow4) {
      this.flow4 = flow4;
   }

   public String getGradeNo() {
      return this.gradeNo;
   }

   public void setGradeNo(String gradeNo) {
      this.gradeNo = gradeNo;
   }

   public String getScoreDoc() {
      return this.scoreDoc;
   }

   public void setScoreDoc(String scoreDoc) {
      this.scoreDoc = scoreDoc;
   }

   public String getCheckScore() {
      return this.checkScore;
   }

   public void setCheckScore(String checkScore) {
      this.checkScore = checkScore;
   }

   public String getCheckDocName() {
      return this.checkDocName;
   }

   public void setCheckDocName(String checkDocName) {
      this.checkDocName = checkDocName;
   }

   public String getDeptScore() {
      return this.deptScore;
   }

   public void setDeptScore(String deptScore) {
      this.deptScore = deptScore;
   }

   public String getDeptQcName() {
      return this.deptQcName;
   }

   public void setDeptQcName(String deptQcName) {
      this.deptQcName = deptQcName;
   }

   public String getTermScore() {
      return this.termScore;
   }

   public void setTermScore(String termScore) {
      this.termScore = termScore;
   }

   public String getTermQcName() {
      return this.termQcName;
   }

   public void setTermQcName(String termQcName) {
      this.termQcName = termQcName;
   }

   public String getCheckJ() {
      return this.checkJ;
   }

   public void setCheckJ(String checkJ) {
      this.checkJ = checkJ;
   }

   public String getCheckY() {
      return this.checkY;
   }

   public void setCheckY(String checkY) {
      this.checkY = checkY;
   }

   public String getCheckB() {
      return this.checkB;
   }

   public void setCheckB(String checkB) {
      this.checkB = checkB;
   }

   public String getDeptJ() {
      return this.deptJ;
   }

   public void setDeptJ(String deptJ) {
      this.deptJ = deptJ;
   }

   public String getDeptY() {
      return this.deptY;
   }

   public void setDeptY(String deptY) {
      this.deptY = deptY;
   }

   public String getDeptB() {
      return this.deptB;
   }

   public void setDeptB(String deptB) {
      this.deptB = deptB;
   }

   public String getTermJ() {
      return this.termJ;
   }

   public void setTermJ(String termJ) {
      this.termJ = termJ;
   }

   public String getTermY() {
      return this.termY;
   }

   public void setTermY(String termY) {
      this.termY = termY;
   }

   public String getTermB() {
      return this.termB;
   }

   public void setTermB(String termB) {
      this.termB = termB;
   }

   public String getTermAvg() {
      return this.termAvg;
   }

   public void setTermAvg(String termAvg) {
      this.termAvg = termAvg;
   }

   public String getDeptAvg() {
      return this.deptAvg;
   }

   public void setDeptAvg(String deptAvg) {
      this.deptAvg = deptAvg;
   }

   public String getCheckAvg() {
      return this.checkAvg;
   }

   public void setCheckAvg(String checkAvg) {
      this.checkAvg = checkAvg;
   }

   public Double getAvgScore() {
      return this.avgScore;
   }

   public void setAvgScore(Double avgScore) {
      this.avgScore = avgScore;
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

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getRemainingTime() {
      return this.remainingTime;
   }

   public void setRemainingTime(String remainingTime) {
      this.remainingTime = remainingTime;
   }

   public String getEmrClassName() {
      return this.emrClassName;
   }

   public void setEmrClassName(String emrClassName) {
      this.emrClassName = emrClassName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getEmrClassCode() {
      return this.emrClassCode;
   }

   public void setEmrClassCode(String emrClassCode) {
      this.emrClassCode = emrClassCode;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getInDeptName() {
      return this.inDeptName;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public String getInDeptCd() {
      return this.inDeptCd;
   }

   public void setInDeptCd(String inDeptCd) {
      this.inDeptCd = inDeptCd;
   }

   public String[] getQcStateList() {
      return this.qcStateList;
   }

   public void setQcStateList(String[] qcStateList) {
      this.qcStateList = qcStateList;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getQcState() {
      return this.qcState;
   }

   public void setQcState(String qcState) {
      this.qcState = qcState;
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public Long getDbEleId() {
      return this.dbEleId;
   }

   public void setDbEleId(Long dbEleId) {
      this.dbEleId = dbEleId;
   }

   public Double getFlowRatio() {
      return this.flowRatio;
   }

   public void setFlowRatio(Double flowRatio) {
      this.flowRatio = flowRatio;
   }

   public Double getAddRatio() {
      return this.addRatio;
   }

   public void setAddRatio(Double addRatio) {
      this.addRatio = addRatio;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getxAxisID() {
      return this.xAxisID;
   }

   public void setxAxisID(String xAxisID) {
      this.xAxisID = xAxisID;
   }

   public String getxAxisName() {
      return this.xAxisName;
   }

   public void setxAxisName(String xAxisName) {
      this.xAxisName = xAxisName;
   }

   public Integer getFlow1() {
      return this.flow1;
   }

   public void setFlow1(Integer flow1) {
      this.flow1 = flow1;
   }

   public Integer getFlow2() {
      return this.flow2;
   }

   public void setFlow2(Integer flow2) {
      this.flow2 = flow2;
   }

   public Integer getFlow3() {
      return this.flow3;
   }

   public void setFlow3(Integer flow3) {
      this.flow3 = flow3;
   }

   public Long getRepId() {
      return this.repId;
   }

   public void setRepId(Long repId) {
      this.repId = repId;
   }

   public Integer getValueTotal() {
      return this.valueTotal;
   }

   public void setValueTotal(Integer valueTotal) {
      this.valueTotal = valueTotal;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }
}
