package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasDictMedicine extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "编码"
   )
   private String code;
   @Excel(
      name = "名称"
   )
   private String name;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "上级编码"
   )
   private String parentCode;
   @Excel(
      name = "1:一级 2 二级"
   )
   private String levelCd;
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
   @Excel(
      name = "归属科室编码"
   )
   private String soleDeptCode;
   @Excel(
      name = "归属科室名称"
   )
   private String soleDeptName;
   private Long soleDeptCodeId;
   private List children;

   public Long getSoleDeptCodeId() {
      return this.soleDeptCodeId;
   }

   public void setSoleDeptCodeId(Long soleDeptCodeId) {
      this.soleDeptCodeId = soleDeptCodeId;
   }

   public String getSoleDeptCode() {
      return this.soleDeptCode;
   }

   public void setSoleDeptCode(String soleDeptCode) {
      this.soleDeptCode = soleDeptCode;
   }

   public String getSoleDeptName() {
      return this.soleDeptName;
   }

   public void setSoleDeptName(String soleDeptName) {
      this.soleDeptName = soleDeptName;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List children) {
      this.children = children;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

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

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setParentCode(String parentCode) {
      this.parentCode = parentCode;
   }

   public String getParentCode() {
      return this.parentCode;
   }

   public void setLevelCd(String levelCd) {
      this.levelCd = levelCd;
   }

   public String getLevelCd() {
      return this.levelCd;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("code", this.getCode()).append("name", this.getName()).append("inputstrphtc", this.getInputstrphtc()).append("parentCode", this.getParentCode()).append("levelCd", this.getLevelCd()).append("validFlag", this.getValidFlag()).append("remark", this.getRemark()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
