package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderAgent extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "诊断编码"
   )
   private String diagCd;
   @Excel(
      name = "诊断名称"
   )
   private String diagName;
   @Excel(
      name = "处方类型编码"
   )
   private String prescriptionCd;
   @Excel(
      name = "处方类型名称"
   )
   private String prescriptionName;
   @Excel(
      name = "代办人姓名"
   )
   private String agentName;
   @Excel(
      name = "代办人身份证号"
   )
   private String agentIdCard;
   @Excel(
      name = "代办人年龄"
   )
   private String agentAge;
   @Excel(
      name = "代办人性别"
   )
   private String agentSex;
   @Excel(
      name = "代办人性别编码"
   )
   private String agentSexCd;
   @Excel(
      name = "代办人与患者关系编码"
   )
   private String relaCd;
   @Excel(
      name = "代办人与患者关系名称"
   )
   private String relaName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人姓名"
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
   @Excel(
      name = "医嘱编号"
   )
   private String admissionNo;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "序号"
   )
   private String orderGroupSortNumber;
   @Excel(
      name = "组号"
   )
   private Integer orderGroupNo;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setDiagCd(String diagCd) {
      this.diagCd = diagCd;
   }

   public String getDiagCd() {
      return this.diagCd;
   }

   public void setDiagName(String diagName) {
      this.diagName = diagName;
   }

   public String getDiagName() {
      return this.diagName;
   }

   public void setPrescriptionCd(String prescriptionCd) {
      this.prescriptionCd = prescriptionCd;
   }

   public String getPrescriptionCd() {
      return this.prescriptionCd;
   }

   public void setPrescriptionName(String prescriptionName) {
      this.prescriptionName = prescriptionName;
   }

   public String getPrescriptionName() {
      return this.prescriptionName;
   }

   public void setAgentName(String agentName) {
      this.agentName = agentName;
   }

   public String getAgentName() {
      return this.agentName;
   }

   public void setAgentIdCard(String agentIdCard) {
      this.agentIdCard = agentIdCard;
   }

   public String getAgentIdCard() {
      return this.agentIdCard;
   }

   public void setAgentAge(String agentAge) {
      this.agentAge = agentAge;
   }

   public String getAgentAge() {
      return this.agentAge;
   }

   public void setAgentSex(String agentSex) {
      this.agentSex = agentSex;
   }

   public String getAgentSex() {
      return this.agentSex;
   }

   public void setAgentSexCd(String agentSexCd) {
      this.agentSexCd = agentSexCd;
   }

   public String getAgentSexCd() {
      return this.agentSexCd;
   }

   public void setRelaCd(String relaCd) {
      this.relaCd = relaCd;
   }

   public String getRelaCd() {
      return this.relaCd;
   }

   public void setRelaName(String relaName) {
      this.relaName = relaName;
   }

   public String getRelaName() {
      return this.relaName;
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

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("diagCd", this.getDiagCd()).append("diagName", this.getDiagName()).append("prescriptionCd", this.getPrescriptionCd()).append("prescriptionName", this.getPrescriptionName()).append("agentName", this.getAgentName()).append("agentIdCard", this.getAgentIdCard()).append("agentAge", this.getAgentAge()).append("agentSex", this.getAgentSex()).append("agentSexCd", this.getAgentSexCd()).append("relaCd", this.getRelaCd()).append("relaName", this.getRelaName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).toString();
   }
}
