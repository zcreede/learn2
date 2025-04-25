package com.emr.project.report.domain.req;

public class BeHospitalizedTableReq {
   private Integer pageSize;
   private Integer pageNum;
   private String caseNo;
   private String name;
   private String beginDate;
   private String endDate;

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

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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
}
