package com.emr.project.dr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrHandoverScheme extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long schemeCd;
   @Excel(
      name = "方案名称"
   )
   private String schemeName;
   @Excel(
      name = "开始时间"
   )
   private String schemeBegin;
   @Excel(
      name = "开始时间跨天标志"
   )
   private Integer schemeBeginDayFlag;
   @Excel(
      name = "结束时间"
   )
   private String schemeEnd;
   @Excel(
      name = "结束时间跨天标志"
   )
   private Integer schemeEndDayFlag;
   @Excel(
      name = "启用标志"
   )
   private String enabled;
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

   public Integer getSchemeBeginDayFlag() {
      return this.schemeBeginDayFlag;
   }

   public void setSchemeBeginDayFlag(Integer schemeBeginDayFlag) {
      this.schemeBeginDayFlag = schemeBeginDayFlag;
   }

   public Integer getSchemeEndDayFlag() {
      return this.schemeEndDayFlag;
   }

   public void setSchemeEndDayFlag(Integer schemeEndDayFlag) {
      this.schemeEndDayFlag = schemeEndDayFlag;
   }

   public void setSchemeCd(Long schemeCd) {
      this.schemeCd = schemeCd;
   }

   public Long getSchemeCd() {
      return this.schemeCd;
   }

   public void setSchemeName(String schemeName) {
      this.schemeName = schemeName;
   }

   public String getSchemeName() {
      return this.schemeName;
   }

   public void setSchemeBegin(String schemeBegin) {
      this.schemeBegin = schemeBegin;
   }

   public String getSchemeBegin() {
      return this.schemeBegin;
   }

   public void setSchemeEnd(String schemeEnd) {
      this.schemeEnd = schemeEnd;
   }

   public String getSchemeEnd() {
      return this.schemeEnd;
   }

   public void setEnabled(String enabled) {
      this.enabled = enabled;
   }

   public String getEnabled() {
      return this.enabled;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("schemeCd", this.getSchemeCd()).append("schemeName", this.getSchemeName()).append("schemeBegin", this.getSchemeBegin()).append("schemeEnd", this.getSchemeEnd()).append("enabled", this.getEnabled()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).toString();
   }
}
