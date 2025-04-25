package com.emr.project.pat.domain.vo;

import com.emr.project.pat.domain.DrgMrGroup;

public class DrgMrGroupVo extends DrgMrGroup {
   private Double costSum;
   private Double loss;

   public Double getCostSum() {
      return this.costSum;
   }

   public void setCostSum(Double costSum) {
      this.costSum = costSum;
   }

   public Double getLoss() {
      return this.loss;
   }

   public void setLoss(Double loss) {
      this.loss = loss;
   }
}
