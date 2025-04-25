package com.emr.project.CDSS.domain.xyt;

public class TestsApplication {
   private String testsItemName;
   private String testsItemCode;
   private String applicationTime;
   private String applicationDept;
   private String testsSpecimens;

   public String getTestsItemName() {
      return this.testsItemName;
   }

   public void setTestsItemName(String testsItemName) {
      this.testsItemName = testsItemName;
   }

   public String getApplicationDept() {
      return this.applicationDept;
   }

   public void setApplicationDept(String applicationDept) {
      this.applicationDept = applicationDept;
   }

   public String getTestsSpecimens() {
      return this.testsSpecimens;
   }

   public void setTestsSpecimens(String testsSpecimens) {
      this.testsSpecimens = testsSpecimens;
   }

   public String getTestsItemCode() {
      return this.testsItemCode;
   }

   public void setTestsItemCode(String testsItemCode) {
      this.testsItemCode = testsItemCode;
   }

   public String getApplicationTime() {
      return this.applicationTime;
   }

   public void setApplicationTime(String applicationTime) {
      this.applicationTime = applicationTime;
   }
}
