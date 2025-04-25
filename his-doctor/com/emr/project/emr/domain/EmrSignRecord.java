package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSignRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病历文件索引ID"
   )
   private Long mrFileId;
   @Excel(
      name = "签名数据ID"
   )
   private Long signDataId;
   @Excel(
      name = "医生级别编码",
      readConverterExp = "医=生职称"
   )
   private String docLevelCd;
   @Excel(
      name = "医生级别名称"
   )
   private String docLevelName;
   @Excel(
      name = "医师编码"
   )
   private String docCode;
   @Excel(
      name = "医师姓名"
   )
   private String docName;
   @Excel(
      name = "签名位置",
      readConverterExp = "签名位置（元素id）"
   )
   private String signPos;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "签名日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date signTime;
   @Excel(
      name = "0：签名；1：取消签名；"
   )
   private String signCancFlag;
   @Excel(
      name = "取消签名原因"
   )
   private String signCancReas;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "取消签名时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date signCancTime;
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
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
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
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;
   @Excel(
      name = "签名图片位置(签名图片在病历控件中的名称)"
   )
   private String signImagePos;
   @Excel(
      name = "签名元素数据组在病历控件中的名称"
   )
   private String signSname;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date emrUpdateTime;

   public Date getEmrUpdateTime() {
      return this.emrUpdateTime;
   }

   public void setEmrUpdateTime(Date emrUpdateTime) {
      this.emrUpdateTime = emrUpdateTime;
   }

   public String getSignImagePos() {
      return this.signImagePos;
   }

   public void setSignImagePos(String signImagePos) {
      this.signImagePos = signImagePos;
   }

   public String getSignSname() {
      return this.signSname;
   }

   public void setSignSname(String signSname) {
      this.signSname = signSname;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setSignDataId(Long signDataId) {
      this.signDataId = signDataId;
   }

   public Long getSignDataId() {
      return this.signDataId;
   }

   public void setDocLevelCd(String docLevelCd) {
      this.docLevelCd = docLevelCd;
   }

   public String getDocLevelCd() {
      return this.docLevelCd;
   }

   public void setDocLevelName(String docLevelName) {
      this.docLevelName = docLevelName;
   }

   public String getDocLevelName() {
      return this.docLevelName;
   }

   public void setDocCode(String docCode) {
      this.docCode = docCode;
   }

   public String getDocCode() {
      return this.docCode;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setSignPos(String signPos) {
      this.signPos = signPos;
   }

   public String getSignPos() {
      return this.signPos;
   }

   public void setSignTime(Date signTime) {
      this.signTime = signTime;
   }

   public Date getSignTime() {
      return this.signTime;
   }

   public void setSignCancFlag(String signCancFlag) {
      this.signCancFlag = signCancFlag;
   }

   public String getSignCancFlag() {
      return this.signCancFlag;
   }

   public void setSignCancReas(String signCancReas) {
      this.signCancReas = signCancReas;
   }

   public String getSignCancReas() {
      return this.signCancReas;
   }

   public void setSignCancTime(Date signCancTime) {
      this.signCancTime = signCancTime;
   }

   public Date getSignCancTime() {
      return this.signCancTime;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("mrFileId", this.getMrFileId()).append("signDataId", this.getSignDataId()).append("docLevelCd", this.getDocLevelCd()).append("docLevelName", this.getDocLevelName()).append("docCode", this.getDocCode()).append("docName", this.getDocName()).append("signPos", this.getSignPos()).append("signTime", this.getSignTime()).append("signCancFlag", this.getSignCancFlag()).append("signCancReas", this.getSignCancReas()).append("signCancTime", this.getSignCancTime()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
