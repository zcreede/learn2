package com.emr.project.dr.domain.vo;

public class DrAntiDrugVo {
   private String drugCode;
   private String drugName;
   private String drugSpec;
   private String drugDose;
   private String drugDoseUnit;
   private String drugLevel;
   private String inputstrphtc;
   private String sqlStr;

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
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

   public String getDrugDose() {
      return this.drugDose;
   }

   public void setDrugDose(String drugDose) {
      this.drugDose = drugDose;
   }

   public String getDrugDoseUnit() {
      return this.drugDoseUnit;
   }

   public void setDrugDoseUnit(String drugDoseUnit) {
      this.drugDoseUnit = drugDoseUnit;
   }

   public String getDrugLevel() {
      return this.drugLevel;
   }

   public void setDrugLevel(String drugLevel) {
      this.drugLevel = drugLevel;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }
}
