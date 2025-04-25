package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TmRdDefine extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @ApiModelProperty(
      value = "上报定义名称",
      required = true
   )
   @Excel(
      name = "上报定义名称"
   )
   private String defineName;
   @ApiModelProperty(
      value = "上报定义描述",
      required = true
   )
   @Excel(
      name = "上报定义描述"
   )
   private String defineDesc;
   @ApiModelProperty(
      value = "使用院区编码",
      required = true
   )
   @Excel(
      name = "使用院区编码"
   )
   private String wardCode;
   @ApiModelProperty(
      value = "使用院区名称",
      required = true
   )
   @Excel(
      name = "使用院区名称"
   )
   private String wardName;
   @ApiModelProperty("上报文件前缀格式")
   @Excel(
      name = "上报文件前缀格式"
   )
   private String defineFp;
   @ApiModelProperty("执行脚本")
   @Excel(
      name = "执行脚本"
   )
   private String executeScript;
   @Excel(
      name = "导出文件格式"
   )
   @ApiModelProperty("导出文件格式")
   private String exportFileFormat;
   @Excel(
      name = "上报定义状态"
   )
   @ApiModelProperty(
      value = "上报定义状态,0:启用 1：禁用",
      required = true
   )
   private String state;
   @Excel(
      name = "创建人"
   )
   @ApiModelProperty("创建人")
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @ApiModelProperty("创建时间")
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setDefineName(String defineName) {
      this.defineName = defineName;
   }

   public String getDefineName() {
      return this.defineName;
   }

   public void setDefineDesc(String defineDesc) {
      this.defineDesc = defineDesc;
   }

   public String getDefineDesc() {
      return this.defineDesc;
   }

   public void setWardCode(String wardCode) {
      this.wardCode = wardCode;
   }

   public String getWardCode() {
      return this.wardCode;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setDefineFp(String defineFp) {
      this.defineFp = defineFp;
   }

   public String getDefineFp() {
      return this.defineFp;
   }

   public void setExecuteScript(String executeScript) {
      this.executeScript = executeScript;
   }

   public String getExecuteScript() {
      return this.executeScript;
   }

   public void setExportFileFormat(String exportFileFormat) {
      this.exportFileFormat = exportFileFormat;
   }

   public String getExportFileFormat() {
      return this.exportFileFormat;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getState() {
      return this.state;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("defineName", this.getDefineName()).append("defineDesc", this.getDefineDesc()).append("wardCode", this.getWardCode()).append("wardName", this.getWardName()).append("defineFp", this.getDefineFp()).append("executeScript", this.getExecuteScript()).append("exportFileFormat", this.getExportFileFormat()).append("state", this.getState()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
