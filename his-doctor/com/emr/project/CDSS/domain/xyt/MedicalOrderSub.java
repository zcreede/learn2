package com.emr.project.CDSS.domain.xyt;

import java.util.Date;

public class MedicalOrderSub {
   private String checkId;
   private String repeatIndicatorCode;
   private String drugName;
   private String drugNo;
   private String dosage;
   private String dosageUnits;
   private String dosageUnitsCode;
   private String administrationCode;
   private String frequency;
   private String orderStatus;
   private Date orderTime;

   public String getCheckId() {
      return this.checkId;
   }

   public void setCheckId(String checkId) {
      this.checkId = checkId;
   }

   public String getRepeatIndicatorCode() {
      return this.repeatIndicatorCode;
   }

   public void setRepeatIndicatorCode(String repeatIndicatorCode) {
      this.repeatIndicatorCode = repeatIndicatorCode;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugNo() {
      return this.drugNo;
   }

   public void setDrugNo(String drugNo) {
      this.drugNo = drugNo;
   }

   public String getDosage() {
      return this.dosage;
   }

   public void setDosage(String dosage) {
      this.dosage = dosage;
   }

   public String getDosageUnits() {
      return this.dosageUnits;
   }

   public void setDosageUnits(String dosageUnits) {
      this.dosageUnits = dosageUnits;
   }

   public String getDosageUnitsCode() {
      return this.dosageUnitsCode;
   }

   public void setDosageUnitsCode(String dosageUnitsCode) {
      this.dosageUnitsCode = dosageUnitsCode;
   }

   public String getAdministrationCode() {
      return this.administrationCode;
   }

   public void setAdministrationCode(String administrationCode) {
      this.administrationCode = administrationCode;
   }

   public String getFrequency() {
      return this.frequency;
   }

   public void setFrequency(String frequency) {
      this.frequency = frequency;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public Date getOrderTime() {
      return this.orderTime;
   }

   public void setOrderTime(Date orderTime) {
      this.orderTime = orderTime;
   }
}
