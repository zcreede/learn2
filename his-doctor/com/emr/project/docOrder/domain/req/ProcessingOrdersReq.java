package com.emr.project.docOrder.domain.req;

import java.util.List;

public class ProcessingOrdersReq {
   private String admissionNo;
   private String performListNo;
   private List performListSortNumberList;
   private String orderNo;
   private String orderSortNumber;
   private String orderClassCode;
   private String orderGroupNo;
   private Integer hospitalizedCount;
   private String message;

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public List getPerformListSortNumberList() {
      return this.performListSortNumberList;
   }

   public void setPerformListSortNumberList(List performListSortNumberList) {
      this.performListSortNumberList = performListSortNumberList;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }
}
