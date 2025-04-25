package com.emr.project.emr.domain.vo;

import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.QcRuleResultVo;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class IndexSaveVo {
   private Long id;
   private String saveType;
   private String base64Str;
   private String xmlStr;
   private String textStr;
   private Long subFileIndexId;
   private String subFileBase64Str;
   private String subFileXmlStr;
   private String subFileTextStr;
   private Long tempId;
   private String patientId;
   private String babyId;
   private String secLevel;
   private String uperDoct;
   private String uperDoctName;
   private String titleCode;
   private String titleName;
   private String mrType;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String indexName;
   private String subIndexName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date recoDate;
   private String subIndexContElemName;
   private String subIndexUperDoct;
   private String subIndexUperDoctName;
   private String subIndexTitleCode;
   private String subIndexTitleName;
   private String emrTypeCode;
   private String emrTypeName;
   private String deptBEDateVoCurnId;
   private Boolean keepEdit;
   private String mrContent;
   private List errorMsg = new ArrayList(1);
   private List resultVoList = new ArrayList(1);
   private List qcElemList = new ArrayList();
   private List emrQcListVoList = new ArrayList();
   private Long taskId;
   private String dataType;
   private String mrFileShowName;
   public Boolean autoSaveFlag;
   public String operApplyFormNo;
   public Long operAgiRuleId;
   public String isCancelSave;
   private String emrIsUpdate;
   private String emrFileUrl;
   private String signSaveFlag;
   private String regainSaveFlag;
   private String patSignFlag;
   private Long signPersonId;
   private IndexSignPatVo indexSignPat;
   private String freeMoveType;
   private String mrFileId;
   private String toEmrTypeCode;
   private Map elemBase64Map;
   private Long resultId;
   private String subIndexMergeFlag;

   public Long getResultId() {
      return this.resultId;
   }

   public void setResultId(Long resultId) {
      this.resultId = resultId;
   }

   public Long getSignPersonId() {
      return this.signPersonId;
   }

   public void setSignPersonId(Long signPersonId) {
      this.signPersonId = signPersonId;
   }

   public Map getElemBase64Map() {
      return this.elemBase64Map;
   }

   public void setElemBase64Map(Map elemBase64Map) {
      this.elemBase64Map = elemBase64Map;
   }

   public String getToEmrTypeCode() {
      return this.toEmrTypeCode;
   }

   public void setToEmrTypeCode(String toEmrTypeCode) {
      this.toEmrTypeCode = toEmrTypeCode;
   }

   public String getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(String mrFileId) {
      this.mrFileId = mrFileId;
   }

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public IndexSignPatVo getIndexSignPat() {
      return this.indexSignPat;
   }

   public void setIndexSignPat(IndexSignPatVo indexSignPat) {
      this.indexSignPat = indexSignPat;
   }

   public String getPatSignFlag() {
      return this.patSignFlag;
   }

   public void setPatSignFlag(String patSignFlag) {
      this.patSignFlag = patSignFlag;
   }

   public String getRegainSaveFlag() {
      return this.regainSaveFlag;
   }

   public void setRegainSaveFlag(String regainSaveFlag) {
      this.regainSaveFlag = regainSaveFlag;
   }

   public String getSignSaveFlag() {
      return this.signSaveFlag;
   }

   public void setSignSaveFlag(String signSaveFlag) {
      this.signSaveFlag = signSaveFlag;
   }

   public String getEmrFileUrl() {
      return this.emrFileUrl;
   }

   public void setEmrFileUrl(String emrFileUrl) {
      this.emrFileUrl = emrFileUrl;
   }

   public String getEmrIsUpdate() {
      return this.emrIsUpdate;
   }

   public void setEmrIsUpdate(String emrIsUpdate) {
      this.emrIsUpdate = emrIsUpdate;
   }

   public String getIsCancelSave() {
      return this.isCancelSave;
   }

   public void setIsCancelSave(String isCancelSave) {
      this.isCancelSave = isCancelSave;
   }

   public Long getOperAgiRuleId() {
      return this.operAgiRuleId;
   }

   public void setOperAgiRuleId(Long operAgiRuleId) {
      this.operAgiRuleId = operAgiRuleId;
   }

   public String getOperApplyFormNo() {
      return this.operApplyFormNo;
   }

   public void setOperApplyFormNo(String operApplyFormNo) {
      this.operApplyFormNo = operApplyFormNo;
   }

   public String getMrFileShowName() {
      return this.mrFileShowName;
   }

   public void setMrFileShowName(String mrFileShowName) {
      this.mrFileShowName = mrFileShowName;
   }

   public String getDataType() {
      return this.dataType;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public Long getTaskId() {
      return this.taskId;
   }

   public void setTaskId(Long taskId) {
      this.taskId = taskId;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public List getEmrQcListVoList() {
      return this.emrQcListVoList;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public void setEmrQcListVoList(List emrQcListVoList) {
      this.emrQcListVoList = emrQcListVoList;
   }

   public List getQcElemList() {
      return this.qcElemList;
   }

   public void setQcElemList(List qcElemList) {
      this.qcElemList = qcElemList;
   }

   public List getErrorMsg() {
      return this.errorMsg;
   }

   public void setErrorMsg(List errorMsg) {
      this.errorMsg = errorMsg;
   }

   public List getResultVoList() {
      return this.resultVoList;
   }

   public void setResultVoList(List resultVoList) {
      this.resultVoList = resultVoList;
   }

   public IndexSaveVo() {
   }

   public IndexSaveVo(IndexSignVo indexSignVo) {
      this.id = indexSignVo.getId();
      this.base64Str = indexSignVo.getBase64Str();
      this.xmlStr = indexSignVo.getXmlStr();
      this.subFileIndexId = indexSignVo.getSubFileIndexId();
      this.subFileBase64Str = indexSignVo.getSubFileBase64Str();
      this.subFileXmlStr = indexSignVo.getSubFileXmlStr();
      this.emrQcListVoList.addAll(indexSignVo.getEmrQcListVoList());
      this.indexName = indexSignVo.getIndexName();
      this.subIndexName = indexSignVo.getSubIndexName();
      this.emrIsUpdate = indexSignVo.getEmrIsUpdate();
      this.keepEdit = indexSignVo.getKeepEdit();
   }

   public Date getRecoDate() {
      return this.recoDate;
   }

   public void setRecoDate(Date recoDate) {
      this.recoDate = recoDate;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getSaveType() {
      return this.saveType;
   }

   public void setSaveType(String saveType) {
      this.saveType = saveType;
   }

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public Long getSubFileIndexId() {
      return this.subFileIndexId;
   }

   public void setSubFileIndexId(Long subFileIndexId) {
      this.subFileIndexId = subFileIndexId;
   }

   public String getSubFileBase64Str() {
      return this.subFileBase64Str;
   }

   public void setSubFileBase64Str(String subFileBase64Str) {
      this.subFileBase64Str = subFileBase64Str;
   }

   public String getSubFileXmlStr() {
      return this.subFileXmlStr;
   }

   public void setSubFileXmlStr(String subFileXmlStr) {
      this.subFileXmlStr = subFileXmlStr;
   }

   public Long getTempId() {
      return this.tempId;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getBabyId() {
      return this.babyId;
   }

   public void setBabyId(String babyId) {
      this.babyId = babyId;
   }

   public String getSecLevel() {
      return this.secLevel;
   }

   public void setSecLevel(String secLevel) {
      this.secLevel = secLevel;
   }

   public String getUperDoct() {
      return this.uperDoct;
   }

   public void setUperDoct(String uperDoct) {
      this.uperDoct = uperDoct;
   }

   public String getUperDoctName() {
      return this.uperDoctName;
   }

   public void setUperDoctName(String uperDoctName) {
      this.uperDoctName = uperDoctName;
   }

   public String getTitleCode() {
      return this.titleCode;
   }

   public void setTitleCode(String titleCode) {
      this.titleCode = titleCode;
   }

   public String getTitleName() {
      return this.titleName;
   }

   public void setTitleName(String titleName) {
      this.titleName = titleName;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(String indexName) {
      this.indexName = indexName;
   }

   public String getSubIndexName() {
      return this.subIndexName;
   }

   public void setSubIndexName(String subIndexName) {
      this.subIndexName = subIndexName;
   }

   public String getSubIndexContElemName() {
      return this.subIndexContElemName;
   }

   public void setSubIndexContElemName(String subIndexContElemName) {
      this.subIndexContElemName = subIndexContElemName;
   }

   public String getSubIndexUperDoct() {
      return this.subIndexUperDoct;
   }

   public void setSubIndexUperDoct(String subIndexUperDoct) {
      this.subIndexUperDoct = subIndexUperDoct;
   }

   public String getSubIndexUperDoctName() {
      return this.subIndexUperDoctName;
   }

   public void setSubIndexUperDoctName(String subIndexUperDoctName) {
      this.subIndexUperDoctName = subIndexUperDoctName;
   }

   public String getSubIndexTitleCode() {
      return this.subIndexTitleCode;
   }

   public void setSubIndexTitleCode(String subIndexTitleCode) {
      this.subIndexTitleCode = subIndexTitleCode;
   }

   public String getSubIndexTitleName() {
      return this.subIndexTitleName;
   }

   public void setSubIndexTitleName(String subIndexTitleName) {
      this.subIndexTitleName = subIndexTitleName;
   }

   public Boolean getAutoSaveFlag() {
      return this.autoSaveFlag;
   }

   public void setAutoSaveFlag(Boolean autoSaveFlag) {
      this.autoSaveFlag = autoSaveFlag;
   }

   public String getTextStr() {
      return this.textStr;
   }

   public void setTextStr(String textStr) {
      this.textStr = textStr;
   }

   public String getSubFileTextStr() {
      return this.subFileTextStr;
   }

   public void setSubFileTextStr(String subFileTextStr) {
      this.subFileTextStr = subFileTextStr;
   }

   public String getDeptBEDateVoCurnId() {
      return this.deptBEDateVoCurnId;
   }

   public void setDeptBEDateVoCurnId(String deptBEDateVoCurnId) {
      this.deptBEDateVoCurnId = deptBEDateVoCurnId;
   }

   public String getMrContent() {
      return this.mrContent;
   }

   public void setMrContent(String mrContent) {
      this.mrContent = mrContent;
   }

   public Boolean getKeepEdit() {
      return this.keepEdit;
   }

   public void setKeepEdit(Boolean keepEdit) {
      this.keepEdit = keepEdit;
   }

   public String getSubIndexMergeFlag() {
      return this.subIndexMergeFlag;
   }

   public void setSubIndexMergeFlag(String subIndexMergeFlag) {
      this.subIndexMergeFlag = subIndexMergeFlag;
   }
}
