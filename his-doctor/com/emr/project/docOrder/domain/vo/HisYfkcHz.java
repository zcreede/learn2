package com.emr.project.docOrder.domain.vo;

import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HisYfkcHz extends BaseEntity {
   private String pybm;
   private String ypbm;
   private String ypmc;
   private String gg;
   private String dw;
   private String jx;
   private BigDecimal lsj;
   private BigDecimal sl;
   private BigDecimal djsl;
   private String sccjbm;
   private String gysbm;
   private String yfbm;
   private String lb;
   private String jxzl;
   private String ykbm;
   private String sfyb;
   private String hxmc;
   private String fldm;
   private String ybbm;
   private String kcbh;
   private BigDecimal jj;
   private Integer zdkc;
   private BigDecimal ypid;
   private BigDecimal pbkc;
   private String kchw;
   private BigDecimal lsj_yk;
   private String ph;
   private Date yxq;

   public HisYfkcHz() {
   }

   public HisYfkcHz(String ypbm, String yfbm, String kcbh) {
      this.ypbm = ypbm;
      this.yfbm = yfbm;
      this.kcbh = kcbh;
   }

   public HisYfkcHz(String ypbm, String yfbm, String kcbh, BigDecimal sl) {
      this.ypbm = ypbm;
      this.yfbm = yfbm;
      this.kcbh = kcbh;
      this.sl = sl;
   }

   public HisYfkcHz(String ypbm, String yfbm, String kcbh, BigDecimal sl, BigDecimal djsl) {
      this.ypbm = ypbm;
      this.yfbm = yfbm;
      this.kcbh = kcbh;
      this.sl = sl;
      this.djsl = djsl;
   }

   public String getPybm() {
      return this.pybm;
   }

   public void setPybm(String pybm) {
      this.pybm = pybm;
   }

   public String getYpbm() {
      return this.ypbm;
   }

   public void setYpbm(String ypbm) {
      this.ypbm = ypbm;
   }

   public String getYpmc() {
      return this.ypmc;
   }

   public void setYpmc(String ypmc) {
      this.ypmc = ypmc;
   }

   public String getGg() {
      return this.gg;
   }

   public void setGg(String gg) {
      this.gg = gg;
   }

   public String getDw() {
      return this.dw;
   }

   public void setDw(String dw) {
      this.dw = dw;
   }

   public String getJx() {
      return this.jx;
   }

   public void setJx(String jx) {
      this.jx = jx;
   }

   public BigDecimal getLsj() {
      return this.lsj;
   }

   public void setLsj(BigDecimal lsj) {
      this.lsj = lsj;
   }

   public BigDecimal getSl() {
      return this.sl;
   }

   public void setSl(BigDecimal sl) {
      this.sl = sl;
   }

   public BigDecimal getDjsl() {
      return this.djsl;
   }

   public void setDjsl(BigDecimal djsl) {
      this.djsl = djsl;
   }

   public String getSccjbm() {
      return this.sccjbm;
   }

   public void setSccjbm(String sccjbm) {
      this.sccjbm = sccjbm;
   }

   public String getGysbm() {
      return this.gysbm;
   }

   public void setGysbm(String gysbm) {
      this.gysbm = gysbm;
   }

   public String getYfbm() {
      return this.yfbm;
   }

   public void setYfbm(String yfbm) {
      this.yfbm = yfbm;
   }

   public String getLb() {
      return this.lb;
   }

   public void setLb(String lb) {
      this.lb = lb;
   }

   public String getJxzl() {
      return this.jxzl;
   }

   public void setJxzl(String jxzl) {
      this.jxzl = jxzl;
   }

   public String getYkbm() {
      return this.ykbm;
   }

   public void setYkbm(String ykbm) {
      this.ykbm = ykbm;
   }

   public String getSfyb() {
      return this.sfyb;
   }

   public void setSfyb(String sfyb) {
      this.sfyb = sfyb;
   }

   public String getHxmc() {
      return this.hxmc;
   }

   public void setHxmc(String hxmc) {
      this.hxmc = hxmc;
   }

   public String getFldm() {
      return this.fldm;
   }

   public void setFldm(String fldm) {
      this.fldm = fldm;
   }

   public String getYbbm() {
      return this.ybbm;
   }

   public void setYbbm(String ybbm) {
      this.ybbm = ybbm;
   }

   public String getKcbh() {
      return this.kcbh;
   }

   public void setKcbh(String kcbh) {
      this.kcbh = kcbh;
   }

   public BigDecimal getJj() {
      return this.jj;
   }

   public void setJj(BigDecimal jj) {
      this.jj = jj;
   }

   public Integer getZdkc() {
      return this.zdkc;
   }

   public void setZdkc(Integer zdkc) {
      this.zdkc = zdkc;
   }

   public BigDecimal getYpid() {
      return this.ypid;
   }

   public void setYpid(BigDecimal ypid) {
      this.ypid = ypid;
   }

   public BigDecimal getPbkc() {
      return this.pbkc;
   }

   public void setPbkc(BigDecimal pbkc) {
      this.pbkc = pbkc;
   }

   public String getKchw() {
      return this.kchw;
   }

   public void setKchw(String kchw) {
      this.kchw = kchw;
   }

   public BigDecimal getLsj_yk() {
      return this.lsj_yk;
   }

   public void setLsj_yk(BigDecimal lsj_yk) {
      this.lsj_yk = lsj_yk;
   }

   public String getPh() {
      return this.ph;
   }

   public void setPh(String ph) {
      this.ph = ph;
   }

   public Date getYxq() {
      return this.yxq;
   }

   public void setYxq(Date yxq) {
      this.yxq = yxq;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("pybm", this.getPybm()).append("ypbm", this.getYpbm()).append("ypmc", this.getYpmc()).append("gg", this.getGg()).append("dw", this.getDw()).append("jx", this.getJx()).append("lsj", this.getLsj()).append("sl", this.getSl()).append("djsl", this.getDjsl()).append("sccjbm", this.getSccjbm()).append("gysbm", this.getGysbm()).append("yfbm", this.getYfbm()).append("lb", this.getLb()).append("jxzl", this.getJxzl()).append("ykbm", this.getYkbm()).append("sfyb", this.getSfyb()).append("hxmc", this.getHxmc()).append("fldm", this.getFldm()).append("ybbm", this.getYbbm()).append("kcbh", this.getKcbh()).append("jj", this.getJj()).append("zdkc", this.getZdkc()).append("ypid", this.getYpid()).append("pbkc", this.getPbkc()).append("kchw", this.getKchw()).append("plsj_ykh", this.getLsj_yk()).append("ph", this.getPh()).append("yxq", this.getYxq()).toString();
   }
}
