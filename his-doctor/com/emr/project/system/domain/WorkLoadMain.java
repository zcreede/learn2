package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WorkLoadMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构编码"
   )
   private String orgCd;
   @Excel(
      name = "科室编码"
   )
   private String deptCode;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "统计日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date sumDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "上报日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date uploadDate;
   @Excel(
      name = "上报人编码"
   )
   private String uploadPerCode;
   @Excel(
      name = "上报人姓名"
   )
   private String uploadPerName;
   @Excel(
      name = "状态 1已上报 0暂存"
   )
   private String status;
   @Excel(
      name = "现有人数"
   )
   private Integer nowNum;
   @Excel(
      name = "原有人数"
   )
   private Integer originalNum;
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
      name = "修改人名称"
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

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setSumDate(Date sumDate) {
      this.sumDate = sumDate;
   }

   public Date getSumDate() {
      return this.sumDate;
   }

   public void setUploadDate(Date uploadDate) {
      this.uploadDate = uploadDate;
   }

   public Date getUploadDate() {
      return this.uploadDate;
   }

   public void setUploadPerCode(String uploadPerCode) {
      this.uploadPerCode = uploadPerCode;
   }

   public String getUploadPerCode() {
      return this.uploadPerCode;
   }

   public void setUploadPerName(String uploadPerName) {
      this.uploadPerName = uploadPerName;
   }

   public String getUploadPerName() {
      return this.uploadPerName;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setNowNum(Integer nowNum) {
      this.nowNum = nowNum;
   }

   public Integer getNowNum() {
      return this.nowNum;
   }

   public void setOriginalNum(Integer originalNum) {
      this.originalNum = originalNum;
   }

   public Integer getOriginalNum() {
      return this.originalNum;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("deptCode", this.getDeptCode()).append("deptName", this.getDeptName()).append("sumDate", this.getSumDate()).append("uploadDate", this.getUploadDate()).append("uploadPerCode", this.getUploadPerCode()).append("uploadPerName", this.getUploadPerName()).append("status", this.getStatus()).append("nowNum", this.getNowNum()).append("originalNum", this.getOriginalNum()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
