package com.emr.project.qc.domain;

import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.pat.domain.Visitinfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PatEvent extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "住院号"
   )
   private String inpNo;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "事件编码"
   )
   private String eventCode;
   @Excel(
      name = "事件名称"
   )
   private String eventName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "事件开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date beginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "事件结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date endTime;
   @Excel(
      name = "事件发生编号",
      readConverterExp = "对=应his中的医嘱编码等信息"
   )
   private String eventNo;
   @Excel(
      name = "事件前医师编码"
   )
   private String preDocCd;
   @Excel(
      name = "事件前医师姓名"
   )
   private String preDocName;
   @Excel(
      name = "事件后医师编码"
   )
   private String postDocCd;
   @Excel(
      name = "事件后医师姓名"
   )
   private String postDocName;
   @Excel(
      name = "事件来源"
   )
   private String eventSource;
   private String delFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "记录创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "最后修改时间(每次更新事件时更新此字段，然后根据此字段检索事件发生情况)",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;

   public PatEvent() {
   }

   public PatEvent(String orgCd, String patientId) {
      this.orgCd = orgCd;
      this.patientId = patientId;
   }

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

   public void setEventCode(String eventCode) {
      this.eventCode = eventCode;
   }

   public String getEventCode() {
      return this.eventCode;
   }

   public void setEventName(String eventName) {
      this.eventName = eventName;
   }

   public String getEventName() {
      return this.eventName;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEventNo(String eventNo) {
      this.eventNo = eventNo;
   }

   public String getEventNo() {
      return this.eventNo;
   }

   public void setPreDocCd(String preDocCd) {
      this.preDocCd = preDocCd;
   }

   public String getPreDocCd() {
      return this.preDocCd;
   }

   public void setPreDocName(String preDocName) {
      this.preDocName = preDocName;
   }

   public String getPreDocName() {
      return this.preDocName;
   }

   public void setPostDocCd(String postDocCd) {
      this.postDocCd = postDocCd;
   }

   public String getPostDocCd() {
      return this.postDocCd;
   }

   public void setPostDocName(String postDocName) {
      this.postDocName = postDocName;
   }

   public String getPostDocName() {
      return this.postDocName;
   }

   public void setEventSource(String eventSource) {
      this.eventSource = eventSource;
   }

   public String getEventSource() {
      return this.eventSource;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("inpNo", this.getInpNo()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("eventCode", this.getEventCode()).append("eventName", this.getEventName()).append("beginTime", this.getBeginTime()).append("endTime", this.getEndTime()).append("eventNo", this.getEventNo()).append("preDocCd", this.getPreDocCd()).append("preDocName", this.getPreDocName()).append("postDocCd", this.getPostDocCd()).append("postDocName", this.getPostDocName()).append("eventSource", this.getEventSource()).append("delFlag", this.getDelFlag()).append("creDate", this.getCreDate()).append("altDate", this.getAltDate()).toString();
   }

   public PatEvent(Visitinfo visitinfo, String eventCode, String eventName, Date beginTime, Date endTime) {
      this.id = SnowIdUtils.uniqueLong();
      this.orgCd = visitinfo.getOrgCd();
      this.inpNo = visitinfo.getInpNo();
      this.patientId = visitinfo.getPatientId();
      this.patientId = visitinfo.getPatientName();
      this.eventCode = eventCode;
      this.eventName = eventName;
      this.beginTime = beginTime;
      this.endTime = endTime;
      this.preDocCd = visitinfo.getResDocCd();
      this.preDocName = visitinfo.getResDocName();
      this.eventSource = "HIS";
      this.delFlag = "0";
   }

   public PatEvent(Map map, String eventCode, String eventName, Date beginTime, Date endTime) {
      this.id = SnowIdUtils.uniqueLong();
      this.orgCd = map.get("hospital_code") != null ? map.get("hospital_code").toString() : null;
      this.inpNo = map.get("admission_no") != null ? map.get("admission_no").toString() : null;
      this.patientId = map.get("patient_id") != null ? map.get("patient_id").toString() : null;
      this.patientName = map.get("name") != null ? map.get("name").toString() : null;
      this.eventCode = eventCode;
      this.eventName = eventName;
      this.beginTime = beginTime;
      this.endTime = endTime;
      this.preDocCd = map.get("visiting_staff_no") != null ? map.get("visiting_staff_no").toString() : null;
      this.preDocName = map.get("visiting_staff_name") != null ? map.get("visiting_staff_name").toString() : null;
      this.eventSource = "HIS";
      this.delFlag = "0";
   }

   public PatEvent(String orgCd, String inpNo, String patientId, String patientName, String preDocCd, String preDocName, String eventCode, String eventName, Date beginTime, Date endTime) {
      this.id = SnowIdUtils.uniqueLong();
      this.orgCd = orgCd;
      this.inpNo = inpNo;
      this.patientId = patientId;
      this.patientName = patientName;
      this.eventCode = eventCode;
      this.eventName = eventName;
      this.beginTime = beginTime;
      this.endTime = endTime;
      this.preDocCd = preDocCd;
      this.preDocName = preDocName;
      this.eventSource = "HIS";
      this.delFlag = "0";
   }
}
