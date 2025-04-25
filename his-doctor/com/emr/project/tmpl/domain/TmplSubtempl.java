package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmplSubtempl extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "使用专业"
   )
   private String major;
   @Excel(
      name = "使用病种"
   )
   private String disease;
   @Excel(
      name = "病历类型",
      readConverterExp = "入=院记录、出院记录"
   )
   private String tempType;
   @Excel(
      name = "子模板类型ID"
   )
   private String elemId;
   @Excel(
      name = "子模板类型名称"
   )
   private String elemName;
   @Excel(
      name = "查询关键词"
   )
   private String tempName;
   @Excel(
      name = "助词码"
   )
   private String inputstrphtc;
   @Excel(
      name = "模板文件"
   )
   private String tempFile;
   @Excel(
      name = "科室编码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "医生编码"
   )
   private String docCd;
   @Excel(
      name = "医生姓名"
   )
   private String docName;
   @Excel(
      name = "1 科室模板 2 个人模板"
   )
   private Long tmepFlag;
   @Excel(
      name = "序号"
   )
   private Long serialNo;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
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
   private String tempText;

   public String getTempText() {
      return this.tempText;
   }

   public void setTempText(String tempText) {
      this.tempText = tempText;
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

   public void setMajor(String major) {
      this.major = major;
   }

   public String getMajor() {
      return this.major;
   }

   public void setDisease(String disease) {
      this.disease = disease;
   }

   public String getDisease() {
      return this.disease;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setElemId(String elemId) {
      this.elemId = elemId;
   }

   public String getElemId() {
      return this.elemId;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setTempFile(String tempFile) {
      this.tempFile = tempFile;
   }

   public String getTempFile() {
      return this.tempFile;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setTmepFlag(Long tmepFlag) {
      this.tmepFlag = tmepFlag;
   }

   public Long getTmepFlag() {
      return this.tmepFlag;
   }

   public void setSerialNo(Long serialNo) {
      this.serialNo = serialNo;
   }

   public Long getSerialNo() {
      return this.serialNo;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("major", this.getMajor()).append("disease", this.getDisease()).append("tempType", this.getTempType()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("tempName", this.getTempName()).append("inputstrphtc", this.getInputstrphtc()).append("tempFile", this.getTempFile()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("docCd", this.getDocCd()).append("docName", this.getDocName()).append("tmepFlag", this.getTmepFlag()).append("serialNo", this.getSerialNo()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
