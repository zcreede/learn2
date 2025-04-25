package com.emr.project.operation.domain.resp;

import io.swagger.annotations.ApiModelProperty;

public class OperPlanCountResp {
   @ApiModelProperty("今日手术数量")
   private Long totalCount;
   @ApiModelProperty("择期手术数量")
   private Long zqCount;
   @ApiModelProperty("急诊手术数量")
   private Long jzCount;

   public Long getTotalCount() {
      return this.totalCount;
   }

   public void setTotalCount(Long totalCount) {
      this.totalCount = totalCount;
   }

   public Long getZqCount() {
      return this.zqCount;
   }

   public void setZqCount(Long zqCount) {
      this.zqCount = zqCount;
   }

   public Long getJzCount() {
      return this.jzCount;
   }

   public void setJzCount(Long jzCount) {
      this.jzCount = jzCount;
   }
}
