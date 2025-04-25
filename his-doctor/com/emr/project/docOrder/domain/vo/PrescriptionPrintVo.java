package com.emr.project.docOrder.domain.vo;

import java.math.BigDecimal;
import java.util.List;

public class PrescriptionPrintVo {
   private String payTypeCd;
   private String payTypeName;
   private String healCardNo;
   private String orderNo;
   private String patientId;
   private String patientName;
   private String sexCd;
   private String age;
   private String inpNo;
   private String deptName;
   private String wardName;
   private String bedNo;
   private String diagInfo;
   private String orderStartDateY;
   private String orderStartDateM;
   private String orderStartDateD;
   private String addr;
   private String tel;
   private List orderSaveVoList;
   private BigDecimal orderTotal;

   public String getPayTypeCd() {
      return this.payTypeCd;
   }

   public void setPayTypeCd(String payTypeCd) {
      this.payTypeCd = payTypeCd;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getHealCardNo() {
      return this.healCardNo;
   }

   public void setHealCardNo(String healCardNo) {
      this.healCardNo = healCardNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getDiagInfo() {
      return this.diagInfo;
   }

   public void setDiagInfo(String diagInfo) {
      this.diagInfo = diagInfo;
   }

   public String getOrderStartDateY() {
      return this.orderStartDateY;
   }

   public void setOrderStartDateY(String orderStartDateY) {
      this.orderStartDateY = orderStartDateY;
   }

   public String getOrderStartDateM() {
      return this.orderStartDateM;
   }

   public void setOrderStartDateM(String orderStartDateM) {
      this.orderStartDateM = orderStartDateM;
   }

   public String getOrderStartDateD() {
      return this.orderStartDateD;
   }

   public void setOrderStartDateD(String orderStartDateD) {
      this.orderStartDateD = orderStartDateD;
   }

   public String getAddr() {
      return this.addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   public String getTel() {
      return this.tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public List getOrderSaveVoList() {
      return this.orderSaveVoList;
   }

   public void setOrderSaveVoList(List orderSaveVoList) {
      this.orderSaveVoList = orderSaveVoList;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }
}
