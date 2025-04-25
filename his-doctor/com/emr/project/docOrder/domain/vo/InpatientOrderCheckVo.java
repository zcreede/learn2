package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class InpatientOrderCheckVo {
   private String depCode;
   private String admissionNo;
   private String orderType;
   private String orderClassCode;
   private String pharmacy;
   private String todayChangeFlag;
   private String distributionFlag;
   private String classify;
   private String operType;
   private String isChange;
   private String isSkinTest;
   private String deptCode;
   private String orderProcessingType;
   private List orderNoList;

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public String getIsSkinTest() {
      return this.isSkinTest;
   }

   public void setIsSkinTest(String isSkinTest) {
      this.isSkinTest = isSkinTest;
   }

   public String getDepCode() {
      return this.depCode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getOrderProcessingType() {
      return this.orderProcessingType;
   }

   public void setOrderProcessingType(String orderProcessingType) {
      this.orderProcessingType = orderProcessingType;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getPharmacy() {
      return this.pharmacy;
   }

   public void setPharmacy(String pharmacy) {
      this.pharmacy = pharmacy;
   }

   public String getTodayChangeFlag() {
      return this.todayChangeFlag;
   }

   public void setTodayChangeFlag(String todayChangeFlag) {
      this.todayChangeFlag = todayChangeFlag;
   }

   public String getDistributionFlag() {
      return this.distributionFlag;
   }

   public void setDistributionFlag(String distributionFlag) {
      this.distributionFlag = distributionFlag;
   }

   public String getClassify() {
      return this.classify;
   }

   public void setClassify(String classify) {
      this.classify = classify;
   }

   public String getOperType() {
      return this.operType;
   }

   public void setOperType(String operType) {
      this.operType = operType;
   }

   public String getIsChange() {
      return this.isChange;
   }

   public void setIsChange(String isChange) {
      this.isChange = isChange;
   }
}
