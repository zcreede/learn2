package com.emr.project.docOrder.domain.resp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderExecutionRecordResp {
   private OrderExecutionItemDetail executionDetail;
   private List detailList = new ArrayList();
   private List performDetailList = new ArrayList();
   private List expenseDetailList = new ArrayList();
   private BigDecimal total;

   public OrderExecutionRecordResp() {
      this.total = BigDecimal.ZERO;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public List getExpenseDetailList() {
      return this.expenseDetailList;
   }

   public void setExpenseDetailList(List expenseDetailList) {
      this.expenseDetailList = expenseDetailList;
   }

   public List getPerformDetailList() {
      return this.performDetailList;
   }

   public void setPerformDetailList(List performDetailList) {
      this.performDetailList = performDetailList;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
   }

   public OrderExecutionItemDetail getExecutionDetail() {
      return this.executionDetail;
   }

   public void setExecutionDetail(OrderExecutionItemDetail executionDetail) {
      this.executionDetail = executionDetail;
   }
}
