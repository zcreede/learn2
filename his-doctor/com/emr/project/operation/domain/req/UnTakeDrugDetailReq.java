package com.emr.project.operation.domain.req;

public class UnTakeDrugDetailReq {
   private String id;
   private String chargeName;
   private String standard;
   private String unit;
   private String price;
   private String total;
   private String orderDose;
   private String applyDose;
   private String applyAmount;
   private String orderNo;
   private String orderSortNumber;
   private String orderGroupSortNumber;
   private String performListNo;
   private String performListSortNumber;
   private String orderGroupNo;
   private String pharmacyNo;
   private String pharmacopoeiaNo;
   private String drugStockNo;
   private String deptName;
   private String deptCode;

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public String getPharmacyNo() {
      return this.pharmacyNo;
   }

   public void setPharmacyNo(String pharmacyNo) {
      this.pharmacyNo = pharmacyNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getPrice() {
      return this.price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getTotal() {
      return this.total;
   }

   public void setTotal(String total) {
      this.total = total;
   }

   public String getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(String orderDose) {
      this.orderDose = orderDose;
   }

   public String getApplyDose() {
      return this.applyDose;
   }

   public void setApplyDose(String applyDose) {
      this.applyDose = applyDose;
   }

   public String getApplyAmount() {
      return this.applyAmount;
   }

   public void setApplyAmount(String applyAmount) {
      this.applyAmount = applyAmount;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }
}
