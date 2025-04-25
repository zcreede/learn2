package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class ItemListIsOpenResVo {
   private String resCode;
   private String msgFlag;
   private String resMsg;
   private List noOpenGroupNo;
   List itemIsOpenResVoList;

   public List getNoOpenGroupNo() {
      return this.noOpenGroupNo;
   }

   public void setNoOpenGroupNo(List noOpenGroupNo) {
      this.noOpenGroupNo = noOpenGroupNo;
   }

   public String getResCode() {
      return this.resCode;
   }

   public void setResCode(String resCode) {
      this.resCode = resCode;
   }

   public String getMsgFlag() {
      return this.msgFlag;
   }

   public void setMsgFlag(String msgFlag) {
      this.msgFlag = msgFlag;
   }

   public String getResMsg() {
      return this.resMsg;
   }

   public void setResMsg(String resMsg) {
      this.resMsg = resMsg;
   }

   public List getItemIsOpenResVoList() {
      return this.itemIsOpenResVoList;
   }

   public void setItemIsOpenResVoList(List itemIsOpenResVoList) {
      this.itemIsOpenResVoList = itemIsOpenResVoList;
   }
}
