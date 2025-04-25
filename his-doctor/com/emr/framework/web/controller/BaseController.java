package com.emr.framework.web.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.framework.web.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
         public void setAsText(String text) {
            this.setValue(DateUtils.parseDate(text));
         }
      });
   }

   protected void startPage() {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

   }

   protected void startPage(PageDomain pageDomain) {
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

   }

   protected void startOrderBy() {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.orderBy(orderBy);
      }

   }

   protected TableDataInfo getDataTable(List list) {
      TableDataInfo rspData = new TableDataInfo();
      rspData.setCode(200);
      rspData.setMsg("查询成功");
      rspData.setRows(list);
      rspData.setTotal((new PageInfo(list)).getTotal());
      return rspData;
   }

   protected TableDataInfo getDataTable(List list, Long total) {
      TableDataInfo rspData = new TableDataInfo();
      rspData.setCode(200);
      rspData.setMsg("查询成功");
      rspData.setRows(list);
      rspData.setTotal(total);
      return rspData;
   }

   protected AjaxResult toAjax(boolean result) {
      return result ? this.success() : this.error();
   }

   public AjaxResult success() {
      return AjaxResult.success();
   }

   public AjaxResult error() {
      return AjaxResult.error();
   }

   public AjaxResult success(String message) {
      return AjaxResult.success(message);
   }

   public AjaxResult error(String message) {
      return AjaxResult.error(message);
   }

   protected AjaxResult toAjax(int rows) {
      return rows > 0 ? AjaxResult.success() : AjaxResult.error();
   }
}
