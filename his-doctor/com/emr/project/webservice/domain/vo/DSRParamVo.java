package com.emr.project.webservice.domain.vo;

import java.util.List;

public class DSRParamVo {
   private List icds;
   private String patientId;
   private String result;
   private String urlStr;
   private String browserType;
   private String browserTypeName;

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getBrowserType() {
      return this.browserType;
   }

   public void setBrowserType(String browserType) {
      this.browserType = browserType;
   }

   public String getBrowserTypeName() {
      return this.browserTypeName;
   }

   public void setBrowserTypeName(String browserTypeName) {
      this.browserTypeName = browserTypeName;
   }

   public String getUrlStr() {
      return this.urlStr;
   }

   public void setUrlStr(String urlStr) {
      this.urlStr = urlStr;
   }

   public List getIcds() {
      return this.icds;
   }

   public void setIcds(List icds) {
      this.icds = icds;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }
}
