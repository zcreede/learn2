package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpOpe extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String opeId;
   @Excel(
      name = "病案ID"
   )
   private String recordId;
   @Excel(
      name = "手术序号"
   )
   private String opeNo;
   @Excel(
      name = "手术及操作编码"
   )
   private String oprIcd;
   @Excel(
      name = "手术及操作名称"
   )
   private String oprName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "手术及操作开始日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date oprBeginDatetime;
   @Excel(
      name = "手术级别编码"
   )
   private String oprLevelCode;
   @Excel(
      name = "手术级别"
   )
   private String oprLevel;
   @Excel(
      name = "主刀医师编码"
   )
   private String opeCode;
   @Excel(
      name = "主刀医师姓名"
   )
   private String opeName;
   @Excel(
      name = "Ⅰ助编码"
   )
   private String aid1Code;
   @Excel(
      name = "Ⅰ助姓名"
   )
   private String aid1Name;
   @Excel(
      name = "Ⅱ助编码"
   )
   private String aid2Code;
   @Excel(
      name = "Ⅱ助姓名"
   )
   private String aid2Name;
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
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
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
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date altDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "手术及操作结束日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date oprEndDatetime;
   @Excel(
      name = "麻醉风险等级ASA"
   )
   private String anestAsa;
   @Excel(
      name = "手术部位代码"
   )
   private String oprSiteCode;
   @Excel(
      name = "手术部位名称"
   )
   private String oprSiteName;
   @Excel(
      name = "手术风险等级代码“手术风险分级标准",
      readConverterExp = "N=NIS"
   )
   private String oprNnis;
   private String oprHealName;
   private String oprInciName;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Long visitId;
   private String patientName;
   private String opeType;
   private String opeMain;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date anestBeginTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date anestEndTime;
   private Long duration;
   private String durationUnit;

   public Date getAnestBeginTime() {
      return this.anestBeginTime;
   }

   public void setAnestBeginTime(Date anestBeginTime) {
      this.anestBeginTime = anestBeginTime;
   }

   public Date getAnestEndTime() {
      return this.anestEndTime;
   }

   public void setAnestEndTime(Date anestEndTime) {
      this.anestEndTime = anestEndTime;
   }

   public String getOpeMain() {
      return this.opeMain;
   }

   public void setOpeMain(String opeMain) {
      this.opeMain = opeMain;
   }

   public String getOpeType() {
      return this.opeType;
   }

   public void setOpeType(String opeType) {
      this.opeType = opeType;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Long getVisitId() {
      return this.visitId;
   }

   public void setVisitId(Long visitId) {
      this.visitId = visitId;
   }

   public String getOprInciName() {
      return this.oprInciName;
   }

   public void setOprInciName(String oprInciName) {
      this.oprInciName = oprInciName;
   }

   public String getOprHealName() {
      return this.oprHealName;
   }

   public void setOprHealName(String oprHealName) {
      this.oprHealName = oprHealName;
   }

   public void setOpeId(String opeId) {
      this.opeId = opeId;
   }

   public String getOpeId() {
      return this.opeId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setOpeNo(String opeNo) {
      this.opeNo = opeNo;
   }

   public String getOpeNo() {
      return this.opeNo;
   }

   public void setOprIcd(String oprIcd) {
      this.oprIcd = oprIcd;
   }

   public String getOprIcd() {
      return this.oprIcd;
   }

   public void setOprName(String oprName) {
      this.oprName = oprName;
   }

   public String getOprName() {
      return this.oprName;
   }

   public void setOprBeginDatetime(Date oprBeginDatetime) {
      this.oprBeginDatetime = oprBeginDatetime;
   }

   public Date getOprBeginDatetime() {
      return this.oprBeginDatetime;
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

   public void setOpeCode(String opeCode) {
      this.opeCode = opeCode;
   }

   public String getOpeCode() {
      return this.opeCode;
   }

   public void setOpeName(String opeName) {
      this.opeName = opeName;
   }

   public String getOpeName() {
      return this.opeName;
   }

   public void setAid1Code(String aid1Code) {
      this.aid1Code = aid1Code;
   }

   public String getAid1Code() {
      return this.aid1Code;
   }

   public void setAid1Name(String aid1Name) {
      this.aid1Name = aid1Name;
   }

   public String getAid1Name() {
      return this.aid1Name;
   }

   public void setAid2Code(String aid2Code) {
      this.aid2Code = aid2Code;
   }

   public String getAid2Code() {
      return this.aid2Code;
   }

   public void setAid2Name(String aid2Name) {
      this.aid2Name = aid2Name;
   }

   public String getAid2Name() {
      return this.aid2Name;
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

   public void setOprEndDatetime(Date oprEndDatetime) {
      this.oprEndDatetime = oprEndDatetime;
   }

   public Date getOprEndDatetime() {
      return this.oprEndDatetime;
   }

   public void setAnestAsa(String anestAsa) {
      this.anestAsa = anestAsa;
   }

   public String getAnestAsa() {
      return this.anestAsa;
   }

   public void setOprSiteCode(String oprSiteCode) {
      this.oprSiteCode = oprSiteCode;
   }

   public String getOprSiteCode() {
      return this.oprSiteCode;
   }

   public void setOprSiteName(String oprSiteName) {
      this.oprSiteName = oprSiteName;
   }

   public String getOprSiteName() {
      return this.oprSiteName;
   }

   public void setOprNnis(String oprNnis) {
      this.oprNnis = oprNnis;
   }

   public String getOprNnis() {
      return this.oprNnis;
   }

   public Long getDuration() {
      return this.duration;
   }

   public void setDuration(Long duration) {
      this.duration = duration;
   }

   public String getDurationUnit() {
      return this.durationUnit;
   }

   public void setDurationUnit(String durationUnit) {
      this.durationUnit = durationUnit;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("opeId", this.getOpeId()).append("recordId", this.getRecordId()).append("opeNo", this.getOpeNo()).append("oprIcd", this.getOprIcd()).append("oprName", this.getOprName()).append("oprBeginDatetime", this.getOprBeginDatetime()).append("oprLevelCode", this.getOprLevelCode()).append("oprLevel", this.getOprLevel()).append("opeCode", this.getOpeCode()).append("opeName", this.getOpeName()).append("aid1Code", this.getAid1Code()).append("aid1Name", this.getAid1Name()).append("aid2Code", this.getAid2Code()).append("aid2Name", this.getAid2Name()).append("oprInciCode", this.getOprInciCode()).append("oprHealCode", this.getOprHealCode()).append("anestMethCode", this.getAnestMethCode()).append("anestMethName", this.getAnestMethName()).append("anestName", this.getAnestName()).append("anestCode", this.getAnestCode()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("oprEndDatetime", this.getOprEndDatetime()).append("anestAsa", this.getAnestAsa()).append("oprSiteCode", this.getOprSiteCode()).append("oprSiteName", this.getOprSiteName()).append("oprNnis", this.getOprNnis()).append("oprHealName", this.getOprHealName()).append("oprInciCode", this.getOprInciCode()).toString();
   }
}
