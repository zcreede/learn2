package com.emr.project.common.domain.vo;

public class ReplaceUrlParamVo {
   private String zyh;
   private String admissionNo;
   private String caseNo;
   private String hospitalizedCount;
   private String staffCode;
   private String staffName;
   private String deptCode;
   private String applyFormNo;
   private String idCard;
   private String beginTime;
   private String endTime;

   public ReplaceUrlParamVo() {
   }

   public ReplaceUrlParamVo(String zyh, String admissionNo, String caseNo, String hospitalizedCount, String staffCode, String staffName, String deptCode, String applyFormNo, String idCard, String beginTime, String endTime) {
      this.zyh = zyh;
      this.admissionNo = admissionNo;
      this.caseNo = caseNo;
      this.hospitalizedCount = hospitalizedCount;
      this.staffCode = staffCode;
      this.staffName = staffName;
      this.deptCode = deptCode;
      this.applyFormNo = applyFormNo;
      this.idCard = idCard;
      this.beginTime = beginTime;
      this.endTime = endTime;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(String beginTime) {
      this.beginTime = beginTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }
}
