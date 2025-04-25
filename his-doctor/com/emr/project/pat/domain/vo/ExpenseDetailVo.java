package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.ExpenseDetail;

public class ExpenseDetailVo extends ExpenseDetail {
   private String caseNo;
   private String inpNo;
   private String filingDateStr;
   private String filingDateStart;
   private String filingDateEnd;
   private String physicianCode;
   private String physicianNo;
   private String physicianName;
   private String physicianDptName;
   private String physicianDptNo;
   private String executorDptNo;
   private String executorDptName;
   private String patientName;
   private String thirdName;
   private String firstType;
   private String accountingDepartmentNo;
   private String departmentName;
   private String operatorName;
   private String departmentNo;

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getDepartmentName() {
      return this.departmentName;
   }

   public void setDepartmentName(String departmentName) {
      this.departmentName = departmentName;
   }

   public String getAccountingDepartmentNo() {
      return this.accountingDepartmentNo;
   }

   public void setAccountingDepartmentNo(String accountingDepartmentNo) {
      this.accountingDepartmentNo = accountingDepartmentNo;
   }

   public String getFirstType() {
      return this.firstType;
   }

   public void setFirstType(String firstType) {
      this.firstType = firstType;
   }

   public String getThirdName() {
      return this.thirdName;
   }

   public void setThirdName(String thirdName) {
      this.thirdName = thirdName;
   }

   public String getPatientName() {
      return this.patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getFilingDateStr() {
      return this.filingDateStr;
   }

   public void setFilingDateStr(String filingDateStr) {
      this.filingDateStr = filingDateStr;
   }

   public String getFilingDateStart() {
      return this.filingDateStart;
   }

   public void setFilingDateStart(String filingDateStart) {
      this.filingDateStart = filingDateStart;
   }

   public String getFilingDateEnd() {
      return this.filingDateEnd;
   }

   public void setFilingDateEnd(String filingDateEnd) {
      this.filingDateEnd = filingDateEnd;
   }

   public String getPhysicianCode() {
      return this.physicianCode;
   }

   public void setPhysicianCode(String physicianCode) {
      this.physicianCode = physicianCode;
   }

   public String getPhysicianNo() {
      return this.physicianNo;
   }

   public void setPhysicianNo(String physicianNo) {
      this.physicianNo = physicianNo;
   }

   public String getPhysicianName() {
      return this.physicianName;
   }

   public void setPhysicianName(String physicianName) {
      this.physicianName = physicianName;
   }

   public String getPhysicianDptName() {
      return this.physicianDptName;
   }

   public void setPhysicianDptName(String physicianDptName) {
      this.physicianDptName = physicianDptName;
   }

   public String getPhysicianDptNo() {
      return this.physicianDptNo;
   }

   public void setPhysicianDptNo(String physicianDptNo) {
      this.physicianDptNo = physicianDptNo;
   }

   public String getExecutorDptNo() {
      return this.executorDptNo;
   }

   public void setExecutorDptNo(String executorDptNo) {
      this.executorDptNo = executorDptNo;
   }

   public String getExecutorDptName() {
      return this.executorDptName;
   }

   public void setExecutorDptName(String executorDptName) {
      this.executorDptName = executorDptName;
   }
}
