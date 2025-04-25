package com.emr.project.PASS.domain.mk;

public class Exam {
   private String RequestNo;
   private String LabExamCode;
   private String LabExamName;
   private String StartDateTime;
   private String DeptCode;
   private String DeptName;
   private String DoctorCode;
   private String DoctorName;

   public String getRequestNo() {
      return this.RequestNo;
   }

   public void setRequestNo(String requestNo) {
      this.RequestNo = requestNo;
   }

   public String getLabExamCode() {
      return this.LabExamCode;
   }

   public void setLabExamCode(String labExamCode) {
      this.LabExamCode = labExamCode;
   }

   public String getLabExamName() {
      return this.LabExamName;
   }

   public void setLabExamName(String labExamName) {
      this.LabExamName = labExamName;
   }

   public String getStartDateTime() {
      return this.StartDateTime;
   }

   public void setStartDateTime(String startDateTime) {
      this.StartDateTime = startDateTime;
   }

   public String getDeptCode() {
      return this.DeptCode;
   }

   public void setDeptCode(String deptCode) {
      this.DeptCode = deptCode;
   }

   public String getDeptName() {
      return this.DeptName;
   }

   public void setDeptName(String deptName) {
      this.DeptName = deptName;
   }

   public String getDoctorCode() {
      return this.DoctorCode;
   }

   public void setDoctorCode(String doctorCode) {
      this.DoctorCode = doctorCode;
   }

   public String getDoctorName() {
      return this.DoctorName;
   }

   public void setDoctorName(String doctorName) {
      this.DoctorName = doctorName;
   }
}
