package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class SkinTestResuleVo {
   private String chargeNo;
   private String chargeName;
   private String skinTestResults;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date skinTestResultsTime;

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public Date getSkinTestResultsTime() {
      return this.skinTestResultsTime;
   }

   public void setSkinTestResultsTime(Date skinTestResultsTime) {
      this.skinTestResultsTime = skinTestResultsTime;
   }
}
