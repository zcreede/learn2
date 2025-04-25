package com.emr.project.report.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class PatientWorkloadResp {
   private String beginDate;
   private String endDate;
   private String hospitalName;
   private String admissionNo;
   private String caseNo;
   private Integer hospitalizedCount;
   private String name;
   private String expenseCategory;
   private String deptName;
   private String days;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   private String threeLevelAccounting;
   private String threeLevelName;
   private BigDecimal total;
   private BigDecimal amount;
   private String type;
   private Integer size;
   private String residentName;
   private String inhosDiag;

   public String getInhosDiag() {
      return this.inhosDiag;
   }

   public void setInhosDiag(String inhosDiag) {
      this.inhosDiag = inhosDiag;
   }

   public String getResidentName() {
      return this.residentName;
   }

   public void setResidentName(String residentName) {
      this.residentName = residentName;
   }

   public Integer getSize() {
      return this.size;
   }

   public void setSize(Integer size) {
      this.size = size;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(String beginDate) {
      this.beginDate = beginDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getHospitalName() {
      return this.hospitalName;
   }

   public void setHospitalName(String hospitalName) {
      this.hospitalName = hospitalName;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
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

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getDays() {
      return this.days;
   }

   public void setDays(String days) {
      this.days = days;
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

   public String getThreeLevelAccounting() {
      return this.threeLevelAccounting;
   }

   public void setThreeLevelAccounting(String threeLevelAccounting) {
      this.threeLevelAccounting = threeLevelAccounting;
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }
}
