package com.emr.project.recall.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IndexRecallApply extends BaseEntity {
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date appTime;
   @Excel(
      name = "召回原因"
   )
   private String recallReason;
   @Excel(
      name = "患者病案号"
   )
   private String caseNo;
   @Excel(
      name = "患者住院号"
   )
   private String admissionNo;
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
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
      name = "申请状态(0申请 1通过 2驳回 3作废)"
   )
   private String appStatus;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "归档时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date fileDate;
   @Excel(
      name = "出院科别编号"
   )
   private String dischargeDepartmentNo;
   @Excel(
      name = "出院科别名称"
   )
   private String dischargeDepartmentName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
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
   @Excel(
      name = "病历状态",
      readConverterExp = "0=0:未提交质控;10提交科室质控；11科室退回；12申请归档；13终末质控退回；14提交病案室；15归档退回；16病历归档；17取消归档"
   )
   private String mrState;

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

   public void setRecallReason(String recallReason) {
      this.recallReason = recallReason;
   }

   public String getRecallReason() {
      return this.recallReason;
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

   public void setFileDate(Date fileDate) {
      this.fileDate = fileDate;
   }

   public Date getFileDate() {
      return this.fileDate;
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

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
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

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getMrState() {
      return this.mrState;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appTime", this.getAppTime()).append("recallReason", this.getRecallReason()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("hospitalizedCount", this.getHospitalizedCount()).append("patientName", this.getPatientName()).append("conDeptCd", this.getConDeptCd()).append("conDeptName", this.getConDeptName()).append("conTime", this.getConTime()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("conRemark", this.getConRemark()).append("appStatus", this.getAppStatus()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("fileDate", this.getFileDate()).append("dischargeDepartmentNo", this.getDischargeDepartmentNo()).append("dischargeDepartmentName", this.getDischargeDepartmentName()).append("hospitalizedDate", this.getHospitalizedDate()).append("leaveHospitalDate", this.getLeaveHospitalDate()).append("residentCode", this.getResidentCode()).append("residentNo", this.getResidentNo()).append("residentName", this.getResidentName()).append("mrState", this.getMrState()).toString();
   }
}
