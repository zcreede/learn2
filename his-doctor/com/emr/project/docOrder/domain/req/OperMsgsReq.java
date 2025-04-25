package com.emr.project.docOrder.domain.req;

import java.util.List;

public class OperMsgsReq {
   private String sign;
   private String thirdFlag;
   private List busIdList;
   private String status;
   private String execUserCd;
   private String execUserName;

   public String getSign() {
      return this.sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getThirdFlag() {
      return this.thirdFlag;
   }

   public void setThirdFlag(String thirdFlag) {
      this.thirdFlag = thirdFlag;
   }

   public List getBusIdList() {
      return this.busIdList;
   }

   public void setBusIdList(List busIdList) {
      this.busIdList = busIdList;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getExecUserCd() {
      return this.execUserCd;
   }

   public void setExecUserCd(String execUserCd) {
      this.execUserCd = execUserCd;
   }

   public String getExecUserName() {
      return this.execUserName;
   }

   public void setExecUserName(String execUserName) {
      this.execUserName = execUserName;
   }
}
