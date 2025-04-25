package com.emr.project.docOrder.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TdPaOrderOperation extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "医嘱编号"
   )
   private String orderNo;
   @Excel(
      name = "医嘱组号"
   )
   private String orderGroupNo;
   @Excel(
      name = "医嘱序号"
   )
   private String orderSortNumber;
   @JsonFormat(
      pattern = "yyyy-MM-dd"
   )
   @Excel(
      name = "处理时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd"
   )
   private Date handleTime;
   @Excel(
      name = "有效标志"
   )
   private Integer valid;

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderGroupNo(String orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setHandleTime(Date handleTime) {
      this.handleTime = handleTime;
   }

   public Date getHandleTime() {
      return this.handleTime;
   }

   public void setValid(Integer valid) {
      this.valid = valid;
   }

   public Integer getValid() {
      return this.valid;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("orderNo", this.getOrderNo()).append("orderGroupNo", this.getOrderGroupNo()).append("orderSortNumber", this.getOrderSortNumber()).append("handleTime", this.getHandleTime()).append("valid", this.getValid()).toString();
   }
}
