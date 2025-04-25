package com.emr.project.CDSS.domain.xyt;

import java.util.Date;

public class ExamResult {
   private String examReportNo;
   private String examItemName;
   private String examItemCode;
   private String resultValue;
   private String unit;
   private String examSaw;
   private String examSuggest;
   private Date reportTime;

   public String getExamReportNo() {
      return this.examReportNo;
   }

   public void setExamReportNo(String examReportNo) {
      this.examReportNo = examReportNo;
   }

   public String getExamItemName() {
      return this.examItemName;
   }

   public void setExamItemName(String examItemName) {
      this.examItemName = examItemName;
   }

   public String getExamItemCode() {
      return this.examItemCode;
   }

   public void setExamItemCode(String examItemCode) {
      this.examItemCode = examItemCode;
   }

   public String getResultValue() {
      return this.resultValue;
   }

   public void setResultValue(String resultValue) {
      this.resultValue = resultValue;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getExamSaw() {
      return this.examSaw;
   }

   public void setExamSaw(String examSaw) {
      this.examSaw = examSaw;
   }

   public String getExamSuggest() {
      return this.examSuggest;
   }

   public void setExamSuggest(String examSuggest) {
      this.examSuggest = examSuggest;
   }

   public Date getReportTime() {
      return this.reportTime;
   }

   public void setReportTime(Date reportTime) {
      this.reportTime = reportTime;
   }
}
