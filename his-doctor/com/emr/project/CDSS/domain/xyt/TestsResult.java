package com.emr.project.CDSS.domain.xyt;

import java.util.Date;

public class TestsResult {
   private String testsReportNo;
   private String testsItemName;
   private String testsItemCode;
   private String resultValue;
   private String unit;
   private String exceptionFlag;
   private String testMethod;
   private Date reportTime;

   public String getTestsReportNo() {
      return this.testsReportNo;
   }

   public void setTestsReportNo(String testsReportNo) {
      this.testsReportNo = testsReportNo;
   }

   public String getTestsItemName() {
      return this.testsItemName;
   }

   public void setTestsItemName(String testsItemName) {
      this.testsItemName = testsItemName;
   }

   public String getTestsItemCode() {
      return this.testsItemCode;
   }

   public void setTestsItemCode(String testsItemCode) {
      this.testsItemCode = testsItemCode;
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

   public String getExceptionFlag() {
      return this.exceptionFlag;
   }

   public void setExceptionFlag(String exceptionFlag) {
      this.exceptionFlag = exceptionFlag;
   }

   public String getTestMethod() {
      return this.testMethod;
   }

   public void setTestMethod(String testMethod) {
      this.testMethod = testMethod;
   }

   public Date getReportTime() {
      return this.reportTime;
   }

   public void setReportTime(Date reportTime) {
      this.reportTime = reportTime;
   }
}
