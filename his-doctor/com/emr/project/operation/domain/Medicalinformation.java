package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Medicalinformation extends MedicalinformationKey {
   private String patientId;
   private String caseNo;
   private String name;
   private Integer hospitalizedCount;
   private String tel;
   private String departmentNo;
   private String wardNo;
   private String wardName;
   private String bedNo;
   private String bedid;
   private BigDecimal bedSurcharge;
   private Date bedChargeDate;
   private String hospitalWay;
   private String hospitalCondition;
   private String levelCare;
   private String levelName;
   private String archiaterNo;
   private String archiaterName;
   private String visitingStaffNo;
   private String visitingStaffName;
   private String supervisorNurseNo;
   private String supervisorNurseName;
   private Date hospitalizedDate;
   private Date leaveHospitalDate;
   private String hospitalMark;
   private String leaveHospitalCondition;
   private String dischargeDepartmentNo;
   private String dischargeWardNo;
   private Date closingDate;
   private String outpatientDiagnosis;
   private String outpatientDoctor;
   private String admittingDiagnosis;
   private String leaveHospitalDiagnosis;
   private Date entryDate;
   private Date actualDischargeTime;
   private String singleDiseaseMark;
   private String inpatArrearageMark;
   private String approver;
   private String bloodPressure;
   private String bingqingDq;
   private String dzblBmjb;
   private String gms;
   private String medicalGroup;
   private String residentCode;
   private String residentNo;
   private String residentName;
   private String nursingGroup;
   private String supervisorNurseCode;
   private String daycaseFlag;
   private String followType;
   private String followFreq;
   private String returnVisitFlag;
   private Date returnVisitDate;
   private String cpNo;
   private String cpName;
   private String borrowBedMark;
   private String shareWardNo;
   private String archiaterCode;
   private String visitingStaffCode;
   private String illness;
   private String days;
   private String areaCode;
   private String areaName;
   private String deptCd;
   private String deptName;
   private String cpFlag;

   public String getAreaCode() {
      return this.areaCode;
   }

   public void setAreaCode(String areaCode) {
      this.areaCode = areaCode;
   }

   public String getAreaName() {
      return this.areaName;
   }

   public void setAreaName(String areaName) {
      this.areaName = areaName;
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

   public String getLevelName() {
      return this.levelName;
   }

   public void setLevelName(String levelName) {
      this.levelName = levelName;
   }

   public String getDays() {
      return this.days;
   }

   public void setDays(String days) {
      this.days = days;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId == null ? null : patientId.trim();
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo == null ? null : caseNo.trim();
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name == null ? null : name.trim();
   }

   public Integer getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(Integer hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getTel() {
      return this.tel;
   }

   public void setTel(String tel) {
      this.tel = tel == null ? null : tel.trim();
   }

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo == null ? null : departmentNo.trim();
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo == null ? null : wardNo.trim();
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo == null ? null : bedNo.trim();
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid == null ? null : bedid.trim();
   }

   public BigDecimal getBedSurcharge() {
      return this.bedSurcharge;
   }

   public void setBedSurcharge(BigDecimal bedSurcharge) {
      this.bedSurcharge = bedSurcharge;
   }

   public Date getBedChargeDate() {
      return this.bedChargeDate;
   }

   public void setBedChargeDate(Date bedChargeDate) {
      this.bedChargeDate = bedChargeDate;
   }

   public String getHospitalWay() {
      return this.hospitalWay;
   }

   public void setHospitalWay(String hospitalWay) {
      this.hospitalWay = hospitalWay == null ? null : hospitalWay.trim();
   }

   public String getHospitalCondition() {
      return this.hospitalCondition;
   }

   public void setHospitalCondition(String hospitalCondition) {
      this.hospitalCondition = hospitalCondition == null ? null : hospitalCondition.trim();
   }

   public String getLevelCare() {
      return this.levelCare;
   }

   public void setLevelCare(String levelCare) {
      this.levelCare = levelCare == null ? null : levelCare.trim();
   }

   public String getArchiaterNo() {
      return this.archiaterNo;
   }

   public void setArchiaterNo(String archiaterNo) {
      this.archiaterNo = archiaterNo == null ? null : archiaterNo.trim();
   }

   public String getArchiaterName() {
      return this.archiaterName;
   }

   public void setArchiaterName(String archiaterName) {
      this.archiaterName = archiaterName == null ? null : archiaterName.trim();
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

   public String getSupervisorNurseNo() {
      return this.supervisorNurseNo;
   }

   public void setSupervisorNurseNo(String supervisorNurseNo) {
      this.supervisorNurseNo = supervisorNurseNo == null ? null : supervisorNurseNo.trim();
   }

   public String getSupervisorNurseName() {
      return this.supervisorNurseName;
   }

   public void setSupervisorNurseName(String supervisorNurseName) {
      this.supervisorNurseName = supervisorNurseName == null ? null : supervisorNurseName.trim();
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public String getHospitalMark() {
      return this.hospitalMark;
   }

   public void setHospitalMark(String hospitalMark) {
      this.hospitalMark = hospitalMark == null ? null : hospitalMark.trim();
   }

   public String getLeaveHospitalCondition() {
      return this.leaveHospitalCondition;
   }

   public void setLeaveHospitalCondition(String leaveHospitalCondition) {
      this.leaveHospitalCondition = leaveHospitalCondition == null ? null : leaveHospitalCondition.trim();
   }

   public String getDischargeDepartmentNo() {
      return this.dischargeDepartmentNo;
   }

   public void setDischargeDepartmentNo(String dischargeDepartmentNo) {
      this.dischargeDepartmentNo = dischargeDepartmentNo == null ? null : dischargeDepartmentNo.trim();
   }

   public String getDischargeWardNo() {
      return this.dischargeWardNo;
   }

   public void setDischargeWardNo(String dischargeWardNo) {
      this.dischargeWardNo = dischargeWardNo == null ? null : dischargeWardNo.trim();
   }

   public Date getClosingDate() {
      return this.closingDate;
   }

   public void setClosingDate(Date closingDate) {
      this.closingDate = closingDate;
   }

   public String getOutpatientDiagnosis() {
      return this.outpatientDiagnosis;
   }

   public void setOutpatientDiagnosis(String outpatientDiagnosis) {
      this.outpatientDiagnosis = outpatientDiagnosis == null ? null : outpatientDiagnosis.trim();
   }

   public String getOutpatientDoctor() {
      return this.outpatientDoctor;
   }

   public void setOutpatientDoctor(String outpatientDoctor) {
      this.outpatientDoctor = outpatientDoctor == null ? null : outpatientDoctor.trim();
   }

   public String getAdmittingDiagnosis() {
      return this.admittingDiagnosis;
   }

   public void setAdmittingDiagnosis(String admittingDiagnosis) {
      this.admittingDiagnosis = admittingDiagnosis == null ? null : admittingDiagnosis.trim();
   }

   public String getLeaveHospitalDiagnosis() {
      return this.leaveHospitalDiagnosis;
   }

   public void setLeaveHospitalDiagnosis(String leaveHospitalDiagnosis) {
      this.leaveHospitalDiagnosis = leaveHospitalDiagnosis == null ? null : leaveHospitalDiagnosis.trim();
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public Date getActualDischargeTime() {
      return this.actualDischargeTime;
   }

   public void setActualDischargeTime(Date actualDischargeTime) {
      this.actualDischargeTime = actualDischargeTime;
   }

   public String getSingleDiseaseMark() {
      return this.singleDiseaseMark;
   }

   public void setSingleDiseaseMark(String singleDiseaseMark) {
      this.singleDiseaseMark = singleDiseaseMark == null ? null : singleDiseaseMark.trim();
   }

   public String getInpatArrearageMark() {
      return this.inpatArrearageMark;
   }

   public void setInpatArrearageMark(String inpatArrearageMark) {
      this.inpatArrearageMark = inpatArrearageMark == null ? null : inpatArrearageMark.trim();
   }

   public String getApprover() {
      return this.approver;
   }

   public void setApprover(String approver) {
      this.approver = approver == null ? null : approver.trim();
   }

   public String getBloodPressure() {
      return this.bloodPressure;
   }

   public void setBloodPressure(String bloodPressure) {
      this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
   }

   public String getBingqingDq() {
      return this.bingqingDq;
   }

   public void setBingqingDq(String bingqingDq) {
      this.bingqingDq = bingqingDq == null ? null : bingqingDq.trim();
   }

   public String getDzblBmjb() {
      return this.dzblBmjb;
   }

   public void setDzblBmjb(String dzblBmjb) {
      this.dzblBmjb = dzblBmjb == null ? null : dzblBmjb.trim();
   }

   public String getGms() {
      return this.gms;
   }

   public void setGms(String gms) {
      this.gms = gms == null ? null : gms.trim();
   }

   public String getMedicalGroup() {
      return this.medicalGroup;
   }

   public void setMedicalGroup(String medicalGroup) {
      this.medicalGroup = medicalGroup == null ? null : medicalGroup.trim();
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

   public String getNursingGroup() {
      return this.nursingGroup;
   }

   public void setNursingGroup(String nursingGroup) {
      this.nursingGroup = nursingGroup == null ? null : nursingGroup.trim();
   }

   public String getSupervisorNurseCode() {
      return this.supervisorNurseCode;
   }

   public void setSupervisorNurseCode(String supervisorNurseCode) {
      this.supervisorNurseCode = supervisorNurseCode == null ? null : supervisorNurseCode.trim();
   }

   public String getDaycaseFlag() {
      return this.daycaseFlag;
   }

   public void setDaycaseFlag(String daycaseFlag) {
      this.daycaseFlag = daycaseFlag == null ? null : daycaseFlag.trim();
   }

   public String getFollowType() {
      return this.followType;
   }

   public void setFollowType(String followType) {
      this.followType = followType == null ? null : followType.trim();
   }

   public String getFollowFreq() {
      return this.followFreq;
   }

   public void setFollowFreq(String followFreq) {
      this.followFreq = followFreq == null ? null : followFreq.trim();
   }

   public String getReturnVisitFlag() {
      return this.returnVisitFlag;
   }

   public void setReturnVisitFlag(String returnVisitFlag) {
      this.returnVisitFlag = returnVisitFlag == null ? null : returnVisitFlag.trim();
   }

   public Date getReturnVisitDate() {
      return this.returnVisitDate;
   }

   public void setReturnVisitDate(Date returnVisitDate) {
      this.returnVisitDate = returnVisitDate;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo == null ? null : cpNo.trim();
   }

   public String getCpName() {
      return this.cpName;
   }

   public void setCpName(String cpName) {
      this.cpName = cpName == null ? null : cpName.trim();
   }

   public String getBorrowBedMark() {
      return this.borrowBedMark;
   }

   public void setBorrowBedMark(String borrowBedMark) {
      this.borrowBedMark = borrowBedMark == null ? null : borrowBedMark.trim();
   }

   public String getShareWardNo() {
      return this.shareWardNo;
   }

   public void setShareWardNo(String shareWardNo) {
      this.shareWardNo = shareWardNo == null ? null : shareWardNo.trim();
   }

   public String getArchiaterCode() {
      return this.archiaterCode;
   }

   public void setArchiaterCode(String archiaterCode) {
      this.archiaterCode = archiaterCode == null ? null : archiaterCode.trim();
   }

   public String getVisitingStaffCode() {
      return this.visitingStaffCode;
   }

   public void setVisitingStaffCode(String visitingStaffCode) {
      this.visitingStaffCode = visitingStaffCode == null ? null : visitingStaffCode.trim();
   }

   public String getIllness() {
      return this.illness;
   }

   public void setIllness(String illness) {
      this.illness = illness;
   }

   public String getWardName() {
      return this.wardName;
   }

   public void setWardName(String wardName) {
      this.wardName = wardName;
   }

   public String getCpFlag() {
      return this.cpFlag;
   }

   public void setCpFlag(String cpFlag) {
      this.cpFlag = cpFlag;
   }
}
