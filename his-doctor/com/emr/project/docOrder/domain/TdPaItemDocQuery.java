package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaItemDocQuery extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "机构编码"
   )
   private String orgCd;
   @Excel(
      name = "医师编码"
   )
   private String docCd;
   @Excel(
      name = "医师姓名"
   )
   private String docName;
   @Excel(
      name = "0.模糊查询 1.精确查询"
   )
   private String queryStatus;
   @Excel(
      name = "1.医嘱录入页面2.中草药录入页面"
   )
   private String orderFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;

   public TdPaItemDocQuery() {
   }

   public TdPaItemDocQuery(String orderFlag, String orgCd, String docCd) {
      this.orderFlag = orderFlag;
      this.orgCd = orgCd;
      this.docCd = docCd;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setQueryStatus(String queryStatus) {
      this.queryStatus = queryStatus;
   }

   public String getQueryStatus() {
      return this.queryStatus;
   }

   public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
   }

   public String getOrderFlag() {
      return this.orderFlag;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("docCd", this.getDocCd()).append("docName", this.getDocName()).append("queryStatus", this.getQueryStatus()).append("orderFlag", this.getOrderFlag()).append("altDate", this.getAltDate()).toString();
   }
}
