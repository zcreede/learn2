package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class RuleAnalysisDataReq implements Serializable {
   private RuleAnalysisPatientDto patient_dtos;
   private String jzlx = "2";
   private String msgid;

   public RuleAnalysisPatientDto getPatient_dtos() {
      return this.patient_dtos;
   }

   public void setPatient_dtos(RuleAnalysisPatientDto patient_dtos) {
      this.patient_dtos = patient_dtos;
   }

   public String getJzlx() {
      return this.jzlx;
   }

   public void setJzlx(String jzlx) {
      this.jzlx = jzlx;
   }

   public String getMsgid() {
      return this.msgid;
   }

   public void setMsgid(String msgid) {
      this.msgid = msgid;
   }
}
