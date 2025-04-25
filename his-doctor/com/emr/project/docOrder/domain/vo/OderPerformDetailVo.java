package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.resp.OrderExecutionExpenseDetail;
import com.emr.project.docOrder.domain.resp.OrderStatusProcessDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OderPerformDetailVo {
   @JsonFormat(
      pattern = "yyyy-MM-dd",
      timezone = "GMT+8"
   )
   private Date performDate;
   private Boolean flag;
   private List childProcess;
   private List orderPerformList;
   private List expenseDetailList;
   private List tackDrugDetailList;

   public OderPerformDetailVo() {
      this.flag = Boolean.FALSE;
      this.childProcess = new ArrayList();
      this.orderPerformList = new ArrayList();
      this.expenseDetailList = new ArrayList();
      this.tackDrugDetailList = new ArrayList();
   }

   public Date getPerformDate() {
      return this.performDate;
   }

   public void setPerformDate(Date performDate) {
      this.performDate = performDate;
   }

   public Boolean getFlag() {
      return this.flag;
   }

   public void setFlag(Boolean flag) {
      this.flag = flag;
   }

   public List getChildProcess() {
      return this.childProcess;
   }

   public void setChildProcess(List childProcess) {
      this.childProcess = childProcess;
   }

   public List getOrderPerformList() {
      return this.orderPerformList;
   }

   public void setOrderPerformList(List orderPerformList) {
      this.orderPerformList = orderPerformList;
   }

   public List getExpenseDetailList() {
      return this.expenseDetailList;
   }

   public void setExpenseDetailList(List expenseDetailList) {
      this.expenseDetailList = expenseDetailList;
   }

   public List getTackDrugDetailList() {
      return this.tackDrugDetailList;
   }

   public void setTackDrugDetailList(List tackDrugDetailList) {
      this.tackDrugDetailList = tackDrugDetailList;
   }
}
