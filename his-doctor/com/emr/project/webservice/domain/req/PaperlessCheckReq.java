package com.emr.project.webservice.domain.req;

public class PaperlessCheckReq {
   private String zyh;

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public PaperlessCheckReq() {
   }

   public PaperlessCheckReq(String zyh) {
      this.zyh = zyh;
   }
}
