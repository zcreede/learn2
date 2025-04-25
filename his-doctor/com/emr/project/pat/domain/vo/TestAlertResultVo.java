package com.emr.project.pat.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.pat.domain.TestAlertReport;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class TestAlertResultVo extends TestAlertReport {
   private String resultId;
   @Excel(
      name = "是否超时",
      readConverterExp = "0=否,1=是",
      sort = 19
   )
   private String overtimeFlag;
   private Long mrFileId;
   @Excel(
      name = "病历",
      readConverterExp = "0=未写,1=已写",
      sort = 20
   )
   private String mrFileFlag;
   private String handleDocNo;
   @Excel(
      name = "处理医师",
      sort = 11
   )
   private String handleDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 12
   )
   private Date handleDocTime;
   @Excel(
      name = "处理措施",
      align = Excel.Align.LEFT,
      sort = 13
   )
   private String handleDocContent;
   private String handleNurseNo;
   @Excel(
      name = "接收护士",
      sort = 14
   )
   private String handleNurseName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "护士接收时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm",
      sort = 15
   )
   private Date handleNurseTime;
   @Excel(
      name = "护士处理措施",
      align = Excel.Align.LEFT,
      sort = 16
   )
   private String handleNurseContent;
   @Excel(
      name = "科室",
      sort = 1
   )
   private String reqDeptName;
   private String contentStr;
   private String visitingStaffName;
   private String caseNo;
   private String patientMainId;
   private Long mainFileId;
   @Excel(
      name = "类型",
      readConverterExp = "1=门诊,2=急诊,3=住院,4=体检",
      sort = 21
   )
   private String patType;

   public String getPatType() {
      return this.patType;
   }

   public void setPatType(String patType) {
      this.patType = patType;
   }

   public Long getMainFileId() {
      return this.mainFileId;
   }

   public void setMainFileId(Long mainFileId) {
      this.mainFileId = mainFileId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getContentStr() {
      return this.contentStr;
   }

   public void setContentStr(String contentStr) {
      this.contentStr = contentStr;
   }

   public String getReqDeptName() {
      return this.reqDeptName;
   }

   public void setReqDeptName(String reqDeptName) {
      this.reqDeptName = reqDeptName;
   }

   public String getHandleDocNo() {
      return this.handleDocNo;
   }

   public void setHandleDocNo(String handleDocNo) {
      this.handleDocNo = handleDocNo;
   }

   public String getHandleDocName() {
      return this.handleDocName;
   }

   public void setHandleDocName(String handleDocName) {
      this.handleDocName = handleDocName;
   }

   public Date getHandleDocTime() {
      return this.handleDocTime;
   }

   public void setHandleDocTime(Date handleDocTime) {
      this.handleDocTime = handleDocTime;
   }

   public String getHandleDocContent() {
      return this.handleDocContent;
   }

   public void setHandleDocContent(String handleDocContent) {
      this.handleDocContent = handleDocContent;
   }

   public String getHandleNurseNo() {
      return this.handleNurseNo;
   }

   public void setHandleNurseNo(String handleNurseNo) {
      this.handleNurseNo = handleNurseNo;
   }

   public String getHandleNurseName() {
      return this.handleNurseName;
   }

   public void setHandleNurseName(String handleNurseName) {
      this.handleNurseName = handleNurseName;
   }

   public Date getHandleNurseTime() {
      return this.handleNurseTime;
   }

   public void setHandleNurseTime(Date handleNurseTime) {
      this.handleNurseTime = handleNurseTime;
   }

   public String getHandleNurseContent() {
      return this.handleNurseContent;
   }

   public void setHandleNurseContent(String handleNurseContent) {
      this.handleNurseContent = handleNurseContent;
   }

   public String getResultId() {
      return this.resultId;
   }

   public void setResultId(String resultId) {
      this.resultId = resultId;
   }

   public String getOvertimeFlag() {
      return this.overtimeFlag;
   }

   public void setOvertimeFlag(String overtimeFlag) {
      this.overtimeFlag = overtimeFlag;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getMrFileFlag() {
      return this.mrFileFlag;
   }

   public void setMrFileFlag(String mrFileFlag) {
      this.mrFileFlag = mrFileFlag;
   }
}
