package com.emr.project.pat.domain.vo;

public class TestReportKbVo {
   private String code;
   private String referenceRange;
   private String influenceFactor;
   private String clinicalSignificance;
   private String errorMessage;

   public String getReferenceRange() {
      return this.referenceRange;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public void setReferenceRange(String referenceRange) {
      this.referenceRange = referenceRange;
   }

   public String getInfluenceFactor() {
      return this.influenceFactor;
   }

   public void setInfluenceFactor(String influenceFactor) {
      this.influenceFactor = influenceFactor;
   }

   public String getClinicalSignificance() {
      return this.clinicalSignificance;
   }

   public void setClinicalSignificance(String clinicalSignificance) {
      this.clinicalSignificance = clinicalSignificance;
   }

   public String getErrorMessage() {
      return this.errorMessage;
   }

   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }
}
