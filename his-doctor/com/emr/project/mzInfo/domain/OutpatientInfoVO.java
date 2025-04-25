package com.emr.project.mzInfo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OutpatientInfoVO {
   private String caseNo;
   private String admissionNo;
   private String name;
   private String sex;
   private String age;
   private String type;
   private String idCard;
   private String deptName;
   private String deptCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date birdthDate;
   private String residentNo;
   private String residentName;
   private String inhosDiagName;
   private Long personAge;
   private Long ageMonth;
   private Long ageDay;
   private Long ageHour;
   private Long ageBranch;
   private String hospitalMark;
   private String documentTypeCd;

   public String getHospitalMark() {
      return this.hospitalMark;
   }

   public void setHospitalMark(String hospitalMark) {
      this.hospitalMark = hospitalMark;
   }

   public String getDocumentTypeCd() {
      return this.documentTypeCd;
   }

   public void setDocumentTypeCd(String documentTypeCd) {
      this.documentTypeCd = documentTypeCd;
   }

   public Long getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(Long personAge) {
      this.personAge = personAge;
   }

   public Long getAgeMonth() {
      return this.ageMonth;
   }

   public void setAgeMonth(Long ageMonth) {
      this.ageMonth = ageMonth;
   }

   public Long getAgeDay() {
      return this.ageDay;
   }

   public void setAgeDay(Long ageDay) {
      this.ageDay = ageDay;
   }

   public Long getAgeHour() {
      return this.ageHour;
   }

   public void setAgeHour(Long ageHour) {
      this.ageHour = ageHour;
   }

   public Long getAgeBranch() {
      return this.ageBranch;
   }

   public void setAgeBranch(Long ageBranch) {
      this.ageBranch = ageBranch;
   }

   public Date getBirdthDate() {
      return this.birdthDate;
   }

   public void setBirdthDate(Date birdthDate) {
      this.birdthDate = birdthDate;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public String getResidentNo() {
      return this.residentNo;
   }

   public void setResidentNo(String residentNo) {
      this.residentNo = residentNo;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }

   public String getInhosDiagName() {
      return this.inhosDiagName;
   }

   public void setInhosDiagName(String inhosDiagName) {
      this.inhosDiagName = inhosDiagName;
   }
}
