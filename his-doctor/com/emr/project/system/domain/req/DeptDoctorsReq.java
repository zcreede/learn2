package com.emr.project.system.domain.req;

import io.swagger.annotations.ApiModelProperty;

public class DeptDoctorsReq {
   @ApiModelProperty("在岗标志（0正常1停用）")
   private String status;
   @ApiModelProperty("传入科室编号")
   private String deptCode;

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }
}
