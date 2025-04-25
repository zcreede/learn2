package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class RuleAnalysisVo implements Serializable {
   private String url;
   private String msgid;

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getMsgid() {
      return this.msgid;
   }

   public void setMsgid(String msgid) {
      this.msgid = msgid;
   }
}
