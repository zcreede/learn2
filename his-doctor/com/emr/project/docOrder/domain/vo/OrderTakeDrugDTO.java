package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class OrderTakeDrugDTO {
   private String takeDrugStatus;
   private String chargeName;
   private String chargeNo;
   private String standard;
   private String orderUnit;
   private BigDecimal orderPrice;
   private BigDecimal orderDose;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date issueDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date takeDrugTime;
   private String orderAuditDoName;
   private String takeDrugOperator;
   private String pharmacyName;
   private BigDecimal total;
   private BigDecimal applyDose;
   private BigDecimal returnDose;
   private String orderGroupSortNumber;
   private String performListNo;
   private String type;
   private String drugStockNo;

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getTakeDrugStatus() {
      return this.takeDrugStatus;
   }

   public void setTakeDrugStatus(String takeDrugStatus) {
      this.takeDrugStatus = takeDrugStatus;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getOrderUnit() {
      return this.orderUnit;
   }

   public void setOrderUnit(String orderUnit) {
      this.orderUnit = orderUnit;
   }

   public BigDecimal getOrderPrice() {
      return this.orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
   }

   public Date getIssueDate() {
      return this.issueDate;
   }

   public void setIssueDate(Date issueDate) {
      this.issueDate = issueDate;
   }

   public Date getTakeDrugTime() {
      return this.takeDrugTime;
   }

   public void setTakeDrugTime(Date takeDrugTime) {
      this.takeDrugTime = takeDrugTime;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getOrderAuditDoName() {
      return this.orderAuditDoName;
   }

   public void setOrderAuditDoName(String orderAuditDoName) {
      this.orderAuditDoName = orderAuditDoName;
   }

   public String getTakeDrugOperator() {
      return this.takeDrugOperator;
   }

   public void setTakeDrugOperator(String takeDrugOperator) {
      this.takeDrugOperator = takeDrugOperator;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getApplyDose() {
      return this.applyDose;
   }

   public void setApplyDose(BigDecimal applyDose) {
      this.applyDose = applyDose;
   }

   public BigDecimal getReturnDose() {
      return this.returnDose;
   }

   public void setReturnDose(BigDecimal returnDose) {
      this.returnDose = returnDose;
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }
}
