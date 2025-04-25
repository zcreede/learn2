package com.emr.project.sys.domain.vo;

import com.emr.project.sys.domain.QuoteElem;

public class QuoteElemVo extends QuoteElem {
   private String tempTypeName;
   private String fromMrTypeName;
   private String tempId;
   private String contElemName;
   private String elemText;

   public QuoteElemVo() {
   }

   public QuoteElemVo(String contElemName, String elemId, String elemName, String elemCd) {
      super.setElemId(elemId);
      super.setElemName(elemName);
      super.setElemCd(elemCd);
      this.contElemName = contElemName;
   }

   public String getElemText() {
      return this.elemText;
   }

   public void setElemText(String elemText) {
      this.elemText = elemText;
   }

   public String getContElemName() {
      return this.contElemName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }

   public String getTempId() {
      return this.tempId;
   }

   public void setTempId(String tempId) {
      this.tempId = tempId;
   }

   public String getTempTypeName() {
      return this.tempTypeName;
   }

   public void setTempTypeName(String tempTypeName) {
      this.tempTypeName = tempTypeName;
   }

   public String getFromMrTypeName() {
      return this.fromMrTypeName;
   }

   public void setFromMrTypeName(String fromMrTypeName) {
      this.fromMrTypeName = fromMrTypeName;
   }
}
