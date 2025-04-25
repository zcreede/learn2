package com.emr.project.qc.domain.vo;

public class RevokeToFileReq {
   private String patientId;
   private String revokeType;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getRevokeType() {
      return this.revokeType;
   }

   public void setRevokeType(String revokeType) {
      this.revokeType = revokeType;
   }
}
