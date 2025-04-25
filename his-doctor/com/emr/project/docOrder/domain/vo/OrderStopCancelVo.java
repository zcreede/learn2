package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderStopCancelVo {
   private List orderStopVoList;
   private String patientId;
   private String stopCancelFlag;
   private List orderNoList;
   private List orderList;

   public List getOrderList() {
      return this.orderList;
   }

   public void setOrderList(List orderList) {
      this.orderList = orderList;
   }

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
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

   public String getStopCancelFlag() {
      return this.stopCancelFlag;
   }

   public void setStopCancelFlag(String stopCancelFlag) {
      this.stopCancelFlag = stopCancelFlag;
   }
}
