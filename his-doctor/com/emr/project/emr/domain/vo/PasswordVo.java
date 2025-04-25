package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.SealupRecord;

public class PasswordVo {
   private SealupRecord sealupRecord;
   private String hospital;
   private String patient;
   private String witness;

   public String getHospital() {
      return this.hospital;
   }

   public void setHospital(String hospital) {
      this.hospital = hospital;
   }

   public String getPatient() {
      return this.patient;
   }

   public void setPatient(String patient) {
      this.patient = patient;
   }

   public SealupRecord getSealupRecord() {
      return this.sealupRecord;
   }

   public void setSealupRecord(SealupRecord sealupRecord) {
      this.sealupRecord = sealupRecord;
   }

   public String getWitness() {
      return this.witness;
   }

   public void setWitness(String witness) {
      this.witness = witness;
   }
}
