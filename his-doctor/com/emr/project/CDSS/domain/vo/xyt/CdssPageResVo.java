package com.emr.project.CDSS.domain.vo.xyt;

public class CdssPageResVo {
   private String url;
   private String flag;

   public CdssPageResVo() {
   }

   public CdssPageResVo(String url, String flag) {
      this.url = url;
      this.flag = flag;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getFlag() {
      return this.flag;
   }

   public void setFlag(String flag) {
      this.flag = flag;
   }
}
