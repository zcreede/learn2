package com.emr.project.qc.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EmrQcFlowStatisDetailExport {
   @Excel(
      name = "所属科室",
      sort = 1,
      height = (double)20.0F
   )
   private String deptName;
   @Excel(
      name = "责任医师",
      sort = 2,
      height = (double)20.0F
   )
   private String doctName;
   @Excel(
      name = "住院医师",
      sort = 3,
      height = (double)20.0F
   )
   private String resDocName;
   @Excel(
      name = "病历分类",
      sort = 4,
      height = (double)20.0F
   )
   private String emrClassName;
   @Excel(
      name = "病历类型",
      sort = 5,
      height = (double)20.0F
   )
   private String mrTypeName;
   @Excel(
      name = "患者姓名",
      sort = 6,
      height = (double)20.0F
   )
   private String patientName;
   @Excel(
      name = "患者住院号",
      sort = 7,
      height = (double)20.0F
   )
   private String inpNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "病历书写时限",
      sort = 8,
      height = (double)20.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date endTime;
   @Excel(
      name = "超时时长",
      sort = 9,
      height = (double)20.0F
   )
   private String remainingTime;
   @Excel(
      name = "书写状态",
      sort = 10,
      height = (double)20.0F
   )
   private String xAxisName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院时间",
      sort = 11,
      height = (double)20.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date entryDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院时间",
      sort = 12,
      height = (double)20.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date leaveHospitalDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "缺陷反馈日期",
      sort = 13,
      height = (double)20.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date recoDate;

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
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

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getRemainingTime() {
      return this.remainingTime;
   }

   public void setRemainingTime(String remainingTime) {
      this.remainingTime = remainingTime;
   }

   public String getxAxisName() {
      return this.xAxisName;
   }

   public void setxAxisName(String xAxisName) {
      this.xAxisName = xAxisName;
   }
}
