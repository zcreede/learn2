package com.emr.project.operation.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdCaOperationRoom extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("主键ID")
   private Long id;
   @Excel(
      name = "医疗机构编码 "
   )
   private String hospitalCode;
   @Excel(
      name = "归属科室代码"
   )
   @ApiModelProperty("归属科室代码")
   private String belongingDepartmentCode;
   @Excel(
      name = "归属科室名称"
   )
   @ApiModelProperty("归属科室名称")
   private String belongingDepartmentName;
   @Excel(
      name = "手术间编码"
   )
   @ApiModelProperty("手术间编码")
   private String surgicalRoomCode;
   @Excel(
      name = "手术间名称"
   )
   @ApiModelProperty("手术间名称")
   private String surgicalRoomName;
   @Excel(
      name = "拼音码"
   )
   @ApiModelProperty("拼音码")
   private String pinyinCode;
   @Excel(
      name = "序号"
   )
   @ApiModelProperty("序号")
   private Long serialNumber;
   @Excel(
      name = "备注"
   )
   @ApiModelProperty("备注")
   private String notes;
   @Excel(
      name = "状态: 0删除 1正常"
   )
   @ApiModelProperty("状态: 0删除 1正常")
   private String status;
   @Excel(
      name = "创建人"
   )
   @ApiModelProperty("创建人")
   private String creName;
   @Excel(
      name = "创建人编码"
   )
   @ApiModelProperty("创建人编码")
   private String creCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss"
   )
   @ApiModelProperty("创建时间")
   private Date creDate;
   @Excel(
      name = "是否被使用 0没有使用 1使用"
   )
   @ApiModelProperty("是否被使用 0没有使用 1使用")
   private String isUse;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setBelongingDepartmentCode(String belongingDepartmentCode) {
      this.belongingDepartmentCode = belongingDepartmentCode;
   }

   public String getBelongingDepartmentCode() {
      return this.belongingDepartmentCode;
   }

   public void setBelongingDepartmentName(String belongingDepartmentName) {
      this.belongingDepartmentName = belongingDepartmentName;
   }

   public String getBelongingDepartmentName() {
      return this.belongingDepartmentName;
   }

   public void setSurgicalRoomCode(String surgicalRoomCode) {
      this.surgicalRoomCode = surgicalRoomCode;
   }

   public String getSurgicalRoomCode() {
      return this.surgicalRoomCode;
   }

   public void setSurgicalRoomName(String surgicalRoomName) {
      this.surgicalRoomName = surgicalRoomName;
   }

   public String getSurgicalRoomName() {
      return this.surgicalRoomName;
   }

   public void setPinyinCode(String pinyinCode) {
      this.pinyinCode = pinyinCode;
   }

   public String getPinyinCode() {
      return this.pinyinCode;
   }

   public void setSerialNumber(Long serialNumber) {
      this.serialNumber = serialNumber;
   }

   public Long getSerialNumber() {
      return this.serialNumber;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public String getNotes() {
      return this.notes;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return this.status;
   }

   public void setCreName(String creName) {
      this.creName = creName;
   }

   public String getCreName() {
      return this.creName;
   }

   public void setCreCode(String creCode) {
      this.creCode = creCode;
   }

   public String getCreCode() {
      return this.creCode;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setIsUse(String isUse) {
      this.isUse = isUse;
   }

   public String getIsUse() {
      return this.isUse;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("hospitalCode", this.getHospitalCode()).append("belongingDepartmentCode", this.getBelongingDepartmentCode()).append("belongingDepartmentName", this.getBelongingDepartmentName()).append("surgicalRoomCode", this.getSurgicalRoomCode()).append("surgicalRoomName", this.getSurgicalRoomName()).append("pinyinCode", this.getPinyinCode()).append("serialNumber", this.getSerialNumber()).append("notes", this.getNotes()).append("status", this.getStatus()).append("creName", this.getCreName()).append("creCode", this.getCreCode()).append("creDate", this.getCreDate()).append("isUse", this.getIsUse()).toString();
   }
}
