package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysEmrConfig extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   private Long parentId;
   @Excel(
      name = "参数名称"
   )
   private String configName;
   @Excel(
      name = "参数键名"
   )
   private String configKey;
   @Excel(
      name = "参数键值"
   )
   private String configValue;
   @Excel(
      name = "系统内置",
      readConverterExp = "Y=是,N=否"
   )
   private String configType;
   private String orgCd;

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public SysEmrConfig() {
   }

   public SysEmrConfig(String configKey) {
      this.configKey = configKey;
   }

   public SysEmrConfig(String configKey, String orgCd) {
      this.configKey = configKey;
      this.orgCd = orgCd;
   }

   public SysEmrConfig(Long parentId) {
      this.parentId = parentId;
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

   public void setConfigName(String configName) {
      this.configName = configName;
   }

   public String getConfigName() {
      return this.configName;
   }

   public void setConfigKey(String configKey) {
      this.configKey = configKey;
   }

   public String getConfigKey() {
      return this.configKey;
   }

   public void setConfigValue(String configValue) {
      this.configValue = configValue;
   }

   public String getConfigValue() {
      return this.configValue;
   }

   public void setConfigType(String configType) {
      this.configType = configType;
   }

   public String getConfigType() {
      return this.configType;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("configName", this.getConfigName()).append("configKey", this.getConfigKey()).append("configValue", this.getConfigValue()).append("configType", this.getConfigType()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
