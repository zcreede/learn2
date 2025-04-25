package com.emr.project.docOrder.domain.req;

public class LcljInfoLocalReq {
   private String admissionNo;
   private String cpNo;
   private String subCpNo;
   private String stageCode;
   private String itemCode;
   private String viewFlag;

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }

   public String getCpNo() {
      return this.cpNo;
   }

   public void setCpNo(String cpNo) {
      this.cpNo = cpNo;
   }

   public String getSubCpNo() {
      return this.subCpNo;
   }

   public void setSubCpNo(String subCpNo) {
      this.subCpNo = subCpNo;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getStageCode() {
      return this.stageCode;
   }

   public void setStageCode(String stageCode) {
      this.stageCode = stageCode;
   }

   public String getViewFlag() {
      return this.viewFlag;
   }

   public void setViewFlag(String viewFlag) {
      this.viewFlag = viewFlag;
   }
}
