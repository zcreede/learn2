package com.emr.project.sys.domain.vo;

import com.emr.project.sys.domain.SysDiaIcd;

public class SysDiaIcdVo extends SysDiaIcd {
   private Boolean isEqual;
   private Boolean collectFlag;
   private String docCd;

   public String getDocCd() {
      return this.docCd;
   }

   public void setDocCd(String docCd) {
      this.docCd = docCd;
   }

   public Boolean getCollectFlag() {
      return this.collectFlag;
   }

   public void setCollectFlag(Boolean collectFlag) {
      this.collectFlag = collectFlag;
   }

   public Boolean getIsEqual() {
      return this.isEqual;
   }

   public void setIsEqual(Boolean isEqual) {
      this.isEqual = isEqual;
   }
}
