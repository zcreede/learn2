package com.emr.project.tmpa.domain;

import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VYyBzbmDrugClin extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String xmbh;
   private String bzbm;
   private String bzmc;
   private BigDecimal zfbl;
   private String xmdj;
   private String ybfl;
   private String scbz;
   private String dzbz;
   private String add3;
   private String sm;
   private String xmlb;
   private String version;
   private String vername;
   private String xmfl;

   public void setXmbh(String xmbh) {
      this.xmbh = xmbh;
   }

   public String getXmbh() {
      return this.xmbh;
   }

   public void setBzbm(String bzbm) {
      this.bzbm = bzbm;
   }

   public String getBzbm() {
      return this.bzbm;
   }

   public void setBzmc(String bzmc) {
      this.bzmc = bzmc;
   }

   public String getBzmc() {
      return this.bzmc;
   }

   public void setZfbl(BigDecimal zfbl) {
      this.zfbl = zfbl;
   }

   public BigDecimal getZfbl() {
      return this.zfbl;
   }

   public void setXmdj(String xmdj) {
      this.xmdj = xmdj;
   }

   public String getXmdj() {
      return this.xmdj;
   }

   public void setYbfl(String ybfl) {
      this.ybfl = ybfl;
   }

   public String getYbfl() {
      return this.ybfl;
   }

   public void setScbz(String scbz) {
      this.scbz = scbz;
   }

   public String getScbz() {
      return this.scbz;
   }

   public void setDzbz(String dzbz) {
      this.dzbz = dzbz;
   }

   public String getDzbz() {
      return this.dzbz;
   }

   public void setAdd3(String add3) {
      this.add3 = add3;
   }

   public String getAdd3() {
      return this.add3;
   }

   public void setSm(String sm) {
      this.sm = sm;
   }

   public String getSm() {
      return this.sm;
   }

   public void setXmlb(String xmlb) {
      this.xmlb = xmlb;
   }

   public String getXmlb() {
      return this.xmlb;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVername(String vername) {
      this.vername = vername;
   }

   public String getVername() {
      return this.vername;
   }

   public void setXmfl(String xmfl) {
      this.xmfl = xmfl;
   }

   public String getXmfl() {
      return this.xmfl;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("xmbh", this.getXmbh()).append("bzbm", this.getBzbm()).append("bzmc", this.getBzmc()).append("zfbl", this.getZfbl()).append("xmdj", this.getXmdj()).append("ybfl", this.getYbfl()).append("scbz", this.getScbz()).append("dzbz", this.getDzbz()).append("add3", this.getAdd3()).append("sm", this.getSm()).append("xmlb", this.getXmlb()).append("version", this.getVersion()).append("vername", this.getVername()).append("xmfl", this.getXmfl()).toString();
   }
}
