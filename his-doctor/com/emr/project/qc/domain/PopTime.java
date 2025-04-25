package com.emr.project.qc.domain;

public class PopTime {
   private String amBeginTime;
   private String amEndTime;
   private String pmBeginTime;
   private String pmEndTime;

   public String getAmBeginTime() {
      return this.amBeginTime;
   }

   public void setAmBeginTime(String amBeginTime) {
      this.amBeginTime = amBeginTime;
   }

   public String getAmEndTime() {
      return this.amEndTime;
   }

   public void setAmEndTime(String amEndTime) {
      this.amEndTime = amEndTime;
   }

   public String getPmBeginTime() {
      return this.pmBeginTime;
   }

   public void setPmBeginTime(String pmBeginTime) {
      this.pmBeginTime = pmBeginTime;
   }

   public String getPmEndTime() {
      return this.pmEndTime;
   }

   public void setPmEndTime(String pmEndTime) {
      this.pmEndTime = pmEndTime;
   }
}
