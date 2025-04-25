package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPmNotice extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "公告标题"
   )
   private String title;
   @Excel(
      name = "公告内容"
   )
   private String content;
   @Excel(
      name = "公告类型 0：全院；1：按科室；2：按医师；3：按子系统"
   )
   private String noticeType;
   @Excel(
      name = "发布科室编码"
   )
   private String creDeptCd;
   @Excel(
      name = "发布科室名称"
   )
   private String creDeptName;
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
   private String docCd;
   private String deptCode;

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return this.title;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getContent() {
      return this.content;
   }

   public void setNoticeType(String noticeType) {
      this.noticeType = noticeType;
   }

   public String getNoticeType() {
      return this.noticeType;
   }

   public void setCreDeptCd(String creDeptCd) {
      this.creDeptCd = creDeptCd;
   }

   public String getCreDeptCd() {
      return this.creDeptCd;
   }

   public void setCreDeptName(String creDeptName) {
      this.creDeptName = creDeptName;
   }

   public String getCreDeptName() {
      return this.creDeptName;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("title", this.getTitle()).append("content", this.getContent()).append("noticeType", this.getNoticeType()).append("creDeptCd", this.getCreDeptCd()).append("creDeptName", this.getCreDeptName()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
