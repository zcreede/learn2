package com.emr.project.emr.domain.vo;

import java.util.List;

public class IndexSignPatVo {
   private List signElemList;
   private String signText;
   private String oldText;
   private String certInfo;
   private String certSn;
   private String signerCd;
   private String signerName;

   public List getSignElemList() {
      return this.signElemList;
   }

   public void setSignElemList(List signElemList) {
      this.signElemList = signElemList;
   }

   public String getSignText() {
      return this.signText;
   }

   public void setSignText(String signText) {
      this.signText = signText;
   }

   public String getOldText() {
      return this.oldText;
   }

   public void setOldText(String oldText) {
      this.oldText = oldText;
   }

   public String getCertInfo() {
      return this.certInfo;
   }

   public void setCertInfo(String certInfo) {
      this.certInfo = certInfo;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public String getSignerCd() {
      return this.signerCd;
   }

   public void setSignerCd(String signerCd) {
      this.signerCd = signerCd;
   }

   public String getSignerName() {
      return this.signerName;
   }

   public void setSignerName(String signerName) {
      this.signerName = signerName;
   }
}
