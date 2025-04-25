package com.emr.project.operation.domain;

public class TakeDrugListKey {
   private String performListNo;
   private Integer performListSortNumber;
   private String orderSortNumber;
   private String orderGroupSortNumber;
   private String orderGroupNo;

   public TakeDrugListKey() {
   }

   public TakeDrugListKey(String performListNo, Integer performListSortNumber, String orderSortNumber, String orderGroupSortNumber, String orderGroupNo) {
      this.performListNo = performListNo;
      this.performListSortNumber = performListSortNumber;
      this.orderSortNumber = orderSortNumber;
      this.orderGroupSortNumber = orderGroupSortNumber;
      this.orderGroupNo = orderGroupNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo == null ? null : performListNo.trim();
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }
}
