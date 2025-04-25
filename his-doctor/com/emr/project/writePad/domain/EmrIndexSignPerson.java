package com.emr.project.writePad.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrIndexSignPerson extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "患者住院号"
   )
   private String admissionNo;
   @Excel(
      name = "患者姓名"
   )
   private String name;
   @Excel(
      name = "与患者关系编码"
   )
   private String relationCode;
   @Excel(
      name = "与患者关系"
   )
   private String relationName;
   @Excel(
      name = "亲属人姓名"
   )
   private String kinsfolk;
   @Excel(
      name = "证件类型编码"
   )
   private String cardCode;
   @Excel(
      name = "证件类型"
   )
   private String cardName;
   @Excel(
      name = "证件号码"
   )
   private String idCard;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   private String signPersonType;
   private String remark;
   private String relationCodeType;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;

   public String getSignPersonType() {
      return this.signPersonType;
   }

   public void setSignPersonType(String signPersonType) {
      this.signPersonType = signPersonType;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public String getRelationCodeType() {
      return this.relationCodeType;
   }

   public void setRelationCodeType(String relationCodeType) {
      this.relationCodeType = relationCodeType;
   }

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

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setRelationCode(String relationCode) {
      this.relationCode = relationCode;
   }

   public String getRelationCode() {
      return this.relationCode;
   }

   public void setRelationName(String relationName) {
      this.relationName = relationName;
   }

   public String getRelationName() {
      return this.relationName;
   }

   public void setKinsfolk(String kinsfolk) {
      this.kinsfolk = kinsfolk;
   }

   public String getKinsfolk() {
      return this.kinsfolk;
   }

   public void setCardCode(String cardCode) {
      this.cardCode = cardCode;
   }

   public String getCardCode() {
      return this.cardCode;
   }

   public void setCardName(String cardName) {
      this.cardName = cardName;
   }

   public String getCardName() {
      return this.cardName;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("admissionNo", this.getAdmissionNo()).append("name", this.getName()).append("relationCode", this.getRelationCode()).append("relationName", this.getRelationName()).append("kinsfolk", this.getKinsfolk()).append("cardCode", this.getCardCode()).append("cardName", this.getCardName()).append("idCard", this.getIdCard()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
