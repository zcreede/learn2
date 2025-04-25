package com.emr.project.docOrder.domain.resp;

public class LcljBaseInfo {
   private String stageCode;
   private String stageName;
   private String currDay;
   private String cpNo;
   private String cpName;
   private String subCpNo;
   private String subCpName;

   public String getStageCode() {
      return this.stageCode;
   }

   public void setStageCode(String stageCode) {
      this.stageCode = stageCode;
   }

   public String getStageName() {
      return this.stageName;
   }

   public void setStageName(String stageName) {
      this.stageName = stageName;
   }

   public String getCurrDay() {
      return this.currDay;
   }

   public void setCurrDay(String currDay) {
      this.currDay = currDay;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getSubCpNo() {
      return this.subCpNo;
   }

   public void setSubCpNo(String subCpNo) {
      this.subCpNo = subCpNo;
   }

   public String getSubCpName() {
      return this.subCpName;
   }

   public void setSubCpName(String subCpName) {
      this.subCpName = subCpName;
   }
}
