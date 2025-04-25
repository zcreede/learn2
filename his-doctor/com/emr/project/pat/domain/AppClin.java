package com.emr.project.pat.domain;

import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AppClin extends BaseEntity {
   private static final long serialVersionUID = 1L;
   private Long id;
   @Excel(
      name = "医疗机构代码"
   )
   private String orgCd;
   @Excel(
      name = "电子申请单编号"
   )
   private String appCd;
   @Excel(
      name = "申请单序号"
   )
   private String appNo;
   @Excel(
      name = "医嘱序号"
   )
   private String maNo;
   @Excel(
      name = "临床项目编号"
   )
   private String clinItemCd;
   @Excel(
      name = "临床项目名称"
   )
   private String clinItemName;
   @Excel(
      name = "数量"
   )
   private Long amount;
   @Excel(
      name = "金额"
   )
   private Long money;
   @Excel(
      name = "单据状态: 1已申请,2已记账,3已作废,4取消申请,5计费确认,6报告完成"
   )
   private String itemState;
   @JsonFormat(
      pattern = "yyyy-MM-dd HH:mm",
      timezone = "GMT+8"
   )
   @Excel(
      name = "报告发布时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm"
   )
   private Date clinRepDate;

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setOrgCd(String orgCd) {
      this.orgCd = orgCd;
   }

   public String getOrgCd() {
      return this.orgCd;
   }

   public void setAppCd(String appCd) {
      this.appCd = appCd;
   }

   public String getAppCd() {
      return this.appCd;
   }

   public void setAppNo(String appNo) {
      this.appNo = appNo;
   }

   public String getAppNo() {
      return this.appNo;
   }

   public void setMaNo(String maNo) {
      this.maNo = maNo;
   }

   public String getMaNo() {
      return this.maNo;
   }

   public void setClinItemCd(String clinItemCd) {
      this.clinItemCd = clinItemCd;
   }

   public String getClinItemCd() {
      return this.clinItemCd;
   }

   public void setClinItemName(String clinItemName) {
      this.clinItemName = clinItemName;
   }

   public String getClinItemName() {
      return this.clinItemName;
   }

   public void setAmount(Long amount) {
      this.amount = amount;
   }

   public Long getAmount() {
      return this.amount;
   }

   public void setMoney(Long money) {
      this.money = money;
   }

   public Long getMoney() {
      return this.money;
   }

   public void setItemState(String itemState) {
      this.itemState = itemState;
   }

   public String getItemState() {
      return this.itemState;
   }

   public void setClinRepDate(Date clinRepDate) {
      this.clinRepDate = clinRepDate;
   }

   public Date getClinRepDate() {
      return this.clinRepDate;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("id", this.getId()).append("orgCd", this.getOrgCd()).append("appCd", this.getAppCd()).append("appNo", this.getAppNo()).append("maNo", this.getMaNo()).append("clinItemCd", this.getClinItemCd()).append("clinItemName", this.getClinItemName()).append("amount", this.getAmount()).append("money", this.getMoney()).append("itemState", this.getItemState()).append("clinRepDate", this.getClinRepDate()).toString();
   }
}
