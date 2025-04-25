package com.emr.project.report.domain.dto;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class LeaveHospitalizedTableDTO {
   @Excel(
      name = "住院号"
   )
   private String caseNo;
   private String admissionNo;
   @Excel(
      name = "姓名"
   )
   private String name;
   @Excel(
      name = "性别"
   )
   private String sex;
   @Excel(
      name = "年龄"
   )
   private String personAge;
   @Excel(
      name = "床位号"
   )
   private String bedid;
   @Excel(
      name = "主治医师"
   )
   private String visitingStaffName;
   @Excel(
      name = "门诊医生"
   )
   private String outpatientDoctorName;
   @Excel(
      name = "费别"
   )
   private String expenseCategory;
   @Excel(
      name = "总费用"
   )
   private BigDecimal totalFee;
   @Excel(
      name = "药费"
   )
   private BigDecimal drugFee;
   @Excel(
      name = "药费比例"
   )
   private BigDecimal drugFeeProportion;
   @Excel(
      name = "入院日期",
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date entryDate;
   @Excel(
      name = "出科日期",
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   @Excel(
      name = "住院天数"
   )
   private BigDecimal days;
   @Excel(
      name = "出院诊断"
   )
   private String leaveHospitalDiagnosis;
   @Excel(
      name = "入院诊断"
   )
   private String admittingDiagnosis;
   @Excel(
      name = "在院状态"
   )
   private String status;
   private String visitingStaffNo;
   private String wardNo;
   private String hospitalizedCount;
   private String hospitalCode;
   private String outpatientDoctor;

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getOutpatientDoctor() {
      return this.outpatientDoctor;
   }

   public void setOutpatientDoctor(String outpatientDoctor) {
      this.outpatientDoctor = outpatientDoctor;
   }

   public String getOutpatientDoctorName() {
      return this.outpatientDoctorName;
   }

   public void setOutpatientDoctorName(String outpatientDoctorName) {
      this.outpatientDoctorName = outpatientDoctorName;
   }

   public BigDecimal getDrugFeeProportion() {
      return this.drugFeeProportion;
   }

   public void setDrugFeeProportion(BigDecimal drugFeeProportion) {
      this.drugFeeProportion = drugFeeProportion;
   }

   public String getVisitingStaffNo() {
      return this.visitingStaffNo;
   }

   public void setVisitingStaffNo(String visitingStaffNo) {
      this.visitingStaffNo = visitingStaffNo;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public String getWardNo() {
      return this.wardNo;
   }

   public void setWardNo(String wardNo) {
      this.wardNo = wardNo;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getBedid() {
      return this.bedid;
   }

   public void setBedid(String bedid) {
      this.bedid = bedid;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public String getLeaveHospitalDiagnosis() {
      return this.leaveHospitalDiagnosis;
   }

   public void setLeaveHospitalDiagnosis(String leaveHospitalDiagnosis) {
      this.leaveHospitalDiagnosis = leaveHospitalDiagnosis;
   }

   public BigDecimal getTotalFee() {
      return this.totalFee;
   }

   public void setTotalFee(BigDecimal totalFee) {
      this.totalFee = totalFee;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

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
      this.hospitalCode = hospitalCode;
   }

   public BigDecimal getDays() {
      return this.days;
   }

   public void setDays(BigDecimal days) {
      this.days = days;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public String getAdmittingDiagnosis() {
      return this.admittingDiagnosis;
   }

   public void setAdmittingDiagnosis(String admittingDiagnosis) {
      this.admittingDiagnosis = admittingDiagnosis;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public BigDecimal getDrugFee() {
      return this.drugFee;
   }

   public void setDrugFee(BigDecimal drugFee) {
      this.drugFee = drugFee;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
