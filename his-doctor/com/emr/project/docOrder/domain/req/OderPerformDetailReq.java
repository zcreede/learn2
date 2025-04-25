package com.emr.project.docOrder.domain.req;

public class OderPerformDetailReq {
   private String orderNo;
   private String orderType;
   private String herbalFlag;

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }
}
