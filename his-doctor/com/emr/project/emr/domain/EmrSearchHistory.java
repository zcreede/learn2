package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSearchHistory extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
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
   @JsonFormat(
      pattern = "MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "检索时间",
      width = (double)30.0F,
      dateFormat = "MM-dd HH:mm"
   )
   private Date searchTime;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date creDate;
   private String keyWords;

   public String getKeyWords() {
      return this.keyWords;
   }

   public void setKeyWords(String keyWords) {
      this.keyWords = keyWords;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
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

   public void setSearchTime(Date searchTime) {
      this.searchTime = searchTime;
   }

   public Date getSearchTime() {
      return this.searchTime;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("emplNumber", this.getEmplNumber()).append("emplName", this.getEmplName()).append("mrNum", this.getMrNum()).append("caseDesc", this.getCaseDesc()).append("searchTime", this.getSearchTime()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).toString();
   }
}
