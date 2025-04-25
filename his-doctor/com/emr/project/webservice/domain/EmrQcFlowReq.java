package com.emr.project.webservice.domain;

public class EmrQcFlowReq {
   private String patientId;
   private String type;
   private String operatorName;
   private String operatorCode;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }
}
