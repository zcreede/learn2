package com.emr.project.pat.domain.req;

import java.math.BigDecimal;

public class FeeDetailReq {
   private String queryType;
   private String groupType;
   private String firstType;
   private String firstTypeStatis;
   private String filingDateStart;
   private String filingDateEnd;
   private String admissionNo;
   private String chargeName;
   private String departmentNo;
   private String accountingDepartmentNo;
   private BigDecimal amount;

   public String getFirstTypeStatis() {
      return this.firstTypeStatis;
   }

   public void setFirstTypeStatis(String firstTypeStatis) {
      this.firstTypeStatis = firstTypeStatis;
   }

   public String getQueryType() {
      return this.queryType;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public String getGroupType() {
      return this.groupType;
   }

   public void setGroupType(String groupType) {
      this.groupType = groupType;
   }

   public String getFirstType() {
      return this.firstType;
   }

   public void setFirstType(String firstType) {
      this.firstType = firstType;
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

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getChargeName() {
      return this.chargeName;
   }

   public void setChargeName(String chargeName) {
      this.chargeName = chargeName;
   }

   public String getDepartmentNo() {
      return this.departmentNo;
   }

   public void setDepartmentNo(String departmentNo) {
      this.departmentNo = departmentNo;
   }

   public String getAccountingDepartmentNo() {
      return this.accountingDepartmentNo;
   }

   public void setAccountingDepartmentNo(String accountingDepartmentNo) {
      this.accountingDepartmentNo = accountingDepartmentNo;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }
}
