package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmPmSpec extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "标本编码"
   )
   private String specCd;
   @Excel(
      name = "标本名称"
   )
   private String specName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "标本归类代码"
   )
   private String specClassCd;
   @Excel(
      name = "标本归类名称"
   )
   private String specClassName;
   @Excel(
      name = "标本采集部位"
   )
   private String specDeterminerCd;
   @Excel(
      name = "标本采集部位名称"
   )
   private String specDeterminerName;
   @Excel(
      name = "标本要求"
   )
   private String specReq;
   @Excel(
      name = "排序"
   )
   private Long sort;
   @Excel(
      name = "使用标志((0禁用；1使用）"
   )
   private String enabled;
   @Excel(
      name = "创建人"
   )
   private String crePerCode;
   @Excel(
      name = "创建人项目"
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
      name = "修改人姓名"
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
   private String examSex;

   public String getExamSex() {
      return this.examSex;
   }

   public void setExamSex(String examSex) {
      this.examSex = examSex;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setSpecClassCd(String specClassCd) {
      this.specClassCd = specClassCd;
   }

   public String getSpecClassCd() {
      return this.specClassCd;
   }

   public void setSpecClassName(String specClassName) {
      this.specClassName = specClassName;
   }

   public String getSpecClassName() {
      return this.specClassName;
   }

   public void setSpecDeterminerCd(String specDeterminerCd) {
      this.specDeterminerCd = specDeterminerCd;
   }

   public String getSpecDeterminerCd() {
      return this.specDeterminerCd;
   }

   public void setSpecDeterminerName(String specDeterminerName) {
      this.specDeterminerName = specDeterminerName;
   }

   public String getSpecDeterminerName() {
      return this.specDeterminerName;
   }

   public void setSpecReq(String specReq) {
      this.specReq = specReq;
   }

   public String getSpecReq() {
      return this.specReq;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("specCd", this.getSpecCd()).append("specName", this.getSpecName()).append("inputstrphtc", this.getInputstrphtc()).append("specClassCd", this.getSpecClassCd()).append("specClassName", this.getSpecClassName()).append("specDeterminerCd", this.getSpecDeterminerCd()).append("specDeterminerName", this.getSpecDeterminerName()).append("specReq", this.getSpecReq()).append("sort", this.getSort()).append("enabled", this.getEnabled()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
