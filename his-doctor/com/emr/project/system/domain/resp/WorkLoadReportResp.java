package com.emr.project.system.domain.resp;

import java.io.Serializable;
import java.math.BigDecimal;

public class WorkLoadReportResp implements Serializable {
   private String orgName;
   private String nickName;
   private String startDate;
   private String endDate;
   private String itemCode;
   private String itemName;
   private String itemTypeCode;
   private String itemTypeName;
   private String deptName;
   private String deptCode;
   private BigDecimal num;
   private BigDecimal amount;

   public String getOrgName() {
      return this.orgName;
   }

   public void setOrgName(String orgName) {
      this.orgName = orgName;
   }

   public String getNickName() {
      return this.nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getStartDate() {
      return this.startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getItemCode() {
      return this.itemCode;
   }

   public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public String getItemTypeCode() {
      return this.itemTypeCode;
   }

   public void setItemTypeCode(String itemTypeCode) {
      this.itemTypeCode = itemTypeCode;
   }

   public String getItemTypeName() {
      return this.itemTypeName;
   }

   public void setItemTypeName(String itemTypeName) {
      this.itemTypeName = itemTypeName;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public BigDecimal getNum() {
      return this.num;
   }

   public void setNum(BigDecimal num) {
      this.num = num;
   }

   public BigDecimal getAmount() {
      return this.amount;
   }

   public void setAmount(BigDecimal amount) {
      this.amount = amount;
   }
}
