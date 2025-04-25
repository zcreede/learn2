package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class RuleAnalysisPatientDto implements Serializable {
   private String patn_id;
   private String patn_name;
   private String gend;
   private String brdy;
   private String poolarea;
   private String curr_jzid;
   private String sfzjhm;
   private String fsi_his_data_dto;
   private FsiEncounterDto fsi_encounter_dtos;

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

   public String getCurr_jzid() {
      return this.curr_jzid;
   }

   public void setCurr_jzid(String curr_jzid) {
      this.curr_jzid = curr_jzid;
   }

   public String getSfzjhm() {
      return this.sfzjhm;
   }

   public void setSfzjhm(String sfzjhm) {
      this.sfzjhm = sfzjhm;
   }

   public String getFsi_his_data_dto() {
      return this.fsi_his_data_dto;
   }

   public void setFsi_his_data_dto(String fsi_his_data_dto) {
      this.fsi_his_data_dto = fsi_his_data_dto;
   }

   public FsiEncounterDto getFsi_encounter_dtos() {
      return this.fsi_encounter_dtos;
   }

   public void setFsi_encounter_dtos(FsiEncounterDto fsi_encounter_dtos) {
      this.fsi_encounter_dtos = fsi_encounter_dtos;
   }
}
