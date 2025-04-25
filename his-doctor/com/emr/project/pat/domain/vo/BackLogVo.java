package com.emr.project.pat.domain.vo;

import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class BackLogVo {
   private String patientId;
   private String patientName;
   private String inpNo;
   private String patientMainId;
   private Long emrFileId;
   private Long mainId;
   private String type;
   private String messageStr;
   private Boolean deptFlag;
   private String isRecallApply;
   private String recordNo;
   private String age;
   private String visitId;
   private String deptCd;
   private String deptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   private String resDocCd;
   private String resDocName;
   private String mrType;
   private TdCaConsApplyVo tdCaConsApplyVo;

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getVisitId() {
      return this.visitId;
   }

   public void setVisitId(String visitId) {
      this.visitId = visitId;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
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

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getIsRecallApply() {
      return this.isRecallApply;
   }

   public void setIsRecallApply(String isRecallApply) {
      this.isRecallApply = isRecallApply;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Boolean getDeptFlag() {
      return this.deptFlag;
   }

   public void setDeptFlag(Boolean deptFlag) {
      this.deptFlag = deptFlag;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Long getEmrFileId() {
      return this.emrFileId;
   }

   public void setEmrFileId(Long emrFileId) {
      this.emrFileId = emrFileId;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public TdCaConsApplyVo getTdCaConsApplyVo() {
      return this.tdCaConsApplyVo;
   }

   public void setTdCaConsApplyVo(TdCaConsApplyVo tdCaConsApplyVo) {
      this.tdCaConsApplyVo = tdCaConsApplyVo;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }
}
