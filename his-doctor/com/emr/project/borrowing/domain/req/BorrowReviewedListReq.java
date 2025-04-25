package com.emr.project.borrowing.domain.req;

public class BorrowReviewedListReq {
   private String status;
   private String appStatus;
   private String appDocName;
   private String appDeptCd;
   private String searchStr;
   private String conDocName;
   private Integer dateType;
   private String startTime;
   private String endTime;
   private String deptCode;

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getAppStatus() {
      return this.appStatus;
   }

   public void setAppStatus(String appStatus) {
      this.appStatus = appStatus;
   }

   public String getAppDocName() {
      return this.appDocName;
   }

   public void setAppDocName(String appDocName) {
      this.appDocName = appDocName;
   }

   public String getAppDeptCd() {
      return this.appDeptCd;
   }

   public void setAppDeptCd(String appDeptCd) {
      this.appDeptCd = appDeptCd;
   }

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public String getConDocName() {
      return this.conDocName;
   }

   public void setConDocName(String conDocName) {
      this.conDocName = conDocName;
   }

   public Integer getDateType() {
      return this.dateType;
   }

   public void setDateType(Integer dateType) {
      this.dateType = dateType;
   }

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

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
