package com.emr.project.tmpl.domain.vo;

import com.emr.project.tmpl.domain.ElemMacro;

public class ElemMacroVo extends ElemMacro {
   private String patientId;
   private Object elemSour;
   private String babyId;

   public Object getElemSour() {
      return this.elemSour;
   }

   public void setElemSour(Object elemSour) {
      this.elemSour = elemSour;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getBabyId() {
      return this.babyId;
   }

   public void setBabyId(String babyId) {
      this.babyId = babyId;
   }
}
