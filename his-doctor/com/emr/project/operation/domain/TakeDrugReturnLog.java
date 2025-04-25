package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TakeDrugReturnLog {
   private Long id;
   private String serialNumber;
   private String serialNumberXh;
   private Integer operateType;
   private Date operateTime;
   private String operator;
   private String performListNo;
   private String performListSortNumber;
   private BigDecimal dose;
   private BigDecimal returnDose;
   private Integer returnType;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber == null ? null : serialNumber.trim();
   }

   public String getSerialNumberXh() {
      return this.serialNumberXh;
   }

   public void setSerialNumberXh(String serialNumberXh) {
      this.serialNumberXh = serialNumberXh;
   }

   public Integer getOperateType() {
      return this.operateType;
   }

   public void setOperateType(Integer operateType) {
      this.operateType = operateType;
   }

   public Date getOperateTime() {
      return this.operateTime;
   }

   public void setOperateTime(Date operateTime) {
      this.operateTime = operateTime;
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator == null ? null : operator.trim();
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getReturnDose() {
      return this.returnDose;
   }

   public void setReturnDose(BigDecimal returnDose) {
      this.returnDose = returnDose;
   }

   public Integer getReturnType() {
      return this.returnType;
   }

   public void setReturnType(Integer returnType) {
      this.returnType = returnType;
   }
}
