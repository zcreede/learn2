package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WardRounRecfile extends BaseEntity {
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
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "录音时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date recTime;
   @Excel(
      name = "录音文件描述"
   )
   private String fileDesc;
   @Excel(
      name = "根据录音文件生成的文本内容"
   )
   private String recCon;
   @Excel(
      name = "文件名"
   )
   private String fileName;
   @Excel(
      name = "文件路径"
   )
   private String filePath;
   @Excel(
      name = "录音人编码"
   )
   private String recCd;
   @Excel(
      name = "录音人姓名"
   )
   private String recName;
   @Excel(
      name = "录音终端"
   )
   private String recTerm;
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
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date beginDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date endDate;

   public Date getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(Date beginDate) {
      this.beginDate = beginDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

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

   public void setRecTime(Date recTime) {
      this.recTime = recTime;
   }

   public Date getRecTime() {
      return this.recTime;
   }

   public void setFileDesc(String fileDesc) {
      this.fileDesc = fileDesc;
   }

   public String getFileDesc() {
      return this.fileDesc;
   }

   public void setRecCon(String recCon) {
      this.recCon = recCon;
   }

   public String getRecCon() {
      return this.recCon;
   }

   public void setFileName(String fileName) {
      this.fileName = fileName;
   }

   public String getFileName() {
      return this.fileName;
   }

   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   public String getFilePath() {
      return this.filePath;
   }

   public void setRecCd(String recCd) {
      this.recCd = recCd;
   }

   public String getRecCd() {
      return this.recCd;
   }

   public void setRecName(String recName) {
      this.recName = recName;
   }

   public String getRecName() {
      return this.recName;
   }

   public void setRecTerm(String recTerm) {
      this.recTerm = recTerm;
   }

   public String getRecTerm() {
      return this.recTerm;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("patientName", this.getPatientName()).append("recTime", this.getRecTime()).append("fileDesc", this.getFileDesc()).append("recCon", this.getRecCon()).append("fileName", this.getFileName()).append("filePath", this.getFilePath()).append("recCd", this.getRecCd()).append("recName", this.getRecName()).append("recTerm", this.getRecTerm()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
