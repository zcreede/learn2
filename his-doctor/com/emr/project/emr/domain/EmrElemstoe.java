package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrElemstoe extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "病历文件索引ID"
   )
   private Long mrFileId;
   @Excel(
      name = "病历文件分类名称"
   )
   private String mrTypeName;
   @Excel(
      name = "病历文件分类编号"
   )
   private String mrTypeCd;
   @Excel(
      name = "父文件元素ID"
   )
   private String prioFileElemid;
   @Excel(
      name = "父元素ID"
   )
   private Long prioElemId;
   @Excel(
      name = "父元素编码"
   )
   private String prioElemCd;
   @Excel(
      name = "父元素名称"
   )
   private String prioElemName;
   @Excel(
      name = "文件元素ID"
   )
   private String fileElemId;
   @Excel(
      name = "元素ID"
   )
   private Long elemId;
   @Excel(
      name = "元素编码"
   )
   private String elemCd;
   @Excel(
      name = "元素名称"
   )
   private String elemName;
   @Excel(
      name = "元素内容"
   )
   private String elemCon;
   @Excel(
      name = "数据元标识ID"
   )
   private Long unitId;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
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
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   private String elemConCode;
   private String elemConBase64;

   public String getElemConBase64() {
      return this.elemConBase64;
   }

   public void setElemConBase64(String elemConBase64) {
      this.elemConBase64 = elemConBase64;
   }

   public String getElemConCode() {
      return this.elemConCode;
   }

   public void setElemConCode(String elemConCode) {
      this.elemConCode = elemConCode;
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

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeCd(String mrTypeCd) {
      this.mrTypeCd = mrTypeCd;
   }

   public String getMrTypeCd() {
      return this.mrTypeCd;
   }

   public void setPrioFileElemid(String prioFileElemid) {
      this.prioFileElemid = prioFileElemid;
   }

   public String getPrioFileElemid() {
      return this.prioFileElemid;
   }

   public void setPrioElemId(Long prioElemId) {
      this.prioElemId = prioElemId;
   }

   public Long getPrioElemId() {
      return this.prioElemId;
   }

   public void setPrioElemCd(String prioElemCd) {
      this.prioElemCd = prioElemCd;
   }

   public String getPrioElemCd() {
      return this.prioElemCd;
   }

   public void setPrioElemName(String prioElemName) {
      this.prioElemName = prioElemName;
   }

   public String getPrioElemName() {
      return this.prioElemName;
   }

   public void setFileElemId(String fileElemId) {
      this.fileElemId = fileElemId;
   }

   public String getFileElemId() {
      return this.fileElemId;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemCon(String elemCon) {
      this.elemCon = elemCon;
   }

   public String getElemCon() {
      return this.elemCon;
   }

   public void setUnitId(Long unitId) {
      this.unitId = unitId;
   }

   public Long getUnitId() {
      return this.unitId;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientId", this.getPatientId()).append("mrFileId", this.getMrFileId()).append("mrTypeName", this.getMrTypeName()).append("mrTypeCd", this.getMrTypeCd()).append("prioFileElemid", this.getPrioFileElemid()).append("prioElemId", this.getPrioElemId()).append("prioElemCd", this.getPrioElemCd()).append("prioElemName", this.getPrioElemName()).append("fileElemId", this.getFileElemId()).append("elemId", this.getElemId()).append("elemCd", this.getElemCd()).append("elemName", this.getElemName()).append("elemCon", this.getElemCon()).append("unitId", this.getUnitId()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("elemConCode", this.getElemConCode()).toString();
   }
}
