package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Transinfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long trId;
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "转入医师姓名"
   )
   private String tiDocName;
   @Excel(
      name = "转入医师编号"
   )
   private String tiDocCd;
   @Excel(
      name = "转入床位号"
   )
   private String tiBedNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "转入日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date tiTime;
   @Excel(
      name = "转入科室"
   )
   private String tiDeptName;
   @Excel(
      name = "转入科室代码"
   )
   private String tiDeptCd;
   @Excel(
      name = "转入病区代码"
   )
   private String tiAreaCd;
   @Excel(
      name = "转出病区名称"
   )
   private String toAreaName;
   @Excel(
      name = "转出医师编号"
   )
   private String toDocCd;
   @Excel(
      name = "转出医师姓名"
   )
   private String toDocName;
   @Excel(
      name = "转出床位号"
   )
   private String toBedNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "转出日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date toTime;
   @Excel(
      name = "转出科室"
   )
   private String toDeptName;
   @Excel(
      name = "转出科室代码"
   )
   private String toDeptCd;
   @Excel(
      name = "转入病区名称"
   )
   private String tiAreaName;
   @Excel(
      name = "转出病区代码"
   )
   private String toAreaCd;
   @Excel(
      name = "入科、转入"
   )
   private String tranInState;
   @Excel(
      name = "出科、转出"
   )
   private String tranOutState;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "修改日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date altDate;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "创建日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date creDate;
   private String serviceTypeCode;

   public String getServiceTypeCode() {
      return this.serviceTypeCode;
   }

   public void setServiceTypeCode(String serviceTypeCode) {
      this.serviceTypeCode = serviceTypeCode;
   }

   public void setTrId(Long trId) {
      this.trId = trId;
   }

   public Long getTrId() {
      return this.trId;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getOrgName() {
      return this.orgName;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setTiDocName(String tiDocName) {
      this.tiDocName = tiDocName;
   }

   public String getTiDocName() {
      return this.tiDocName;
   }

   public void setTiDocCd(String tiDocCd) {
      this.tiDocCd = tiDocCd;
   }

   public String getTiDocCd() {
      return this.tiDocCd;
   }

   public void setTiBedNo(String tiBedNo) {
      this.tiBedNo = tiBedNo;
   }

   public String getTiBedNo() {
      return this.tiBedNo;
   }

   public void setTiTime(Date tiTime) {
      this.tiTime = tiTime;
   }

   public Date getTiTime() {
      return this.tiTime;
   }

   public void setTiDeptName(String tiDeptName) {
      this.tiDeptName = tiDeptName;
   }

   public String getTiDeptName() {
      return this.tiDeptName;
   }

   public void setTiDeptCd(String tiDeptCd) {
      this.tiDeptCd = tiDeptCd;
   }

   public String getTiDeptCd() {
      return this.tiDeptCd;
   }

   public void setTiAreaCd(String tiAreaCd) {
      this.tiAreaCd = tiAreaCd;
   }

   public String getTiAreaCd() {
      return this.tiAreaCd;
   }

   public void setToAreaName(String toAreaName) {
      this.toAreaName = toAreaName;
   }

   public String getToAreaName() {
      return this.toAreaName;
   }

   public void setToDocCd(String toDocCd) {
      this.toDocCd = toDocCd;
   }

   public String getToDocCd() {
      return this.toDocCd;
   }

   public void setToDocName(String toDocName) {
      this.toDocName = toDocName;
   }

   public String getToDocName() {
      return this.toDocName;
   }

   public void setToBedNo(String toBedNo) {
      this.toBedNo = toBedNo;
   }

   public String getToBedNo() {
      return this.toBedNo;
   }

   public void setToTime(Date toTime) {
      this.toTime = toTime;
   }

   public Date getToTime() {
      return this.toTime;
   }

   public void setToDeptName(String toDeptName) {
      this.toDeptName = toDeptName;
   }

   public String getToDeptName() {
      return this.toDeptName;
   }

   public void setToDeptCd(String toDeptCd) {
      this.toDeptCd = toDeptCd;
   }

   public String getToDeptCd() {
      return this.toDeptCd;
   }

   public void setTiAreaName(String tiAreaName) {
      this.tiAreaName = tiAreaName;
   }

   public String getTiAreaName() {
      return this.tiAreaName;
   }

   public void setToAreaCd(String toAreaCd) {
      this.toAreaCd = toAreaCd;
   }

   public String getToAreaCd() {
      return this.toAreaCd;
   }

   public void setTranInState(String tranInState) {
      this.tranInState = tranInState;
   }

   public String getTranInState() {
      return this.tranInState;
   }

   public void setTranOutState(String tranOutState) {
      this.tranOutState = tranOutState;
   }

   public String getTranOutState() {
      return this.tranOutState;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public Date getAltDate() {
      return this.altDate;
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
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("trId", this.getTrId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("patientName", this.getPatientName()).append("tiDocName", this.getTiDocName()).append("tiDocCd", this.getTiDocCd()).append("tiBedNo", this.getTiBedNo()).append("tiTime", this.getTiTime()).append("tiDeptName", this.getTiDeptName()).append("tiDeptCd", this.getTiDeptCd()).append("tiAreaCd", this.getTiAreaCd()).append("toAreaName", this.getToAreaName()).append("toDocCd", this.getToDocCd()).append("toDocName", this.getToDocName()).append("toBedNo", this.getToBedNo()).append("toTime", this.getToTime()).append("toDeptName", this.getToDeptName()).append("toDeptCd", this.getToDeptCd()).append("tiAreaName", this.getTiAreaName()).append("toAreaCd", this.getToAreaCd()).append("tranInState", this.getTranInState()).append("tranOutState", this.getTranOutState()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).toString();
   }
}
