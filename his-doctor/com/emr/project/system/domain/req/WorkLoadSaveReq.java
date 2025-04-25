package com.emr.project.system.domain.req;

import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.WorkLoadPatient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkLoadSaveReq implements Serializable {
   private Long mainId;
   private List otherList = new ArrayList();
   private List patientList = new ArrayList();

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public List getOtherList() {
      return this.otherList;
   }

   public void setOtherList(List otherList) {
      this.otherList = otherList;
   }

   public List getPatientList() {
      return this.patientList;
   }

   public void setPatientList(List patientList) {
      this.patientList = patientList;
   }
}
