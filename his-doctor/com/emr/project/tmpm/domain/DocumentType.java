package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DocumentType extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   @Excel(
      name = "单据类型编码"
   )
   private String documentTypeCd;
   @Excel(
      name = "单据类型名称"
   )
   private String documentTypeName;
   @Excel(
      name = "助记码"
   )
   private String inputstrphtc;
   @Excel(
      name = "排序"
   )
   private Long sort;
   @Excel(
      name = "单据归类",
      readConverterExp = "0=2检查03检验"
   )
   private String documentClass;
   @Excel(
      name = "录入格式"
   )
   private String inputFormat;
   @Excel(
      name = "打印格式"
   )
   private String printFormat;
   @Excel(
      name = "执行科室编码"
   )
   private String execDeptCd;
   @Excel(
      name = "执行科室名称"
   )
   private String execDeptName;
   @Excel(
      name = "检查设备编码"
   )
   private String examMachineCd;
   @Excel(
      name = "检查设备名称"
   )
   private String examMachineName;
   @Excel(
      name = "打印标志",
      readConverterExp = "0=不打印；1直接打印；2提示是否打印"
   )
   private String printFlag;
   @Excel(
      name = "医嘱标志",
      readConverterExp = "0=不允许医嘱开立；1允许医嘱开立"
   )
   private String orderFlag;
   @Excel(
      name = "使用标志"
   )
   private String enabled;
   @Excel(
      name = "报告URL地址"
   )
   private String reportUrl;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
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
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   private String browserType;
   private String browserTypeName;

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setDocumentTypeCd(String documentTypeCd) {
      this.documentTypeCd = documentTypeCd;
   }

   public String getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(String browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public String getDocumentTypeCd() {
      return this.documentTypeCd;
   }

   public void setDocumentTypeName(String documentTypeName) {
      this.documentTypeName = documentTypeName;
   }

   public String getDocumentTypeName() {
      return this.documentTypeName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setDocumentClass(String documentClass) {
      this.documentClass = documentClass;
   }

   public String getDocumentClass() {
      return this.documentClass;
   }

   public void setInputFormat(String inputFormat) {
      this.inputFormat = inputFormat;
   }

   public String getInputFormat() {
      return this.inputFormat;
   }

   public void setPrintFormat(String printFormat) {
      this.printFormat = printFormat;
   }

   public String getPrintFormat() {
      return this.printFormat;
   }

   public void setExecDeptCd(String execDeptCd) {
      this.execDeptCd = execDeptCd;
   }

   public String getExecDeptCd() {
      return this.execDeptCd;
   }

   public void setExecDeptName(String execDeptName) {
      this.execDeptName = execDeptName;
   }

   public String getExecDeptName() {
      return this.execDeptName;
   }

   public void setExamMachineCd(String examMachineCd) {
      this.examMachineCd = examMachineCd;
   }

   public String getExamMachineCd() {
      return this.examMachineCd;
   }

   public void setExamMachineName(String examMachineName) {
      this.examMachineName = examMachineName;
   }

   public String getExamMachineName() {
      return this.examMachineName;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
   }

   public String getOrderFlag() {
      return this.orderFlag;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
   }

   public void setReportUrl(String reportUrl) {
      this.reportUrl = reportUrl;
   }

   public String getReportUrl() {
      return this.reportUrl;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("hospitalCode", this.getHospitalCode()).append("documentTypeCd", this.getDocumentTypeCd()).append("documentTypeName", this.getDocumentTypeName()).append("inputstrphtc", this.getInputstrphtc()).append("sort", this.getSort()).append("documentClass", this.getDocumentClass()).append("inputFormat", this.getInputFormat()).append("printFormat", this.getPrintFormat()).append("execDeptCd", this.getExecDeptCd()).append("execDeptName", this.getExecDeptName()).append("examMachineCd", this.getExamMachineCd()).append("examMachineName", this.getExamMachineName()).append("printFlag", this.getPrintFlag()).append("orderFlag", this.getOrderFlag()).append("enabled", this.getEnabled()).append("reportUrl", this.getReportUrl()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
