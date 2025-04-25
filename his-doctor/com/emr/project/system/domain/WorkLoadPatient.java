package com.emr.project.system.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WorkLoadPatient extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构编码"
   )
   private String orgCd;
   @Excel(
      name = "上报主表id"
   )
   private Long mainId;
   @Excel(
      name = "类型"
   )
   private String itemTypeCode;
   @Excel(
      name = "类型名称"
   )
   private String itemTypeName;
   @Excel(
      name = "病案号"
   )
   private String caseNo;
   @Excel(
      name = "住院号"
   )
   private String admissionNo;
   @Excel(
      name = "姓名"
   )
   private String name;
   @Excel(
      name = "性别"
   )
   private String sex;
   @Excel(
      name = "年龄"
   )
   private Long personAge;
   @Excel(
      name = "年龄 月"
   )
   private Long ageMonth;
   @Excel(
      name = "年龄 日"
   )
   private Long ageDay;
   @Excel(
      name = "年龄 时"
   )
   private Long ageHour;
   @Excel(
      name = "年龄 分"
   )
   private Long ageBranch;
   @Excel(
      name = "床位编号"
   )
   private String bedNo;
   @Excel(
      name = "床位号"
   )
   private String bedid;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date leaveHospitalDate;
   @Excel(
      name = "转入科室编号"
   )
   private String inWardNo;
   @Excel(
      name = "转出科室编号"
   )
   private String outWardNo;
   @Excel(
      name = "在科天数"
   )
   private Integer inDays;
   @Excel(
      name = "项目名称"
   )
   private String itemName;
   @Excel(
      name = "项目编码"
   )
   private String itemCode;

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setItemTypeCode(String itemTypeCode) {
      this.itemTypeCode = itemTypeCode;
   }

   public String getItemTypeCode() {
      return this.itemTypeCode;
   }

   public void setItemTypeName(String itemTypeName) {
      this.itemTypeName = itemTypeName;
   }

   public String getItemTypeName() {
      return this.itemTypeName;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getSex() {
      return this.sex;
   }

   public void setPersonAge(Long personAge) {
      this.personAge = personAge;
   }

   public Long getPersonAge() {
      return this.personAge;
   }

   public void setAgeMonth(Long ageMonth) {
      this.ageMonth = ageMonth;
   }

   public Long getAgeMonth() {
      return this.ageMonth;
   }

   public void setAgeDay(Long ageDay) {
      this.ageDay = ageDay;
   }

   public Long getAgeDay() {
      return this.ageDay;
   }

   public void setAgeHour(Long ageHour) {
      this.ageHour = ageHour;
   }

   public Long getAgeHour() {
      return this.ageHour;
   }

   public void setAgeBranch(Long ageBranch) {
      this.ageBranch = ageBranch;
   }

   public Long getAgeBranch() {
      return this.ageBranch;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setInWardNo(String inWardNo) {
      this.inWardNo = inWardNo;
   }

   public String getInWardNo() {
      return this.inWardNo;
   }

   public void setOutWardNo(String outWardNo) {
      this.outWardNo = outWardNo;
   }

   public String getOutWardNo() {
      return this.outWardNo;
   }

   public void setInDays(Integer inDays) {
      this.inDays = inDays;
   }

   public Integer getInDays() {
      return this.inDays;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("mainId", this.getMainId()).append("itemTypeCode", this.getItemTypeCode()).append("itemTypeName", this.getItemTypeName()).append("caseNo", this.getCaseNo()).append("admissionNo", this.getAdmissionNo()).append("name", this.getName()).append("sex", this.getSex()).append("personAge", this.getPersonAge()).append("ageMonth", this.getAgeMonth()).append("ageDay", this.getAgeDay()).append("ageHour", this.getAgeHour()).append("ageBranch", this.getAgeBranch()).append("bedNo", this.getBedNo()).append("bedid", this.getBedid()).append("hospitalizedDate", this.getHospitalizedDate()).append("leaveHospitalDate", this.getLeaveHospitalDate()).append("inWardNo", this.getInWardNo()).append("outWardNo", this.getOutWardNo()).append("inDays", this.getInDays()).toString();
   }
}
