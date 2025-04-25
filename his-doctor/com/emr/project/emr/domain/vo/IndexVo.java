package com.emr.project.emr.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.Index;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class IndexVo extends Index {
   private String altDateBegin;
   private String visitNo;
   private String altDateEnd;
   private Date creDateBegin;
   private Date creDateEnd;
   private Long emrTypeCode;
   private String emrTypeName;
   private String mrCon;
   private String xmlStr;
   private String base64Str;
   private List base64StrList;
   private List mrFileIdList;
   private Long subFileIndexId;
   private List emrSignRecordList;
   private Long mainId;
   private List macroList;
   private List quoteList;
   private List idList;
   private List admissionNos;
   private List setStructsTextIdList;
   private String dutyDocName;
   private Boolean agingFlag;
   private String firstSign;
   private String superSign;
   private String finishSign;
   private String flawNum;
   private Long taskId;
   private String agiRuleCode;
   private String ruleCode;
   private String remainingTime;
   private String mainFileFlag;
   private String taskType;
   private Long primaryId;
   private String mainName;
   private String mark;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date endTime;
   private String dataType;
   private List noFinishStateList;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "记录日期",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date subRecoDate;
   private String mrTypeClass;
   private String patientMainId;
   private Boolean deptFlag;
   private String agiRuleId;
   private String agiRuleName;
   private String messageStr;
   private String sex;
   private String resDocName;
   private String resDocCd;
   private String editMode;
   private String manMadeQcListFlag;
   private String filePath;
   private String recordNo;
   private String allFlag;
   private Integer browserType;
   private String browserTypeName;
   @Excel(
      name = "病历内容(txt)"
   )
   private String mrContent;
   private String freeMoveType;
   private String newSubFileFlag;
   private String mainFileCancelSignFlag;
   private String closeEditIgnoreIp;
   private List signList;
   private List dataElementList;
   private List dataArrayList;
   private List dataAreaList;
   private List elemForBase64List;
   private String subFileXmlStr;
   private String signFlag;
   private String signCertSn;
   private String tempName;
   private String showName;
   private String tempMajor;
   private String tempDisease;
   private String qcBillFlag;
   private Long fileId;
   private String proofNo;
   private Long proofId;
   private List childList = new ArrayList();
   private List emrTypeCodeList;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date inhosTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date outTime;
   private Integer mrTypeCdIndex;
   private String testExamResultId;
   private Boolean print;
   private String routePath;
   private String menuTypeCode;
   private List subFileIndexList;

   public Long getProofId() {
      return this.proofId;
   }

   public void setProofId(Long proofId) {
      this.proofId = proofId;
   }

   public Long getFileId() {
      return this.fileId;
   }

   public void setFileId(Long fileId) {
      this.fileId = fileId;
   }

   public String getProofNo() {
      return this.proofNo;
   }

   public void setProofNo(String proofNo) {
      this.proofNo = proofNo;
   }

   public String getSignCertSn() {
      return this.signCertSn;
   }

   public void setSignCertSn(String signCertSn) {
      this.signCertSn = signCertSn;
   }

   public String getSignFlag() {
      return this.signFlag;
   }

   public void setSignFlag(String signFlag) {
      this.signFlag = signFlag;
   }

   public List getBase64StrList() {
      return this.base64StrList;
   }

   public void setBase64StrList(List base64StrList) {
      this.base64StrList = base64StrList;
   }

   public List getMrFileIdList() {
      return this.mrFileIdList;
   }

   public void setMrFileIdList(List mrFileIdList) {
      this.mrFileIdList = mrFileIdList;
   }

   public String getSubFileXmlStr() {
      return this.subFileXmlStr;
   }

   public void setSubFileXmlStr(String subFileXmlStr) {
      this.subFileXmlStr = subFileXmlStr;
   }

   public List getElemForBase64List() {
      return this.elemForBase64List;
   }

   public void setElemForBase64List(List elemForBase64List) {
      this.elemForBase64List = elemForBase64List;
   }

   public String getTaskType() {
      return this.taskType;
   }

   public void setTaskType(String taskType) {
      this.taskType = taskType;
   }

   public List getDataElementList() {
      return this.dataElementList;
   }

   public void setDataElementList(List dataElementList) {
      this.dataElementList = dataElementList;
   }

   public List getAdmissionNos() {
      return this.admissionNos;
   }

   public void setAdmissionNos(List admissionNos) {
      this.admissionNos = admissionNos;
   }

   public List getDataArrayList() {
      return this.dataArrayList;
   }

   public void setDataArrayList(List dataArrayList) {
      this.dataArrayList = dataArrayList;
   }

   public List getDataAreaList() {
      return this.dataAreaList;
   }

   public void setDataAreaList(List dataAreaList) {
      this.dataAreaList = dataAreaList;
   }

   public Integer getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(Integer browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public List getSignList() {
      return this.signList;
   }

   public void setSignList(List signList) {
      this.signList = signList;
   }

   public String getCloseEditIgnoreIp() {
      return this.closeEditIgnoreIp;
   }

   public void setCloseEditIgnoreIp(String closeEditIgnoreIp) {
      this.closeEditIgnoreIp = closeEditIgnoreIp;
   }

   public String getMainFileCancelSignFlag() {
      return this.mainFileCancelSignFlag;
   }

   public void setMainFileCancelSignFlag(String mainFileCancelSignFlag) {
      this.mainFileCancelSignFlag = mainFileCancelSignFlag;
   }

   public String getNewSubFileFlag() {
      return this.newSubFileFlag;
   }

   public void setNewSubFileFlag(String newSubFileFlag) {
      this.newSubFileFlag = newSubFileFlag;
   }

   public String getMrContent() {
      return this.mrContent;
   }

   public void setMrContent(String mrContent) {
      this.mrContent = mrContent;
   }

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public String getAllFlag() {
      return this.allFlag;
   }

   public void setAllFlag(String allFlag) {
      this.allFlag = allFlag;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getMenuTypeCode() {
      return this.menuTypeCode;
   }

   public void setMenuTypeCode(String menuTypeCode) {
      this.menuTypeCode = menuTypeCode;
   }

   public Boolean getPrint() {
      return this.print;
   }

   public void setPrint(Boolean print) {
      this.print = print;
   }

   public String getRoutePath() {
      return this.routePath;
   }

   public void setRoutePath(String routePath) {
      this.routePath = routePath;
   }

   public String getTestExamResultId() {
      return this.testExamResultId;
   }

   public void setTestExamResultId(String testExamResultId) {
      this.testExamResultId = testExamResultId;
   }

   public Integer getMrTypeCdIndex() {
      return this.mrTypeCdIndex;
   }

   public void setMrTypeCdIndex(Integer mrTypeCdIndex) {
      this.mrTypeCdIndex = mrTypeCdIndex;
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

   public List getEmrTypeCodeList() {
      return this.emrTypeCodeList;
   }

   public void setEmrTypeCodeList(List emrTypeCodeList) {
      this.emrTypeCodeList = emrTypeCodeList;
   }

   public String getFilePath() {
      return this.filePath;
   }

   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }

   public String getManMadeQcListFlag() {
      return this.manMadeQcListFlag;
   }

   public void setManMadeQcListFlag(String manMadeQcListFlag) {
      this.manMadeQcListFlag = manMadeQcListFlag;
   }

   public List getChildList() {
      return this.childList;
   }

   public void setChildList(List childList) {
      this.childList = childList;
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

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getAgiRuleName() {
      return this.agiRuleName;
   }

   public void setAgiRuleName(String agiRuleName) {
      this.agiRuleName = agiRuleName;
   }

   public String getAgiRuleId() {
      return this.agiRuleId;
   }

   public void setAgiRuleId(String agiRuleId) {
      this.agiRuleId = agiRuleId;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public Boolean getDeptFlag() {
      return this.deptFlag;
   }

   public void setDeptFlag(Boolean deptFlag) {
      this.deptFlag = deptFlag;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public String getMrTypeClass() {
      return this.mrTypeClass;
   }

   public void setMrTypeClass(String mrTypeClass) {
      this.mrTypeClass = mrTypeClass;
   }

   public String getRuleCode() {
      return this.ruleCode;
   }

   public void setRuleCode(String ruleCode) {
      this.ruleCode = ruleCode;
   }

   public Date getSubRecoDate() {
      return this.subRecoDate;
   }

   public void setSubRecoDate(Date subRecoDate) {
      this.subRecoDate = subRecoDate;
   }

   public List getNoFinishStateList() {
      return this.noFinishStateList;
   }

   public void setNoFinishStateList(List noFinishStateList) {
      this.noFinishStateList = noFinishStateList;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getMark() {
      return this.mark;
   }

   public void setMark(String mark) {
      this.mark = mark;
   }

   public String getMainName() {
      return this.mainName;
   }

   public void setMainName(String mainName) {
      this.mainName = mainName;
   }

   public Long getPrimaryId() {
      return this.primaryId;
   }

   public void setPrimaryId(Long primaryId) {
      this.primaryId = primaryId;
   }

   public String getMainFileFlag() {
      return this.mainFileFlag;
   }

   public void setMainFileFlag(String mainFileFlag) {
      this.mainFileFlag = mainFileFlag;
   }

   public Long getTaskId() {
      return this.taskId;
   }

   public void setTaskId(Long taskId) {
      this.taskId = taskId;
   }

   public String getAgiRuleCode() {
      return this.agiRuleCode;
   }

   public void setAgiRuleCode(String agiRuleCode) {
      this.agiRuleCode = agiRuleCode;
   }

   public String getRemainingTime() {
      return this.remainingTime;
   }

   public void setRemainingTime(String remainingTime) {
      this.remainingTime = remainingTime;
   }

   public String getFlawNum() {
      return this.flawNum;
   }

   public void setFlawNum(String flawNum) {
      this.flawNum = flawNum;
   }

   public String getFirstSign() {
      return this.firstSign;
   }

   public void setFirstSign(String firstSign) {
      this.firstSign = firstSign;
   }

   public String getSuperSign() {
      return this.superSign;
   }

   public void setSuperSign(String superSign) {
      this.superSign = superSign;
   }

   public String getFinishSign() {
      return this.finishSign;
   }

   public void setFinishSign(String finishSign) {
      this.finishSign = finishSign;
   }

   public Boolean getAgingFlag() {
      return this.agingFlag;
   }

   public void setAgingFlag(Boolean agingFlag) {
      this.agingFlag = agingFlag;
   }

   public String getDutyDocName() {
      return this.dutyDocName;
   }

   public void setDutyDocName(String dutyDocName) {
      this.dutyDocName = dutyDocName;
   }

   public List getSetStructsTextIdList() {
      return this.setStructsTextIdList;
   }

   public void setSetStructsTextIdList(List setStructsTextIdList) {
      this.setStructsTextIdList = setStructsTextIdList;
   }

   public List getIdList() {
      return this.idList;
   }

   public void setIdList(List idList) {
      this.idList = idList;
   }

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }

   public List getMacroList() {
      return this.macroList;
   }

   public void setMacroList(List macroList) {
      this.macroList = macroList;
   }

   public List getQuoteList() {
      return this.quoteList;
   }

   public void setQuoteList(List quoteList) {
      this.quoteList = quoteList;
   }

   public Long getMainId() {
      return this.mainId;
   }

   public void setMainId(Long mainId) {
      this.mainId = mainId;
   }

   public List getEmrSignRecordList() {
      return this.emrSignRecordList;
   }

   public void setEmrSignRecordList(List emrSignRecordList) {
      this.emrSignRecordList = emrSignRecordList;
   }

   public Long getSubFileIndexId() {
      return this.subFileIndexId;
   }

   public void setSubFileIndexId(Long subFileIndexId) {
      this.subFileIndexId = subFileIndexId;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public String getMrCon() {
      return this.mrCon;
   }

   public void setMrCon(String mrCon) {
      this.mrCon = mrCon;
   }

   public List getSubFileIndexList() {
      return this.subFileIndexList;
   }

   public void setSubFileIndexList(List subFileIndexList) {
      this.subFileIndexList = subFileIndexList;
   }

   public Date getCreDateBegin() {
      return this.creDateBegin;
   }

   public void setCreDateBegin(Date creDateBegin) {
      this.creDateBegin = creDateBegin;
   }

   public Date getCreDateEnd() {
      return this.creDateEnd;
   }

   public void setCreDateEnd(Date creDateEnd) {
      this.creDateEnd = creDateEnd;
   }

   public Long getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(Long emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getAltDateBegin() {
      return this.altDateBegin;
   }

   public void setAltDateBegin(String altDateBegin) {
      this.altDateBegin = altDateBegin;
   }

   public String getAltDateEnd() {
      return this.altDateEnd;
   }

   public void setAltDateEnd(String altDateEnd) {
      this.altDateEnd = altDateEnd;
   }

   public String getEditMode() {
      return this.editMode;
   }

   public void setEditMode(String editMode) {
      this.editMode = editMode;
   }

   public String getTempName() {
      return this.tempName;
   }

   public void setTempName(String tempName) {
      this.tempName = tempName;
   }

   public String getShowName() {
      return this.showName;
   }

   public void setShowName(String showName) {
      this.showName = showName;
   }

   public String getTempMajor() {
      return this.tempMajor;
   }

   public void setTempMajor(String tempMajor) {
      this.tempMajor = tempMajor;
   }

   public String getTempDisease() {
      return this.tempDisease;
   }

   public void setTempDisease(String tempDisease) {
      this.tempDisease = tempDisease;
   }

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public String getQcBillFlag() {
      return this.qcBillFlag;
   }

   public void setQcBillFlag(String qcBillFlag) {
      this.qcBillFlag = qcBillFlag;
   }
}
