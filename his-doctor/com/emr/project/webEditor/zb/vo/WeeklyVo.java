package com.emr.project.webEditor.zb.vo;

import java.util.List;

public class WeeklyVo {
   private Integer weekly;
   private String startTime;
   private String endTime;
   private List dayVoList;

   public Integer getWeekly() {
      return this.weekly;
   }

   public void setWeekly(Integer weekly) {
      this.weekly = weekly;
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
      return "WeeklyVo{weekly=" + this.weekly + ", startTime='" + this.startTime + '\'' + ", endTime='" + this.endTime + '\'' + ", dayVoList=" + this.dayVoList + '}';
   }
}
