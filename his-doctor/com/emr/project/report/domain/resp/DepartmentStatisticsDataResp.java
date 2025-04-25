package com.emr.project.report.domain.resp;

import java.io.Serializable;

public class DepartmentStatisticsDataResp implements Serializable {
   private String firstCode;
   private String code;
   private String entryName;
   private String amount;

   public String getFirstCode() {
      return this.firstCode;
   }

   public void setFirstCode(String firstCode) {
      this.firstCode = firstCode;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getEntryName() {
      return this.entryName;
   }

   public void setEntryName(String entryName) {
      this.entryName = entryName;
   }

   public String getAmount() {
      return this.amount;
   }

   public void setAmount(String amount) {
      this.amount = amount;
   }
}
