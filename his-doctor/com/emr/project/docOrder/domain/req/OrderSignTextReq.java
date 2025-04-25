package com.emr.project.docOrder.domain.req;

import java.util.List;

public class OrderSignTextReq {
   private String signType;
   private List orderNoList;

   public String getSignType() {
      return this.signType;
   }

   public void setSignType(String signType) {
      this.signType = signType;
   }

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }
}
