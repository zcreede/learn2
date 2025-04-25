package com.emr.project.mzInfo.domain.req;

public class OutpatientInfoReq {
   private String source;
   private String inpNo;
   private String visitNo;
   private String idCard;

   public String getVisitNo() {
      return this.visitNo;
   }

   public void setVisitNo(String visitNo) {
      this.visitNo = visitNo;
   }

   public String getSource() {
      return this.source;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getInpNo() {
      return this.inpNo;
   }

   public void setInpNo(String inpNo) {
      this.inpNo = inpNo;
   }

   public String getIdCard() {
      return this.idCard;
   }

   public void setIdCard(String idCard) {
      this.idCard = idCard;
   }
}
