package com.emr.project.CDSS.domain.xyt;

public class Diagnoses {
   private String diagnosisName;
   private String diagnosisCode;
   private String diagnosisType;
   private Boolean diagnosisMark;
   private String diagnosisDate;

   public String getDiagnosisName() {
      return this.diagnosisName;
   }

   public void setDiagnosisName(String diagnosisName) {
      this.diagnosisName = diagnosisName;
   }

   public String getDiagnosisCode() {
      return this.diagnosisCode;
   }

   public void setDiagnosisCode(String diagnosisCode) {
      this.diagnosisCode = diagnosisCode;
   }

   public String getDiagnosisType() {
      return this.diagnosisType;
   }

   public void setDiagnosisType(String diagnosisType) {
      this.diagnosisType = diagnosisType;
   }

   public Boolean getDiagnosisMark() {
      return this.diagnosisMark;
   }

   public void setDiagnosisMark(Boolean diagnosisMark) {
      this.diagnosisMark = diagnosisMark;
   }

   public String getDiagnosisDate() {
      return this.diagnosisDate;
   }

   public void setDiagnosisDate(String diagnosisDate) {
      this.diagnosisDate = diagnosisDate;
   }
}
