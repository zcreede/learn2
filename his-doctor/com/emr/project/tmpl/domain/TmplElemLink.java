package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmplElemLink extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "模板ID"
   )
   private Long tempId;
   private List tempIdList;
   @Excel(
      name = "模板名称"
   )
   private String tempName;
   @Excel(
      name = "控件元素名称   在模板中的控件名称 用于识别在模板中的标识"
   )
   private String contElemName;
   @Excel(
      name = "元素ID   在元素管理表的ID"
   )
   private Long elemId;
   @Excel(
      name = "元素名称  在元素管理表的名称"
   )
   private String elemName;
   @Excel(
      name = "元素编码 在元素管理表的编码"
   )
   private String elemCd;
   @Excel(
      name = "隐藏元素条件内容"
   )
   private String conditionsContent2;
   @Excel(
      name = "显示元素条件内容"
   )
   private String conditionsContent;
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
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   private String contType;
   private String typeFlag;

   public List getTempIdList() {
      return this.tempIdList;
   }

   public void setTempIdList(List tempIdList) {
      this.tempIdList = tempIdList;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
   }

   public Long getTempId() {
      return this.tempId;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public String getConditionsContent2() {
      return this.conditionsContent2;
   }

   public void setConditionsContent2(String conditionsContent2) {
      this.conditionsContent2 = conditionsContent2;
   }

   public String getConditionsContent() {
      return this.conditionsContent;
   }

   public void setConditionsContent(String conditionsContent) {
      this.conditionsContent = conditionsContent;
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

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public String getContType() {
      return this.contType;
   }

   public void setContType(String contType) {
      this.contType = contType;
   }

   public String getTypeFlag() {
      return this.typeFlag;
   }

   public void setTypeFlag(String typeFlag) {
      this.typeFlag = typeFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempId", this.getTempId()).append("tempName", this.getTempName()).append("contElemName", this.getContElemName()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("conditionsContent2", this.getConditionsContent2()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
