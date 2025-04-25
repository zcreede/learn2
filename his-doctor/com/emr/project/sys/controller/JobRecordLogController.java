package com.emr.project.sys.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.JobRecordLog;
import com.emr.project.sys.service.IJobRecordLogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/sys/log"})
public class JobRecordLogController extends BaseController {
   @Autowired
   private IJobRecordLogService jobRecordLogService;

   @PreAuthorize("@ss.hasPermi('sys:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(JobRecordLog jobRecordLog) {
      this.startPage();
      List<JobRecordLog> list = this.jobRecordLogService.selectJobRecordLogList(jobRecordLog);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('sys:log:export')")
   @Log(
      title = "任务执行日志",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(JobRecordLog jobRecordLog) {
      List<JobRecordLog> list = this.jobRecordLogService.selectJobRecordLogList(jobRecordLog);
      ExcelUtil<JobRecordLog> util = new ExcelUtil(JobRecordLog.class);
      return util.exportExcel(list, "任务执行日志数据");
   }

   @PreAuthorize("@ss.hasPermi('sys:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.jobRecordLogService.selectJobRecordLogById(id));
   }

   @PreAuthorize("@ss.hasPermi('sys:log:add')")
   @Log(
      title = "任务执行日志",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody JobRecordLog jobRecordLog) {
      return this.toAjax(this.jobRecordLogService.insertJobRecordLog(jobRecordLog));
   }

   @PreAuthorize("@ss.hasPermi('sys:log:edit')")
   @Log(
      title = "任务执行日志",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody JobRecordLog jobRecordLog) {
      return this.toAjax(this.jobRecordLogService.updateJobRecordLog(jobRecordLog));
   }

   @PreAuthorize("@ss.hasPermi('sys:log:remove')")
   @Log(
      title = "任务执行日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.jobRecordLogService.deleteJobRecordLogByIds(ids));
   }
}
