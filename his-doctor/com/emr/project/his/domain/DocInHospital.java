package com.emr.project.his.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class DocInHospital {
   private String orgCd;
   private String orgName;
   private String inpNo;
   private String payTypeName;
   private String outpatientNo;
   private String inHospitalNo;
   private String patientName;
   private String sex;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMin;
   private String nativePlace;
   private String tel;
   private String idCard;
   private String address;
   private String outpatientDiag;
   private String inhosType;
   private String inhosDeptName;
   private String inhosCond;
   private BigDecimal advanceDeposit;
   private String remark;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date inhosTime;
   private String outpatientDoc;
   private String lxr;
   private String mzksMc;

   public String getLxr() {
      return this.lxr;
   }

   public void setLxr(String lxr) {
      this.lxr = lxr;
   }

   public String getMzksMc() {
      return this.mzksMc;
   }

   public void setMzksMc(String mzksMc) {
      this.mzksMc = mzksMc;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getOutpatientNo() {
      return this.outpatientNo;
   }

   public void setOutpatientNo(String outpatientNo) {
      this.outpatientNo = outpatientNo;
   }

   public String getInHospitalNo() {
      return this.inHospitalNo;
   }

   public void setInHospitalNo(String inHospitalNo) {
      this.inHospitalNo = inHospitalNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeMin() {
      return this.ageMin;
   }

   public void setAgeMin(Long ageMin) {
      this.ageMin = ageMin;
   }

   public String getNativePlace() {
      return this.nativePlace;
   }

   public void setNativePlace(String nativePlace) {
      this.nativePlace = nativePlace;
   }

   public String getTel() {
      return this.tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getOutpatientDiag() {
      return this.outpatientDiag;
   }

   public void setOutpatientDiag(String outpatientDiag) {
      this.outpatientDiag = outpatientDiag;
   }

   public String getInhosType() {
      return this.inhosType;
   }

   public void setInhosType(String inhosType) {
      this.inhosType = inhosType;
   }

   public String getInhosDeptName() {
      return this.inhosDeptName;
   }

   public void setInhosDeptName(String inhosDeptName) {
      this.inhosDeptName = inhosDeptName;
   }

   public String getInhosCond() {
      return this.inhosCond;
   }

   public void setInhosCond(String inhosCond) {
      this.inhosCond = inhosCond;
   }

   public BigDecimal getAdvanceDeposit() {
      return this.advanceDeposit;
   }

   public void setAdvanceDeposit(BigDecimal advanceDeposit) {
      this.advanceDeposit = advanceDeposit;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public String getOutpatientDoc() {
      return this.outpatientDoc;
   }

   public void setOutpatientDoc(String outpatientDoc) {
      this.outpatientDoc = outpatientDoc;
   }
}
