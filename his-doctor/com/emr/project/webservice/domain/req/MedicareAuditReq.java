package com.emr.project.webservice.domain.req;

public class MedicareAuditReq {
   private MedicareAuditPatientDtosReq patient_dtos;
   private String[] rule_ids;
   private String trig_scen;

   public MedicareAuditPatientDtosReq getPatient_dtos() {
      return this.patient_dtos;
   }

   public void setPatient_dtos(MedicareAuditPatientDtosReq patient_dtos) {
      this.patient_dtos = patient_dtos;
   }

   public String[] getRule_ids() {
      return this.rule_ids;
   }

   public void setRule_ids(String[] rule_ids) {
      this.rule_ids = rule_ids;
   }

   public String getTrig_scen() {
      return this.trig_scen;
   }

   public void setTrig_scen(String trig_scen) {
      this.trig_scen = trig_scen;
   }
}
