package com.emr.project.operation.domain.req;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class OperPlanListReq {
   @ApiModelProperty(
      hidden = true
   )
   private String orgCode;
   @ApiModelProperty("状态（01待提交(保存)02待审核（提交）03已作废  04驳回  05已审核（审核）06已安排 07 已完成）")
   private String status;
   @ApiModelProperty("预约日期")
   private String planDate;
   @ApiModelProperty("安排日期")
   private String operDate;
   @ApiModelProperty("申请科室")
   private String deptCd;
   @ApiModelProperty("手术间")
   private String operRoom;
   @ApiModelProperty("姓名/住院号")
   private String searchValue;
   @ApiModelProperty(
      hidden = true
   )
   private List statusList;

   public String getOrgCode() {
      return this.orgCode;
   }

   public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getPlanDate() {
      return this.planDate;
   }

   public void setPlanDate(String planDate) {
      this.planDate = planDate;
   }

   public String getDeptCd() {
      return this.deptCd;
   }

   public void setDeptCd(String deptCd) {
      this.deptCd = deptCd;
   }

   public String getOperRoom() {
      return this.operRoom;
   }

   public void setOperRoom(String operRoom) {
      this.operRoom = operRoom;
   }

   public String getSearchValue() {
      return this.searchValue;
   }

   public void setSearchValue(String searchValue) {
      this.searchValue = searchValue;
   }

   public String getOperDate() {
      return this.operDate;
   }

   public void setOperDate(String operDate) {
      this.operDate = operDate;
   }

   public List getStatusList() {
      return this.statusList;
   }

   public void setStatusList(List statusList) {
      this.statusList = statusList;
   }
}
