package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.SealupRecord;

public class SealupRecordVo {
   private SealupRecord sealupRecord;
   private String scurePass;
   private Integer nums;

   public SealupRecord getSealupRecord() {
      return this.sealupRecord;
   }

   public void setSealupRecord(SealupRecord sealupRecord) {
      this.sealupRecord = sealupRecord;
   }

   public String getScurePass() {
      return this.scurePass;
   }

   public void setScurePass(String scurePass) {
      this.scurePass = scurePass;
   }

   public Integer getNums() {
      return this.nums;
   }

   public void setNums(Integer nums) {
      this.nums = nums;
   }
}
