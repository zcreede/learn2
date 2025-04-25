package com.emr.project.emr.domain;

import java.io.Serializable;

public class UnConsultationDTO implements Serializable {
   private String id;
   private String patientId;
   private String applyDocCd;
   private String applyDocName;
   private String invDocCd;
   private String invDocName;

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getApplyDocCd() {
      return this.applyDocCd;
   }

   public void setApplyDocCd(String applyDocCd) {
      this.applyDocCd = applyDocCd;
   }

   public String getApplyDocName() {
      return this.applyDocName;
   }

   public void setApplyDocName(String applyDocName) {
      this.applyDocName = applyDocName;
   }

   public String getInvDocCd() {
      return this.invDocCd;
   }

   public void setInvDocCd(String invDocCd) {
      this.invDocCd = invDocCd;
   }

   public String getInvDocName() {
      return this.invDocName;
   }

   public void setInvDocName(String invDocName) {
      this.invDocName = invDocName;
   }
}
