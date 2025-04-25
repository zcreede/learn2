package com.emr.project.operation.domain.resp;

import io.swagger.annotations.ApiModelProperty;

public class OperRoomInfoResp {
   @ApiModelProperty("手术间编号")
   private String surgicalRoomCode;
   @ApiModelProperty("手术间名称")
   private String surgicalRoomName;
   @ApiModelProperty("手术间人数")
   private Integer count;

   public String getSurgicalRoomCode() {
      return this.surgicalRoomCode;
   }

   public void setSurgicalRoomCode(String surgicalRoomCode) {
      this.surgicalRoomCode = surgicalRoomCode;
   }

   public String getSurgicalRoomName() {
      return this.surgicalRoomName;
   }

   public void setSurgicalRoomName(String surgicalRoomName) {
      this.surgicalRoomName = surgicalRoomName;
   }

   public Integer getCount() {
      return this.count;
   }

   public void setCount(Integer count) {
      this.count = count;
   }
}
