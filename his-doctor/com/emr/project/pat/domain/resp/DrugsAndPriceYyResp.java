package com.emr.project.pat.domain.resp;

import java.io.Serializable;

public class DrugsAndPriceYyResp implements Serializable {
   private String ypmc;
   private String ypbm;
   private String gg;
   private String pybm;

   public String getYpmc() {
      return this.ypmc;
   }

   public void setYpmc(String ypmc) {
      this.ypmc = ypmc;
   }

   public String getYpbm() {
      return this.ypbm;
   }

   public void setYpbm(String ypbm) {
      this.ypbm = ypbm;
   }

   public String getGg() {
      return this.gg;
   }

   public void setGg(String gg) {
      this.gg = gg;
   }

   public String getPybm() {
      return this.pybm;
   }

   public void setPybm(String pybm) {
      this.pybm = pybm;
   }
}
