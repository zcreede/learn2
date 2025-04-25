package com.emr.project.docOrder.domain;

import java.util.Date;

public class TakeDrugBatch {
   private Long id;
   private String depCode;
   private Date takeDrugDate;
   private Integer takeDrugBatchNum;
   private String takeDrugDateStr;

   public String getTakeDrugDateStr() {
      return this.takeDrugDateStr;
   }

   public void setTakeDrugDateStr(String takeDrugDateStr) {
      this.takeDrugDateStr = takeDrugDateStr;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getDepCode() {
      return this.depCode;
   }

   public void setDepCode(String depCode) {
      this.depCode = depCode;
   }

   public Date getTakeDrugDate() {
      return this.takeDrugDate;
   }

   public void setTakeDrugDate(Date takeDrugDate) {
      this.takeDrugDate = takeDrugDate;
   }

   public Integer getTakeDrugBatchNum() {
      return this.takeDrugBatchNum;
   }

   public void setTakeDrugBatchNum(Integer takeDrugBatchNum) {
      this.takeDrugBatchNum = takeDrugBatchNum;
   }
}
