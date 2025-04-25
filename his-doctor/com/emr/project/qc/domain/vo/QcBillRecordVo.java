package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcBillRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class QcBillRecordVo extends QcBillRecord {
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date qcDateBegin;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date qcDateEnd;
   private Integer sampleNum;
   private Double sampleRate;
   private String patientFlag;
   private Integer checkingNum;
   private Integer checkedNum;
   private Integer checkModifiedNum;
   private Integer checkFinishNum;

   public Date getQcDateBegin() {
      return this.qcDateBegin;
   }

   public void setQcDateBegin(Date qcDateBegin) {
      this.qcDateBegin = qcDateBegin;
   }

   public Date getQcDateEnd() {
      return this.qcDateEnd;
   }

   public void setQcDateEnd(Date qcDateEnd) {
      this.qcDateEnd = qcDateEnd;
   }

   public Integer getCheckingNum() {
      return this.checkingNum;
   }

   public void setCheckingNum(Integer checkingNum) {
      this.checkingNum = checkingNum;
   }

   public Integer getCheckedNum() {
      return this.checkedNum;
   }

   public void setCheckedNum(Integer checkedNum) {
      this.checkedNum = checkedNum;
   }

   public Integer getCheckModifiedNum() {
      return this.checkModifiedNum;
   }

   public void setCheckModifiedNum(Integer checkModifiedNum) {
      this.checkModifiedNum = checkModifiedNum;
   }

   public Integer getCheckFinishNum() {
      return this.checkFinishNum;
   }

   public void setCheckFinishNum(Integer checkFinishNum) {
      this.checkFinishNum = checkFinishNum;
   }

   public Integer getSampleNum() {
      return this.sampleNum;
   }

   public void setSampleNum(Integer sampleNum) {
      this.sampleNum = sampleNum;
   }

   public Double getSampleRate() {
      return this.sampleRate;
   }

   public void setSampleRate(Double sampleRate) {
      this.sampleRate = sampleRate;
   }

   public String getPatientFlag() {
      return this.patientFlag;
   }

   public void setPatientFlag(String patientFlag) {
      this.patientFlag = patientFlag;
   }
}
