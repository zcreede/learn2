package com.emr.project.borrowing.domain.req;

public class PatientOutReq {
   private String startTime;
   private String endTime;
   private String deptNo;
   private String searchStr;

   public String getStartTime() {
      return this.startTime;
   }

   public void setStartTime(String startTime) {
      this.startTime = startTime;
   }

   public String getEndTime() {
      return this.endTime;
   }

   public void setEndTime(String endTime) {
      this.endTime = endTime;
   }

   public String getDeptNo() {
      return this.deptNo;
   }

   public void setDeptNo(String deptNo) {
      this.deptNo = deptNo;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }
}
