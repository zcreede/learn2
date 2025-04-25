package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmrSignData extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "类别",
      readConverterExp = "1=.病历文件；2：病案首页,3=：住院诊断证明"
   )
   private String typeCd;
   @Excel(
      name = "数据类型",
      readConverterExp = "1=：数据；2：文件"
   )
   private String certType;
   @Excel(
      name = "签名文件ID",
      readConverterExp = "类=别为病历文件时，存病历索引ID；类别为病案首页时，存病案ID"
   )
   private String signFileId;
   @Excel(
      name = "签名人编码",
      readConverterExp = "签=名在业务系统中的用户编码"
   )
   private String signerCd;
   @Excel(
      name = "签名人姓名"
   )
   private String signerName;
   @Excel(
      name = "原文",
      readConverterExp = "类=别为病历文件时，存XML格式，有4个结点（病历文字内容，签名人编码，签名人姓名，签名日期"
   )
   private String oldText;
   @Excel(
      name = "签名值"
   )
   private String signText;
   @Excel(
      name = "签名文件地址"
   )
   private String fileUrl;
   @Excel(
      name = "时间戳"
   )
   private String timeStamp;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "进行电子签名时的公元纪年日期和时间的完整描述",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date signTime;
   @Excel(
      name = "证书信息"
   )
   private String certInfo;
   @Excel(
      name = "证书序列号"
   )
   private String certSn;
   @Excel(
      name = "有效标志",
      readConverterExp = "1=：有效；0：无效"
   )
   private String isValid;
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
   private List signerCdList;
   private Long signPersonId;
   private String signPersonType;

   public Long getSignPersonId() {
      return this.signPersonId;
   }

   public void setSignPersonId(Long signPersonId) {
      this.signPersonId = signPersonId;
   }

   public String getSignPersonType() {
      return this.signPersonType;
   }

   public void setSignPersonType(String signPersonType) {
      this.signPersonType = signPersonType;
   }

   public List getSignerCdList() {
      return this.signerCdList;
   }

   public void setSignerCdList(List signerCdList) {
      this.signerCdList = signerCdList;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTypeCd(String typeCd) {
      this.typeCd = typeCd;
   }

   public String getTypeCd() {
      return this.typeCd;
   }

   public void setCertType(String certType) {
      this.certType = certType;
   }

   public String getCertType() {
      return this.certType;
   }

   public void setSignFileId(String signFileId) {
      this.signFileId = signFileId;
   }

   public String getSignFileId() {
      return this.signFileId;
   }

   public void setSignerCd(String signerCd) {
      this.signerCd = signerCd;
   }

   public String getSignerCd() {
      return this.signerCd;
   }

   public void setSignerName(String signerName) {
      this.signerName = signerName;
   }

   public String getSignerName() {
      return this.signerName;
   }

   public void setOldText(String oldText) {
      this.oldText = oldText;
   }

   public String getOldText() {
      return this.oldText;
   }

   public void setSignText(String signText) {
      this.signText = signText;
   }

   public String getSignText() {
      return this.signText;
   }

   public void setFileUrl(String fileUrl) {
      this.fileUrl = fileUrl;
   }

   public String getFileUrl() {
      return this.fileUrl;
   }

   public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
   }

   public String getTimeStamp() {
      return this.timeStamp;
   }

   public void setSignTime(Date signTime) {
      this.signTime = signTime;
   }

   public Date getSignTime() {
      return this.signTime;
   }

   public void setCertInfo(String certInfo) {
      this.certInfo = certInfo;
   }

   public String getCertInfo() {
      return this.certInfo;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setIsValid(String isValid) {
      this.isValid = isValid;
   }

   public String getIsValid() {
      return this.isValid;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("typeCd", this.getTypeCd()).append("certType", this.getCertType()).append("signFileId", this.getSignFileId()).append("signerCd", this.getSignerCd()).append("signerName", this.getSignerName()).append("oldText", this.getOldText()).append("signText", this.getSignText()).append("fileUrl", this.getFileUrl()).append("timeStamp", this.getTimeStamp()).append("signTime", this.getSignTime()).append("certInfo", this.getCertInfo()).append("certSn", this.getCertSn()).append("isValid", this.getIsValid()).append("remark", this.getRemark()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
