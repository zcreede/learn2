package com.emr.project.operation.domain.req;

import io.swagger.annotations.ApiModelProperty;

public class OperPlanDetailReq {
   @ApiModelProperty("申请单号")
   private String applyFormNo;
   @ApiModelProperty("科室编码")
   private String deptCd;
   @ApiModelProperty("日期")
   private String currentDate;

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getCurrentDate() {
      return this.currentDate;
   }

   public void setCurrentDate(String currentDate) {
      this.currentDate = currentDate;
   }
}
