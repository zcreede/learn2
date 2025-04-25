package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.emr.domain.vo.IndexVo;
import java.util.Date;
import java.util.List;

public class TdCaOperationApplyVo extends TdCaOperationApply {
   private String emrTypeCode;
   private String emrTypeName;
   private Long mrFileId;
   private IndexVo mrFile;
   private List mrFileList;
   private List statusList;
   private String patDeptName;
   private String bedNo;
   private String inpNo;
   private String patTypeName;
   private String arcDocName;
   private String auditState;
   private Date planOperStartTime;
   private Date planOperEndTime;
   private Date operStartDate;
   private Date operEndDate;
   private String dateStatus;
   private List orderList;
   private Boolean submitFlag;
   private String emrClassCode;
   private List emrTypeList;
   private Long agiRuleId;
   private String agiRuleName;
   private String eventTypeName;
   private String opeType;
   private String opeTypeName;
   private String editPersonCd;
   private String operatingRoomFlag;

   public String getOperatingRoomFlag() {
      return this.operatingRoomFlag;
   }

   public void setOperatingRoomFlag(String operatingRoomFlag) {
      this.operatingRoomFlag = operatingRoomFlag;
   }

   public String getOpeTypeName() {
      return this.opeTypeName;
   }

   public String getEditPersonCd() {
      return this.editPersonCd;
   }

   public void setEditPersonCd(String editPersonCd) {
      this.editPersonCd = editPersonCd;
   }

   public void setOpeTypeName(String opeTypeName) {
      this.opeTypeName = opeTypeName;
   }

   public String getOpeType() {
      return this.opeType;
   }

   public void setOpeType(String opeType) {
      this.opeType = opeType;
   }

   public String getAgiRuleName() {
      return this.agiRuleName;
   }

   public void setAgiRuleName(String agiRuleName) {
      this.agiRuleName = agiRuleName;
   }

   public String getEventTypeName() {
      return this.eventTypeName;
   }

   public void setEventTypeName(String eventTypeName) {
      this.eventTypeName = eventTypeName;
   }

   public Long getAgiRuleId() {
      return this.agiRuleId;
   }

   public void setAgiRuleId(Long agiRuleId) {
      this.agiRuleId = agiRuleId;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getPatTypeName() {
      return this.patTypeName;
   }

   public void setPatTypeName(String patTypeName) {
      this.patTypeName = patTypeName;
   }

   public String getArcDocName() {
      return this.arcDocName;
   }

   public void setArcDocName(String arcDocName) {
      this.arcDocName = arcDocName;
   }

   public List getEmrTypeList() {
      return this.emrTypeList;
   }

   public void setEmrTypeList(List emrTypeList) {
      this.emrTypeList = emrTypeList;
   }

   public String getEmrClassCode() {
      return this.emrClassCode;
   }

   public void setEmrClassCode(String emrClassCode) {
      this.emrClassCode = emrClassCode;
   }

   public List getOrderList() {
      return this.orderList;
   }

   public void setOrderList(List orderList) {
      this.orderList = orderList;
   }

   public Boolean getSubmitFlag() {
      return this.submitFlag;
   }

   public void setSubmitFlag(Boolean submitFlag) {
      this.submitFlag = submitFlag;
   }

   public String getDateStatus() {
      return this.dateStatus;
   }

   public void setDateStatus(String dateStatus) {
      this.dateStatus = dateStatus;
   }

   public Date getOperStartDate() {
      return this.operStartDate;
   }

   public void setOperStartDate(Date operStartDate) {
      this.operStartDate = operStartDate;
   }

   public Date getOperEndDate() {
      return this.operEndDate;
   }

   public void setOperEndDate(Date operEndDate) {
      this.operEndDate = operEndDate;
   }

   public IndexVo getMrFile() {
      return this.mrFile;
   }

   public void setMrFile(IndexVo mrFile) {
      this.mrFile = mrFile;
   }

   public List getMrFileList() {
      return this.mrFileList;
   }

   public void setMrFileList(List mrFileList) {
      this.mrFileList = mrFileList;
   }

   public Date getPlanOperStartTime() {
      return this.planOperStartTime;
   }

   public void setPlanOperStartTime(Date planOperStartTime) {
      this.planOperStartTime = planOperStartTime;
   }

   public Date getPlanOperEndTime() {
      return this.planOperEndTime;
   }

   public void setPlanOperEndTime(Date planOperEndTime) {
      this.planOperEndTime = planOperEndTime;
   }

   public String getAuditState() {
      return this.auditState;
   }

   public void setAuditState(String auditState) {
      this.auditState = auditState;
   }

   public String getPatDeptName() {
      return this.patDeptName;
   }

   public void setPatDeptName(String patDeptName) {
      this.patDeptName = patDeptName;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public List getStatusList() {
      return this.statusList;
   }

   public void setStatusList(List statusList) {
      this.statusList = statusList;
   }

   public String getEmrTypeCode() {
      return this.emrTypeCode;
   }

   public void setEmrTypeCode(String emrTypeCode) {
      this.emrTypeCode = emrTypeCode;
   }

   public String getEmrTypeName() {
      return this.emrTypeName;
   }

   public void setEmrTypeName(String emrTypeName) {
      this.emrTypeName = emrTypeName;
   }

   public Long getMrFileId() {
      return this.mrFileId;
   }

   public void setMrFileId(Long mrFileId) {
      this.mrFileId = mrFileId;
   }
}
