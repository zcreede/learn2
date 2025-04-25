package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverQuotElem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者标识编码"
   )
   private Long typeCode;
   @Excel(
      name = "患者标识名称"
   )
   private String typeName;
   @Excel(
      name = "来源元素ID"
   )
   private Long fromElemId;
   @Excel(
      name = "来源元素名称"
   )
   private String fromElemName;
   @Excel(
      name = "来源元素编码"
   )
   private String fromElemCd;
   @Excel(
      name = "来源病历类型编码"
   )
   private String fromMrTypeCd;
   @Excel(
      name = "来源病历类型名称"
   )
   private String fromMrTypeName;
   @Excel(
      name = "元素提取顺序"
   )
   private Integer elemOrder;
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

   public void setTypeCode(Long typeCode) {
      this.typeCode = typeCode;
   }

   public Long getTypeCode() {
      return this.typeCode;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public void setFromElemId(Long fromElemId) {
      this.fromElemId = fromElemId;
   }

   public Long getFromElemId() {
      return this.fromElemId;
   }

   public void setFromElemName(String fromElemName) {
      this.fromElemName = fromElemName;
   }

   public String getFromElemName() {
      return this.fromElemName;
   }

   public String getFromElemCd() {
      return this.fromElemCd;
   }

   public void setFromElemCd(String fromElemCd) {
      this.fromElemCd = fromElemCd;
   }

   public void setFromMrTypeCd(String fromMrTypeCd) {
      this.fromMrTypeCd = fromMrTypeCd;
   }

   public String getFromMrTypeCd() {
      return this.fromMrTypeCd;
   }

   public void setFromMrTypeName(String fromMrTypeName) {
      this.fromMrTypeName = fromMrTypeName;
   }

   public String getFromMrTypeName() {
      return this.fromMrTypeName;
   }

   public void setElemOrder(Integer elemOrder) {
      this.elemOrder = elemOrder;
   }

   public Integer getElemOrder() {
      return this.elemOrder;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("typeCode", this.getTypeCode()).append("typeName", this.getTypeName()).append("fromElemId", this.getFromElemId()).append("fromElemName", this.getFromElemName()).append("fromMrTypeCd", this.getFromMrTypeCd()).append("fromMrTypeName", this.getFromMrTypeName()).append("elemOrder", this.getElemOrder()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
