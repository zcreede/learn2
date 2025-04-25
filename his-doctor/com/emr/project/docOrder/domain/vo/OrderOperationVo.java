package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderOperationVo {
   private List selectedList;
   private String type;
   private String admissionNo;
   private List orderSignCommitVoList;

   public List getOrderSignCommitVoList() {
      return this.orderSignCommitVoList;
   }

   public void setOrderSignCommitVoList(List orderSignCommitVoList) {
      this.orderSignCommitVoList = orderSignCommitVoList;
   }

   public List getSelectedList() {
      return this.selectedList;
   }

   public void setSelectedList(List selectedList) {
      this.selectedList = selectedList;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getAdmissionNo() {
      return this.admissionNo;
   }

   public void setAdmissionNo(String admissionNo) {
      this.admissionNo = admissionNo;
   }
}
