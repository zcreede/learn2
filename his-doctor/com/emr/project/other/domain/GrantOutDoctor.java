package com.emr.project.other.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GrantOutDoctor extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "附属号"
   )
   private String subNo;
   @Excel(
      name = "附属号姓名"
   )
   private String subName;
   @Excel(
      name = "性别"
   )
   private String gender;
   @Excel(
      name = "医师编码"
   )
   private String docCode;
   @Excel(
      name = "医师姓名"
   )
   private String docName;
   @Excel(
      name = "科室代码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "结束时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date endDate;
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
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用；2：未开始；3：已结束"
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
   private String sourceCd;
   private String source;

   public String getSource() {
      return this.source;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getSourceCd() {
      return this.sourceCd;
   }

   public void setSourceCd(String sourceCd) {
      this.sourceCd = sourceCd;
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

   public void setSubNo(String subNo) {
      this.subNo = subNo;
   }

   public String getSubNo() {
      return this.subNo;
   }

   public void setSubName(String subName) {
      this.subName = subName;
   }

   public String getSubName() {
      return this.subName;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getGender() {
      return this.gender;
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

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("subNo", this.getSubNo()).append("subName", this.getSubName()).append("gender", this.getGender()).append("docCode", this.getDocCode()).append("docName", this.getDocName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("endDate", this.getEndDate()).append("remark", this.getRemark()).append("creDate", this.getCreDate()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
