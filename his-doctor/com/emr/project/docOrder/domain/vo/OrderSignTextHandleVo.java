package com.emr.project.docOrder.domain.vo;

public class OrderSignTextHandleVo {
   private String admissionNo;
   private String orderNo;
   private String chargeName;
   private String submitDoctorNo;
   private String submitTime;
   private String auditDoctorNo;
   private String auditTime;
   private Integer orderGroupNo;
   private String orderSortNumber;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

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

   public String getAuditDoctorNo() {
      return this.auditDoctorNo;
   }

   public void setAuditDoctorNo(String auditDoctorNo) {
      this.auditDoctorNo = auditDoctorNo;
   }

   public String getAuditTime() {
      return this.auditTime;
   }

   public void setAuditTime(String auditTime) {
      this.auditTime = auditTime;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }
}
