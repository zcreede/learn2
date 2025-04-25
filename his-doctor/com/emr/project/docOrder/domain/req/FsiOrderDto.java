package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class FsiOrderDto implements Serializable {
   private String rx_id;
   private String rxno;
   private String grpno;
   private String long_drord_flag;
   private String hilist_type;
   private String chrg_type;
   private String drord_bhvr;
   private String hilist_code;
   private String hilist_name;
   private String hilist_dosform;
   private String hilist_lv;
   private BigDecimal hilist_pric;
   private BigDecimal lv1_hosp_item_pric;
   private BigDecimal lv2_hosp_item_pric;
   private BigDecimal lv3_hosp_item_pric;
   private String hilist_memo;
   private String hosplist_code;
   private String hosplist_name;
   private String hosplist_dosform;
   private BigDecimal cnt;
   private BigDecimal pric;
   private BigDecimal sumamt;
   private BigDecimal ownpay_amt;
   private BigDecimal selfpay_amt;
   private String spec;
   private String spec_unt;
   private String drord_begn_date;
   private String drord_stop_date;
   private String drord_dept_codg;
   private String drord_dept_name;
   private String drord_dr_codg;
   private String drord_dr_name;
   private String drord_dr_profttl;
   private String curr_drord_flag;
   private String xmlx;
   private String sfxmlbFp;
   private BigDecimal ybzfbl;
   private String sfxmfl;

   public String getRx_id() {
      return this.rx_id;
   }

   public void setRx_id(String rx_id) {
      this.rx_id = rx_id;
   }

   public String getRxno() {
      return this.rxno;
   }

   public void setRxno(String rxno) {
      this.rxno = rxno;
   }

   public String getGrpno() {
      return this.grpno;
   }

   public void setGrpno(String grpno) {
      this.grpno = grpno;
   }

   public String getLong_drord_flag() {
      return this.long_drord_flag;
   }

   public void setLong_drord_flag(String long_drord_flag) {
      this.long_drord_flag = long_drord_flag;
   }

   public String getHilist_type() {
      return this.hilist_type;
   }

   public void setHilist_type(String hilist_type) {
      this.hilist_type = hilist_type;
   }

   public String getChrg_type() {
      return this.chrg_type;
   }

   public void setChrg_type(String chrg_type) {
      this.chrg_type = chrg_type;
   }

   public String getDrord_bhvr() {
      return this.drord_bhvr;
   }

   public void setDrord_bhvr(String drord_bhvr) {
      this.drord_bhvr = drord_bhvr;
   }

   public String getHilist_code() {
      return this.hilist_code;
   }

   public void setHilist_code(String hilist_code) {
      this.hilist_code = hilist_code;
   }

   public String getHilist_name() {
      return this.hilist_name;
   }

   public void setHilist_name(String hilist_name) {
      this.hilist_name = hilist_name;
   }

   public String getHilist_dosform() {
      return this.hilist_dosform;
   }

   public void setHilist_dosform(String hilist_dosform) {
      this.hilist_dosform = hilist_dosform;
   }

   public String getHilist_lv() {
      return this.hilist_lv;
   }

   public void setHilist_lv(String hilist_lv) {
      this.hilist_lv = hilist_lv;
   }

   public BigDecimal getHilist_pric() {
      return this.hilist_pric;
   }

   public void setHilist_pric(BigDecimal hilist_pric) {
      this.hilist_pric = hilist_pric;
   }

   public BigDecimal getLv1_hosp_item_pric() {
      return this.lv1_hosp_item_pric;
   }

   public void setLv1_hosp_item_pric(BigDecimal lv1_hosp_item_pric) {
      this.lv1_hosp_item_pric = lv1_hosp_item_pric;
   }

   public BigDecimal getLv2_hosp_item_pric() {
      return this.lv2_hosp_item_pric;
   }

   public void setLv2_hosp_item_pric(BigDecimal lv2_hosp_item_pric) {
      this.lv2_hosp_item_pric = lv2_hosp_item_pric;
   }

   public BigDecimal getLv3_hosp_item_pric() {
      return this.lv3_hosp_item_pric;
   }

   public void setLv3_hosp_item_pric(BigDecimal lv3_hosp_item_pric) {
      this.lv3_hosp_item_pric = lv3_hosp_item_pric;
   }

   public String getHilist_memo() {
      return this.hilist_memo;
   }

   public void setHilist_memo(String hilist_memo) {
      this.hilist_memo = hilist_memo;
   }

   public String getHosplist_code() {
      return this.hosplist_code;
   }

   public void setHosplist_code(String hosplist_code) {
      this.hosplist_code = hosplist_code;
   }

   public String getHosplist_name() {
      return this.hosplist_name;
   }

   public void setHosplist_name(String hosplist_name) {
      this.hosplist_name = hosplist_name;
   }

   public String getHosplist_dosform() {
      return this.hosplist_dosform;
   }

   public void setHosplist_dosform(String hosplist_dosform) {
      this.hosplist_dosform = hosplist_dosform;
   }

   public BigDecimal getCnt() {
      return this.cnt;
   }

   public void setCnt(BigDecimal cnt) {
      this.cnt = cnt;
   }

   public BigDecimal getPric() {
      return this.pric;
   }

   public void setPric(BigDecimal pric) {
      this.pric = pric;
   }

   public BigDecimal getSumamt() {
      return this.sumamt;
   }

   public void setSumamt(BigDecimal sumamt) {
      this.sumamt = sumamt;
   }

   public BigDecimal getOwnpay_amt() {
      return this.ownpay_amt;
   }

   public void setOwnpay_amt(BigDecimal ownpay_amt) {
      this.ownpay_amt = ownpay_amt;
   }

   public BigDecimal getSelfpay_amt() {
      return this.selfpay_amt;
   }

   public void setSelfpay_amt(BigDecimal selfpay_amt) {
      this.selfpay_amt = selfpay_amt;
   }

   public String getSpec() {
      return this.spec;
   }

   public void setSpec(String spec) {
      this.spec = spec;
   }

   public String getSpec_unt() {
      return this.spec_unt;
   }

   public void setSpec_unt(String spec_unt) {
      this.spec_unt = spec_unt;
   }

   public String getDrord_begn_date() {
      return this.drord_begn_date;
   }

   public void setDrord_begn_date(String drord_begn_date) {
      this.drord_begn_date = drord_begn_date;
   }

   public String getDrord_stop_date() {
      return this.drord_stop_date;
   }

   public void setDrord_stop_date(String drord_stop_date) {
      this.drord_stop_date = drord_stop_date;
   }

   public String getDrord_dept_codg() {
      return this.drord_dept_codg;
   }

   public void setDrord_dept_codg(String drord_dept_codg) {
      this.drord_dept_codg = drord_dept_codg;
   }

   public String getDrord_dept_name() {
      return this.drord_dept_name;
   }

   public void setDrord_dept_name(String drord_dept_name) {
      this.drord_dept_name = drord_dept_name;
   }

   public String getDrord_dr_codg() {
      return this.drord_dr_codg;
   }

   public void setDrord_dr_codg(String drord_dr_codg) {
      this.drord_dr_codg = drord_dr_codg;
   }

   public String getDrord_dr_name() {
      return this.drord_dr_name;
   }

   public void setDrord_dr_name(String drord_dr_name) {
      this.drord_dr_name = drord_dr_name;
   }

   public String getDrord_dr_profttl() {
      return this.drord_dr_profttl;
   }

   public void setDrord_dr_profttl(String drord_dr_profttl) {
      this.drord_dr_profttl = drord_dr_profttl;
   }

   public String getCurr_drord_flag() {
      return this.curr_drord_flag;
   }

   public void setCurr_drord_flag(String curr_drord_flag) {
      this.curr_drord_flag = curr_drord_flag;
   }

   public String getXmlx() {
      return this.xmlx;
   }

   public void setXmlx(String xmlx) {
      this.xmlx = xmlx;
   }

   public String getSfxmlbFp() {
      return this.sfxmlbFp;
   }

   public void setSfxmlbFp(String sfxmlbFp) {
      this.sfxmlbFp = sfxmlbFp;
   }

   public BigDecimal getYbzfbl() {
      return this.ybzfbl;
   }

   public void setYbzfbl(BigDecimal ybzfbl) {
      this.ybzfbl = ybzfbl;
   }

   public String getSfxmfl() {
      return this.sfxmfl;
   }

   public void setSfxmfl(String sfxmfl) {
      this.sfxmfl = sfxmfl;
   }
}
