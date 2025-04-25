package com.emr.project.webservice.domain.req;

public class MedicareAuditPatientDtosReq {
   private String patn_id;
   private String patn_name;
   private String gend;
   private String brdy;
   private String poolarea;
   private String curr_mdtrt_id;
   private MedicareAuditEncounterDtosReq fsi_encounter_dtos;
   private MedicareAuditHisDataDtosReq fsi_his_data_dto;

   public String getPatn_id() {
      return this.patn_id;
   }

   public void setPatn_id(String patn_id) {
      this.patn_id = patn_id;
   }

   public String getPatn_name() {
      return this.patn_name;
   }

   public void setPatn_name(String patn_name) {
      this.patn_name = patn_name;
   }

   public String getGend() {
      return this.gend;
   }

   public void setGend(String gend) {
      this.gend = gend;
   }

   public String getBrdy() {
      return this.brdy;
   }

   public void setBrdy(String brdy) {
      this.brdy = brdy;
   }

   public String getPoolarea() {
      return this.poolarea;
   }

   public void setPoolarea(String poolarea) {
      this.poolarea = poolarea;
   }

   public String getCurr_mdtrt_id() {
      return this.curr_mdtrt_id;
   }

   public void setCurr_mdtrt_id(String curr_mdtrt_id) {
      this.curr_mdtrt_id = curr_mdtrt_id;
   }

   public MedicareAuditEncounterDtosReq getFsi_encounter_dtos() {
      return this.fsi_encounter_dtos;
   }

   public void setFsi_encounter_dtos(MedicareAuditEncounterDtosReq fsi_encounter_dtos) {
      this.fsi_encounter_dtos = fsi_encounter_dtos;
   }

   public MedicareAuditHisDataDtosReq getFsi_his_data_dto() {
      return this.fsi_his_data_dto;
   }

   public void setFsi_his_data_dto(MedicareAuditHisDataDtosReq fsi_his_data_dto) {
      this.fsi_his_data_dto = fsi_his_data_dto;
   }
}
