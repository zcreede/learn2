package com.emr.project.mrhp.domain.vo;

import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.MrHpIcu;
import java.util.List;

public class MrHpAttachVo extends MrHpAttach {
   private List mrHpIcuList;
   private String bloodFlag;
   private String isEmeCriRecord;
   private String diaDateYear;
   private String diaDateMouth;
   private String diaDateDay;

   public List getMrHpIcuList() {
      return this.mrHpIcuList;
   }

   public void setMrHpIcuList(List mrHpIcuList) {
      this.mrHpIcuList = mrHpIcuList;
   }

   public String getBloodFlag() {
      return this.bloodFlag;
   }

   public void setBloodFlag(String bloodFlag) {
      this.bloodFlag = bloodFlag;
   }

   public String getIsEmeCriRecord() {
      return this.isEmeCriRecord;
   }

   public void setIsEmeCriRecord(String isEmeCriRecord) {
      this.isEmeCriRecord = isEmeCriRecord;
   }

   public String getDiaDateYear() {
      return this.diaDateYear;
   }

   public void setDiaDateYear(String diaDateYear) {
      this.diaDateYear = diaDateYear;
   }

   public String getDiaDateMouth() {
      return this.diaDateMouth;
   }

   public void setDiaDateMouth(String diaDateMouth) {
      this.diaDateMouth = diaDateMouth;
   }

   public String getDiaDateDay() {
      return this.diaDateDay;
   }

   public void setDiaDateDay(String diaDateDay) {
      this.diaDateDay = diaDateDay;
   }
}
