package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcAgiRule;
import java.util.Date;
import java.util.List;

public class QcAgiRuleVo extends QcAgiRule {
   private String evenName;
   private String defeLevelName;
   private List emrTypeCodeList;
   private Long eventId;
   private Date beginTime;
   private Date endTime;
   private String emrClassCode;

   public String getEmrClassCode() {
      return this.emrClassCode;
   }

   public void setEmrClassCode(String emrClassCode) {
      this.emrClassCode = emrClassCode;
   }

   public Long getEventId() {
      return this.eventId;
   }

   public void setEventId(Long eventId) {
      this.eventId = eventId;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public List getEmrTypeCodeList() {
      return this.emrTypeCodeList;
   }

   public void setEmrTypeCodeList(List emrTypeCodeList) {
      this.emrTypeCodeList = emrTypeCodeList;
   }

   public String getDefeLevelName() {
      return this.defeLevelName;
   }

   public void setDefeLevelName(String defeLevelName) {
      this.defeLevelName = defeLevelName;
   }

   public String getEvenName() {
      return this.evenName;
   }

   public void setEvenName(String evenName) {
      this.evenName = evenName;
   }
}
