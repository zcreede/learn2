package com.emr.project.webservice.domain.resp;

public class WebServiceFoodborneResp {
   private String fillGuid;
   private String result;
   private String status;
   private String foodborneUrl;

   public String toString() {
      return "WebServiceFoodborneResp{fillGuid='" + this.fillGuid + '\'' + ", result='" + this.result + '\'' + ", status='" + this.status + '\'' + ", foodborneUrl='" + this.foodborneUrl + '\'' + '}';
   }

   public String getFillGuid() {
      return this.fillGuid;
   }

   public void setFillGuid(String fillGuid) {
      this.fillGuid = fillGuid;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getFoodborneUrl() {
      return this.foodborneUrl;
   }

   public void setFoodborneUrl(String foodborneUrl) {
      this.foodborneUrl = foodborneUrl;
   }
}
