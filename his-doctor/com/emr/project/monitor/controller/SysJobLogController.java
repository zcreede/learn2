package com.emr.project.monitor.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.monitor.domain.SysJobLog;
import com.emr.project.monitor.service.ISysJobLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/jobLog"})
public class SysJobLogController extends BaseController {
   @Autowired
   private ISysJobLogService jobLogService;

   @PreAuthorize("@ss.hasPermi('monitor:job:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysJobLog sysJobLog) {
      this.startPage();
      List<SysJobLog> list = this.jobLogService.selectJobLogList(sysJobLog);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:export')")
   @Log(
      title = "任务调度日志",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysJobLog sysJobLog) {
      List<SysJobLog> list = this.jobLogService.selectJobLogList(sysJobLog);
      ExcelUtil<SysJobLog> util = new ExcelUtil(SysJobLog.class);
      return util.exportExcel(list, "调度日志");
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:query')")
   @GetMapping({"/{configId}"})
   public AjaxResult getInfo(@PathVariable Long jobLogId) {
      return AjaxResult.success((Object)this.jobLogService.selectJobLogById(jobLogId));
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
   @Log(
      title = "定时任务调度日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{jobLogIds}"})
   public AjaxResult remove(@PathVariable Long[] jobLogIds) {
      return this.toAjax(this.jobLogService.deleteJobLogByIds(jobLogIds));
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
   @Log(
      title = "调度日志",
      businessType = BusinessType.CLEAN
   )
   @DeleteMapping({"/clean"})
   public AjaxResult clean() {
      this.jobLogService.cleanJobLog();
      return AjaxResult.success();
   }
}
