package com.emr.project.operation.domain.req;

import io.swagger.annotations.ApiModelProperty;

public class SearchDoctorListReq {
   String searchStr;
   String type = "0";
   @ApiModelProperty("0启用 1禁用")
   private String status;

   public String getSearchStr() {
      return this.searchStr;
   }

   public void setSearchStr(String searchStr) {
      this.searchStr = searchStr;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
