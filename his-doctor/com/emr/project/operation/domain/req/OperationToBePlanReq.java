package com.emr.project.operation.domain.req;

import com.emr.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

public class OperationToBePlanReq {
   @Excel(
      name = "开始日期"
   )
   @ApiModelProperty("开始日期")
   private String startDate;
   @Excel(
      name = "结束日期"
   )
   @ApiModelProperty("结束日期")
   private String endDate;
   @Excel(
      name = "医疗机构编码"
   )
   @ApiModelProperty("安排日期")
   private String orgCode;
   @Excel(
      name = "统计方式"
   )
   @ApiModelProperty("统计方式 1:按手术日期 2:按手术间 3:按病区")
   private String statistType;
   @Excel(
      name = "状态"
   )
   @ApiModelProperty("状态（01待提交(保存)02待审核（提交）03已作废  04驳回  05已审核（审核）06已安排 07 已完成）")
   private String status;

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
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

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getStatistType() {
      return this.statistType;
   }

   public void setStatistType(String statistType) {
      this.statistType = statistType;
   }
}
