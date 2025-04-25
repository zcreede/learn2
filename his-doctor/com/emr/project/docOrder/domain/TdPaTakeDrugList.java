package com.emr.project.docOrder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class TdPaTakeDrugList {
   private Long id;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String takeDrugWardNo;
   private String patientName;
   private String bedid;
   private String orderNo;
   private String orderType;
   private String drugName;
   private String drugNamePym;
   private String orderStandard;
   private BigDecimal drugActualUsage;
   private String usageUnit;
   private BigDecimal orderPrice;
   private BigDecimal orderDose;
   private String orderUnit;
   private String drugForm;
   private String drugClassCode;
   private String drugStockNo;
   private String pharmacopoeiaNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date takeDrugTime;
   private String takeDrugOperator;
   private String takeDrugOperatorName;
   private String drugUsageWay;
   private String drugUsageWayCode;
   private String drugFreq;
   private String drugFreqCode;
   private String orderDoctorCode;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date drugEatTime;
   private Integer hygienicMaterialFlag;
   private Integer herbalDose;
   private Integer herbalFlag;
   private String outDrugFlag;
   private String patientDepCode;
   private String patientDepName;
   private String pharmacyNo;
   private Integer todayAccountTimes;
   private Integer performedTimes;
   private Integer purposeAntimicrobialUse;
   private String prescribeNo;
   private String prescribeName;
   private String cpNo;
   private String doctorInstructions;
   private String babyAdmissionNo;
   private String surgicalFormNo;
   private String remark;
   private Integer takeDrugStatus;
   private Integer returnDrugStatus;
   private Integer valid;
   private String pharmacyName;
   private String orderDoctorName;
   private String caseNo;
   private String upStatus;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public String getTakeDrugWardNo() {
      return this.takeDrugWardNo;
   }

   public void setTakeDrugWardNo(String takeDrugWardNo) {
      this.takeDrugWardNo = takeDrugWardNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
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

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName;
   }

   public String getDrugNamePym() {
      return this.drugNamePym;
   }

   public void setDrugNamePym(String drugNamePym) {
      this.drugNamePym = drugNamePym;
   }

   public String getOrderStandard() {
      return this.orderStandard;
   }

   public void setOrderStandard(String orderStandard) {
      this.orderStandard = orderStandard;
   }

   public BigDecimal getDrugActualUsage() {
      return this.drugActualUsage;
   }

   public void setDrugActualUsage(BigDecimal drugActualUsage) {
      this.drugActualUsage = drugActualUsage;
   }

   public String getUsageUnit() {
      return this.usageUnit;
   }

   public void setUsageUnit(String usageUnit) {
      this.usageUnit = usageUnit;
   }

   public BigDecimal getOrderPrice() {
      return this.orderPrice;
   }

   public void setOrderPrice(BigDecimal orderPrice) {
      this.orderPrice = orderPrice;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getOrderUnit() {
      return this.orderUnit;
   }

   public void setOrderUnit(String orderUnit) {
      this.orderUnit = orderUnit;
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm;
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode;
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }

   public String getPharmacopoeiaNo() {
      return this.pharmacopoeiaNo;
   }

   public void setPharmacopoeiaNo(String pharmacopoeiaNo) {
      this.pharmacopoeiaNo = pharmacopoeiaNo;
   }

   public Date getTakeDrugTime() {
      return this.takeDrugTime;
   }

   public void setTakeDrugTime(Date takeDrugTime) {
      this.takeDrugTime = takeDrugTime;
   }

   public String getTakeDrugOperator() {
      return this.takeDrugOperator;
   }

   public void setTakeDrugOperator(String takeDrugOperator) {
      this.takeDrugOperator = takeDrugOperator;
   }

   public String getTakeDrugOperatorName() {
      return this.takeDrugOperatorName;
   }

   public void setTakeDrugOperatorName(String takeDrugOperatorName) {
      this.takeDrugOperatorName = takeDrugOperatorName;
   }

   public String getDrugUsageWay() {
      return this.drugUsageWay;
   }

   public void setDrugUsageWay(String drugUsageWay) {
      this.drugUsageWay = drugUsageWay;
   }

   public String getDrugUsageWayCode() {
      return this.drugUsageWayCode;
   }

   public void setDrugUsageWayCode(String drugUsageWayCode) {
      this.drugUsageWayCode = drugUsageWayCode;
   }

   public String getDrugFreq() {
      return this.drugFreq;
   }

   public void setDrugFreq(String drugFreq) {
      this.drugFreq = drugFreq;
   }

   public String getDrugFreqCode() {
      return this.drugFreqCode;
   }

   public void setDrugFreqCode(String drugFreqCode) {
      this.drugFreqCode = drugFreqCode;
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode;
   }

   public Date getDrugEatTime() {
      return this.drugEatTime;
   }

   public void setDrugEatTime(Date drugEatTime) {
      this.drugEatTime = drugEatTime;
   }

   public Integer getHygienicMaterialFlag() {
      return this.hygienicMaterialFlag;
   }

   public void setHygienicMaterialFlag(Integer hygienicMaterialFlag) {
      this.hygienicMaterialFlag = hygienicMaterialFlag;
   }

   public Integer getHerbalDose() {
      return this.herbalDose;
   }

   public void setHerbalDose(Integer herbalDose) {
      this.herbalDose = herbalDose;
   }

   public Integer getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(Integer herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getOutDrugFlag() {
      return this.outDrugFlag;
   }

   public void setOutDrugFlag(String outDrugFlag) {
      this.outDrugFlag = outDrugFlag;
   }

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode;
   }

   public String getPatientDepName() {
      return this.patientDepName;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName;
   }

   public String getPharmacyNo() {
      return this.pharmacyNo;
   }

   public void setPharmacyNo(String pharmacyNo) {
      this.pharmacyNo = pharmacyNo;
   }

   public Integer getTodayAccountTimes() {
      return this.todayAccountTimes;
   }

   public void setTodayAccountTimes(Integer todayAccountTimes) {
      this.todayAccountTimes = todayAccountTimes;
   }

   public Integer getPerformedTimes() {
      return this.performedTimes;
   }

   public void setPerformedTimes(Integer performedTimes) {
      this.performedTimes = performedTimes;
   }

   public Integer getPurposeAntimicrobialUse() {
      return this.purposeAntimicrobialUse;
   }

   public void setPurposeAntimicrobialUse(Integer purposeAntimicrobialUse) {
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

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions;
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo;
   }

   public String getSurgicalFormNo() {
      return this.surgicalFormNo;
   }

   public void setSurgicalFormNo(String surgicalFormNo) {
      this.surgicalFormNo = surgicalFormNo;
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public Integer getTakeDrugStatus() {
      return this.takeDrugStatus;
   }

   public void setTakeDrugStatus(Integer takeDrugStatus) {
      this.takeDrugStatus = takeDrugStatus;
   }

   public Integer getReturnDrugStatus() {
      return this.returnDrugStatus;
   }

   public void setReturnDrugStatus(Integer returnDrugStatus) {
      this.returnDrugStatus = returnDrugStatus;
   }

   public Integer getValid() {
      return this.valid;
   }

   public void setValid(Integer valid) {
      this.valid = valid;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getUpStatus() {
      return this.upStatus;
   }

   public void setUpStatus(String upStatus) {
      this.upStatus = upStatus;
   }
}
