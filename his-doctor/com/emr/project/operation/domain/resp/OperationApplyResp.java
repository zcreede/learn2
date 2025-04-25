package com.emr.project.operation.domain.resp;

import java.util.Date;

public class OperationApplyResp {
   private String patientName;
   private String caseNo;
   private String admissionNo;
   private String operationCode;
   private String operationName;
   private String orderNo;
   private String orderType;
   private String orderSortNumber;
   private String orderGroupSortNumber;
   private String orderGroupNo;
   private String orderClassCode;
   private Date planOperTime;

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public Date getPlanOperTime() {
      return this.planOperTime;
   }

   public void setPlanOperTime(Date planOperTime) {
      this.planOperTime = planOperTime;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOperationCode() {
      return this.operationCode;
   }

   public void setOperationCode(String operationCode) {
      this.operationCode = operationCode;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

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

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }
}
