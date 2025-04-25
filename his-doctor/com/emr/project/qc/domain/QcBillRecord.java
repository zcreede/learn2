package com.emr.project.qc.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QcBillRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "质控人员编码"
   )
   private String qcdoctCd;
   @Excel(
      name = "质控人员名称"
   )
   private String qcdoctName;
   @Excel(
      name = "质控科室编号"
   )
   private String qcDepartCode;
   @Excel(
      name = "质控名称名称"
   )
   private String qcDepartName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "质控日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date qcDate;
   @Excel(
      name = "抽查方式 1 样本数量 2 样本比例"
   )
   private String checkWay;
   @Excel(
      name = "抽查方式名称"
   )
   private String checkWayName;
   @Excel(
      name = "抽查病历总数"
   )
   private Integer checkCount;
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

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
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

   public void setQcdoctCd(String qcdoctCd) {
      this.qcdoctCd = qcdoctCd;
   }

   public String getQcdoctCd() {
      return this.qcdoctCd;
   }

   public void setQcdoctName(String qcdoctName) {
      this.qcdoctName = qcdoctName;
   }

   public String getQcdoctName() {
      return this.qcdoctName;
   }

   public void setQcDepartCode(String qcDepartCode) {
      this.qcDepartCode = qcDepartCode;
   }

   public String getQcDepartCode() {
      return this.qcDepartCode;
   }

   public void setQcDepartName(String qcDepartName) {
      this.qcDepartName = qcDepartName;
   }

   public String getQcDepartName() {
      return this.qcDepartName;
   }

   public void setQcDate(Date qcDate) {
      this.qcDate = qcDate;
   }

   public Date getQcDate() {
      return this.qcDate;
   }

   public void setCheckWay(String checkWay) {
      this.checkWay = checkWay;
   }

   public String getCheckWay() {
      return this.checkWay;
   }

   public void setCheckWayName(String checkWayName) {
      this.checkWayName = checkWayName;
   }

   public String getCheckWayName() {
      return this.checkWayName;
   }

   public void setCheckCount(Integer checkCount) {
      this.checkCount = checkCount;
   }

   public Integer getCheckCount() {
      return this.checkCount;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("qcdoctCd", this.getQcdoctCd()).append("qcdoctName", this.getQcdoctName()).append("qcDepartCode", this.getQcDepartCode()).append("qcDepartName", this.getQcDepartName()).append("qcDate", this.getQcDate()).append("checkWay", this.getCheckWay()).append("checkWayName", this.getCheckWayName()).append("checkCount", this.getCheckCount()).toString();
   }
}
