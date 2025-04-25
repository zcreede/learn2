package com.emr.project.system.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.system.domain.SysDept;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SysOrgVo extends SysDept {
   private Long id;
   @Excel(
      name = "父机构id"
   )
   private Long parentId;
   @Excel(
      name = "医疗组织机构编码 病案"
   )
   private String orgCode;
   @Excel(
      name = "机构名称"
   )
   private String orgName;
   @Excel(
      name = "机构第二名称"
   )
   private String orgNameSecond;
   @Excel(
      name = "机构分类管理类别代码  1 非盈利性 医疗机构 2  盈 利性医疗机构  9 其 他"
   )
   private String manageType;
   @Excel(
      name = "机构类型 字典类型 s019"
   )
   private String orgType;
   @Excel(
      name = "机构类型名称"
   )
   private String orgTypeName;
   @Excel(
      name = "机构等级"
   )
   private String orgLevel;
   @Excel(
      name = "机构等级名称 字典类型 s020"
   )
   private String orgLevelName;
   @Excel(
      name = "机构法人姓名"
   )
   private String corporationName;
   @Excel(
      name = "机构法人手机号"
   )
   private String corporationPhone;
   @Excel(
      name = "机构地址"
   )
   private String address;
   @Excel(
      name = "医保定点医疗机构代码"
   )
   private String medicalInsuranceOrgCode;
   @Excel(
      name = "排序"
   )
   private Long sort;
   private String delFlag;
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
   private String deptFlag;
   private String currentDeptFlag;

   public String getCurrentDeptFlag() {
      return this.currentDeptFlag;
   }

   public void setCurrentDeptFlag(String currentDeptFlag) {
      this.currentDeptFlag = currentDeptFlag;
   }

   public String getDeptFlag() {
      return this.deptFlag;
   }

   public void setDeptFlag(String deptFlag) {
      this.deptFlag = deptFlag;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getParentId() {
      return this.parentId;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgNameSecond() {
      return this.orgNameSecond;
   }

   public void setOrgNameSecond(String orgNameSecond) {
      this.orgNameSecond = orgNameSecond;
   }

   public String getManageType() {
      return this.manageType;
   }

   public void setManageType(String manageType) {
      this.manageType = manageType;
   }

   public String getOrgType() {
      return this.orgType;
   }

   public void setOrgType(String orgType) {
      this.orgType = orgType;
   }

   public String getOrgTypeName() {
      return this.orgTypeName;
   }

   public void setOrgTypeName(String orgTypeName) {
      this.orgTypeName = orgTypeName;
   }

   public String getOrgLevel() {
      return this.orgLevel;
   }

   public void setOrgLevel(String orgLevel) {
      this.orgLevel = orgLevel;
   }

   public String getOrgLevelName() {
      return this.orgLevelName;
   }

   public void setOrgLevelName(String orgLevelName) {
      this.orgLevelName = orgLevelName;
   }

   public String getCorporationName() {
      return this.corporationName;
   }

   public void setCorporationName(String corporationName) {
      this.corporationName = corporationName;
   }

   public String getCorporationPhone() {
      return this.corporationPhone;
   }

   public void setCorporationPhone(String corporationPhone) {
      this.corporationPhone = corporationPhone;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getMedicalInsuranceOrgCode() {
      return this.medicalInsuranceOrgCode;
   }

   public void setMedicalInsuranceOrgCode(String medicalInsuranceOrgCode) {
      this.medicalInsuranceOrgCode = medicalInsuranceOrgCode;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

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
}
