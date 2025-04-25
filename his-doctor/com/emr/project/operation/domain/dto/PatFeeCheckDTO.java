package com.emr.project.operation.domain.dto;

public class PatFeeCheckDTO {
   private String admissionNo;
   private String threeLevelAccounting;
   private String projectName;
   private String projectNo;
   private Integer number;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getThreeLevelAccounting() {
      return this.threeLevelAccounting;
   }

   public void setThreeLevelAccounting(String threeLevelAccounting) {
      this.threeLevelAccounting = threeLevelAccounting;
   }

   public String getProjectName() {
      return this.projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public String getProjectNo() {
      return this.projectNo;
   }

   public void setProjectNo(String projectNo) {
      this.projectNo = projectNo;
   }

   public Integer getNumber() {
      return this.number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }
}
