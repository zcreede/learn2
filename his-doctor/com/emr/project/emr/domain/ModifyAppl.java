package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ModifyAppl extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "病历文件索引ID"
   )
   private Long mrFileId;
   @Excel(
      name = "病历文件名称"
   )
   private String fileName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date appTime;
   @Excel(
      name = "申请医生编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请医生姓名"
   )
   private String appDocName;
   @Excel(
      name = "申请科室编号"
   )
   private String appDeptCd;
   @Excel(
      name = "申请科室"
   )
   private String appDeptName;
   @Excel(
      name = "申请原因"
   )
   private String appReason;
   @Excel(
      name = "申请原因编码   1 病历修改  2 病历删除 9 其他"
   )
   private Long appReasonCd;
   @Excel(
      name = "申请原因详述"
   )
   private String appReasonDetails;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date conDate;
   @Excel(
      name = "审核状态  0  未审核  1：同意；2：驳回"
   )
   private String conState;
   @Excel(
      name = "审核医师编码"
   )
   private String conDocCd;
   @Excel(
      name = "审核医师姓名"
   )
   private String conDocName;
   @Excel(
      name = "处理期限"
   )
   private Integer treatDeadline;
   @Excel(
      name = "处理期限单位",
      readConverterExp = "天=、时"
   )
   private String deadlineUnit;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "处理截止时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date endDatetime;
   @Excel(
      name = "审核意见"
   )
   private String conView;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
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
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recoDate;
   private String mrFlag;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public String getFileName() {
      return this.fileName;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
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

   public void setAppReason(String appReason) {
      this.appReason = appReason;
   }

   public String getAppReason() {
      return this.appReason;
   }

   public void setAppReasonCd(Long appReasonCd) {
      this.appReasonCd = appReasonCd;
   }

   public Long getAppReasonCd() {
      return this.appReasonCd;
   }

   public void setAppReasonDetails(String appReasonDetails) {
      this.appReasonDetails = appReasonDetails;
   }

   public String getAppReasonDetails() {
      return this.appReasonDetails;
   }

   public void setConDate(Date conDate) {
      this.conDate = conDate;
   }

   public Date getConDate() {
      return this.conDate;
   }

   public void setConState(String conState) {
      this.conState = conState;
   }

   public String getConState() {
      return this.conState;
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

   public void setTreatDeadline(Integer treatDeadline) {
      this.treatDeadline = treatDeadline;
   }

   public Integer getTreatDeadline() {
      return this.treatDeadline;
   }

   public void setDeadlineUnit(String deadlineUnit) {
      this.deadlineUnit = deadlineUnit;
   }

   public String getDeadlineUnit() {
      return this.deadlineUnit;
   }

   public void setEndDatetime(Date endDatetime) {
      this.endDatetime = endDatetime;
   }

   public Date getEndDatetime() {
      return this.endDatetime;
   }

   public void setConView(String conView) {
      this.conView = conView;
   }

   public String getConView() {
      return this.conView;
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

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public String getMrFlag() {
      return this.mrFlag;
   }

   public void setMrFlag(String mrFlag) {
      this.mrFlag = mrFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("mrFileId", this.getMrFileId()).append("fileName", this.getFileName()).append("appTime", this.getAppTime()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appReason", this.getAppReason()).append("appReasonCd", this.getAppReasonCd()).append("appReasonDetails", this.getAppReasonDetails()).append("conDate", this.getConDate()).append("conState", this.getConState()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("treatDeadline", this.getTreatDeadline()).append("deadlineUnit", this.getDeadlineUnit()).append("endDatetime", this.getEndDatetime()).append("conView", this.getConView()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
