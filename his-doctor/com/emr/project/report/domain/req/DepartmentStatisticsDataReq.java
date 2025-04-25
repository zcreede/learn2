package com.emr.project.report.domain.req;

import java.io.Serializable;

public class DepartmentStatisticsDataReq implements Serializable {
   private String dateStart;
   private String dateEnd;
   private String costType;
   private String departmentType;

   public String getDateStart() {
      return this.dateStart;
   }

   public void setDateStart(String dateStart) {
      this.dateStart = dateStart;
   }

   public String getDateEnd() {
      return this.dateEnd;
   }

   public void setDateEnd(String dateEnd) {
      this.dateEnd = dateEnd;
   }

   public String getCostType() {
      return this.costType;
   }

   public void setCostType(String costType) {
      this.costType = costType;
   }

   public String getDepartmentType() {
      return this.departmentType;
   }

   public void setDepartmentType(String departmentType) {
      this.departmentType = departmentType;
   }
}
