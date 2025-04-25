package com.emr.project.esSearch.domain.vo;

import com.emr.project.emr.domain.EmrSearchCaseDetail;
import java.util.List;

public class EmrFileSearchVo {
   private Integer pageNum;
   private Integer pageSize;
   private Integer searchType;
   private List detailList;
   private String searchName;
   private String keyWords;
   private String caseFlag;

   public String getCaseFlag() {
      return this.caseFlag;
   }

   public void setCaseFlag(String caseFlag) {
      this.caseFlag = caseFlag;
   }

   public String getKeyWords() {
      return this.keyWords;
   }

   public void setKeyWords(String keyWords) {
      this.keyWords = keyWords;
   }

   public String getSearchName() {
      return this.searchName;
   }

   public void setSearchName(String searchName) {
      this.searchName = searchName;
   }

   public List getDetailList() {
      return this.detailList;
   }

   public void setDetailList(List detailList) {
      this.detailList = detailList;
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

   public Integer getSearchType() {
      return this.searchType;
   }

   public void setSearchType(Integer searchType) {
      this.searchType = searchType;
   }
}
