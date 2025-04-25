package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderOperationSign extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "患者id"
   )
   private String patinetId;
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
   @Excel(
      name = "操作类型(1,审核；2,处理; 3,执行; 4,停止; 5,停止确认; 9,取消;7,取消确认;11.执行确认;12:作废;13:作废恢复)"
   )
   private Integer operationType;
   @Excel(
      name = "操作类型名称"
   )
   private String operationName;
   @Excel(
      name = "操作员"
   )
   private String operatorCode;
   @Excel(
      name = "操作员姓名"
   )
   private String operatorName;
   @Excel(
      name = "加密方式签名源SQL"
   )
   private String encryptionType;
   @Excel(
      name = "加密后密文"
   )
   private String encryptedInfo;
   @Excel(
      name = "签名证书"
   )
   private String signCertificate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date signTime;
   @Excel(
      name = "签名明文"
   )
   private String signedText;
   @Excel(
      name = "时间戳"
   )
   private String sjc;
   @Excel(
      name = "组合内容"
   )
   private String yzzhNr;
   @Excel(
      name = "组合方式"
   )
   private String yzzhFs;
   @Excel(
      name = ""
   )
   private String column16;

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

   public void setPatinetId(String patinetId) {
      this.patinetId = patinetId;
   }

   public String getPatinetId() {
      return this.patinetId;
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

   public Integer getOperationType() {
      return this.operationType;
   }

   public void setOperationType(Integer operationType) {
      this.operationType = operationType;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setEncryptionType(String encryptionType) {
      this.encryptionType = encryptionType;
   }

   public String getEncryptionType() {
      return this.encryptionType;
   }

   public void setEncryptedInfo(String encryptedInfo) {
      this.encryptedInfo = encryptedInfo;
   }

   public String getEncryptedInfo() {
      return this.encryptedInfo;
   }

   public void setSignCertificate(String signCertificate) {
      this.signCertificate = signCertificate;
   }

   public String getSignCertificate() {
      return this.signCertificate;
   }

   public void setSignTime(Date signTime) {
      this.signTime = signTime;
   }

   public Date getSignTime() {
      return this.signTime;
   }

   public void setSignedText(String signedText) {
      this.signedText = signedText;
   }

   public String getSignedText() {
      return this.signedText;
   }

   public void setSjc(String sjc) {
      this.sjc = sjc;
   }

   public String getSjc() {
      return this.sjc;
   }

   public void setYzzhNr(String yzzhNr) {
      this.yzzhNr = yzzhNr;
   }

   public String getYzzhNr() {
      return this.yzzhNr;
   }

   public void setYzzhFs(String yzzhFs) {
      this.yzzhFs = yzzhFs;
   }

   public String getYzzhFs() {
      return this.yzzhFs;
   }

   public void setColumn16(String column16) {
      this.column16 = column16;
   }

   public String getColumn16() {
      return this.column16;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("admissionNo", this.getAdmissionNo()).append("patinetId", this.getPatinetId()).append("orderNo", this.getOrderNo()).append("orderGroupNo", this.getOrderGroupNo()).append("orderSortNumber", this.getOrderSortNumber()).append("operatorCode", this.getOperatorCode()).append("operatorName", this.getOperatorName()).append("encryptionType", this.getEncryptionType()).append("encryptedInfo", this.getEncryptedInfo()).append("signCertificate", this.getSignCertificate()).append("signTime", this.getSignTime()).append("signedText", this.getSignedText()).append("sjc", this.getSjc()).append("yzzhNr", this.getYzzhNr()).append("yzzhFs", this.getYzzhFs()).append("column16", this.getColumn16()).toString();
   }
}
