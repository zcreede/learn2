package com.emr.project.webservice.domain.vo;

import java.math.BigDecimal;

public class MedicareAuditResultDetailVo {
   private String jrd_id;
   private String patn_id;
   private String mdtrt_id;
   private String rx_id;
   private String vola_item_type;
   private BigDecimal vola_amt;

   public String getJrd_id() {
      return this.jrd_id;
   }

   public void setJrd_id(String jrd_id) {
      this.jrd_id = jrd_id;
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

   public String getRx_id() {
      return this.rx_id;
   }

   public void setRx_id(String rx_id) {
      this.rx_id = rx_id;
   }

   public String getVola_item_type() {
      return this.vola_item_type;
   }

   public void setVola_item_type(String vola_item_type) {
      this.vola_item_type = vola_item_type;
   }

   public BigDecimal getVola_amt() {
      return this.vola_amt;
   }

   public void setVola_amt(BigDecimal vola_amt) {
      this.vola_amt = vola_amt;
   }
}
