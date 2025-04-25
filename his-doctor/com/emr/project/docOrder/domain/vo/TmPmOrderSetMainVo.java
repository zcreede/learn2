package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import java.util.List;

public class TmPmOrderSetMainVo extends TmPmOrderSetMain {
   private List detailIdList;
   private String patientId;
   private List detailList;
   private String[] itemClassCdList;
   private List itemCdList;
   private String copySetCd;
   private Boolean collectFlag;
   private List orderSaveList;

   public List getOrderSaveList() {
      return this.orderSaveList;
   }

   public void setOrderSaveList(List orderSaveList) {
      this.orderSaveList = orderSaveList;
   }

   public Boolean getCollectFlag() {
      return this.collectFlag;
   }

   public void setCollectFlag(Boolean collectFlag) {
      this.collectFlag = collectFlag;
   }

   public String getCopySetCd() {
      return this.copySetCd;
   }

   public void setCopySetCd(String copySetCd) {
      this.copySetCd = copySetCd;
   }

   public List getItemCdList() {
      return this.itemCdList;
   }

   public void setItemCdList(List itemCdList) {
      this.itemCdList = itemCdList;
   }

   public String[] getItemClassCdList() {
      return this.itemClassCdList;
   }

   public void setItemClassCdList(String[] itemClassCdList) {
      this.itemClassCdList = itemClassCdList;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public List getDetailIdList() {
      return this.detailIdList;
   }

   public void setDetailIdList(List detailIdList) {
      this.detailIdList = detailIdList;
   }
}
