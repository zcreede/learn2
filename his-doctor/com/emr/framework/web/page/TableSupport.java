package com.emr.framework.web.page;

import com.emr.common.utils.ServletUtils;

public class TableSupport {
   public static final String PAGE_NUM = "pageNum";
   public static final String PAGE_SIZE = "pageSize";
   public static final String ORDER_BY_COLUMN = "orderByColumn";
   public static final String IS_ASC = "isAsc";

   public static PageDomain getPageDomain() {
      PageDomain pageDomain = new PageDomain();
      pageDomain.setPageNum(ServletUtils.getParameterToInt("pageNum"));
      pageDomain.setPageSize(ServletUtils.getParameterToInt("pageSize"));
      pageDomain.setOrderByColumn(ServletUtils.getParameter("orderByColumn"));
      pageDomain.setIsAsc(ServletUtils.getParameter("isAsc"));
      return pageDomain;
   }

   public static PageDomain buildPageRequest() {
      return getPageDomain();
   }
}
