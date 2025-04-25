package com.emr.project.emr.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class EmrTimeoutCaseVo {
   @ApiModelProperty("患者信息")
   private String searchValue;
   @ApiModelProperty("超时任务id")
   private String taskId;
   @ApiModelProperty("住院号")
   private String patientId;
   @ApiModelProperty("就诊号")
   private String inpNo;
   @ApiModelProperty("住院号")
   private String patientMainId;
   @ApiModelProperty("病历类型：字典s004")
   private String mrType;
   @ApiModelProperty("病历类型名称")
   private String mrTypeName;
   @ApiModelProperty("病历分类")
   private String mrTypeClass;
   @ApiModelProperty("病历分类名称")
   private String mrTypeClassName;
   @ApiModelProperty("超时时长")
   private String remainingTime;
   @ApiModelProperty("超时任务状态：0未处理 1已处理")
   private String taskState;
   @ApiModelProperty("科室编码")
   private String deptCd;
   @ApiModelProperty("科室名称")
   private String deptName;
   @ApiModelProperty("责任医师编码")
   private String docCd;
   @ApiModelProperty("责任医师名称")
   private String docName;
   @ApiModelProperty("病历文件id")
   private Long emrFileId;
   @ApiModelProperty("主病历文件id")
   private Long mainId;
   @ApiModelProperty("患者姓名")
   private String patName;
   @ApiModelProperty("书写时限")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date endTime;
   @ApiModelProperty("完成条件(病历创建 1 创建医师签名 2 病历完成 3)")
   private String taskFinishFlag;
   @ApiModelProperty("住院医师编码")
   private String residentCode;
   @ApiModelProperty("住院医师姓名")
   private String residentName;
   @ApiModelProperty("入院时间")
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date entryDate;
   @ApiModelProperty("性别")
   private String sex;
   @ApiModelProperty("年龄")
   private String ageStr;
   @ApiModelProperty("出生日期")
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date birdthDate;

   public String getSearchValue() {
      return this.searchValue;
   }

   public void setSearchValue(String searchValue) {
      this.searchValue = searchValue;
   }

   public String getMrTypeClass() {
      return this.mrTypeClass;
   }

   public void setMrTypeClass(String mrTypeClass) {
      this.mrTypeClass = mrTypeClass;
   }

   public String getMrTypeClassName() {
      return this.mrTypeClassName;
   }

   public void setMrTypeClassName(String mrTypeClassName) {
      this.mrTypeClassName = mrTypeClassName;
   }

   public String getAgeStr() {
      return this.ageStr;
   }

   public void setAgeStr(String ageStr) {
      this.ageStr = ageStr;
   }

   public String getTaskId() {
      return this.taskId;
   }

   public void setTaskId(String taskId) {
      this.taskId = taskId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public String getRemainingTime() {
      return this.remainingTime;
   }

   public void setRemainingTime(String remainingTime) {
      this.remainingTime = remainingTime;
   }

   public String getTaskState() {
      return this.taskState;
   }

   public void setTaskState(String taskState) {
      this.taskState = taskState;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public Long getEmrFileId() {
      return this.emrFileId;
   }

   public void setEmrFileId(Long emrFileId) {
      this.emrFileId = emrFileId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public String getPatName() {
      return this.patName;
   }

   public void setPatName(String patName) {
      this.patName = patName;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getTaskFinishFlag() {
      return this.taskFinishFlag;
   }

   public void setTaskFinishFlag(String taskFinishFlag) {
      this.taskFinishFlag = taskFinishFlag;
   }

   public String getResidentCode() {
      return this.residentCode;
   }

   public void setResidentCode(String residentCode) {
      this.residentCode = residentCode;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Date getBirdthDate() {
      return this.birdthDate;
   }

   public void setBirdthDate(Date birdthDate) {
      this.birdthDate = birdthDate;
   }
}
