package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class OrderStopSignVo {
   List orderStopVoList;
   List orderList;
   private String patientId;
   private String encryptedInfo;
   private String signCertificate;
   private String signedText;
   private String sjc;
   private List orderPerformList;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date currTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date stopTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderCancelTime;
   private String orderCancelDesc;

   public Date getCurrTime() {
      return this.currTime;
   }

   public void setCurrTime(Date currTime) {
      this.currTime = currTime;
   }

   public List getOrderList() {
      return this.orderList;
   }

   public void setOrderList(List orderList) {
      this.orderList = orderList;
   }

   public List getOrderPerformList() {
      return this.orderPerformList;
   }

   public void setOrderPerformList(List orderPerformList) {
      this.orderPerformList = orderPerformList;
   }

   public List getOrderStopVoList() {
      return this.orderStopVoList;
   }

   public void setOrderStopVoList(List orderStopVoList) {
      this.orderStopVoList = orderStopVoList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
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

   public Date getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(Date stopTime) {
      this.stopTime = stopTime;
   }

   public Date getOrderCancelTime() {
      return this.orderCancelTime;
   }

   public void setOrderCancelTime(Date orderCancelTime) {
      this.orderCancelTime = orderCancelTime;
   }

   public String getOrderCancelDesc() {
      return this.orderCancelDesc;
   }

   public void setOrderCancelDesc(String orderCancelDesc) {
      this.orderCancelDesc = orderCancelDesc;
   }
}
