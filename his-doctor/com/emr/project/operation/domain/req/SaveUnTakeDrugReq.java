package com.emr.project.operation.domain.req;

import java.util.ArrayList;
import java.util.List;

public class SaveUnTakeDrugReq {
   private List detailList = new ArrayList();
   private String admissionNo;
   private String caseNo;
   private String patientDepName;
   private String patientDepCode;

   public String getPatientDepCode() {
      return this.patientDepCode;
   }

   public void setPatientDepCode(String patientDepCode) {
      this.patientDepCode = patientDepCode;
   }

   public String getCaseNo() {
      return this.caseNo;
   }

   public void setCaseNo(String caseNo) {
      this.caseNo = caseNo;
   }

   public String getPatientDepName() {
      return this.patientDepName;
   }

   public void setPatientDepName(String patientDepName) {
      this.patientDepName = patientDepName;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }
}
