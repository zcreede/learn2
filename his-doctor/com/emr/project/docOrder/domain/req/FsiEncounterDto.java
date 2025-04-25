package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FsiEncounterDto implements Serializable {
   private String jzid;
   private String medins_id;
   private String medins_name;
   private String medins_admdvs;
   private String medins_type;
   private String medins_lv;
   private String wardarea_codg;
   private String wardno;
   private String bedno;
   private String adm_date;
   private String dscg_date;
   private String dscg_main_dise_codg;
   private String dscg_main_dise_name;
   private List fsi_diagnose_dtos;
   private String dr_codg;
   private String adm_dept_codg;
   private String adm_dept_name;
   private String dscg_dept_codg;
   private String dscg_dept_name;
   private String med_mdtrt_type;
   private String med_type;
   private List fsi_order_dtos;
   private String matn_stas;
   private BigDecimal medfee_sumamt;
   private BigDecimal ownpay_amt;
   private BigDecimal selfpay_amt;
   private BigDecimal acct_payamt;
   private BigDecimal ma_amt;
   private BigDecimal hifp_payamt;
   private BigDecimal setl_totlnum;
   private String insutype;
   private String reim_flag;
   private String out_setl_flag;
   private List fsi_operation_dtos;

   public String getJzid() {
      return this.jzid;
   }

   public void setJzid(String jzid) {
      this.jzid = jzid;
   }

   public String getMedins_id() {
      return this.medins_id;
   }

   public void setMedins_id(String medins_id) {
      this.medins_id = medins_id;
   }

   public String getMedins_name() {
      return this.medins_name;
   }

   public void setMedins_name(String medins_name) {
      this.medins_name = medins_name;
   }

   public String getMedins_admdvs() {
      return this.medins_admdvs;
   }

   public void setMedins_admdvs(String medins_admdvs) {
      this.medins_admdvs = medins_admdvs;
   }

   public String getMedins_type() {
      return this.medins_type;
   }

   public void setMedins_type(String medins_type) {
      this.medins_type = medins_type;
   }

   public String getMedins_lv() {
      return this.medins_lv;
   }

   public void setMedins_lv(String medins_lv) {
      this.medins_lv = medins_lv;
   }

   public String getWardarea_codg() {
      return this.wardarea_codg;
   }

   public void setWardarea_codg(String wardarea_codg) {
      this.wardarea_codg = wardarea_codg;
   }

   public String getWardno() {
      return this.wardno;
   }

   public void setWardno(String wardno) {
      this.wardno = wardno;
   }

   public String getBedno() {
      return this.bedno;
   }

   public void setBedno(String bedno) {
      this.bedno = bedno;
   }

   public String getAdm_date() {
      return this.adm_date;
   }

   public void setAdm_date(String adm_date) {
      this.adm_date = adm_date;
   }

   public String getDscg_date() {
      return this.dscg_date;
   }

   public void setDscg_date(String dscg_date) {
      this.dscg_date = dscg_date;
   }

   public String getDscg_main_dise_codg() {
      return this.dscg_main_dise_codg;
   }

   public void setDscg_main_dise_codg(String dscg_main_dise_codg) {
      this.dscg_main_dise_codg = dscg_main_dise_codg;
   }

   public String getDscg_main_dise_name() {
      return this.dscg_main_dise_name;
   }

   public void setDscg_main_dise_name(String dscg_main_dise_name) {
      this.dscg_main_dise_name = dscg_main_dise_name;
   }

   public List getFsi_diagnose_dtos() {
      return this.fsi_diagnose_dtos;
   }

   public void setFsi_diagnose_dtos(List fsi_diagnose_dtos) {
      this.fsi_diagnose_dtos = fsi_diagnose_dtos;
   }

   public String getDr_codg() {
      return this.dr_codg;
   }

   public void setDr_codg(String dr_codg) {
      this.dr_codg = dr_codg;
   }

   public String getAdm_dept_codg() {
      return this.adm_dept_codg;
   }

   public void setAdm_dept_codg(String adm_dept_codg) {
      this.adm_dept_codg = adm_dept_codg;
   }

   public String getAdm_dept_name() {
      return this.adm_dept_name;
   }

   public void setAdm_dept_name(String adm_dept_name) {
      this.adm_dept_name = adm_dept_name;
   }

   public String getDscg_dept_codg() {
      return this.dscg_dept_codg;
   }

   public void setDscg_dept_codg(String dscg_dept_codg) {
      this.dscg_dept_codg = dscg_dept_codg;
   }

   public String getDscg_dept_name() {
      return this.dscg_dept_name;
   }

   public void setDscg_dept_name(String dscg_dept_name) {
      this.dscg_dept_name = dscg_dept_name;
   }

   public String getMed_mdtrt_type() {
      return this.med_mdtrt_type;
   }

   public void setMed_mdtrt_type(String med_mdtrt_type) {
      this.med_mdtrt_type = med_mdtrt_type;
   }

   public String getMed_type() {
      return this.med_type;
   }

   public void setMed_type(String med_type) {
      this.med_type = med_type;
   }

   public List getFsi_order_dtos() {
      return this.fsi_order_dtos;
   }

   public void setFsi_order_dtos(List fsi_order_dtos) {
      this.fsi_order_dtos = fsi_order_dtos;
   }

   public String getMatn_stas() {
      return this.matn_stas;
   }

   public void setMatn_stas(String matn_stas) {
      this.matn_stas = matn_stas;
   }

   public BigDecimal getMedfee_sumamt() {
      return this.medfee_sumamt;
   }

   public void setMedfee_sumamt(BigDecimal medfee_sumamt) {
      this.medfee_sumamt = medfee_sumamt;
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

   public BigDecimal getAcct_payamt() {
      return this.acct_payamt;
   }

   public void setAcct_payamt(BigDecimal acct_payamt) {
      this.acct_payamt = acct_payamt;
   }

   public BigDecimal getMa_amt() {
      return this.ma_amt;
   }

   public void setMa_amt(BigDecimal ma_amt) {
      this.ma_amt = ma_amt;
   }

   public BigDecimal getHifp_payamt() {
      return this.hifp_payamt;
   }

   public void setHifp_payamt(BigDecimal hifp_payamt) {
      this.hifp_payamt = hifp_payamt;
   }

   public BigDecimal getSetl_totlnum() {
      return this.setl_totlnum;
   }

   public void setSetl_totlnum(BigDecimal setl_totlnum) {
      this.setl_totlnum = setl_totlnum;
   }

   public String getInsutype() {
      return this.insutype;
   }

   public void setInsutype(String insutype) {
      this.insutype = insutype;
   }

   public String getReim_flag() {
      return this.reim_flag;
   }

   public void setReim_flag(String reim_flag) {
      this.reim_flag = reim_flag;
   }

   public String getOut_setl_flag() {
      return this.out_setl_flag;
   }

   public void setOut_setl_flag(String out_setl_flag) {
      this.out_setl_flag = out_setl_flag;
   }

   public List getFsi_operation_dtos() {
      return this.fsi_operation_dtos;
   }

   public void setFsi_operation_dtos(List fsi_operation_dtos) {
      this.fsi_operation_dtos = fsi_operation_dtos;
   }
}
