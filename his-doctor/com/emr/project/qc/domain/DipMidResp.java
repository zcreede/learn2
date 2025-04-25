package com.emr.project.qc.domain;

import java.io.Serializable;

public class DipMidResp implements Serializable {
   private String bah;
   private String zyh;
   private String xm;

   public String getBah() {
      return this.bah;
   }

   public void setBah(String bah) {
      this.bah = bah;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getXm() {
      return this.xm;
   }

   public void setXm(String xm) {
      this.xm = xm;
   }
}
