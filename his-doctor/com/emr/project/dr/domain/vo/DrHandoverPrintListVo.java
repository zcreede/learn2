package com.emr.project.dr.domain.vo;

import java.util.List;

public class DrHandoverPrintListVo {
   private List list;
   private Integer pageNum;
   private Integer total;
   List pageDataList;

   public List getList() {
      return this.list;
   }

   public void setList(List list) {
      this.list = list;
   }

   public Integer getPageNum() {
      return this.pageNum;
   }

   public void setPageNum(Integer pageNum) {
      this.pageNum = pageNum;
   }

   public Integer getTotal() {
      return this.total;
   }

   public void setTotal(Integer total) {
      this.total = total;
   }

   public List getPageDataList() {
      return this.pageDataList;
   }

   public void setPageDataList(List pageDataList) {
      this.pageDataList = pageDataList;
   }
}
