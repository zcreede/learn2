package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderOperationLog extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医嘱编码"
   )
   private String orderNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @Excel(
      name = "医嘱组号"
   )
   private String orderGroupNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "操作时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date operationTime;
   @Excel(
      name = "操作类型(1,审核；2,处理; 3,执行; 4,停止; 5,停止确认; 9,取消;7,取消确认;11.执行确认;12:作废;13:作废恢复)"
   )
   private Integer operationType;
   @Excel(
      name = "操作类型名称"
   )
   private String operationName;
   @Excel(
      name = "操作人内码"
   )
   private String operatorCode;
   @Excel(
      name = "操作人编码"
   )
   private String operatorNo;
   @Excel(
      name = "操作人姓名"
   )
   private String operatorName;
   @Excel(
      name = "操作说明信息(取消原因等)"
   )
   private String operatorDesc;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOperationTime(Date operationTime) {
      this.operationTime = operationTime;
   }

   public Date getOperationTime() {
      return this.operationTime;
   }

   public void setOperationType(Integer operationType) {
      this.operationType = operationType;
   }

   public Integer getOperationType() {
      return this.operationType;
   }

   public void setOperationName(String operationName) {
      this.operationName = operationName;
   }

   public String getOperationName() {
      return this.operationName;
   }

   public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
   }

   public String getOperatorCode() {
      return this.operatorCode;
   }

   public void setOperatorNo(String operatorNo) {
      this.operatorNo = operatorNo;
   }

   public String getOperatorNo() {
      return this.operatorNo;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public String getOperatorName() {
      return this.operatorName;
   }

   public void setOperatorDesc(String operatorDesc) {
      this.operatorDesc = operatorDesc;
   }

   public String getOperatorDesc() {
      return this.operatorDesc;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orderNo", this.getOrderNo()).append("orderSortNumber", this.getOrderSortNumber()).append("orderGroupNo", this.getOrderGroupNo()).append("operationTime", this.getOperationTime()).append("operationType", this.getOperationType()).append("operationName", this.getOperationName()).append("operatorCode", this.getOperatorCode()).append("operatorNo", this.getOperatorNo()).append("operatorName", this.getOperatorName()).append("operatorDesc", this.getOperatorDesc()).toString();
   }
}
