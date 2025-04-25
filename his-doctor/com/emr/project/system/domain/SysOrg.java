package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysOrg extends BaseEntity {
   private static final long serialVersionUID = 1L;
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
      name = "机构名称拼音码"
   )
   private String searchCode;
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
   private List children = new ArrayList();
   private String parentCode;
   private String chsCode;
   private String chsName;
   @ApiModelProperty("省")
   private String addressSheng;
   @ApiModelProperty("市")
   private String addressShi;
   @ApiModelProperty("县")
   private String addressXian;
   @ApiModelProperty("乡")
   private String addressXiang;
   @ApiModelProperty("(省)地址code")
   private String addressShengCode;
   @ApiModelProperty("(市)地址code")
   private String addressShiCode;
   @ApiModelProperty("(县)地址code")
   private String addressXianCode;
   @ApiModelProperty("(乡)地址code")
   private String addressXiangCode;

   public String getAddressShengCode() {
      return this.addressShengCode;
   }

   public void setAddressShengCode(String addressShengCode) {
      this.addressShengCode = addressShengCode;
   }

   public String getAddressShiCode() {
      return this.addressShiCode;
   }

   public void setAddressShiCode(String addressShiCode) {
      this.addressShiCode = addressShiCode;
   }

   public String getAddressXianCode() {
      return this.addressXianCode;
   }

   public void setAddressXianCode(String addressXianCode) {
      this.addressXianCode = addressXianCode;
   }

   public String getAddressXiangCode() {
      return this.addressXiangCode;
   }

   public void setAddressXiangCode(String addressXiangCode) {
      this.addressXiangCode = addressXiangCode;
   }

   public String getChsCode() {
      return this.chsCode;
   }

   public void setChsCode(String chsCode) {
      this.chsCode = chsCode;
   }

   public String getChsName() {
      return this.chsName;
   }

   public void setChsName(String chsName) {
      this.chsName = chsName;
   }

   public String getParentCode() {
      return this.parentCode;
   }

   public void setParentCode(String parentCode) {
      this.parentCode = parentCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public Long getParentId() {
      return this.parentId;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public String getSearchCode() {
      return this.searchCode;
   }

   public void setSearchCode(String searchCode) {
      this.searchCode = searchCode;
   }

   public void setOrgNameSecond(String orgNameSecond) {
      this.orgNameSecond = orgNameSecond;
   }

   public String getOrgNameSecond() {
      return this.orgNameSecond;
   }

   public void setManageType(String manageType) {
      this.manageType = manageType;
   }

   public String getManageType() {
      return this.manageType;
   }

   public void setOrgType(String orgType) {
      this.orgType = orgType;
   }

   public String getOrgType() {
      return this.orgType;
   }

   public void setOrgTypeName(String orgTypeName) {
      this.orgTypeName = orgTypeName;
   }

   public String getOrgTypeName() {
      return this.orgTypeName;
   }

   public void setOrgLevel(String orgLevel) {
      this.orgLevel = orgLevel;
   }

   public String getOrgLevel() {
      return this.orgLevel;
   }

   public void setOrgLevelName(String orgLevelName) {
      this.orgLevelName = orgLevelName;
   }

   public String getOrgLevelName() {
      return this.orgLevelName;
   }

   public void setCorporationName(String corporationName) {
      this.corporationName = corporationName;
   }

   public String getCorporationName() {
      return this.corporationName;
   }

   public void setCorporationPhone(String corporationPhone) {
      this.corporationPhone = corporationPhone;
   }

   public String getCorporationPhone() {
      return this.corporationPhone;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getAddress() {
      return this.address;
   }

   public void setMedicalInsuranceOrgCode(String medicalInsuranceOrgCode) {
      this.medicalInsuranceOrgCode = medicalInsuranceOrgCode;
   }

   public String getMedicalInsuranceOrgCode() {
      return this.medicalInsuranceOrgCode;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
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

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public String getAddressSheng() {
      return this.addressSheng;
   }

   public void setAddressSheng(String addressSheng) {
      this.addressSheng = addressSheng;
   }

   public String getAddressShi() {
      return this.addressShi;
   }

   public void setAddressShi(String addressShi) {
      this.addressShi = addressShi;
   }

   public String getAddressXian() {
      return this.addressXian;
   }

   public void setAddressXian(String addressXian) {
      this.addressXian = addressXian;
   }

   public String getAddressXiang() {
      return this.addressXiang;
   }

   public void setAddressXiang(String addressXiang) {
      this.addressXiang = addressXiang;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("parentId", this.getParentId()).append("orgCode", this.getOrgCode()).append("orgName", this.getOrgName()).append("orgNameSecond", this.getOrgNameSecond()).append("manageType", this.getManageType()).append("orgType", this.getOrgType()).append("orgTypeName", this.getOrgTypeName()).append("orgLevel", this.getOrgLevel()).append("orgLevelName", this.getOrgLevelName()).append("corporationName", this.getCorporationName()).append("corporationPhone", this.getCorporationPhone()).append("address", this.getAddress()).append("medicalInsuranceOrgCode", this.getMedicalInsuranceOrgCode()).append("sort", this.getSort()).append("delFlag", this.getDelFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
