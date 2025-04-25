package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AppItem extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String appCd;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "单据类型编号"
   )
   private String itemTypeCd;
   @Excel(
      name = "患者类型代码"
   )
   private String patTypeCd;
   @Excel(
      name = "患者类型名称"
   )
   private String patTypeName;
   @Excel(
      name = "医嘱编号"
   )
   private String maCd;
   @Excel(
      name = "医嘱类型编号"
   )
   private String maTypeCd;
   @Excel(
      name = "医嘱类型名称"
   )
   private String maTypeName;
   @Excel(
      name = "医嘱类别名称"
   )
   private String maClasCd;
   @Excel(
      name = "医嘱类别代码"
   )
   private String maClasName;
   @Excel(
      name = "医嘱类别代码       01：药疗，02：检查 03：检验 04：手术 05：处置 06：材料 07：嘱托 08：输血 09 护理 10：膳食 99：其他"
   )
   private String maTypeId;
   @Excel(
      name = "医嘱项目类型代码"
   )
   private String maItemCd;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "申请时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date appTime;
   @Excel(
      name = "申请科室编码"
   )
   private String appDeptCd;
   @Excel(
      name = "申请科室名称"
   )
   private String appDeptName;
   @Excel(
      name = "申请医生编码"
   )
   private String appDocCd;
   @Excel(
      name = "申请医生名称"
   )
   private String appDocName;
   @Excel(
      name = "标本编号"
   )
   private String specCd;
   @Excel(
      name = "标本名称"
   )
   private String specName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "采样时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date sampTime;
   @Excel(
      name = "采样护士编号"
   )
   private String sampNurCd;
   @Excel(
      name = "采样护士名称"
   )
   private String sampNurName;
   @Excel(
      name = "住院诊断"
   )
   private String inhosDiag;
   @Excel(
      name = "其他诊断"
   )
   private String otherDiag;
   @Excel(
      name = "单据状态"
   )
   private String itemState;
   @Excel(
      name = "送检目的"
   )
   private String asptAim;
   @Excel(
      name = "检查部位"
   )
   private String examPos;
   @Excel(
      name = "加急标志"
   )
   private String urgFlag;
   @Excel(
      name = "警告标志"
   )
   private String warnFlag;
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
   @Excel(
      name = "医嘱项目类型名称，即检查检验类别名称"
   )
   private String maItemName;
   @Excel(
      name = "单据类型名称"
   )
   private String itemTypeName;

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getAppCd() {
      return this.appCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setItemTypeCd(String itemTypeCd) {
      this.itemTypeCd = itemTypeCd;
   }

   public String getItemTypeCd() {
      return this.itemTypeCd;
   }

   public void setPatTypeCd(String patTypeCd) {
      this.patTypeCd = patTypeCd;
   }

   public String getPatTypeCd() {
      return this.patTypeCd;
   }

   public void setPatTypeName(String patTypeName) {
      this.patTypeName = patTypeName;
   }

   public String getPatTypeName() {
      return this.patTypeName;
   }

   public void setMaCd(String maCd) {
      this.maCd = maCd;
   }

   public String getMaCd() {
      return this.maCd;
   }

   public void setMaTypeCd(String maTypeCd) {
      this.maTypeCd = maTypeCd;
   }

   public String getMaTypeCd() {
      return this.maTypeCd;
   }

   public void setMaTypeName(String maTypeName) {
      this.maTypeName = maTypeName;
   }

   public String getMaTypeName() {
      return this.maTypeName;
   }

   public void setMaClasCd(String maClasCd) {
      this.maClasCd = maClasCd;
   }

   public String getMaClasCd() {
      return this.maClasCd;
   }

   public void setMaClasName(String maClasName) {
      this.maClasName = maClasName;
   }

   public String getMaClasName() {
      return this.maClasName;
   }

   public void setMaTypeId(String maTypeId) {
      this.maTypeId = maTypeId;
   }

   public String getMaTypeId() {
      return this.maTypeId;
   }

   public void setMaItemCd(String maItemCd) {
      this.maItemCd = maItemCd;
   }

   public String getMaItemCd() {
      return this.maItemCd;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setAppTime(Date appTime) {
      this.appTime = appTime;
   }

   public Date getAppTime() {
      return this.appTime;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptName(String appDeptName) {
      this.appDeptName = appDeptName;
   }

   public String getAppDeptName() {
      return this.appDeptName;
   }

   public void setAppDocCd(String appDocCd) {
      this.appDocCd = appDocCd;
   }

   public String getAppDocCd() {
      return this.appDocCd;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setSampTime(Date sampTime) {
      this.sampTime = sampTime;
   }

   public Date getSampTime() {
      return this.sampTime;
   }

   public void setSampNurCd(String sampNurCd) {
      this.sampNurCd = sampNurCd;
   }

   public String getSampNurCd() {
      return this.sampNurCd;
   }

   public void setSampNurName(String sampNurName) {
      this.sampNurName = sampNurName;
   }

   public String getSampNurName() {
      return this.sampNurName;
   }

   public void setInhosDiag(String inhosDiag) {
      this.inhosDiag = inhosDiag;
   }

   public String getInhosDiag() {
      return this.inhosDiag;
   }

   public void setOtherDiag(String otherDiag) {
      this.otherDiag = otherDiag;
   }

   public String getOtherDiag() {
      return this.otherDiag;
   }

   public void setItemState(String itemState) {
      this.itemState = itemState;
   }

   public String getItemState() {
      return this.itemState;
   }

   public void setAsptAim(String asptAim) {
      this.asptAim = asptAim;
   }

   public String getAsptAim() {
      return this.asptAim;
   }

   public void setExamPos(String examPos) {
      this.examPos = examPos;
   }

   public String getExamPos() {
      return this.examPos;
   }

   public void setUrgFlag(String urgFlag) {
      this.urgFlag = urgFlag;
   }

   public String getUrgFlag() {
      return this.urgFlag;
   }

   public void setWarnFlag(String warnFlag) {
      this.warnFlag = warnFlag;
   }

   public String getWarnFlag() {
      return this.warnFlag;
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

   public void setMaItemName(String maItemName) {
      this.maItemName = maItemName;
   }

   public String getMaItemName() {
      return this.maItemName;
   }

   public void setItemTypeName(String itemTypeName) {
      this.itemTypeName = itemTypeName;
   }

   public String getItemTypeName() {
      return this.itemTypeName;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("appCd", this.getAppCd()).append("orgCd", this.getOrgCd()).append("itemTypeCd", this.getItemTypeCd()).append("patTypeCd", this.getPatTypeCd()).append("patTypeName", this.getPatTypeName()).append("maCd", this.getMaCd()).append("maTypeCd", this.getMaTypeCd()).append("maTypeName", this.getMaTypeName()).append("maClasCd", this.getMaClasCd()).append("maClasName", this.getMaClasName()).append("maTypeId", this.getMaTypeId()).append("maItemCd", this.getMaItemCd()).append("patientId", this.getPatientId()).append("appTime", this.getAppTime()).append("appDeptCd", this.getAppDeptCd()).append("appDeptName", this.getAppDeptName()).append("appDocCd", this.getAppDocCd()).append("appDocName", this.getAppDocName()).append("specCd", this.getSpecCd()).append("specName", this.getSpecName()).append("sampTime", this.getSampTime()).append("sampNurCd", this.getSampNurCd()).append("sampNurName", this.getSampNurName()).append("inhosDiag", this.getInhosDiag()).append("otherDiag", this.getOtherDiag()).append("itemState", this.getItemState()).append("asptAim", this.getAsptAim()).append("examPos", this.getExamPos()).append("remark", this.getRemark()).append("urgFlag", this.getUrgFlag()).append("warnFlag", this.getWarnFlag()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("maItemName", this.getMaItemName()).append("itemTypeName", this.getItemTypeName()).toString();
   }
}
