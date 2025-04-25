package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class StageListReq implements Serializable {
   private String stageGsljchr;
   private String stageGszljchr;
   private String projectCode;
   private String sign;

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
}
