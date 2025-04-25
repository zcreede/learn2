package com.emr.project.CDSS.domain.xyt;

import java.util.Date;

public class VitalSigns {
   private String signsName;
   private String signsValue;
   private String signsUnit;
   private Date recordTime;

   public String getSignsName() {
      return this.signsName;
   }

   public void setSignsName(String signsName) {
      this.signsName = signsName;
   }

   public String getSignsValue() {
      return this.signsValue;
   }

   public void setSignsValue(String signsValue) {
      this.signsValue = signsValue;
   }

   public String getSignsUnit() {
      return this.signsUnit;
   }

   public void setSignsUnit(String signsUnit) {
      this.signsUnit = signsUnit;
   }

   public Date getRecordTime() {
      return this.recordTime;
   }

   public void setRecordTime(Date recordTime) {
      this.recordTime = recordTime;
   }
}
