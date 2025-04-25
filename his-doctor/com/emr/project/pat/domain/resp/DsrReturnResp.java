package com.emr.project.pat.domain.resp;

import io.swagger.annotations.ApiModelProperty;

public class DsrReturnResp {
   @ApiModelProperty("返回状态 0:成功 1失败")
   private String returnStatus;
   @ApiModelProperty("返回消息")
   private String returnMsg;

   public String getReturnStatus() {
      return this.returnStatus;
   }

   public void setReturnStatus(String returnStatus) {
      this.returnStatus = returnStatus;
   }

   public String getReturnMsg() {
      return this.returnMsg;
   }

   public void setReturnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
   }
}
