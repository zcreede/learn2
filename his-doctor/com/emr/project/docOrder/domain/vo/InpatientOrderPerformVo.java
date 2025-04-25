package com.emr.project.docOrder.domain.vo;

public class InpatientOrderPerformVo {
   private String admissionNo;
   private String depCode;
   private String orderType;
   private String orderClassCode;
   private String pharmacy;
   private String classify;
   private String sign;
   private String isSkinTest;
   private String chargeName;
   private String performListStatus1;
   private String performListStatus2;
   private String performListStatus3;
   private String planUsageFlag;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getDepCode() {
      return this.depCode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
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

   public String getClassify() {
      return this.classify;
   }

   public void setClassify(String classify) {
      this.classify = classify;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getIsSkinTest() {
      return this.isSkinTest;
   }

   public void setIsSkinTest(String isSkinTest) {
      this.isSkinTest = isSkinTest;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getPerformListStatus1() {
      return this.performListStatus1;
   }

   public void setPerformListStatus1(String performListStatus1) {
      this.performListStatus1 = performListStatus1;
   }

   public String getPerformListStatus2() {
      return this.performListStatus2;
   }

   public void setPerformListStatus2(String performListStatus2) {
      this.performListStatus2 = performListStatus2;
   }

   public String getPerformListStatus3() {
      return this.performListStatus3;
   }

   public void setPerformListStatus3(String performListStatus3) {
      this.performListStatus3 = performListStatus3;
   }

   public String getPlanUsageFlag() {
      return this.planUsageFlag;
   }

   public void setPlanUsageFlag(String planUsageFlag) {
      this.planUsageFlag = planUsageFlag;
   }
}
