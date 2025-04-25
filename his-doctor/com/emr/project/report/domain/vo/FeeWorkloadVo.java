package com.emr.project.report.domain.vo;

import com.emr.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

public class FeeWorkloadVo extends BaseEntity {
   private String queryType;
   private String beginDateTime;
   private String endDateTime;
   private String departmentType;
   private BigDecimal total;
   private String staffCode;
   private String threeLevelCode;
   private String threeLevelName;
   private String staffName;
   private String billDeptCode;

   public String getDepartmentType() {
      return this.departmentType;
   }

   public void setDepartmentType(String departmentType) {
      this.departmentType = departmentType;
   }

   public String getBillDeptCode() {
      return this.billDeptCode;
   }

   public void setBillDeptCode(String billDeptCode) {
      this.billDeptCode = billDeptCode;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getStaffCode() {
      return this.staffCode;
   }

   public void setStaffCode(String staffCode) {
      this.staffCode = staffCode;
   }

   public String getThreeLevelCode() {
      return this.threeLevelCode;
   }

   public void setThreeLevelCode(String threeLevelCode) {
      this.threeLevelCode = threeLevelCode;
   }

   public String getThreeLevelName() {
      return this.threeLevelName;
   }

   public void setThreeLevelName(String threeLevelName) {
      this.threeLevelName = threeLevelName;
   }

   public String getStaffName() {
      return this.staffName;
   }

   public void setStaffName(String staffName) {
      this.staffName = staffName;
   }

   public String getQueryType() {
      return this.queryType;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public String getBeginDateTime() {
      return this.beginDateTime;
   }

   public void setBeginDateTime(String beginDateTime) {
      this.beginDateTime = beginDateTime;
   }

   public String getEndDateTime() {
      return this.endDateTime;
   }

   public void setEndDateTime(String endDateTime) {
      this.endDateTime = endDateTime;
   }
}
