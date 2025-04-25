package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrQcFlowRecord extends BaseEntity {
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
      name = "操作类型编码",
      readConverterExp = "0=0:抽查;10提交科室质控；11科室退回；12申请归档；13终末质控退回；14提交病案室；15归档退回；16病历归档；17取消归档 "
   )
   private String operTypeCd;
   @Excel(
      name = "操作类型名称"
   )
   private String operTypeName;
   @Excel(
      name = "质控评分"
   )
   private Double qcScore;
   @Excel(
      name = "质控评分等级编码"
   )
   private String qcGradeNo;
   @Excel(
      name = "质控评分等级名称"
   )
   private String qcGradeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "操作时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date operTime;
   @Excel(
      name = "操作人编码"
   )
   private String operDcoCd;
   @Excel(
      name = "操作人姓名"
   )
   private String operDcoName;
   @Excel(
      name = "操作原因",
      readConverterExp = "病=历退回等操作室可以记录操作原因"
   )
   private String operReason;
   @Excel(
      name = "操作科室编码"
   )
   private String operDeptCd;
   @Excel(
      name = "操作科室名称"
   )
   private String operDeptName;
   @Excel(
      name = "操作电脑IP地址"
   )
   private String operPcIp;
   @Excel(
      name = "质控流水号"
   )
   private Long qcSn;
   private String fileDocCd;
   private String fileDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "评分时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date fileTime;

   public Date getFileTime() {
      return this.fileTime;
   }

   public void setFileTime(Date fileTime) {
      this.fileTime = fileTime;
   }

   public String getFileDocCd() {
      return this.fileDocCd;
   }

   public void setFileDocCd(String fileDocCd) {
      this.fileDocCd = fileDocCd;
   }

   public String getFileDocName() {
      return this.fileDocName;
   }

   public void setFileDocName(String fileDocName) {
      this.fileDocName = fileDocName;
   }

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

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setOperTypeCd(String operTypeCd) {
      this.operTypeCd = operTypeCd;
   }

   public String getOperTypeCd() {
      return this.operTypeCd;
   }

   public void setOperTypeName(String operTypeName) {
      this.operTypeName = operTypeName;
   }

   public String getOperTypeName() {
      return this.operTypeName;
   }

   public void setQcScore(Double qcScore) {
      this.qcScore = qcScore;
   }

   public Double getQcScore() {
      return this.qcScore;
   }

   public void setQcGradeNo(String qcGradeNo) {
      this.qcGradeNo = qcGradeNo;
   }

   public String getQcGradeNo() {
      return this.qcGradeNo;
   }

   public void setQcGradeName(String qcGradeName) {
      this.qcGradeName = qcGradeName;
   }

   public String getQcGradeName() {
      return this.qcGradeName;
   }

   public void setOperTime(Date operTime) {
      this.operTime = operTime;
   }

   public Date getOperTime() {
      return this.operTime;
   }

   public void setOperDcoCd(String operDcoCd) {
      this.operDcoCd = operDcoCd;
   }

   public String getOperDcoCd() {
      return this.operDcoCd;
   }

   public void setOperDcoName(String operDcoName) {
      this.operDcoName = operDcoName;
   }

   public String getOperDcoName() {
      return this.operDcoName;
   }

   public void setOperReason(String operReason) {
      this.operReason = operReason;
   }

   public String getOperReason() {
      return this.operReason;
   }

   public void setOperDeptCd(String operDeptCd) {
      this.operDeptCd = operDeptCd;
   }

   public String getOperDeptCd() {
      return this.operDeptCd;
   }

   public void setOperDeptName(String operDeptName) {
      this.operDeptName = operDeptName;
   }

   public String getOperDeptName() {
      return this.operDeptName;
   }

   public void setOperPcIp(String operPcIp) {
      this.operPcIp = operPcIp;
   }

   public String getOperPcIp() {
      return this.operPcIp;
   }

   public void setQcSn(Long qcSn) {
      this.qcSn = qcSn;
   }

   public Long getQcSn() {
      return this.qcSn;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientId", this.getPatientId()).append("operTypeCd", this.getOperTypeCd()).append("operTypeName", this.getOperTypeName()).append("qcScore", this.getQcScore()).append("qcGradeNo", this.getQcGradeNo()).append("qcGradeName", this.getQcGradeName()).append("operTime", this.getOperTime()).append("operDcoCd", this.getOperDcoCd()).append("operDcoName", this.getOperDcoName()).append("operReason", this.getOperReason()).append("operDeptCd", this.getOperDeptCd()).append("operDeptName", this.getOperDeptName()).append("operPcIp", this.getOperPcIp()).append("qcSn", this.getQcSn()).toString();
   }
}
