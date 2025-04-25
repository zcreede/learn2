package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderApplyFormTestVo {
   private String orderNo;
   private String applyFormNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date printsj;
   private String printOper;
   private String printOperName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date smsj;
   private String smOper;
   private String smOperName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date scsj;
   private String scOper;
   private String scOperName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date machineConfirmTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date issTime;
   private String issCd;
   private String issName;

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public Date getPrintsj() {
      return this.printsj;
   }

   public void setPrintsj(Date printsj) {
      this.printsj = printsj;
   }

   public String getPrintOper() {
      return this.printOper;
   }

   public void setPrintOper(String printOper) {
      this.printOper = printOper;
   }

   public String getPrintOperName() {
      return this.printOperName;
   }

   public void setPrintOperName(String printOperName) {
      this.printOperName = printOperName;
   }

   public Date getSmsj() {
      return this.smsj;
   }

   public void setSmsj(Date smsj) {
      this.smsj = smsj;
   }

   public String getSmOper() {
      return this.smOper;
   }

   public void setSmOper(String smOper) {
      this.smOper = smOper;
   }

   public String getSmOperName() {
      return this.smOperName;
   }

   public void setSmOperName(String smOperName) {
      this.smOperName = smOperName;
   }

   public Date getScsj() {
      return this.scsj;
   }

   public void setScsj(Date scsj) {
      this.scsj = scsj;
   }

   public String getScOper() {
      return this.scOper;
   }

   public void setScOper(String scOper) {
      this.scOper = scOper;
   }

   public String getScOperName() {
      return this.scOperName;
   }

   public void setScOperName(String scOperName) {
      this.scOperName = scOperName;
   }

   public Date getMachineConfirmTime() {
      return this.machineConfirmTime;
   }

   public void setMachineConfirmTime(Date machineConfirmTime) {
      this.machineConfirmTime = machineConfirmTime;
   }

   public Date getIssTime() {
      return this.issTime;
   }

   public void setIssTime(Date issTime) {
      this.issTime = issTime;
   }

   public String getIssCd() {
      return this.issCd;
   }

   public void setIssCd(String issCd) {
      this.issCd = issCd;
   }

   public String getIssName() {
      return this.issName;
   }

   public void setIssName(String issName) {
      this.issName = issName;
   }
}
