package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysShareElem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病历类型编码"
   )
   private String tempType;
   @Excel(
      name = "元素ID   元素管理表中的ID"
   )
   private Long elemId;
   @Excel(
      name = "元素名称  元素管理表中的名称"
   )
   private String elemName;
   @Excel(
      name = "元素编码  元素管理表中的编码"
   )
   private String elemCd;
   @Excel(
      name = "有效标志"
   )
   private String isValid;
   @Excel(
      name = "创建者"
   )
   private String creatorid;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private String createtime;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTempType(String tempType) {
      this.tempType = tempType;
   }

   public String getTempType() {
      return this.tempType;
   }

   public void setElemId(Long elemId) {
      this.elemId = elemId;
   }

   public Long getElemId() {
      return this.elemId;
   }

   public void setElemName(String elemName) {
      this.elemName = elemName;
   }

   public String getElemName() {
      return this.elemName;
   }

   public void setElemCd(String elemCd) {
      this.elemCd = elemCd;
   }

   public String getElemCd() {
      return this.elemCd;
   }

   public void setIsValid(String isValid) {
      this.isValid = isValid;
   }

   public String getIsValid() {
      return this.isValid;
   }

   public void setCreatorid(String creatorid) {
      this.creatorid = creatorid;
   }

   public String getCreatorid() {
      return this.creatorid;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getCreatetime() {
      return this.createtime;
   }

   public void setCreatetime(String createtime) {
      this.createtime = createtime;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tempType", this.getTempType()).append("elemId", this.getElemId()).append("elemName", this.getElemName()).append("elemCd", this.getElemCd()).append("isValid", this.getIsValid()).append("creatorid", this.getCreatorid()).append("createtime", this.getCreatetime()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).toString();
   }
}
