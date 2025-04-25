package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class PhysignValueVo {
   private Long baseid;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH-mm-ss"
   )
   private Date curDay;
   private String timebz;
   private String timecs;
   private String tzNameCode;
   private String tzName;
   private String tzValue;
   private String timeStr;
   private String sqlStr;
   private String curDayStart;
   private String curDayEnd;
   private String xyType;
   private String xyValue;
   private String xyGroup;

   public String getXyGroup() {
      return this.xyGroup;
   }

   public void setXyGroup(String xyGroup) {
      this.xyGroup = xyGroup;
   }

   public String getXyType() {
      return this.xyType;
   }

   public void setXyType(String xyType) {
      this.xyType = xyType;
   }

   public String getXyValue() {
      return this.xyValue;
   }

   public void setXyValue(String xyValue) {
      this.xyValue = xyValue;
   }

   public PhysignValueVo() {
   }

   public PhysignValueVo(Long baseid, String curDayStart, String curDayEnd) {
      this.baseid = baseid;
      this.curDayStart = curDayStart;
      this.curDayEnd = curDayEnd;
   }

   public String getCurDayStart() {
      return this.curDayStart;
   }

   public void setCurDayStart(String curDayStart) {
      this.curDayStart = curDayStart;
   }

   public String getCurDayEnd() {
      return this.curDayEnd;
   }

   public void setCurDayEnd(String curDayEnd) {
      this.curDayEnd = curDayEnd;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public Long getBaseid() {
      return this.baseid;
   }

   public void setBaseid(Long baseid) {
      this.baseid = baseid;
   }

   public Date getCurDay() {
      return this.curDay;
   }

   public void setCurDay(Date curDay) {
      this.curDay = curDay;
   }

   public String getTimebz() {
      return this.timebz;
   }

   public void setTimebz(String timebz) {
      this.timebz = timebz;
   }

   public String getTimecs() {
      return this.timecs;
   }

   public void setTimecs(String timecs) {
      this.timecs = timecs;
   }

   public String getTzName() {
      return this.tzName;
   }

   public void setTzName(String tzName) {
      this.tzName = tzName;
   }

   public String getTzValue() {
      return this.tzValue;
   }

   public void setTzValue(String tzValue) {
      this.tzValue = tzValue;
   }

   public String getTimeStr() {
      return this.timeStr;
   }

   public void setTimeStr(String timeStr) {
      this.timeStr = timeStr;
   }

   public String getTzNameCode() {
      return this.tzNameCode;
   }

   public void setTzNameCode(String tzNameCode) {
      this.tzNameCode = tzNameCode;
   }
}
