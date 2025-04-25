package com.emr.project.webservice.domain.req;

public class VteInfoReq {
   private String hospitalCode;
   private String hisPatientId;
   private String assessmentOpportunityKey;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHisPatientId() {
      return this.hisPatientId;
   }

   public void setHisPatientId(String hisPatientId) {
      this.hisPatientId = hisPatientId;
   }

   public String getAssessmentOpportunityKey() {
      return this.assessmentOpportunityKey;
   }

   public void setAssessmentOpportunityKey(String assessmentOpportunityKey) {
      this.assessmentOpportunityKey = assessmentOpportunityKey;
   }
}
