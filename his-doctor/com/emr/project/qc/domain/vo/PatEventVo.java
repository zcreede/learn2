package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.PatEvent;
import java.util.List;

public class PatEventVo extends PatEvent {
   private Integer afterBeginHour;
   private List mrTypeList;
   private Long indexId;
   private String deptCd;
   private String deptName;
   private String resDocCd;
   private String resDocName;
   private Integer afterOutTimeDayNum;
   private List eventCodeList;
   private List ruleIdList;
   private String signFlag;
   private String idcard;

   public String getIdcard() {
      return this.idcard;
   }

   public void setIdcard(String idcard) {
      this.idcard = idcard;
   }

   public String getSignFlag() {
      return this.signFlag;
   }

   public void setSignFlag(String signFlag) {
      this.signFlag = signFlag;
   }

   public PatEventVo() {
   }

   public PatEventVo(String orgCd, String patientId) {
      super.setOrgCd(orgCd);
      super.setPatientId(patientId);
   }

   public List getRuleIdList() {
      return this.ruleIdList;
   }

   public void setRuleIdList(List ruleIdList) {
      this.ruleIdList = ruleIdList;
   }

   public List getEventCodeList() {
      return this.eventCodeList;
   }

   public void setEventCodeList(List eventCodeList) {
      this.eventCodeList = eventCodeList;
   }

   public Integer getAfterOutTimeDayNum() {
      return this.afterOutTimeDayNum;
   }

   public void setAfterOutTimeDayNum(Integer afterOutTimeDayNum) {
      this.afterOutTimeDayNum = afterOutTimeDayNum;
   }

   public String getDeptCd() {
      return this.deptCd;
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

   public Long getIndexId() {
      return this.indexId;
   }

   public void setIndexId(Long indexId) {
      this.indexId = indexId;
   }

   public List getMrTypeList() {
      return this.mrTypeList;
   }

   public void setMrTypeList(List mrTypeList) {
      this.mrTypeList = mrTypeList;
   }

   public Integer getAfterBeginHour() {
      return this.afterBeginHour;
   }

   public void setAfterBeginHour(Integer afterBeginHour) {
      this.afterBeginHour = afterBeginHour;
   }
}
