package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderStopVo {
   private String orderNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date stopTime;

   public OrderStopVo() {
   }

   public OrderStopVo(String orderNo, Date stopTime) {
      this.orderNo = orderNo;
      this.stopTime = stopTime;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Date getStopTime() {
      return this.stopTime;
   }

   public void setStopTime(Date stopTime) {
      this.stopTime = stopTime;
   }
}
