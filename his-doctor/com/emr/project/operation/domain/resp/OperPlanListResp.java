package com.emr.project.operation.domain.resp;

import com.emr.project.docOrder.domain.TdCaOperationApply;
import io.swagger.annotations.ApiModelProperty;

public class OperPlanListResp extends TdCaOperationApply {
   @ApiModelProperty("手术间名称")
   private String operRoomName;

   public String getOperRoomName() {
      return this.operRoomName;
   }

   public void setOperRoomName(String operRoomName) {
      this.operRoomName = operRoomName;
   }
}
