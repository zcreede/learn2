package com.emr.project.docOrder.domain.resp;

import java.util.List;

public class OrderSignTextResp {
   private String admissionNo;
   private String signedText;
   private String currTime;
   private List orderNoList;

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getSignedText() {
      return this.signedText;
   }

   public void setSignedText(String signedText) {
      this.signedText = signedText;
   }

   public String getCurrTime() {
      return this.currTime;
   }

   public void setCurrTime(String currTime) {
      this.currTime = currTime;
   }
}
