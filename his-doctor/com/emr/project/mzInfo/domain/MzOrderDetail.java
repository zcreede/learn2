package com.emr.project.mzInfo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class MzOrderDetail {
   private String prescriptionNum;
   private String orderGroupNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String orderStatus;
   private String cpName;
   private String standard;
   private String orderActualUsage;
   private String orderFreq;
   private String orderUsageWay;
   private BigDecimal orderUsageDays;
   private String doctorInstructions;
   private String detailPerformName;
   private BigDecimal price;
   private BigDecimal orderDose;
   private String unit;
   private String orderClassCode;
   private String mzh;
   private String visitNo;

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getMzh() {
      return this.mzh;
   }

   public void setMzh(String mzh) {
      this.mzh = mzh;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getPrescriptionNum() {
      return this.prescriptionNum;
   }

   public void setPrescriptionNum(String prescriptionNum) {
      this.prescriptionNum = prescriptionNum;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(String orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public BigDecimal getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(BigDecimal orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getDetailPerformName() {
      return this.detailPerformName;
   }

   public void setDetailPerformName(String detailPerformName) {
      this.detailPerformName = detailPerformName;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }
}
