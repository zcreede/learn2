package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SyncDatasource extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "业务名称"
   )
   private String syncName;
   @Excel(
      name = "业务编码"
   )
   private String syncCode;
   @Excel(
      name = "数据来源"
   )
   private String syncSource;
   @Excel(
      name = "数据库类型"
   )
   private String databaseType;
   @Excel(
      name = "数据库URL"
   )
   private String databaseUrl;
   @Excel(
      name = "数据库驱动类名"
   )
   private String databaseDcn;
   @Excel(
      name = "用户名"
   )
   private String databaseUsername;
   @Excel(
      name = "密码"
   )
   private String databasePassword;
   @Excel(
      name = "数据库名称"
   )
   private String querySql;
   @Excel(
      name = "表或视图名称"
   )
   private String classMethod;
   @Excel(
      name = "有效标识"
   )
   private Long vaildFlag;
   private Long delFlag;

   public String getQuerySql() {
      return this.querySql;
   }

   public void setQuerySql(String querySql) {
      this.querySql = querySql;
   }

   public String getDatabaseType() {
      return this.databaseType;
   }

   public String getDatabaseUrl() {
      return this.databaseUrl;
   }

   public void setDatabaseUrl(String databaseUrl) {
      this.databaseUrl = databaseUrl;
   }

   public String getDatabaseDcn() {
      return this.databaseDcn;
   }

   public void setDatabaseDcn(String databaseDcn) {
      this.databaseDcn = databaseDcn;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setSyncName(String syncName) {
      this.syncName = syncName;
   }

   public String getSyncName() {
      return this.syncName;
   }

   public void setSyncCode(String syncCode) {
      this.syncCode = syncCode;
   }

   public String getSyncCode() {
      return this.syncCode;
   }

   public void setSyncSource(String syncSource) {
      this.syncSource = syncSource;
   }

   public String getSyncSource() {
      return this.syncSource;
   }

   public void setDatabaseType(String databaseType) {
      this.databaseType = databaseType;
   }

   public void setDatabaseUsername(String databaseUsername) {
      this.databaseUsername = databaseUsername;
   }

   public String getDatabaseUsername() {
      return this.databaseUsername;
   }

   public void setDatabasePassword(String databasePassword) {
      this.databasePassword = databasePassword;
   }

   public String getDatabasePassword() {
      return this.databasePassword;
   }

   public void setClassMethod(String classMethod) {
      this.classMethod = classMethod;
   }

   public String getClassMethod() {
      return this.classMethod;
   }

   public void setVaildFlag(Long vaildFlag) {
      this.vaildFlag = vaildFlag;
   }

   public Long getVaildFlag() {
      return this.vaildFlag;
   }

   public void setDelFlag(Long delFlag) {
      this.delFlag = delFlag;
   }

   public Long getDelFlag() {
      return this.delFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("syncName", this.getSyncName()).append("syncCode", this.getSyncCode()).append("syncSource", this.getSyncSource()).append("databaseType", this.getDatabaseType()).append("databaseUrl", this.getDatabaseUrl()).append("databaseDcn", this.getDatabaseDcn()).append("databaseUsername", this.getDatabaseUsername()).append("databasePassword", this.getDatabasePassword()).append("querySql", this.getQuerySql()).append("classMethod", this.getClassMethod()).append("vaildFlag", this.getVaildFlag()).append("delFlag", this.getDelFlag()).toString();
   }
}
