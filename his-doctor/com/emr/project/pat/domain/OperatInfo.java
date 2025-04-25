package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OperatInfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "患者主索引ID"
   )
   private String patientMainId;
   @Excel(
      name = "就诊ID"
   )
   private String patientId;
   @Excel(
      name = "手术类型  1 门诊手术  2 日间手术  3住院手术"
   )
   private String operType;
   @Excel(
      name = "手术及操作名称"
   )
   private String oprName;
   @Excel(
      name = "手术医生姓名"
   )
   private String oprDoctName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "手术开始日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date oprBeginTime;
   @Excel(
      name = "手术全程时间(min)"
   )
   private Long oprDuraMins;
   @Excel(
      name = "手术医师编号"
   )
   private String oprDoctCode;
   @Excel(
      name = "手术及操作编码"
   )
   private String oprIcd;
   @Excel(
      name = "手术级别"
   )
   private String oprLevelCode;
   @Excel(
      name = "手术级别"
   )
   private String oprLevel;
   @Excel(
      name = "手术切口类别代码"
   )
   private String oprInciCode;
   @Excel(
      name = "手术愈合等级代码"
   )
   private String oprHealCode;
   @Excel(
      name = "麻醉方法代码"
   )
   private String anestMethCode;
   @Excel(
      name = "麻醉方法名称"
   )
   private String anestMethName;
   @Excel(
      name = "麻醉医师姓名"
   )
   private String anestName;
   @Excel(
      name = "麻醉医师编号"
   )
   private String anestCode;
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

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setOperType(String operType) {
      this.operType = operType;
   }

   public String getOperType() {
      return this.operType;
   }

   public void setOprName(String oprName) {
      this.oprName = oprName;
   }

   public String getOprName() {
      return this.oprName;
   }

   public void setOprDoctName(String oprDoctName) {
      this.oprDoctName = oprDoctName;
   }

   public String getOprDoctName() {
      return this.oprDoctName;
   }

   public void setOprBeginTime(Date oprBeginTime) {
      this.oprBeginTime = oprBeginTime;
   }

   public Date getOprBeginTime() {
      return this.oprBeginTime;
   }

   public void setOprDuraMins(Long oprDuraMins) {
      this.oprDuraMins = oprDuraMins;
   }

   public Long getOprDuraMins() {
      return this.oprDuraMins;
   }

   public void setOprDoctCode(String oprDoctCode) {
      this.oprDoctCode = oprDoctCode;
   }

   public String getOprDoctCode() {
      return this.oprDoctCode;
   }

   public void setOprIcd(String oprIcd) {
      this.oprIcd = oprIcd;
   }

   public String getOprIcd() {
      return this.oprIcd;
   }

   public void setOprLevelCode(String oprLevelCode) {
      this.oprLevelCode = oprLevelCode;
   }

   public String getOprLevelCode() {
      return this.oprLevelCode;
   }

   public void setOprLevel(String oprLevel) {
      this.oprLevel = oprLevel;
   }

   public String getOprLevel() {
      return this.oprLevel;
   }

   public void setOprInciCode(String oprInciCode) {
      this.oprInciCode = oprInciCode;
   }

   public String getOprInciCode() {
      return this.oprInciCode;
   }

   public void setOprHealCode(String oprHealCode) {
      this.oprHealCode = oprHealCode;
   }

   public String getOprHealCode() {
      return this.oprHealCode;
   }

   public void setAnestMethCode(String anestMethCode) {
      this.anestMethCode = anestMethCode;
   }

   public String getAnestMethCode() {
      return this.anestMethCode;
   }

   public void setAnestMethName(String anestMethName) {
      this.anestMethName = anestMethName;
   }

   public String getAnestMethName() {
      return this.anestMethName;
   }

   public void setAnestName(String anestName) {
      this.anestName = anestName;
   }

   public String getAnestName() {
      return this.anestName;
   }

   public void setAnestCode(String anestCode) {
      this.anestCode = anestCode;
   }

   public String getAnestCode() {
      return this.anestCode;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("patientMainId", this.getPatientMainId()).append("patientId", this.getPatientId()).append("operType", this.getOperType()).append("oprName", this.getOprName()).append("oprDoctName", this.getOprDoctName()).append("oprBeginTime", this.getOprBeginTime()).append("oprDuraMins", this.getOprDuraMins()).append("oprDoctCode", this.getOprDoctCode()).append("oprIcd", this.getOprIcd()).append("oprLevelCode", this.getOprLevelCode()).append("oprLevel", this.getOprLevel()).append("oprInciCode", this.getOprInciCode()).append("oprHealCode", this.getOprHealCode()).append("anestMethCode", this.getAnestMethCode()).append("anestMethName", this.getAnestMethName()).append("anestName", this.getAnestName()).append("anestCode", this.getAnestCode()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).toString();
   }
}
