package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderSaveResVo {
   private String windowFlag;
   private List windowCodeList;
   private String saveStopTit;
   private List psychotropicDrugs;
   private List anaesthesiaDrugs;

   public List getPsychotropicDrugs() {
      return this.psychotropicDrugs;
   }

   public void setPsychotropicDrugs(List psychotropicDrugs) {
      this.psychotropicDrugs = psychotropicDrugs;
   }

   public List getAnaesthesiaDrugs() {
      return this.anaesthesiaDrugs;
   }

   public void setAnaesthesiaDrugs(List anaesthesiaDrugs) {
      this.anaesthesiaDrugs = anaesthesiaDrugs;
   }

   public String getWindowFlag() {
      return this.windowFlag;
   }

   public void setWindowFlag(String windowFlag) {
      this.windowFlag = windowFlag;
   }

   public List getWindowCodeList() {
      return this.windowCodeList;
   }

   public void setWindowCodeList(List windowCodeList) {
      this.windowCodeList = windowCodeList;
   }

   public String getSaveStopTit() {
      return this.saveStopTit;
   }

   public void setSaveStopTit(String saveStopTit) {
      this.saveStopTit = saveStopTit;
   }
}
