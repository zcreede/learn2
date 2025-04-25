package com.emr.project.webservice.domain.resp;

public class WebServiceVteResp {
   private String code;
   private String message;
   private Object data;
   private String hospitalCode;
   private String hisPatientId;
   private String patientName;
   private String assessmentOpportunityKey;
   private String assessmentOpportunityValue;
   private WebServiceVteResult vteRiskAssessment;
   private WebServiceVteResult bloodRiskAssessment;
   private WebServiceVteResult wellsRiskAssessment;
   private WebServiceVteResult vtePreventiveMeasure;

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

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getAssessmentOpportunityKey() {
      return this.assessmentOpportunityKey;
   }

   public void setAssessmentOpportunityKey(String assessmentOpportunityKey) {
      this.assessmentOpportunityKey = assessmentOpportunityKey;
   }

   public String getAssessmentOpportunityValue() {
      return this.assessmentOpportunityValue;
   }

   public void setAssessmentOpportunityValue(String assessmentOpportunityValue) {
      this.assessmentOpportunityValue = assessmentOpportunityValue;
   }

   public WebServiceVteResult getVteRiskAssessment() {
      return this.vteRiskAssessment;
   }

   public void setVteRiskAssessment(WebServiceVteResult vteRiskAssessment) {
      this.vteRiskAssessment = vteRiskAssessment;
   }

   public WebServiceVteResult getBloodRiskAssessment() {
      return this.bloodRiskAssessment;
   }

   public void setBloodRiskAssessment(WebServiceVteResult bloodRiskAssessment) {
      this.bloodRiskAssessment = bloodRiskAssessment;
   }

   public WebServiceVteResult getWellsRiskAssessment() {
      return this.wellsRiskAssessment;
   }

   public void setWellsRiskAssessment(WebServiceVteResult wellsRiskAssessment) {
      this.wellsRiskAssessment = wellsRiskAssessment;
   }

   public WebServiceVteResult getVtePreventiveMeasure() {
      return this.vtePreventiveMeasure;
   }

   public void setVtePreventiveMeasure(WebServiceVteResult vtePreventiveMeasure) {
      this.vtePreventiveMeasure = vtePreventiveMeasure;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Object getData() {
      return this.data;
   }

   public void setData(Object data) {
      this.data = data;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }
}
