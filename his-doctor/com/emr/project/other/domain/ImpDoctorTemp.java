package com.emr.project.other.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ImpDoctorTemp extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "床位编号"
   )
   private String bedNo;
   @Excel(
      name = "医师编码"
   )
   private String docCode;
   @Excel(
      name = "医师姓名"
   )
   private String docName;
   @Excel(
      name = "授权医师编码"
   )
   private String impDocCd;
   @Excel(
      name = "授权医师姓名"
   )
   private String impDocName;
   @Excel(
      name = "授权科室编号"
   )
   private String impDeptCd;
   @Excel(
      name = "授权科室名称"
   )
   private String impDeptName;
   @Excel(
      name = "授权范围",
      readConverterExp = "0=：个人；1：科室"
   )
   private String impRange;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "授权时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date impDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "授权开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date impBegTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "授权结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date impEndTime;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
   @Excel(
      name = "授权类型",
      readConverterExp = "1=：会诊；2：跨科处置；3：其他"
   )
   private String impType;
   @Excel(
      name = "授权目的"
   )
   private String impAim;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setDocCode(String docCode) {
      this.docCode = docCode;
   }

   public String getDocCode() {
      return this.docCode;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setImpDocCd(String impDocCd) {
      this.impDocCd = impDocCd;
   }

   public String getImpDocCd() {
      return this.impDocCd;
   }

   public void setImpDocName(String impDocName) {
      this.impDocName = impDocName;
   }

   public String getImpDocName() {
      return this.impDocName;
   }

   public void setImpDeptCd(String impDeptCd) {
      this.impDeptCd = impDeptCd;
   }

   public String getImpDeptCd() {
      return this.impDeptCd;
   }

   public void setImpDeptName(String impDeptName) {
      this.impDeptName = impDeptName;
   }

   public String getImpDeptName() {
      return this.impDeptName;
   }

   public void setImpRange(String impRange) {
      this.impRange = impRange;
   }

   public String getImpRange() {
      return this.impRange;
   }

   public void setImpDate(Date impDate) {
      this.impDate = impDate;
   }

   public Date getImpDate() {
      return this.impDate;
   }

   public void setImpBegTime(Date impBegTime) {
      this.impBegTime = impBegTime;
   }

   public Date getImpBegTime() {
      return this.impBegTime;
   }

   public void setImpEndTime(Date impEndTime) {
      this.impEndTime = impEndTime;
   }

   public Date getImpEndTime() {
      return this.impEndTime;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
   }

   public void setImpType(String impType) {
      this.impType = impType;
   }

   public String getImpType() {
      return this.impType;
   }

   public void setImpAim(String impAim) {
      this.impAim = impAim;
   }

   public String getImpAim() {
      return this.impAim;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("bedNo", this.getBedNo()).append("docCode", this.getDocCode()).append("docName", this.getDocName()).append("impDocCd", this.getImpDocCd()).append("impDocName", this.getImpDocName()).append("impDeptCd", this.getImpDeptCd()).append("impDeptName", this.getImpDeptName()).append("impRange", this.getImpRange()).append("impDate", this.getImpDate()).append("impBegTime", this.getImpBegTime()).append("impEndTime", this.getImpEndTime()).append("validFlag", this.getValidFlag()).append("impType", this.getImpType()).append("impAim", this.getImpAim()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
