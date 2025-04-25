package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderPerformDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private String performListNo;
   private String performListSortNumber;
   private String orderGroupNo;
   @Excel(
      name = "医院服务项目编码"
   )
   private String chargeNo;
   @Excel(
      name = "医院服务项目名称"
   )
   private String chargeName;
   @Excel(
      name = "标准项目编号"
   )
   private String standardCode;
   @Excel(
      name = "标准项目名称"
   )
   private String standardName;
   @Excel(
      name = "规格"
   )
   private String standard;
   @Excel(
      name = "单位"
   )
   private String unit;
   @Excel(
      name = "实际用量"
   )
   private BigDecimal orderActualUsage;
   @Excel(
      name = "用量单位"
   )
   private String usageUnit;
   @Excel(
      name = "数量"
   )
   private BigDecimal orderDose;
   @Excel(
      name = "总量"
   )
   private BigDecimal orderTotalDose;
   @Excel(
      name = "单价"
   )
   private BigDecimal price;
   @Excel(
      name = "金额"
   )
   private BigDecimal orderTotal;
   @Excel(
      name = "频率"
   )
   private String orderFreq;
   @Excel(
      name = "用法"
   )
   private String orderUsageWay;
   @Excel(
      name = "是否草药(0：否；1：是)"
   )
   private Integer herbalFlag;
   @Excel(
      name = "草药剂数"
   )
   private BigDecimal herbalDose;
   @Excel(
      name = "药品剂型"
   )
   private String drugForm;
   @Excel(
      name = "药品类型"
   )
   private String drugClassCode;
   @Excel(
      name = "医师说明"
   )
   private String doctorInstructions;
   @Excel(
      name = "自备药品标志(0：否；1：是)"
   )
   private Integer patientSelfDrugFlag;
   @Excel(
      name = "计价标志(0：不计价；1：计价)"
   )
   private Integer priceFlag;
   @Excel(
      name = "药典编号"
   )
   private String pharmacopoeiaNo;
   @Excel(
      name = "临床路径编号"
   )
   private String cpNo;
   private String orderGroupSortNumber;
   @Excel(
      name = "库存编号"
   )
   private String stockNo;
   @Excel(
      name = "卫材标志(0：否；1：是) (医嘱表中没有此数据)"
   )
   private Integer hygienicMaterialFlag;
   @Excel(
      name = "皮试结果"
   )
   private String skinTestResults;
   private String orderSortNumber;
   private Integer subjectFlag;
   private String upStatus;

   public Integer getSubjectFlag() {
      return this.subjectFlag;
   }

   public void setSubjectFlag(Integer subjectFlag) {
      this.subjectFlag = subjectFlag;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListSortNumber(String performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public String getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo;
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setStandardCode(String standardCode) {
      this.standardCode = standardCode;
   }

   public String getStandardCode() {
      return this.standardCode;
   }

   public void setStandardName(String standardName) {
      this.standardName = standardName;
   }

   public String getStandardName() {
      return this.standardName;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getUnit() {
      return this.unit;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderUsageWay(String orderUsageWay) {
      this.orderUsageWay = orderUsageWay;
   }

   public String getOrderUsageWay() {
      return this.orderUsageWay;
   }

   public void setHerbalFlag(Integer herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public Integer getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setPatientSelfDrugFlag(Integer patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public Integer getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPriceFlag(Integer priceFlag) {
      this.priceFlag = priceFlag;
   }

   public Integer getPriceFlag() {
      return this.priceFlag;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setHygienicMaterialFlag(Integer hygienicMaterialFlag) {
      this.hygienicMaterialFlag = hygienicMaterialFlag;
   }

   public Integer getHygienicMaterialFlag() {
      return this.hygienicMaterialFlag;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public String getUpStatus() {
      return this.upStatus;
   }

   public void setUpStatus(String upStatus) {
      this.upStatus = upStatus;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("performListNo", this.getPerformListNo()).append("performListSortNumber", this.getPerformListSortNumber()).append("orderGroupNo", this.getOrderGroupNo()).append("chargeNo", this.getChargeNo()).append("chargeName", this.getChargeName()).append("standardCode", this.getStandardCode()).append("standardName", this.getStandardName()).append("standard", this.getStandard()).append("unit", this.getUnit()).append("orderActualUsage", this.getOrderActualUsage()).append("usageUnit", this.getUsageUnit()).append("orderDose", this.getOrderDose()).append("orderTotalDose", this.getOrderTotalDose()).append("price", this.getPrice()).append("orderTotal", this.getOrderTotal()).append("orderFreq", this.getOrderFreq()).append("orderUsageWay", this.getOrderUsageWay()).append("herbalFlag", this.getHerbalFlag()).append("herbalDose", this.getHerbalDose()).append("drugForm", this.getDrugForm()).append("drugClassCode", this.getDrugClassCode()).append("doctorInstructions", this.getDoctorInstructions()).append("patientSelfDrugFlag", this.getPatientSelfDrugFlag()).append("priceFlag", this.getPriceFlag()).append("pharmacopoeiaNo", this.getPharmacopoeiaNo()).append("cpNo", this.getCpNo()).append("orderGroupSortNumber", this.getOrderGroupSortNumber()).append("stockNo", this.getStockNo()).append("hygienicMaterialFlag", this.getHygienicMaterialFlag()).append("skinTestResults", this.getSkinTestResults()).append("orderSortNumber", this.getOrderSortNumber()).append("upStatus", this.getUpStatus()).toString();
   }
}
