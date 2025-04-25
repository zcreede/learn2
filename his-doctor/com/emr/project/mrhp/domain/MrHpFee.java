package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpFee extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String feeId;
   @Excel(
      name = "病案ID"
   )
   private String recordId;
   @Excel(
      name = "费用编号"
   )
   private String feeCd;
   @Excel(
      name = "费用名称"
   )
   private String feeName;
   @Excel(
      name = "金额"
   )
   private Double amount;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Long visitId;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Long getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Long visitId) {
      this.visitId = visitId;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public void setFeeId(String feeId) {
      this.feeId = feeId;
   }

   public String getFeeId() {
      return this.feeId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setFeeCd(String feeCd) {
      this.feeCd = feeCd;
   }

   public String getFeeCd() {
      return this.feeCd;
   }

   public void setFeeName(String feeName) {
      this.feeName = feeName;
   }

   public String getFeeName() {
      return this.feeName;
   }

   public void setAmount(Double amount) {
      this.amount = amount;
   }

   public Double getAmount() {
      return this.amount;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("feeId", this.getFeeId()).append("recordId", this.getRecordId()).append("feeCd", this.getFeeCd()).append("feeName", this.getFeeName()).append("amount", this.getAmount()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("crePerName", this.getCrePerName()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).toString();
   }
}
