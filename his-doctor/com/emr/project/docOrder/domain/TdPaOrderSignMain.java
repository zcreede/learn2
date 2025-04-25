package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderSignMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "签名证书"
   )
   private String signCertificate;
   @Excel(
      name = "加密方式签名源SQL(select 加密字段1,加密字段2 from 表 where  条件）"
   )
   private String encryptionType;
   @Excel(
      name = "签名方式(字段组合,后面已分号结束），如住院号,医嘱编号,医嘱名称;"
   )
   private String signMode;
   @Excel(
      name = "签名明文"
   )
   private String signedText;
   @Excel(
      name = "加密后密文"
   )
   private String encryptionInfo;
   @Excel(
      name = "签名时间戳密文"
   )
   private String signTimestamp;
   @Excel(
      name = "签名描述(单条签名还是打包签名等备注说明）"
   )
   private String signDesc;
   @Excel(
      name = "操作类型"
   )
   private String operationType;
   @Excel(
      name = "操作名称"
   )
   private String operationName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date signTime;
   @Excel(
      name = "操作员"
   )
   private String operatorCode;
   @Excel(
      name = "操作员姓名"
   )
   private String operatorName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSignCertificate(String signCertificate) {
      this.signCertificate = signCertificate;
   }

   public String getSignCertificate() {
      return this.signCertificate;
   }

   public void setEncryptionType(String encryptionType) {
      this.encryptionType = encryptionType;
   }

   public String getEncryptionType() {
      return this.encryptionType;
   }

   public void setSignMode(String signMode) {
      this.signMode = signMode;
   }

   public String getSignMode() {
      return this.signMode;
   }

   public void setSignedText(String signedText) {
      this.signedText = signedText;
   }

   public String getSignedText() {
      return this.signedText;
   }

   public void setEncryptionInfo(String encryptionInfo) {
      this.encryptionInfo = encryptionInfo;
   }

   public String getEncryptionInfo() {
      return this.encryptionInfo;
   }

   public void setSignTimestamp(String signTimestamp) {
      this.signTimestamp = signTimestamp;
   }

   public String getSignTimestamp() {
      return this.signTimestamp;
   }

   public void setSignDesc(String signDesc) {
      this.signDesc = signDesc;
   }

   public String getSignDesc() {
      return this.signDesc;
   }

   public void setOperationType(String operationType) {
      this.operationType = operationType;
   }

   public String getOperationType() {
      return this.operationType;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setSignTime(Date signTime) {
      this.signTime = signTime;
   }

   public Date getSignTime() {
      return this.signTime;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("signCertificate", this.getSignCertificate()).append("encryptionType", this.getEncryptionType()).append("signMode", this.getSignMode()).append("signedText", this.getSignedText()).append("encryptionInfo", this.getEncryptionInfo()).append("signTimestamp", this.getSignTimestamp()).append("signDesc", this.getSignDesc()).append("operationType", this.getOperationType()).append("operationName", this.getOperationName()).append("signTime", this.getSignTime()).append("operatorCode", this.getOperatorCode()).append("operatorName", this.getOperatorName()).toString();
   }
}
