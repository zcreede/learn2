package com.emr.project.dr.domain.vo;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.emr.domain.vo.ElemSignVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class TdCaConsApplyVo extends TdCaConsApply {
   private String base64Str;
   private String xmlStr;
   private String indexName;
   private String mrType;
   private String textStr;
   private Long tempId;
   private String docCd;
   private String consQuality;
   @Excel(
      name = "累计用时",
      sort = 9
   )
   private String remainHours;
   private List stateList;
   @Excel(
      name = "超时状态",
      sort = 10
   )
   private String exceedState;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date startDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date endDate;
   private String docName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date timeNode;
   private String stateNode;
   private String docType;
   private String satiCd;
   private Integer startHours;
   private Integer endHours;
   private String patientMainId;
   private String archiaterFlag;
   private String mrState;
   private String messageStr;
   private String emrFileUrl;
   private String mrContent;
   private List signElemList;
   private List signElemNameList;
   private String assignDocFlag;
   private String recordNo;

   public List getSignElemNameList() {
      return this.signElemNameList;
   }

   public void setSignElemNameList(List signElemNameList) {
      this.signElemNameList = signElemNameList;
   }

   public List getSignElemList() {
      return this.signElemList;
   }

   public void setSignElemList(List signElemList) {
      this.signElemList = signElemList;
   }

   public String getMrContent() {
      return this.mrContent;
   }

   public void setMrContent(String mrContent) {
      this.mrContent = mrContent;
   }

   public String getEmrFileUrl() {
      return this.emrFileUrl;
   }

   public void setEmrFileUrl(String emrFileUrl) {
      this.emrFileUrl = emrFileUrl;
   }

   public String getMessageStr() {
      return this.messageStr;
   }

   public void setMessageStr(String messageStr) {
      this.messageStr = messageStr;
   }

   public String getMrState() {
      return this.mrState;
   }

   public void setMrState(String mrState) {
      this.mrState = mrState;
   }

   public String getArchiaterFlag() {
      return this.archiaterFlag;
   }

   public void setArchiaterFlag(String archiaterFlag) {
      this.archiaterFlag = archiaterFlag;
   }

   public String getPatientMainId() {
      return this.patientMainId;
   }

   public void setPatientMainId(String patientMainId) {
      this.patientMainId = patientMainId;
   }

   public Integer getStartHours() {
      return this.startHours;
   }

   public void setStartHours(Integer startHours) {
      this.startHours = startHours;
   }

   public Integer getEndHours() {
      return this.endHours;
   }

   public void setEndHours(Integer endHours) {
      this.endHours = endHours;
   }

   public String getSatiCd() {
      return this.satiCd;
   }

   public void setSatiCd(String satiCd) {
      this.satiCd = satiCd;
   }

   public String getDocType() {
      return this.docType;
   }

   public void setDocType(String docType) {
      this.docType = docType;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public Date getTimeNode() {
      return this.timeNode;
   }

   public void setTimeNode(Date timeNode) {
      this.timeNode = timeNode;
   }

   public String getStateNode() {
      return this.stateNode;
   }

   public void setStateNode(String stateNode) {
      this.stateNode = stateNode;
   }

   public String getExceedState() {
      return this.exceedState;
   }

   public void setExceedState(String exceedState) {
      this.exceedState = exceedState;
   }

   public List getStateList() {
      return this.stateList;
   }

   public void setStateList(List stateList) {
      this.stateList = stateList;
   }

   public String getRemainHours() {
      return this.remainHours;
   }

   public void setRemainHours(String remainHours) {
      this.remainHours = remainHours;
   }

   public String getConsQuality() {
      return this.consQuality;
   }

   public void setConsQuality(String consQuality) {
      this.consQuality = consQuality;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getTextStr() {
      return this.textStr;
   }

   public void setTextStr(String textStr) {
      this.textStr = textStr;
   }

   public String getMrType() {
      return this.mrType;
   }

   public void setMrType(String mrType) {
      this.mrType = mrType;
   }

   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(String indexName) {
      this.indexName = indexName;
   }

   public Long getTempId() {
      return this.tempId;
   }

   public void setTempId(Long tempId) {
      this.tempId = tempId;
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

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public String getAssignDocFlag() {
      return this.assignDocFlag;
   }

   public void setAssignDocFlag(String assignDocFlag) {
      this.assignDocFlag = assignDocFlag;
   }
}
