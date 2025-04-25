package com.emr.project.docOrder.domain.req;

public class SuspendOrderListReq {
   private String admissionNo;
   private String orderType;
   private String sign;
   private String performListStatus1;
   private String performListStatus2;
   private String performListStatus3;
   private String depCode;

   public String getDepCode() {
      return this.depCode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getPerformListStatus1() {
      return this.performListStatus1;
   }

   public void setPerformListStatus1(String performListStatus1) {
      this.performListStatus1 = performListStatus1;
   }

   public String getPerformListStatus2() {
      return this.performListStatus2;
   }

   public void setPerformListStatus2(String performListStatus2) {
      this.performListStatus2 = performListStatus2;
   }

   public String getPerformListStatus3() {
      return this.performListStatus3;
   }

   public void setPerformListStatus3(String performListStatus3) {
      this.performListStatus3 = performListStatus3;
   }
}
