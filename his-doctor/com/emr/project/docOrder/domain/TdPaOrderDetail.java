package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderDetail extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱类型"
   )
   private String orderType;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "序号"
   )
   private String orderGroupSortNumber;
   @Excel(
      name = "组号"
   )
   private Integer orderGroupNo;
   @Excel(
      name = "医嘱类别编号"
   )
   private String orderClassCode;
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
      name = "数量(每次数量，如每次1瓶，tid）"
   )
   private BigDecimal orderDose;
   @Excel(
      name = "总量",
      readConverterExp = "t=id,3=瓶"
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
   private String herbalFlag;
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
      name = "主从标志(0：主项目；1：从项目)"
   )
   private String subjectFlag;
   @Excel(
      name = "执行科室编号"
   )
   private String detailPerformDepCode;
   @Excel(
      name = "自备药品标志(0：否；1：是)"
   )
   private String patientSelfDrugFlag;
   @Excel(
      name = "用药天数(用于解决口服药领药问题)"
   )
   private BigDecimal orderUsageDays;
   @Excel(
      name = "抗菌药物使用目的(1：治疗性用药；2：预防性用药)"
   )
   private String purposeAntimicrobialUse;
   @Excel(
      name = "滴速"
   )
   private String drippingSpeed;
   @Excel(
      name = "首次翻倍标志(0：否；1：是)"
   )
   private String firstDoubleFlag;
   @Excel(
      name = "首次执行次数"
   )
   private BigDecimal firstPerformNum;
   @Excel(
      name = "首次执行总量"
   )
   private BigDecimal firstPerformTotalDose;
   @Excel(
      name = "皮试结果"
   )
   private String skinTestResults;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "皮试时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date skinTestResultsTime;
   @Excel(
      name = "皮试批号"
   )
   private String skinTestResultsLot;
   @Excel(
      name = "药典编号"
   )
   private String pharmacopoeiaNo;
   @Excel(
      name = "医师说明"
   )
   private String doctorInstructions;
   @Excel(
      name = "婴儿住院号"
   )
   private String babyAdmissionNo;
   @Excel(
      name = "临床项目编号"
   )
   private String cpNo;
   @Excel(
      name = "临床项目名称"
   )
   private String cpName;
   @Excel(
      name = "医嘱重整序号"
   )
   private Integer reOrganizationNo;
   @Excel(
      name = "原医嘱编号(医嘱复制和医嘱重整存原来饿医嘱编码)"
   )
   private String sourceOrderNo;
   @Excel(
      name = "配送标志(0：未配送；1：已配送)"
   )
   private String distributionFlag;
   @Excel(
      name = "库存编号"
   )
   private String stockNo;
   @Excel(
      name = "出院带药标志(0：无带药；1：有带药)  2 "
   )
   private String outHavaDrugFlag;
   @Excel(
      name = "卫材标志(0：否；1：是) (医嘱表中没有此数据)"
   )
   private String hygienicMaterialFlag;
   @Excel(
      name = "医嘱项目标志",
      readConverterExp = "正=常药品、自备药品、特殊药品、嘱托"
   )
   private String orderItemFlag;
   @Excel(
      name = "高危药品标志",
      readConverterExp = "0=非高危药品，1高危药品"
   )
   private String highRiskFlag;
   private String masterOrder;
   private String cpId;
   private String doseCoef;
   private String cpStageCode;
   private String cpItemCode;

   public String getDoseCoef() {
      return this.doseCoef;
   }

   public void setDoseCoef(String doseCoef) {
      this.doseCoef = doseCoef;
   }

   public String getCpId() {
      return this.cpId;
   }

   public void setCpId(String cpId) {
      this.cpId = cpId;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
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

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getHerbalFlag() {
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

   public void setSubjectFlag(String subjectFlag) {
      this.subjectFlag = subjectFlag;
   }

   public String getSubjectFlag() {
      return this.subjectFlag;
   }

   public void setDetailPerformDepCode(String detailPerformDepCode) {
      this.detailPerformDepCode = detailPerformDepCode;
   }

   public String getDetailPerformDepCode() {
      return this.detailPerformDepCode;
   }

   public void setPatientSelfDrugFlag(String patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public String getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPurposeAntimicrobialUse(String purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
   }

   public String getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setFirstDoubleFlag(String firstDoubleFlag) {
      this.firstDoubleFlag = firstDoubleFlag;
   }

   public String getFirstDoubleFlag() {
      return this.firstDoubleFlag;
   }

   public void setFirstPerformTotalDose(BigDecimal firstPerformTotalDose) {
      this.firstPerformTotalDose = firstPerformTotalDose;
   }

   public BigDecimal getFirstPerformTotalDose() {
      return this.firstPerformTotalDose;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResultsTime(Date skinTestResultsTime) {
      this.skinTestResultsTime = skinTestResultsTime;
   }

   public Date getSkinTestResultsTime() {
      return this.skinTestResultsTime;
   }

   public void setSkinTestResultsLot(String skinTestResultsLot) {
      this.skinTestResultsLot = skinTestResultsLot;
   }

   public String getSkinTestResultsLot() {
      return this.skinTestResultsLot;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setReOrganizationNo(Integer reOrganizationNo) {
      this.reOrganizationNo = reOrganizationNo;
   }

   public Integer getReOrganizationNo() {
      return this.reOrganizationNo;
   }

   public void setSourceOrderNo(String sourceOrderNo) {
      this.sourceOrderNo = sourceOrderNo;
   }

   public String getSourceOrderNo() {
      return this.sourceOrderNo;
   }

   public void setDistributionFlag(String distributionFlag) {
      this.distributionFlag = distributionFlag;
   }

   public String getDistributionFlag() {
      return this.distributionFlag;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setHygienicMaterialFlag(String hygienicMaterialFlag) {
      this.hygienicMaterialFlag = hygienicMaterialFlag;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public BigDecimal getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(BigDecimal orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public BigDecimal getFirstPerformNum() {
      return this.firstPerformNum;
   }

   public void setFirstPerformNum(BigDecimal firstPerformNum) {
      this.firstPerformNum = firstPerformNum;
   }

   public String getHygienicMaterialFlag() {
      return this.hygienicMaterialFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setHighRiskFlag(String highRiskFlag) {
      this.highRiskFlag = highRiskFlag;
   }

   public String getHighRiskFlag() {
      return this.highRiskFlag;
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

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orderNo", this.getOrderNo()).append("orderType", this.getOrderType()).append("orderSortNumber", this.getOrderSortNumber()).append("orderGroupSortNumber", this.getOrderGroupSortNumber()).append("orderGroupNo", this.getOrderGroupNo()).append("orderClassCode", this.getOrderClassCode()).append("chargeNo", this.getChargeNo()).append("chargeName", this.getChargeName()).append("standardCode", this.getStandardCode()).append("standardName", this.getStandardName()).append("standard", this.getStandard()).append("unit", this.getUnit()).append("orderActualUsage", this.getOrderActualUsage()).append("usageUnit", this.getUsageUnit()).append("orderDose", this.getOrderDose()).append("orderTotalDose", this.getOrderTotalDose()).append("price", this.getPrice()).append("orderTotal", this.getOrderTotal()).append("orderFreq", this.getOrderFreq()).append("orderUsageWay", this.getOrderUsageWay()).append("herbalFlag", this.getHerbalFlag()).append("herbalDose", this.getHerbalDose()).append("drugForm", this.getDrugForm()).append("drugClassCode", this.getDrugClassCode()).append("subjectFlag", this.getSubjectFlag()).append("detailPerformDepCode", this.getDetailPerformDepCode()).append("patientSelfDrugFlag", this.getPatientSelfDrugFlag()).append("orderUsageDays", this.getOrderUsageDays()).append("purposeAntimicrobialUse", this.getPurposeAntimicrobialUse()).append("drippingSpeed", this.getDrippingSpeed()).append("firstDoubleFlag", this.getFirstDoubleFlag()).append("firstPerformNum", this.getFirstPerformNum()).append("firstPerformTotalDose", this.getFirstPerformTotalDose()).append("skinTestResults", this.getSkinTestResults()).append("skinTestResultsTime", this.getSkinTestResultsTime()).append("skinTestResultsLot", this.getSkinTestResultsLot()).append("pharmacopoeiaNo", this.getPharmacopoeiaNo()).append("doctorInstructions", this.getDoctorInstructions()).append("babyAdmissionNo", this.getBabyAdmissionNo()).append("cpNo", this.getCpNo()).append("cpName", this.getCpName()).append("reOrganizationNo", this.getReOrganizationNo()).append("sourceOrderNo", this.getSourceOrderNo()).append("distributionFlag", this.getDistributionFlag()).append("stockNo", this.getStockNo()).append("outHavaDrugFlag", this.getOutHavaDrugFlag()).append("hygienicMaterialFlag", this.getHygienicMaterialFlag()).append("orderItemFlag", this.getOrderItemFlag()).append("highRiskFlag", this.getHighRiskFlag()).append("masterOrder", this.getMasterOrder()).append("doseCoef", this.getDoseCoef()).toString();
   }
}
