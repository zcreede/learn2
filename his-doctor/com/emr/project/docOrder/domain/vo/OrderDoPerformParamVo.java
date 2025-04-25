package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderDoPerformParamVo {
   private List performList;
   private AllergyRecordDTO allergyRecord;
   private List orderSignCommitVoList;

   public List getOrderSignCommitVoList() {
      return this.orderSignCommitVoList;
   }

   public void setOrderSignCommitVoList(List orderSignCommitVoList) {
      this.orderSignCommitVoList = orderSignCommitVoList;
   }

   public List getPerformList() {
      return this.performList;
   }

   public void setPerformList(List performList) {
      this.performList = performList;
   }

   public AllergyRecordDTO getAllergyRecord() {
      return this.allergyRecord;
   }

   public void setAllergyRecord(AllergyRecordDTO allergyRecord) {
      this.allergyRecord = allergyRecord;
   }
}
