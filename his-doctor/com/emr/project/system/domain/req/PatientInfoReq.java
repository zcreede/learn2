package com.emr.project.system.domain.req;

import java.io.Serializable;

public class PatientInfoReq implements Serializable {
   private String searchValue;
   private String itemCode;
   private String dateTime;

   public String getDateTime() {
      return this.dateTime;
   }

   public void setDateTime(String dateTime) {
      this.dateTime = dateTime;
   }

   public String getSearchValue() {
      return this.searchValue;
   }

   public void setSearchValue(String searchValue) {
      this.searchValue = searchValue;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }
}
