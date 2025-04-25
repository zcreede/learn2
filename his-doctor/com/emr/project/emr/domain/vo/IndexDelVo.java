package com.emr.project.emr.domain.vo;

public class IndexDelVo {
   private Long id;
   private Long subfileIndexId;
   private String base64;
   private String xmlStr;
   private String paragraphElemNameCurrn;

   public String getParagraphElemNameCurrn() {
      return this.paragraphElemNameCurrn;
   }

   public void setParagraphElemNameCurrn(String paragraphElemNameCurrn) {
      this.paragraphElemNameCurrn = paragraphElemNameCurrn;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getSubfileIndexId() {
      return this.subfileIndexId;
   }

   public void setSubfileIndexId(Long subfileIndexId) {
      this.subfileIndexId = subfileIndexId;
   }

   public String getBase64() {
      return this.base64;
   }

   public void setBase64(String base64) {
      this.base64 = base64;
   }

   public String getXmlStr() {
      return this.xmlStr;
   }

   public void setXmlStr(String xmlStr) {
      this.xmlStr = xmlStr;
   }
}
