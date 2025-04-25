package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasDoctGroup extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "医疗机构代码"
   )
   private String groupName;
   @Excel(
      name = "患者当前所在科室代码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "有效标志",
      readConverterExp = "1=：有效；0：无效"
   )
   private String isValid;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
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
      name = "修改人编码"
   )
   private String altPerCode;
   private String altPerName;
   private Date altDate;
   private Integer nums;

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
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

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getGroupName() {
      return this.groupName;
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

   public void setIsValid(String isValid) {
      this.isValid = isValid;
   }

   public String getIsValid() {
      return this.isValid;
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

   public Integer getNums() {
      return this.nums;
   }

   public void setNums(Integer nums) {
      this.nums = nums;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("groupName", this.getGroupName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("isValid", this.getIsValid()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).toString();
   }
}
