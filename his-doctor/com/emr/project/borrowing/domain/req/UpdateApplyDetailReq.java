package com.emr.project.borrowing.domain.req;

public class UpdateApplyDetailReq {
   private Long id;
   private Long borrowPeriod;
   private String borrowPurpose;

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

   public String getBorrowPurpose() {
      return this.borrowPurpose;
   }

   public void setBorrowPurpose(String borrowPurpose) {
      this.borrowPurpose = borrowPurpose;
   }
}
