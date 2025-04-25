package com.emr.project.docOrder.domain;

public class OrderDetailDTO extends TdPaOrderDetail {
   private String performDepCode;
   private String orderItemTypd;
   private Integer orderTotalItem;
   private String useLimit;
   private String zxbz;

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getOrderItemTypd() {
      return this.orderItemTypd;
   }

   public void setOrderItemTypd(String orderItemTypd) {
      this.orderItemTypd = orderItemTypd;
   }

   public Integer getOrderTotalItem() {
      return this.orderTotalItem;
   }

   public void setOrderTotalItem(Integer orderTotalItem) {
      this.orderTotalItem = orderTotalItem;
   }

   public String getUseLimit() {
      return this.useLimit;
   }

   public void setUseLimit(String useLimit) {
      this.useLimit = useLimit;
   }

   public String getZxbz() {
      return this.zxbz;
   }

   public void setZxbz(String zxbz) {
      this.zxbz = zxbz;
   }
}
