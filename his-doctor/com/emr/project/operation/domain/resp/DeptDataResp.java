package com.emr.project.operation.domain.resp;

public class DeptDataResp {
   private String hospitalCode;
   private String deptName;
   private String deptNamePym;
   private String deptCode;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptNamePym() {
      return this.deptNamePym;
   }

   public void setDeptNamePym(String deptNamePym) {
      this.deptNamePym = deptNamePym;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }
}
