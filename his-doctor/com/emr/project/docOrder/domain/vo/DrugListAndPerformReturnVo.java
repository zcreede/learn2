package com.emr.project.docOrder.domain.vo;

import java.math.BigDecimal;

public class DrugListAndPerformReturnVo {
   private Long id;
   private String performListNo;
   private Integer performListSortNumber;
   private BigDecimal drugReturnDose;

   public DrugListAndPerformReturnVo() {
   }

   public DrugListAndPerformReturnVo(Long id, String performListNo, Integer performListSortNumber, BigDecimal drugReturnDose) {
      this.id = id;
      this.performListNo = performListNo;
      this.performListSortNumber = performListSortNumber;
      this.drugReturnDose = drugReturnDose;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public BigDecimal getDrugReturnDose() {
      return this.drugReturnDose;
   }

   public void setDrugReturnDose(BigDecimal drugReturnDose) {
      this.drugReturnDose = drugReturnDose;
   }
}
