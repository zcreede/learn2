package com.emr.project.borrowing.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrIndexBorrow extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "申请人编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请人姓名"
   )
   private String appDocName;
   @Excel(
      name = "申请人科室"
   )
   private String appDeptCd;
   @Excel(
      name = "申请人科室名称"
   )
   private String appDeptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date appTime;
   @Excel(
      name = "借阅用途"
   )
   private String borrowPurpose;
   @Excel(
      name = "借阅期限"
   )
   private Long borrowPeriod;
   @Excel(
      name = "借阅期限单位 D天"
   )
   private String borrowPeriodUnit;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "应归还时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date borrowEndTime;
   @Excel(
      name = "患者病案号"
   )
   private String caseNo;
   @Excel(
      name = "患者住院号"
   )
   private String admissionNo;
   private List admissionNoList;
   @Excel(
      name = "患者住院次数"
   )
   private Long hospitalizedCount;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "审批人科室编码"
   )
   private String conDeptCd;
   @Excel(
      name = "审批人科室名称"
   )
   private String conDeptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "审批时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date conTime;
   @Excel(
      name = "审批人编码"
   )
   private String conDocCd;
   @Excel(
      name = "审批人姓名"
   )
   private String conDocName;
   @Excel(
      name = "审批备注"
   )
   private String conRemark;
   @Excel(
      name = "申请状态(0申请 1通过 2驳回 3作废 4已归还)"
   )
   private String appStatus;
   private List appStatusList;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "出院科别编号"
   )
   private String dischargeDepartmentNo;
   @Excel(
      name = "出院科别名称"
   )
   private String dischargeDepartmentName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "出科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date leaveHospitalDate;
   @Excel(
      name = "住院医师内码"
   )
   private String residentCode;
   @Excel(
      name = "住院医师编号"
   )
   private String residentNo;
   @Excel(
      name = "住院医师姓名"
   )
   private String residentName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date leaveHospitalDateTime;

   public Date getLeaveHospitalDateTime() {
      return this.leaveHospitalDateTime;
   }

   public void setLeaveHospitalDateTime(Date leaveHospitalDateTime) {
      this.leaveHospitalDateTime = leaveHospitalDateTime;
   }

   public List getAppStatusList() {
      return this.appStatusList;
   }

   public void setAppStatusList(List appStatusList) {
      this.appStatusList = appStatusList;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setBorrowPurpose(String borrowPurpose) {
      this.borrowPurpose = borrowPurpose;
   }

   public String getBorrowPurpose() {
      return this.borrowPurpose;
   }

   public void setBorrowPeriod(Long borrowPeriod) {
      this.borrowPeriod = borrowPeriod;
   }

   public Long getBorrowPeriod() {
      return this.borrowPeriod;
   }

   public void setBorrowPeriodUnit(String borrowPeriodUnit) {
      this.borrowPeriodUnit = borrowPeriodUnit;
   }

   public String getBorrowPeriodUnit() {
      return this.borrowPeriodUnit;
   }

   public void setBorrowEndTime(Date borrowEndTime) {
      this.borrowEndTime = borrowEndTime;
   }

   public Date getBorrowEndTime() {
      return this.borrowEndTime;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public List getAdmissionNoList() {
      return this.admissionNoList;
   }

   public void setAdmissionNoList(List admissionNoList) {
      this.admissionNoList = admissionNoList;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setHospitalizedCount(Long hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Long getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setConDeptCd(String conDeptCd) {
      this.conDeptCd = conDeptCd;
   }

   public String getConDeptCd() {
      return this.conDeptCd;
   }

   public void setConDeptName(String conDeptName) {
      this.conDeptName = conDeptName;
   }

   public String getConDeptName() {
      return this.conDeptName;
   }

   public void setConTime(Date conTime) {
      this.conTime = conTime;
   }

   public Date getConTime() {
      return this.conTime;
   }

   public void setConDocCd(String conDocCd) {
      this.conDocCd = conDocCd;
   }

   public String getConDocCd() {
      return this.conDocCd;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setConRemark(String conRemark) {
      this.conRemark = conRemark;
   }

   public String getConRemark() {
      return this.conRemark;
   }

   public void setAppStatus(String appStatus) {
      this.appStatus = appStatus;
   }

   public String getAppStatus() {
      return this.appStatus;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setDischargeDepartmentNo(String dischargeDepartmentNo) {
      this.dischargeDepartmentNo = dischargeDepartmentNo;
   }

   public String getDischargeDepartmentNo() {
      return this.dischargeDepartmentNo;
   }

   public void setDischargeDepartmentName(String dischargeDepartmentName) {
      this.dischargeDepartmentName = dischargeDepartmentName;
   }

   public String getDischargeDepartmentName() {
      return this.dischargeDepartmentName;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setResidentCode(String residentCode) {
      this.residentCode = residentCode;
   }

   public String getResidentCode() {
      return this.residentCode;
   }

   public void setResidentNo(String residentNo) {
      this.residentNo = residentNo;
   }

   public String getResidentNo() {
      return this.residentNo;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appTime", this.getAppTime()).append("borrowPurpose", this.getBorrowPurpose()).append("borrowPeriod", this.getBorrowPeriod()).append("borrowPeriodUnit", this.getBorrowPeriodUnit()).append("borrowEndTime", this.getBorrowEndTime()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientName", this.getPatientName()).append("conDeptCd", this.getConDeptCd()).append("conDeptName", this.getConDeptName()).append("conTime", this.getConTime()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("conRemark", this.getConRemark()).append("appStatus", this.getAppStatus()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("dischargeDepartmentNo", this.getDischargeDepartmentNo()).append("dischargeDepartmentName", this.getDischargeDepartmentName()).append("leaveHospitalDate", this.getLeaveHospitalDate()).append("residentCode", this.getResidentCode()).append("residentNo", this.getResidentNo()).append("residentName", this.getResidentName()).toString();
   }
}
