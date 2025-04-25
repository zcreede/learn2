package com.emr.project.docOrder.domain;

import java.math.BigDecimal;

public class InpatientOrderPerformDetail {
   private static final long serialVersionUID = 1L;
   private String performListNo;
   private Integer performListSortNumber;
   private String orderSortNumber;
   private Integer orderGroupNo;
   private String orderGroupSortNumber;
   private String chargeNo;
   private String chargeName;
   private String standardCode;
   private String standardName;
   private String standard;
   private String unit;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private BigDecimal orderDose;
   private BigDecimal orderTotalDose;
   private BigDecimal price;
   private BigDecimal orderTotal;
   private String orderFreq;
   private String orderFreqCode;
   private String orderUsageWay;
   private String orderUsageWayCode;
   private Integer herbalFlag;
   private BigDecimal herbalDose;
   private String drugForm;
   private String doctorInstructions;
   private Integer patientSelfDrugFlag;
   private Integer priceFlag;
   private String pharmacopoeiaNo;
   private String cpNo;
   private String cpName;
   private String stockNo;
   private Integer hygienicMaterialFlag;
   private String drugClassCode;
   private BigDecimal firstPerformTotalDose;

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getOrderFreqCode() {
      return this.orderFreqCode;
   }

   public void setOrderFreqCode(String orderFreqCode) {
      this.orderFreqCode = orderFreqCode;
   }

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public String getOrderUsageWayCode() {
      return this.orderUsageWayCode;
   }

   public void setOrderUsageWayCode(String orderUsageWayCode) {
      this.orderUsageWayCode = orderUsageWayCode;
   }

   public Integer getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(Integer herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public Integer getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPatientSelfDrugFlag(Integer patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public Integer getPriceFlag() {
      return this.priceFlag;
   }

   public void setPriceFlag(Integer priceFlag) {
      this.priceFlag = priceFlag;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public Integer getHygienicMaterialFlag() {
      return this.hygienicMaterialFlag;
   }

   public void setHygienicMaterialFlag(Integer hygienicMaterialFlag) {
      this.hygienicMaterialFlag = hygienicMaterialFlag;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public BigDecimal getFirstPerformTotalDose() {
      return this.firstPerformTotalDose;
   }

   public void setFirstPerformTotalDose(BigDecimal firstPerformTotalDose) {
      this.firstPerformTotalDose = firstPerformTotalDose;
   }
}
