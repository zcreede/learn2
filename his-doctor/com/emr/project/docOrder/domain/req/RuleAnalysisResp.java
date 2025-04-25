package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleAnalysisResp implements Serializable {
   private String code;
   private String msg;
   private String url;
   private List data = new ArrayList();

   public List getData() {
      return this.data;
   }

   public void setData(List data) {
      this.data = data;
   }

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

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
}
