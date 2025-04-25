package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.resp.OrderSignTextResp;

public class OrderSignCommitVo extends OrderSignTextResp {
   private String encryptedInfo;
   private String signCertificate;

   public String getEncryptedInfo() {
      return this.encryptedInfo;
   }

   public void setEncryptedInfo(String encryptedInfo) {
      this.encryptedInfo = encryptedInfo;
   }

   public String getSignCertificate() {
      return this.signCertificate;
   }

   public void setSignCertificate(String signCertificate) {
      this.signCertificate = signCertificate;
   }
}
