package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderMessageVo {
   private String patientMainId;
   private String patientId;
   private String inpNo;
   private String patientName;
   private String admissionNo;
   private String caseNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date createDate;
   private String msgContent;
   private String msgType;
   private String msgStatus;
   private String busType;

   public String getBusType() {
      return this.busType;
   }

   public void setBusType(String busType) {
      this.busType = busType;
   }

   public String getMsgType() {
      return this.msgType;
   }

   public void setMsgType(String msgType) {
      this.msgType = msgType;
   }

   public String getMsgStatus() {
      return this.msgStatus;
   }

   public void setMsgStatus(String msgStatus) {
      this.msgStatus = msgStatus;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
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

   public Date getCreateDate() {
      return this.createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public String getMsgContent() {
      return this.msgContent;
   }

   public void setMsgContent(String msgContent) {
      this.msgContent = msgContent;
   }
}
