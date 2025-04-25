package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class InpatientOrderDTO {
   private String orderNo;
   private String orderSortNumber;
   private String statusString;
   private Integer status;
   private Integer orderStatus;
   private String orderStatusStr;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operationTime;
   private String operatorCode;
   private String bedNum;
   private String patientName;
   private String patientId;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStartTime;
   private String orderType;
   private String orderClassCode;
   private String orderGroupNo;
   private String orderGroupSortNumber;
   private String chargeName;
   private String chargeNo;
   private String standardCode;
   private String standardName;
   private String standard;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private String orderUsageWay;
   private String orderUsageWayName;
   private String orderFreq;
   private String orderFreqName;
   private BigDecimal orderDose;
   private String unit;
   private BigDecimal orderTotalDose;
   private String herbalFlag;
   private String drugForm;
   private String orderDoctorCode;
   private String orderDoctorNo;
   private String orderDoctorName;
   private BigDecimal price;
   private BigDecimal orderTotal;
   private String drippingSpeed;
   private String doctorInstructions;
   private BigDecimal herbalDose;
   private Integer patientSelfDrugFlag;
   private String takeDrugMode;
   private String detailPerformDepCode;
   private String detailPerformDepName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date orderStopTime;
   private String babyAdmissionNo;
   private Integer priceFlag;
   private String pharmacopoeiaNo;
   private String cpName;
   private String cpNo;
   private String hospitalCode;
   private String patientWardNo;
   private String patientDepCode;
   private String caseNo;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String orderDoctorWardNo;
   private String orderDoctorDepCode;
   private String orderDoctorDepName;
   private String documentTypeNo;
   private String cpNo1;
   private String cpName1;
   private String outHavaDrugFlag;
   private Integer orderItemType;
   private Integer firstDoubleFlag;
   private Integer subjectFlag;
   private Integer firstPerformNum;
   private BigDecimal firstPerformTotalDose;
   private String residentDoctorCode;
   private String residentDoctorNo;
   private String surgicalFormNo;
   private String stockNo;
   private Integer hygienicMaterialFlag;
   private Integer orderPerformFlag;
   private String performWardNo;
   private String performDepCode;
   private int purposeAntimicrobialUse;
   private String prescribeNo;
   private String prescribeName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date submitTime;
   private String patientDepName;
   private String patientWardName;
   private String residentDoctorName;
   private BigDecimal orderDoseItem;
   private String unitItem;
   private BigDecimal priceItem;
   private BigDecimal orderTotalItem;
   private String drugClassCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date klsj;
   private String zxks;
   private String klys;
   private String shhs;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date shsj;
   private String clhs;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date clsj;
   private String zxhs;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date zxsj;
   private String orderItemFlag;
   private String orderGroupStr;
   private boolean checked;
   private String orderFlagCd;
   private String applyFormNo;
   private String pharmacyNo;
   private Date orderStopAuditTime;
   private Date orderAuditTime;
   private Date orderDealTime;
   private Integer orderUsageDays;

   public Date getOrderStopAuditTime() {
      return this.orderStopAuditTime;
   }

   public void setOrderStopAuditTime(Date orderStopAuditTime) {
      this.orderStopAuditTime = orderStopAuditTime;
   }

   public Date getOrderAuditTime() {
      return this.orderAuditTime;
   }

   public void setOrderAuditTime(Date orderAuditTime) {
      this.orderAuditTime = orderAuditTime;
   }

   public Date getOrderDealTime() {
      return this.orderDealTime;
   }

   public void setOrderDealTime(Date orderDealTime) {
      this.orderDealTime = orderDealTime;
   }

   public Integer getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(Integer orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public String getPharmacyNo() {
      return this.pharmacyNo;
   }

   public void setPharmacyNo(String pharmacyNo) {
      this.pharmacyNo = pharmacyNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getOrderFlagCd() {
      return this.orderFlagCd;
   }

   public void setOrderFlagCd(String orderFlagCd) {
      this.orderFlagCd = orderFlagCd;
   }

   public boolean isChecked() {
      return this.checked;
   }

   public void setChecked(boolean checked) {
      this.checked = checked;
   }

   public String getOrderGroupStr() {
      return this.orderGroupStr;
   }

   public void setOrderGroupStr(String orderGroupStr) {
      this.orderGroupStr = orderGroupStr;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getStatusString() {
      return this.statusString;
   }

   public void setStatusString(String statusString) {
      this.statusString = statusString;
   }

   public Integer getStatus() {
      return this.status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public Integer getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(Integer orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getOrderStatusStr() {
      return this.orderStatusStr;
   }

   public void setOrderStatusStr(String orderStatusStr) {
      this.orderStatusStr = orderStatusStr;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getBedNum() {
      return this.bedNum;
   }

   public void setBedNum(String bedNum) {
      this.bedNum = bedNum;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Date getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(Date orderStartTime) {
      this.orderStartTime = orderStartTime;
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

   public BigDecimal getOrderTotalDose() {
      return this.orderTotalDose;
   }

   public void setOrderTotalDose(BigDecimal orderTotalDose) {
      this.orderTotalDose = orderTotalDose;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
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

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public Integer getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPatientSelfDrugFlag(Integer patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public String getTakeDrugMode() {
      return this.takeDrugMode;
   }

   public void setTakeDrugMode(String takeDrugMode) {
      this.takeDrugMode = takeDrugMode;
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

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
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

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getPatientWardNo() {
      return this.patientWardNo;
   }

   public void setPatientWardNo(String patientWardNo) {
      this.patientWardNo = patientWardNo;
   }

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getOrderDoctorWardNo() {
      return this.orderDoctorWardNo;
   }

   public void setOrderDoctorWardNo(String orderDoctorWardNo) {
      this.orderDoctorWardNo = orderDoctorWardNo;
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

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getCpNo1() {
      return this.cpNo1;
   }

   public void setCpNo1(String cpNo1) {
      this.cpNo1 = cpNo1;
   }

   public String getCpName1() {
      return this.cpName1;
   }

   public void setCpName1(String cpName1) {
      this.cpName1 = cpName1;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public Integer getOrderItemType() {
      return this.orderItemType;
   }

   public void setOrderItemType(Integer orderItemType) {
      this.orderItemType = orderItemType;
   }

   public Integer getFirstDoubleFlag() {
      return this.firstDoubleFlag;
   }

   public void setFirstDoubleFlag(Integer firstDoubleFlag) {
      this.firstDoubleFlag = firstDoubleFlag;
   }

   public Integer getSubjectFlag() {
      return this.subjectFlag;
   }

   public void setSubjectFlag(Integer subjectFlag) {
      this.subjectFlag = subjectFlag;
   }

   public Integer getFirstPerformNum() {
      return this.firstPerformNum;
   }

   public void setFirstPerformNum(Integer firstPerformNum) {
      this.firstPerformNum = firstPerformNum;
   }

   public BigDecimal getFirstPerformTotalDose() {
      return this.firstPerformTotalDose;
   }

   public void setFirstPerformTotalDose(BigDecimal firstPerformTotalDose) {
      this.firstPerformTotalDose = firstPerformTotalDose;
   }

   public String getResidentDoctorCode() {
      return this.residentDoctorCode;
   }

   public void setResidentDoctorCode(String residentDoctorCode) {
      this.residentDoctorCode = residentDoctorCode;
   }

   public String getResidentDoctorNo() {
      return this.residentDoctorNo;
   }

   public void setResidentDoctorNo(String residentDoctorNo) {
      this.residentDoctorNo = residentDoctorNo;
   }

   public String getSurgicalFormNo() {
      return this.surgicalFormNo;
   }

   public void setSurgicalFormNo(String surgicalFormNo) {
      this.surgicalFormNo = surgicalFormNo;
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

   public Integer getOrderPerformFlag() {
      return this.orderPerformFlag;
   }

   public void setOrderPerformFlag(Integer orderPerformFlag) {
      this.orderPerformFlag = orderPerformFlag;
   }

   public String getPerformWardNo() {
      return this.performWardNo;
   }

   public void setPerformWardNo(String performWardNo) {
      this.performWardNo = performWardNo;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public int getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setPurposeAntimicrobialUse(int purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
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

   public Date getSubmitTime() {
      return this.submitTime;
   }

   public void setSubmitTime(Date submitTime) {
      this.submitTime = submitTime;
   }

   public String getPatientDepName() {
      return this.patientDepName;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName;
   }

   public String getPatientWardName() {
      return this.patientWardName;
   }

   public void setPatientWardName(String patientWardName) {
      this.patientWardName = patientWardName;
   }

   public String getResidentDoctorName() {
      return this.residentDoctorName;
   }

   public void setResidentDoctorName(String residentDoctorName) {
      this.residentDoctorName = residentDoctorName;
   }

   public BigDecimal getOrderDoseItem() {
      return this.orderDoseItem;
   }

   public void setOrderDoseItem(BigDecimal orderDoseItem) {
      this.orderDoseItem = orderDoseItem;
   }

   public String getUnitItem() {
      return this.unitItem;
   }

   public void setUnitItem(String unitItem) {
      this.unitItem = unitItem;
   }

   public BigDecimal getPriceItem() {
      return this.priceItem;
   }

   public void setPriceItem(BigDecimal priceItem) {
      this.priceItem = priceItem;
   }

   public BigDecimal getOrderTotalItem() {
      return this.orderTotalItem;
   }

   public void setOrderTotalItem(BigDecimal orderTotalItem) {
      this.orderTotalItem = orderTotalItem;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public Date getKlsj() {
      return this.klsj;
   }

   public void setKlsj(Date klsj) {
      this.klsj = klsj;
   }

   public String getZxks() {
      return this.zxks;
   }

   public void setZxks(String zxks) {
      this.zxks = zxks;
   }

   public String getKlys() {
      return this.klys;
   }

   public void setKlys(String klys) {
      this.klys = klys;
   }

   public String getShhs() {
      return this.shhs;
   }

   public void setShhs(String shhs) {
      this.shhs = shhs;
   }

   public Date getShsj() {
      return this.shsj;
   }

   public void setShsj(Date shsj) {
      this.shsj = shsj;
   }

   public String getClhs() {
      return this.clhs;
   }

   public void setClhs(String clhs) {
      this.clhs = clhs;
   }

   public Date getClsj() {
      return this.clsj;
   }

   public void setClsj(Date clsj) {
      this.clsj = clsj;
   }

   public String getZxhs() {
      return this.zxhs;
   }

   public void setZxhs(String zxhs) {
      this.zxhs = zxhs;
   }

   public Date getZxsj() {
      return this.zxsj;
   }

   public void setZxsj(Date zxsj) {
      this.zxsj = zxsj;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }
}
