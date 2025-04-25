package com.emr.project.docOrder.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderExecutionItemDetail {
   private String name;
   private String orderNo;
   private String orderType;
   private String orderStatus;
   private String havaDrugFlag;
   private String orderGroupNo;
   private String orderDoctorDepName;
   private String orderDoctorName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operationTime;
   private String babyAdmissionNo;
   private String performDepName;
   private String firstPerformNum;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date performTime;
   private String orderExecuteDocName;
   private String orderAuditDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderaAuditTime;
   private String orderStopDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStopTime;
   private String operatorName;
   private String orderClassCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operationStopTime;

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getHavaDrugFlag() {
      return this.havaDrugFlag;
   }

   public void setHavaDrugFlag(String havaDrugFlag) {
      this.havaDrugFlag = havaDrugFlag;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderDoctorDepName() {
      return this.orderDoctorDepName;
   }

   public void setOrderDoctorDepName(String orderDoctorDepName) {
      this.orderDoctorDepName = orderDoctorDepName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getFirstPerformNum() {
      return this.firstPerformNum;
   }

   public void setFirstPerformNum(String firstPerformNum) {
      this.firstPerformNum = firstPerformNum;
   }

   public Date getPerformTime() {
      return this.performTime;
   }

   public void setPerformTime(Date performTime) {
      this.performTime = performTime;
   }

   public String getOrderExecuteDocName() {
      return this.orderExecuteDocName;
   }

   public void setOrderExecuteDocName(String orderExecuteDocName) {
      this.orderExecuteDocName = orderExecuteDocName;
   }

   public String getOrderAuditDocName() {
      return this.orderAuditDocName;
   }

   public void setOrderAuditDocName(String orderAuditDocName) {
      this.orderAuditDocName = orderAuditDocName;
   }

   public Date getOrderaAuditTime() {
      return this.orderaAuditTime;
   }

   public void setOrderaAuditTime(Date orderaAuditTime) {
      this.orderaAuditTime = orderaAuditTime;
   }

   public String getOrderStopDocName() {
      return this.orderStopDocName;
   }

   public void setOrderStopDocName(String orderStopDocName) {
      this.orderStopDocName = orderStopDocName;
   }

   public Date getOrderStopTime() {
      return this.orderStopTime;
   }

   public void setOrderStopTime(Date orderStopTime) {
      this.orderStopTime = orderStopTime;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public Date getOperationStopTime() {
      return this.operationStopTime;
   }

   public void setOperationStopTime(Date operationStopTime) {
      this.operationStopTime = operationStopTime;
   }
}
