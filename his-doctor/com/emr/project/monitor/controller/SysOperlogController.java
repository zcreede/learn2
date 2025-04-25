package com.emr.project.monitor.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.monitor.domain.SysOperLog;
import com.emr.project.monitor.service.ISysOperLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/operlog"})
public class SysOperlogController extends BaseController {
   @Autowired
   private ISysOperLogService operLogService;

   @PreAuthorize("@ss.hasPermi('monitor:operlog:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysOperLog operLog) {
      this.startPage();
      List<SysOperLog> list = this.operLogService.selectOperLogList(operLog);
      return this.getDataTable(list);
   }

   @Log(
      title = "操作日志",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysOperLog operLog) {
      List<SysOperLog> list = this.operLogService.selectOperLogList(operLog);
      ExcelUtil<SysOperLog> util = new ExcelUtil(SysOperLog.class);
      return util.exportExcel(list, "操作日志");
   }

   @Log(
      title = "操作日志",
      businessType = BusinessType.DELETE
   )
   @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
   @DeleteMapping({"/{operIds}"})
   public AjaxResult remove(@PathVariable Long[] operIds) {
      return this.toAjax(this.operLogService.deleteOperLogByIds(operIds));
   }

   @Log(
      title = "操作日志",
      businessType = BusinessType.CLEAN
   )
   @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
   @DeleteMapping({"/clean"})
   public AjaxResult clean() {
      this.operLogService.cleanOperLog();
      return AjaxResult.success();
   }
}
