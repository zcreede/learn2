package com.emr.project.docOrder.domain.vo;

public class OrderListSearchVo {
   private String patientId;
   private String babyAdmissionNo;
   private Integer pplorderGroupNo;
   private Integer pageNum;
   private Integer pageSize;
   private String orderType;
   private String herbalFlag;
   private String deptCode;

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

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public Integer getPplorderGroupNo() {
      return this.pplorderGroupNo;
   }

   public void setPplorderGroupNo(Integer pplorderGroupNo) {
      this.pplorderGroupNo = pplorderGroupNo;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }
}
