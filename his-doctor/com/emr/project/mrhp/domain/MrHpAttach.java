package com.emr.project.mrhp.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MrHpAttach extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String recordId;
   @Excel(
      name = "患者就诊ID"
   )
   private String patientId;
   @Excel(
      name = "危重病历"
   )
   private String isCriRecord;
   @Excel(
      name = "入院病情代码"
   )
   private String inhosCondCd;
   @Excel(
      name = "入院时病情情况"
   )
   private String inhosCond;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "确诊日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date diaDate;
   @Excel(
      name = "门诊诊断与出院诊断符合情况"
   )
   private String outDisDia;
   @Excel(
      name = "入院与出院诊断符合情况"
   )
   private String admDisDia;
   @Excel(
      name = "术前与术后诊断符合情况"
   )
   private String prePosDia;
   @Excel(
      name = "临床诊断与病理诊断符合情况"
   )
   private String clinPathDia;
   @Excel(
      name = "医学影像与病理诊断符合情况"
   )
   private String medPathDia;
   @Excel(
      name = "恶性肿瘤术前诊断与病理诊断符合情况"
   )
   private String prePathDia;
   @Excel(
      name = "主诊断治疗效果"
   )
   private String diaEff;
   @Excel(
      name = "主要诊断依据名称"
   )
   private String diaBasName;
   @Excel(
      name = "是否1-31天内同一病再入院  0.当天   1.15天内     2.31天内    3.否"
   )
   private String readmFlag;
   @Excel(
      name = "呼吸机使用时间--天"
   )
   private Long ventilatorD;
   @Excel(
      name = "呼吸机使用时间--时"
   )
   private Long ventilatorH;
   @Excel(
      name = "呼吸机使用时间--分"
   )
   private Long ventilatorMi;
   @Excel(
      name = "有创呼吸机使用时间--天"
   )
   private Long invasiveVentilatorD;
   @Excel(
      name = "有创呼吸机使用时间--时"
   )
   private Long invasiveVentilatorH;
   @Excel(
      name = "有创呼吸机使用时间--分"
   )
   private Long invasiveVentilatorMi;
   @Excel(
      name = "日常生活能力评定量表得分",
      readConverterExp = "入=院"
   )
   private Long adlInhos;
   @Excel(
      name = "日常生活能力评定量表得分(出院)"
   )
   private Long adlOuthos;
   @Excel(
      name = "抢救次数"
   )
   private Long saveTimes;
   @Excel(
      name = "抢救成功次数"
   )
   private Long saveSuccessTimes;
   @Excel(
      name = "是否临床路径病种",
      readConverterExp = "1=.是,2=.否"
   )
   private String iscp;
   @Excel(
      name = "肿瘤分期_T(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)"
   )
   private String stagingT;
   @Excel(
      name = "肿瘤分期_N(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)"
   )
   private String stagingN;
   @Excel(
      name = "肿瘤分期_M(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)"
   )
   private String stagingM;
   @Excel(
      name = "肿瘤分期(0.0期  1. Ⅰ期 2.Ⅱ期 3.Ⅲ期 4.Ⅳ期 5.不详)"
   )
   private String staging;
   @Excel(
      name = "术前住院天数"
   )
   private Long preoperHospD;
   @Excel(
      name = "是否发生术后并发症"
   )
   private String isPostoperComp;
   @Excel(
      name = "是否发生压疮"
   )
   private String isPressure;
   @Excel(
      name = "压疮等级"
   )
   private String pressureGrade;
   @Excel(
      name = "压疮原因"
   )
   private String pressureCaus;
   @Excel(
      name = "压疮部位"
   )
   private String pressureSite;
   @Excel(
      name = "是否跌倒/坠床"
   )
   private String isFall;
   @Excel(
      name = "跌倒等级"
   )
   private String fallGrade;
   @Excel(
      name = "跌倒原因"
   )
   private String fallCaus;
   @Excel(
      name = "输液情况"
   )
   private String isInfuse;
   @Excel(
      name = "输液反应"
   )
   private String isInfuseReac;
   @Excel(
      name = "是否随诊"
   )
   private String isFollowUp;
   @Excel(
      name = "随诊期限-年"
   )
   private String followIntervalY;
   @Excel(
      name = "使用抗菌药物"
   )
   private String isUseAnti;
   @Excel(
      name = "抗菌药物用药目的(1治疗性用药、2预防性用药）"
   )
   private String useAntiPurp;
   @Excel(
      name = "抗菌药物用药方案"
   )
   private String useAntiSche;
   @Excel(
      name = "抗菌药物使用天数"
   )
   private Long useAntiDays;
   @Excel(
      name = "特级护理天数"
   )
   private Long sprCareD;
   @Excel(
      name = "一级护理天"
   )
   private Long priCareD;
   @Excel(
      name = "二级护理天数"
   )
   private Long secCareD;
   @Excel(
      name = "三级护理天数"
   )
   private Long terCareD;
   @Excel(
      name = "创建人编码"
   )
   private String crePerCode;
   @Excel(
      name = "创建人名称"
   )
   private String crePerName;
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
      name = "修改人编码"
   )
   private String altPerCode;
   @Excel(
      name = "修改人姓名"
   )
   private String altPerName;
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
      name = "疑难病历"
   )
   private String isDiffRecord;
   @Excel(
      name = "急症病历"
   )
   private String isEmeRecord;
   @Excel(
      name = "科研病历"
   )
   private String isResRecord;
   @Excel(
      name = "主要诊断依据编码"
   )
   private String diaBasCode;
   @Excel(
      name = "分化程度代码"
   )
   private String diffProCode;
   @Excel(
      name = "分化程度名称"
   )
   private String diffProName;
   @Excel(
      name = "手术类型 0.非手术患者  1.急诊手术   2.择期手术"
   )
   private String opeType;
   @Excel(
      name = "本院第一例"
   )
   private String firstRecord;
   @Excel(
      name = "HBsAg"
   )
   private String hbsag;
   @Excel(
      name = "HCV-Ab"
   )
   private String hcvAb;
   @Excel(
      name = "HIV-Ab"
   )
   private String hivAb;
   @Excel(
      name = "输血反应1.无  2.有"
   )
   private String transfusionReaction;
   @Excel(
      name = "输红细胞量(单位)"
   )
   private Long redBloodCell;
   @Excel(
      name = "输血小板量(袋)"
   )
   private Long platelet;
   @Excel(
      name = "输血浆量(单位)"
   )
   private Long plasma;
   @Excel(
      name = "输全血量(单位)"
   )
   private Long wholeBlood;
   @Excel(
      name = "自体回收(ml)"
   )
   private Long selfBolld;
   @Excel(
      name = "输其它血制品量(ml)"
   )
   private Long otherBlood;
   @Excel(
      name = "随诊期限-月"
   )
   private String followIntervalM;
   @Excel(
      name = "随诊期限-天"
   )
   private String followIntervalD;

   public Long getInvasiveVentilatorD() {
      return this.invasiveVentilatorD;
   }

   public void setInvasiveVentilatorD(Long invasiveVentilatorD) {
      this.invasiveVentilatorD = invasiveVentilatorD;
   }

   public Long getInvasiveVentilatorH() {
      return this.invasiveVentilatorH;
   }

   public void setInvasiveVentilatorH(Long invasiveVentilatorH) {
      this.invasiveVentilatorH = invasiveVentilatorH;
   }

   public Long getInvasiveVentilatorMi() {
      return this.invasiveVentilatorMi;
   }

   public void setInvasiveVentilatorMi(Long invasiveVentilatorMi) {
      this.invasiveVentilatorMi = invasiveVentilatorMi;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setIsCriRecord(String isCriRecord) {
      this.isCriRecord = isCriRecord;
   }

   public String getIsCriRecord() {
      return this.isCriRecord;
   }

   public void setInhosCondCd(String inhosCondCd) {
      this.inhosCondCd = inhosCondCd;
   }

   public String getInhosCondCd() {
      return this.inhosCondCd;
   }

   public void setInhosCond(String inhosCond) {
      this.inhosCond = inhosCond;
   }

   public String getInhosCond() {
      return this.inhosCond;
   }

   public void setDiaDate(Date diaDate) {
      this.diaDate = diaDate;
   }

   public Date getDiaDate() {
      return this.diaDate;
   }

   public void setOutDisDia(String outDisDia) {
      this.outDisDia = outDisDia;
   }

   public String getOutDisDia() {
      return this.outDisDia;
   }

   public void setAdmDisDia(String admDisDia) {
      this.admDisDia = admDisDia;
   }

   public String getAdmDisDia() {
      return this.admDisDia;
   }

   public void setPrePosDia(String prePosDia) {
      this.prePosDia = prePosDia;
   }

   public String getPrePosDia() {
      return this.prePosDia;
   }

   public void setClinPathDia(String clinPathDia) {
      this.clinPathDia = clinPathDia;
   }

   public String getClinPathDia() {
      return this.clinPathDia;
   }

   public void setMedPathDia(String medPathDia) {
      this.medPathDia = medPathDia;
   }

   public String getMedPathDia() {
      return this.medPathDia;
   }

   public void setPrePathDia(String prePathDia) {
      this.prePathDia = prePathDia;
   }

   public String getPrePathDia() {
      return this.prePathDia;
   }

   public void setDiaEff(String diaEff) {
      this.diaEff = diaEff;
   }

   public String getDiaEff() {
      return this.diaEff;
   }

   public void setDiaBasName(String diaBasName) {
      this.diaBasName = diaBasName;
   }

   public String getDiaBasName() {
      return this.diaBasName;
   }

   public void setReadmFlag(String readmFlag) {
      this.readmFlag = readmFlag;
   }

   public String getReadmFlag() {
      return this.readmFlag;
   }

   public void setVentilatorD(Long ventilatorD) {
      this.ventilatorD = ventilatorD;
   }

   public Long getVentilatorD() {
      return this.ventilatorD;
   }

   public void setVentilatorH(Long ventilatorH) {
      this.ventilatorH = ventilatorH;
   }

   public Long getVentilatorH() {
      return this.ventilatorH;
   }

   public void setVentilatorMi(Long ventilatorMi) {
      this.ventilatorMi = ventilatorMi;
   }

   public Long getVentilatorMi() {
      return this.ventilatorMi;
   }

   public void setAdlInhos(Long adlInhos) {
      this.adlInhos = adlInhos;
   }

   public Long getAdlInhos() {
      return this.adlInhos;
   }

   public void setAdlOuthos(Long adlOuthos) {
      this.adlOuthos = adlOuthos;
   }

   public Long getAdlOuthos() {
      return this.adlOuthos;
   }

   public void setSaveTimes(Long saveTimes) {
      this.saveTimes = saveTimes;
   }

   public Long getSaveTimes() {
      return this.saveTimes;
   }

   public void setSaveSuccessTimes(Long saveSuccessTimes) {
      this.saveSuccessTimes = saveSuccessTimes;
   }

   public Long getSaveSuccessTimes() {
      return this.saveSuccessTimes;
   }

   public void setIscp(String iscp) {
      this.iscp = iscp;
   }

   public String getIscp() {
      return this.iscp;
   }

   public void setStagingT(String stagingT) {
      this.stagingT = stagingT;
   }

   public String getStagingT() {
      return this.stagingT;
   }

   public void setStagingN(String stagingN) {
      this.stagingN = stagingN;
   }

   public String getStagingN() {
      return this.stagingN;
   }

   public void setStagingM(String stagingM) {
      this.stagingM = stagingM;
   }

   public String getStagingM() {
      return this.stagingM;
   }

   public void setStaging(String staging) {
      this.staging = staging;
   }

   public String getStaging() {
      return this.staging;
   }

   public void setPreoperHospD(Long preoperHospD) {
      this.preoperHospD = preoperHospD;
   }

   public Long getPreoperHospD() {
      return this.preoperHospD;
   }

   public void setIsPostoperComp(String isPostoperComp) {
      this.isPostoperComp = isPostoperComp;
   }

   public String getIsPostoperComp() {
      return this.isPostoperComp;
   }

   public void setIsPressure(String isPressure) {
      this.isPressure = isPressure;
   }

   public String getIsPressure() {
      return this.isPressure;
   }

   public void setPressureGrade(String pressureGrade) {
      this.pressureGrade = pressureGrade;
   }

   public String getPressureGrade() {
      return this.pressureGrade;
   }

   public void setPressureCaus(String pressureCaus) {
      this.pressureCaus = pressureCaus;
   }

   public String getPressureCaus() {
      return this.pressureCaus;
   }

   public void setPressureSite(String pressureSite) {
      this.pressureSite = pressureSite;
   }

   public String getPressureSite() {
      return this.pressureSite;
   }

   public void setIsFall(String isFall) {
      this.isFall = isFall;
   }

   public String getIsFall() {
      return this.isFall;
   }

   public void setFallGrade(String fallGrade) {
      this.fallGrade = fallGrade;
   }

   public String getFallGrade() {
      return this.fallGrade;
   }

   public void setFallCaus(String fallCaus) {
      this.fallCaus = fallCaus;
   }

   public String getFallCaus() {
      return this.fallCaus;
   }

   public void setIsInfuse(String isInfuse) {
      this.isInfuse = isInfuse;
   }

   public String getIsInfuse() {
      return this.isInfuse;
   }

   public void setIsInfuseReac(String isInfuseReac) {
      this.isInfuseReac = isInfuseReac;
   }

   public String getIsInfuseReac() {
      return this.isInfuseReac;
   }

   public void setIsFollowUp(String isFollowUp) {
      this.isFollowUp = isFollowUp;
   }

   public String getIsFollowUp() {
      return this.isFollowUp;
   }

   public void setFollowIntervalY(String followIntervalY) {
      this.followIntervalY = followIntervalY;
   }

   public String getFollowIntervalY() {
      return this.followIntervalY;
   }

   public void setIsUseAnti(String isUseAnti) {
      this.isUseAnti = isUseAnti;
   }

   public String getIsUseAnti() {
      return this.isUseAnti;
   }

   public void setUseAntiPurp(String useAntiPurp) {
      this.useAntiPurp = useAntiPurp;
   }

   public String getUseAntiPurp() {
      return this.useAntiPurp;
   }

   public void setUseAntiSche(String useAntiSche) {
      this.useAntiSche = useAntiSche;
   }

   public String getUseAntiSche() {
      return this.useAntiSche;
   }

   public void setUseAntiDays(Long useAntiDays) {
      this.useAntiDays = useAntiDays;
   }

   public Long getUseAntiDays() {
      return this.useAntiDays;
   }

   public void setSprCareD(Long sprCareD) {
      this.sprCareD = sprCareD;
   }

   public Long getSprCareD() {
      return this.sprCareD;
   }

   public void setPriCareD(Long priCareD) {
      this.priCareD = priCareD;
   }

   public Long getPriCareD() {
      return this.priCareD;
   }

   public void setSecCareD(Long secCareD) {
      this.secCareD = secCareD;
   }

   public Long getSecCareD() {
      return this.secCareD;
   }

   public void setTerCareD(Long terCareD) {
      this.terCareD = terCareD;
   }

   public Long getTerCareD() {
      return this.terCareD;
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

   public void setIsDiffRecord(String isDiffRecord) {
      this.isDiffRecord = isDiffRecord;
   }

   public String getIsDiffRecord() {
      return this.isDiffRecord;
   }

   public void setIsEmeRecord(String isEmeRecord) {
      this.isEmeRecord = isEmeRecord;
   }

   public String getIsEmeRecord() {
      return this.isEmeRecord;
   }

   public void setIsResRecord(String isResRecord) {
      this.isResRecord = isResRecord;
   }

   public String getIsResRecord() {
      return this.isResRecord;
   }

   public void setDiaBasCode(String diaBasCode) {
      this.diaBasCode = diaBasCode;
   }

   public String getDiaBasCode() {
      return this.diaBasCode;
   }

   public void setDiffProCode(String diffProCode) {
      this.diffProCode = diffProCode;
   }

   public String getDiffProCode() {
      return this.diffProCode;
   }

   public void setDiffProName(String diffProName) {
      this.diffProName = diffProName;
   }

   public String getDiffProName() {
      return this.diffProName;
   }

   public void setOpeType(String opeType) {
      this.opeType = opeType;
   }

   public String getOpeType() {
      return this.opeType;
   }

   public void setFirstRecord(String firstRecord) {
      this.firstRecord = firstRecord;
   }

   public String getFirstRecord() {
      return this.firstRecord;
   }

   public void setHbsag(String hbsag) {
      this.hbsag = hbsag;
   }

   public String getHbsag() {
      return this.hbsag;
   }

   public void setHcvAb(String hcvAb) {
      this.hcvAb = hcvAb;
   }

   public String getHcvAb() {
      return this.hcvAb;
   }

   public void setHivAb(String hivAb) {
      this.hivAb = hivAb;
   }

   public String getHivAb() {
      return this.hivAb;
   }

   public void setTransfusionReaction(String transfusionReaction) {
      this.transfusionReaction = transfusionReaction;
   }

   public String getTransfusionReaction() {
      return this.transfusionReaction;
   }

   public void setRedBloodCell(Long redBloodCell) {
      this.redBloodCell = redBloodCell;
   }

   public Long getRedBloodCell() {
      return this.redBloodCell;
   }

   public void setPlatelet(Long platelet) {
      this.platelet = platelet;
   }

   public Long getPlatelet() {
      return this.platelet;
   }

   public void setPlasma(Long plasma) {
      this.plasma = plasma;
   }

   public Long getPlasma() {
      return this.plasma;
   }

   public void setWholeBlood(Long wholeBlood) {
      this.wholeBlood = wholeBlood;
   }

   public Long getWholeBlood() {
      return this.wholeBlood;
   }

   public void setSelfBolld(Long selfBolld) {
      this.selfBolld = selfBolld;
   }

   public Long getSelfBolld() {
      return this.selfBolld;
   }

   public void setOtherBlood(Long otherBlood) {
      this.otherBlood = otherBlood;
   }

   public Long getOtherBlood() {
      return this.otherBlood;
   }

   public void setFollowIntervalM(String followIntervalM) {
      this.followIntervalM = followIntervalM;
   }

   public String getFollowIntervalM() {
      return this.followIntervalM;
   }

   public void setFollowIntervalD(String followIntervalD) {
      this.followIntervalD = followIntervalD;
   }

   public String getFollowIntervalD() {
      return this.followIntervalD;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("recordId", this.getRecordId()).append("patientId", this.getPatientId()).append("isCriRecord", this.getIsCriRecord()).append("inhosCondCd", this.getInhosCondCd()).append("inhosCond", this.getInhosCond()).append("diaDate", this.getDiaDate()).append("outDisDia", this.getOutDisDia()).append("admDisDia", this.getAdmDisDia()).append("prePosDia", this.getPrePosDia()).append("clinPathDia", this.getClinPathDia()).append("medPathDia", this.getMedPathDia()).append("prePathDia", this.getPrePathDia()).append("diaEff", this.getDiaEff()).append("diaBasName", this.getDiaBasName()).append("readmFlag", this.getReadmFlag()).append("ventilatorD", this.getVentilatorD()).append("ventilatorH", this.getVentilatorH()).append("ventilatorMi", this.getVentilatorMi()).append("adlInhos", this.getAdlInhos()).append("adlOuthos", this.getAdlOuthos()).append("saveTimes", this.getSaveTimes()).append("saveSuccessTimes", this.getSaveSuccessTimes()).append("iscp", this.getIscp()).append("stagingT", this.getStagingT()).append("stagingN", this.getStagingN()).append("stagingM", this.getStagingM()).append("staging", this.getStaging()).append("preoperHospD", this.getPreoperHospD()).append("isPostoperComp", this.getIsPostoperComp()).append("isPressure", this.getIsPressure()).append("pressureGrade", this.getPressureGrade()).append("pressureCaus", this.getPressureCaus()).append("pressureSite", this.getPressureSite()).append("isFall", this.getIsFall()).append("fallGrade", this.getFallGrade()).append("fallCaus", this.getFallCaus()).append("isInfuse", this.getIsInfuse()).append("isInfuseReac", this.getIsInfuseReac()).append("isFollowUp", this.getIsFollowUp()).append("followIntervalY", this.getFollowIntervalY()).append("isUseAnti", this.getIsUseAnti()).append("useAntiPurp", this.getUseAntiPurp()).append("useAntiSche", this.getUseAntiSche()).append("useAntiDays", this.getUseAntiDays()).append("sprCareD", this.getSprCareD()).append("priCareD", this.getPriCareD()).append("secCareD", this.getSecCareD()).append("terCareD", this.getTerCareD()).append("crePerCode", this.getCrePerCode()).append("crePerName", this.getCrePerName()).append("creDate", this.getCreDate()).append("altPerCode", this.getAltPerCode()).append("altPerName", this.getAltPerName()).append("altDate", this.getAltDate()).append("isDiffRecord", this.getIsDiffRecord()).append("isEmeRecord", this.getIsEmeRecord()).append("isResRecord", this.getIsResRecord()).append("diaBasCode", this.getDiaBasCode()).append("diffProCode", this.getDiffProCode()).append("diffProName", this.getDiffProName()).append("opeType", this.getOpeType()).append("firstRecord", this.getFirstRecord()).append("hbsag", this.getHbsag()).append("hcvAb", this.getHcvAb()).append("hivAb", this.getHivAb()).append("transfusionReaction", this.getTransfusionReaction()).append("redBloodCell", this.getRedBloodCell()).append("platelet", this.getPlatelet()).append("plasma", this.getPlasma()).append("wholeBlood", this.getWholeBlood()).append("selfBolld", this.getSelfBolld()).append("otherBlood", this.getOtherBlood()).append("followIntervalM", this.getFollowIntervalM()).append("followIntervalD", this.getFollowIntervalD()).toString();
   }
}
