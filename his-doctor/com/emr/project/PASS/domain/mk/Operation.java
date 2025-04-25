package com.emr.project.PASS.domain.mk;

public class Operation {
   private String pcIndex;
   private String pcOprCode;
   private String pcOprName;
   private String pcIncisionType;
   private String pcOprStartDateTime;
   private String pcOprEndDateTime;

   public String getPcIndex() {
      return this.pcIndex;
   }

   public void setPcIndex(String pcIndex) {
      this.pcIndex = pcIndex;
   }

   public String getPcOprCode() {
      return this.pcOprCode;
   }

   public void setPcOprCode(String pcOprCode) {
      this.pcOprCode = pcOprCode;
   }

   public String getPcOprName() {
      return this.pcOprName;
   }

   public void setPcOprName(String pcOprName) {
      this.pcOprName = pcOprName;
   }

   public String getPcIncisionType() {
      return this.pcIncisionType;
   }

   public void setPcIncisionType(String pcIncisionType) {
      this.pcIncisionType = pcIncisionType;
   }

   public String getPcOprStartDateTime() {
      return this.pcOprStartDateTime;
   }

   public void setPcOprStartDateTime(String pcOprStartDateTime) {
      this.pcOprStartDateTime = pcOprStartDateTime;
   }

   public String getPcOprEndDateTime() {
      return this.pcOprEndDateTime;
   }

   public void setPcOprEndDateTime(String pcOprEndDateTime) {
      this.pcOprEndDateTime = pcOprEndDateTime;
   }
}
