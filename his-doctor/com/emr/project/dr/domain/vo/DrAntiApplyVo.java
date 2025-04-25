package com.emr.project.dr.domain.vo;

import com.emr.project.dr.domain.DrAntiApply;
import java.util.Date;
import java.util.List;

public class DrAntiApplyVo extends DrAntiApply {
   private Date dateStr;
   private Date dateEnd;
   private String docCd;
   private String sqlStr;
   private String drugClassCode;
   private String drugClassName;
   private String applyFlag;
   private String useEnabled = "1";
   private Long[] ids;
   private List stateList;
   private Date auditBeginTime;
   private Date auditEndTime;
   private String drugDose;
   private String inputstrphtc;
   private String purposeAntimicrobialUseName;
   private List drugCodeList;

   public Date getDateStr() {
      return this.dateStr;
   }

   public void setDateStr(Date dateStr) {
      this.dateStr = dateStr;
   }

   public Date getDateEnd() {
      return this.dateEnd;
   }

   public void setDateEnd(Date dateEnd) {
      this.dateEnd = dateEnd;
   }

   public List getDrugCodeList() {
      return this.drugCodeList;
   }

   public void setDrugCodeList(List drugCodeList) {
      this.drugCodeList = drugCodeList;
   }

   public String getPurposeAntimicrobialUseName() {
      return this.purposeAntimicrobialUseName;
   }

   public void setPurposeAntimicrobialUseName(String purposeAntimicrobialUseName) {
      this.purposeAntimicrobialUseName = purposeAntimicrobialUseName;
   }

   public String getDrugDose() {
      return this.drugDose;
   }

   public void setDrugDose(String drugDose) {
      this.drugDose = drugDose;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public Date getAuditBeginTime() {
      return this.auditBeginTime;
   }

   public void setAuditBeginTime(Date auditBeginTime) {
      this.auditBeginTime = auditBeginTime;
   }

   public Date getAuditEndTime() {
      return this.auditEndTime;
   }

   public void setAuditEndTime(Date auditEndTime) {
      this.auditEndTime = auditEndTime;
   }

   public List getStateList() {
      return this.stateList;
   }

   public void setStateList(List stateList) {
      this.stateList = stateList;
   }

   public Long[] getIds() {
      return this.ids;
   }

   public void setIds(Long[] ids) {
      this.ids = ids;
   }

   public String getApplyFlag() {
      return this.applyFlag;
   }

   public void setApplyFlag(String applyFlag) {
      this.applyFlag = applyFlag;
   }

   public String getUseEnabled() {
      return this.useEnabled;
   }

   public void setUseEnabled(String useEnabled) {
      this.useEnabled = useEnabled;
   }

   public String getDrugClassName() {
      return this.drugClassName;
   }

   public void setDrugClassName(String drugClassName) {
      this.drugClassName = drugClassName;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }
}
