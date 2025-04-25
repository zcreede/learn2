package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPmNoticeObject extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "公告ID"
   )
   private Long noticeId;
   @Excel(
      name = "对象编码"
   )
   private String objectCd;
   @Excel(
      name = "对象名称"
   )
   private String objectName;
   @Excel(
      name = "公告类型 0：全院；1：按科室；2：按医师；3：按子系统"
   )
   private String noticeType;
   @Excel(
      name = "发布人"
   )
   private String crePerCode;
   @Excel(
      name = "发布科室"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "发布时间",
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setNoticeId(Long noticeId) {
      this.noticeId = noticeId;
   }

   public Long getNoticeId() {
      return this.noticeId;
   }

   public void setObjectCd(String objectCd) {
      this.objectCd = objectCd;
   }

   public String getObjectCd() {
      return this.objectCd;
   }

   public void setObjectName(String objectName) {
      this.objectName = objectName;
   }

   public String getObjectName() {
      return this.objectName;
   }

   public void setNoticeType(String noticeType) {
      this.noticeType = noticeType;
   }

   public String getNoticeType() {
      return this.noticeType;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("noticeId", this.getNoticeId()).append("objectCd", this.getObjectCd()).append("objectName", this.getObjectName()).append("noticeType", this.getNoticeType()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
