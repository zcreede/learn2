package com.emr.project.report.domain.resp;

import com.emr.project.report.domain.FeeDayDataDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class FeeSummaryDayResp {
   private String hospitalName;
   private String admissionNo;
   private String caseNo;
   private String patientName;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   private String expenseCategory;
   private String deptName;
   private Integer hospitalizationDays;
   private List detailList;
   private String printUserName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date printDate;

   public String getHospitalName() {
      return this.hospitalName;
   }

   public void setHospitalName(String hospitalName) {
      this.hospitalName = hospitalName;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
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

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public Integer getHospitalizationDays() {
      return this.hospitalizationDays;
   }

   public void setHospitalizationDays(Integer hospitalizationDays) {
      this.hospitalizationDays = hospitalizationDays;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public String getPrintUserName() {
      return this.printUserName;
   }

   public void setPrintUserName(String printUserName) {
      this.printUserName = printUserName;
   }

   public Date getPrintDate() {
      return this.printDate;
   }

   public void setPrintDate(Date printDate) {
      this.printDate = printDate;
   }
}
