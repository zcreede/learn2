package com.emr.project.docOrder.domain.req;

import java.io.Serializable;

public class LcljBaseInfoReq implements Serializable {
   private String projectCode;
   private String operCode;
   private String sign;

   public String getProjectCode() {
      return this.projectCode;
   }

   public void setProjectCode(String projectCode) {
      this.projectCode = projectCode;
   }

   public String getOperCode() {
      return this.operCode;
   }

   public void setOperCode(String operCode) {
      this.operCode = operCode;
   }

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }
}
