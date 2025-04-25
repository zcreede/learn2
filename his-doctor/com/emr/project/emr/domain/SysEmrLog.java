package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysEmrLog extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "住院号"
   )
   private String inpNo;
   @Excel(
      name = "就诊ID"
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
   private String mrFileName;
   @Excel(
      name = "病历类型编码"
   )
   private String mrTypeCd;
   @Excel(
      name = "病历类型名称"
   )
   private String mrTypeName;
   @Excel(
      name = "操作类型 s013"
   )
   private String optType;
   @Excel(
      name = "操作类型名称"
   )
   private String optTypeName;
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
   private String ip;

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
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

   public void setMrFileName(String mrFileName) {
      this.mrFileName = mrFileName;
   }

   public String getMrFileName() {
      return this.mrFileName;
   }

   public void setMrTypeCd(String mrTypeCd) {
      this.mrTypeCd = mrTypeCd;
   }

   public String getMrTypeCd() {
      return this.mrTypeCd;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setOptType(String optType) {
      this.optType = optType;
   }

   public String getOptType() {
      return this.optType;
   }

   public void setOptTypeName(String optTypeName) {
      this.optTypeName = optTypeName;
   }

   public String getOptTypeName() {
      return this.optTypeName;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("inpNo", this.getInpNo()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("mrFileId", this.getMrFileId()).append("mrFileName", this.getMrFileName()).append("mrTypeCd", this.getMrTypeCd()).append("mrTypeName", this.getMrTypeName()).append("optType", this.getOptType()).append("optTypeName", this.getOptTypeName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("ip", this.getIp()).toString();
   }
}
