package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysRegionInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String code;
   @Excel(
      name = "行政区域名称"
   )
   private String name;
   @Excel(
      name = "简称"
   )
   private String shortName;
   @Excel(
      name = "上级行政区域代码"
   )
   private String superCode;
   @Excel(
      name = "邮编"
   )
   private String zipcode;
   @Excel(
      name = "城市编码"
   )
   private String citycode;
   @Excel(
      name = "行政级别"
   )
   private Long regLevel;
   @Excel(
      name = "排序"
   )
   private Long sort;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
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
      name = "默认标识(当前医院所在省市县)"
   )
   private String defaultFlag;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setShortName(String shortName) {
      this.shortName = shortName;
   }

   public String getShortName() {
      return this.shortName;
   }

   public void setSuperCode(String superCode) {
      this.superCode = superCode;
   }

   public String getSuperCode() {
      return this.superCode;
   }

   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }

   public String getZipcode() {
      return this.zipcode;
   }

   public void setCitycode(String citycode) {
      this.citycode = citycode;
   }

   public String getCitycode() {
      return this.citycode;
   }

   public void setRegLevel(Long regLevel) {
      this.regLevel = regLevel;
   }

   public Long getRegLevel() {
      return this.regLevel;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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

   public void setDefaultFlag(String defaultFlag) {
      this.defaultFlag = defaultFlag;
   }

   public String getDefaultFlag() {
      return this.defaultFlag;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("code", this.getCode()).append("name", this.getName()).append("shortName", this.getShortName()).append("superCode", this.getSuperCode()).append("zipcode", this.getZipcode()).append("citycode", this.getCitycode()).append("regLevel", this.getRegLevel()).append("sort", this.getSort()).append("validFlag", this.getValidFlag()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("defaultFlag", this.getDefaultFlag()).append("inputstrphtc", this.getInputstrphtc()).toString();
   }
}
