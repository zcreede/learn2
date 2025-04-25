package com.emr.project.webservice.domain.req;

public class ClinicalPathwayReq {
   private String admissionNo;
   private String zyh;
   private String icd;
   private String method;

   public String getZyh() {
      return this.zyh;
   }

   public void setZyh(String zyh) {
      this.zyh = zyh;
   }

   public String getIcd() {
      return this.icd;
   }

   public void setIcd(String icd) {
      this.icd = icd;
   }

   public String getMethod() {
      return this.method;
   }

   public void setMethod(String method) {
      this.method = method;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }
}
