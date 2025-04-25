package com.emr.project.esSearch.domain.req;

public class DrugStockSearchReq {
   private String deptCode;
   private String drugCd;

   public DrugStockSearchReq() {
   }

   public DrugStockSearchReq(String deptCode, String drugCd) {
      this.deptCode = deptCode;
      this.drugCd = drugCd;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getDrugCd() {
      return this.drugCd;
   }

   public void setDrugCd(String drugCd) {
      this.drugCd = drugCd;
   }
}
