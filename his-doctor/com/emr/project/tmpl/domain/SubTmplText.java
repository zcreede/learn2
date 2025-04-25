package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SubTmplText extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "子模板类型",
      readConverterExp = "1=.个人2.科室"
   )
   private String subTempType;
   @Excel(
      name = "科室code/医师code"
   )
   private String subTempObject;
   @Excel(
      name = "子模板名称"
   )
   private String subTempName;
   @Excel(
      name = "拼音码"
   )
   private String inputstrphtc;
   @Excel(
      name = "文本"
   )
   private String subTempText;
   @Excel(
      name = "创建人"
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
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSubTempType(String subTempType) {
      this.subTempType = subTempType;
   }

   public String getSubTempType() {
      return this.subTempType;
   }

   public void setSubTempObject(String subTempObject) {
      this.subTempObject = subTempObject;
   }

   public String getSubTempObject() {
      return this.subTempObject;
   }

   public void setSubTempName(String subTempName) {
      this.subTempName = subTempName;
   }

   public String getSubTempName() {
      return this.subTempName;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setSubTempText(String subTempText) {
      this.subTempText = subTempText;
   }

   public String getSubTempText() {
      return this.subTempText;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("subTempType", this.getSubTempType()).append("subTempObject", this.getSubTempObject()).append("subTempName", this.getSubTempName()).append("inputstrphtc", this.getInputstrphtc()).append("subTempText", this.getSubTempText()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).toString();
   }
}
