package com.emr.project.qc.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class EmrQcExporByDeptCodeVo {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "科室"
   )
   private String inDeptName;
   @Excel(
      name = "患者住院号"
   )
   private String inpNo;
   @Excel(
      name = "患者姓名"
   )
   private String patientName;
   @Excel(
      name = "责任医师"
   )
   private String doctName;
   @Excel(
      name = "病历类型"
   )
   private String mrTypeName;
   @Excel(
      name = "缺陷名称"
   )
   private String ruleName;
   @Excel(
      name = "缺陷描述"
   )
   private String flawDesc;
   @Excel(
      name = "缺陷病历名称"
   )
   private String mrFileName;
   @Excel(
      name = "缺陷类型"
   )
   private String emrEleName;
   private String qcState;
   @Excel(
      name = "缺陷状态"
   )
   private String qcStateName;
   private String patientId;
   private String checkLevel;
   private String defeLevelName;
   private String labelPage;
   private String qcType;
   private List qcSectionList;
   private List qcStateList;
   private List qcCommRecordList;
   private Long systemQcId;
   private Integer qcNum;
   private String mrFileShowName;
   private String limitHours;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recoDate;
   private Integer randomCheckFlag;
   private StatementVo statementVo;
   private Integer total;
   private String patientMainId;
   private String emrMainId;
   private String bedNo;
   private String sex;
   private String messageStr;
   private String dedId;
   private String dedDesc;
   private String itemCd;
   private String itemName;
   private String dedType;
   private Double dedScoreSingle;
   private String dedScoreDesc;
   private List mrFileIdList;
   private String base64Str;
   private String xmlStr;
   private Double schetotalscore;
   private Long itemId;
   private Integer itemClassSort;
   private Integer detailSort;
   private String itemClassCd;
   private String itemClassName;
   private String itemDesc;
   private Double itemTotalScore;
   private Integer itemSort;
   private Long id;
   private Long mainId;
   private String mrType;
   private Integer qconTimes;
   private String emrEleId;
   private String defectRow;
   private String eleContext;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date treatTime;
   private String treatCd;
   private String treatName;
   private String treatFlag;
   private String treatDesc;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date conTime;
   private String conCd;
   private String conName;
   private String deadlineUnit;
   private String conResult;
   private String conDesc;
   private String doctCd;
   private String qcSection;
   private String qcdoctCd;
   private String qcdoctName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date qcDate;
   private Long treatDeadline;
   private String mrFileId;
   private String altPerName;
   private String altPerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date altDate;
   private String crePerName;
   private String crePerCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String flawDate;
   private Long ruleId;
   private String defeLevel;
   private String dbEleId;
   private String recordId;
   private List emrTypeCodeList;

   public String getQcStateName() {
      return this.qcStateName;
   }

   public void setQcStateName(String qcStateName) {
      this.qcStateName = qcStateName;
   }

   public String getRuleName() {
      return this.ruleName;
   }

   public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
   }

   public String getFlawDesc() {
      return this.flawDesc;
   }

   public void setFlawDesc(String flawDesc) {
      this.flawDesc = flawDesc;
   }

   public String getDoctName() {
      return this.doctName;
   }

   public void setDoctName(String doctName) {
      this.doctName = doctName;
   }

   public String getEmrEleName() {
      return this.emrEleName;
   }

   public void setEmrEleName(String emrEleName) {
      this.emrEleName = emrEleName;
   }

   public String getQcState() {
      return this.qcState;
   }

   public void setQcState(String qcState) {
      this.qcState = qcState;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getMrTypeName() {
      return this.mrTypeName;
   }

   public void setMrTypeName(String mrTypeName) {
      this.mrTypeName = mrTypeName;
   }

   public Integer getQconTimes() {
      return this.qconTimes;
   }

   public void setQconTimes(Integer qconTimes) {
      this.qconTimes = qconTimes;
   }

   public String getEmrEleId() {
      return this.emrEleId;
   }

   public void setEmrEleId(String emrEleId) {
      this.emrEleId = emrEleId;
   }

   public String getDefectRow() {
      return this.defectRow;
   }

   public void setDefectRow(String defectRow) {
      this.defectRow = defectRow;
   }

   public String getEleContext() {
      return this.eleContext;
   }

   public void setEleContext(String eleContext) {
      this.eleContext = eleContext;
   }

   public Date getTreatTime() {
      return this.treatTime;
   }

   public void setTreatTime(Date treatTime) {
      this.treatTime = treatTime;
   }

   public String getTreatCd() {
      return this.treatCd;
   }

   public void setTreatCd(String treatCd) {
      this.treatCd = treatCd;
   }

   public String getTreatName() {
      return this.treatName;
   }

   public void setTreatName(String treatName) {
      this.treatName = treatName;
   }

   public String getTreatFlag() {
      return this.treatFlag;
   }

   public void setTreatFlag(String treatFlag) {
      this.treatFlag = treatFlag;
   }

   public String getTreatDesc() {
      return this.treatDesc;
   }

   public void setTreatDesc(String treatDesc) {
      this.treatDesc = treatDesc;
   }

   public Date getConTime() {
      return this.conTime;
   }

   public void setConTime(Date conTime) {
      this.conTime = conTime;
   }

   public String getConCd() {
      return this.conCd;
   }

   public void setConCd(String conCd) {
      this.conCd = conCd;
   }

   public String getConName() {
      return this.conName;
   }

   public void setConName(String conName) {
      this.conName = conName;
   }

   public String getDeadlineUnit() {
      return this.deadlineUnit;
   }

   public void setDeadlineUnit(String deadlineUnit) {
      this.deadlineUnit = deadlineUnit;
   }

   public String getConResult() {
      return this.conResult;
   }

   public void setConResult(String conResult) {
      this.conResult = conResult;
   }

   public String getConDesc() {
      return this.conDesc;
   }

   public void setConDesc(String conDesc) {
      this.conDesc = conDesc;
   }

   public String getDoctCd() {
      return this.doctCd;
   }

   public void setDoctCd(String doctCd) {
      this.doctCd = doctCd;
   }

   public String getQcSection() {
      return this.qcSection;
   }

   public void setQcSection(String qcSection) {
      this.qcSection = qcSection;
   }

   public String getQcdoctCd() {
      return this.qcdoctCd;
   }

   public void setQcdoctCd(String qcdoctCd) {
      this.qcdoctCd = qcdoctCd;
   }

   public String getQcdoctName() {
      return this.qcdoctName;
   }

   public void setQcdoctName(String qcdoctName) {
      this.qcdoctName = qcdoctName;
   }

   public Date getQcDate() {
      return this.qcDate;
   }

   public void setQcDate(Date qcDate) {
      this.qcDate = qcDate;
   }

   public Long getTreatDeadline() {
      return this.treatDeadline;
   }

   public void setTreatDeadline(Long treatDeadline) {
      this.treatDeadline = treatDeadline;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getAltPerName() {
      return this.altPerName;
   }

   public void setAltPerName(String altPerName) {
      this.altPerName = altPerName;
   }

   public String getAltPerCode() {
      return this.altPerCode;
   }

   public void setAltPerCode(String altPerCode) {
      this.altPerCode = altPerCode;
   }

   public Date getAltDate() {
      return this.altDate;
   }

   public void setAltDate(Date altDate) {
      this.altDate = altDate;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getFlawDate() {
      return this.flawDate;
   }

   public void setFlawDate(String flawDate) {
      this.flawDate = flawDate;
   }

   public Long getRuleId() {
      return this.ruleId;
   }

   public void setRuleId(Long ruleId) {
      this.ruleId = ruleId;
   }

   public String getDefeLevel() {
      return this.defeLevel;
   }

   public void setDefeLevel(String defeLevel) {
      this.defeLevel = defeLevel;
   }

   public String getDbEleId() {
      return this.dbEleId;
   }

   public void setDbEleId(String dbEleId) {
      this.dbEleId = dbEleId;
   }

   public String getRecordId() {
      return this.recordId;
   }

   public void setRecordId(String recordId) {
      this.recordId = recordId;
   }

   public Integer getItemSort() {
      return this.itemSort;
   }

   public void setItemSort(Integer itemSort) {
      this.itemSort = itemSort;
   }

   public Double getItemTotalScore() {
      return this.itemTotalScore;
   }

   public void setItemTotalScore(Double itemTotalScore) {
      this.itemTotalScore = itemTotalScore;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getItemClassName() {
      return this.itemClassName;
   }

   public void setItemClassName(String itemClassName) {
      this.itemClassName = itemClassName;
   }

   public String getItemDesc() {
      return this.itemDesc;
   }

   public void setItemDesc(String itemDesc) {
      this.itemDesc = itemDesc;
   }

   public Double getSchetotalscore() {
      return this.schetotalscore;
   }

   public void setSchetotalscore(Double schetotalscore) {
      this.schetotalscore = schetotalscore;
   }

   public Long getItemId() {
      return this.itemId;
   }

   public void setItemId(Long itemId) {
      this.itemId = itemId;
   }

   public Integer getItemClassSort() {
      return this.itemClassSort;
   }

   public void setItemClassSort(Integer itemClassSort) {
      this.itemClassSort = itemClassSort;
   }

   public Integer getDetailSort() {
      return this.detailSort;
   }

   public void setDetailSort(Integer detailSort) {
      this.detailSort = detailSort;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }

   public String getDedId() {
      return this.dedId;
   }

   public void setDedId(String dedId) {
      this.dedId = dedId;
   }

   public String getDedDesc() {
      return this.dedDesc;
   }

   public void setDedDesc(String dedDesc) {
      this.dedDesc = dedDesc;
   }

   public String getItemCd() {
      return this.itemCd;
   }

   public void setItemCd(String itemCd) {
      this.itemCd = itemCd;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getDedType() {
      return this.dedType;
   }

   public void setDedType(String dedType) {
      this.dedType = dedType;
   }

   public Double getDedScoreSingle() {
      return this.dedScoreSingle;
   }

   public void setDedScoreSingle(Double dedScoreSingle) {
      this.dedScoreSingle = dedScoreSingle;
   }

   public String getDedScoreDesc() {
      return this.dedScoreDesc;
   }

   public void setDedScoreDesc(String dedScoreDesc) {
      this.dedScoreDesc = dedScoreDesc;
   }

   public List getMrFileIdList() {
      return this.mrFileIdList;
   }

   public void setMrFileIdList(List mrFileIdList) {
      this.mrFileIdList = mrFileIdList;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getEmrMainId() {
      return this.emrMainId;
   }

   public void setEmrMainId(String emrMainId) {
      this.emrMainId = emrMainId;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getInDeptName() {
      return this.inDeptName;
   }

   public void setInDeptName(String inDeptName) {
      this.inDeptName = inDeptName;
   }

   public List getEmrTypeCodeList() {
      return this.emrTypeCodeList;
   }

   public void setEmrTypeCodeList(List emrTypeCodeList) {
      this.emrTypeCodeList = emrTypeCodeList;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public StatementVo getStatementVo() {
      return this.statementVo;
   }

   public void setStatementVo(StatementVo statementVo) {
      this.statementVo = statementVo;
   }

   public Integer getRandomCheckFlag() {
      return this.randomCheckFlag;
   }

   public void setRandomCheckFlag(Integer randomCheckFlag) {
      this.randomCheckFlag = randomCheckFlag;
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
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

   public String getLimitHours() {
      return this.limitHours;
   }

   public void setLimitHours(String limitHours) {
      this.limitHours = limitHours;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public Integer getQcNum() {
      return this.qcNum;
   }

   public void setQcNum(Integer qcNum) {
      this.qcNum = qcNum;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public List getQcCommRecordList() {
      return this.qcCommRecordList;
   }

   public void setQcCommRecordList(List qcCommRecordList) {
      this.qcCommRecordList = qcCommRecordList;
   }

   public Long getSystemQcId() {
      return this.systemQcId;
   }

   public void setSystemQcId(Long systemQcId) {
      this.systemQcId = systemQcId;
   }

   public String getMrFileName() {
      return this.mrFileName;
   }

   public void setMrFileName(String mrFileName) {
      this.mrFileName = mrFileName;
   }

   public List getQcStateList() {
      return this.qcStateList;
   }

   public void setQcStateList(List qcStateList) {
      this.qcStateList = qcStateList;
   }

   public String getQcType() {
      return this.qcType;
   }

   public void setQcType(String qcType) {
      this.qcType = qcType;
   }

   public List getQcSectionList() {
      return this.qcSectionList;
   }

   public void setQcSectionList(List qcSectionList) {
      this.qcSectionList = qcSectionList;
   }

   public String getLabelPage() {
      return this.labelPage;
   }

   public void setLabelPage(String labelPage) {
      this.labelPage = labelPage;
   }

   public String getCheckLevel() {
      return this.checkLevel;
   }

   public void setCheckLevel(String checkLevel) {
      this.checkLevel = checkLevel;
   }
}
