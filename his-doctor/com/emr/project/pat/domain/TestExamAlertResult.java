package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestExamAlertResult extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "申请单编码"
   )
   private String appCd;
   @Excel(
      name = "结果id"
   )
   private String reportId;
   @Excel(
      name = "结果明细id"
   )
   private String reportItemId;
   @Excel(
      name = "上报危急值ID"
   )
   private String resultAlertId;
   @Excel(
      name = "02检查03检验分类"
   )
   private String classCode;
   @Excel(
      name = "处理医师编码"
   )
   private String handleDocNo;
   @Excel(
      name = "处理医师姓名"
   )
   private String handleDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date handleDocTime;
   @Excel(
      name = "处理措施"
   )
   private String handleDocContent;
   @Excel(
      name = "处理护士编码"
   )
   private String handleNurseNo;
   @Excel(
      name = "处理护士姓名"
   )
   private String handleNurseName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date handleNurseTime;
   @Excel(
      name = "处理措施"
   )
   private String handleNurseContent;
   @Excel(
      name = "是否超时(0未超时 1超时)"
   )
   private String overtimeFlag;
   @Excel(
      name = "关联病历id"
   )
   private Long mrFileId;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getAppCd() {
      return this.appCd;
   }

   public void setReportId(String reportId) {
      this.reportId = reportId;
   }

   public String getReportId() {
      return this.reportId;
   }

   public void setReportItemId(String reportItemId) {
      this.reportItemId = reportItemId;
   }

   public String getReportItemId() {
      return this.reportItemId;
   }

   public void setResultAlertId(String resultAlertId) {
      this.resultAlertId = resultAlertId;
   }

   public String getResultAlertId() {
      return this.resultAlertId;
   }

   public void setClassCode(String classCode) {
      this.classCode = classCode;
   }

   public String getClassCode() {
      return this.classCode;
   }

   public void setHandleDocNo(String handleDocNo) {
      this.handleDocNo = handleDocNo;
   }

   public String getHandleDocNo() {
      return this.handleDocNo;
   }

   public void setHandleDocName(String handleDocName) {
      this.handleDocName = handleDocName;
   }

   public String getHandleDocName() {
      return this.handleDocName;
   }

   public void setHandleDocTime(Date handleDocTime) {
      this.handleDocTime = handleDocTime;
   }

   public Date getHandleDocTime() {
      return this.handleDocTime;
   }

   public void setHandleDocContent(String handleDocContent) {
      this.handleDocContent = handleDocContent;
   }

   public String getHandleDocContent() {
      return this.handleDocContent;
   }

   public void setHandleNurseNo(String handleNurseNo) {
      this.handleNurseNo = handleNurseNo;
   }

   public String getHandleNurseNo() {
      return this.handleNurseNo;
   }

   public void setHandleNurseName(String handleNurseName) {
      this.handleNurseName = handleNurseName;
   }

   public String getHandleNurseName() {
      return this.handleNurseName;
   }

   public void setHandleNurseTime(Date handleNurseTime) {
      this.handleNurseTime = handleNurseTime;
   }

   public Date getHandleNurseTime() {
      return this.handleNurseTime;
   }

   public void setHandleNurseContent(String handleNurseContent) {
      this.handleNurseContent = handleNurseContent;
   }

   public String getHandleNurseContent() {
      return this.handleNurseContent;
   }

   public void setOvertimeFlag(String overtimeFlag) {
      this.overtimeFlag = overtimeFlag;
   }

   public String getOvertimeFlag() {
      return this.overtimeFlag;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("appCd", this.getAppCd()).append("reportId", this.getReportId()).append("reportItemId", this.getReportItemId()).append("resultAlertId", this.getResultAlertId()).append("classCode", this.getClassCode()).append("handleDocNo", this.getHandleDocNo()).append("handleDocName", this.getHandleDocName()).append("handleDocTime", this.getHandleDocTime()).append("handleDocContent", this.getHandleDocContent()).append("handleNurseNo", this.getHandleNurseNo()).append("handleNurseName", this.getHandleNurseName()).append("handleNurseTime", this.getHandleNurseTime()).append("handleNurseContent", this.getHandleNurseContent()).append("overtimeFlag", this.getOvertimeFlag()).append("mrFileId", this.getMrFileId()).toString();
   }
}
