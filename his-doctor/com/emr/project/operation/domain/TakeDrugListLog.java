package com.emr.project.operation.domain;

import java.util.Date;

public class TakeDrugListLog {
   private Long id;
   private String performListNo;
   private Integer performListSortNumber;
   private Integer operateType;
   private Date operateTime;
   private String operator;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPerformListNo() {
      return this.performListNo;
   }

   public void setPerformListNo(String performListNo) {
      this.performListNo = performListNo == null ? null : performListNo.trim();
   }

   public Integer getPerformListSortNumber() {
      return this.performListSortNumber;
   }

   public void setPerformListSortNumber(Integer performListSortNumber) {
      this.performListSortNumber = performListSortNumber;
   }

   public Integer getOperateType() {
      return this.operateType;
   }

   public void setOperateType(Integer operateType) {
      this.operateType = operateType;
   }

   public Date getOperateTime() {
      return this.operateTime;
   }

   public void setOperateTime(Date operateTime) {
      this.operateTime = operateTime;
   }

   public String getOperator() {
      return this.operator;
   }

   public void setOperator(String operator) {
      this.operator = operator == null ? null : operator.trim();
   }
}
