package com.emr.project.operation.domain.resp;

public class DoctorDataResp {
   private String hospitalCode;
   private String doctorName;
   private String doctorCode;
   private String doctorNo;
   private String doctorNamePym;
   private String deptCode;
   private String deptName;

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getDoctorName() {
      return this.doctorName;
   }

   public void setDoctorName(String doctorName) {
      this.doctorName = doctorName;
   }

   public String getDoctorCode() {
      return this.doctorCode;
   }

   public void setDoctorCode(String doctorCode) {
      this.doctorCode = doctorCode;
   }

   public String getDoctorNo() {
      return this.doctorNo;
   }

   public void setDoctorNo(String doctorNo) {
      this.doctorNo = doctorNo;
   }

   public String getDoctorNamePym() {
      return this.doctorNamePym;
   }

   public void setDoctorNamePym(String doctorNamePym) {
      this.doctorNamePym = doctorNamePym;
   }
}
