package com.emr.project.report.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillingDetailsVo implements Serializable {
   private String admissionNo;
   private BigDecimal totalAmount;
   private BigDecimal zfje;

   public BillingDetailsVo() {
      this.totalAmount = BigDecimal.ZERO;
      this.zfje = BigDecimal.ZERO;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public BigDecimal getTotalAmount() {
      return this.totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public BigDecimal getZfje() {
      return this.zfje;
   }

   public void setZfje(BigDecimal zfje) {
      this.zfje = zfje;
   }
}
