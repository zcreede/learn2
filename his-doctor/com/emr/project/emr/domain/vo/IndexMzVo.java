package com.emr.project.emr.domain.vo;

public class IndexMzVo {
   private Long fileId;
   private Long proofId;
   private String proofNo;

   public Long getFileId() {
      return this.fileId;
   }

   public void setFileId(Long fileId) {
      this.fileId = fileId;
   }

   public String getProofNo() {
      return this.proofNo;
   }

   public void setProofNo(String proofNo) {
      this.proofNo = proofNo;
   }

   public Long getProofId() {
      return this.proofId;
   }

   public void setProofId(Long proofId) {
      this.proofId = proofId;
   }
}
