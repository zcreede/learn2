package com.emr.project.report.domain.dto;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class BeHospitalizedDTO {
   @Excel(
      name = "住院号"
   )
   private String caseNo;
   private String hospitalizedCount;
   @Excel(
      name = "床位号"
   )
   private String bedNo;
   @Excel(
      name = "姓名"
   )
   private String name;
   @Excel(
      name = "性别"
   )
   private String sexName;
   @Excel(
      name = "年龄"
   )
   private String personAge;
   @Excel(
      name = "费别"
   )
   private String expenseCategory;
   private String wardIn;
   private String wardOut;
   private String visitingStaffName;
   @Excel(
      name = "入院时间",
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date hospitalizedDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date entryDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date leaveHospitalDate;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date closingDate;

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public String getBedNo() {
      return this.bedNo;
   }

   public void setBedNo(String bedNo) {
      this.bedNo = bedNo;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getSexName() {
      return this.sexName;
   }

   public void setSexName(String sexName) {
      this.sexName = sexName;
   }

   public String getPersonAge() {
      return this.personAge;
   }

   public void setPersonAge(String personAge) {
      this.personAge = personAge;
   }

   public String getExpenseCategory() {
      return this.expenseCategory;
   }

   public void setExpenseCategory(String expenseCategory) {
      this.expenseCategory = expenseCategory;
   }

   public String getWardIn() {
      return this.wardIn;
   }

   public void setWardIn(String wardIn) {
      this.wardIn = wardIn;
   }

   public String getWardOut() {
      return this.wardOut;
   }

   public void setWardOut(String wardOut) {
      this.wardOut = wardOut;
   }

   public String getVisitingStaffName() {
      return this.visitingStaffName;
   }

   public void setVisitingStaffName(String visitingStaffName) {
      this.visitingStaffName = visitingStaffName;
   }

   public Date getHospitalizedDate() {
      return this.hospitalizedDate;
   }

   public void setHospitalizedDate(Date hospitalizedDate) {
      this.hospitalizedDate = hospitalizedDate;
   }

   public Date getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(Date entryDate) {
      this.entryDate = entryDate;
   }

   public Date getLeaveHospitalDate() {
      return this.leaveHospitalDate;
   }

   public void setLeaveHospitalDate(Date leaveHospitalDate) {
      this.leaveHospitalDate = leaveHospitalDate;
   }

   public Date getClosingDate() {
      return this.closingDate;
   }

   public void setClosingDate(Date closingDate) {
      this.closingDate = closingDate;
   }
}
