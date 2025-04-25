package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcList;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class EmrQcListVo extends EmrQcList {
   private String checkLevel;
   private Long ListScoreId;
   private String defeLevelName;
   private String labelPage;
   private String qcType;
   private List qcSectionList;
   private List qcStateList;
   private List qcCommRecordList;
   private String mrFileName;
   private Long systemQcId;
   private Integer qcNum;
   private String mrFileShowName;
   private String limitHours;
   private String inpNo;
   private String patientName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recoDate;
   private Integer randomCheckFlag;
   private StatementVo statementVo;
   private Integer total;
   private String inDeptName;
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
   private String qcStateName;
   private String elemId;
   private String recordBz;
   private String mrState;
   private String isRecallApply;
   private String visitId;
   private String deptCd;
   private String deptName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   private String resDocCd;
   private String resDocName;
   private String recordNo;
   private Long ageY;
   private Long ageM;
   private Long ageD;
   private Long ageH;
   private Long ageMi;
   private List emrTypeCodeList;

   public String getVisitId() {
      return this.visitId;
   }

   public void setVisitId(String visitId) {
      this.visitId = visitId;
   }

   public String getDeptCd() {
      return this.deptCd;
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

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getResDocCd() {
      return this.resDocCd;
   }

   public void setResDocCd(String resDocCd) {
      this.resDocCd = resDocCd;
   }

   public String getResDocName() {
      return this.resDocName;
   }

   public void setResDocName(String resDocName) {
      this.resDocName = resDocName;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getIsRecallApply() {
      return this.isRecallApply;
   }

   public void setIsRecallApply(String isRecallApply) {
      this.isRecallApply = isRecallApply;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getRecordBz() {
      return this.recordBz;
   }

   public void setRecordBz(String recordBz) {
      this.recordBz = recordBz;
   }

   public Long getListScoreId() {
      return this.ListScoreId;
   }

   public void setListScoreId(Long listScoreId) {
      this.ListScoreId = listScoreId;
   }

   public String getElemId() {
      return this.elemId;
   }

   public void setElemId(String elemId) {
      this.elemId = elemId;
   }

   public String getQcStateName() {
      return this.qcStateName;
   }

   public void setQcStateName(String qcStateName) {
      this.qcStateName = qcStateName;
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
