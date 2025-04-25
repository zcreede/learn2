package com.emr.project.docOrder.domain.vo;

public class OrderSignTextVo {
   private String orderNo;
   private String chargeName;
   private String submitDoctorNo;
   private String submitTime;
   private String stopDoctorNo;
   private String stopTime;
   private String cancelDoctorNo;
   private String cancelTime;

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getSubmitDoctorNo() {
      return this.submitDoctorNo;
   }

   public void setSubmitDoctorNo(String submitDoctorNo) {
      this.submitDoctorNo = submitDoctorNo;
   }

   public String getSubmitTime() {
      return this.submitTime;
   }

   public void setSubmitTime(String submitTime) {
      this.submitTime = submitTime;
   }

   public String getStopDoctorNo() {
      return this.stopDoctorNo;
   }

   public void setStopDoctorNo(String stopDoctorNo) {
      this.stopDoctorNo = stopDoctorNo;
   }

   public String getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(String stopTime) {
      this.stopTime = stopTime;
   }

   public String getCancelDoctorNo() {
      return this.cancelDoctorNo;
   }

   public void setCancelDoctorNo(String cancelDoctorNo) {
      this.cancelDoctorNo = cancelDoctorNo;
   }

   public String getCancelTime() {
      return this.cancelTime;
   }

   public void setCancelTime(String cancelTime) {
      this.cancelTime = cancelTime;
   }
}
