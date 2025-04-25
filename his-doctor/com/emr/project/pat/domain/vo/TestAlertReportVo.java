package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.TestAlertReport;
import java.util.List;

public class TestAlertReportVo extends TestAlertReport {
   public List resultalertidList;
   private String patientName;
   private String sex;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String age;
   private String inpNo;
   private String bedNo;
   private String caseNo;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String alertdtBegin;
   private String alertdtEnd;
   private String overtimeFlag;
   private Long msgId;
   private int overtimeConfigure;
   private String mainFileId;
   private String patientMainId;
   private String mrFileFlag;
   private String reqDeptname;
   private String residentNo;
   private String residentName;

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

   public String getMrFileFlag() {
      return this.mrFileFlag;
   }

   public void setMrFileFlag(String mrFileFlag) {
      this.mrFileFlag = mrFileFlag;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getMainFileId() {
      return this.mainFileId;
   }

   public void setMainFileId(String mainFileId) {
      this.mainFileId = mainFileId;
   }

   public int getOvertimeConfigure() {
      return this.overtimeConfigure;
   }

   public void setOvertimeConfigure(int overtimeConfigure) {
      this.overtimeConfigure = overtimeConfigure;
   }

   public Long getMsgId() {
      return this.msgId;
   }

   public void setMsgId(Long msgId) {
      this.msgId = msgId;
   }

   public String getOvertimeFlag() {
      return this.overtimeFlag;
   }

   public void setOvertimeFlag(String overtimeFlag) {
      this.overtimeFlag = overtimeFlag;
   }

   public String getAlertdtBegin() {
      return this.alertdtBegin;
   }

   public void setAlertdtBegin(String alertdtBegin) {
      this.alertdtBegin = alertdtBegin;
   }

   public String getAlertdtEnd() {
      return this.alertdtEnd;
   }

   public void setAlertdtEnd(String alertdtEnd) {
      this.alertdtEnd = alertdtEnd;
   }

   public List getResultalertidList() {
      return this.resultalertidList;
   }

   public void setResultalertidList(List resultalertidList) {
      this.resultalertidList = resultalertidList;
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

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
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

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getReqDeptname() {
      return this.reqDeptname;
   }

   public void setReqDeptname(String reqDeptname) {
      this.reqDeptname = reqDeptname;
   }
}
