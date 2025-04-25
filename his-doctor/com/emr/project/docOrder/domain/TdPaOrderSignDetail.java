package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderSignDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "患者id"
   )
   private String patientId;
   @Excel(
      name = "签名id",
      readConverterExp = "主=表id"
   )
   private Long signId;
   @Excel(
      name = "医嘱编码"
   )
   private String orderNo;
   @Excel(
      name = "医嘱组号"
   )
   private Integer orderGroupNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setSignId(Long signId) {
      this.signId = signId;
   }

   public Long getSignId() {
      return this.signId;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("admissionNo", this.getAdmissionNo()).append("patientId", this.getPatientId()).append("signId", this.getSignId()).append("orderNo", this.getOrderNo()).append("orderGroupNo", this.getOrderGroupNo()).append("orderSortNumber", this.getOrderSortNumber()).toString();
   }
}
