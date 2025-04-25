package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RuleAnalysisDataResp implements Serializable {
   private String id;
   private String gzid;
   private String gzmc;
   private String wgnr;
   private BigDecimal wgje;
   private String zyh;
   private String yzcd;
   private String wgxwfl;
   private Date shsj;
   private String msgid;

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getGzid() {
      return this.gzid;
   }

   public void setGzid(String gzid) {
      this.gzid = gzid;
   }

   public String getGzmc() {
      return this.gzmc;
   }

   public void setGzmc(String gzmc) {
      this.gzmc = gzmc;
   }

   public String getWgnr() {
      return this.wgnr;
   }

   public void setWgnr(String wgnr) {
      this.wgnr = wgnr;
   }

   public BigDecimal getWgje() {
      return this.wgje;
   }

   public void setWgje(BigDecimal wgje) {
      this.wgje = wgje;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getYzcd() {
      return this.yzcd;
   }

   public void setYzcd(String yzcd) {
      this.yzcd = yzcd;
   }

   public String getWgxwfl() {
      return this.wgxwfl;
   }

   public void setWgxwfl(String wgxwfl) {
      this.wgxwfl = wgxwfl;
   }

   public Date getShsj() {
      return this.shsj;
   }

   public void setShsj(Date shsj) {
      this.shsj = shsj;
   }

   public String getMsgid() {
      return this.msgid;
   }

   public void setMsgid(String msgid) {
      this.msgid = msgid;
   }
}
