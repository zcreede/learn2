package com.emr.project.CDSS.domain.xyt;

import java.util.Date;

public class TreatmentOrder {
   private String treatmentType;
   private String treatmentName;
   private String treatmentCode;
   private Date applicationTime;

   public String getTreatmentType() {
      return this.treatmentType;
   }

   public void setTreatmentType(String treatmentType) {
      this.treatmentType = treatmentType;
   }

   public String getTreatmentName() {
      return this.treatmentName;
   }

   public void setTreatmentName(String treatmentName) {
      this.treatmentName = treatmentName;
   }

   public String getTreatmentCode() {
      return this.treatmentCode;
   }

   public void setTreatmentCode(String treatmentCode) {
      this.treatmentCode = treatmentCode;
   }

   public Date getApplicationTime() {
      return this.applicationTime;
   }

   public void setApplicationTime(Date applicationTime) {
      this.applicationTime = applicationTime;
   }
}
