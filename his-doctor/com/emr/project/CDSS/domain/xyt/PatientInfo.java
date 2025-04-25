package com.emr.project.CDSS.domain.xyt;

public class PatientInfo {
   private String patientId;
   private String visitId;
   private String sexCode;
   private String birthday;
   private String isPregnancy;
   private String isLactation;
   private String dept;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getVisitId() {
      return this.visitId;
   }

   public void setVisitId(String visitId) {
      this.visitId = visitId;
   }

   public String getSexCode() {
      return this.sexCode;
   }

   public void setSexCode(String sexCode) {
      this.sexCode = sexCode;
   }

   public String getBirthday() {
      return this.birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getIsPregnancy() {
      return this.isPregnancy;
   }

   public void setIsPregnancy(String isPregnancy) {
      this.isPregnancy = isPregnancy;
   }

   public String getIsLactation() {
      return this.isLactation;
   }

   public void setIsLactation(String isLactation) {
      this.isLactation = isLactation;
   }

   public String getDept() {
      return this.dept;
   }

   public void setDept(String dept) {
      this.dept = dept;
   }
}
