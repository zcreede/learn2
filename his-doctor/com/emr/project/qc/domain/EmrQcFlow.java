package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcFlow extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String orgCd;
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Integer visitId;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "科室代码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "住院医师编码"
   )
   private String resDocCd;
   @Excel(
      name = "住院医师姓名"
   )
   private String resDocName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "入科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date inDeptTime;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "出科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date outDeptTime;
   @Excel(
      name = "抽查医师编码"
   )
   private String checkDocCd;
   @Excel(
      name = "抽查医师名称"
   )
   private String checkDocName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "抽查时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date checkTime;
   @Excel(
      name = "抽查评分"
   )
   private Double checkScore;
   @Excel(
      name = "抽查评分等级编码"
   )
   private String checkGradeNo;
   @Excel(
      name = "抽查评分等级名称"
   )
   private String checkGradeName;
   @Excel(
      name = "抽查质控状态   0：未质控；1：已质控"
   )
   private String checkState;
   @Excel(
      name = "病历状态",
      readConverterExp = "0=0:未提交质控;10提交科室质控；11科室退回；12申请归档；13终末质控退回；14提交病案室；15归档退回；16病历归档；17取消归档 "
   )
   private String mrState;
   @Excel(
      name = "病历提交人编号"
   )
   private String mrSubCd;
   @Excel(
      name = "病历提交人姓名"
   )
   private String mrSubName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "病历提交科室时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date mrSubTime;
   @Excel(
      name = "科室质控人编码"
   )
   private String deptQcCd;
   @Excel(
      name = "科室质控人姓名"
   )
   private String deptQcName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "科室质控时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date deptQcTime;
   @Excel(
      name = "科室评分质控 0：未质控；1：已质控"
   )
   private String deptQcState;
   @Excel(
      name = "科室评分"
   )
   private Double deptScore;
   @Excel(
      name = "科室评分等级编码"
   )
   private String deptGradeNo;
   @Excel(
      name = "科室评分等级名称"
   )
   private String deptGradeName;
   @Excel(
      name = "申请归档科室编码"
   )
   private String applyDeptCd;
   @Excel(
      name = "申请归档科室名称"
   )
   private String applyDeptName;
   @Excel(
      name = "申请归档人编号"
   )
   private String applyDocCd;
   @Excel(
      name = "申请归档人姓名"
   )
   private String applyDocName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "申请归档时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date applyFileTime;
   @Excel(
      name = "终末质控医生编码"
   )
   private String termQcCd;
   @Excel(
      name = "终末质控医生姓名"
   )
   private String termQcName;
   @Excel(
      name = "终末评分质控    0：未质控；1：已质控"
   )
   private String termQcState;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "终末质控时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date termQcTime;
   @Excel(
      name = "终末质控评分"
   )
   private Double termScore;
   @Excel(
      name = "终末质控评分等级编码"
   )
   private String termGradeNo;
   @Excel(
      name = "终末质控评分等级名称"
   )
   private String termGradeName;
   @Excel(
      name = "归档人编码"
   )
   private String fileDocCd;
   @Excel(
      name = "归档人姓名"
   )
   private String fileDocName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "归档时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date fileTime;
   @Excel(
      name = "抽查单号"
   )
   private String checkQcSn;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date creDate;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "首次归档时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date firstFileTime;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "首页质控时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date hpQcDate;
   private String shelfNum;
   private Date termQcBackTime;

   public Date getHpQcDate() {
      return this.hpQcDate;
   }

   public void setHpQcDate(Date hpQcDate) {
      this.hpQcDate = hpQcDate;
   }

   public Date getTermQcBackTime() {
      return this.termQcBackTime;
   }

   public void setTermQcBackTime(Date termQcBackTime) {
      this.termQcBackTime = termQcBackTime;
   }

   public String getShelfNum() {
      return this.shelfNum;
   }

   public void setShelfNum(String shelfNum) {
      this.shelfNum = shelfNum;
   }

   public Date getFirstFileTime() {
      return this.firstFileTime;
   }

   public void setFirstFileTime(Date firstFileTime) {
      this.firstFileTime = firstFileTime;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setInDeptTime(Date inDeptTime) {
      this.inDeptTime = inDeptTime;
   }

   public Date getInDeptTime() {
      return this.inDeptTime;
   }

   public void setOutDeptTime(Date outDeptTime) {
      this.outDeptTime = outDeptTime;
   }

   public Date getOutDeptTime() {
      return this.outDeptTime;
   }

   public void setCheckDocCd(String checkDocCd) {
      this.checkDocCd = checkDocCd;
   }

   public String getCheckDocCd() {
      return this.checkDocCd;
   }

   public void setCheckDocName(String checkDocName) {
      this.checkDocName = checkDocName;
   }

   public String getCheckDocName() {
      return this.checkDocName;
   }

   public void setCheckTime(Date checkTime) {
      this.checkTime = checkTime;
   }

   public Date getCheckTime() {
      return this.checkTime;
   }

   public void setCheckScore(Double checkScore) {
      this.checkScore = checkScore;
   }

   public Double getCheckScore() {
      return this.checkScore;
   }

   public void setCheckGradeNo(String checkGradeNo) {
      this.checkGradeNo = checkGradeNo;
   }

   public String getCheckGradeNo() {
      return this.checkGradeNo;
   }

   public void setCheckGradeName(String checkGradeName) {
      this.checkGradeName = checkGradeName;
   }

   public String getCheckGradeName() {
      return this.checkGradeName;
   }

   public void setCheckState(String checkState) {
      this.checkState = checkState;
   }

   public String getCheckState() {
      return this.checkState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrSubCd(String mrSubCd) {
      this.mrSubCd = mrSubCd;
   }

   public String getMrSubCd() {
      return this.mrSubCd;
   }

   public void setMrSubName(String mrSubName) {
      this.mrSubName = mrSubName;
   }

   public String getMrSubName() {
      return this.mrSubName;
   }

   public void setMrSubTime(Date mrSubTime) {
      this.mrSubTime = mrSubTime;
   }

   public Date getMrSubTime() {
      return this.mrSubTime;
   }

   public void setDeptQcCd(String deptQcCd) {
      this.deptQcCd = deptQcCd;
   }

   public String getDeptQcCd() {
      return this.deptQcCd;
   }

   public void setDeptQcName(String deptQcName) {
      this.deptQcName = deptQcName;
   }

   public String getDeptQcName() {
      return this.deptQcName;
   }

   public void setDeptQcTime(Date deptQcTime) {
      this.deptQcTime = deptQcTime;
   }

   public Date getDeptQcTime() {
      return this.deptQcTime;
   }

   public void setDeptQcState(String deptQcState) {
      this.deptQcState = deptQcState;
   }

   public String getDeptQcState() {
      return this.deptQcState;
   }

   public void setDeptScore(Double deptScore) {
      this.deptScore = deptScore;
   }

   public Double getDeptScore() {
      return this.deptScore;
   }

   public void setDeptGradeNo(String deptGradeNo) {
      this.deptGradeNo = deptGradeNo;
   }

   public String getDeptGradeNo() {
      return this.deptGradeNo;
   }

   public void setDeptGradeName(String deptGradeName) {
      this.deptGradeName = deptGradeName;
   }

   public String getDeptGradeName() {
      return this.deptGradeName;
   }

   public void setApplyDeptCd(String applyDeptCd) {
      this.applyDeptCd = applyDeptCd;
   }

   public String getApplyDeptCd() {
      return this.applyDeptCd;
   }

   public void setApplyDeptName(String applyDeptName) {
      this.applyDeptName = applyDeptName;
   }

   public String getApplyDeptName() {
      return this.applyDeptName;
   }

   public void setApplyDocCd(String applyDocCd) {
      this.applyDocCd = applyDocCd;
   }

   public String getApplyDocCd() {
      return this.applyDocCd;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyFileTime(Date applyFileTime) {
      this.applyFileTime = applyFileTime;
   }

   public Date getApplyFileTime() {
      return this.applyFileTime;
   }

   public void setTermQcCd(String termQcCd) {
      this.termQcCd = termQcCd;
   }

   public String getTermQcCd() {
      return this.termQcCd;
   }

   public String getTermQcName() {
      return this.termQcName;
   }

   public void setTermQcName(String termQcName) {
      this.termQcName = termQcName;
   }

   public void setTermQcState(String termQcState) {
      this.termQcState = termQcState;
   }

   public String getTermQcState() {
      return this.termQcState;
   }

   public void setTermQcTime(Date termQcTime) {
      this.termQcTime = termQcTime;
   }

   public Date getTermQcTime() {
      return this.termQcTime;
   }

   public void setTermScore(Double termScore) {
      this.termScore = termScore;
   }

   public Double getTermScore() {
      return this.termScore;
   }

   public void setTermGradeNo(String termGradeNo) {
      this.termGradeNo = termGradeNo;
   }

   public String getTermGradeNo() {
      return this.termGradeNo;
   }

   public void setTermGradeName(String termGradeName) {
      this.termGradeName = termGradeName;
   }

   public String getTermGradeName() {
      return this.termGradeName;
   }

   public void setFileDocCd(String fileDocCd) {
      this.fileDocCd = fileDocCd;
   }

   public String getFileDocCd() {
      return this.fileDocCd;
   }

   public void setFileDocName(String fileDocName) {
      this.fileDocName = fileDocName;
   }

   public String getFileDocName() {
      return this.fileDocName;
   }

   public void setFileTime(Date fileTime) {
      this.fileTime = fileTime;
   }

   public Date getFileTime() {
      return this.fileTime;
   }

   public void setCheckQcSn(String checkQcSn) {
      this.checkQcSn = checkQcSn;
   }

   public String getCheckQcSn() {
      return this.checkQcSn;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("inpNo", this.getInpNo()).append("visitId", this.getVisitId()).append("recordNo", this.getRecordNo()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("resDocCd", this.getResDocCd()).append("resDocName", this.getResDocName()).append("inDeptTime", this.getInDeptTime()).append("outDeptTime", this.getOutDeptTime()).append("checkDocCd", this.getCheckDocCd()).append("checkDocName", this.getCheckDocName()).append("checkTime", this.getCheckTime()).append("checkScore", this.getCheckScore()).append("checkGradeNo", this.getCheckGradeNo()).append("checkGradeName", this.getCheckGradeName()).append("checkState", this.getCheckState()).append("mrState", this.getMrState()).append("mrSubCd", this.getMrSubCd()).append("mrSubName", this.getMrSubName()).append("mrSubTime", this.getMrSubTime()).append("deptQcCd", this.getDeptQcCd()).append("deptQcName", this.getDeptQcName()).append("deptQcTime", this.getDeptQcTime()).append("deptQcState", this.getDeptQcState()).append("deptScore", this.getDeptScore()).append("deptGradeNo", this.getDeptGradeNo()).append("deptGradeName", this.getDeptGradeName()).append("applyDeptCd", this.getApplyDeptCd()).append("applyDeptName", this.getApplyDeptName()).append("applyDocCd", this.getApplyDocCd()).append("applyDocName", this.getApplyDocName()).append("applyFileTime", this.getApplyFileTime()).append("termQcCd", this.getTermQcCd()).append("termoQcName", this.getTermQcName()).append("termQcState", this.getTermQcState()).append("termQcTime", this.getTermQcTime()).append("termScore", this.getTermScore()).append("termGradeNo", this.getTermGradeNo()).append("termGradeName", this.getTermGradeName()).append("fileDocCd", this.getFileDocCd()).append("fileDocName", this.getFileDocName()).append("fileTime", this.getFileTime()).append("checkQcSn", this.getCheckQcSn()).toString();
   }
}
