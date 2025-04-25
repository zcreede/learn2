package com.emr.project.recall.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class IndexRecallApplyVO {
   private Long id;
   private String appDocCd;
   private String appDocName;
   private String appDeptCd;
   private String appDeptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date appTime;
   private String recallReason;
   private String caseNo;
   private String admissionNo;
   private Long hospitalizedCount;
   private String patientName;
   private String conDeptCd;
   private String conDeptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date conTime;
   private String conDocCd;
   private String conDocName;
   private String conRemark;
   private String appStatus;
   private String crePerCode;
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String altPerCode;
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date fileDate;
   private String dischargeDepartmentNo;
   private String dischargeDepartmentName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   private String residentCode;
   private String residentNo;
   private String residentName;
   private String mrState;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public String getRecallReason() {
      return this.recallReason;
   }

   public void setRecallReason(String recallReason) {
      this.recallReason = recallReason;
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

   public Long getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Long hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getConDeptCd() {
      return this.conDeptCd;
   }

   public void setConDeptCd(String conDeptCd) {
      this.conDeptCd = conDeptCd;
   }

   public String getConDeptName() {
      return this.conDeptName;
   }

   public void setConDeptName(String conDeptName) {
      this.conDeptName = conDeptName;
   }

   public Date getConTime() {
      return this.conTime;
   }

   public void setConTime(Date conTime) {
      this.conTime = conTime;
   }

   public String getConDocCd() {
      return this.conDocCd;
   }

   public void setConDocCd(String conDocCd) {
      this.conDocCd = conDocCd;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public String getConRemark() {
      return this.conRemark;
   }

   public void setConRemark(String conRemark) {
      this.conRemark = conRemark;
   }

   public String getAppStatus() {
      return this.appStatus;
   }

   public void setAppStatus(String appStatus) {
      this.appStatus = appStatus;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getFileDate() {
      return this.fileDate;
   }

   public void setFileDate(Date fileDate) {
      this.fileDate = fileDate;
   }

   public String getDischargeDepartmentNo() {
      return this.dischargeDepartmentNo;
   }

   public void setDischargeDepartmentNo(String dischargeDepartmentNo) {
      this.dischargeDepartmentNo = dischargeDepartmentNo;
   }

   public String getDischargeDepartmentName() {
      return this.dischargeDepartmentName;
   }

   public void setDischargeDepartmentName(String dischargeDepartmentName) {
      this.dischargeDepartmentName = dischargeDepartmentName;
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

   public String getResidentCode() {
      return this.residentCode;
   }

   public void setResidentCode(String residentCode) {
      this.residentCode = residentCode;
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

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }
}
