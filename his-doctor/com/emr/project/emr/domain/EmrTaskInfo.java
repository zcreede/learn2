package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrTaskInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private List ids;
   @Excel(
      name = "住院号"
   )
   private String inpNo;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "患者所在科室编号"
   )
   private String deptCd;
   @Excel(
      name = "患者所在科室"
   )
   private String deptName;
   @Excel(
      name = "任务类型"
   )
   private String taskType;
   @Excel(
      name = "任务类型名称"
   )
   private String taskTypeName;
   @Excel(
      name = "任务发起人"
   )
   private String taskAppDoc;
   @Excel(
      name = "任务发起人姓名"
   )
   private String taskAppDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "任务生成时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date beginTime;
   @Excel(
      name = "任务时限"
   )
   private Integer limitTime;
   @Excel(
      name = "时限单位"
   )
   private String limitTimeUnit;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "任务截止时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date endTime;
   @Excel(
      name = "任务关联业务单据ID"
   )
   private String busId;
   @Excel(
      name = "任务关联业务单据名称"
   )
   private String busName;
   @Excel(
      name = "任务描述"
   )
   private String mark;
   @Excel(
      name = "责任医师编码"
   )
   private String docCd;
   @Excel(
      name = "责任医师姓名"
   )
   private String docName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "完成时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date finishTime;
   @Excel(
      name = "任务完成用时",
      readConverterExp = "包=括单位"
   )
   private String useTime;
   @Excel(
      name = "任务是否完成超时 0 否 1 是"
   )
   private Integer overtimeFlag;
   @Excel(
      name = "处理状态",
      readConverterExp = "0=：未处理；1：已处理"
   )
   private String treatFlag;
   @Excel(
      name = "任务业务环节"
   )
   private String busSection;
   @Excel(
      name = "任务业务环节名称"
   )
   private String busSectionName;
   @Excel(
      name = "病历id"
   )
   private Long mrFileId;
   private String eventNo;
   private String dutyDeptCd;
   private String dutyDeptName;

   public List getIds() {
      return this.ids;
   }

   public void setIds(List ids) {
      this.ids = ids;
   }

   public String getDutyDeptCd() {
      return this.dutyDeptCd;
   }

   public void setDutyDeptCd(String dutyDeptCd) {
      this.dutyDeptCd = dutyDeptCd;
   }

   public String getDutyDeptName() {
      return this.dutyDeptName;
   }

   public void setDutyDeptName(String dutyDeptName) {
      this.dutyDeptName = dutyDeptName;
   }

   public String getEventNo() {
      return this.eventNo;
   }

   public void setEventNo(String eventNo) {
      this.eventNo = eventNo;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
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

   public void setTaskType(String taskType) {
      this.taskType = taskType;
   }

   public String getTaskType() {
      return this.taskType;
   }

   public void setTaskTypeName(String taskTypeName) {
      this.taskTypeName = taskTypeName;
   }

   public String getTaskTypeName() {
      return this.taskTypeName;
   }

   public void setTaskAppDoc(String taskAppDoc) {
      this.taskAppDoc = taskAppDoc;
   }

   public String getTaskAppDoc() {
      return this.taskAppDoc;
   }

   public void setTaskAppDocName(String taskAppDocName) {
      this.taskAppDocName = taskAppDocName;
   }

   public String getTaskAppDocName() {
      return this.taskAppDocName;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setLimitTime(Integer limitTime) {
      this.limitTime = limitTime;
   }

   public Integer getLimitTime() {
      return this.limitTime;
   }

   public void setLimitTimeUnit(String limitTimeUnit) {
      this.limitTimeUnit = limitTimeUnit;
   }

   public String getLimitTimeUnit() {
      return this.limitTimeUnit;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setBusId(String busId) {
      this.busId = busId;
   }

   public String getBusId() {
      return this.busId;
   }

   public void setBusName(String busName) {
      this.busName = busName;
   }

   public String getBusName() {
      return this.busName;
   }

   public void setMark(String mark) {
      this.mark = mark;
   }

   public String getMark() {
      return this.mark;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setFinishTime(Date finishTime) {
      this.finishTime = finishTime;
   }

   public Date getFinishTime() {
      return this.finishTime;
   }

   public void setUseTime(String useTime) {
      this.useTime = useTime;
   }

   public String getUseTime() {
      return this.useTime;
   }

   public void setOvertimeFlag(Integer overtimeFlag) {
      this.overtimeFlag = overtimeFlag;
   }

   public Integer getOvertimeFlag() {
      return this.overtimeFlag;
   }

   public void setTreatFlag(String treatFlag) {
      this.treatFlag = treatFlag;
   }

   public String getTreatFlag() {
      return this.treatFlag;
   }

   public void setBusSection(String busSection) {
      this.busSection = busSection;
   }

   public String getBusSection() {
      return this.busSection;
   }

   public void setBusSectionName(String busSectionName) {
      this.busSectionName = busSectionName;
   }

   public String getBusSectionName() {
      return this.busSectionName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("inpNo", this.getInpNo()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("taskType", this.getTaskType()).append("taskTypeName", this.getTaskTypeName()).append("taskAppDoc", this.getTaskAppDoc()).append("taskAppDocName", this.getTaskAppDocName()).append("beginTime", this.getBeginTime()).append("limitTime", this.getLimitTime()).append("limitTimeUnit", this.getLimitTimeUnit()).append("endTime", this.getEndTime()).append("busId", this.getBusId()).append("busName", this.getBusName()).append("mark", this.getMark()).append("docCd", this.getDocCd()).append("docName", this.getDocName()).append("finishTime", this.getFinishTime()).append("useTime", this.getUseTime()).append("overtimeFlag", this.getOvertimeFlag()).append("treatFlag", this.getTreatFlag()).append("busSection", this.getBusSection()).append("busSectionName", this.getBusSectionName()).append("eventNo", this.getEventNo()).toString();
   }
}
