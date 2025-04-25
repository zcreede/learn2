package com.emr.framework.web.page;

import com.emr.common.utils.StringUtils;

public class PageDomain {
   private Integer pageNum;
   private Integer pageSize;
   private String orderByColumn;
   private String isAsc = "asc";

   public String getOrderBy() {
      return StringUtils.isEmpty(this.orderByColumn) ? "" : StringUtils.toUnderScoreCase(this.orderByColumn) + " " + this.isAsc;
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

   public String getOrderByColumn() {
      return this.orderByColumn;
   }

   public void setOrderByColumn(String orderByColumn) {
      this.orderByColumn = orderByColumn;
   }

   public String getIsAsc() {
      return this.isAsc;
   }

   public void setIsAsc(String isAsc) {
      if (StringUtils.isNotEmpty(isAsc)) {
         if ("ascending".equals(isAsc)) {
            isAsc = "asc";
         } else if ("descending".equals(isAsc)) {
            isAsc = "desc";
         }

         this.isAsc = isAsc;
      }

   }
}
