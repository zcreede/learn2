package com.emr.project.operation.domain.resp;

public class QueryPriceYyResp {
   private String itemNo;
   private String itemName;
   private String unit;
   private String price;
   private String hosUpperName;
   private String itemNamePym;
   private String deptName;
   private String standardCode;

   public String getItemNo() {
      return this.itemNo;
   }

   public void setItemNo(String itemNo) {
      this.itemNo = itemNo;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
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

   public String getHosUpperName() {
      return this.hosUpperName;
   }

   public void setHosUpperName(String hosUpperName) {
      this.hosUpperName = hosUpperName;
   }

   public String getItemNamePym() {
      return this.itemNamePym;
   }

   public void setItemNamePym(String itemNamePym) {
      this.itemNamePym = itemNamePym;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }
}
