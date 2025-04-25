package com.emr.project.dr.domain.vo;

import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.DrHandoverMain;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class DrHandoverMainVo extends DrHandoverMain {
   private String stateName;
   private String numCloumn;
   private List drHandoverDetailList;
   private String schemeNameStr;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date handoverBeginDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date handoverEndDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   private Date handoverDateTime;
   private List stateList;

   public Date getHandoverDateTime() {
      return this.handoverDateTime;
   }

   public void setHandoverDateTime(Date handoverDateTime) {
      this.handoverDateTime = handoverDateTime;
   }

   public List getStateList() {
      return this.stateList;
   }

   public void setStateList(List stateList) {
      this.stateList = stateList;
   }

   public Date getHandoverBeginDate() {
      return this.handoverBeginDate;
   }

   public void setHandoverBeginDate(Date handoverBeginDate) {
      this.handoverBeginDate = handoverBeginDate;
   }

   public Date getHandoverEndDate() {
      return this.handoverEndDate;
   }

   public void setHandoverEndDate(Date handoverEndDate) {
      this.handoverEndDate = handoverEndDate;
   }

   public String getSchemeNameStr() {
      return this.schemeNameStr;
   }

   public void setSchemeNameStr(String schemeNameStr) {
      this.schemeNameStr = schemeNameStr;
   }

   public String getStateName() {
      return this.stateName;
   }

   public void setStateName(String stateName) {
      this.stateName = stateName;
   }

   public List getDrHandoverDetailList() {
      return this.drHandoverDetailList;
   }

   public void setDrHandoverDetailList(List drHandoverDetailList) {
      this.drHandoverDetailList = drHandoverDetailList;
   }

   public String getNumCloumn() {
      return this.numCloumn;
   }

   public void setNumCloumn(String numCloumn) {
      this.numCloumn = numCloumn;
   }
}
