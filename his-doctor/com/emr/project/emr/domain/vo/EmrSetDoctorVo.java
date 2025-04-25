package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSetDoctor;
import java.util.List;

public class EmrSetDoctorVo extends EmrSetDoctor {
   private List detailList;
   private String patientId;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }
}
