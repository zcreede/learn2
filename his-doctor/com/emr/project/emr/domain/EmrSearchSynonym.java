package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSearchSynonym extends BaseEntity {
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
      name = "同义词1"
   )
   private String synonymS;
   @Excel(
      name = "同义词2"
   )
   private String synonymT;
   @Excel(
      name = "是否系统内置 1系统内置 0个人"
   )
   private Integer typeFlag;
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
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;

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

   public void setSynonymS(String synonymS) {
      this.synonymS = synonymS;
   }

   public String getSynonymS() {
      return this.synonymS;
   }

   public void setSynonymT(String synonymT) {
      this.synonymT = synonymT;
   }

   public String getSynonymT() {
      return this.synonymT;
   }

   public void setTypeFlag(Integer typeFlag) {
      this.typeFlag = typeFlag;
   }

   public Integer getTypeFlag() {
      return this.typeFlag;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("emplNumber", this.getEmplNumber()).append("emplName", this.getEmplName()).append("synonymS", this.getSynonymS()).append("synonymT", this.getSynonymT()).append("typeFlag", this.getTypeFlag()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
