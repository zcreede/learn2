package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.WorkLoadPatient;

public class WorkLoadPatientVo extends WorkLoadPatient {
   private String age;
   private String outWardName;
   private String inWardName;

   public String getOutWardName() {
      return this.outWardName;
   }

   public void setOutWardName(String outWardName) {
      this.outWardName = outWardName;
   }

   public String getInWardName() {
      return this.inWardName;
   }

   public void setInWardName(String inWardName) {
      this.inWardName = inWardName;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }
}
