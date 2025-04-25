package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.OperatRecord;

public class OperatRecordVo extends OperatRecord {
   private String patientMainId;
   private String creDateBegin;
   private String creDateEnd;

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getCreDateBegin() {
      return this.creDateBegin;
   }

   public void setCreDateBegin(String creDateBegin) {
      this.creDateBegin = creDateBegin;
   }

   public String getCreDateEnd() {
      return this.creDateEnd;
   }

   public void setCreDateEnd(String creDateEnd) {
      this.creDateEnd = creDateEnd;
   }
}
