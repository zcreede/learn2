package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmplElemLinkElem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "联动元素配置主表id"
   )
   private Long linkId;
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
   @Excel(
      name = "联动类型(1同步元素 2显示元素 3隐藏元素)"
   )
   private String linkType;
   private String contType;
   private String typeFlag;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setLinkId(Long linkId) {
      this.linkId = linkId;
   }

   public Long getLinkId() {
      return this.linkId;
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

   public String getLinkType() {
      return this.linkType;
   }

   public void setLinkType(String linkType) {
      this.linkType = linkType;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("linkId", this.getLinkId()).append("contElemName", this.getContElemName()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
