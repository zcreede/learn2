package com.emr.project.esSearch.domain.vo;

import com.emr.project.esSearch.domain.EmrFile;

public class EmrFileVo extends EmrFile {
   private String beginDate;
   private String endDate;
   private String patientId;

   public String getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(String beginDate) {
      this.beginDate = beginDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
