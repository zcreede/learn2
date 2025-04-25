package com.emr.project.report.domain;

import java.math.BigDecimal;

public class FeeDayDataDetail {
   private String standardCode;
   private String standardName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private BigDecimal totalOwnRatio;
   private String medicalInsuranceCode;
   private String medicalInsuranceName;
   private String projectName;
   private String sumName;

   public String getSumName() {
      return this.sumName;
   }

   public void setSumName(String sumName) {
      this.sumName = sumName;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public String getMedicalInsuranceCode() {
      return this.medicalInsuranceCode;
   }

   public void setMedicalInsuranceCode(String medicalInsuranceCode) {
      this.medicalInsuranceCode = medicalInsuranceCode;
   }

   public String getMedicalInsuranceName() {
      return this.medicalInsuranceName;
   }

   public void setMedicalInsuranceName(String medicalInsuranceName) {
      this.medicalInsuranceName = medicalInsuranceName;
   }

   public BigDecimal getTotalOwnRatio() {
      return this.totalOwnRatio;
   }

   public void setTotalOwnRatio(BigDecimal totalOwnRatio) {
      this.totalOwnRatio = totalOwnRatio;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }
}
