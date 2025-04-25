package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.SubfileIndex;
import java.util.Date;
import java.util.List;

public class SubfileIndexVo extends SubfileIndex {
   private String patientId;
   private String mrCon;
   private String mrConHeader;
   private String mrConFooter;
   private String xmlStr;
   private String msg;
   private Boolean mergeState;
   private List idList;
   private Long taskId;
   private String dataType;
   private String manMadeQcListFlag;
   private String base64Str;
   private Date startDate;
   private Date endDate;
   private String testExamResultId;
   private List setStructsTextIdList;
   private String dutyDocName;
   private String subFileXmlStr;
   private String headerBase64Str;
   private String footerBase64Str;
   private List signList;
   private String freeMoveType;
   private String signCertSn;
   private String tempName;
   private String showName;
   private String tempMajor;
   private String tempDisease;

   public String getSignCertSn() {
      return this.signCertSn;
   }

   public void setSignCertSn(String signCertSn) {
      this.signCertSn = signCertSn;
   }

   public String getFreeMoveType() {
      return this.freeMoveType;
   }

   public void setFreeMoveType(String freeMoveType) {
      this.freeMoveType = freeMoveType;
   }

   public List getSignList() {
      return this.signList;
   }

   public void setSignList(List signList) {
      this.signList = signList;
   }

   public String getHeaderBase64Str() {
      return this.headerBase64Str;
   }

   public void setHeaderBase64Str(String headerBase64Str) {
      this.headerBase64Str = headerBase64Str;
   }

   public String getFooterBase64Str() {
      return this.footerBase64Str;
   }

   public void setFooterBase64Str(String footerBase64Str) {
      this.footerBase64Str = footerBase64Str;
   }

   public String getSubFileXmlStr() {
      return this.subFileXmlStr;
   }

   public void setSubFileXmlStr(String subFileXmlStr) {
      this.subFileXmlStr = subFileXmlStr;
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

   public String getTestExamResultId() {
      return this.testExamResultId;
   }

   public void setTestExamResultId(String testExamResultId) {
      this.testExamResultId = testExamResultId;
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

   public String getBase64Str() {
      return this.base64Str;
   }

   public void setBase64Str(String base64Str) {
      this.base64Str = base64Str;
   }

   public String getManMadeQcListFlag() {
      return this.manMadeQcListFlag;
   }

   public void setManMadeQcListFlag(String manMadeQcListFlag) {
      this.manMadeQcListFlag = manMadeQcListFlag;
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

   public List getIdList() {
      return this.idList;
   }

   public void setIdList(List idList) {
      this.idList = idList;
   }

   public Boolean getMergeState() {
      return this.mergeState;
   }

   public void setMergeState(Boolean mergeState) {
      this.mergeState = mergeState;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getMrCon() {
      return this.mrCon;
   }

   public void setMrCon(String mrCon) {
      this.mrCon = mrCon;
   }

   public String getMrConHeader() {
      return this.mrConHeader;
   }

   public void setMrConHeader(String mrConHeader) {
      this.mrConHeader = mrConHeader;
   }

   public String getMrConFooter() {
      return this.mrConFooter;
   }

   public void setMrConFooter(String mrConFooter) {
      this.mrConFooter = mrConFooter;
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
}
