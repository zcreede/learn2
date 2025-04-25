package com.emr.project.qc.domain;

import com.emr.common.constant.CommonConstants;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrMessage extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "患者住院号"
   )
   private String inpNo;
   @Excel(
      name = "患者病房号"
   )
   private String roomNo;
   @Excel(
      name = "患者床号"
   )
   private String bedNo;
   @Excel(
      name = "患者所在科室编码"
   )
   private String deptCd;
   @Excel(
      name = "患者所在科室名称"
   )
   private String deptName;
   @Excel(
      name = "消息类型 1病历缺陷 2病历质控退回"
   )
   private Integer msgType;
   @Excel(
      name = "消息内容"
   )
   private String msgContent;
   @Excel(
      name = "消息状态 0 未处理 1已处理"
   )
   private Integer msgState;
   @Excel(
      name = "关联业务类型 1病历文件 2病案首页 3质控事件"
   )
   private Integer busType;
   @Excel(
      name = "关联业务唯一标识"
   )
   private String busId;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;
   private String doctCd;
   private String doctName;
   private String msgWarnType;
   private Long eventId;

   public Long getEventId() {
      return this.eventId;
   }

   public void setEventId(Long eventId) {
      this.eventId = eventId;
   }

   public EmrMessage() {
   }

   public EmrMessage(TdCaConsApplyVo tdCaConsApplyVo) {
      this.patientId = tdCaConsApplyVo.getPatientId();
      this.patientName = tdCaConsApplyVo.getPatientName();
      this.inpNo = tdCaConsApplyVo.getInpNo();
      this.bedNo = tdCaConsApplyVo.getBedNo();
      this.deptCd = tdCaConsApplyVo.getConsDeptCd();
      this.deptName = tdCaConsApplyVo.getConsDeptName();
      this.msgState = 0;
      this.msgType = CommonConstants.EMR_QC_MESSAGE.MSG_TYPE_3;
      this.busType = CommonConstants.EMR_QC_MESSAGE.BUS_TYPE_4;
      this.busId = tdCaConsApplyVo.getId().toString();
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
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

   public void setRoomNo(String roomNo) {
      this.roomNo = roomNo;
   }

   public String getRoomNo() {
      return this.roomNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
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

   public void setMsgType(Integer msgType) {
      this.msgType = msgType;
   }

   public Integer getMsgType() {
      return this.msgType;
   }

   public void setMsgContent(String msgContent) {
      this.msgContent = msgContent;
   }

   public String getMsgContent() {
      return this.msgContent;
   }

   public void setMsgState(Integer msgState) {
      this.msgState = msgState;
   }

   public Integer getMsgState() {
      return this.msgState;
   }

   public void setBusType(Integer busType) {
      this.busType = busType;
   }

   public Integer getBusType() {
      return this.busType;
   }

   public void setBusId(String busId) {
      this.busId = busId;
   }

   public String getBusId() {
      return this.busId;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String getMsgWarnType() {
      return this.msgWarnType;
   }

   public void setMsgWarnType(String msgWarnType) {
      this.msgWarnType = msgWarnType;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("inpNo", this.getInpNo()).append("roomNo", this.getRoomNo()).append("bedNo", this.getBedNo()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("msgType", this.getMsgType()).append("msgContent", this.getMsgContent()).append("msgState", this.getMsgState()).append("busType", this.getBusType()).append("busId", this.getBusId()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("doctCd", this.getDoctCd()).append("doctName", this.getDoctName()).toString();
   }
}
