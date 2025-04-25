package com.emr.project.docOrder.domain.req;

import java.util.ArrayList;
import java.util.List;

public class PrintOrderDataListReq {
   private String admissionNo;
   private List detailList = new ArrayList();

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }
}
