package com.emr.project.qc.domain.vo;

import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class EmrQcFlowVo extends EmrQcFlow {
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm"
   )
   private Date outTimeBegin;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm"
   )
   private Date outTimeEnd;
   private String ngCd;
   private String ngName;
   private String timeType;
   private String docType;
   private Date outHospitalTimeBegin;
   private Date outHospitalTimeEnd;
   private String outHospitalTimeBeginS;
   private String outHospitalTimeEndS;
   private int inhosDayNum;
   private String deptQcCd;
   private String qcState;
   private String mrStateName;
   private String alleInfo;
   private List alleInfoList;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date applyFileTimeBegin;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date applyFileTimeEnd;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date fileTimeBegin;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd"
   )
   private Date fileTimeEnd;
   private String deptCode;
   private String patientId;
   private String patientName;
   private String inpNo;
   private String sex;
   private String age;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private String deptName;
   private String inhosWmDiaName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date inhosTime;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date outTime;
   private String pcName;
   private String resDocName;
   private String operFlag;
   private String consFlag;
   private String dieFlag;
   private String bloodTrans;
   private String changeFlag;
   private String infectFlag;
   private String rescueFlag;
   private String dayNumFlag;
   private String costSumFlag;
   private String alertFlag;
   private String pcCdFlag;
   private String operIcon;
   private String consIcon;
   private String dieIcon;
   private String bloodIcon;
   private String changeIcon;
   private String infectIcon;
   private String rescueIcon;
   private String dayNumIcon;
   private String costSumIcon;
   private String bedNo;
   private String payTypeName;
   private String state;
   private String stateName;
   private Long qcId;
   private String qcSection;
   private String qcSectionName;
   private Long flowId;
   private String backState;
   private Integer outDeptTotal;
   private String backoutFileBz;
   private String backoutFilePerCode;
   private String backoutFilePerName;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm:ss"
   )
   private Date backoutFileDate;
   private List deptQcStateList;
   private List termQcStateList;
   private List mrStateList;
   private Integer dayNum;
   private Double costSum;
   private List emrQcFlowRecordList;
   private Integer limitHours;
   private Integer unWriteNum = 0;
   private Integer unFinishNum = 0;
   private Integer qcEmrNum = 0;
   private String patName;
   private String subLimit;
   private String operReason;
   private String ip;
   private Double qcScore;
   private String qcGradeName;
   private String qcGradeNo;
   private String patientMainId;
   private String messageStr;
   private String termQcState;
   private String termReturnFlag;
   private String outHospitalTotal;
   private String ygdTotal;
   private String jjProportion;
   private String fxlProportion;
   private String twoProportion;
   private String threeProportion;
   private String sevenProportion;
   private String twoFiling;
   private String threeFiling;
   private String sevenFiling;
   private String statics;

   public Date getBackoutFileDate() {
      return this.backoutFileDate;
   }

   public void setBackoutFileDate(Date backoutFileDate) {
      this.backoutFileDate = backoutFileDate;
   }

   public String getBackoutFilePerCode() {
      return this.backoutFilePerCode;
   }

   public void setBackoutFilePerCode(String backoutFilePerCode) {
      this.backoutFilePerCode = backoutFilePerCode;
   }

   public String getBackoutFilePerName() {
      return this.backoutFilePerName;
   }

   public void setBackoutFilePerName(String backoutFilePerName) {
      this.backoutFilePerName = backoutFilePerName;
   }

   public String getBackoutFileBz() {
      return this.backoutFileBz;
   }

   public void setBackoutFileBz(String backoutFileBz) {
      this.backoutFileBz = backoutFileBz;
   }

   public Integer getOutDeptTotal() {
      return this.outDeptTotal;
   }

   public void setOutDeptTotal(Integer outDeptTotal) {
      this.outDeptTotal = outDeptTotal;
   }

   public String getAlleInfo() {
      return this.alleInfo;
   }

   public void setAlleInfo(String alleInfo) {
      this.alleInfo = alleInfo;
   }

   public List getAlleInfoList() {
      return this.alleInfoList;
   }

   public void setAlleInfoList(List alleInfoList) {
      this.alleInfoList = alleInfoList;
   }

   public String getAlertFlag() {
      return this.alertFlag;
   }

   public void setAlertFlag(String alertFlag) {
      this.alertFlag = alertFlag;
   }

   public String getOutHospitalTimeBeginS() {
      return this.outHospitalTimeBeginS;
   }

   public void setOutHospitalTimeBeginS(String outHospitalTimeBeginS) {
      this.outHospitalTimeBeginS = outHospitalTimeBeginS;
   }

   public String getOutHospitalTimeEndS() {
      return this.outHospitalTimeEndS;
   }

   public void setOutHospitalTimeEndS(String outHospitalTimeEndS) {
      this.outHospitalTimeEndS = outHospitalTimeEndS;
   }

   public String getStatics() {
      return this.statics;
   }

   public void setStatics(String statics) {
      this.statics = statics;
   }

   public String getBackState() {
      return this.backState;
   }

   public String getTwoFiling() {
      return this.twoFiling;
   }

   public void setTwoFiling(String twoFiling) {
      this.twoFiling = twoFiling;
   }

   public String getThreeFiling() {
      return this.threeFiling;
   }

   public void setThreeFiling(String threeFiling) {
      this.threeFiling = threeFiling;
   }

   public String getSevenFiling() {
      return this.sevenFiling;
   }

   public void setSevenFiling(String sevenFiling) {
      this.sevenFiling = sevenFiling;
   }

   public void setBackState(String backState) {
      this.backState = backState;
   }

   public int getInhosDayNum() {
      return this.inhosDayNum;
   }

   public void setInhosDayNum(int inhosDayNum) {
      this.inhosDayNum = inhosDayNum;
   }

   public String getDocType() {
      return this.docType;
   }

   public void setDocType(String docType) {
      this.docType = docType;
   }

   public String getNgName() {
      return this.ngName;
   }

   public void setNgName(String ngName) {
      this.ngName = ngName;
   }

   public String getNgCd() {
      return this.ngCd;
   }

   public void setNgCd(String ngCd) {
      this.ngCd = ngCd;
   }

   public String getMrStateName() {
      return this.mrStateName;
   }

   public void setMrStateName(String mrStateName) {
      this.mrStateName = mrStateName;
   }

   public String getQcSectionName() {
      return this.qcSectionName;
   }

   public void setQcSectionName(String qcSectionName) {
      this.qcSectionName = qcSectionName;
   }

   public String getTimeType() {
      return this.timeType;
   }

   public void setTimeType(String timeType) {
      this.timeType = timeType;
   }

   public String getOutHospitalTotal() {
      return this.outHospitalTotal;
   }

   public void setOutHospitalTotal(String outHospitalTotal) {
      this.outHospitalTotal = outHospitalTotal;
   }

   public String getYgdTotal() {
      return this.ygdTotal;
   }

   public void setYgdTotal(String ygdTotal) {
      this.ygdTotal = ygdTotal;
   }

   public String getJjProportion() {
      return this.jjProportion;
   }

   public void setJjProportion(String jjProportion) {
      this.jjProportion = jjProportion;
   }

   public String getFxlProportion() {
      return this.fxlProportion;
   }

   public void setFxlProportion(String fxlProportion) {
      this.fxlProportion = fxlProportion;
   }

   public String getTwoProportion() {
      return this.twoProportion;
   }

   public void setTwoProportion(String twoProportion) {
      this.twoProportion = twoProportion;
   }

   public String getThreeProportion() {
      return this.threeProportion;
   }

   public void setThreeProportion(String threeProportion) {
      this.threeProportion = threeProportion;
   }

   public String getSevenProportion() {
      return this.sevenProportion;
   }

   public void setSevenProportion(String sevenProportion) {
      this.sevenProportion = sevenProportion;
   }

   public String getTermReturnFlag() {
      return this.termReturnFlag;
   }

   public void setTermReturnFlag(String termReturnFlag) {
      this.termReturnFlag = termReturnFlag;
   }

   public Date getOutHospitalTimeBegin() {
      return this.outHospitalTimeBegin;
   }

   public void setOutHospitalTimeBegin(Date outHospitalTimeBegin) {
      this.outHospitalTimeBegin = outHospitalTimeBegin;
   }

   public Date getOutHospitalTimeEnd() {
      return this.outHospitalTimeEnd;
   }

   public void setOutHospitalTimeEnd(Date outHospitalTimeEnd) {
      this.outHospitalTimeEnd = outHospitalTimeEnd;
   }

   public String getTermQcState() {
      return this.termQcState;
   }

   public void setTermQcState(String termQcState) {
      this.termQcState = termQcState;
   }

   public EmrQcFlowVo() {
   }

   public EmrQcFlowVo(String patientId, String qcGradeNo, String qcGradeName, Double qcScore, String mrState) {
      this.patientId = patientId;
      this.qcGradeNo = qcGradeNo;
      this.qcGradeName = qcGradeName;
      this.qcScore = qcScore;
      super.setMrState(mrState);
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Long getFlowId() {
      return this.flowId;
   }

   public void setFlowId(Long flowId) {
      this.flowId = flowId;
   }

   public Double getQcScore() {
      return this.qcScore;
   }

   public void setQcScore(Double qcScore) {
      this.qcScore = qcScore;
   }

   public String getQcGradeName() {
      return this.qcGradeName;
   }

   public void setQcGradeName(String qcGradeName) {
      this.qcGradeName = qcGradeName;
   }

   public String getQcGradeNo() {
      return this.qcGradeNo;
   }

   public void setQcGradeNo(String qcGradeNo) {
      this.qcGradeNo = qcGradeNo;
   }

   public String getIp() {
      return this.ip;
   }

   public void setIp(String ip) {
      this.ip = ip;
   }

   public String getOperReason() {
      return this.operReason;
   }

   public void setOperReason(String operReason) {
      this.operReason = operReason;
   }

   public String getSubLimit() {
      return this.subLimit;
   }

   public void setSubLimit(String subLimit) {
      this.subLimit = subLimit;
   }

   public String getPatName() {
      return this.patName;
   }

   public void setPatName(String patName) {
      this.patName = patName;
   }

   public Integer getUnWriteNum() {
      return this.unWriteNum;
   }

   public void setUnWriteNum(Integer unWriteNum) {
      this.unWriteNum = unWriteNum;
   }

   public Integer getUnFinishNum() {
      return this.unFinishNum;
   }

   public void setUnFinishNum(Integer unFinishNum) {
      this.unFinishNum = unFinishNum;
   }

   public Integer getQcEmrNum() {
      return this.qcEmrNum;
   }

   public void setQcEmrNum(Integer qcEmrNum) {
      this.qcEmrNum = qcEmrNum;
   }

   public Integer getLimitHours() {
      return this.limitHours;
   }

   public void setLimitHours(Integer limitHours) {
      this.limitHours = limitHours;
   }

   public List getEmrQcFlowRecordList() {
      return this.emrQcFlowRecordList;
   }

   public void setEmrQcFlowRecordList(List emrQcFlowRecordList) {
      this.emrQcFlowRecordList = emrQcFlowRecordList;
   }

   public List getMrStateList() {
      return this.mrStateList;
   }

   public void setMrStateList(List mrStateList) {
      this.mrStateList = mrStateList;
   }

   public Date getFileTimeBegin() {
      return this.fileTimeBegin;
   }

   public void setFileTimeBegin(Date fileTimeBegin) {
      this.fileTimeBegin = fileTimeBegin;
   }

   public Date getFileTimeEnd() {
      return this.fileTimeEnd;
   }

   public void setFileTimeEnd(Date fileTimeEnd) {
      this.fileTimeEnd = fileTimeEnd;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public Date getOutTimeBegin() {
      return this.outTimeBegin;
   }

   public void setOutTimeBegin(Date outTimeBegin) {
      this.outTimeBegin = outTimeBegin;
   }

   public Date getOutTimeEnd() {
      return this.outTimeEnd;
   }

   public void setOutTimeEnd(Date outTimeEnd) {
      this.outTimeEnd = outTimeEnd;
   }

   public Date getApplyFileTimeBegin() {
      return this.applyFileTimeBegin;
   }

   public void setApplyFileTimeBegin(Date applyFileTimeBegin) {
      this.applyFileTimeBegin = applyFileTimeBegin;
   }

   public Date getApplyFileTimeEnd() {
      return this.applyFileTimeEnd;
   }

   public void setApplyFileTimeEnd(Date applyFileTimeEnd) {
      this.applyFileTimeEnd = applyFileTimeEnd;
   }

   public Double getCostSum() {
      return this.costSum;
   }

   public void setCostSum(Double costSum) {
      this.costSum = costSum;
   }

   public Integer getDayNum() {
      return this.dayNum;
   }

   public void setDayNum(Integer dayNum) {
      this.dayNum = dayNum;
   }

   public List getDeptQcStateList() {
      return this.deptQcStateList;
   }

   public void setDeptQcStateList(List deptQcStateList) {
      this.deptQcStateList = deptQcStateList;
   }

   public List getTermQcStateList() {
      return this.termQcStateList;
   }

   public void setTermQcStateList(List termQcStateList) {
      this.termQcStateList = termQcStateList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public Long getAgeY() {
      return this.ageY;
   }

   public void setAgeY(Long ageY) {
      this.ageY = ageY;
   }

   public Long getAgeM() {
      return this.ageM;
   }

   public void setAgeM(Long ageM) {
      this.ageM = ageM;
   }

   public Long getAgeD() {
      return this.ageD;
   }

   public void setAgeD(Long ageD) {
      this.ageD = ageD;
   }

   public Long getAgeH() {
      return this.ageH;
   }

   public void setAgeH(Long ageH) {
      this.ageH = ageH;
   }

   public Long getAgeMi() {
      return this.ageMi;
   }

   public void setAgeMi(Long ageMi) {
      this.ageMi = ageMi;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getInhosWmDiaName() {
      return this.inhosWmDiaName;
   }

   public void setInhosWmDiaName(String inhosWmDiaName) {
      this.inhosWmDiaName = inhosWmDiaName;
   }

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getPcName() {
      return this.pcName;
   }

   public void setPcName(String pcName) {
      this.pcName = pcName;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getOperFlag() {
      return this.operFlag;
   }

   public void setOperFlag(String operFlag) {
      this.operFlag = operFlag;
   }

   public String getConsFlag() {
      return this.consFlag;
   }

   public void setConsFlag(String consFlag) {
      this.consFlag = consFlag;
   }

   public String getDieFlag() {
      return this.dieFlag;
   }

   public void setDieFlag(String dieFlag) {
      this.dieFlag = dieFlag;
   }

   public String getBloodTrans() {
      return this.bloodTrans;
   }

   public void setBloodTrans(String bloodTrans) {
      this.bloodTrans = bloodTrans;
   }

   public String getChangeFlag() {
      return this.changeFlag;
   }

   public String getDeptQcCd() {
      return this.deptQcCd;
   }

   public void setDeptQcCd(String deptQcCd) {
      this.deptQcCd = deptQcCd;
   }

   public String getQcState() {
      return this.qcState;
   }

   public void setQcState(String qcState) {
      this.qcState = qcState;
   }

   public void setChangeFlag(String changeFlag) {
      this.changeFlag = changeFlag;
   }

   public String getInfectFlag() {
      return this.infectFlag;
   }

   public void setInfectFlag(String infectFlag) {
      this.infectFlag = infectFlag;
   }

   public String getRescueFlag() {
      return this.rescueFlag;
   }

   public void setRescueFlag(String rescueFlag) {
      this.rescueFlag = rescueFlag;
   }

   public String getDayNumFlag() {
      return this.dayNumFlag;
   }

   public void setDayNumFlag(String dayNumFlag) {
      this.dayNumFlag = dayNumFlag;
   }

   public String getCostSumFlag() {
      return this.costSumFlag;
   }

   public void setCostSumFlag(String costSumFlag) {
      this.costSumFlag = costSumFlag;
   }

   public String getPcCdFlag() {
      return this.pcCdFlag;
   }

   public void setPcCdFlag(String pcCdFlag) {
      this.pcCdFlag = pcCdFlag;
   }

   public String getOperIcon() {
      return this.operIcon;
   }

   public void setOperIcon(String operIcon) {
      this.operIcon = operIcon;
   }

   public String getConsIcon() {
      return this.consIcon;
   }

   public void setConsIcon(String consIcon) {
      this.consIcon = consIcon;
   }

   public String getDieIcon() {
      return this.dieIcon;
   }

   public void setDieIcon(String dieIcon) {
      this.dieIcon = dieIcon;
   }

   public String getBloodIcon() {
      return this.bloodIcon;
   }

   public void setBloodIcon(String bloodIcon) {
      this.bloodIcon = bloodIcon;
   }

   public String getChangeIcon() {
      return this.changeIcon;
   }

   public void setChangeIcon(String changeIcon) {
      this.changeIcon = changeIcon;
   }

   public String getInfectIcon() {
      return this.infectIcon;
   }

   public void setInfectIcon(String infectIcon) {
      this.infectIcon = infectIcon;
   }

   public String getRescueIcon() {
      return this.rescueIcon;
   }

   public void setRescueIcon(String rescueIcon) {
      this.rescueIcon = rescueIcon;
   }

   public String getDayNumIcon() {
      return this.dayNumIcon;
   }

   public void setDayNumIcon(String dayNumIcon) {
      this.dayNumIcon = dayNumIcon;
   }

   public String getCostSumIcon() {
      return this.costSumIcon;
   }

   public void setCostSumIcon(String costSumIcon) {
      this.costSumIcon = costSumIcon;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getPayTypeName() {
      return this.payTypeName;
   }

   public void setPayTypeName(String payTypeName) {
      this.payTypeName = payTypeName;
   }

   public String getState() {
      return this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getStateName() {
      return this.stateName;
   }

   public void setStateName(String stateName) {
      this.stateName = stateName;
   }

   public Long getQcId() {
      return this.qcId;
   }

   public void setQcId(Long qcId) {
      this.qcId = qcId;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }
}
