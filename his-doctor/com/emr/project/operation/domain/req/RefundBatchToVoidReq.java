package com.emr.project.operation.domain.req;

import java.util.ArrayList;
import java.util.List;

public class RefundBatchToVoidReq {
   private List list = new ArrayList();
   private String admissionNo;

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }
}
