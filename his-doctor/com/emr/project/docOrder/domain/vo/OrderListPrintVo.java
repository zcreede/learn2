package com.emr.project.docOrder.domain.vo;

import java.util.List;

public class OrderListPrintVo {
   private String orderListType;
   private Integer printType;
   private Integer printPageNum;
   private Integer printStartPageNum;
   private Integer printEndPageNum;
   private Integer printStartLineNum;
   private Integer printEndLineNum;
   private List linePrintList;
   private Integer printPageNum2;
   private OrderListSearchVo orderListSearchVo;

   public OrderListSearchVo getOrderListSearchVo() {
      return this.orderListSearchVo;
   }

   public void setOrderListSearchVo(OrderListSearchVo orderListSearchVo) {
      this.orderListSearchVo = orderListSearchVo;
   }

   public String getOrderListType() {
      return this.orderListType;
   }

   public void setOrderListType(String orderListType) {
      this.orderListType = orderListType;
   }

   public Integer getPrintType() {
      return this.printType;
   }

   public void setPrintType(Integer printType) {
      this.printType = printType;
   }

   public Integer getPrintPageNum() {
      return this.printPageNum;
   }

   public void setPrintPageNum(Integer printPageNum) {
      this.printPageNum = printPageNum;
   }

   public Integer getPrintStartPageNum() {
      return this.printStartPageNum;
   }

   public void setPrintStartPageNum(Integer printStartPageNum) {
      this.printStartPageNum = printStartPageNum;
   }

   public Integer getPrintEndPageNum() {
      return this.printEndPageNum;
   }

   public void setPrintEndPageNum(Integer printEndPageNum) {
      this.printEndPageNum = printEndPageNum;
   }

   public Integer getPrintStartLineNum() {
      return this.printStartLineNum;
   }

   public void setPrintStartLineNum(Integer printStartLineNum) {
      this.printStartLineNum = printStartLineNum;
   }

   public Integer getPrintEndLineNum() {
      return this.printEndLineNum;
   }

   public void setPrintEndLineNum(Integer printEndLineNum) {
      this.printEndLineNum = printEndLineNum;
   }

   public List getLinePrintList() {
      return this.linePrintList;
   }

   public void setLinePrintList(List linePrintList) {
      this.linePrintList = linePrintList;
   }

   public Integer getPrintPageNum2() {
      return this.printPageNum2;
   }

   public void setPrintPageNum2(Integer printPageNum2) {
      this.printPageNum2 = printPageNum2;
   }
}
