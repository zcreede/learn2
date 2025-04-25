package com.emr.project.his.domain;

public class DrugCheck {
   private String drugCode;
   private String drugName;
   private String drugSpec;
   private String drugClassCode;
   private String drugClassName;
   private String applyFlag;
   private String drugGradeName;
   private String useEnabled;
   private String reason;
   private String drugLevelCd;

   public String getDrugLevelCd() {
      return this.drugLevelCd;
   }

   public void setDrugLevelCd(String drugLevelCd) {
      this.drugLevelCd = drugLevelCd;
   }

   public String getDrugCode() {
      return this.drugCode;
   }

   public void setDrugCode(String drugCode) {
      this.drugCode = drugCode;
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugSpec() {
      return this.drugSpec;
   }

   public void setDrugSpec(String drugSpec) {
      this.drugSpec = drugSpec;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getDrugClassName() {
      return this.drugClassName;
   }

   public void setDrugClassName(String drugClassName) {
      this.drugClassName = drugClassName;
   }

   public String getApplyFlag() {
      return this.applyFlag;
   }

   public void setApplyFlag(String applyFlag) {
      this.applyFlag = applyFlag;
   }

   public String getDrugGradeName() {
      return this.drugGradeName;
   }

   public void setDrugGradeName(String drugGradeName) {
      this.drugGradeName = drugGradeName;
   }

   public String getUseEnabled() {
      return this.useEnabled;
   }

   public void setUseEnabled(String useEnabled) {
      this.useEnabled = useEnabled;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
