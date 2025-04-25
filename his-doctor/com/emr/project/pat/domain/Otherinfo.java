package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Otherinfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long patientId;
   @Excel(
      name = "患者传染性标志"
   )
   private String catFlag;
   @Excel(
      name = "传染病名称"
   )
   private String catName;
   @Excel(
      name = "患者感染标志"
   )
   private String infectFlag;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "患者感染时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date infectTime;
   @Excel(
      name = "感染部位"
   )
   private String infectPart;
   @Excel(
      name = "过敏史标志"
   )
   private String alleFlag;
   @Excel(
      name = "过敏药物"
   )
   private String alleDrug;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
   @Excel(
      name = "修改人编码"
   )
   private String altPerCode;
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
      name = "创建人名称"
   )
   private String crePerName;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
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
      name = "手术标志",
      readConverterExp = "0=：未手术；1：手术"
   )
   private String operFlag;
   @Excel(
      name = "会诊标志",
      readConverterExp = "0=：未会诊；1：会诊"
   )
   private String consFlag;
   @Excel(
      name = "血透标志",
      readConverterExp = "0=：未血透；1：血透"
   )
   private String bloodFlag;
   @Excel(
      name = "介入标志",
      readConverterExp = "0=：无介入操作；1：有介入操作"
   )
   private String interFlag;
   @Excel(
      name = "麻醉标志",
      readConverterExp = "0=：无麻醉；1：有麻醉"
   )
   private String anesFlag;
   @Excel(
      name = "死亡标志",
      readConverterExp = "0=：正常；1：死亡"
   )
   private String dieFlag;
   @Excel(
      name = "转科标志",
      readConverterExp = "0=：未转科；1：转科"
   )
   private String changeFlag;
   @Excel(
      name = "输血标志",
      readConverterExp = "0=：未输血；1：输血"
   )
   private String bloodTrans;
   @Excel(
      name = "病人其他信息修改标志，默认0，当为1时只同步BB_CONFIG_SOURCE.UPDATEFIELD中的字段数据"
   )
   private String modiFlag;
   @Excel(
      name = "单病种标志",
      readConverterExp = "0=：否；1：是"
   )
   private String singleFlag;
   @Excel(
      name = "多重耐药菌标志",
      readConverterExp = "1=：确定；2：取消"
   )
   private String medicineFlag;
   private String rescueFlag;
   private String illFlag;
   private String dyFlag;
   private String antiFlag;
   private String crisisFlag;

   public static long getSerialVersionUID() {
      return 1L;
   }

   public String getRescueFlag() {
      return this.rescueFlag;
   }

   public void setRescueFlag(String rescueFlag) {
      this.rescueFlag = rescueFlag;
   }

   public String getIllFlag() {
      return this.illFlag;
   }

   public void setIllFlag(String illFlag) {
      this.illFlag = illFlag;
   }

   public String getDyFlag() {
      return this.dyFlag;
   }

   public void setDyFlag(String dyFlag) {
      this.dyFlag = dyFlag;
   }

   public String getAntiFlag() {
      return this.antiFlag;
   }

   public void setAntiFlag(String antiFlag) {
      this.antiFlag = antiFlag;
   }

   public String getCrisisFlag() {
      return this.crisisFlag;
   }

   public void setCrisisFlag(String crisisFlag) {
      this.crisisFlag = crisisFlag;
   }

   public void setPatientId(Long patientId) {
      this.patientId = patientId;
   }

   public Long getPatientId() {
      return this.patientId;
   }

   public void setCatFlag(String catFlag) {
      this.catFlag = catFlag;
   }

   public String getCatFlag() {
      return this.catFlag;
   }

   public void setCatName(String catName) {
      this.catName = catName;
   }

   public String getCatName() {
      return this.catName;
   }

   public void setInfectFlag(String infectFlag) {
      this.infectFlag = infectFlag;
   }

   public String getInfectFlag() {
      return this.infectFlag;
   }

   public void setInfectTime(Date infectTime) {
      this.infectTime = infectTime;
   }

   public Date getInfectTime() {
      return this.infectTime;
   }

   public void setInfectPart(String infectPart) {
      this.infectPart = infectPart;
   }

   public String getInfectPart() {
      return this.infectPart;
   }

   public void setAlleFlag(String alleFlag) {
      this.alleFlag = alleFlag;
   }

   public String getAlleFlag() {
      return this.alleFlag;
   }

   public void setAlleDrug(String alleDrug) {
      this.alleDrug = alleDrug;
   }

   public String getAlleDrug() {
      return this.alleDrug;
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

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setBloodFlag(String bloodFlag) {
      this.bloodFlag = bloodFlag;
   }

   public String getBloodFlag() {
      return this.bloodFlag;
   }

   public void setInterFlag(String interFlag) {
      this.interFlag = interFlag;
   }

   public String getInterFlag() {
      return this.interFlag;
   }

   public void setAnesFlag(String anesFlag) {
      this.anesFlag = anesFlag;
   }

   public String getAnesFlag() {
      return this.anesFlag;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public void setBloodTrans(String bloodTrans) {
      this.bloodTrans = bloodTrans;
   }

   public String getBloodTrans() {
      return this.bloodTrans;
   }

   public void setModiFlag(String modiFlag) {
      this.modiFlag = modiFlag;
   }

   public String getModiFlag() {
      return this.modiFlag;
   }

   public void setSingleFlag(String singleFlag) {
      this.singleFlag = singleFlag;
   }

   public String getSingleFlag() {
      return this.singleFlag;
   }

   public void setMedicineFlag(String medicineFlag) {
      this.medicineFlag = medicineFlag;
   }

   public String getMedicineFlag() {
      return this.medicineFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("patientId", this.getPatientId()).append("catFlag", this.getCatFlag()).append("catName", this.getCatName()).append("infectFlag", this.getInfectFlag()).append("infectTime", this.getInfectTime()).append("infectPart", this.getInfectPart()).append("alleFlag", this.getAlleFlag()).append("alleDrug", this.getAlleDrug()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("operFlag", this.getOperFlag()).append("consFlag", this.getConsFlag()).append("bloodFlag", this.getBloodFlag()).append("interFlag", this.getInterFlag()).append("anesFlag", this.getAnesFlag()).append("dieFlag", this.getDieFlag()).append("changeFlag", this.getChangeFlag()).append("bloodTrans", this.getBloodTrans()).append("modiFlag", this.getModiFlag()).append("singleFlag", this.getSingleFlag()).append("medicineFlag", this.getMedicineFlag()).toString();
   }
}
