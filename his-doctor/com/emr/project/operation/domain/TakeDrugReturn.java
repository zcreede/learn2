package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TakeDrugReturn {
   private String serialNumber;
   private String serialNumberXh;
   private String hospitalCode;
   private String caseNo;
   private String patientId;
   private String admissionNo;
   private Integer hospitalizedCount;
   private String patientName;
   private String residentCode;
   private String residentNo;
   private String residentName;
   private String wardNo;
   private String wardName;
   private String chargeCode;
   private String drugStockNo;
   private String chargeName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private String operatorCode;
   private String operatorNo;
   private String operatorName;
   private Date operatorDate;
   private String billsNoOld;
   private String billsNoNew;
   private String operatorNewCode;
   private String operatorNewNo;
   private String operatorNewName;
   private Date operatorNewDate;
   private String babyNo;
   private String mark;
   private String applyNo;
   private String applyName;
   private String executorDptNo;
   private String pharmacyName;
   private String orderDoctorWardName;
   private String orderDoctorName;
   private String orderDeptName;
   private String physicianName;
   private String takeDrugWardNo;
   private String orderNo;
   private String orderSortNumber;
   private String orderGroupSortNumber;
   private Integer orderGroupNo;
   private String prescriptionOld;
   private String operatorDateTime;
   private BigDecimal orderDose;
   private String operatorNewDateTime;
   private String orderTypeStr;

   public String getOrderTypeStr() {
      return this.orderTypeStr;
   }

   public void setOrderTypeStr(String orderTypeStr) {
      this.orderTypeStr = orderTypeStr;
   }

   public String getOperatorDateTime() {
      return this.operatorDateTime;
   }

   public void setOperatorDateTime(String operatorDateTime) {
      this.operatorDateTime = operatorDateTime;
   }

   public BigDecimal getOrderDose() {
      return this.orderDose;
   }

   public void setOrderDose(BigDecimal orderDose) {
      this.orderDose = orderDose;
   }

   public String getOperatorNewDateTime() {
      return this.operatorNewDateTime;
   }

   public void setOperatorNewDateTime(String operatorNewDateTime) {
      this.operatorNewDateTime = operatorNewDateTime;
   }

   public String getOrderDeptName() {
      return this.orderDeptName;
   }

   public void setOrderDeptName(String orderDeptName) {
      this.orderDeptName = orderDeptName;
   }

   public String getPrescriptionOld() {
      return this.prescriptionOld;
   }

   public void setPrescriptionOld(String prescriptionOld) {
      this.prescriptionOld = prescriptionOld;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public String getOrderGroupSortNumber() {
      return this.orderGroupSortNumber;
   }

   public void setOrderGroupSortNumber(String orderGroupSortNumber) {
      this.orderGroupSortNumber = orderGroupSortNumber;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getTakeDrugWardNo() {
      return this.takeDrugWardNo;
   }

   public void setTakeDrugWardNo(String takeDrugWardNo) {
      this.takeDrugWardNo = takeDrugWardNo;
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName;
   }

   public String getOrderDoctorWardName() {
      return this.orderDoctorWardName;
   }

   public void setOrderDoctorWardName(String orderDoctorWardName) {
      this.orderDoctorWardName = orderDoctorWardName;
   }

   public String getOrderDoctorName() {
      return this.orderDoctorName;
   }

   public void setOrderDoctorName(String orderDoctorName) {
      this.orderDoctorName = orderDoctorName;
   }

   public String getSerialNumberXh() {
      return this.serialNumberXh;
   }

   public void setSerialNumberXh(String serialNumberXh) {
      this.serialNumberXh = serialNumberXh;
   }

   public String getPharmacyName() {
      return this.pharmacyName;
   }

   public void setPharmacyName(String pharmacyName) {
      this.pharmacyName = pharmacyName;
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo;
   }

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber == null ? null : serialNumber.trim();
   }

   public String getHospitalCode() {
      return this.hospitalCode;
   }

   public void setHospitalCode(String hospitalCode) {
      this.hospitalCode = hospitalCode == null ? null : hospitalCode.trim();
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo == null ? null : caseNo.trim();
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId == null ? null : patientId.trim();
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

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName == null ? null : patientName.trim();
   }

   public String getResidentCode() {
      return this.residentCode;
   }

   public void setResidentCode(String residentCode) {
      this.residentCode = residentCode == null ? null : residentCode.trim();
   }

   public String getResidentNo() {
      return this.residentNo;
   }

   public void setResidentNo(String residentNo) {
      this.residentNo = residentNo == null ? null : residentNo.trim();
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName == null ? null : residentName.trim();
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName == null ? null : wardName.trim();
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode == null ? null : chargeCode.trim();
   }

   public String getDrugStockNo() {
      return this.drugStockNo;
   }

   public void setDrugStockNo(String drugStockNo) {
      this.drugStockNo = drugStockNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName == null ? null : chargeName.trim();
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String standard) {
      this.standard = standard == null ? null : standard.trim();
   }

   public String getUnit() {
      return this.unit;
   }

   public void setUnit(String unit) {
      this.unit = unit == null ? null : unit.trim();
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BigDecimal getDose() {
      return this.dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode == null ? null : operatorCode.trim();
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo == null ? null : operatorNo.trim();
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName == null ? null : operatorName.trim();
   }

   public Date getOperatorDate() {
      return this.operatorDate;
   }

   public void setOperatorDate(Date operatorDate) {
      this.operatorDate = operatorDate;
   }

   public String getBillsNoOld() {
      return this.billsNoOld;
   }

   public void setBillsNoOld(String billsNoOld) {
      this.billsNoOld = billsNoOld == null ? null : billsNoOld.trim();
   }

   public String getBillsNoNew() {
      return this.billsNoNew;
   }

   public void setBillsNoNew(String billsNoNew) {
      this.billsNoNew = billsNoNew == null ? null : billsNoNew.trim();
   }

   public String getOperatorNewCode() {
      return this.operatorNewCode;
   }

   public void setOperatorNewCode(String operatorNewCode) {
      this.operatorNewCode = operatorNewCode == null ? null : operatorNewCode.trim();
   }

   public String getOperatorNewNo() {
      return this.operatorNewNo;
   }

   public void setOperatorNewNo(String operatorNewNo) {
      this.operatorNewNo = operatorNewNo == null ? null : operatorNewNo.trim();
   }

   public String getOperatorNewName() {
      return this.operatorNewName;
   }

   public void setOperatorNewName(String operatorNewName) {
      this.operatorNewName = operatorNewName == null ? null : operatorNewName.trim();
   }

   public Date getOperatorNewDate() {
      return this.operatorNewDate;
   }

   public void setOperatorNewDate(Date operatorNewDate) {
      this.operatorNewDate = operatorNewDate;
   }

   public String getBabyNo() {
      return this.babyNo;
   }

   public void setBabyNo(String babyNo) {
      this.babyNo = babyNo == null ? null : babyNo.trim();
   }

   public String getMark() {
      return this.mark;
   }

   public void setMark(String mark) {
      this.mark = mark == null ? null : mark.trim();
   }

   public String getApplyNo() {
      return this.applyNo;
   }

   public void setApplyNo(String applyNo) {
      this.applyNo = applyNo == null ? null : applyNo.trim();
   }

   public String getApplyName() {
      return this.applyName;
   }

   public void setApplyName(String applyName) {
      this.applyName = applyName == null ? null : applyName.trim();
   }
}
