package com.emr.project.operation.domain.req;

import java.util.ArrayList;
import java.util.List;

public class SuppPrintDataListReq {
   private String admissionNo;
   private String hospitalizedCount;
   private List reqList = new ArrayList();

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getHospitalizedCount() {
      return this.hospitalizedCount;
   }

   public void setHospitalizedCount(String hospitalizedCount) {
      this.hospitalizedCount = hospitalizedCount;
   }

   public List getReqList() {
      return this.reqList;
   }

   public void setReqList(List reqList) {
      this.reqList = reqList;
   }
}
