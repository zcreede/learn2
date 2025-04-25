package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.InpatientOrderUsageFee;

public class InpatientOrderUsageFeeVo extends InpatientOrderUsageFee {
   private String feeName;

   public String getFeeName() {
      return this.feeName;
   }

   public void setFeeName(String feeName) {
      this.feeName = feeName;
   }
}
