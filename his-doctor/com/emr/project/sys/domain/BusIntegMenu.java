package com.emr.project.sys.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BusIntegMenu extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "${comment}",
      readConverterExp = "$column.readConverterExp()"
   )
   private String code;
   @Excel(
      name = "名称"
   )
   private String name;
   @Excel(
      name = "类型编码 1 固定业务页面   2  病历页面  3  外部系统链接"
   )
   private Long typeCode;
   @Excel(
      name = "加载类型编码 1 固定 2 动态加载"
   )
   private Long loadMode;
   @Excel(
      name = "页面路由地址",
      readConverterExp = "包=括页面传入参数"
   )
   private String routePath;
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
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
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
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "菜单分组(1当前就诊  2历史就诊)"
   )
   private String menuClass;
   @Excel(
      name = "菜单顺序"
   )
   private Long menuOrder;
   private Long parentId;
   private Integer browserType;
   private String browserTypeName;
   private String sysCode;
   private String firstMenu;

   public String getSysCode() {
      return this.sysCode;
   }

   public void setSysCode(String sysCode) {
      this.sysCode = sysCode;
   }

   public String getFirstMenu() {
      return this.firstMenu;
   }

   public void setFirstMenu(String firstMenu) {
      this.firstMenu = firstMenu;
   }

   public Integer getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(Integer browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public Long getParentId() {
      return this.parentId;
   }

   public void setParentId(Long parentId) {
      this.parentId = parentId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getCode() {
      return this.code;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setTypeCode(Long typeCode) {
      this.typeCode = typeCode;
   }

   public Long getTypeCode() {
      return this.typeCode;
   }

   public void setLoadMode(Long loadMode) {
      this.loadMode = loadMode;
   }

   public Long getLoadMode() {
      return this.loadMode;
   }

   public void setRoutePath(String routePath) {
      this.routePath = routePath;
   }

   public String getRoutePath() {
      return this.routePath;
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

   public void setMenuClass(String menuClass) {
      this.menuClass = menuClass;
   }

   public String getMenuClass() {
      return this.menuClass;
   }

   public void setMenuOrder(Long menuOrder) {
      this.menuOrder = menuOrder;
   }

   public Long getMenuOrder() {
      return this.menuOrder;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("code", this.getCode()).append("name", this.getName()).append("typeCode", this.getTypeCode()).append("loadMode", this.getLoadMode()).append("routePath", this.getRoutePath()).append("isValid", this.getIsValid()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("menuClass", this.getMenuClass()).append("menuOrder", this.getMenuOrder()).toString();
   }
}
