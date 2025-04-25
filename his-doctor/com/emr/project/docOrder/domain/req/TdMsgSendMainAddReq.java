package com.emr.project.docOrder.domain.req;

import com.emr.project.docOrder.domain.TdMsgSendMain;
import java.io.Serializable;
import java.util.List;

public class TdMsgSendMainAddReq implements Serializable {
   private String projectCode;
   private String sign;
   private List mainList;

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

   public List getMainList() {
      return this.mainList;
   }

   public void setMainList(List mainList) {
      this.mainList = mainList;
   }
}
