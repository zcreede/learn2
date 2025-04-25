package com.emr.project.system.domain.req;

import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.WorkLoadPatient;
import java.util.List;

public class WorkLoadCancelReq {
   private Long mainId;
   private List patient;
   private List other;

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public List getPatient() {
      return this.patient;
   }

   public void setPatient(List patient) {
      this.patient = patient;
   }

   public List getOther() {
      return this.other;
   }

   public void setOther(List other) {
      this.other = other;
   }
}
