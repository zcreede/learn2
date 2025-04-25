package com.emr.project.emr.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysEmrTypeConfig extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "病历类型编码"
   )
   private String emrTypeCode;
   @Excel(
      name = "病历分类编码"
   )
   private String emrClassCode;
   @Excel(
      name = "病历归档类型"
   )
   private String mrFileType;
   @Excel(
      name = "签名标识 0 不需要签名  1 医师签名   2   患者签名  3  医师患者都需签名  4  医师会签"
   )
   private Long signFlag;
   @Excel(
      name = "病程合并打印顺序，0：不合并打印， 1，合并后分页打印， 2，正常合并"
   )
   private Long mergeFlag;
   @Excel(
      name = "启用标识",
      readConverterExp = "0=：未启用；1：启用"
   )
   private String validFlag;
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
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
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
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private String editMode;
   @Excel(
      name = "审签级别，1：一级审签，2：二级审签，3：三级审签"
   )
   private String reviewedLevel;
   private String isOds;
   private Integer limitWriteNum;
   private String limitWriteFlag;
   private String secondDocCode = "";
   private String secondDeptCode = "";
   private String secondDocName = "";
   private String secondDeptName = "";
   private String thirdDocCode = "";
   private String thirdDeptCode = "";
   private String thirdDocName = "";
   private String thirdDeptName = "";

   public String getIsOds() {
      return this.isOds;
   }

   public void setIsOds(String isOds) {
      this.isOds = isOds;
   }

   public static long getSerialVersionUID() {
      return 1L;
   }

   public String getSecondDocCode() {
      return this.secondDocCode;
   }

   public void setSecondDocCode(String secondDocCode) {
      this.secondDocCode = secondDocCode;
   }

   public String getSecondDeptCode() {
      return this.secondDeptCode;
   }

   public void setSecondDeptCode(String secondDeptCode) {
      this.secondDeptCode = secondDeptCode;
   }

   public String getSecondDocName() {
      return this.secondDocName;
   }

   public void setSecondDocName(String secondDocName) {
      this.secondDocName = secondDocName;
   }

   public String getSecondDeptName() {
      return this.secondDeptName;
   }

   public void setSecondDeptName(String secondDeptName) {
      this.secondDeptName = secondDeptName;
   }

   public String getThirdDocCode() {
      return this.thirdDocCode;
   }

   public void setThirdDocCode(String thirdDocCode) {
      this.thirdDocCode = thirdDocCode;
   }

   public String getThirdDeptCode() {
      return this.thirdDeptCode;
   }

   public void setThirdDeptCode(String thirdDeptCode) {
      this.thirdDeptCode = thirdDeptCode;
   }

   public String getThirdDocName() {
      return this.thirdDocName;
   }

   public void setThirdDocName(String thirdDocName) {
      this.thirdDocName = thirdDocName;
   }

   public String getThirdDeptName() {
      return this.thirdDeptName;
   }

   public void setThirdDeptName(String thirdDeptName) {
      this.thirdDeptName = thirdDeptName;
   }

   public String getReviewedLevel() {
      return this.reviewedLevel;
   }

   public void setReviewedLevel(String reviewedLevel) {
      this.reviewedLevel = reviewedLevel;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrClassCode(String emrClassCode) {
      this.emrClassCode = emrClassCode;
   }

   public String getEmrClassCode() {
      return this.emrClassCode;
   }

   public void setMrFileType(String mrFileType) {
      this.mrFileType = mrFileType;
   }

   public String getMrFileType() {
      return this.mrFileType;
   }

   public void setSignFlag(Long signFlag) {
      this.signFlag = signFlag;
   }

   public Long getSignFlag() {
      return this.signFlag;
   }

   public void setMergeFlag(Long mergeFlag) {
      this.mergeFlag = mergeFlag;
   }

   public Long getMergeFlag() {
      return this.mergeFlag;
   }

   public void setValidFlag(String validFlag) {
      this.validFlag = validFlag;
   }

   public String getValidFlag() {
      return this.validFlag;
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

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public String getEditMode() {
      return this.editMode;
   }

   public void setEditMode(String editMode) {
      this.editMode = editMode;
   }

   public Integer getLimitWriteNum() {
      return this.limitWriteNum;
   }

   public void setLimitWriteNum(Integer limitWriteNum) {
      this.limitWriteNum = limitWriteNum;
   }

   public String getLimitWriteFlag() {
      return this.limitWriteFlag;
   }

   public void setLimitWriteFlag(String limitWriteFlag) {
      this.limitWriteFlag = limitWriteFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("emrTypeCode", this.getEmrTypeCode()).append("emrClassCode", this.getEmrClassCode()).append("mrFileType", this.getMrFileType()).append("signFlag", this.getSignFlag()).append("mergeFlag", this.getMergeFlag()).append("validFlag", this.getValidFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("altDate", this.getAltDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("creDate", this.getCreDate()).append("editMode", this.getEditMode()).toString();
   }
}
