package com.emr.project.pat.domain.vo;

public class TestReportReq {
   private String[] patientId;
   private String type;

   public String[] getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String[] patientId) {
      this.patientId = patientId;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
