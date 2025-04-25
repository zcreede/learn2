package com.emr.project.docOrder.domain.req;

public class LcljInfoReq {
   private String projectCode;
   private String sign;
   private String zyh;
   private String jdbh;
   private String stageBmchr;
   private String stageGsljchr;
   private String stageGszljchr;
   private String type;

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getProjectCode() {
      return this.projectCode;
   }

   public void setProjectCode(String projectCode) {
      this.projectCode = projectCode;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getStageBmchr() {
      return this.stageBmchr;
   }

   public void setStageBmchr(String stageBmchr) {
      this.stageBmchr = stageBmchr;
   }

   public String getStageGsljchr() {
      return this.stageGsljchr;
   }

   public void setStageGsljchr(String stageGsljchr) {
      this.stageGsljchr = stageGsljchr;
   }

   public String getStageGszljchr() {
      return this.stageGszljchr;
   }

   public void setStageGszljchr(String stageGszljchr) {
      this.stageGszljchr = stageGszljchr;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getJdbh() {
      return this.jdbh;
   }

   public void setJdbh(String jdbh) {
      this.jdbh = jdbh;
   }
}
