package com.emr.project.report.domain.req;

public class LeaveHospitalizedTableReq {
   private Integer pageSize;
   private Integer pageNum;
   private String beginDate;
   private String endDate;
   private String status;
   private String residentNo;

   public String getResidentNo() {
      return this.residentNo;
   }

   public void setResidentNo(String residentNo) {
      this.residentNo = residentNo;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public String getBeginDate() {
      return this.beginDate;
   }

   public void setBeginDate(String beginDate) {
      this.beginDate = beginDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
