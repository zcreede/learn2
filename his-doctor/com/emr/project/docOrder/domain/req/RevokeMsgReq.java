package com.emr.project.docOrder.domain.req;

import java.io.Serializable;
import java.util.List;

public class RevokeMsgReq implements Serializable {
   private String sign;
   private String thirdFlag;
   private List busIdList;
   private String cancelPerCode;
   private String cancelPerName;

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

   public String getCancelPerCode() {
      return this.cancelPerCode;
   }

   public void setCancelPerCode(String cancelPerCode) {
      this.cancelPerCode = cancelPerCode;
   }

   public String getCancelPerName() {
      return this.cancelPerName;
   }

   public void setCancelPerName(String cancelPerName) {
      this.cancelPerName = cancelPerName;
   }
}
