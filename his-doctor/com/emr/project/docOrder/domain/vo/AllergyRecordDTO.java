package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class AllergyRecordDTO {
   private String admissionNo;
   private String userCode;
   private String passWord;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operatorDate;
   private String skinTestResults;
   private String skinTestResultsLot;
   private String orderNo;
   private String orderSortNumber;
   private String chargeNo;
   private String chargeName;

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getSkinTestResultsLot() {
      return this.skinTestResultsLot;
   }

   public void setSkinTestResultsLot(String skinTestResultsLot) {
      this.skinTestResultsLot = skinTestResultsLot;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getUserCode() {
      return this.userCode;
   }

   public void setUserCode(String userCode) {
      this.userCode = userCode;
   }

   public String getPassWord() {
      return this.passWord;
   }

   public void setPassWord(String passWord) {
      this.passWord = passWord;
   }

   public Date getOperatorDate() {
      return this.operatorDate;
   }

   public void setOperatorDate(Date operatorDate) {
      this.operatorDate = operatorDate;
   }
}
