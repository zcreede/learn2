package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmBsModule extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "模块编号"
   )
   private String moduleCode;
   @Excel(
      name = "模块名称"
   )
   private String moduleName;
   @Excel(
      name = "拼音码"
   )
   private String modulePym;
   @Excel(
      name = "是否第三方"
   )
   private Long isThirdParty;
   @Excel(
      name = "是否启用",
      readConverterExp = "0=是1否"
   )
   private Long isEnable;
   @Excel(
      name = "默认用户名"
   )
   private String defaultUser;
   @Excel(
      name = "默认密码"
   )
   private String defaultPwd;
   @Excel(
      name = "菜单权限"
   )
   private Long isMenu;
   @Excel(
      name = "模块状态",
      readConverterExp = "0=正常1停用"
   )
   private String status;
   private String delFlag;
   @Excel(
      name = "排序"
   )
   private Long sort;
   @Excel(
      name = "所属公司"
   )
   private String company;
   @Excel(
      name = "集成方式",
      readConverterExp = "0=菜单集成1系统集成"
   )
   private String integrationMode;
   @Excel(
      name = "打开方式"
   )
   private String openMode;
   @Excel(
      name = "打开类型"
   )
   private String openType;
   @Excel(
      name = "程序路径"
   )
   private String programPath;
   @Excel(
      name = "系统归类",
      readConverterExp = "护=理，质控等"
   )
   private String moduleType;
   @Excel(
      name = "图标"
   )
   private String moduleIcon;
   @Excel(
      name = "颜色"
   )
   private String moduleColor;
   @Excel(
      name = "备用1"
   )
   private String bz1;
   @Excel(
      name = "备用2"
   )
   private String bz2;
   @Excel(
      name = "备用3"
   )
   private String bz3;
   @Excel(
      name = "医院编码"
   )
   private String hospitalNo;
   @Excel(
      name = "医院名称"
   )
   private String hospitalName;
   @Excel(
      name = "是否平台系统",
      readConverterExp = "0=是1否"
   )
   private String isPlatform;
   private String indexUrl;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setModuleCode(String moduleCode) {
      this.moduleCode = moduleCode;
   }

   public String getModuleCode() {
      return this.moduleCode;
   }

   public void setModuleName(String moduleName) {
      this.moduleName = moduleName;
   }

   public String getModuleName() {
      return this.moduleName;
   }

   public void setModulePym(String modulePym) {
      this.modulePym = modulePym;
   }

   public String getModulePym() {
      return this.modulePym;
   }

   public void setIsThirdParty(Long isThirdParty) {
      this.isThirdParty = isThirdParty;
   }

   public Long getIsThirdParty() {
      return this.isThirdParty;
   }

   public void setIsEnable(Long isEnable) {
      this.isEnable = isEnable;
   }

   public Long getIsEnable() {
      return this.isEnable;
   }

   public void setDefaultUser(String defaultUser) {
      this.defaultUser = defaultUser;
   }

   public String getDefaultUser() {
      return this.defaultUser;
   }

   public void setDefaultPwd(String defaultPwd) {
      this.defaultPwd = defaultPwd;
   }

   public String getDefaultPwd() {
      return this.defaultPwd;
   }

   public void setIsMenu(Long isMenu) {
      this.isMenu = isMenu;
   }

   public Long getIsMenu() {
      return this.isMenu;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setSort(Long sort) {
      this.sort = sort;
   }

   public Long getSort() {
      return this.sort;
   }

   public void setCompany(String company) {
      this.company = company;
   }

   public String getCompany() {
      return this.company;
   }

   public void setIntegrationMode(String integrationMode) {
      this.integrationMode = integrationMode;
   }

   public String getIntegrationMode() {
      return this.integrationMode;
   }

   public void setOpenMode(String openMode) {
      this.openMode = openMode;
   }

   public String getOpenMode() {
      return this.openMode;
   }

   public void setOpenType(String openType) {
      this.openType = openType;
   }

   public String getOpenType() {
      return this.openType;
   }

   public void setProgramPath(String programPath) {
      this.programPath = programPath;
   }

   public String getProgramPath() {
      return this.programPath;
   }

   public void setModuleType(String moduleType) {
      this.moduleType = moduleType;
   }

   public String getModuleType() {
      return this.moduleType;
   }

   public void setModuleIcon(String moduleIcon) {
      this.moduleIcon = moduleIcon;
   }

   public String getModuleIcon() {
      return this.moduleIcon;
   }

   public void setModuleColor(String moduleColor) {
      this.moduleColor = moduleColor;
   }

   public String getModuleColor() {
      return this.moduleColor;
   }

   public void setBz1(String bz1) {
      this.bz1 = bz1;
   }

   public String getBz1() {
      return this.bz1;
   }

   public void setBz2(String bz2) {
      this.bz2 = bz2;
   }

   public String getBz2() {
      return this.bz2;
   }

   public void setBz3(String bz3) {
      this.bz3 = bz3;
   }

   public String getBz3() {
      return this.bz3;
   }

   public void setHospitalNo(String hospitalNo) {
      this.hospitalNo = hospitalNo;
   }

   public String getHospitalNo() {
      return this.hospitalNo;
   }

   public void setHospitalName(String hospitalName) {
      this.hospitalName = hospitalName;
   }

   public String getHospitalName() {
      return this.hospitalName;
   }

   public void setIsPlatform(String isPlatform) {
      this.isPlatform = isPlatform;
   }

   public String getIsPlatform() {
      return this.isPlatform;
   }

   public String getIndexUrl() {
      return this.indexUrl;
   }

   public void setIndexUrl(String indexUrl) {
      this.indexUrl = indexUrl;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("moduleCode", this.getModuleCode()).append("moduleName", this.getModuleName()).append("modulePym", this.getModulePym()).append("isThirdParty", this.getIsThirdParty()).append("isEnable", this.getIsEnable()).append("defaultUser", this.getDefaultUser()).append("defaultPwd", this.getDefaultPwd()).append("isMenu", this.getIsMenu()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("sort", this.getSort()).append("company", this.getCompany()).append("integrationMode", this.getIntegrationMode()).append("openMode", this.getOpenMode()).append("openType", this.getOpenType()).append("programPath", this.getProgramPath()).append("moduleType", this.getModuleType()).append("moduleIcon", this.getModuleIcon()).append("moduleColor", this.getModuleColor()).append("bz1", this.getBz1()).append("bz2", this.getBz2()).append("bz3", this.getBz3()).append("hospitalNo", this.getHospitalNo()).append("hospitalName", this.getHospitalName()).append("isPlatform", this.getIsPlatform()).append("createTime", this.getCreateTime()).append("updateTime", this.getUpdateTime()).toString();
   }
}
