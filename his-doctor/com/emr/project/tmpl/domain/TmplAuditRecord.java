package com.emr.project.tmpl.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmplAuditRecord extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "模板ID"
   )
   private Long tmplId;
   @Excel(
      name = "申请科室编码"
   )
   private String appDeptCd;
   @Excel(
      name = "申请科室名称"
   )
   private String appDeptName;
   @Excel(
      name = "申请医生编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请医生名称"
   )
   private String appDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date appTime;
   @Excel(
      name = "审核科室编码"
   )
   private String conDeptCd;
   @Excel(
      name = "审核科室名称"
   )
   private String conDeptName;
   @Excel(
      name = "审核医师编码"
   )
   private String conDocCd;
   @Excel(
      name = "审核医师签名"
   )
   private String conDocName;
   @Excel(
      name = "审核状态：2：驳回；4：审核通过"
   )
   private Long auditResult;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "审核时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   private Date conDate;
   @Excel(
      name = "审核意见"
   )
   private String conView;
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

   public void setTmplId(Long tmplId) {
      this.tmplId = tmplId;
   }

   public Long getTmplId() {
      return this.tmplId;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setConDeptCd(String conDeptCd) {
      this.conDeptCd = conDeptCd;
   }

   public String getConDeptCd() {
      return this.conDeptCd;
   }

   public void setConDeptName(String conDeptName) {
      this.conDeptName = conDeptName;
   }

   public String getConDeptName() {
      return this.conDeptName;
   }

   public void setConDocCd(String conDocCd) {
      this.conDocCd = conDocCd;
   }

   public String getConDocCd() {
      return this.conDocCd;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setAuditResult(Long auditResult) {
      this.auditResult = auditResult;
   }

   public Long getAuditResult() {
      return this.auditResult;
   }

   public void setConDate(Date conDate) {
      this.conDate = conDate;
   }

   public Date getConDate() {
      return this.conDate;
   }

   public void setConView(String conView) {
      this.conView = conView;
   }

   public String getConView() {
      return this.conView;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("tmplId", this.getTmplId()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("appTime", this.getAppTime()).append("conDeptCd", this.getConDeptCd()).append("conDeptName", this.getConDeptName()).append("conDocCd", this.getConDocCd()).append("conDocName", this.getConDocName()).append("auditResult", this.getAuditResult()).append("conDate", this.getConDate()).append("conView", this.getConView()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
