package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class PhysignDayVo {
   private String sqlStr;
   private String inpNo;
   private Long dayid;
   private Long baseid;
   @JsonFormat(
      timezone = "GMT+8",
      pattern = "yyyy-MM-dd HH:mm"
   )
   private Date curDay;
   private String zd;
   private String daych;
   private String xy1;
   private String xy2;
   private String xy3;
   private String xy4;
   private String ryfs;
   private String hds;
   private String shengao;
   private String tizhong;
   private String dbcs;
   private String chsl;
   private String yll;
   private String xbcs;
   private String xbl;
   private String rshl;
   private String gmyw;
   private String bz;
   private String bzbt1;
   private String bznr1;
   private String bzbt2;
   private String bznr2;
   private String mur;
   private String bz1;
   private String bz2;
   private String bz3;
   private String bz4;
   private String shuil;
   private String zyh;
   private String bah;
   private String szyy;
   private String hzxm;
   private String nl;
   private String ch;
   private String xb;
   private String bqmc;
   private String ryrq;
   private Integer rycs;
   private String zzysxm;
   private String hldj;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date rksj;
   private String ryzd;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date ssrq1;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date ssrq2;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date ssrq3;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date ssrq4;
   private String kjm;
   private String kjmjz;
   private String yqym;
   private String yqymjz;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date fmsj;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date csrq;

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Long getDayid() {
      return this.dayid;
   }

   public void setDayid(Long dayid) {
      this.dayid = dayid;
   }

   public Long getBaseid() {
      return this.baseid;
   }

   public void setBaseid(Long baseid) {
      this.baseid = baseid;
   }

   public Date getCurDay() {
      return this.curDay;
   }

   public void setCurDay(Date curDay) {
      this.curDay = curDay;
   }

   public String getZd() {
      return this.zd;
   }

   public void setZd(String zd) {
      this.zd = zd;
   }

   public String getDaych() {
      return this.daych;
   }

   public void setDaych(String daych) {
      this.daych = daych;
   }

   public String getXy1() {
      return this.xy1;
   }

   public void setXy1(String xy1) {
      this.xy1 = xy1;
   }

   public String getXy2() {
      return this.xy2;
   }

   public void setXy2(String xy2) {
      this.xy2 = xy2;
   }

   public String getXy3() {
      return this.xy3;
   }

   public void setXy3(String xy3) {
      this.xy3 = xy3;
   }

   public String getXy4() {
      return this.xy4;
   }

   public void setXy4(String xy4) {
      this.xy4 = xy4;
   }

   public String getRyfs() {
      return this.ryfs;
   }

   public void setRyfs(String ryfs) {
      this.ryfs = ryfs;
   }

   public String getHds() {
      return this.hds;
   }

   public void setHds(String hds) {
      this.hds = hds;
   }

   public String getShengao() {
      return this.shengao;
   }

   public void setShengao(String shengao) {
      this.shengao = shengao;
   }

   public String getTizhong() {
      return this.tizhong;
   }

   public void setTizhong(String tizhong) {
      this.tizhong = tizhong;
   }

   public String getDbcs() {
      return this.dbcs;
   }

   public void setDbcs(String dbcs) {
      this.dbcs = dbcs;
   }

   public String getChsl() {
      return this.chsl;
   }

   public void setChsl(String chsl) {
      this.chsl = chsl;
   }

   public String getYll() {
      return this.yll;
   }

   public void setYll(String yll) {
      this.yll = yll;
   }

   public String getXbcs() {
      return this.xbcs;
   }

   public void setXbcs(String xbcs) {
      this.xbcs = xbcs;
   }

   public String getXbl() {
      return this.xbl;
   }

   public void setXbl(String xbl) {
      this.xbl = xbl;
   }

   public String getRshl() {
      return this.rshl;
   }

   public void setRshl(String rshl) {
      this.rshl = rshl;
   }

   public String getGmyw() {
      return this.gmyw;
   }

   public void setGmyw(String gmyw) {
      this.gmyw = gmyw;
   }

   public String getBz() {
      return this.bz;
   }

   public void setBz(String bz) {
      this.bz = bz;
   }

   public String getBzbt1() {
      return this.bzbt1;
   }

   public void setBzbt1(String bzbt1) {
      this.bzbt1 = bzbt1;
   }

   public String getBznr1() {
      return this.bznr1;
   }

   public void setBznr1(String bznr1) {
      this.bznr1 = bznr1;
   }

   public String getBzbt2() {
      return this.bzbt2;
   }

   public void setBzbt2(String bzbt2) {
      this.bzbt2 = bzbt2;
   }

   public String getBznr2() {
      return this.bznr2;
   }

   public void setBznr2(String bznr2) {
      this.bznr2 = bznr2;
   }

   public String getMur() {
      return this.mur;
   }

   public void setMur(String mur) {
      this.mur = mur;
   }

   public String getBz1() {
      return this.bz1;
   }

   public void setBz1(String bz1) {
      this.bz1 = bz1;
   }

   public String getBz2() {
      return this.bz2;
   }

   public void setBz2(String bz2) {
      this.bz2 = bz2;
   }

   public String getBz3() {
      return this.bz3;
   }

   public void setBz3(String bz3) {
      this.bz3 = bz3;
   }

   public String getBz4() {
      return this.bz4;
   }

   public void setBz4(String bz4) {
      this.bz4 = bz4;
   }

   public String getShuil() {
      return this.shuil;
   }

   public void setShuil(String shuil) {
      this.shuil = shuil;
   }

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getBah() {
      return this.bah;
   }

   public void setBah(String bah) {
      this.bah = bah;
   }

   public String getSzyy() {
      return this.szyy;
   }

   public void setSzyy(String szyy) {
      this.szyy = szyy;
   }

   public String getHzxm() {
      return this.hzxm;
   }

   public void setHzxm(String hzxm) {
      this.hzxm = hzxm;
   }

   public String getNl() {
      return this.nl;
   }

   public void setNl(String nl) {
      this.nl = nl;
   }

   public String getCh() {
      return this.ch;
   }

   public void setCh(String ch) {
      this.ch = ch;
   }

   public String getXb() {
      return this.xb;
   }

   public void setXb(String xb) {
      this.xb = xb;
   }

   public String getBqmc() {
      return this.bqmc;
   }

   public void setBqmc(String bqmc) {
      this.bqmc = bqmc;
   }

   public String getRyrq() {
      return this.ryrq;
   }

   public void setRyrq(String ryrq) {
      this.ryrq = ryrq;
   }

   public Integer getRycs() {
      return this.rycs;
   }

   public void setRycs(Integer rycs) {
      this.rycs = rycs;
   }

   public String getZzysxm() {
      return this.zzysxm;
   }

   public void setZzysxm(String zzysxm) {
      this.zzysxm = zzysxm;
   }

   public String getHldj() {
      return this.hldj;
   }

   public void setHldj(String hldj) {
      this.hldj = hldj;
   }

   public Date getRksj() {
      return this.rksj;
   }

   public void setRksj(Date rksj) {
      this.rksj = rksj;
   }

   public String getRyzd() {
      return this.ryzd;
   }

   public void setRyzd(String ryzd) {
      this.ryzd = ryzd;
   }

   public Date getSsrq1() {
      return this.ssrq1;
   }

   public void setSsrq1(Date ssrq1) {
      this.ssrq1 = ssrq1;
   }

   public Date getSsrq2() {
      return this.ssrq2;
   }

   public void setSsrq2(Date ssrq2) {
      this.ssrq2 = ssrq2;
   }

   public Date getSsrq3() {
      return this.ssrq3;
   }

   public void setSsrq3(Date ssrq3) {
      this.ssrq3 = ssrq3;
   }

   public Date getSsrq4() {
      return this.ssrq4;
   }

   public void setSsrq4(Date ssrq4) {
      this.ssrq4 = ssrq4;
   }

   public String getKjm() {
      return this.kjm;
   }

   public void setKjm(String kjm) {
      this.kjm = kjm;
   }

   public String getKjmjz() {
      return this.kjmjz;
   }

   public void setKjmjz(String kjmjz) {
      this.kjmjz = kjmjz;
   }

   public String getYqym() {
      return this.yqym;
   }

   public void setYqym(String yqym) {
      this.yqym = yqym;
   }

   public String getYqymjz() {
      return this.yqymjz;
   }

   public void setYqymjz(String yqymjz) {
      this.yqymjz = yqymjz;
   }

   public Date getFmsj() {
      return this.fmsj;
   }

   public void setFmsj(Date fmsj) {
      this.fmsj = fmsj;
   }

   public Date getCsrq() {
      return this.csrq;
   }

   public void setCsrq(Date csrq) {
      this.csrq = csrq;
   }
}
