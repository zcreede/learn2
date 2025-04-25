package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderMessage extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "消息内容"
   )
   private String msgContent;
   @Excel(
      name = "消息接收科室"
   )
   private String receiveDeptCode;
   @Excel(
      name = "患者所在科室"
   )
   private String patDeptCode;
   @Excel(
      name = "患者住院号"
   )
   private String admissionNo;
   @Excel(
      name = "患者病案号"
   )
   private String caseNo;
   @Excel(
      name = "患者病案号"
   )
   private Integer hospitalizedCount;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "消息状态 0 未处理 1已处理"
   )
   private String msgStatus;
   @Excel(
      name = "消息类别 1 医嘱消息"
   )
   private String msgType;
   @Excel(
      name = "医嘱类型"
   )
   private String orderType;
   @Excel(
      name = "医嘱类型名称"
   )
   private String orderTypeName;
   @Excel(
      name = "医嘱类别"
   )
   private String orderClassCode;
   @Excel(
      name = "医嘱类别名称"
   )
   private String orderClassCodeName;
   @Excel(
      name = "医嘱状态"
   )
   private String orderStatus;
   @Excel(
      name = "医嘱状态名称"
   )
   private String orderStatusName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date createDate;
   @Excel(
      name = "创建人"
   )
   private String createOper;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "更新时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date updateDate;
   @Excel(
      name = "更新人"
   )
   private String updateOper;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setMsgContent(String msgContent) {
      this.msgContent = msgContent;
   }

   public String getMsgContent() {
      return this.msgContent;
   }

   public void setReceiveDeptCode(String receiveDeptCode) {
      this.receiveDeptCode = receiveDeptCode;
   }

   public String getReceiveDeptCode() {
      return this.receiveDeptCode;
   }

   public void setPatDeptCode(String patDeptCode) {
      this.patDeptCode = patDeptCode;
   }

   public String getPatDeptCode() {
      return this.patDeptCode;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setMsgStatus(String msgStatus) {
      this.msgStatus = msgStatus;
   }

   public String getMsgStatus() {
      return this.msgStatus;
   }

   public void setMsgType(String msgType) {
      this.msgType = msgType;
   }

   public String getMsgType() {
      return this.msgType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderTypeName(String orderTypeName) {
      this.orderTypeName = orderTypeName;
   }

   public String getOrderTypeName() {
      return this.orderTypeName;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCodeName(String orderClassCodeName) {
      this.orderClassCodeName = orderClassCodeName;
   }

   public String getOrderClassCodeName() {
      return this.orderClassCodeName;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatusName(String orderStatusName) {
      this.orderStatusName = orderStatusName;
   }

   public String getOrderStatusName() {
      return this.orderStatusName;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateOper(String createOper) {
      this.createOper = createOper;
   }

   public String getCreateOper() {
      return this.createOper;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

   public Date getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateOper(String updateOper) {
      this.updateOper = updateOper;
   }

   public String getUpdateOper() {
      return this.updateOper;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orderNo", this.getOrderNo()).append("msgContent", this.getMsgContent()).append("receiveDeptCode", this.getReceiveDeptCode()).append("patDeptCode", this.getPatDeptCode()).append("admissionNo", this.getAdmissionNo()).append("patientName", this.getPatientName()).append("msgStatus", this.getMsgStatus()).append("msgType", this.getMsgType()).append("orderType", this.getOrderType()).append("orderTypeName", this.getOrderTypeName()).append("orderClassCode", this.getOrderClassCode()).append("orderClassCodeName", this.getOrderClassCodeName()).append("orderStatus", this.getOrderStatus()).append("orderStatusName", this.getOrderStatusName()).append("createDate", this.getCreateDate()).append("createOper", this.getCreateOper()).append("updateDate", this.getUpdateDate()).append("updateOper", this.getUpdateOper()).toString();
   }
}
