package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpDrawMain extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "接口名称"
   )
   private String interfaceName;
   @Excel(
      name = "HIS数据库表"
   )
   private String hisTableName;
   @Excel(
      name = "接口类型",
      readConverterExp = "1=三方API,2=数据库"
   )
   private String interfaceType;
   @Excel(
      name = "数据源业务名称"
   )
   private String datasourceSyncName;
   @Excel(
      name = "数据源业务编码"
   )
   private String datasourceSyncCode;
   @Excel(
      name = "数据来源"
   )
   private String dataFrom;
   @Excel(
      name = "sql语句(数据库)"
   )
   private String sqlStatement;
   @Excel(
      name = "接口请求地址",
      readConverterExp = "三=方API"
   )
   private String reqUrl;
   @Excel(
      name = "请求方式",
      readConverterExp = "三=方API"
   )
   private String requestMethod;
   @Excel(
      name = "方法名",
      readConverterExp = "三=方API"
   )
   private String method;
   @Excel(
      name = "状态",
      readConverterExp = "1启用,0禁用"
   )
   private String status;
   @Excel(
      name = "应用场景",
      readConverterExp = "1=医生打开病案首页时,2=手动点击重新提取按钮"
   )
   private String appSce;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "修改编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setInterfaceName(String interfaceName) {
      this.interfaceName = interfaceName;
   }

   public String getInterfaceName() {
      return this.interfaceName;
   }

   public void setHisTableName(String hisTableName) {
      this.hisTableName = hisTableName;
   }

   public String getHisTableName() {
      return this.hisTableName;
   }

   public void setInterfaceType(String interfaceType) {
      this.interfaceType = interfaceType;
   }

   public String getInterfaceType() {
      return this.interfaceType;
   }

   public void setDatasourceSyncName(String datasourceSyncName) {
      this.datasourceSyncName = datasourceSyncName;
   }

   public String getDatasourceSyncName() {
      return this.datasourceSyncName;
   }

   public void setDatasourceSyncCode(String datasourceSyncCode) {
      this.datasourceSyncCode = datasourceSyncCode;
   }

   public String getDatasourceSyncCode() {
      return this.datasourceSyncCode;
   }

   public void setDataFrom(String dataFrom) {
      this.dataFrom = dataFrom;
   }

   public String getDataFrom() {
      return this.dataFrom;
   }

   public void setSqlStatement(String sqlStatement) {
      this.sqlStatement = sqlStatement;
   }

   public String getSqlStatement() {
      return this.sqlStatement;
   }

   public void setReqUrl(String reqUrl) {
      this.reqUrl = reqUrl;
   }

   public String getReqUrl() {
      return this.reqUrl;
   }

   public void setRequestMethod(String requestMethod) {
      this.requestMethod = requestMethod;
   }

   public String getRequestMethod() {
      return this.requestMethod;
   }

   public void setMethod(String method) {
      this.method = method;
   }

   public String getMethod() {
      return this.method;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setAppSce(String appSce) {
      this.appSce = appSce;
   }

   public String getAppSce() {
      return this.appSce;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("interfaceName", this.getInterfaceName()).append("hisTableName", this.getHisTableName()).append("interfaceType", this.getInterfaceType()).append("datasourceSyncName", this.getDatasourceSyncName()).append("datasourceSyncCode", this.getDatasourceSyncCode()).append("dataFrom", this.getDataFrom()).append("sqlStatement", this.getSqlStatement()).append("reqUrl", this.getReqUrl()).append("requestMethod", this.getRequestMethod()).append("method", this.getMethod()).append("status", this.getStatus()).append("appSce", this.getAppSce()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
