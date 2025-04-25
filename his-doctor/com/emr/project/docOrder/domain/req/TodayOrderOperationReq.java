package com.emr.project.docOrder.domain.req;

public class TodayOrderOperationReq {
   private String admissionNo;
   private String startTime;
   private String endTime;
   private String orderType;
   private Integer orderStatus;
   private String orderClassCode;
   private Integer deptType;
   private String deptCode;
   private String performListNo;
   private Integer flag;

   public Integer getFlag() {
      return this.flag;
   }

   public void setFlag(Integer flag) {
      this.flag = flag;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public Integer getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(Integer orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public Integer getDeptType() {
      return this.deptType;
   }

   public void setDeptType(Integer deptType) {
      this.deptType = deptType;
   }
}
