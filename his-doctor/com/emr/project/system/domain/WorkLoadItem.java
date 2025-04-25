package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WorkLoadItem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "项目编码"
   )
   private String itemCode;
   @Excel(
      name = "项目名称"
   )
   private String itemName;
   @Excel(
      name = "类型"
   )
   private String itemTypeCode;
   @Excel(
      name = "类型名称"
   )
   private String itemTypeName;
   @Excel(
      name = "数据库id"
   )
   private Long dataSourceId;
   @Excel(
      name = "数据库描述"
   )
   private String databaseDesc;
   @Excel(
      name = "数据来源类型"
   )
   private String sourceFromType;
   @Excel(
      name = "数据来源类型名称"
   )
   private String sourceFromName;
   @Excel(
      name = "序号"
   )
   private Integer sortNo;
   @Excel(
      name = "状态 1启用 0禁用"
   )
   private String status;
   @Excel(
      name = "SQL语句"
   )
   private String sqlScript;
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
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人名称"
   )
   private String altPerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemTypeCode(String itemTypeCode) {
      this.itemTypeCode = itemTypeCode;
   }

   public String getItemTypeCode() {
      return this.itemTypeCode;
   }

   public void setItemTypeName(String itemTypeName) {
      this.itemTypeName = itemTypeName;
   }

   public String getItemTypeName() {
      return this.itemTypeName;
   }

   public Long getDataSourceId() {
      return this.dataSourceId;
   }

   public void setDataSourceId(Long dataSourceId) {
      this.dataSourceId = dataSourceId;
   }

   public void setDatabaseDesc(String databaseDesc) {
      this.databaseDesc = databaseDesc;
   }

   public String getDatabaseDesc() {
      return this.databaseDesc;
   }

   public void setSourceFromType(String sourceFromType) {
      this.sourceFromType = sourceFromType;
   }

   public String getSourceFromType() {
      return this.sourceFromType;
   }

   public void setSourceFromName(String sourceFromName) {
      this.sourceFromName = sourceFromName;
   }

   public String getSourceFromName() {
      return this.sourceFromName;
   }

   public void setSortNo(Integer sortNo) {
      this.sortNo = sortNo;
   }

   public Integer getSortNo() {
      return this.sortNo;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setSqlScript(String sqlScript) {
      this.sqlScript = sqlScript;
   }

   public String getSqlScript() {
      return this.sqlScript;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("itemCode", this.getItemCode()).append("itemName", this.getItemName()).append("itemTypeCode", this.getItemTypeCode()).append("itemTypeName", this.getItemTypeName()).append("dataSourceTag", this.getDataSourceId()).append("databaseDesc", this.getDatabaseDesc()).append("sourceFromType", this.getSourceFromType()).append("sourceFromName", this.getSourceFromName()).append("sortNo", this.getSortNo()).append("status", this.getStatus()).append("sqlScript", this.getSqlScript()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
