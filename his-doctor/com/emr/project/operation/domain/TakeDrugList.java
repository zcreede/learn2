package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TakeDrugList extends TakeDrugListKey {
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
   private Date takeDrugTime;
   private String takeDrugOperator;
   private String takeDrugOperatorName;
   private String drugUsageWay;
   private String drugUsageWayCode;
   private String drugFreq;
   private String drugFreqCode;
   private String orderDoctorCode;
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
   private String deptName;
   private String applyNo;
   private String physicianDptNo;
   private String physicianDptName;
   private String sourceFlag;
   private String prescription;
   private String checkerCode;
   private String checkerName;
   private Date checkerTime;
   private String checkerDesc;
   private String checkerStatus;
   private String hospitalCode;

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode;
   }

   public String getPrescription() {
      return this.prescription;
   }

   public void setPrescription(String prescription) {
      this.prescription = prescription;
   }

   public String getCheckerCode() {
      return this.checkerCode;
   }

   public void setCheckerCode(String checkerCode) {
      this.checkerCode = checkerCode;
   }

   public String getCheckerName() {
      return this.checkerName;
   }

   public void setCheckerName(String checkerName) {
      this.checkerName = checkerName;
   }

   public Date getCheckerTime() {
      return this.checkerTime;
   }

   public void setCheckerTime(Date checkerTime) {
      this.checkerTime = checkerTime;
   }

   public String getCheckerDesc() {
      return this.checkerDesc;
   }

   public void setCheckerDesc(String checkerDesc) {
      this.checkerDesc = checkerDesc;
   }

   public String getCheckerStatus() {
      return this.checkerStatus;
   }

   public void setCheckerStatus(String checkerStatus) {
      this.checkerStatus = checkerStatus;
   }

   public String getSourceFlag() {
      return this.sourceFlag;
   }

   public void setSourceFlag(String sourceFlag) {
      this.sourceFlag = sourceFlag;
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo;
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName;
   }

   public String getApplyNo() {
      return this.applyNo;
   }

   public void setApplyNo(String applyNo) {
      this.applyNo = applyNo;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getTakeDrugOperatorName() {
      return this.takeDrugOperatorName;
   }

   public void setTakeDrugOperatorName(String takeDrugOperatorName) {
      this.takeDrugOperatorName = takeDrugOperatorName;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public TakeDrugList() {
   }

   public String getDrugUsageWayCode() {
      return this.drugUsageWayCode;
   }

   public void setDrugUsageWayCode(String drugUsageWayCode) {
      this.drugUsageWayCode = drugUsageWayCode;
   }

   public String getDrugFreqCode() {
      return this.drugFreqCode;
   }

   public void setDrugFreqCode(String drugFreqCode) {
      this.drugFreqCode = drugFreqCode;
   }

   public TakeDrugList(String performListNo, Integer performListSortNumber, String orderSortNumber, String orderGroupSortNumber, String orderGroupNo) {
      super(performListNo, performListSortNumber, orderSortNumber, orderGroupSortNumber, orderGroupNo);
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

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo == null ? null : admissionNo.trim();
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
      this.takeDrugWardNo = takeDrugWardNo == null ? null : takeDrugWardNo.trim();
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName == null ? null : patientName.trim();
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid == null ? null : bedid.trim();
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo == null ? null : orderNo.trim();
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType == null ? null : orderType.trim();
   }

   public String getDrugName() {
      return this.drugName;
   }

   public void setDrugName(String drugName) {
      this.drugName = drugName == null ? null : drugName.trim();
   }

   public String getDrugNamePym() {
      return this.drugNamePym;
   }

   public void setDrugNamePym(String drugNamePym) {
      this.drugNamePym = drugNamePym == null ? null : drugNamePym.trim();
   }

   public String getOrderStandard() {
      return this.orderStandard;
   }

   public void setOrderStandard(String orderStandard) {
      this.orderStandard = orderStandard == null ? null : orderStandard.trim();
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
      this.usageUnit = usageUnit == null ? null : usageUnit.trim();
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
      this.orderUnit = orderUnit == null ? null : orderUnit.trim();
   }

   public String getDrugForm() {
      return this.drugForm;
   }

   public void setDrugForm(String drugForm) {
      this.drugForm = drugForm == null ? null : drugForm.trim();
   }

   public String getDrugClassCode() {
      return this.drugClassCode;
   }

   public void setDrugClassCode(String drugClassCode) {
      this.drugClassCode = drugClassCode == null ? null : drugClassCode.trim();
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
      this.pharmacopoeiaNo = pharmacopoeiaNo == null ? null : pharmacopoeiaNo.trim();
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
      this.takeDrugOperator = takeDrugOperator == null ? null : takeDrugOperator.trim();
   }

   public String getDrugUsageWay() {
      return this.drugUsageWay;
   }

   public void setDrugUsageWay(String drugUsageWay) {
      this.drugUsageWay = drugUsageWay == null ? null : drugUsageWay.trim();
   }

   public String getDrugFreq() {
      return this.drugFreq;
   }

   public void setDrugFreq(String drugFreq) {
      this.drugFreq = drugFreq == null ? null : drugFreq.trim();
   }

   public String getOrderDoctorCode() {
      return this.orderDoctorCode;
   }

   public void setOrderDoctorCode(String orderDoctorCode) {
      this.orderDoctorCode = orderDoctorCode == null ? null : orderDoctorCode.trim();
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
      this.patientDepCode = patientDepCode == null ? null : patientDepCode.trim();
   }

   public String getPatientDepName() {
      return this.patientDepName;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName == null ? null : patientDepName.trim();
   }

   public String getPharmacyNo() {
      return this.pharmacyNo;
   }

   public void setPharmacyNo(String pharmacyNo) {
      this.pharmacyNo = pharmacyNo == null ? null : pharmacyNo.trim();
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
      this.prescribeNo = prescribeNo == null ? null : prescribeNo.trim();
   }

   public String getPrescribeName() {
      return this.prescribeName;
   }

   public void setPrescribeName(String prescribeName) {
      this.prescribeName = prescribeName == null ? null : prescribeName.trim();
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo == null ? null : cpNo.trim();
   }

   public String getDoctorInstructions() {
      return this.doctorInstructions;
   }

   public void setDoctorInstructions(String doctorInstructions) {
      this.doctorInstructions = doctorInstructions == null ? null : doctorInstructions.trim();
   }

   public String getBabyAdmissionNo() {
      return this.babyAdmissionNo;
   }

   public void setBabyAdmissionNo(String babyAdmissionNo) {
      this.babyAdmissionNo = babyAdmissionNo == null ? null : babyAdmissionNo.trim();
   }

   public String getSurgicalFormNo() {
      return this.surgicalFormNo;
   }

   public void setSurgicalFormNo(String surgicalFormNo) {
      this.surgicalFormNo = surgicalFormNo == null ? null : surgicalFormNo.trim();
   }

   public String getRemark() {
      return this.remark;
   }

   public void setRemark(String remark) {
      this.remark = remark == null ? null : remark.trim();
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

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUpStatus() {
      return this.upStatus;
   }

   public void setUpStatus(String upStatus) {
      this.upStatus = upStatus;
   }
}
