package com.emr.project.operation.domain.resp;

public class NurseDataResp {
   private String hospitalCode;
   private String nurseName;
   private String nurseCode;
   private String nurseNo;
   private String nurseNamePym;
   private String deptCode;
   private String deptName;
   private String wardNo;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getNurseName() {
      return this.nurseName;
   }

   public void setNurseName(String nurseName) {
      this.nurseName = nurseName;
   }

   public String getNurseCode() {
      return this.nurseCode;
   }

   public void setNurseCode(String nurseCode) {
      this.nurseCode = nurseCode;
   }

   public String getNurseNo() {
      return this.nurseNo;
   }

   public void setNurseNo(String nurseNo) {
      this.nurseNo = nurseNo;
   }

   public String getNurseNamePym() {
      return this.nurseNamePym;
   }

   public void setNurseNamePym(String nurseNamePym) {
      this.nurseNamePym = nurseNamePym;
   }

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

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }
}
