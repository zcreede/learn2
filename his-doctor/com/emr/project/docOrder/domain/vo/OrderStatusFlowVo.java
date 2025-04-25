package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderStatusFlowVo {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date date;
   private String docName;
   private String status;

   public Date getDate() {
      return this.date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getDocName() {
      return this.docName;
   }

   public void setDocName(String docName) {
      this.docName = docName;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
