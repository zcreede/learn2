package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Doctorder extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String id;
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
      name = "医嘱编号"
   )
   private String maCd;
   @Excel(
      name = "医嘱组号"
   )
   private Long maNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "医嘱开立日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date maOpenTime;
   @Excel(
      name = "医嘱开立科室编号"
   )
   private String maDeptCd;
   @Excel(
      name = "开立医嘱的科室名称"
   )
   private String maDeptName;
   @Excel(
      name = "医嘱开立医师编号"
   )
   private String maDocCd;
   @Excel(
      name = "医嘱开立者签名"
   )
   private String maDocName;
   @Excel(
      name = "责任医师编号"
   )
   private String dutyDocCd;
   @Excel(
      name = "责任医师姓名"
   )
   private String dutyDocName;
   @Excel(
      name = "责任护士姓名"
   )
   private String dutyNurCd;
   @Excel(
      name = "责任护士编码"
   )
   private String dutyNurName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "开始时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date beginTime;
   @Excel(
      name = "开始确认护士编号"
   )
   private String bconNurCd;
   @Excel(
      name = "开始确认护士"
   )
   private String bconNurName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "开始确认时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date bconTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "首次执行时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date firExeTime;
   @Excel(
      name = "首次执行人"
   )
   private String firExePerson;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "医嘱执行日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date maExeTime;
   @Excel(
      name = "医嘱执行者签名"
   )
   private String maExePerson;
   @Excel(
      name = "患者所在科室编号"
   )
   private String inDeptCd;
   @Excel(
      name = "患者所在科室"
   )
   private String inDeptName;
   @Excel(
      name = "医嘱类型编号 1：长期医嘱 2：临时医嘱 3：出院带药 4：长期备用 5：临时备用  8：汤剂医嘱 9：其他"
   )
   private String maTypeCd;
   @Excel(
      name = "医嘱类型名称"
   )
   private String maTypeName;
   @Excel(
      name = "临床医嘱类别的分类代码 01：药疗，02：检查 03：检验 04：手术 05：处置 06：材料 07：嘱托 08：输血 09 护理 10：膳食 99：其他"
   )
   private String maTypeId;
   @Excel(
      name = "医嘱项目类型代码"
   )
   private String maItemCd;
   @Excel(
      name = "医嘱状态编号 tsl_bas_dictionary.typeid=4292"
   )
   private String maStateCd;
   @Excel(
      name = "医嘱状态名称"
   )
   private String maStateName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "医嘱停止日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date maStopTime;
   @Excel(
      name = "医嘱停止原因(1.临嘱执行，2.长嘱正常停，3.欠费停，4.库存不足停)"
   )
   private String maStopReason;
   @Excel(
      name = "停止医师编号"
   )
   private String stopDocCd;
   @Excel(
      name = "停止医师"
   )
   private String stopDocName;
   @Excel(
      name = "停止确认护士编号"
   )
   private String stopNurCd;
   @Excel(
      name = "停止确认护士"
   )
   private String stopNurName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "停止确认时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date stopTime;
   @Excel(
      name = "处方标志"
   )
   private String presFlag;
   @Excel(
      name = "持续时间"
   )
   private Long lastTime;
   @Excel(
      name = "持续时间单位"
   )
   private String lastTimeU;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "用药时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date useDrugTime;
   @Excel(
      name = "摆药标志"
   )
   private String putdFlag;
   @Excel(
      name = "打印标志"
   )
   private String printFlag;
   @Excel(
      name = "领药方式"
   )
   private String getdType;
   @Excel(
      name = "转科出院标志"
   )
   private String outCdFlag;
   @Excel(
      name = "预约标志"
   )
   private String orderFlag;
   @Excel(
      name = "医嘱重整标志"
   )
   private String rmaFlag;
   @Excel(
      name = "用药时间说明"
   )
   private String udTimeExp;
   @Excel(
      name = "草药剂数"
   )
   private Long herbDose;
   @Excel(
      name = "是否草药"
   )
   private String isHerb;
   @Excel(
      name = "方剂编号"
   )
   private String presCd;
   @Excel(
      name = "方剂名称"
   )
   private String presName;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "医嘱取消日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date maCanTime;
   @Excel(
      name = "手术标志"
   )
   private String operFlag;
   @Excel(
      name = "医嘱取消医师姓名"
   )
   private String maCanDocName;
   @Excel(
      name = "医嘱取消医师编号"
   )
   private String maCanDocCd;
   @Excel(
      name = "麻醉处方代办人身份证号"
   )
   private String ageIdCard;
   @Excel(
      name = "麻醉处方代办人"
   )
   private String ageName;
   @Excel(
      name = "执行次数"
   )
   private Long exeTimes;
   @Excel(
      name = "婴儿住院号"
   )
   private String babyInhosNo;
   @Excel(
      name = "录入电脑IP"
   )
   private String inCompIp;
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
   @Excel(
      name = "死亡医嘱标志",
      readConverterExp = "1=：死亡医嘱"
   )
   private String dieFlag;

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
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

   public void setMaCd(String maCd) {
      this.maCd = maCd;
   }

   public String getMaCd() {
      return this.maCd;
   }

   public void setMaNo(Long maNo) {
      this.maNo = maNo;
   }

   public Long getMaNo() {
      return this.maNo;
   }

   public void setMaOpenTime(Date maOpenTime) {
      this.maOpenTime = maOpenTime;
   }

   public Date getMaOpenTime() {
      return this.maOpenTime;
   }

   public void setMaDeptCd(String maDeptCd) {
      this.maDeptCd = maDeptCd;
   }

   public String getMaDeptCd() {
      return this.maDeptCd;
   }

   public void setMaDeptName(String maDeptName) {
      this.maDeptName = maDeptName;
   }

   public String getMaDeptName() {
      return this.maDeptName;
   }

   public void setMaDocCd(String maDocCd) {
      this.maDocCd = maDocCd;
   }

   public String getMaDocCd() {
      return this.maDocCd;
   }

   public void setMaDocName(String maDocName) {
      this.maDocName = maDocName;
   }

   public String getMaDocName() {
      return this.maDocName;
   }

   public void setDutyDocCd(String dutyDocCd) {
      this.dutyDocCd = dutyDocCd;
   }

   public String getDutyDocCd() {
      return this.dutyDocCd;
   }

   public void setDutyDocName(String dutyDocName) {
      this.dutyDocName = dutyDocName;
   }

   public String getDutyDocName() {
      return this.dutyDocName;
   }

   public void setDutyNurCd(String dutyNurCd) {
      this.dutyNurCd = dutyNurCd;
   }

   public String getDutyNurCd() {
      return this.dutyNurCd;
   }

   public void setDutyNurName(String dutyNurName) {
      this.dutyNurName = dutyNurName;
   }

   public String getDutyNurName() {
      return this.dutyNurName;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBconNurCd(String bconNurCd) {
      this.bconNurCd = bconNurCd;
   }

   public String getBconNurCd() {
      return this.bconNurCd;
   }

   public void setBconNurName(String bconNurName) {
      this.bconNurName = bconNurName;
   }

   public String getBconNurName() {
      return this.bconNurName;
   }

   public void setBconTime(Date bconTime) {
      this.bconTime = bconTime;
   }

   public Date getBconTime() {
      return this.bconTime;
   }

   public void setFirExeTime(Date firExeTime) {
      this.firExeTime = firExeTime;
   }

   public Date getFirExeTime() {
      return this.firExeTime;
   }

   public void setFirExePerson(String firExePerson) {
      this.firExePerson = firExePerson;
   }

   public String getFirExePerson() {
      return this.firExePerson;
   }

   public void setMaExeTime(Date maExeTime) {
      this.maExeTime = maExeTime;
   }

   public Date getMaExeTime() {
      return this.maExeTime;
   }

   public void setMaExePerson(String maExePerson) {
      this.maExePerson = maExePerson;
   }

   public String getMaExePerson() {
      return this.maExePerson;
   }

   public void setInDeptCd(String inDeptCd) {
      this.inDeptCd = inDeptCd;
   }

   public String getInDeptCd() {
      return this.inDeptCd;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public String getInDeptName() {
      return this.inDeptName;
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

   public void setMaStateCd(String maStateCd) {
      this.maStateCd = maStateCd;
   }

   public String getMaStateCd() {
      return this.maStateCd;
   }

   public void setMaStateName(String maStateName) {
      this.maStateName = maStateName;
   }

   public String getMaStateName() {
      return this.maStateName;
   }

   public void setMaStopTime(Date maStopTime) {
      this.maStopTime = maStopTime;
   }

   public Date getMaStopTime() {
      return this.maStopTime;
   }

   public void setMaStopReason(String maStopReason) {
      this.maStopReason = maStopReason;
   }

   public String getMaStopReason() {
      return this.maStopReason;
   }

   public void setStopDocCd(String stopDocCd) {
      this.stopDocCd = stopDocCd;
   }

   public String getStopDocCd() {
      return this.stopDocCd;
   }

   public void setStopDocName(String stopDocName) {
      this.stopDocName = stopDocName;
   }

   public String getStopDocName() {
      return this.stopDocName;
   }

   public void setStopNurCd(String stopNurCd) {
      this.stopNurCd = stopNurCd;
   }

   public String getStopNurCd() {
      return this.stopNurCd;
   }

   public void setStopNurName(String stopNurName) {
      this.stopNurName = stopNurName;
   }

   public String getStopNurName() {
      return this.stopNurName;
   }

   public void setStopTime(Date stopTime) {
      this.stopTime = stopTime;
   }

   public Date getStopTime() {
      return this.stopTime;
   }

   public void setPresFlag(String presFlag) {
      this.presFlag = presFlag;
   }

   public String getPresFlag() {
      return this.presFlag;
   }

   public void setLastTime(Long lastTime) {
      this.lastTime = lastTime;
   }

   public Long getLastTime() {
      return this.lastTime;
   }

   public void setLastTimeU(String lastTimeU) {
      this.lastTimeU = lastTimeU;
   }

   public String getLastTimeU() {
      return this.lastTimeU;
   }

   public void setUseDrugTime(Date useDrugTime) {
      this.useDrugTime = useDrugTime;
   }

   public Date getUseDrugTime() {
      return this.useDrugTime;
   }

   public void setPutdFlag(String putdFlag) {
      this.putdFlag = putdFlag;
   }

   public String getPutdFlag() {
      return this.putdFlag;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setGetdType(String getdType) {
      this.getdType = getdType;
   }

   public String getGetdType() {
      return this.getdType;
   }

   public void setOutCdFlag(String outCdFlag) {
      this.outCdFlag = outCdFlag;
   }

   public String getOutCdFlag() {
      return this.outCdFlag;
   }

   public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
   }

   public String getOrderFlag() {
      return this.orderFlag;
   }

   public void setRmaFlag(String rmaFlag) {
      this.rmaFlag = rmaFlag;
   }

   public String getRmaFlag() {
      return this.rmaFlag;
   }

   public void setUdTimeExp(String udTimeExp) {
      this.udTimeExp = udTimeExp;
   }

   public String getUdTimeExp() {
      return this.udTimeExp;
   }

   public void setHerbDose(Long herbDose) {
      this.herbDose = herbDose;
   }

   public Long getHerbDose() {
      return this.herbDose;
   }

   public void setIsHerb(String isHerb) {
      this.isHerb = isHerb;
   }

   public String getIsHerb() {
      return this.isHerb;
   }

   public void setPresCd(String presCd) {
      this.presCd = presCd;
   }

   public String getPresCd() {
      return this.presCd;
   }

   public void setPresName(String presName) {
      this.presName = presName;
   }

   public String getPresName() {
      return this.presName;
   }

   public void setMaCanTime(Date maCanTime) {
      this.maCanTime = maCanTime;
   }

   public Date getMaCanTime() {
      return this.maCanTime;
   }

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setMaCanDocName(String maCanDocName) {
      this.maCanDocName = maCanDocName;
   }

   public String getMaCanDocName() {
      return this.maCanDocName;
   }

   public void setMaCanDocCd(String maCanDocCd) {
      this.maCanDocCd = maCanDocCd;
   }

   public String getMaCanDocCd() {
      return this.maCanDocCd;
   }

   public void setAgeIdCard(String ageIdCard) {
      this.ageIdCard = ageIdCard;
   }

   public String getAgeIdCard() {
      return this.ageIdCard;
   }

   public void setAgeName(String ageName) {
      this.ageName = ageName;
   }

   public String getAgeName() {
      return this.ageName;
   }

   public void setExeTimes(Long exeTimes) {
      this.exeTimes = exeTimes;
   }

   public Long getExeTimes() {
      return this.exeTimes;
   }

   public void setBabyInhosNo(String babyInhosNo) {
      this.babyInhosNo = babyInhosNo;
   }

   public String getBabyInhosNo() {
      return this.babyInhosNo;
   }

   public void setInCompIp(String inCompIp) {
      this.inCompIp = inCompIp;
   }

   public String getInCompIp() {
      return this.inCompIp;
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

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientId", this.getPatientId()).append("maCd", this.getMaCd()).append("maNo", this.getMaNo()).append("maOpenTime", this.getMaOpenTime()).append("maDeptCd", this.getMaDeptCd()).append("maDeptName", this.getMaDeptName()).append("maDocCd", this.getMaDocCd()).append("maDocName", this.getMaDocName()).append("dutyDocCd", this.getDutyDocCd()).append("dutyDocName", this.getDutyDocName()).append("dutyNurCd", this.getDutyNurCd()).append("dutyNurName", this.getDutyNurName()).append("beginTime", this.getBeginTime()).append("bconNurCd", this.getBconNurCd()).append("bconNurName", this.getBconNurName()).append("bconTime", this.getBconTime()).append("firExeTime", this.getFirExeTime()).append("firExePerson", this.getFirExePerson()).append("maExeTime", this.getMaExeTime()).append("maExePerson", this.getMaExePerson()).append("inDeptCd", this.getInDeptCd()).append("inDeptName", this.getInDeptName()).append("maTypeCd", this.getMaTypeCd()).append("maTypeName", this.getMaTypeName()).append("maTypeId", this.getMaTypeId()).append("maItemCd", this.getMaItemCd()).append("maStateCd", this.getMaStateCd()).append("maStateName", this.getMaStateName()).append("maStopTime", this.getMaStopTime()).append("maStopReason", this.getMaStopReason()).append("stopDocCd", this.getStopDocCd()).append("stopDocName", this.getStopDocName()).append("stopNurCd", this.getStopNurCd()).append("stopNurName", this.getStopNurName()).append("stopTime", this.getStopTime()).append("presFlag", this.getPresFlag()).append("lastTime", this.getLastTime()).append("lastTimeU", this.getLastTimeU()).append("useDrugTime", this.getUseDrugTime()).append("putdFlag", this.getPutdFlag()).append("printFlag", this.getPrintFlag()).append("getdType", this.getGetdType()).append("outCdFlag", this.getOutCdFlag()).append("orderFlag", this.getOrderFlag()).append("rmaFlag", this.getRmaFlag()).append("udTimeExp", this.getUdTimeExp()).append("herbDose", this.getHerbDose()).append("isHerb", this.getIsHerb()).append("presCd", this.getPresCd()).append("presName", this.getPresName()).append("maCanTime", this.getMaCanTime()).append("operFlag", this.getOperFlag()).append("maCanDocName", this.getMaCanDocName()).append("maCanDocCd", this.getMaCanDocCd()).append("ageIdCard", this.getAgeIdCard()).append("ageName", this.getAgeName()).append("exeTimes", this.getExeTimes()).append("babyInhosNo", this.getBabyInhosNo()).append("inCompIp", this.getInCompIp()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("dieFlag", this.getDieFlag()).toString();
   }
}
