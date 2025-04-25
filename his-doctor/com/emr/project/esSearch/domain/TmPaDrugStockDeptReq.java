package com.emr.project.esSearch.domain;

public class TmPaDrugStockDeptReq {
   private String orgCd;
   private String deptCode;
   private String stockNo;
   private Integer storeNumSl;
   private Integer validNumSl;
   private String freezeSerial;
   private String freezeId;

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public Integer getStoreNumSl() {
      return this.storeNumSl;
   }

   public void setStoreNumSl(Integer storeNumSl) {
      this.storeNumSl = storeNumSl;
   }

   public Integer getValidNumSl() {
      return this.validNumSl;
   }

   public void setValidNumSl(Integer validNumSl) {
      this.validNumSl = validNumSl;
   }
}
