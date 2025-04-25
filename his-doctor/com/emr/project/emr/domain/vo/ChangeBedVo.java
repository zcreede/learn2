package com.emr.project.emr.domain.vo;

import java.util.Date;
import java.util.Map;

public class ChangeBedVo {
   private String inpNo;
   private String deptCode;
   private String tiBedNo;
   private String toBedNo;
   private Date toTime;

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getTiBedNo() {
      return this.tiBedNo;
   }

   public void setTiBedNo(String tiBedNo) {
      this.tiBedNo = tiBedNo;
   }

   public String getToBedNo() {
      return this.toBedNo;
   }

   public void setToBedNo(String toBedNo) {
      this.toBedNo = toBedNo;
   }

   public Date getToTime() {
      return this.toTime;
   }

   public void setToTime(Date toTime) {
      this.toTime = toTime;
   }

   public void getChangeBedVo(Map map) {
      this.setInpNo(map.get("inp_no") != null ? map.get("inp_no").toString() : null);
      this.setTiBedNo(map.get("ti_bed_no") != null ? map.get("ti_bed_no").toString() : null);
      this.setToBedNo(map.get("to_bed_no") != null ? map.get("to_bed_no").toString() : null);
      this.setDeptCode(map.get("dept_code") != null ? map.get("dept_code").toString() : null);
      this.setToTime(map.get("to_time") != null ? (Date)map.get("to_time") : null);
   }
}
