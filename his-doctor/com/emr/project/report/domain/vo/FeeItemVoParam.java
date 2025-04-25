package com.emr.project.report.domain.vo;

import com.emr.framework.web.domain.BaseEntity;

public class FeeItemVoParam extends BaseEntity {
   private String queryType;
   private String beginDateTime;
   private String endDateTime;
   private String billDeptCd;
   private String billDocCd;
   private String feeItemName;

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

   public String getBillDeptCd() {
      return this.billDeptCd;
   }

   public void setBillDeptCd(String billDeptCd) {
      this.billDeptCd = billDeptCd;
   }

   public String getBillDocCd() {
      return this.billDocCd;
   }

   public void setBillDocCd(String billDocCd) {
      this.billDocCd = billDocCd;
   }

   public String getFeeItemName() {
      return this.feeItemName;
   }

   public void setFeeItemName(String feeItemName) {
      this.feeItemName = feeItemName;
   }
}
