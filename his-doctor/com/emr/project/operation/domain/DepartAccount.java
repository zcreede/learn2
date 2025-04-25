package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class DepartAccount {
   private String hospitalCode;
   private String caseNo;
   private String admissionNo;
   private String name;
   private Integer hospitalizedCount;
   private String serialNumber;
   private String serialNumberSub;
   private String operatorCode;
   private String operatorNo;
   private String operatorName;
   private Date operatorDate;
   private String wardNo;
   private String wardName;
   private String medicalGroupV;
   private String visitingStaffCode;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String medicalGroupP;
   private String physicianCode;
   private String physicianNo;
   private String physicianName;
   private String physicianDptNo;
   private String physicianDptName;
   private String nursingGroup;
   private String executorCode;
   private String executorNo;
   private String executorName;
   private String executorDptNo;
   private String executorDptName;
   private String accountingDepartmentNo;
   private String accountingDepartmentName;
   private String chargeCode;
   private String chargeNo;
   private String chargeName;
   private String standard;
   private String unit;
   private BigDecimal price;
   private BigDecimal dose;
   private BigDecimal total;
   private String operatorNewCode;
   private String operatorNewNo;
   private String operatorNewName;
   private Date operatorNewDate;
   private String prescription;
   private String mark;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getSerialNumber() {
      return this.serialNumber;
   }

   public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber == null ? null : serialNumber.trim();
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

   public String getMedicalGroupV() {
      return this.medicalGroupV;
   }

   public void setMedicalGroupV(String medicalGroupV) {
      this.medicalGroupV = medicalGroupV == null ? null : medicalGroupV.trim();
   }

   public String getVisitingStaffCode() {
      return this.visitingStaffCode;
   }

   public void setVisitingStaffCode(String visitingStaffCode) {
      this.visitingStaffCode = visitingStaffCode == null ? null : visitingStaffCode.trim();
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo == null ? null : visitingStaffNo.trim();
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName == null ? null : visitingStaffName.trim();
   }

   public String getMedicalGroupP() {
      return this.medicalGroupP;
   }

   public void setMedicalGroupP(String medicalGroupP) {
      this.medicalGroupP = medicalGroupP == null ? null : medicalGroupP.trim();
   }

   public String getPhysicianCode() {
      return this.physicianCode;
   }

   public void setPhysicianCode(String physicianCode) {
      this.physicianCode = physicianCode == null ? null : physicianCode.trim();
   }

   public String getPhysicianNo() {
      return this.physicianNo;
   }

   public void setPhysicianNo(String physicianNo) {
      this.physicianNo = physicianNo == null ? null : physicianNo.trim();
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName == null ? null : physicianName.trim();
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo == null ? null : physicianDptNo.trim();
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName == null ? null : physicianDptName.trim();
   }

   public String getNursingGroup() {
      return this.nursingGroup;
   }

   public void setNursingGroup(String nursingGroup) {
      this.nursingGroup = nursingGroup == null ? null : nursingGroup.trim();
   }

   public String getExecutorCode() {
      return this.executorCode;
   }

   public void setExecutorCode(String executorCode) {
      this.executorCode = executorCode == null ? null : executorCode.trim();
   }

   public String getExecutorNo() {
      return this.executorNo;
   }

   public void setExecutorNo(String executorNo) {
      this.executorNo = executorNo == null ? null : executorNo.trim();
   }

   public String getExecutorName() {
      return this.executorName;
   }

   public void setExecutorName(String executorName) {
      this.executorName = executorName == null ? null : executorName.trim();
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo == null ? null : executorDptNo.trim();
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName == null ? null : executorDptName.trim();
   }

   public String getAccountingDepartmentNo() {
      return this.accountingDepartmentNo;
   }

   public void setAccountingDepartmentNo(String accountingDepartmentNo) {
      this.accountingDepartmentNo = accountingDepartmentNo == null ? null : accountingDepartmentNo.trim();
   }

   public String getAccountingDepartmentName() {
      return this.accountingDepartmentName;
   }

   public void setAccountingDepartmentName(String accountingDepartmentName) {
      this.accountingDepartmentName = accountingDepartmentName == null ? null : accountingDepartmentName.trim();
   }

   public String getChargeCode() {
      return this.chargeCode;
   }

   public void setChargeCode(String chargeCode) {
      this.chargeCode = chargeCode == null ? null : chargeCode.trim();
   }

   public String getChargeNo() {
      return this.chargeNo;
   }

   public void setChargeNo(String chargeNo) {
      this.chargeNo = chargeNo == null ? null : chargeNo.trim();
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

   public String getPrescription() {
      return this.prescription;
   }

   public void setPrescription(String prescription) {
      this.prescription = prescription == null ? null : prescription.trim();
   }

   public String getMark() {
      return this.mark;
   }

   public void setMark(String mark) {
      this.mark = mark == null ? null : mark.trim();
   }

   public String getSerialNumberSub() {
      return this.serialNumberSub;
   }

   public void setSerialNumberSub(String serialNumberSub) {
      this.serialNumberSub = serialNumberSub;
   }
}
