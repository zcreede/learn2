package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmDsPreserveOut extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("主键ID")
   private Long id;
   @ApiModelProperty(
      value = "数据源标识编码 限制只能是字母+数字",
      required = true
   )
   @Excel(
      name = "数据源标识编码"
   )
   private String dataSourceTag;
   @ApiModelProperty(
      value = "数据源描述",
      required = true
   )
   @Excel(
      name = "数据源描述"
   )
   private String databaseDesc;
   @ApiModelProperty("数据库类型 /system/tmpa/data/type/s098")
   @Excel(
      name = "数据库类型"
   )
   private String databaseType;
   @ApiModelProperty(
      value = "服务器地址",
      required = true
   )
   @Excel(
      name = "服务器地址"
   )
   private String serverAddress;
   @ApiModelProperty(
      value = "数据库名称",
      required = true
   )
   @Excel(
      name = "数据库名称"
   )
   private String databaseName;
   @ApiModelProperty(
      value = "用户名",
      required = true
   )
   @Excel(
      name = "用户名"
   )
   private String userName;
   @ApiModelProperty(
      value = "密码",
      required = true
   )
   @Excel(
      name = "密码"
   )
   private String passward;
   @ApiModelProperty(
      value = "数据库驱动类名",
      required = true
   )
   @Excel(
      name = "数据库驱动类名"
   )
   private String driverClass;
   @ApiModelProperty(
      value = "数据库URL",
      required = true
   )
   @Excel(
      name = "数据库URL"
   )
   private String databaseUrl;
   @ApiModelProperty(
      value = "状态 1启用 0禁用",
      required = true
   )
   @Excel(
      name = "状态"
   )
   private String state;
   @Excel(
      name = "创建人编码"
   )
   @ApiModelProperty(
      hidden = true
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   @ApiModelProperty(
      hidden = true
   )
   private String crePerName;
   @ApiModelProperty(
      hidden = true
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @ApiModelProperty(
      hidden = true
   )
   @Excel(
      name = "修改人名称"
   )
   private String altPerName;
   @ApiModelProperty(
      hidden = true
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @ApiModelProperty("院区编码")
   private String orgCd;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setDataSourceTag(String dataSourceTag) {
      this.dataSourceTag = dataSourceTag;
   }

   public String getDataSourceTag() {
      return this.dataSourceTag;
   }

   public void setDatabaseDesc(String databaseDesc) {
      this.databaseDesc = databaseDesc;
   }

   public String getDatabaseDesc() {
      return this.databaseDesc;
   }

   public void setDatabaseType(String databaseType) {
      this.databaseType = databaseType;
   }

   public String getDatabaseType() {
      return this.databaseType;
   }

   public void setServerAddress(String serverAddress) {
      this.serverAddress = serverAddress;
   }

   public String getServerAddress() {
      return this.serverAddress;
   }

   public void setDatabaseName(String databaseName) {
      this.databaseName = databaseName;
   }

   public String getDatabaseName() {
      return this.databaseName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getUserName() {
      return this.userName;
   }

   public void setPassward(String passward) {
      this.passward = passward;
   }

   public String getPassward() {
      return this.passward;
   }

   public void setDriverClass(String driverClass) {
      this.driverClass = driverClass;
   }

   public String getDriverClass() {
      return this.driverClass;
   }

   public void setDatabaseUrl(String databaseUrl) {
      this.databaseUrl = databaseUrl;
   }

   public String getDatabaseUrl() {
      return this.databaseUrl;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
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

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("dataSourceTag", this.getDataSourceTag()).append("databaseDesc", this.getDatabaseDesc()).append("databaseType", this.getDatabaseType()).append("serverAddress", this.getServerAddress()).append("databaseName", this.getDatabaseName()).append("userName", this.getUserName()).append("passward", this.getPassward()).append("driverClass", this.getDriverClass()).append("databaseUrl", this.getDatabaseUrl()).append("state", this.getState()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
