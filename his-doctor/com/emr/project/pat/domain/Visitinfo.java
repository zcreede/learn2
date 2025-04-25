package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Visitinfo extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "医疗机构名称"
   )
   private String orgName;
   private String patientId;
   @Excel(
      name = "住院号或门诊号"
   )
   private String inpNo;
   @Excel(
      name = "住院次数"
   )
   private Integer visitId;
   @Excel(
      name = "病案号"
   )
   private String recordNo;
   @Excel(
      name = "入院证号"
   )
   private String inhosCard;
   @Excel(
      name = "就诊门诊号"
   )
   private String sdOutNo;
   @Excel(
      name = "患者类型代码"
   )
   private String patTypeCd;
   @Excel(
      name = "患者类型名称"
   )
   private String patTypeName;
   @Excel(
      name = "医疗保险类别代码"
   )
   private String insuTypeCd;
   @Excel(
      name = "医疗保险类别名称"
   )
   private String insuTypeName;
   @Excel(
      name = "医疗费用支付方式代码"
   )
   private String payTypeCd;
   @Excel(
      name = "医疗费用支付方式名称"
   )
   private String payTypeName;
   @Excel(
      name = "开住院证的医师编码"
   )
   private String inDocCd;
   @Excel(
      name = "开住院证的医师姓名"
   )
   private String inDocName;
   @Excel(
      name = "入院科室代码"
   )
   private String inDeptCd;
   @Excel(
      name = "入院科室名称"
   )
   private String inDeptName;
   @Excel(
      name = "入院病区代码"
   )
   private String inAreaCd;
   @Excel(
      name = "入院病区名称"
   )
   private String inAreaName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date inhosTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "入科日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date inDeptTime;
   @Excel(
      name = "入院病情情况"
   )
   private String inhosCond;
   @Excel(
      name = "入院病情情况编号"
   )
   private String inhosCondCd;
   @Excel(
      name = "入院原因"
   )
   private String inhosReason;
   @Excel(
      name = "入院途径代码"
   )
   private String inhosWayCd;
   @Excel(
      name = "入院途径名称"
   )
   private String inhosWayName;
   @Excel(
      name = "入院诊断编码"
   )
   private String inhosDiagCd;
   @Excel(
      name = "入院诊断名称"
   )
   private String inhosDiag;
   @Excel(
      name = "入院诊断-西医诊断名称"
   )
   private String inhosWmDiaName;
   @Excel(
      name = "入院诊断-西医诊断编码"
   )
   private String inhosWmDiaCd;
   @Excel(
      name = "入院诊断-中医证候名称"
   )
   private String inhosCmSymName;
   @Excel(
      name = "入院诊断-中医证候代码"
   )
   private String inhosCmSymCd;
   @Excel(
      name = "入院诊断-中医病名名称"
   )
   private String inhosCmIllName;
   @Excel(
      name = "入院诊断-中医病名代码"
   )
   private String inhosCmIllCd;
   @Excel(
      name = "病区代码"
   )
   private String areaCode;
   @Excel(
      name = "病区名称"
   )
   private String wardName;
   @Excel(
      name = "科室代码"
   )
   private String deptCd;
   @Excel(
      name = "科室名称"
   )
   private String deptName;
   @Excel(
      name = "住院医师编码"
   )
   private String resDocCd;
   @Excel(
      name = "住院医师姓名"
   )
   private String resDocName;
   @Excel(
      name = "主治医师编码"
   )
   private String attDocCd;
   @Excel(
      name = "主治医师姓名"
   )
   private String attDocName;
   @Excel(
      name = "主任医师编码"
   )
   private String arcDocCd;
   @Excel(
      name = "主任医师姓名"
   )
   private String arcDocName;
   @Excel(
      name = "责任护士编码"
   )
   private String dutyNurName;
   @Excel(
      name = "责任护士姓名"
   )
   private String dutyNurCd;
   @Excel(
      name = "病房号"
   )
   private String roomNo;
   @Excel(
      name = "病床号"
   )
   private String bedNo;
   @Excel(
      name = "护理等级代码"
   )
   private String ngCd;
   @Excel(
      name = "护理等级名称"
   )
   private String ngName;
   @Excel(
      name = "患者病情代码"
   )
   private String pcCd;
   @Excel(
      name = "患者病情名称"
   )
   private String pcName;
   @Excel(
      name = "出院情况"
   )
   private String outCon;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "出院日期时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date outTime;
   @Excel(
      name = "出院病区代码"
   )
   private String outAreaCd;
   @Excel(
      name = "出院病区名称"
   )
   private String outAreaName;
   @Excel(
      name = "出院科室代码"
   )
   private String outDeptCd;
   @Excel(
      name = "出院科室名称"
   )
   private String outDeptName;
   @Excel(
      name = "出院西医诊断-主要诊断-入院病情代码"
   )
   private String wmIllStateCd;
   @Excel(
      name = "出院西医诊断-主要诊断名称"
   )
   private String leaveWmIllName;
   @Excel(
      name = "出院西医诊断-主要诊断疾病编码"
   )
   private String leaveWmIllCd;
   @Excel(
      name = "出院西医诊断-其他诊断-入院病情代码"
   )
   private String wmProStateCd;
   @Excel(
      name = "出院西医诊断-其他诊断名称"
   )
   private String leaveWmProName;
   @Excel(
      name = "出院西医诊断-其他诊断病症编码"
   )
   private String leaveWmProCd;
   @Excel(
      name = "出院诊断-中医病名代码"
   )
   private String leaveCmIllCd;
   @Excel(
      name = "出院诊断-中医病名名称"
   )
   private String leaveCmIllName;
   @Excel(
      name = "出院诊断-中医证候代码"
   )
   private String leaveCmSymCd;
   @Excel(
      name = "出院诊断-中医证候名称"
   )
   private String leaveCmSymName;
   @Excel(
      name = "出院诊断-主要诊断入院病情代码"
   )
   private String leaveMainIllCd;
   @Excel(
      name = "出院诊断-主要诊断名称"
   )
   private String leaveMainDiaName;
   @Excel(
      name = "出院诊断-主要诊断疾病编码"
   )
   private String leaveMainDiaCd;
   @Excel(
      name = "出院诊断-其他诊断入院病情代码"
   )
   private String leaveOthIllCd;
   @Excel(
      name = "出院诊断-其他诊断名称"
   )
   private String leaveOthDiaName;
   @Excel(
      name = "出院诊断-其他诊断疾病编码"
   )
   private String leaveOthDiaCd;
   @Excel(
      name = "出院诊断-西医诊断名称"
   )
   private String leaveWmDiaCd;
   @Excel(
      name = "出院诊断-西医诊断编码"
   )
   private String leaveWmDiaName;
   @Excel(
      name = "患者重点标志"
   )
   private String mainPatFlag;
   @Excel(
      name = "临床路径标志",
      readConverterExp = "0=：未进行临床路径；1：进行临床路径"
   )
   private String cpFlag;
   @Excel(
      name = "临床路径ID",
      readConverterExp = "C=P+医疗机构编码+5位流水号；通用路径ID为：CP+医疗机构编码+00000，其它路径不得使用此编号"
   )
   private String cpId;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "路径第一天日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date cpFirstDate;
   @Excel(
      name = "临床路径名称"
   )
   private String cpName;
   @Excel(
      name = "进入路径第几天"
   )
   private Long cpDayNo;
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
      name = "费用总额"
   )
   private Double costSum;
   @Excel(
      name = "押金总额"
   )
   private Double foreSum;
   @Excel(
      name = "体重(KG)"
   )
   private String weight;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "病史采集时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date gathMhTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "标记日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date markDate;
   @Excel(
      name = "病人可用余额"
   )
   private Double totalPatAva;
   @Excel(
      name = "病人就诊信息修改标志，默认0，当为1时只同步BB_CONFIG_SOURCE.UPDATEFIELD中的字段数据"
   )
   private String modiFlag;
   @Excel(
      name = "新生儿出生体重(g)"
   )
   private Long birWeight;
   @Excel(
      name = "新生儿入院体重(g)"
   )
   private Long inhosWeight;
   @Excel(
      name = "是否已经质控过"
   )
   private String isQuality;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   @Excel(
      name = "确认质控日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date qualityDate;
   private String patientName;
   private String height;
   private String specialGroupCd;
   private String specialGroupName;
   private String referralHospital;
   private String outType;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date modifyTime;
   private String patientTel;
   private String daycaseFlag;

   public String getPatientTel() {
      return this.patientTel;
   }

   public void setPatientTel(String patientTel) {
      this.patientTel = patientTel;
   }

   public Date getModifyTime() {
      return this.modifyTime;
   }

   public void setModifyTime(Date modifyTime) {
      this.modifyTime = modifyTime;
   }

   public String getOutType() {
      return this.outType;
   }

   public void setOutType(String outType) {
      this.outType = outType;
   }

   public String getReferralHospital() {
      return this.referralHospital;
   }

   public void setReferralHospital(String referralHospital) {
      this.referralHospital = referralHospital;
   }

   public String getSpecialGroupCd() {
      return this.specialGroupCd;
   }

   public void setSpecialGroupCd(String specialGroupCd) {
      this.specialGroupCd = specialGroupCd;
   }

   public String getSpecialGroupName() {
      return this.specialGroupName;
   }

   public void setSpecialGroupName(String specialGroupName) {
      this.specialGroupName = specialGroupName;
   }

   public String getHeight() {
      return this.height;
   }

   public void setHeight(String height) {
      this.height = height;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
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

   public void setVisitId(Integer visitId) {
      this.visitId = visitId;
   }

   public Integer getVisitId() {
      return this.visitId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setInhosCard(String inhosCard) {
      this.inhosCard = inhosCard;
   }

   public String getInhosCard() {
      return this.inhosCard;
   }

   public void setSdOutNo(String sdOutNo) {
      this.sdOutNo = sdOutNo;
   }

   public String getSdOutNo() {
      return this.sdOutNo;
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

   public void setInsuTypeCd(String insuTypeCd) {
      this.insuTypeCd = insuTypeCd;
   }

   public String getInsuTypeCd() {
      return this.insuTypeCd;
   }

   public void setInsuTypeName(String insuTypeName) {
      this.insuTypeName = insuTypeName;
   }

   public String getInsuTypeName() {
      return this.insuTypeName;
   }

   public void setPayTypeCd(String payTypeCd) {
      this.payTypeCd = payTypeCd;
   }

   public String getPayTypeCd() {
      return this.payTypeCd;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setInDocCd(String inDocCd) {
      this.inDocCd = inDocCd;
   }

   public String getInDocCd() {
      return this.inDocCd;
   }

   public void setInDocName(String inDocName) {
      this.inDocName = inDocName;
   }

   public String getInDocName() {
      return this.inDocName;
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

   public void setInAreaCd(String inAreaCd) {
      this.inAreaCd = inAreaCd;
   }

   public String getInAreaCd() {
      return this.inAreaCd;
   }

   public void setInAreaName(String inAreaName) {
      this.inAreaName = inAreaName;
   }

   public String getInAreaName() {
      return this.inAreaName;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInDeptTime(Date inDeptTime) {
      this.inDeptTime = inDeptTime;
   }

   public Date getInDeptTime() {
      return this.inDeptTime;
   }

   public void setInhosCond(String inhosCond) {
      this.inhosCond = inhosCond;
   }

   public String getInhosCond() {
      return this.inhosCond;
   }

   public void setInhosCondCd(String inhosCondCd) {
      this.inhosCondCd = inhosCondCd;
   }

   public String getInhosCondCd() {
      return this.inhosCondCd;
   }

   public void setInhosReason(String inhosReason) {
      this.inhosReason = inhosReason;
   }

   public String getInhosReason() {
      return this.inhosReason;
   }

   public void setInhosWayCd(String inhosWayCd) {
      this.inhosWayCd = inhosWayCd;
   }

   public String getInhosWayCd() {
      return this.inhosWayCd;
   }

   public void setInhosWayName(String inhosWayName) {
      this.inhosWayName = inhosWayName;
   }

   public String getInhosWayName() {
      return this.inhosWayName;
   }

   public void setInhosDiagCd(String inhosDiagCd) {
      this.inhosDiagCd = inhosDiagCd;
   }

   public String getInhosDiagCd() {
      return this.inhosDiagCd;
   }

   public void setInhosDiag(String inhosDiag) {
      this.inhosDiag = inhosDiag;
   }

   public String getInhosDiag() {
      return this.inhosDiag;
   }

   public void setInhosWmDiaName(String inhosWmDiaName) {
      this.inhosWmDiaName = inhosWmDiaName;
   }

   public String getInhosWmDiaName() {
      return this.inhosWmDiaName;
   }

   public void setInhosWmDiaCd(String inhosWmDiaCd) {
      this.inhosWmDiaCd = inhosWmDiaCd;
   }

   public String getInhosWmDiaCd() {
      return this.inhosWmDiaCd;
   }

   public void setInhosCmSymName(String inhosCmSymName) {
      this.inhosCmSymName = inhosCmSymName;
   }

   public String getInhosCmSymName() {
      return this.inhosCmSymName;
   }

   public void setInhosCmSymCd(String inhosCmSymCd) {
      this.inhosCmSymCd = inhosCmSymCd;
   }

   public String getInhosCmSymCd() {
      return this.inhosCmSymCd;
   }

   public void setInhosCmIllName(String inhosCmIllName) {
      this.inhosCmIllName = inhosCmIllName;
   }

   public String getInhosCmIllName() {
      return this.inhosCmIllName;
   }

   public void setInhosCmIllCd(String inhosCmIllCd) {
      this.inhosCmIllCd = inhosCmIllCd;
   }

   public String getInhosCmIllCd() {
      return this.inhosCmIllCd;
   }

   public void setAreaCode(String areaCode) {
      this.areaCode = areaCode;
   }

   public String getAreaCode() {
      return this.areaCode;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setAttDocCd(String attDocCd) {
      this.attDocCd = attDocCd;
   }

   public String getAttDocCd() {
      return this.attDocCd;
   }

   public void setAttDocName(String attDocName) {
      this.attDocName = attDocName;
   }

   public String getAttDocName() {
      return this.attDocName;
   }

   public void setArcDocCd(String arcDocCd) {
      this.arcDocCd = arcDocCd;
   }

   public String getArcDocCd() {
      return this.arcDocCd;
   }

   public void setArcDocName(String arcDocName) {
      this.arcDocName = arcDocName;
   }

   public String getArcDocName() {
      return this.arcDocName;
   }

   public void setDutyNurName(String dutyNurName) {
      this.dutyNurName = dutyNurName;
   }

   public String getDutyNurName() {
      return this.dutyNurName;
   }

   public void setDutyNurCd(String dutyNurCd) {
      this.dutyNurCd = dutyNurCd;
   }

   public String getDutyNurCd() {
      return this.dutyNurCd;
   }

   public void setRoomNo(String roomNo) {
      this.roomNo = roomNo;
   }

   public String getRoomNo() {
      return this.roomNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setNgCd(String ngCd) {
      this.ngCd = ngCd;
   }

   public String getNgCd() {
      return this.ngCd;
   }

   public void setNgName(String ngName) {
      this.ngName = ngName;
   }

   public String getNgName() {
      return this.ngName;
   }

   public void setPcCd(String pcCd) {
      this.pcCd = pcCd;
   }

   public String getPcCd() {
      return this.pcCd;
   }

   public void setPcName(String pcName) {
      this.pcName = pcName;
   }

   public String getPcName() {
      return this.pcName;
   }

   public void setOutCon(String outCon) {
      this.outCon = outCon;
   }

   public String getOutCon() {
      return this.outCon;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutAreaCd(String outAreaCd) {
      this.outAreaCd = outAreaCd;
   }

   public String getOutAreaCd() {
      return this.outAreaCd;
   }

   public void setOutAreaName(String outAreaName) {
      this.outAreaName = outAreaName;
   }

   public String getOutAreaName() {
      return this.outAreaName;
   }

   public void setOutDeptCd(String outDeptCd) {
      this.outDeptCd = outDeptCd;
   }

   public String getOutDeptCd() {
      return this.outDeptCd;
   }

   public void setOutDeptName(String outDeptName) {
      this.outDeptName = outDeptName;
   }

   public String getOutDeptName() {
      return this.outDeptName;
   }

   public void setWmIllStateCd(String wmIllStateCd) {
      this.wmIllStateCd = wmIllStateCd;
   }

   public String getWmIllStateCd() {
      return this.wmIllStateCd;
   }

   public void setLeaveWmIllName(String leaveWmIllName) {
      this.leaveWmIllName = leaveWmIllName;
   }

   public String getLeaveWmIllName() {
      return this.leaveWmIllName;
   }

   public void setLeaveWmIllCd(String leaveWmIllCd) {
      this.leaveWmIllCd = leaveWmIllCd;
   }

   public String getLeaveWmIllCd() {
      return this.leaveWmIllCd;
   }

   public void setWmProStateCd(String wmProStateCd) {
      this.wmProStateCd = wmProStateCd;
   }

   public String getWmProStateCd() {
      return this.wmProStateCd;
   }

   public void setLeaveWmProName(String leaveWmProName) {
      this.leaveWmProName = leaveWmProName;
   }

   public String getLeaveWmProName() {
      return this.leaveWmProName;
   }

   public void setLeaveWmProCd(String leaveWmProCd) {
      this.leaveWmProCd = leaveWmProCd;
   }

   public String getLeaveWmProCd() {
      return this.leaveWmProCd;
   }

   public void setLeaveCmIllCd(String leaveCmIllCd) {
      this.leaveCmIllCd = leaveCmIllCd;
   }

   public String getLeaveCmIllCd() {
      return this.leaveCmIllCd;
   }

   public void setLeaveCmIllName(String leaveCmIllName) {
      this.leaveCmIllName = leaveCmIllName;
   }

   public String getLeaveCmIllName() {
      return this.leaveCmIllName;
   }

   public void setLeaveCmSymCd(String leaveCmSymCd) {
      this.leaveCmSymCd = leaveCmSymCd;
   }

   public String getLeaveCmSymCd() {
      return this.leaveCmSymCd;
   }

   public void setLeaveCmSymName(String leaveCmSymName) {
      this.leaveCmSymName = leaveCmSymName;
   }

   public String getLeaveCmSymName() {
      return this.leaveCmSymName;
   }

   public void setLeaveMainIllCd(String leaveMainIllCd) {
      this.leaveMainIllCd = leaveMainIllCd;
   }

   public String getLeaveMainIllCd() {
      return this.leaveMainIllCd;
   }

   public void setLeaveMainDiaName(String leaveMainDiaName) {
      this.leaveMainDiaName = leaveMainDiaName;
   }

   public String getLeaveMainDiaName() {
      return this.leaveMainDiaName;
   }

   public void setLeaveMainDiaCd(String leaveMainDiaCd) {
      this.leaveMainDiaCd = leaveMainDiaCd;
   }

   public String getLeaveMainDiaCd() {
      return this.leaveMainDiaCd;
   }

   public void setLeaveOthIllCd(String leaveOthIllCd) {
      this.leaveOthIllCd = leaveOthIllCd;
   }

   public String getLeaveOthIllCd() {
      return this.leaveOthIllCd;
   }

   public void setLeaveOthDiaName(String leaveOthDiaName) {
      this.leaveOthDiaName = leaveOthDiaName;
   }

   public String getLeaveOthDiaName() {
      return this.leaveOthDiaName;
   }

   public void setLeaveOthDiaCd(String leaveOthDiaCd) {
      this.leaveOthDiaCd = leaveOthDiaCd;
   }

   public String getLeaveOthDiaCd() {
      return this.leaveOthDiaCd;
   }

   public void setLeaveWmDiaCd(String leaveWmDiaCd) {
      this.leaveWmDiaCd = leaveWmDiaCd;
   }

   public String getLeaveWmDiaCd() {
      return this.leaveWmDiaCd;
   }

   public void setLeaveWmDiaName(String leaveWmDiaName) {
      this.leaveWmDiaName = leaveWmDiaName;
   }

   public String getLeaveWmDiaName() {
      return this.leaveWmDiaName;
   }

   public void setMainPatFlag(String mainPatFlag) {
      this.mainPatFlag = mainPatFlag;
   }

   public String getMainPatFlag() {
      return this.mainPatFlag;
   }

   public void setCpFlag(String cpFlag) {
      this.cpFlag = cpFlag;
   }

   public String getCpFlag() {
      return this.cpFlag;
   }

   public void setCpId(String cpId) {
      this.cpId = cpId;
   }

   public String getCpId() {
      return this.cpId;
   }

   public void setCpFirstDate(Date cpFirstDate) {
      this.cpFirstDate = cpFirstDate;
   }

   public Date getCpFirstDate() {
      return this.cpFirstDate;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpDayNo(Long cpDayNo) {
      this.cpDayNo = cpDayNo;
   }

   public Long getCpDayNo() {
      return this.cpDayNo;
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

   public void setWeight(String weight) {
      this.weight = weight;
   }

   public String getWeight() {
      return this.weight;
   }

   public void setGathMhTime(Date gathMhTime) {
      this.gathMhTime = gathMhTime;
   }

   public Date getGathMhTime() {
      return this.gathMhTime;
   }

   public void setMarkDate(Date markDate) {
      this.markDate = markDate;
   }

   public Date getMarkDate() {
      return this.markDate;
   }

   public static long getSerialVersionUID() {
      return 1L;
   }

   public Double getCostSum() {
      return this.costSum;
   }

   public void setCostSum(Double costSum) {
      this.costSum = costSum;
   }

   public Double getForeSum() {
      return this.foreSum;
   }

   public void setForeSum(Double foreSum) {
      this.foreSum = foreSum;
   }

   public Double getTotalPatAva() {
      return this.totalPatAva;
   }

   public void setTotalPatAva(Double totalPatAva) {
      this.totalPatAva = totalPatAva;
   }

   public void setModiFlag(String modiFlag) {
      this.modiFlag = modiFlag;
   }

   public String getModiFlag() {
      return this.modiFlag;
   }

   public void setBirWeight(Long birWeight) {
      this.birWeight = birWeight;
   }

   public Long getBirWeight() {
      return this.birWeight;
   }

   public void setInhosWeight(Long inhosWeight) {
      this.inhosWeight = inhosWeight;
   }

   public Long getInhosWeight() {
      return this.inhosWeight;
   }

   public void setIsQuality(String isQuality) {
      this.isQuality = isQuality;
   }

   public String getIsQuality() {
      return this.isQuality;
   }

   public void setQualityDate(Date qualityDate) {
      this.qualityDate = qualityDate;
   }

   public Date getQualityDate() {
      return this.qualityDate;
   }

   public String getDaycaseFlag() {
      return this.daycaseFlag;
   }

   public void setDaycaseFlag(String daycaseFlag) {
      this.daycaseFlag = daycaseFlag;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("orgCd", this.getOrgCd()).append("orgName", this.getOrgName()).append("patientId", this.getPatientId()).append("inpNo", this.getInpNo()).append("visitId", this.getVisitId()).append("recordNo", this.getRecordNo()).append("inhosCard", this.getInhosCard()).append("sdOutNo", this.getSdOutNo()).append("patTypeCd", this.getPatTypeCd()).append("patTypeName", this.getPatTypeName()).append("insuTypeCd", this.getInsuTypeCd()).append("insuTypeName", this.getInsuTypeName()).append("payTypeCd", this.getPayTypeCd()).append("payTypeName", this.getPayTypeName()).append("inDocCd", this.getInDocCd()).append("inDocName", this.getInDocName()).append("inDeptCd", this.getInDeptCd()).append("inDeptName", this.getInDeptName()).append("inAreaCd", this.getInAreaCd()).append("inAreaName", this.getInAreaName()).append("inhosTime", this.getInhosTime()).append("inDeptTime", this.getInDeptTime()).append("inhosCond", this.getInhosCond()).append("inhosCondCd", this.getInhosCondCd()).append("inhosReason", this.getInhosReason()).append("inhosWayCd", this.getInhosWayCd()).append("inhosWayName", this.getInhosWayName()).append("inhosDiagCd", this.getInhosDiagCd()).append("inhosDiag", this.getInhosDiag()).append("inhosWmDiaName", this.getInhosWmDiaName()).append("inhosWmDiaCd", this.getInhosWmDiaCd()).append("inhosCmSymName", this.getInhosCmSymName()).append("inhosCmSymCd", this.getInhosCmSymCd()).append("inhosCmIllName", this.getInhosCmIllName()).append("inhosCmIllCd", this.getInhosCmIllCd()).append("areaCode", this.getAreaCode()).append("wardName", this.getWardName()).append("deptCd", this.getDeptCd()).append("deptName", this.getDeptName()).append("resDocCd", this.getResDocCd()).append("resDocName", this.getResDocName()).append("attDocCd", this.getAttDocCd()).append("attDocName", this.getAttDocName()).append("arcDocCd", this.getArcDocCd()).append("arcDocName", this.getArcDocName()).append("dutyNurName", this.getDutyNurName()).append("dutyNurCd", this.getDutyNurCd()).append("roomNo", this.getRoomNo()).append("bedNo", this.getBedNo()).append("ngCd", this.getNgCd()).append("ngName", this.getNgName()).append("pcCd", this.getPcCd()).append("pcName", this.getPcName()).append("outCon", this.getOutCon()).append("outTime", this.getOutTime()).append("outAreaCd", this.getOutAreaCd()).append("outAreaName", this.getOutAreaName()).append("outDeptCd", this.getOutDeptCd()).append("outDeptName", this.getOutDeptName()).append("wmIllStateCd", this.getWmIllStateCd()).append("leaveWmIllName", this.getLeaveWmIllName()).append("leaveWmIllCd", this.getLeaveWmIllCd()).append("wmProStateCd", this.getWmProStateCd()).append("leaveWmProName", this.getLeaveWmProName()).append("leaveWmProCd", this.getLeaveWmProCd()).append("leaveCmIllCd", this.getLeaveCmIllCd()).append("leaveCmIllName", this.getLeaveCmIllName()).append("leaveCmSymCd", this.getLeaveCmSymCd()).append("leaveCmSymName", this.getLeaveCmSymName()).append("leaveMainIllCd", this.getLeaveMainIllCd()).append("leaveMainDiaName", this.getLeaveMainDiaName()).append("leaveMainDiaCd", this.getLeaveMainDiaCd()).append("leaveOthIllCd", this.getLeaveOthIllCd()).append("leaveOthDiaName", this.getLeaveOthDiaName()).append("leaveOthDiaCd", this.getLeaveOthDiaCd()).append("leaveWmDiaCd", this.getLeaveWmDiaCd()).append("leaveWmDiaName", this.getLeaveWmDiaName()).append("mainPatFlag", this.getMainPatFlag()).append("cpFlag", this.getCpFlag()).append("cpId", this.getCpId()).append("cpFirstDate", this.getCpFirstDate()).append("cpName", this.getCpName()).append("cpDayNo", this.getCpDayNo()).append("altPerName", this.getAltPerName()).append("altPerCode", this.getAltPerCode()).append("altDate", this.getAltDate()).append("crePerName", this.getCrePerName()).append("crePerCode", this.getCrePerCode()).append("creDate", this.getCreDate()).append("costSum", this.getCostSum()).append("foreSum", this.getForeSum()).append("weight", this.getWeight()).append("gathMhTime", this.getGathMhTime()).append("markDate", this.getMarkDate()).append("totalPatAva", this.getTotalPatAva()).append("modiFlag", this.getModiFlag()).append("birWeight", this.getBirWeight()).append("inhosWeight", this.getInhosWeight()).append("isQuality", this.getIsQuality()).append("qualityDate", this.getQualityDate()).append("specialGroupCd", this.getSpecialGroupCd()).append("specialGroupName", this.getSpecialGroupName()).toString();
   }
}
