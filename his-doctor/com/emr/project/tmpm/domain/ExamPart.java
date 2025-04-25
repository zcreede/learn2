package com.emr.project.tmpm.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ExamPart extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "检查部位编码"
   )
   private String examPartCd;
   @Excel(
      name = "检查部位名称"
   )
   private String examPartName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "部位归类编码"
   )
   private String partClassCd;
   @Excel(
      name = "部位归类名称"
   )
   private String partClassName;
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
   private String documentTypeNo;
   private String examSex;

   public String getExamSex() {
      return this.examSex;
   }

   public void setExamSex(String examSex) {
      this.examSex = examSex;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setPartClassCd(String partClassCd) {
      this.partClassCd = partClassCd;
   }

   public String getPartClassCd() {
      return this.partClassCd;
   }

   public void setPartClassName(String partClassName) {
      this.partClassName = partClassName;
   }

   public String getPartClassName() {
      return this.partClassName;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("examPartCd", this.getExamPartCd()).append("examPartName", this.getExamPartName()).append("inputstrphtc", this.getInputstrphtc()).append("partClassCd", this.getPartClassCd()).append("partClassName", this.getPartClassName()).append("sort", this.getSort()).append("enabled", this.getEnabled()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
