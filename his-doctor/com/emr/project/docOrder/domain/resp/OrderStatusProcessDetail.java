package com.emr.project.docOrder.domain.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderStatusProcessDetail {
   private String operationName;
   private String operationType;
   private String operatorName;
   private String operatorNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date operationTime;
   private Boolean flg;

   public OrderStatusProcessDetail() {
      this.flg = Boolean.FALSE;
   }

   public String getOperationType() {
      return this.operationType;
   }

   public void setOperationType(String operationType) {
      this.operationType = operationType;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public Boolean getFlg() {
      return this.flg;
   }

   public void setFlg(Boolean flg) {
      this.flg = flg;
   }
}
