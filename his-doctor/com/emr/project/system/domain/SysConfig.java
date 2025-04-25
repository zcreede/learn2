package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysConfig extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "参数主键",
      cellType = Excel.ColumnType.NUMERIC
   )
   private Long configId;
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

   public Long getConfigId() {
      return this.configId;
   }

   public void setConfigId(Long configId) {
      this.configId = configId;
   }

   public @NotBlank(
   message = "参数名称不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "参数名称不能超过100个字符"
) String getConfigName() {
      return this.configName;
   }

   public void setConfigName(String configName) {
      this.configName = configName;
   }

   public @NotBlank(
   message = "参数键名长度不能为空"
) @Size(
   min = 0,
   max = 100,
   message = "参数键名长度不能超过100个字符"
) String getConfigKey() {
      return this.configKey;
   }

   public void setConfigKey(String configKey) {
      this.configKey = configKey;
   }

   public @NotBlank(
   message = "参数键值不能为空"
) @Size(
   min = 0,
   max = 500,
   message = "参数键值长度不能超过500个字符"
) String getConfigValue() {
      return this.configValue;
   }

   public void setConfigValue(String configValue) {
      this.configValue = configValue;
   }

   public String getConfigType() {
      return this.configType;
   }

   public void setConfigType(String configType) {
      this.configType = configType;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("configId", this.getConfigId()).append("configName", this.getConfigName()).append("configKey", this.getConfigKey()).append("configValue", this.getConfigValue()).append("configType", this.getConfigType()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).toString();
   }
}
