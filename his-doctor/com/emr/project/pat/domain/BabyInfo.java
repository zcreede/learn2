package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BabyInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
   private String patientId;
   private String patBabyId;
   @Excel(
      name = "婴儿姓名"
   )
   private String babyName;
   @Excel(
      name = "婴儿性别代码"
   )
   private String babySexCd;
   @Excel(
      name = "婴儿性别"
   )
   private String babySexName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出生日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date birDate;
   @Excel(
      name = "出生方式"
   )
   private String birthWay;
   @Excel(
      name = "体重(KG)"
   )
   private Long weight;
   @Excel(
      name = "病区代码"
   )
   private String areaCode;
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
   private Date beginTime;
   private Date endTime;
   private String wardNo;

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatBabyId(String patBabyId) {
      this.patBabyId = patBabyId;
   }

   public String getPatBabyId() {
      return this.patBabyId;
   }

   public void setBabyName(String babyName) {
      this.babyName = babyName;
   }

   public String getBabyName() {
      return this.babyName;
   }

   public void setBabySexCd(String babySexCd) {
      this.babySexCd = babySexCd;
   }

   public String getBabySexCd() {
      return this.babySexCd;
   }

   public void setBabySexName(String babySexName) {
      this.babySexName = babySexName;
   }

   public String getBabySexName() {
      return this.babySexName;
   }

   public void setBirDate(Date birDate) {
      this.birDate = birDate;
   }

   public Date getBirDate() {
      return this.birDate;
   }

   public void setBirthWay(String birthWay) {
      this.birthWay = birthWay;
   }

   public String getBirthWay() {
      return this.birthWay;
   }

   public void setWeight(Long weight) {
      this.weight = weight;
   }

   public Long getWeight() {
      return this.weight;
   }

   public void setAreaCode(String areaCode) {
      this.areaCode = areaCode;
   }

   public String getAreaCode() {
      return this.areaCode;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("patientId", this.getPatientId()).append("patBabyId", this.getPatBabyId()).append("babyName", this.getBabyName()).append("babySexCd", this.getBabySexCd()).append("babySexName", this.getBabySexName()).append("birDate", this.getBirDate()).append("birthWay", this.getBirthWay()).append("weight", this.getWeight()).append("areaCode", this.getAreaCode()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).toString();
   }
}
