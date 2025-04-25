package com.emr.project.docOrder.domain;

public class InpatientOrderPerformFirstBottle {
   private Long id;
   private Integer performListSortNumber;
   private String orderNo;
   private String admissionNo;
   private String wardNo;
   private Integer hospitalizedCount;
   private Integer jjbz;
   private String orderSortNumber;
   private Long firstFlag;

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Integer getJjbz() {
      return this.jjbz;
   }

   public void setJjbz(Integer jjbz) {
      this.jjbz = jjbz;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo == null ? null : orderNo.trim();
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber == null ? null : orderSortNumber.trim();
   }

   public Long getFirstFlag() {
      return this.firstFlag;
   }

   public void setFirstFlag(Long firstFlag) {
      this.firstFlag = firstFlag;
   }
}
