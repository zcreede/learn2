package com.emr.project.esSearch.domain.vo;

import java.util.List;

public class DrugAndClinSearchVo {
   private String keyWord;
   private String performDepCode;
   private String fuzzyMatchFlag;
   private String orderClassCode;
   private String pageType;
   private Integer pageNum;
   private Integer pageSize;
   private String itemClassCd;
   private String documentTypeNo;
   private String allDocumentType;
   private String commonType;
   private List itemCdList;
   private String herbalFlag;
   private String itemFlagCd;
   private String orderType;
   private String orderItemFlag;
   private String opeFlag;
   private String keyWordUpper;

   public String getOpeFlag() {
      return this.opeFlag;
   }

   public void setOpeFlag(String opeFlag) {
      this.opeFlag = opeFlag;
   }

   public String getKeyWordUpper() {
      return this.keyWordUpper;
   }

   public void setKeyWordUpper(String keyWordUpper) {
      this.keyWordUpper = keyWordUpper;
   }

   public String getOrderItemFlag() {
      return this.orderItemFlag;
   }

   public void setOrderItemFlag(String orderItemFlag) {
      this.orderItemFlag = orderItemFlag;
   }

   public String getOrderType() {
      return this.orderType;
   }

   public void setOrderType(String orderType) {
      this.orderType = orderType;
   }

   public String getItemFlagCd() {
      return this.itemFlagCd;
   }

   public void setItemFlagCd(String itemFlagCd) {
      this.itemFlagCd = itemFlagCd;
   }

   public String getHerbalFlag() {
      return this.herbalFlag;
   }

   public void setHerbalFlag(String herbalFlag) {
      this.herbalFlag = herbalFlag;
   }

   public String getAllDocumentType() {
      return this.allDocumentType;
   }

   public void setAllDocumentType(String allDocumentType) {
      this.allDocumentType = allDocumentType;
   }

   public String getPerformDepCode() {
      return this.performDepCode;
   }

   public void setPerformDepCode(String performDepCode) {
      this.performDepCode = performDepCode;
   }

   public String getFuzzyMatchFlag() {
      return this.fuzzyMatchFlag;
   }

   public void setFuzzyMatchFlag(String fuzzyMatchFlag) {
      this.fuzzyMatchFlag = fuzzyMatchFlag;
   }

   public String getPageType() {
      return this.pageType;
   }

   public void setPageType(String pageType) {
      this.pageType = pageType;
   }

   public String getCommonType() {
      return this.commonType;
   }

   public void setCommonType(String commonType) {
      this.commonType = commonType;
   }

   public List getItemCdList() {
      return this.itemCdList;
   }

   public void setItemCdList(List itemCdList) {
      this.itemCdList = itemCdList;
   }

   public String getDocumentTypeNo() {
      return this.documentTypeNo;
   }

   public void setDocumentTypeNo(String documentTypeNo) {
      this.documentTypeNo = documentTypeNo;
   }

   public String getItemClassCd() {
      return this.itemClassCd;
   }

   public void setItemClassCd(String itemClassCd) {
      this.itemClassCd = itemClassCd;
   }

   public String getKeyWord() {
      return this.keyWord;
   }

   public void setKeyWord(String keyWord) {
      this.keyWord = keyWord;
   }

   public String getOrderClassCode() {
      return this.orderClassCode;
   }

   public void setOrderClassCode(String orderClassCode) {
      this.orderClassCode = orderClassCode;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public Integer getPageSize() {
      return this.pageSize;
   }

   public void setPageSize(Integer pageSize) {
      this.pageSize = pageSize;
   }
}
