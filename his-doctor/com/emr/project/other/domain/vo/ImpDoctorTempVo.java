package com.emr.project.other.domain.vo;

import com.emr.project.other.domain.ImpDoctorTemp;
import java.util.Date;

public class ImpDoctorTempVo extends ImpDoctorTemp {
   private Date impBegTimeStr;
   private Date impEndTimeStr;
   private String inpNo;
   private String impStatus;
   private String deptCd;

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getImpStatus() {
      return this.impStatus;
   }

   public void setImpStatus(String impStatus) {
      this.impStatus = impStatus;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public Date getImpBegTimeStr() {
      return this.impBegTimeStr;
   }

   public void setImpBegTimeStr(Date impBegTimeStr) {
      this.impBegTimeStr = impBegTimeStr;
   }

   public Date getImpEndTimeStr() {
      return this.impEndTimeStr;
   }

   public void setImpEndTimeStr(Date impEndTimeStr) {
      this.impEndTimeStr = impEndTimeStr;
   }
}
