package com.emr.project.qc.domain.vo;

import java.util.Date;

public class BqBeginEndTimeVo {
   private String bqCode;
   private String bqName;
   private Date beginTime;
   private Date endTime;

   public BqBeginEndTimeVo() {
   }

   public BqBeginEndTimeVo(String bqCode, String bqName, Date beginTime, Date endTime) {
      this.bqCode = bqCode;
      this.bqName = bqName;
      this.beginTime = beginTime;
      this.endTime = endTime;
   }

   public String getBqName() {
      return this.bqName;
   }

   public void setBqName(String bqName) {
      this.bqName = bqName;
   }

   public String getBqCode() {
      return this.bqCode;
   }

   public void setBqCode(String bqCode) {
      this.bqCode = bqCode;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }
}
