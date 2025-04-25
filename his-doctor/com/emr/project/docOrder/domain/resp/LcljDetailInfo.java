package com.emr.project.docOrder.domain.resp;

import java.math.BigDecimal;

public class LcljDetailInfo {
   private String orderType;
   private Integer orderGroupNo;
   private String orderClassCode;
   private String masterOrder;
   private String cpNo;
   private String cpName;
   private String standard;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private String orderUsageWay;
   private String orderUsageWayName;
   private String orderFreq;
   private String orderFreqName;
   private BigDecimal orderUsageDays;
   private BigDecimal orderDose;
   private String unit;
   private BigDecimal price;
   private BigDecimal orderTotal;
   private String requiredFlag;
   private String drugClassCode;
   private String performDepCode;
   private String performDepName;
   private String detailPerformDepCode;
   private String detailPerformDepName;
   private String lcljItemName;
   private String takeDrugMode;
   private String doctorInstructions;
   private String outHavaDrugFlag;
   private String examPartCd;
   private String examPartName;
   private String specCd;
   private String specName;
   private String distributionFlag;
   private String usedFlag;
   private String lcljCpNo;
   private String lcljCpName;
   private Long stockNo;
   private String cpStageCode;
   private String cpItemCode;

   public Long getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(Long stockNo) {
      this.stockNo = stockNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
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

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
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

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public String getOrderUsageWayName() {
      return this.orderUsageWayName;
   }

   public void setOrderUsageWayName(String orderUsageWayName) {
      this.orderUsageWayName = orderUsageWayName;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getOrderFreqName() {
      return this.orderFreqName;
   }

   public void setOrderFreqName(String orderFreqName) {
      this.orderFreqName = orderFreqName;
   }

   public BigDecimal getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(BigDecimal orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
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

   public String getRequiredFlag() {
      return this.requiredFlag;
   }

   public void setRequiredFlag(String requiredFlag) {
      this.requiredFlag = requiredFlag;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getDetailPerformDepCode() {
      return this.detailPerformDepCode;
   }

   public void setDetailPerformDepCode(String detailPerformDepCode) {
      this.detailPerformDepCode = detailPerformDepCode;
   }

   public String getDetailPerformDepName() {
      return this.detailPerformDepName;
   }

   public void setDetailPerformDepName(String detailPerformDepName) {
      this.detailPerformDepName = detailPerformDepName;
   }

   public String getLcljItemName() {
      return this.lcljItemName;
   }

   public void setLcljItemName(String lcljItemName) {
      this.lcljItemName = lcljItemName;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getExamPartCd() {
      return this.examPartCd;
   }

   public void setExamPartCd(String examPartCd) {
      this.examPartCd = examPartCd;
   }

   public String getExamPartName() {
      return this.examPartName;
   }

   public void setExamPartName(String examPartName) {
      this.examPartName = examPartName;
   }

   public String getSpecCd() {
      return this.specCd;
   }

   public void setSpecCd(String specCd) {
      this.specCd = specCd;
   }

   public String getSpecName() {
      return this.specName;
   }

   public void setSpecName(String specName) {
      this.specName = specName;
   }

   public String getDistributionFlag() {
      return this.distributionFlag;
   }

   public void setDistributionFlag(String distributionFlag) {
      this.distributionFlag = distributionFlag;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public String getUsedFlag() {
      return this.usedFlag;
   }

   public void setUsedFlag(String usedFlag) {
      this.usedFlag = usedFlag;
   }

   public String getLcljCpNo() {
      return this.lcljCpNo;
   }

   public void setLcljCpNo(String lcljCpNo) {
      this.lcljCpNo = lcljCpNo;
   }

   public String getLcljCpName() {
      return this.lcljCpName;
   }

   public void setLcljCpName(String lcljCpName) {
      this.lcljCpName = lcljCpName;
   }

   public String getCpStageCode() {
      return this.cpStageCode;
   }

   public void setCpStageCode(String cpStageCode) {
      this.cpStageCode = cpStageCode;
   }

   public String getCpItemCode() {
      return this.cpItemCode;
   }

   public void setCpItemCode(String cpItemCode) {
      this.cpItemCode = cpItemCode;
   }
}
