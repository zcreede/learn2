package com.emr.project.borrowing.domain.req;

public class SaveApplyAgreeReq {
   private Long id;
   private Long borrowPeriod;
   private String conRemark;
   private String admissionNo;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getBorrowPeriod() {
      return this.borrowPeriod;
   }

   public void setBorrowPeriod(Long borrowPeriod) {
      this.borrowPeriod = borrowPeriod;
   }

   public String getConRemark() {
      return this.conRemark;
   }

   public void setConRemark(String conRemark) {
      this.conRemark = conRemark;
   }
}
