package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpIcu extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String recordId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "序号"
   )
   private Long icuNo;
   @Excel(
      name = "ICU科室编码"
   )
   private String icuCode;
   @Excel(
      name = "ICU科室名称"
   )
   private String icuName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "进重症监护室时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date icuInTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出重症监护室时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date icuOutTime;
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
   private String icuId;
   private BigDecimal icuHour;

   public BigDecimal getIcuHour() {
      return this.icuHour;
   }

   public void setIcuHour(BigDecimal icuHour) {
      this.icuHour = icuHour;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setIcuNo(Long icuNo) {
      this.icuNo = icuNo;
   }

   public Long getIcuNo() {
      return this.icuNo;
   }

   public void setIcuCode(String icuCode) {
      this.icuCode = icuCode;
   }

   public String getIcuCode() {
      return this.icuCode;
   }

   public void setIcuName(String icuName) {
      this.icuName = icuName;
   }

   public String getIcuName() {
      return this.icuName;
   }

   public void setIcuInTime(Date icuInTime) {
      this.icuInTime = icuInTime;
   }

   public Date getIcuInTime() {
      return this.icuInTime;
   }

   public void setIcuOutTime(Date icuOutTime) {
      this.icuOutTime = icuOutTime;
   }

   public Date getIcuOutTime() {
      return this.icuOutTime;
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

   public void setIcuId(String icuId) {
      this.icuId = icuId;
   }

   public String getIcuId() {
      return this.icuId;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("recordId", this.getRecordId()).append("patientId", this.getPatientId()).append("icuNo", this.getIcuNo()).append("icuCode", this.getIcuCode()).append("icuName", this.getIcuName()).append("icuInTime", this.getIcuInTime()).append("icuOutTime", this.getIcuOutTime()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("icuId", this.getIcuId()).append("icuHour", this.getIcuHour()).toString();
   }
}
