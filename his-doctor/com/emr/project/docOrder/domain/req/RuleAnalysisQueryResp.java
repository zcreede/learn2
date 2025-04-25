package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class RuleAnalysisQueryResp implements Serializable {
   private String code;
   private String msg;
   private String data;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMsg() {
      return this.msg;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public String getData() {
      return this.data;
   }

   public void setData(String data) {
      this.data = data;
   }
}
