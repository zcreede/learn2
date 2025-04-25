package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class OrderCommitVo {
   private List orderNoList;
   private List applyFormNoList;
   private List orderList;
   private String patientId;
   private String operatorDesc;
   private String encryptedInfo;
   private String signCertificate;
   private String signedText;
   private String sjc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date cancelTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date submitTime;
   private String babyAdmissionNo;
   private Boolean noSubmit;
   private List orderCpVaryList;
   private String operatingRoomFlag;

   public List getApplyFormNoList() {
      return this.applyFormNoList;
   }

   public void setApplyFormNoList(List applyFormNoList) {
      this.applyFormNoList = applyFormNoList;
   }

   public Boolean getNoSubmit() {
      return this.noSubmit;
   }

   public void setNoSubmit(Boolean noSubmit) {
      this.noSubmit = noSubmit;
   }

   public List getOrderList() {
      return this.orderList;
   }

   public void setOrderList(List orderList) {
      this.orderList = orderList;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public Date getSubmitTime() {
      return this.submitTime;
   }

   public void setSubmitTime(Date submitTime) {
      this.submitTime = submitTime;
   }

   public Date getCancelTime() {
      return this.cancelTime;
   }

   public void setCancelTime(Date cancelTime) {
      this.cancelTime = cancelTime;
   }

   public String getEncryptedInfo() {
      return this.encryptedInfo;
   }

   public void setEncryptedInfo(String encryptedInfo) {
      this.encryptedInfo = encryptedInfo;
   }

   public String getSignCertificate() {
      return this.signCertificate;
   }

   public void setSignCertificate(String signCertificate) {
      this.signCertificate = signCertificate;
   }

   public String getSignedText() {
      return this.signedText;
   }

   public void setSignedText(String signedText) {
      this.signedText = signedText;
   }

   public String getSjc() {
      return this.sjc;
   }

   public void setSjc(String sjc) {
      this.sjc = sjc;
   }

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getOperatorDesc() {
      return this.operatorDesc;
   }

   public void setOperatorDesc(String operatorDesc) {
      this.operatorDesc = operatorDesc;
   }

   public List getOrderCpVaryList() {
      return this.orderCpVaryList;
   }

   public void setOrderCpVaryList(List orderCpVaryList) {
      this.orderCpVaryList = orderCpVaryList;
   }

   public String getOperatingRoomFlag() {
      return this.operatingRoomFlag;
   }

   public void setOperatingRoomFlag(String operatingRoomFlag) {
      this.operatingRoomFlag = operatingRoomFlag;
   }
}
