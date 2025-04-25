package com.emr.project.operation.domain.req;

public class NurseWorkloadReq {
   private Integer pageNum;
   private Integer pageSize;
   private String startDate;
   private String endDate;
   private String queryType;
   private String firstTypeStatis;
   private String admissionNo;
   private String nurseNo;
   private String returnFlag;
   private String selChargeNo;

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public String getSelChargeNo() {
      return this.selChargeNo;
   }

   public void setSelChargeNo(String selChargeNo) {
      this.selChargeNo = selChargeNo;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }

   public String getStartDate() {
      return this.startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getQueryType() {
      return this.queryType;
   }

   public String getFirstTypeStatis() {
      return this.firstTypeStatis;
   }

   public void setFirstTypeStatis(String firstTypeStatis) {
      this.firstTypeStatis = firstTypeStatis;
   }

   public void setQueryType(String queryType) {
      this.queryType = queryType;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getNurseNo() {
      return this.nurseNo;
   }

   public void setNurseNo(String nurseNo) {
      this.nurseNo = nurseNo;
   }

   public String getReturnFlag() {
      return this.returnFlag;
   }

   public void setReturnFlag(String returnFlag) {
      this.returnFlag = returnFlag;
   }
}
