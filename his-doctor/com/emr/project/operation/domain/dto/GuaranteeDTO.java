package com.emr.project.operation.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class GuaranteeDTO implements Serializable {
   private String orderNo;
   private String admissionNo;
   private String wardNo;
   private String operationDate;
   private String operator;
   private String approvalStartTime;
   private String stopTime;
   private String validMark;
   private Date approvalEndTime;
   private String approver;
   private String effectiveDate;
   private String stopPeople;
   private String stopOperator;
   private String remarkCode;
   private String Remark1;
   private String Remark2;
   private String amountGuaranteed;
   private String caseNo;
   private Integer hospitalizedCount;

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator;
   }

   public String getValidMark() {
      return this.validMark;
   }

   public void setValidMark(String validMark) {
      this.validMark = validMark;
   }

   public String getApprover() {
      return this.approver;
   }

   public void setApprover(String approver) {
      this.approver = approver;
   }

   public String getStopPeople() {
      return this.stopPeople;
   }

   public void setStopPeople(String stopPeople) {
      this.stopPeople = stopPeople;
   }

   public String getStopOperator() {
      return this.stopOperator;
   }

   public void setStopOperator(String stopOperator) {
      this.stopOperator = stopOperator;
   }

   public String getRemarkCode() {
      return this.remarkCode;
   }

   public void setRemarkCode(String remarkCode) {
      this.remarkCode = remarkCode;
   }

   public String getRemark1() {
      return this.Remark1;
   }

   public void setRemark1(String remark1) {
      this.Remark1 = remark1;
   }

   public String getRemark2() {
      return this.Remark2;
   }

   public void setRemark2(String remark2) {
      this.Remark2 = remark2;
   }

   public String getAmountGuaranteed() {
      return this.amountGuaranteed;
   }

   public void setAmountGuaranteed(String amountGuaranteed) {
      this.amountGuaranteed = amountGuaranteed;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getOperationDate() {
      return this.operationDate;
   }

   public void setOperationDate(String operationDate) {
      this.operationDate = operationDate;
   }

   public String getApprovalStartTime() {
      return this.approvalStartTime;
   }

   public void setApprovalStartTime(String approvalStartTime) {
      this.approvalStartTime = approvalStartTime;
   }

   public String getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(String stopTime) {
      this.stopTime = stopTime;
   }

   public Date getApprovalEndTime() {
      return this.approvalEndTime;
   }

   public void setApprovalEndTime(Date approvalEndTime) {
      this.approvalEndTime = approvalEndTime;
   }

   public String getEffectiveDate() {
      return this.effectiveDate;
   }

   public void setEffectiveDate(String effectiveDate) {
      this.effectiveDate = effectiveDate;
   }

   public String toString() {
      return "Guarantee [orderNo=" + this.orderNo + ", admissionNo=" + this.admissionNo + ", wardNo=" + this.wardNo + ", operationDate=" + this.operationDate + ", operator=" + this.operator + ", approvalStartTime=" + this.approvalStartTime + ", stopTime=" + this.stopTime + ", validMark=" + this.validMark + ", approvalEndTime=" + this.approvalEndTime + ", approver=" + this.approver + ", effectiveDate=" + this.effectiveDate + ", stopPeople=" + this.stopPeople + ", stopOperator=" + this.stopOperator + ", remarkCode=" + this.remarkCode + ", Remark1=" + this.Remark1 + ", amountGuaranteed=" + this.amountGuaranteed + ", caseNo=" + this.caseNo + ", hospitalizedCount=" + this.hospitalizedCount + "]";
   }
}
