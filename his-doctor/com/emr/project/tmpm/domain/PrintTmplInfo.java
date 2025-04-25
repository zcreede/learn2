package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PrintTmplInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "打印分类编码"
   )
   private String typeCode;
   @Excel(
      name = "打印分类名称"
   )
   private String typeName;
   @Excel(
      name = "模板编码"
   )
   private String tmplCode;
   @Excel(
      name = "模板名称"
   )
   private String tmplName;
   @Excel(
      name = "模板名称拼音码"
   )
   private String tmplNamePym;
   @Excel(
      name = "打印模板文件名称"
   )
   private String tmplFileName;
   @Excel(
      name = "打印模板文件地址"
   )
   private String tmplFilePath;
   @Excel(
      name = "静默打印(0否 1是)"
   )
   private String silenceFlag;
   @Excel(
      name = "类型(1系统自带 2自定义)"
   )
   private String defaultFlag;
   @Excel(
      name = "打印机名称"
   )
   private String printerName;
   @Excel(
      name = "状态(1启用 0禁用)"
   )
   private String valid;
   private String delFlag;
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
   private String searchStr;

   public String getTmplNamePym() {
      return this.tmplNamePym;
   }

   public void setTmplNamePym(String tmplNamePym) {
      this.tmplNamePym = tmplNamePym;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTypeCode(String typeCode) {
      this.typeCode = typeCode;
   }

   public String getTypeCode() {
      return this.typeCode;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public void setTmplCode(String tmplCode) {
      this.tmplCode = tmplCode;
   }

   public String getTmplCode() {
      return this.tmplCode;
   }

   public void setTmplName(String tmplName) {
      this.tmplName = tmplName;
   }

   public String getTmplName() {
      return this.tmplName;
   }

   public void setTmplFileName(String tmplFileName) {
      this.tmplFileName = tmplFileName;
   }

   public String getTmplFileName() {
      return this.tmplFileName;
   }

   public void setTmplFilePath(String tmplFilePath) {
      this.tmplFilePath = tmplFilePath;
   }

   public String getTmplFilePath() {
      return this.tmplFilePath;
   }

   public void setSilenceFlag(String silenceFlag) {
      this.silenceFlag = silenceFlag;
   }

   public String getSilenceFlag() {
      return this.silenceFlag;
   }

   public void setDefaultFlag(String defaultFlag) {
      this.defaultFlag = defaultFlag;
   }

   public String getDefaultFlag() {
      return this.defaultFlag;
   }

   public void setPrinterName(String printerName) {
      this.printerName = printerName;
   }

   public String getPrinterName() {
      return this.printerName;
   }

   public void setValid(String valid) {
      this.valid = valid;
   }

   public String getValid() {
      return this.valid;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("typeCode", this.getTypeCode()).append("typeName", this.getTypeName()).append("tmplCode", this.getTmplCode()).append("tmplName", this.getTmplName()).append("tmplFileName", this.getTmplFileName()).append("tmplFilePath", this.getTmplFilePath()).append("silenceFlag", this.getSilenceFlag()).append("defaultFlag", this.getDefaultFlag()).append("printerName", this.getPrinterName()).append("remark", this.getRemark()).append("valid", this.getValid()).append("delFlag", this.getDelFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
