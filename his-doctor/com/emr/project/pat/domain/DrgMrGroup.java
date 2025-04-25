package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DrgMrGroup extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "DRG编码"
   )
   private String drgCode;
   @Excel(
      name = "DRG名称"
   )
   private String drgName;
   @Excel(
      name = "DRG版本"
   )
   private String drgVersion;
   @Excel(
      name = "权重"
   )
   private Long weight;
   @Excel(
      name = "支付标准"
   )
   private Double paymentStandard;
   @Excel(
      name = "是否入组"
   )
   private Long isIndrg;
   @Excel(
      name = "风险标识"
   )
   private String riskFlag;
   @Excel(
      name = "风险标识名称"
   )
   private String riskFlagName;
   @Excel(
      name = "未入组原因"
   )
   private String notDrgReason;
   private Long delFlag;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setDrgCode(String drgCode) {
      this.drgCode = drgCode;
   }

   public String getDrgCode() {
      return this.drgCode;
   }

   public void setDrgName(String drgName) {
      this.drgName = drgName;
   }

   public String getDrgName() {
      return this.drgName;
   }

   public void setDrgVersion(String drgVersion) {
      this.drgVersion = drgVersion;
   }

   public String getDrgVersion() {
      return this.drgVersion;
   }

   public void setWeight(Long weight) {
      this.weight = weight;
   }

   public Long getWeight() {
      return this.weight;
   }

   public void setPaymentStandard(Double paymentStandard) {
      this.paymentStandard = paymentStandard;
   }

   public Double getPaymentStandard() {
      return this.paymentStandard;
   }

   public void setIsIndrg(Long isIndrg) {
      this.isIndrg = isIndrg;
   }

   public Long getIsIndrg() {
      return this.isIndrg;
   }

   public void setRiskFlag(String riskFlag) {
      this.riskFlag = riskFlag;
   }

   public String getRiskFlag() {
      return this.riskFlag;
   }

   public void setRiskFlagName(String riskFlagName) {
      this.riskFlagName = riskFlagName;
   }

   public String getRiskFlagName() {
      return this.riskFlagName;
   }

   public void setNotDrgReason(String notDrgReason) {
      this.notDrgReason = notDrgReason;
   }

   public String getNotDrgReason() {
      return this.notDrgReason;
   }

   public void setDelFlag(Long delFlag) {
      this.delFlag = delFlag;
   }

   public Long getDelFlag() {
      return this.delFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("patientId", this.getPatientId()).append("drgCode", this.getDrgCode()).append("drgName", this.getDrgName()).append("drgVersion", this.getDrgVersion()).append("weight", this.getWeight()).append("paymentStandard", this.getPaymentStandard()).append("isIndrg", this.getIsIndrg()).append("riskFlag", this.getRiskFlag()).append("riskFlagName", this.getRiskFlagName()).append("notDrgReason", this.getNotDrgReason()).append("delFlag", this.getDelFlag()).append("updateTime", this.getUpdateTime()).append("updateBy", this.getUpdateBy()).append("createTime", this.getCreateTime()).append("createBy", this.getCreateBy()).toString();
   }
}
