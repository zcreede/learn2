package com.emr.project.docOrder.domain;

public class ApplyFormItemKey {
   private String applyFormNo;
   private String itemSortNumber;

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo == null ? null : applyFormNo.trim();
   }

   public String getItemSortNumber() {
      return this.itemSortNumber;
   }

   public void setItemSortNumber(String itemSortNumber) {
      this.itemSortNumber = itemSortNumber == null ? null : itemSortNumber.trim();
   }
}
