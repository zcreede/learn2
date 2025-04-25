package com.emr.project.docOrder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class OrderApplyFormDetailVo {
   private String orderNo;
   private String applyFormNo;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date examTime;
   private String examDocCd;
   private String examDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date examRepDate;
   private String repDocCd;
   private String repDocName;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   private Date creDate;
   private String crePerCode;
   private String crePerName;

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public String getApplyFormNo() {
      return this.applyFormNo;
   }

   public void setApplyFormNo(String applyFormNo) {
      this.applyFormNo = applyFormNo;
   }

   public Date getExamTime() {
      return this.examTime;
   }

   public void setExamTime(Date examTime) {
      this.examTime = examTime;
   }

   public String getExamDocCd() {
      return this.examDocCd;
   }

   public void setExamDocCd(String examDocCd) {
      this.examDocCd = examDocCd;
   }

   public String getExamDocName() {
      return this.examDocName;
   }

   public void setExamDocName(String examDocName) {
      this.examDocName = examDocName;
   }

   public Date getExamRepDate() {
      return this.examRepDate;
   }

   public void setExamRepDate(Date examRepDate) {
      this.examRepDate = examRepDate;
   }

   public String getRepDocCd() {
      return this.repDocCd;
   }

   public void setRepDocCd(String repDocCd) {
      this.repDocCd = repDocCd;
   }

   public String getRepDocName() {
      return this.repDocName;
   }

   public void setRepDocName(String repDocName) {
      this.repDocName = repDocName;
   }

   public Date getCreDate() {
      return this.creDate;
   }

   public void setCreDate(Date creDate) {
      this.creDate = creDate;
   }

   public String getCrePerCode() {
      return this.crePerCode;
   }

   public void setCrePerCode(String crePerCode) {
      this.crePerCode = crePerCode;
   }

   public String getCrePerName() {
      return this.crePerName;
   }

   public void setCrePerName(String crePerName) {
      this.crePerName = crePerName;
   }
}
