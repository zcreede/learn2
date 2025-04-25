package com.emr.project.webservice.domain.resp;

public class WebServiceGeneralResp {
   private String code;
   private String message;
   private Object data;

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Object getData() {
      return this.data;
   }

   public void setData(Object data) {
      this.data = data;
   }
}
