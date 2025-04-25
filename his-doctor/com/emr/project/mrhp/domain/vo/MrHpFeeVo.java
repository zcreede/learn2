package com.emr.project.mrhp.domain.vo;

import com.emr.project.mrhp.domain.MrHpFee;

public class MrHpFeeVo extends MrHpFee {
   private String patientId;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
