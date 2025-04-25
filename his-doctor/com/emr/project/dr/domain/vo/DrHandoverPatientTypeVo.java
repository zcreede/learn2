package com.emr.project.dr.domain.vo;

import com.emr.project.dr.domain.DrHandoverPatientType;
import java.util.Date;

public class DrHandoverPatientTypeVo extends DrHandoverPatientType {
   private String deptCd;
   private Date beginTime;
   private Date endTime;

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public Date getBeginTime() {
      return this.beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return this.endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }
}
