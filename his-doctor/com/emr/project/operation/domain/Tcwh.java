package com.emr.project.operation.domain;

public class Tcwh {
   private Long id;
   private String hospitalCode;
   private String wardNo;
   private String packageNo;
   private String packageName;
   private String packagePym;
   private String shareClass;
   private String flag;
   private String operatorNo;

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

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
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getPackageNo() {
      return this.packageNo;
   }

   public void setPackageNo(String packageNo) {
      this.packageNo = packageNo == null ? null : packageNo.trim();
   }

   public String getPackageName() {
      return this.packageName;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName == null ? null : packageName.trim();
   }

   public String getPackagePym() {
      return this.packagePym;
   }

   public void setPackagePym(String packagePym) {
      this.packagePym = packagePym == null ? null : packagePym.trim();
   }

   public String getShareClass() {
      return this.shareClass;
   }

   public void setShareClass(String shareClass) {
      this.shareClass = shareClass == null ? null : shareClass.trim();
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String flag) {
      this.flag = flag == null ? null : flag.trim();
   }
}
