package com.emr.project.CDSS.domain.xyt;

public class MedRecordInfo {
   private String medRecordId;
   private String medRecordType;
   private String medRecordTxt;
   private String medRecordItems;

   public String getMedRecordId() {
      return this.medRecordId;
   }

   public void setMedRecordId(String medRecordId) {
      this.medRecordId = medRecordId;
   }

   public String getMedRecordType() {
      return this.medRecordType;
   }

   public void setMedRecordType(String medRecordType) {
      this.medRecordType = medRecordType;
   }

   public String getMedRecordTxt() {
      return this.medRecordTxt;
   }

   public void setMedRecordTxt(String medRecordTxt) {
      this.medRecordTxt = medRecordTxt;
   }

   public String getMedRecordItems() {
      return this.medRecordItems;
   }

   public void setMedRecordItems(String medRecordItems) {
      this.medRecordItems = medRecordItems;
   }
}
