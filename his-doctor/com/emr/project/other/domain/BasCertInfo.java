package com.emr.project.other.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BasCertInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private String certSn;
   @Excel(
      name = "证书信息"
   )
   private String certInfo;
   @Excel(
      name = "证书登录密码"
   )
   private String certPsw;
   @Excel(
      name = "签章图片"
   )
   private String certPic;
   @Excel(
      name = "职员编号"
   )
   private String employeenumber;
   @Excel(
      name = "职员姓名"
   )
   private String employeename;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "有效开始日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date begDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "有效结束日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date endDate;
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
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
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
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date altDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setCertInfo(String certInfo) {
      this.certInfo = certInfo;
   }

   public String getCertInfo() {
      return this.certInfo;
   }

   public void setCertPsw(String certPsw) {
      this.certPsw = certPsw;
   }

   public String getCertPsw() {
      return this.certPsw;
   }

   public void setCertPic(String certPic) {
      this.certPic = certPic;
   }

   public String getCertPic() {
      return this.certPic;
   }

   public void setEmployeenumber(String employeenumber) {
      this.employeenumber = employeenumber;
   }

   public String getEmployeenumber() {
      return this.employeenumber;
   }

   public void setEmployeename(String employeename) {
      this.employeename = employeename;
   }

   public String getEmployeename() {
      return this.employeename;
   }

   public void setBegDate(Date begDate) {
      this.begDate = begDate;
   }

   public Date getBegDate() {
      return this.begDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getEndDate() {
      return this.endDate;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("certSn", this.getCertSn()).append("certInfo", this.getCertInfo()).append("certPsw", this.getCertPsw()).append("certPic", this.getCertPic()).append("employeenumber", this.getEmployeenumber()).append("employeename", this.getEmployeename()).append("begDate", this.getBegDate()).append("endDate", this.getEndDate()).append("isValid", this.getIsValid()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
