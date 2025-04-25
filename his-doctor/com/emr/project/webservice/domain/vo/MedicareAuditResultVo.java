package com.emr.project.webservice.domain.vo;

import java.math.BigDecimal;
import java.util.List;

public class MedicareAuditResultVo {
   private String jr_id;
   private String rule_id;
   private String rule_name;
   private String vola_cont;
   private String patn_id;
   private String mdtrt_id;
   private List judge_result_detail_dtos;
   private BigDecimal vola_amt;
   private String vola_amt_stas;
   private String sev_deg;
   private String vola_evid;
   private String vola_bhvr_type;
   private String task_id;

   public String getJr_id() {
      return this.jr_id;
   }

   public void setJr_id(String jr_id) {
      this.jr_id = jr_id;
   }

   public String getRule_id() {
      return this.rule_id;
   }

   public void setRule_id(String rule_id) {
      this.rule_id = rule_id;
   }

   public String getRule_name() {
      return this.rule_name;
   }

   public void setRule_name(String rule_name) {
      this.rule_name = rule_name;
   }

   public String getVola_cont() {
      return this.vola_cont;
   }

   public void setVola_cont(String vola_cont) {
      this.vola_cont = vola_cont;
   }

   public String getPatn_id() {
      return this.patn_id;
   }

   public void setPatn_id(String patn_id) {
      this.patn_id = patn_id;
   }

   public String getMdtrt_id() {
      return this.mdtrt_id;
   }

   public void setMdtrt_id(String mdtrt_id) {
      this.mdtrt_id = mdtrt_id;
   }

   public List getJudge_result_detail_dtos() {
      return this.judge_result_detail_dtos;
   }

   public void setJudge_result_detail_dtos(List judge_result_detail_dtos) {
      this.judge_result_detail_dtos = judge_result_detail_dtos;
   }

   public BigDecimal getVola_amt() {
      return this.vola_amt;
   }

   public void setVola_amt(BigDecimal vola_amt) {
      this.vola_amt = vola_amt;
   }

   public String getVola_amt_stas() {
      return this.vola_amt_stas;
   }

   public void setVola_amt_stas(String vola_amt_stas) {
      this.vola_amt_stas = vola_amt_stas;
   }

   public String getSev_deg() {
      return this.sev_deg;
   }

   public void setSev_deg(String sev_deg) {
      this.sev_deg = sev_deg;
   }

   public String getVola_evid() {
      return this.vola_evid;
   }

   public void setVola_evid(String vola_evid) {
      this.vola_evid = vola_evid;
   }

   public String getVola_bhvr_type() {
      return this.vola_bhvr_type;
   }

   public void setVola_bhvr_type(String vola_bhvr_type) {
      this.vola_bhvr_type = vola_bhvr_type;
   }

   public String getTask_id() {
      return this.task_id;
   }

   public void setTask_id(String task_id) {
      this.task_id = task_id;
   }
}
