package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EmrMessageVo extends EmrMessage {
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date beginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date endTime;
   private String patientMainId;
   private String mrFileId;
   private String testExamId;
   private String isRecallApply;
   private String mrState;
   private String visitId;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   private String resDocCd;
   private String resDocName;
   private String recordNo;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String age;

   public String getVisitId() {
      return this.visitId;
   }

   public void setVisitId(String visitId) {
      this.visitId = visitId;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
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

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getIsRecallApply() {
      return this.isRecallApply;
   }

   public void setIsRecallApply(String isRecallApply) {
      this.isRecallApply = isRecallApply;
   }

   public String getTestExamId() {
      return this.testExamId;
   }

   public void setTestExamId(String testExamId) {
      this.testExamId = testExamId;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }
}
