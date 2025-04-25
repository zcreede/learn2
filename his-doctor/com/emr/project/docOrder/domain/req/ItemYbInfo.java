package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemYbInfo implements Serializable {
   private String cpNo;
   private String ybCode;
   private String ybName;
   private String ybLv;
   private BigDecimal ybDj;
   private BigDecimal ybZfbl;

   public BigDecimal getYbZfbl() {
      return this.ybZfbl;
   }

   public void setYbZfbl(BigDecimal ybZfbl) {
      this.ybZfbl = ybZfbl;
   }

   public BigDecimal getYbDj() {
      return this.ybDj;
   }

   public void setYbDj(BigDecimal ybDj) {
      this.ybDj = ybDj;
   }

   public String getYbLv() {
      return this.ybLv;
   }

   public void setYbLv(String ybLv) {
      this.ybLv = ybLv;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getYbCode() {
      return this.ybCode;
   }

   public void setYbCode(String ybCode) {
      this.ybCode = ybCode;
   }

   public String getYbName() {
      return this.ybName;
   }

   public void setYbName(String ybName) {
      this.ybName = ybName;
   }
}
