package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel("病历编辑状态对象")
public class EditState extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @ApiModelProperty("锁定状态ID - 解锁时必传")
   private Long id;
   @Excel(
      name = "住院号"
   )
   @ApiModelProperty("住院号 - 列表查询参数 列表显示列")
   private String inpNo;
   @Excel(
      name = "科室ID"
   )
   private String deptId;
   @Excel(
      name = "患者姓名"
   )
   @ApiModelProperty("患者姓名 - 列表显示列")
   private String patientName;
   @Excel(
      name = "患者在医疗机构就诊的科室名称"
   )
   @ApiModelProperty("科室 - 列表显示列")
   private String deptName;
   @Excel(
      name = "病历文件索引ID"
   )
   private Long mrFileId;
   @Excel(
      name = "病历文档名称"
   )
   @ApiModelProperty("病历文档名称 - 列表查询参数 列表显示列")
   private String mrFileName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "开始编辑时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @ApiModelProperty("开始时间 - 列表查询参数")
   private Date beginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "完成编辑时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   @ApiModelProperty("结束时间 - 列表查询参数")
   private Date endTime;
   @Excel(
      name = "编辑人编码"
   )
   @ApiModelProperty("锁定操作员工工号 - 列表查询参数 列表显示列")
   private String editPersonCd;
   @Excel(
      name = "编辑人姓名"
   )
   @ApiModelProperty("锁定操作员 - 列表显示列")
   private String editPersonName;
   @Excel(
      name = "IP 地址"
   )
   @ApiModelProperty("锁定IP - 列表显示列")
   private String ip;
   @Excel(
      name = "MAC地址"
   )
   private String mac;
   @Excel(
      name = "编辑状态(1：在编辑；0：完成编辑)"
   )
   private String deitState;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @ApiModelProperty("病历类型 - 列表显示列")
   private String mrTypeName;

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public String getDeptId() {
      return this.deptId;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileName(String mrFileName) {
      this.mrFileName = mrFileName;
   }

   public String getMrFileName() {
      return this.mrFileName;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEditPersonCd(String editPersonCd) {
      this.editPersonCd = editPersonCd;
   }

   public String getEditPersonCd() {
      return this.editPersonCd;
   }

   public void setEditPersonName(String editPersonName) {
      this.editPersonName = editPersonName;
   }

   public String getEditPersonName() {
      return this.editPersonName;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getIp() {
      return this.ip;
   }

   public void setMac(String mac) {
      this.mac = mac;
   }

   public String getMac() {
      return this.mac;
   }

   public void setDeitState(String deitState) {
      this.deitState = deitState;
   }

   public String getDeitState() {
      return this.deitState;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("inpNo", this.getInpNo()).append("deptId", this.getDeptId()).append("patientName", this.getPatientName()).append("deptName", this.getDeptName()).append("mrFileId", this.getMrFileId()).append("mrFileName", this.getMrFileName()).append("beginTime", this.getBeginTime()).append("endTime", this.getEndTime()).append("editPersonCd", this.getEditPersonCd()).append("editPersonName", this.getEditPersonName()).append("ip", this.getIp()).append("mac", this.getMac()).append("deitState", this.getDeitState()).append("remark", this.getRemark()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).toString();
   }
}
