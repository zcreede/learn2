package com.emr.project.docOrder.domain.resp;

import java.math.BigDecimal;

public class OrderExecutionDetail {
   private String chargeName;
   private String standard;
   private String actualUsage;
   private String sigName;
   private String freqName;
   private String orderDose;
   private String doctorInstructions;
   private String drippingSpeed;
   private String patientSelf;
   private String deptName;
   private String antimicrobialUse;
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
      return this.usageUnit != null ? this.orderActualUsage.toString() + this.usageUnit : this.orderActualUsage.toString();
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

   public String getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(String orderDose) {
      this.orderDose = orderDose;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getPatientSelf() {
      return this.patientSelf;
   }

   public void setPatientSelf(String patientSelf) {
      this.patientSelf = patientSelf;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getAntimicrobialUse() {
      return this.antimicrobialUse;
   }

   public void setAntimicrobialUse(String antimicrobialUse) {
      this.antimicrobialUse = antimicrobialUse;
   }
}
