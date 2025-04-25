package com.emr.project.webEditor.zb.vo;

import java.util.List;

public class SevenDayVo {
   private String startTime;
   private String endTime;
   private List dayVoList;
   private List weeklyVoList;

   public List getWeeklyVoList() {
      return this.weeklyVoList;
   }

   public void setWeeklyVoList(List weeklyVoList) {
      this.weeklyVoList = weeklyVoList;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public List getDayVoList() {
      return this.dayVoList;
   }

   public void setDayVoList(List dayVoList) {
      this.dayVoList = dayVoList;
   }

   public String toString() {
      return "SevenDayVo{startTime='" + this.startTime + '\'' + ", endTime='" + this.endTime + '\'' + ", dayVoList=" + this.dayVoList + '}';
   }
}
