package com.emr.project.system.domain.vo;

import com.emr.project.system.domain.SysShareElem;

public class SysShareElemVo extends SysShareElem {
   private String patientId;
   private String elemCon;
   private String elemTypeCd;
   private String elemConText;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getElemCon() {
      return this.elemCon;
   }

   public void setElemCon(String elemCon) {
      this.elemCon = elemCon;
   }

   public String getElemTypeCd() {
      return this.elemTypeCd;
   }

   public void setElemTypeCd(String elemTypeCd) {
      this.elemTypeCd = elemTypeCd;
   }

   public String getElemConText() {
      return this.elemConText;
   }

   public void setElemConText(String elemConText) {
      this.elemConText = elemConText;
   }
}
