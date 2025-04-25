package com.emr.project.system.domain.req;

import java.io.Serializable;

public class WorkLoadReportReq implements Serializable {
   private String startDate;
   private String endDate;

   public String getStartDate() {
      return this.startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }
}
