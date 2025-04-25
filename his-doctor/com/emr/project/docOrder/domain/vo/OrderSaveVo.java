package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class OrderSaveVo {
   private String orderNo;
   private String performComputerIp;
   private Integer reOrganizationNo;
   private String applyFormNo;
   private String patientId;
   private String babyAdmissionNo;
   private String orderSortNumber;
   private Integer orderGroupNo;
   private String orderGroupSortNumber;
   private String orderType;
   private String examSex;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String orderClassCode;
   private String orderClassName;
   private String orderItemFlag;
   private String outHavaDrugFlag;
   private String cpId;
   private String cpNo;
   private String cpName;
   private String standard;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private String orderUsageWay;
   private String orderUsageWayName;
   private String orderFreq;
   private String orderFreqName;
   private Integer freqValue;
   private BigDecimal orderDose;
   private BigDecimal price;
   private String unit;
   private BigDecimal orderTotalDose;
   private BigDecimal firstPerformNum;
   private String drippingSpeed;
   private String additionalFlag;
   private String urgentFlag;
   private String takeDrugMode;
   private BigDecimal orderUsageDays;
   private String purposeAntimicrobialUse;
   private String doctorInstructions;
   private BigDecimal orderTotal;
   private String detailPerformDepCode;
   private String detailPerformDepName;
   private String orderFlagCd;
   private String orderFlagName;
   private String documentTypeNo;
   private String orderItemType;
   private String masterOrder;
   private String auditDepFlag;
   private String auditDepCode;
   private String auditDepName;
   private String performDepFlag;
   private String performDepCode;
   private String performDepName;
   private String sourceOrderNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date outTime;
   private String outCon;
   private String leaveWayCd;
   private String referralHospital;
   private String priceFlag;
   private String stockNo;
   private String highRiskFlag;
   private String prescribeNo;
   private String prescribeName;
   private String herbalFlag;
   private BigDecimal herbalDose;
   private String herbalIntr;
   private String decoctMethod;
   private String plasterFlag;
   private Integer precontractFlag;
   private String diagnosisCode;
   private String diagnosisName;
   private String complaint;
   private String medicalRecordDigest;
   private String purposeExamination;
   private String examPartCd;
   private String examPartName;
   private String specCd;
   private String specName;
   private String executorFlag;
   private String emrEventCd;
   private String drugForm;
   private String drugType;
   private String drugTypeName;
   private String examMachineName;
   private String drugClassCode;
   private String drugLevelCd;
   private Long agentId;
   private String isOpenFlag;
   private String lcljCpNo;
   private String lcljCpName;
   private String changeClass;
   private String changeReason;
   private String changeRemark;
   private String lcljNo;
   private String lcljName;
   private String cpStageCode;
   private String cpItemCode;

   public String getExamSex() {
      return this.examSex;
   }

   public void setExamSex(String examSex) {
      this.examSex = examSex;
   }

   public String getIsOpenFlag() {
      return this.isOpenFlag;
   }

   public void setIsOpenFlag(String isOpenFlag) {
      this.isOpenFlag = isOpenFlag;
   }

   public Long getAgentId() {
      return this.agentId;
   }

   public void setAgentId(Long agentId) {
      this.agentId = agentId;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getDrugLevelCd() {
      return this.drugLevelCd;
   }

   public void setDrugLevelCd(String drugLevelCd) {
      this.drugLevelCd = drugLevelCd;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getExamMachineName() {
      return this.examMachineName;
   }

   public void setExamMachineName(String examMachineName) {
      this.examMachineName = examMachineName;
   }

   public String getDrugType() {
      return this.drugType;
   }

   public void setDrugType(String drugType) {
      this.drugType = drugType;
   }

   public String getDrugTypeName() {
      return this.drugTypeName;
   }

   public void setDrugTypeName(String drugTypeName) {
      this.drugTypeName = drugTypeName;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getExecutorFlag() {
      return this.executorFlag;
   }

   public void setExecutorFlag(String executorFlag) {
      this.executorFlag = executorFlag;
   }

   public String getEmrEventCd() {
      return this.emrEventCd;
   }

   public void setEmrEventCd(String emrEventCd) {
      this.emrEventCd = emrEventCd;
   }

   public String getDiagnosisCode() {
      return this.diagnosisCode;
   }

   public void setDiagnosisCode(String diagnosisCode) {
      this.diagnosisCode = diagnosisCode;
   }

   public String getDiagnosisName() {
      return this.diagnosisName;
   }

   public void setDiagnosisName(String diagnosisName) {
      this.diagnosisName = diagnosisName;
   }

   public String getComplaint() {
      return this.complaint;
   }

   public void setComplaint(String complaint) {
      this.complaint = complaint;
   }

   public String getMedicalRecordDigest() {
      return this.medicalRecordDigest;
   }

   public void setMedicalRecordDigest(String medicalRecordDigest) {
      this.medicalRecordDigest = medicalRecordDigest;
   }

   public String getPurposeExamination() {
      return this.purposeExamination;
   }

   public void setPurposeExamination(String purposeExamination) {
      this.purposeExamination = purposeExamination;
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

   public Integer getPrecontractFlag() {
      return this.precontractFlag;
   }

   public void setPrecontractFlag(Integer precontractFlag) {
      this.precontractFlag = precontractFlag;
   }

   public String getPriceFlag() {
      return this.priceFlag;
   }

   public void setPriceFlag(String priceFlag) {
      this.priceFlag = priceFlag;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public String getOutCon() {
      return this.outCon;
   }

   public void setOutCon(String outCon) {
      this.outCon = outCon;
   }

   public String getLeaveWayCd() {
      return this.leaveWayCd;
   }

   public void setLeaveWayCd(String leaveWayCd) {
      this.leaveWayCd = leaveWayCd;
   }

   public String getPerformDepFlag() {
      return this.performDepFlag;
   }

   public void setPerformDepFlag(String performDepFlag) {
      this.performDepFlag = performDepFlag;
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

   public String getAuditDepName() {
      return this.auditDepName;
   }

   public void setAuditDepName(String auditDepName) {
      this.auditDepName = auditDepName;
   }

   public String getAuditDepFlag() {
      return this.auditDepFlag;
   }

   public void setAuditDepFlag(String auditDepFlag) {
      this.auditDepFlag = auditDepFlag;
   }

   public String getAuditDepCode() {
      return this.auditDepCode;
   }

   public void setAuditDepCode(String auditDepCode) {
      this.auditDepCode = auditDepCode;
   }

   public String getOrderClassName() {
      return this.orderClassName;
   }

   public void setOrderClassName(String orderClassName) {
      this.orderClassName = orderClassName;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
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

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getCpId() {
      return this.cpId;
   }

   public void setCpId(String cpId) {
      this.cpId = cpId;
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

   public String getOrderFreq() {
      return this.orderFreq;
   }

   public void setOrderFreq(String orderFreq) {
      this.orderFreq = orderFreq;
   }

   public String getOrderUsageWayName() {
      return this.orderUsageWayName;
   }

   public void setOrderUsageWayName(String orderUsageWayName) {
      this.orderUsageWayName = orderUsageWayName;
   }

   public String getOrderFreqName() {
      return this.orderFreqName;
   }

   public void setOrderFreqName(String orderFreqName) {
      this.orderFreqName = orderFreqName;
   }

   public Integer getFreqValue() {
      return this.freqValue;
   }

   public void setFreqValue(Integer freqValue) {
      this.freqValue = freqValue;
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

   public BigDecimal getFirstPerformNum() {
      return this.firstPerformNum;
   }

   public void setFirstPerformNum(BigDecimal firstPerformNum) {
      this.firstPerformNum = firstPerformNum;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getAdditionalFlag() {
      return this.additionalFlag;
   }

   public void setAdditionalFlag(String additionalFlag) {
      this.additionalFlag = additionalFlag;
   }

   public String getUrgentFlag() {
      return this.urgentFlag;
   }

   public void setUrgentFlag(String urgentFlag) {
      this.urgentFlag = urgentFlag;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
   }

   public BigDecimal getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(BigDecimal orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public String getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setPurposeAntimicrobialUse(String purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public BigDecimal getOrderTotal() {
      return this.orderTotal;
   }

   public void setOrderTotal(BigDecimal orderTotal) {
      this.orderTotal = orderTotal;
   }

   public String getOrderFlagCd() {
      return this.orderFlagCd;
   }

   public void setOrderFlagCd(String orderFlagCd) {
      this.orderFlagCd = orderFlagCd;
   }

   public String getOrderFlagName() {
      return this.orderFlagName;
   }

   public void setOrderFlagName(String orderFlagName) {
      this.orderFlagName = orderFlagName;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getPerformComputerIp() {
      return this.performComputerIp;
   }

   public void setPerformComputerIp(String performComputerIp) {
      this.performComputerIp = performComputerIp;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(String orderItemType) {
      this.orderItemType = orderItemType;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
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

   public Integer getReOrganizationNo() {
      return this.reOrganizationNo;
   }

   public void setReOrganizationNo(Integer reOrganizationNo) {
      this.reOrganizationNo = reOrganizationNo;
   }

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public String getPrescribeNo() {
      return this.prescribeNo;
   }

   public void setPrescribeNo(String prescribeNo) {
      this.prescribeNo = prescribeNo;
   }

   public String getPrescribeName() {
      return this.prescribeName;
   }

   public void setPrescribeName(String prescribeName) {
      this.prescribeName = prescribeName;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getHerbalIntr() {
      return this.herbalIntr;
   }

   public void setHerbalIntr(String herbalIntr) {
      this.herbalIntr = herbalIntr;
   }

   public String getDecoctMethod() {
      return this.decoctMethod;
   }

   public void setDecoctMethod(String decoctMethod) {
      this.decoctMethod = decoctMethod;
   }

   public String getPlasterFlag() {
      return this.plasterFlag;
   }

   public void setPlasterFlag(String plasterFlag) {
      this.plasterFlag = plasterFlag;
   }

   public String getSourceOrderNo() {
      return this.sourceOrderNo;
   }

   public void setSourceOrderNo(String sourceOrderNo) {
      this.sourceOrderNo = sourceOrderNo;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public String getHighRiskFlag() {
      return this.highRiskFlag;
   }

   public void setHighRiskFlag(String highRiskFlag) {
      this.highRiskFlag = highRiskFlag;
   }

   public String getReferralHospital() {
      return this.referralHospital;
   }

   public void setReferralHospital(String referralHospital) {
      this.referralHospital = referralHospital;
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

   public String getChangeClass() {
      return this.changeClass;
   }

   public void setChangeClass(String changeClass) {
      this.changeClass = changeClass;
   }

   public String getChangeReason() {
      return this.changeReason;
   }

   public void setChangeReason(String changeReason) {
      this.changeReason = changeReason;
   }

   public String getChangeRemark() {
      return this.changeRemark;
   }

   public void setChangeRemark(String changeRemark) {
      this.changeRemark = changeRemark;
   }

   public String getLcljNo() {
      return this.lcljNo;
   }

   public void setLcljNo(String lcljNo) {
      this.lcljNo = lcljNo;
   }

   public String getLcljName() {
      return this.lcljName;
   }

   public void setLcljName(String lcljName) {
      this.lcljName = lcljName;
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
