package com.emr.project.mzpb.domain;

public class ExamReportRecord {
   private String appointNo;
   private String examId;
   private String zyMzFlag;
   private String zyh;
   private String xm;

   public String getAppointNo() {
      return this.appointNo;
   }

   public void setAppointNo(String appointNo) {
      this.appointNo = appointNo;
   }

   public String getExamId() {
      return this.examId;
   }

   public void setExamId(String examId) {
      this.examId = examId;
   }

   public String getZyMzFlag() {
      return this.zyMzFlag;
   }

   public void setZyMzFlag(String zyMzFlag) {
      this.zyMzFlag = zyMzFlag;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getXm() {
      return this.xm;
   }

   public void setXm(String xm) {
      this.xm = xm;
   }
}
