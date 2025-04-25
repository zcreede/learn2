package com.emr.project.his.domain.vo;

import java.math.BigDecimal;

public class DrugDoseVo {
   private String ypid;
   private String yfbm;
   private String ypbm;
   private String kcbh;
   private BigDecimal orderDose;
   private String orderNo;
   private Long takeDrugListId;
   private BigDecimal price;

   public DrugDoseVo() {
   }

   public DrugDoseVo(String ypid, String yfbm, String ypbm, String kcbh, BigDecimal orderDose, BigDecimal price) {
      this.ypid = ypid;
      this.yfbm = yfbm;
      this.ypbm = ypbm;
      this.kcbh = kcbh;
      this.orderDose = orderDose;
      this.price = price;
   }

   public DrugDoseVo(String ypid, String yfbm, String ypbm, String kcbh, BigDecimal orderDose, String orderNo, Long takeDrugListId, BigDecimal price) {
      this.ypid = ypid;
      this.yfbm = yfbm;
      this.ypbm = ypbm;
      this.kcbh = kcbh;
      this.orderDose = orderDose;
      this.orderNo = orderNo;
      this.takeDrugListId = takeDrugListId;
      this.price = price;
   }

   public String getYpid() {
      return this.ypid;
   }

   public void setYpid(String ypid) {
      this.ypid = ypid;
   }

   public String getYfbm() {
      return this.yfbm;
   }

   public void setYfbm(String yfbm) {
      this.yfbm = yfbm;
   }

   public String getYpbm() {
      return this.ypbm;
   }

   public void setYpbm(String ypbm) {
      this.ypbm = ypbm;
   }

   public String getKcbh() {
      return this.kcbh;
   }

   public void setKcbh(String kcbh) {
      this.kcbh = kcbh;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Long getTakeDrugListId() {
      return this.takeDrugListId;
   }

   public void setTakeDrugListId(Long takeDrugListId) {
      this.takeDrugListId = takeDrugListId;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }
}
