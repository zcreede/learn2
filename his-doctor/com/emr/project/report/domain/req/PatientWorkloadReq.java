package com.emr.project.report.domain.req;

public class PatientWorkloadReq {
   private String type;
   private String beginDate;
   private String endDate;
   private String departmentType;
   private String residentNo;

   public String getResidentNo() {
      return this.residentNo;
   }

   public void setResidentNo(String residentNo) {
      this.residentNo = residentNo;
   }

   public String getDepartmentType() {
      return this.departmentType;
   }

   public void setDepartmentType(String departmentType) {
      this.departmentType = departmentType;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(String beginDate) {
      this.beginDate = beginDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }
}
