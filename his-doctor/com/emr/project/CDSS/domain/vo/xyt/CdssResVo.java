package com.emr.project.CDSS.domain.vo.xyt;

import java.util.List;

public class CdssResVo {
   private List cdssDateResVoList;
   private List cdssConfirmList;
   private List cdssMsgList;
   private Boolean cdssConfirmFlag = false;
   private Boolean cdssMsgFlag = false;

   public List getCdssConfirmList() {
      return this.cdssConfirmList;
   }

   public void setCdssConfirmList(List cdssConfirmList) {
      this.cdssConfirmList = cdssConfirmList;
   }

   public List getCdssMsgList() {
      return this.cdssMsgList;
   }

   public void setCdssMsgList(List cdssMsgList) {
      this.cdssMsgList = cdssMsgList;
   }

   public List getCdssDateResVoList() {
      return this.cdssDateResVoList;
   }

   public void setCdssDateResVoList(List cdssDateResVoList) {
      this.cdssDateResVoList = cdssDateResVoList;
   }

   public Boolean getCdssConfirmFlag() {
      return this.cdssConfirmFlag;
   }

   public void setCdssConfirmFlag(Boolean cdssConfirmFlag) {
      this.cdssConfirmFlag = cdssConfirmFlag;
   }

   public Boolean getCdssMsgFlag() {
      return this.cdssMsgFlag;
   }

   public void setCdssMsgFlag(Boolean cdssMsgFlag) {
      this.cdssMsgFlag = cdssMsgFlag;
   }
}
