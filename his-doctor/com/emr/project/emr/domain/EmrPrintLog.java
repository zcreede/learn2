package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrPrintLog extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者就诊id"
   )
   private String patientId;
   @Excel(
      name = "住院号或者门诊号"
   )
   private String inpNo;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "病历文件索引id"
   )
   private String fileId;
   @Excel(
      name = "文件类型 1 病历文档  2 病案首页 3 医嘱本 9 整体打印"
   )
   private String fileType;
   @Excel(
      name = "打印人编码"
   )
   private String printPer;
   @Excel(
      name = "打印人姓名"
   )
   private String printPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "打印时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date printDate;
   @Excel(
      name = "打印机器IP"
   )
   private String printIp;
   @Excel(
      name = "打印类型",
      readConverterExp = "1=、完整打印,2=、病历续打"
   )
   private String printType;
   @Excel(
      name = "起始页码"
   )
   private Integer beginPage;
   @Excel(
      name = "起始行"
   )
   private Integer beginRow;
   @Excel(
      name = "结束页码"
   )
   private Integer endPage;
   @Excel(
      name = "结束行"
   )
   private Integer endRow;

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

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setFileId(String fileId) {
      this.fileId = fileId;
   }

   public String getFileId() {
      return this.fileId;
   }

   public void setFileType(String fileType) {
      this.fileType = fileType;
   }

   public String getFileType() {
      return this.fileType;
   }

   public void setPrintPer(String printPer) {
      this.printPer = printPer;
   }

   public String getPrintPer() {
      return this.printPer;
   }

   public void setPrintPerName(String printPerName) {
      this.printPerName = printPerName;
   }

   public String getPrintPerName() {
      return this.printPerName;
   }

   public void setPrintDate(Date printDate) {
      this.printDate = printDate;
   }

   public Date getPrintDate() {
      return this.printDate;
   }

   public void setPrintIp(String printIp) {
      this.printIp = printIp;
   }

   public String getPrintIp() {
      return this.printIp;
   }

   public void setPrintType(String printType) {
      this.printType = printType;
   }

   public String getPrintType() {
      return this.printType;
   }

   public void setBeginPage(Integer beginPage) {
      this.beginPage = beginPage;
   }

   public Integer getBeginPage() {
      return this.beginPage;
   }

   public void setBeginRow(Integer beginRow) {
      this.beginRow = beginRow;
   }

   public Integer getBeginRow() {
      return this.beginRow;
   }

   public void setEndPage(Integer endPage) {
      this.endPage = endPage;
   }

   public Integer getEndPage() {
      return this.endPage;
   }

   public void setEndRow(Integer endRow) {
      this.endRow = endRow;
   }

   public Integer getEndRow() {
      return this.endRow;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("patientName", this.getPatientName()).append("fileId", this.getFileId()).append("fileType", this.getFileType()).append("printPer", this.getPrintPer()).append("printPerName", this.getPrintPerName()).append("printDate", this.getPrintDate()).append("printIp", this.getPrintIp()).append("printType", this.getPrintType()).append("beginPage", this.getBeginPage()).append("beginRow", this.getBeginRow()).append("endPage", this.getEndPage()).append("endRow", this.getEndRow()).toString();
   }
}
