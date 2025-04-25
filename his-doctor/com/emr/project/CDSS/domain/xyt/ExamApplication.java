package com.emr.project.CDSS.domain.xyt;

import java.util.List;

public class ExamApplication {
   private String examItemName;
   private String examItemCode;
   private String applicationTime;
   private String applicationDept;
   private List bodyParts;

   public String getExamItemCode() {
      return this.examItemCode;
   }

   public void setExamItemCode(String examItemCode) {
      this.examItemCode = examItemCode;
   }

   public String getApplicationDept() {
      return this.applicationDept;
   }

   public void setApplicationDept(String applicationDept) {
      this.applicationDept = applicationDept;
   }

   public List getBodyParts() {
      return this.bodyParts;
   }

   public void setBodyParts(List bodyParts) {
      this.bodyParts = bodyParts;
   }

   public String getApplicationTime() {
      return this.applicationTime;
   }

   public void setApplicationTime(String applicationTime) {
      this.applicationTime = applicationTime;
   }

   public String getExamItemName() {
      return this.examItemName;
   }

   public void setExamItemName(String examItemName) {
      this.examItemName = examItemName;
   }
}
