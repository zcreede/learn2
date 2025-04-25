package com.emr.project.webservice.domain.vo;

public class DSRParam {
   private String icd;
   private String bh;
   private String id;
   private String type;

   public DSRParam() {
   }

   public DSRParam(String icd, String bh, String id) {
      this.icd = icd;
      this.bh = bh;
      this.id = id;
      this.type = "a";
   }

   public String getIcd() {
      return this.icd;
   }

   public void setIcd(String icd) {
      this.icd = icd;
   }

   public String getBh() {
      return this.bh;
   }

   public void setBh(String bh) {
      this.bh = bh;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
