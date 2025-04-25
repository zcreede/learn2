package com.emr.project.esSearch.domain.req;

public class DrugAndStockSearchReq {
   private String drugCd;
   private String stockNo;

   public String getDrugCd() {
      return this.drugCd;
   }

   public void setDrugCd(String drugCd) {
      this.drugCd = drugCd;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }
}
