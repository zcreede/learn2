package com.emr.project.emr.domain.vo;

import com.emr.project.emr.domain.EmrSharing;

public class EmrSharingVo extends EmrSharing {
   private Boolean quoteFlag;
   private String contElemName;

   public String getContElemName() {
      return this.contElemName;
   }

   public void setContElemName(String contElemName) {
      this.contElemName = contElemName;
   }

   public Boolean getQuoteFlag() {
      return this.quoteFlag;
   }

   public void setQuoteFlag(Boolean quoteFlag) {
      this.quoteFlag = quoteFlag;
   }
}
