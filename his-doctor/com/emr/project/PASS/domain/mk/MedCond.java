package com.emr.project.PASS.domain.mk;

public class MedCond {
   private String Index;
   private String DiseaseCode;
   private String DiseaseName;
   private String RecipNo;
   private String starttime;
   private String endtime;

   public String getIndex() {
      return this.Index;
   }

   public void setIndex(String index) {
      this.Index = index;
   }

   public String getDiseaseCode() {
      return this.DiseaseCode;
   }

   public void setDiseaseCode(String diseaseCode) {
      this.DiseaseCode = diseaseCode;
   }

   public String getDiseaseName() {
      return this.DiseaseName;
   }

   public void setDiseaseName(String diseaseName) {
      this.DiseaseName = diseaseName;
   }

   public String getRecipNo() {
      return this.RecipNo;
   }

   public void setRecipNo(String recipNo) {
      this.RecipNo = recipNo;
   }

   public String getStarttime() {
      return this.starttime;
   }

   public void setStarttime(String starttime) {
      this.starttime = starttime;
   }

   public String getEndtime() {
      return this.endtime;
   }

   public void setEndtime(String endtime) {
      this.endtime = endtime;
   }
}
