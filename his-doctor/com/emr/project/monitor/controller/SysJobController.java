package com.emr.project.monitor.controller;

import com.emr.common.exception.job.TaskException;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.job.CronUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.monitor.domain.SysJob;
import com.emr.project.monitor.service.ISysJobService;
import java.util.List;
import org.quartz.SchedulerException;
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
@RequestMapping({"/monitor/job"})
public class SysJobController extends BaseController {
   @Autowired
   private ISysJobService jobService;

   @PreAuthorize("@ss.hasPermi('monitor:job:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysJob sysJob) {
      this.startPage();
      List<SysJob> list = this.jobService.selectJobList(sysJob);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:export')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysJob sysJob) {
      List<SysJob> list = this.jobService.selectJobList(sysJob);
      ExcelUtil<SysJob> util = new ExcelUtil(SysJob.class);
      return util.exportExcel(list, "定时任务");
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:query')")
   @GetMapping({"/{jobId}"})
   public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
      return AjaxResult.success((Object)this.jobService.selectJobById(jobId));
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:add')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
      if (!CronUtils.isValid(sysJob.getCronExpression())) {
         return AjaxResult.error("新增任务'" + sysJob.getJobName() + "'失败，Cron表达式不正确");
      } else if (StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), "rmi://")) {
         return AjaxResult.error("新增任务'" + sysJob.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
      } else {
         sysJob.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.jobService.insertJob(sysJob));
      }
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
      if (!CronUtils.isValid(sysJob.getCronExpression())) {
         return AjaxResult.error("修改任务'" + sysJob.getJobName() + "'失败，Cron表达式不正确");
      } else if (StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), "rmi://")) {
         return AjaxResult.error("修改任务'" + sysJob.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
      } else {
         sysJob.setUpdateBy(SecurityUtils.getUsername());
         return this.toAjax(this.jobService.updateJob(sysJob));
      }
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/changeStatus"})
   public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
      SysJob newJob = this.jobService.selectJobById(job.getJobId());
      newJob.setStatus(job.getStatus());
      return this.toAjax(this.jobService.changeStatus(newJob));
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/run"})
   public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {
      AjaxResult ajaxResult = AjaxResult.success("执行成功");

      try {
         this.jobService.run(job);
      } catch (Exception var4) {
         ajaxResult = AjaxResult.error("执行定时任务异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
   @Log(
      title = "定时任务",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{jobIds}"})
   public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException {
      this.jobService.deleteJobByIds(jobIds);
      return AjaxResult.success();
   }
}
