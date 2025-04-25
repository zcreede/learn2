package com.emr.project.report.domain.resp;

import java.math.BigDecimal;

public class DeptWorkloadResp {
   private String beginDate;
   private String endDate;
   private String hospitalName;
   private String deptName;
   private String executorDptNo;
   private String executorDptName;
   private String threeLevelAccounting;
   private String threeLevelName;
   private BigDecimal total;
   private BigDecimal amount;
   private String type;
   private String departmentTypeStr;

   public String getDepartmentTypeStr() {
      return this.departmentTypeStr;
   }

   public void setDepartmentTypeStr(String departmentTypeStr) {
      this.departmentTypeStr = departmentTypeStr;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
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

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
