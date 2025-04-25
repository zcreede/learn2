package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderPdfListVo {
   private List herbalList;
   private String deptCd;
   private String deptName;
   private String bedNo;
   private String patientId;
   private String babyAdmissionNo;
   private String orderNo;
   private String orderType;
   private String orderClassCode;
   private String orderSortNumber;
   private String orderGroupNo;
   private String orderGroupSortNumber;
   private String orderStartTime;
   private String cpNo;
   private String cpName;
   private String cpName0;
   private String cpName1;
   private String cpName2;
   private String cpName4;
   private String cpName5;
   private BigDecimal orderActualUsage;
   private String usageUnit;
   private BigDecimal orderDose;
   private String unit;
   private String drippingSpeed;
   private String itemOrderUsageWay;
   private String itemOrderUsageWayName;
   private String itemOrderFreq;
   private String itemOrderFreqName;
   private String patientSelfDrugFlag;
   private String skinTestResults;
   private String doctorInstructions;
   private String submitDoctorNo;
   private String submitDoctorName;
   private String submitDoctorSignImg;
   private String orderAuditDoc;
   private String orderAuditDocName;
   private String orderAuditDocSignImg;
   @JsonFormat(
      pattern = "MM.dd",
      timezone = "GMT+8"
   )
   private String orderStopDate;
   private String orderStopTime;
   private String orderStopDoc;
   private String orderStopDocName;
   private String orderStopDocSignImg;
   private String orderStopNurse;
   private String orderStopNurseName;
   private String orderStopNurseSignImg;
   private Date orderCancelDate;
   private Date orderCancelTime;
   private String orderCancelDoc;
   private String orderCancelDocName;
   private String masterOrder;
   private String orderStatus;
   private String orderFlagCd;
   private String groupStr;
   private Date printDate;
   private String outHavaDrugFlag;
   private String standard;
   private String orderPerformNurse;
   private String orderPerformNurseName;
   private String orderPerformNurseSignImg;
   private String orderPerformTime;
   private BigDecimal herbalDose;
   private String oederDesc;
   private Integer reOrganizationNo;
   private String orderStartDoc;
   private String orderStartDocName;
   private String purposeAntimicrobialUse;
   private String purposeAntimicrobialUseName;
   private String examPart;
   private Date orderStopAuditTime;
   private String orderStopAuditDoc;
   private String orderStopAuditDocName;
   private String additionalFlag;
   private String patientName;
   private String sex;
   private String age;
   private String inpNo;
   private String recordNo;
   private Integer pageNum;
   private String hospital;
   private String caFlag;
   private String caType;

   public String getCpName0() {
      return this.cpName0;
   }

   public void setCpName0(String cpName0) {
      this.cpName0 = cpName0;
   }

   public String getAdditionalFlag() {
      return this.additionalFlag;
   }

   public void setAdditionalFlag(String additionalFlag) {
      this.additionalFlag = additionalFlag;
   }

   public Date getOrderStopAuditTime() {
      return this.orderStopAuditTime;
   }

   public void setOrderStopAuditTime(Date orderStopAuditTime) {
      this.orderStopAuditTime = orderStopAuditTime;
   }

   public String getOrderStopAuditDoc() {
      return this.orderStopAuditDoc;
   }

   public void setOrderStopAuditDoc(String orderStopAuditDoc) {
      this.orderStopAuditDoc = orderStopAuditDoc;
   }

   public String getOrderStopAuditDocName() {
      return this.orderStopAuditDocName;
   }

   public void setOrderStopAuditDocName(String orderStopAuditDocName) {
      this.orderStopAuditDocName = orderStopAuditDocName;
   }

   public String getCpName1() {
      return this.cpName1;
   }

   public void setCpName1(String cpName1) {
      this.cpName1 = cpName1;
   }

   public String getExamPart() {
      return this.examPart;
   }

   public void setExamPart(String examPart) {
      this.examPart = examPart;
   }

   public String getPurposeAntimicrobialUseName() {
      return this.purposeAntimicrobialUseName;
   }

   public void setPurposeAntimicrobialUseName(String purposeAntimicrobialUseName) {
      this.purposeAntimicrobialUseName = purposeAntimicrobialUseName;
   }

   public String getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setPurposeAntimicrobialUse(String purposeAntimicrobialUse) {
      this.purposeAntimicrobialUse = purposeAntimicrobialUse;
   }

   public String getOrderStartDoc() {
      return this.orderStartDoc;
   }

   public void setOrderStartDoc(String orderStartDoc) {
      this.orderStartDoc = orderStartDoc;
   }

   public String getOrderStartDocName() {
      return this.orderStartDocName;
   }

   public void setOrderStartDocName(String orderStartDocName) {
      this.orderStartDocName = orderStartDocName;
   }

   public Integer getReOrganizationNo() {
      return this.reOrganizationNo;
   }

   public void setReOrganizationNo(Integer reOrganizationNo) {
      this.reOrganizationNo = reOrganizationNo;
   }

   public String getOederDesc() {
      return this.oederDesc;
   }

   public void setOederDesc(String oederDesc) {
      this.oederDesc = oederDesc;
   }

   public BigDecimal getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(BigDecimal herbalDose) {
      this.herbalDose = herbalDose;
   }

   public String getOrderPerformNurse() {
      return this.orderPerformNurse;
   }

   public void setOrderPerformNurse(String orderPerformNurse) {
      this.orderPerformNurse = orderPerformNurse;
   }

   public String getOrderPerformNurseName() {
      return this.orderPerformNurseName;
   }

   public void setOrderPerformNurseName(String orderPerformNurseName) {
      this.orderPerformNurseName = orderPerformNurseName;
   }

   public String getOrderPerformNurseSignImg() {
      return this.orderPerformNurseSignImg;
   }

   public void setOrderPerformNurseSignImg(String orderPerformNurseSignImg) {
      this.orderPerformNurseSignImg = orderPerformNurseSignImg;
   }

   public String getOrderPerformTime() {
      return this.orderPerformTime;
   }

   public void setOrderPerformTime(String orderPerformTime) {
      this.orderPerformTime = orderPerformTime;
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

   public String getCpName4() {
      return this.cpName4;
   }

   public void setCpName4(String cpName4) {
      this.cpName4 = cpName4;
   }

   public String getCpName5() {
      return this.cpName5;
   }

   public void setCpName5(String cpName5) {
      this.cpName5 = cpName5;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getOutHavaDrugFlag() {
      return this.outHavaDrugFlag;
   }

   public void setOutHavaDrugFlag(String outHavaDrugFlag) {
      this.outHavaDrugFlag = outHavaDrugFlag;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard;
   }

   public Date getPrintDate() {
      return this.printDate;
   }

   public void setPrintDate(Date printDate) {
      this.printDate = printDate;
   }

   public String getOrderFlagCd() {
      return this.orderFlagCd;
   }

   public void setOrderFlagCd(String orderFlagCd) {
      this.orderFlagCd = orderFlagCd;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getOrderStatus() {
      return this.orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public String getGroupStr() {
      return this.groupStr;
   }

   public void setGroupStr(String groupStr) {
      this.groupStr = groupStr;
   }

   public String getMasterOrder() {
      return this.masterOrder;
   }

   public void setMasterOrder(String masterOrder) {
      this.masterOrder = masterOrder;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
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

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public String getOrderStartTime() {
      return this.orderStartTime;
   }

   public void setOrderStartTime(String orderStartTime) {
      this.orderStartTime = orderStartTime;
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

   public String getCpName2() {
      return this.cpName2;
   }

   public void setCpName2(String cpName2) {
      this.cpName2 = cpName2;
   }

   public BigDecimal getOrderActualUsage() {
      return this.orderActualUsage;
   }

   public void setOrderActualUsage(BigDecimal orderActualUsage) {
      this.orderActualUsage = orderActualUsage;
   }

   public String getDrippingSpeed() {
      return this.drippingSpeed;
   }

   public void setDrippingSpeed(String drippingSpeed) {
      this.drippingSpeed = drippingSpeed;
   }

   public String getSkinTestResults() {
      return this.skinTestResults;
   }

   public void setSkinTestResults(String skinTestResults) {
      this.skinTestResults = skinTestResults;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getItemOrderUsageWay() {
      return this.itemOrderUsageWay;
   }

   public void setItemOrderUsageWay(String itemOrderUsageWay) {
      this.itemOrderUsageWay = itemOrderUsageWay;
   }

   public String getItemOrderUsageWayName() {
      return this.itemOrderUsageWayName;
   }

   public void setItemOrderUsageWayName(String itemOrderUsageWayName) {
      this.itemOrderUsageWayName = itemOrderUsageWayName;
   }

   public String getItemOrderFreq() {
      return this.itemOrderFreq;
   }

   public void setItemOrderFreq(String itemOrderFreq) {
      this.itemOrderFreq = itemOrderFreq;
   }

   public String getItemOrderFreqName() {
      return this.itemOrderFreqName;
   }

   public void setItemOrderFreqName(String itemOrderFreqName) {
      this.itemOrderFreqName = itemOrderFreqName;
   }

   public String getSubmitDoctorNo() {
      return this.submitDoctorNo;
   }

   public void setSubmitDoctorNo(String submitDoctorNo) {
      this.submitDoctorNo = submitDoctorNo;
   }

   public String getSubmitDoctorName() {
      return this.submitDoctorName;
   }

   public void setSubmitDoctorName(String submitDoctorName) {
      this.submitDoctorName = submitDoctorName;
   }

   public String getSubmitDoctorSignImg() {
      return this.submitDoctorSignImg;
   }

   public void setSubmitDoctorSignImg(String submitDoctorSignImg) {
      this.submitDoctorSignImg = submitDoctorSignImg;
   }

   public String getOrderAuditDoc() {
      return this.orderAuditDoc;
   }

   public void setOrderAuditDoc(String orderAuditDoc) {
      this.orderAuditDoc = orderAuditDoc;
   }

   public String getOrderAuditDocName() {
      return this.orderAuditDocName;
   }

   public void setOrderAuditDocName(String orderAuditDocName) {
      this.orderAuditDocName = orderAuditDocName;
   }

   public String getOrderAuditDocSignImg() {
      return this.orderAuditDocSignImg;
   }

   public void setOrderAuditDocSignImg(String orderAuditDocSignImg) {
      this.orderAuditDocSignImg = orderAuditDocSignImg;
   }

   public String getOrderStopDate() {
      return this.orderStopDate;
   }

   public void setOrderStopDate(String orderStopDate) {
      this.orderStopDate = orderStopDate;
   }

   public String getOrderStopTime() {
      return this.orderStopTime;
   }

   public void setOrderStopTime(String orderStopTime) {
      this.orderStopTime = orderStopTime;
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

   public String getOrderStopDocSignImg() {
      return this.orderStopDocSignImg;
   }

   public void setOrderStopDocSignImg(String orderStopDocSignImg) {
      this.orderStopDocSignImg = orderStopDocSignImg;
   }

   public String getOrderStopNurse() {
      return this.orderStopNurse;
   }

   public void setOrderStopNurse(String orderStopNurse) {
      this.orderStopNurse = orderStopNurse;
   }

   public String getOrderStopNurseName() {
      return this.orderStopNurseName;
   }

   public void setOrderStopNurseName(String orderStopNurseName) {
      this.orderStopNurseName = orderStopNurseName;
   }

   public String getOrderStopNurseSignImg() {
      return this.orderStopNurseSignImg;
   }

   public void setOrderStopNurseSignImg(String orderStopNurseSignImg) {
      this.orderStopNurseSignImg = orderStopNurseSignImg;
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

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public String getPatientSelfDrugFlag() {
      return this.patientSelfDrugFlag;
   }

   public void setPatientSelfDrugFlag(String patientSelfDrugFlag) {
      this.patientSelfDrugFlag = patientSelfDrugFlag;
   }

   public Date getOrderCancelDate() {
      return this.orderCancelDate;
   }

   public void setOrderCancelDate(Date orderCancelDate) {
      this.orderCancelDate = orderCancelDate;
   }

   public Date getOrderCancelTime() {
      return this.orderCancelTime;
   }

   public void setOrderCancelTime(Date orderCancelTime) {
      this.orderCancelTime = orderCancelTime;
   }

   public String getOrderCancelDoc() {
      return this.orderCancelDoc;
   }

   public void setOrderCancelDoc(String orderCancelDoc) {
      this.orderCancelDoc = orderCancelDoc;
   }

   public String getOrderCancelDocName() {
      return this.orderCancelDocName;
   }

   public void setOrderCancelDocName(String orderCancelDocName) {
      this.orderCancelDocName = orderCancelDocName;
   }

   public List getHerbalList() {
      return this.herbalList;
   }

   public void setHerbalList(List herbalList) {
      this.herbalList = herbalList;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getAge() {
      return this.age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getRecordNo() {
      return this.recordNo;
   }

   public void setRecordNo(String recordNo) {
      this.recordNo = recordNo;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public String getHospital() {
      return this.hospital;
   }

   public void setHospital(String hospital) {
      this.hospital = hospital;
   }

   public String getCaFlag() {
      return this.caFlag;
   }

   public void setCaFlag(String caFlag) {
      this.caFlag = caFlag;
   }

   public String getCaType() {
      return this.caType;
   }

   public void setCaType(String caType) {
      this.caType = caType;
   }
}
