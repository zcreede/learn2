package com.emr.project.pat.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class keyPatientsDetailResp {
   private String deptCode;
   private String deptName;
   private String caseNo;
   private String admissionNo;
   private String bedid;
   private String name;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   private String sex;
   private String personAge;
   private Long ageY;
   private Long ageMonth;
   private Long ageDay;
   private Long ageHour;
   private Long ageBranch;
   private String residentName;
   private String operFlag;
   private String consFlag;
   private String bloodTrans;
   private String dieFlag;
   private String rescueFlag;
   private String illFlag;
   private String dyFlag;
   private String antiFlag;
   private String crisisFlag;

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getBloodTrans() {
      return this.bloodTrans;
   }

   public void setBloodTrans(String bloodTrans) {
      this.bloodTrans = bloodTrans;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getRescueFlag() {
      return this.rescueFlag;
   }

   public void setRescueFlag(String rescueFlag) {
      this.rescueFlag = rescueFlag;
   }

   public String getIllFlag() {
      return this.illFlag;
   }

   public void setIllFlag(String illFlag) {
      this.illFlag = illFlag;
   }

   public String getDyFlag() {
      return this.dyFlag;
   }

   public void setDyFlag(String dyFlag) {
      this.dyFlag = dyFlag;
   }

   public String getAntiFlag() {
      return this.antiFlag;
   }

   public void setAntiFlag(String antiFlag) {
      this.antiFlag = antiFlag;
   }

   public String getCrisisFlag() {
      return this.crisisFlag;
   }

   public void setCrisisFlag(String crisisFlag) {
      this.crisisFlag = crisisFlag;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
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

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
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

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }
}
