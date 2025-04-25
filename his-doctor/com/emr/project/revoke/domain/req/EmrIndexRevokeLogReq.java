package com.emr.project.revoke.domain.req;

public class EmrIndexRevokeLogReq {
   private String startTime;
   private String endTime;
   private String deptCode;
   private String searchStr;
   private String mrStatus;

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

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public String getMrStatus() {
      return this.mrStatus;
   }

   public void setMrStatus(String mrStatus) {
      this.mrStatus = mrStatus;
   }
}
