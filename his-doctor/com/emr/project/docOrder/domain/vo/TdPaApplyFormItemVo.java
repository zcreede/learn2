package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaApplyFormItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class TdPaApplyFormItemVo extends TdPaApplyFormItem {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date applyTime;
   private String orderDoctorCode;
   private String orderDoctorName;
   private String physicianDptNo;
   private String physicianDptName;
   private String applyFormClassCode;
   private String performDepSite;
   private String indication;
   private String examNote;
   private String specCollectionReq;
   private String examSign;
   private String supportDiag;
   private String exclusionDiag;
   private String performDepName;
   private BigDecimal money;
   private String patientId;
   private String notice;
   private String examPart;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getExamPart() {
      return this.examPart;
   }

   public void setExamPart(String examPart) {
      this.examPart = examPart;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public BigDecimal getMoney() {
      return this.money;
   }

   public void setMoney(BigDecimal money) {
      this.money = money;
   }

   public String getPerformDepSite() {
      return this.performDepSite;
   }

   public void setPerformDepSite(String performDepSite) {
      this.performDepSite = performDepSite;
   }

   public Date getApplyTime() {
      return this.applyTime;
   }

   public void setApplyTime(Date applyTime) {
      this.applyTime = applyTime;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo;
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName;
   }

   public String getApplyFormClassCode() {
      return this.applyFormClassCode;
   }

   public void setApplyFormClassCode(String applyFormClassCode) {
      this.applyFormClassCode = applyFormClassCode;
   }

   public String getIndication() {
      return this.indication;
   }

   public void setIndication(String indication) {
      this.indication = indication;
   }

   public String getExamNote() {
      return this.examNote;
   }

   public void setExamNote(String examNote) {
      this.examNote = examNote;
   }

   public String getSpecCollectionReq() {
      return this.specCollectionReq;
   }

   public void setSpecCollectionReq(String specCollectionReq) {
      this.specCollectionReq = specCollectionReq;
   }

   public String getExamSign() {
      return this.examSign;
   }

   public void setExamSign(String examSign) {
      this.examSign = examSign;
   }

   public String getSupportDiag() {
      return this.supportDiag;
   }

   public void setSupportDiag(String supportDiag) {
      this.supportDiag = supportDiag;
   }

   public String getExclusionDiag() {
      return this.exclusionDiag;
   }

   public void setExclusionDiag(String exclusionDiag) {
      this.exclusionDiag = exclusionDiag;
   }

   public String getNotice() {
      return this.notice;
   }

   public void setNotice(String notice) {
      this.notice = notice;
   }
}
