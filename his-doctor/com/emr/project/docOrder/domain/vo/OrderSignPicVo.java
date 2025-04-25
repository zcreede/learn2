package com.emr.project.docOrder.domain.vo;

import com.emr.project.docOrder.domain.TdPaOrderSignMain;

public class OrderSignPicVo extends TdPaOrderSignMain {
   private String orderNo;
   private Integer orderGroupNo;
   private String orderSortNumber;
   private String certPic;
   private String employeenumber;

   public String getEmployeenumber() {
      return this.employeenumber;
   }

   public void setEmployeenumber(String employeenumber) {
      this.employeenumber = employeenumber;
   }

   public String getCertPic() {
      return this.certPic;
   }

   public void setCertPic(String certPic) {
      this.certPic = certPic;
   }

   public String getOrderNo() {
      return this.orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Integer getOrderGroupNo() {
      return this.orderGroupNo;
   }

   public void setOrderGroupNo(Integer orderGroupNo) {
      this.orderGroupNo = orderGroupNo;
   }

   public String getOrderSortNumber() {
      return this.orderSortNumber;
   }

   public void setOrderSortNumber(String orderSortNumber) {
      this.orderSortNumber = orderSortNumber;
   }
}
