package com.emr.project.docOrder.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class OrderExecutionPerformDetail {
   private String performListNo;
   private String performListStatus;
   private String performListStatusStr;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date performTime;
   private String orderDoctorName;
   private String orderDoctorNo;
   private String chargeName;
   private String standard;
   private String actualUsage;
   private String sigName;
   private String freqName;
   private BigDecimal orderActualUsage;
   private String usageUnit;

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getPerformListStatusStr() {
      return this.performListStatusStr;
   }

   public void setPerformListStatusStr(String performListStatusStr) {
      this.performListStatusStr = performListStatusStr;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListStatus() {
      return this.performListStatus;
   }

   public void setPerformListStatus(String performListStatus) {
      this.performListStatus = performListStatus;
   }

   public Date getPerformTime() {
      return this.performTime;
   }

   public void setPerformTime(Date performTime) {
      this.performTime = performTime;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderDoctorNo() {
      return this.orderDoctorNo;
   }

   public void setOrderDoctorNo(String orderDoctorNo) {
      this.orderDoctorNo = orderDoctorNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getActualUsage() {
      return this.actualUsage;
   }

   public void setActualUsage(String actualUsage) {
      this.actualUsage = actualUsage;
   }

   public String getSigName() {
      return this.sigName;
   }

   public void setSigName(String sigName) {
      this.sigName = sigName;
   }

   public String getFreqName() {
      return this.freqName;
   }

   public void setFreqName(String freqName) {
      this.freqName = freqName;
   }
}
