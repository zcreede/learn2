package com.emr.project.operation.domain.req;

import java.util.ArrayList;
import java.util.List;

public class RefundAccountReq {
   private List list = new ArrayList();
   private String name;
   private String admissionNo;
   private String caseNo;
   private String hospitalizedCount;
   private String expenseCategoryCode;
   private String staffNo;
   private String staffName;
   private String deptName;
   private String deptCode;
   private String printFlag;

   public String getPrintFlag() {
      return this.printFlag;
   }

   public void setPrintFlag(String printFlag) {
      this.printFlag = printFlag;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getStaffNo() {
      return this.staffNo;
   }

   public void setStaffNo(String staffNo) {
      this.staffNo = staffNo;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getExpenseCategoryCode() {
      return this.expenseCategoryCode;
   }

   public void setExpenseCategoryCode(String expenseCategoryCode) {
      this.expenseCategoryCode = expenseCategoryCode;
   }

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }
}
