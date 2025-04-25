package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.Visitinfo;
import java.util.Date;

public class VisitinfoPersonalVo extends Visitinfo {
   private String patientMainId;
   private String inputstrphtc;
   private String sex;
   private String sexCd;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String secLevel;
   private Integer weekly;
   private String startTime;
   private String endTime;
   private Date inhosStartTime;
   private Date inhosEndTime;
   private Date outHosStartTime;
   private Date outHosEndTime;
   private String inHosState;
   private String proTypeName;
   private String nowAddrTel;
   private String age;
   private String address;
   private Date birDate;
   private String idCard;
   private String hospitalType;

   public String getHospitalType() {
      return this.hospitalType;
   }

   public void setHospitalType(String hospitalType) {
      this.hospitalType = hospitalType;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Date getOutHosStartTime() {
      return this.outHosStartTime;
   }

   public void setOutHosStartTime(Date outHosStartTime) {
      this.outHosStartTime = outHosStartTime;
   }

   public Date getOutHosEndTime() {
      return this.outHosEndTime;
   }

   public void setOutHosEndTime(Date outHosEndTime) {
      this.outHosEndTime = outHosEndTime;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public Date getBirDate() {
      return this.birDate;
   }

   public void setBirDate(Date birDate) {
      this.birDate = birDate;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getProTypeName() {
      return this.proTypeName;
   }

   public void setProTypeName(String proTypeName) {
      this.proTypeName = proTypeName;
   }

   public String getNowAddrTel() {
      return this.nowAddrTel;
   }

   public void setNowAddrTel(String nowAddrTel) {
      this.nowAddrTel = nowAddrTel;
   }

   public String getInHosState() {
      return this.inHosState;
   }

   public Date getInhosStartTime() {
      return this.inhosStartTime;
   }

   public void setInhosStartTime(Date inhosStartTime) {
      this.inhosStartTime = inhosStartTime;
   }

   public Date getInhosEndTime() {
      return this.inhosEndTime;
   }

   public void setInhosEndTime(Date inhosEndTime) {
      this.inhosEndTime = inhosEndTime;
   }

   public void setInHosState(String inHosState) {
      this.inHosState = inHosState;
   }

   public Integer getWeekly() {
      return this.weekly;
   }

   public void setWeekly(Integer weekly) {
      this.weekly = weekly;
   }

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSexCd() {
      return this.sexCd;
   }

   public void setSexCd(String sexCd) {
      this.sexCd = sexCd;
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

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }

   public String getSecLevel() {
      return this.secLevel;
   }

   public void setSecLevel(String secLevel) {
      this.secLevel = secLevel;
   }
}
