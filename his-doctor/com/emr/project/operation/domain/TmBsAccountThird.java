package com.emr.project.operation.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TmBsAccountThird {
   private String code;
   private String name;
   private String pym;
   private String detailMark;
   private BigDecimal preferRatio;
   private String printName;
   private BigDecimal printSort;
   private String outFlag;
   private String hosFlag;
   private String firstCode;
   private BigDecimal sort;
   private Date createTime;
   private Date updateTime;

   public Date getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   public Date getUpdateTime() {
      return this.updateTime;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code == null ? null : code.trim();
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name == null ? null : name.trim();
   }

   public String getPym() {
      return this.pym;
   }

   public void setPym(String pym) {
      this.pym = pym == null ? null : pym.trim();
   }

   public String getDetailMark() {
      return this.detailMark;
   }

   public void setDetailMark(String detailMark) {
      this.detailMark = detailMark == null ? null : detailMark.trim();
   }

   public BigDecimal getPreferRatio() {
      return this.preferRatio;
   }

   public void setPreferRatio(BigDecimal preferRatio) {
      this.preferRatio = preferRatio;
   }

   public String getPrintName() {
      return this.printName;
   }

   public void setPrintName(String printName) {
      this.printName = printName == null ? null : printName.trim();
   }

   public BigDecimal getPrintSort() {
      return this.printSort;
   }

   public void setPrintSort(BigDecimal printSort) {
      this.printSort = printSort;
   }

   public String getOutFlag() {
      return this.outFlag;
   }

   public void setOutFlag(String outFlag) {
      this.outFlag = outFlag == null ? null : outFlag.trim();
   }

   public String getHosFlag() {
      return this.hosFlag;
   }

   public void setHosFlag(String hosFlag) {
      this.hosFlag = hosFlag == null ? null : hosFlag.trim();
   }

   public String getFirstCode() {
      return this.firstCode;
   }

   public void setFirstCode(String firstCode) {
      this.firstCode = firstCode == null ? null : firstCode.trim();
   }

   public BigDecimal getSort() {
      return this.sort;
   }

   public void setSort(BigDecimal sort) {
      this.sort = sort;
   }
}
