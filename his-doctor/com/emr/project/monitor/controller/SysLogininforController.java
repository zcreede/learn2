package com.emr.project.monitor.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.monitor.domain.SysLogininfor;
import com.emr.project.monitor.service.ISysLogininforService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/logininfor"})
public class SysLogininforController extends BaseController {
   @Autowired
   private ISysLogininforService logininforService;

   @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysLogininfor logininfor) {
      this.startPage();
      List<SysLogininfor> list = this.logininforService.selectLogininforList(logininfor);
      return this.getDataTable(list);
   }

   @Log(
      title = "登录日志",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysLogininfor logininfor) {
      List<SysLogininfor> list = this.logininforService.selectLogininforList(logininfor);
      ExcelUtil<SysLogininfor> util = new ExcelUtil(SysLogininfor.class);
      return util.exportExcel(list, "登录日志");
   }

   @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
   @Log(
      title = "登录日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{infoIds}"})
   public AjaxResult remove(@PathVariable Long[] infoIds) {
      return this.toAjax(this.logininforService.deleteLogininforByIds(infoIds));
   }

   @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
   @Log(
      title = "登录日志",
      businessType = BusinessType.CLEAN
   )
   @DeleteMapping({"/clean"})
   public AjaxResult clean() {
      this.logininforService.cleanLogininfor();
      return AjaxResult.success();
   }
}
