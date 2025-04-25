package com.emr.project.operation.domain.resp;

public class CombinationListResp {
   private Long id;
   private String hospitalCode;
   private String deptCode;
   private String packageNo;
   private String packageName;
   private String packagePym;
   private String shareClass;
   private String flag;
   private String operatorNo;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo;
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName;
   }

   public String getPackagePym() {
      return this.packagePym;
   }

   public void setPackagePym(String packagePym) {
      this.packagePym = packagePym;
   }

   public String getShareClass() {
      return this.shareClass;
   }

   public void setShareClass(String shareClass) {
      this.shareClass = shareClass;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String flag) {
      this.flag = flag;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }
}
