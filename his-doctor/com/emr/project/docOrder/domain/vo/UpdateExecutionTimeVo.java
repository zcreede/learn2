package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class UpdateExecutionTimeVo {
   private List orderNoList;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date updateExecutionTime;

   public List getOrderNoList() {
      return this.orderNoList;
   }

   public void setOrderNoList(List orderNoList) {
      this.orderNoList = orderNoList;
   }

   public Date getUpdateExecutionTime() {
      return this.updateExecutionTime;
   }

   public void setUpdateExecutionTime(Date updateExecutionTime) {
      this.updateExecutionTime = updateExecutionTime;
   }
}
