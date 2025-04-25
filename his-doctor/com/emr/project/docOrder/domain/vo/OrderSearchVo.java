package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderSearchVo {
   private String orderClassCode01;
   private String masterOrder;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date orderStartTimeBegin;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date orderStartTimeEnd;
   private String commitFlag;
   private String orderstatusF1;
   private String isAsc;
   private String hospitalCode;
   private List mainOrderNoList;
   private List distinctOrderNoList;
   private String herbalFlag;
   private BigDecimal herbalDose;
   private List statusList;
   private String patientId;
   private String orderNo;
   private String babyAdmissionNo;
   private String orderSortNumber;
   private String orderGroupNo;
   private String orderGroupSortNumber;
   private String orderType;
   private String orderClassCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String orderStatus;
   private List orderStatusList;
   private String cpNo;
   private String cpName;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private String orderUsageWay;
   private String orderUsageWayName;
   private String orderFreq;
   private String orderFreqName;
   private BigDecimal orderDose;
   private String unit;
   private String firstPerformNum;
   private String drippingSpeed;
   private String drippingSpeedName;
   private String additionalFlag;
   private String urgentFlag;
   private String takeDrugMode;
   private String takeDrugModeName;
   private BigDecimal orderUsageDays;
   private String purposeAntimicrobialUse;
   private String purposeAntimicrobialUseName;
   private String doctorInstructions;
   private BigDecimal orderTotal;
   private String detailPerformDepCode;
   private String detailPerformDepName;
   private String orderItemType;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStopTime;
   private String orderDoctorNo;
   private String orderDoctorName;
   private String orderStopDoc;
   private String orderStopDocName;
   private String orderDoctorDepCode;
   private String orderDoctorDepName;
   private String orderAuditDeptCode;
   private String orderAuditDeptName;
   private String orderDoctorCode;
   private String orderDoctorWardNo;
   private String patientSelfDrugFlag;
   private String orderFlagCd;
   private String orderFlagName;
   private String orderItemFlag;
   private String orderItemFlagName;
   private String outHavaDrugFlag;
   private String highRiskFlag;
   @JsonFormat(
      pattern = "HH:mm",
      timezone = "GMT+8"
   )
   private Date cancelTime;
   private String stockNo;
   private String performDepCode;
   private String performDepName;
   private BigDecimal price;
   private Boolean stopTimeFlag = false;
   private String examPartCd;
   private String examPartName;
   private String specCd;
   private String specName;
   private String documentTypeNo;
   private String standard;
   private String drugForm;
   private String drugType;
   private String groupStr;
   private String applyFormNo;
   private String itemFlagCd;
   private String itemFlagName;
   private String skinTestResults;
   private String sqlStr;
   private String orderDesc;
   private String diagnosisCode;
   private String diagnosisName;
   private String medicalRecordDigest;
   private String purposeExamination;
   private String lcljCpNo;
   private String lcljCpName;
   private String cpStageCode;
   private String cpItemCode;
   private String changeClass;
   private String changeReason;
   private String changeReasonName;

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

   public String getOrderDesc() {
      return this.orderDesc;
   }

   public void setOrderDesc(String orderDesc) {
      this.orderDesc = orderDesc;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getSqlStr() {
      return this.sqlStr;
   }

   public void setSqlStr(String sqlStr) {
      this.sqlStr = sqlStr;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getItemFlagCd() {
      return this.itemFlagCd;
   }

   public void setItemFlagCd(String itemFlagCd) {
      this.itemFlagCd = itemFlagCd;
   }

   public String getItemFlagName() {
      return this.itemFlagName;
   }

   public void setItemFlagName(String itemFlagName) {
      this.itemFlagName = itemFlagName;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getGroupStr() {
      return this.groupStr;
   }

   public void setGroupStr(String groupStr) {
      this.groupStr = groupStr;
   }

   public List getOrderStatusList() {
      return this.orderStatusList;
   }

   public void setOrderStatusList(List orderStatusList) {
      this.orderStatusList = orderStatusList;
   }

   public String getPerformDepName() {
      return this.performDepName;
   }

   public void setPerformDepName(String performDepName) {
      this.performDepName = performDepName;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDrugType() {
      return this.drugType;
   }

   public void setDrugType(String drugType) {
      this.drugType = drugType;
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

   public Boolean getStopTimeFlag() {
      return this.stopTimeFlag;
   }

   public void setStopTimeFlag(Boolean stopTimeFlag) {
      this.stopTimeFlag = stopTimeFlag;
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getStockNo() {
      return this.stockNo;
   }

   public void setStockNo(String stockNo) {
      this.stockNo = stockNo;
   }

   public Date getCancelTime() {
      return this.cancelTime;
   }

   public void setCancelTime(Date cancelTime) {
      this.cancelTime = cancelTime;
   }

   public String getHighRiskFlag() {
      return this.highRiskFlag;
   }

   public void setHighRiskFlag(String highRiskFlag) {
      this.highRiskFlag = highRiskFlag;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPatientSelfDrugFlag(String patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public String getOrderDoctorWardNo() {
      return this.orderDoctorWardNo;
   }

   public void setOrderDoctorWardNo(String orderDoctorWardNo) {
      this.orderDoctorWardNo = orderDoctorWardNo;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
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

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
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

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
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

   public String getFirstPerformNum() {
      return this.firstPerformNum;
   }

   public void setFirstPerformNum(String firstPerformNum) {
      this.firstPerformNum = firstPerformNum;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getDrippingSpeedName() {
      return this.drippingSpeedName;
   }

   public void setDrippingSpeedName(String drippingSpeedName) {
      this.drippingSpeedName = drippingSpeedName;
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

   public String getTakeDrugModeName() {
      return this.takeDrugModeName;
   }

   public void setTakeDrugModeName(String takeDrugModeName) {
      this.takeDrugModeName = takeDrugModeName;
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

   public String getPurposeAntimicrobialUseName() {
      return this.purposeAntimicrobialUseName;
   }

   public void setPurposeAntimicrobialUseName(String purposeAntimicrobialUseName) {
      this.purposeAntimicrobialUseName = purposeAntimicrobialUseName;
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

   public Date getOrderStopTime() {
      return this.orderStopTime;
   }

   public void setOrderStopTime(Date orderStopTime) {
      this.orderStopTime = orderStopTime;
   }

   public String getOrderDoctorNo() {
      return this.orderDoctorNo;
   }

   public void setOrderDoctorNo(String orderDoctorNo) {
      this.orderDoctorNo = orderDoctorNo;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getOrderStopDoc() {
      return this.orderStopDoc;
   }

   public void setOrderStopDoc(String orderStopDoc) {
      this.orderStopDoc = orderStopDoc;
   }

   public String getOrderStopDocName() {
      return this.orderStopDocName;
   }

   public void setOrderStopDocName(String orderStopDocName) {
      this.orderStopDocName = orderStopDocName;
   }

   public String getOrderDoctorDepCode() {
      return this.orderDoctorDepCode;
   }

   public void setOrderDoctorDepCode(String orderDoctorDepCode) {
      this.orderDoctorDepCode = orderDoctorDepCode;
   }

   public String getOrderDoctorDepName() {
      return this.orderDoctorDepName;
   }

   public void setOrderDoctorDepName(String orderDoctorDepName) {
      this.orderDoctorDepName = orderDoctorDepName;
   }

   public String getOrderAuditDeptCode() {
      return this.orderAuditDeptCode;
   }

   public void setOrderAuditDeptCode(String orderAuditDeptCode) {
      this.orderAuditDeptCode = orderAuditDeptCode;
   }

   public String getOrderAuditDeptName() {
      return this.orderAuditDeptName;
   }

   public void setOrderAuditDeptName(String orderAuditDeptName) {
      this.orderAuditDeptName = orderAuditDeptName;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public Date getOrderStartTimeBegin() {
      return this.orderStartTimeBegin;
   }

   public void setOrderStartTimeBegin(Date orderStartTimeBegin) {
      this.orderStartTimeBegin = orderStartTimeBegin;
   }

   public Date getOrderStartTimeEnd() {
      return this.orderStartTimeEnd;
   }

   public void setOrderStartTimeEnd(Date orderStartTimeEnd) {
      this.orderStartTimeEnd = orderStartTimeEnd;
   }

   public String getCommitFlag() {
      return this.commitFlag;
   }

   public void setCommitFlag(String commitFlag) {
      this.commitFlag = commitFlag;
   }

   public String getOrderstatusF1() {
      return this.orderstatusF1;
   }

   public void setOrderstatusF1(String orderstatusF1) {
      this.orderstatusF1 = orderstatusF1;
   }

   public String getIsAsc() {
      return this.isAsc;
   }

   public void setIsAsc(String isAsc) {
      this.isAsc = isAsc;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getOrderClassCode01() {
      return this.orderClassCode01;
   }

   public void setOrderClassCode01(String orderClassCode01) {
      this.orderClassCode01 = orderClassCode01;
   }

   public List getDistinctOrderNoList() {
      return this.distinctOrderNoList;
   }

   public void setDistinctOrderNoList(List distinctOrderNoList) {
      this.distinctOrderNoList = distinctOrderNoList;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public List getMainOrderNoList() {
      return this.mainOrderNoList;
   }

   public void setMainOrderNoList(List mainOrderNoList) {
      this.mainOrderNoList = mainOrderNoList;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(String orderItemType) {
      this.orderItemType = orderItemType;
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

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }

   public String getOrderItemFlagName() {
      return this.orderItemFlagName;
   }

   public void setOrderItemFlagName(String orderItemFlagName) {
      this.orderItemFlagName = orderItemFlagName;
   }

   public List getStatusList() {
      return this.statusList;
   }

   public void setStatusList(List statusList) {
      this.statusList = statusList;
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

   public String getChangeReasonName() {
      return this.changeReasonName;
   }

   public void setChangeReasonName(String changeReasonName) {
      this.changeReasonName = changeReasonName;
   }
}
