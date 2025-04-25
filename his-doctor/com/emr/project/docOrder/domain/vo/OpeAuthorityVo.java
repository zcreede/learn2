package com.emr.project.docOrder.domain.vo;

public class OpeAuthorityVo {
   private String docCd;
   private String operLevel;
   private String applyAuth;
   private String operationAuth;

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public String getOperLevel() {
      return this.operLevel;
   }

   public void setOperLevel(String operLevel) {
      this.operLevel = operLevel;
   }

   public String getApplyAuth() {
      return this.applyAuth;
   }

   public void setApplyAuth(String applyAuth) {
      this.applyAuth = applyAuth;
   }

   public String getOperationAuth() {
      return this.operationAuth;
   }

   public void setOperationAuth(String operationAuth) {
      this.operationAuth = operationAuth;
   }
}
