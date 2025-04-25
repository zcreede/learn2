package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSearchCase extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "方案名称"
   )
   private String caseName;
   @Excel(
      name = "医师工号"
   )
   private String emplNumber;
   @Excel(
      name = "医师名称"
   )
   private String emplName;
   @Excel(
      name = "病历文件数 "
   )
   private Long mrNum;
   @Excel(
      name = "搜索条件描述(根据查询条件生成)"
   )
   private String caseDesc;
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setCaseName(String caseName) {
      this.caseName = caseName;
   }

   public String getCaseName() {
      return this.caseName;
   }

   public void setEmplNumber(String emplNumber) {
      this.emplNumber = emplNumber;
   }

   public String getEmplNumber() {
      return this.emplNumber;
   }

   public void setEmplName(String emplName) {
      this.emplName = emplName;
   }

   public String getEmplName() {
      return this.emplName;
   }

   public void setMrNum(Long mrNum) {
      this.mrNum = mrNum;
   }

   public Long getMrNum() {
      return this.mrNum;
   }

   public void setCaseDesc(String caseDesc) {
      this.caseDesc = caseDesc;
   }

   public String getCaseDesc() {
      return this.caseDesc;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("caseName", this.getCaseName()).append("emplNumber", this.getEmplNumber()).append("emplName", this.getEmplName()).append("mrNum", this.getMrNum()).append("caseDesc", this.getCaseDesc()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
