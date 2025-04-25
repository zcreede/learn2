package com.emr.project.qc.domain.vo;

import com.emr.project.qc.domain.QcCheckElem;
import java.util.Date;

public class QcCheckElemVo extends QcCheckElem {
   private String contElemName;
   private Date inhosTime;
   private Date outTime;
   private Date nowDate;

   public Date getInhosTime() {
      return this.inhosTime;
   }

   public void setInhosTime(Date inhosTime) {
      this.inhosTime = inhosTime;
   }

   public Date getOutTime() {
      return this.outTime;
   }

   public void setOutTime(Date outTime) {
      this.outTime = outTime;
   }

   public Date getNowDate() {
      return this.nowDate;
   }

   public void setNowDate(Date nowDate) {
      this.nowDate = nowDate;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }
}
