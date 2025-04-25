package com.emr.project.borrowing.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class PatientOutResp {
   private String inhosDiag;
   private String deptName;
   private String recordNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date fileTime;
   private String inpNo;
   private String mrState;
   private String mrStateStr;
   private String age;
   private String sex;
   private Integer visitId;
   private String patientName;
   private String patTypeName;
   private String resDocName;
   private String resDocCd;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date inhosTime;
   private int inhosDayNum;
   private Double costSum;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String deptCd;

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getMrStateStr() {
      return this.mrStateStr;
   }

   public void setMrStateStr(String mrStateStr) {
      this.mrStateStr = mrStateStr;
   }

   public Date getFileTime() {
      return this.fileTime;
   }

   public void setFileTime(Date fileTime) {
      this.fileTime = fileTime;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
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

   public String getInhosDiag() {
      return this.inhosDiag;
   }

   public void setInhosDiag(String inhosDiag) {
      this.inhosDiag = inhosDiag;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatTypeName() {
      return this.patTypeName;
   }

   public void setPatTypeName(String patTypeName) {
      this.patTypeName = patTypeName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public int getInhosDayNum() {
      return this.inhosDayNum;
   }

   public void setInhosDayNum(int inhosDayNum) {
      this.inhosDayNum = inhosDayNum;
   }

   public Double getCostSum() {
      return this.costSum;
   }

   public void setCostSum(Double costSum) {
      this.costSum = costSum;
   }
}
