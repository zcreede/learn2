package com.emr.project.emr.domain.vo;

public class AnalysisXmlDataReq {
   private String fromEmrTypeCode;
   private String toEmrTypeCode;
   private String fromXmlStr;
   private String toXmlStr;

   public String getFromEmrTypeCode() {
      return this.fromEmrTypeCode;
   }

   public void setFromEmrTypeCode(String fromEmrTypeCode) {
      this.fromEmrTypeCode = fromEmrTypeCode;
   }

   public String getToEmrTypeCode() {
      return this.toEmrTypeCode;
   }

   public void setToEmrTypeCode(String toEmrTypeCode) {
      this.toEmrTypeCode = toEmrTypeCode;
   }

   public String getFromXmlStr() {
      return this.fromXmlStr;
   }

   public void setFromXmlStr(String fromXmlStr) {
      this.fromXmlStr = fromXmlStr;
   }

   public String getToXmlStr() {
      return this.toXmlStr;
   }

   public void setToXmlStr(String toXmlStr) {
      this.toXmlStr = toXmlStr;
   }
}
