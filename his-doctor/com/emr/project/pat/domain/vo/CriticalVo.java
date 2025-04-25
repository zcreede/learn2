package com.emr.project.pat.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

public class CriticalVo {
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date inTime;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm:ss",
      timezone = "GMT+8"
   )
   private Date outTime;
   private BigDecimal hours;
   private String deptId;

   public String getDeptId() {
      return this.deptId;
   }

   public void setDeptId(String deptId) {
      this.deptId = deptId;
   }

   public Date getInTime() {
      return this.inTime;
   }

   public void setInTime(Date inTime) {
      this.inTime = inTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public BigDecimal getHours() {
      return this.hours;
   }

   public void setHours(BigDecimal hours) {
      this.hours = hours;
   }
}
