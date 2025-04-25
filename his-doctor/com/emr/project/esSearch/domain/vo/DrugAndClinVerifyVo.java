package com.emr.project.esSearch.domain.vo;

import com.emr.project.esSearch.domain.DrugAndClin;
import java.math.BigDecimal;

public class DrugAndClinVerifyVo extends DrugAndClin {
   private Boolean replaceFlag;
   private Boolean replacePerformDepFlag;
   private BigDecimal orderUsageDays;

   public BigDecimal getOrderUsageDays() {
      return this.orderUsageDays;
   }

   public void setOrderUsageDays(BigDecimal orderUsageDays) {
      this.orderUsageDays = orderUsageDays;
   }

   public Boolean getReplaceFlag() {
      return this.replaceFlag;
   }

   public void setReplaceFlag(Boolean replaceFlag) {
      this.replaceFlag = replaceFlag;
   }

   public Boolean getReplacePerformDepFlag() {
      return this.replacePerformDepFlag;
   }

   public void setReplacePerformDepFlag(Boolean replacePerformDepFlag) {
      this.replacePerformDepFlag = replacePerformDepFlag;
   }
}
