package com.emr.project.sys.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.JobRecord;
import com.emr.project.sys.service.IJobRecordService;
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
@RequestMapping({"/sys/record"})
public class JobRecordController extends BaseController {
   @Autowired
   private IJobRecordService jobRecordService;

   @PreAuthorize("@ss.hasPermi('sys:record:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(JobRecord jobRecord) {
      this.startPage();
      List<JobRecord> list = this.jobRecordService.selectJobRecordList(jobRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('sys:record:export')")
   @Log(
      title = "同步任务",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(JobRecord jobRecord) {
      List<JobRecord> list = this.jobRecordService.selectJobRecordList(jobRecord);
      ExcelUtil<JobRecord> util = new ExcelUtil(JobRecord.class);
      return util.exportExcel(list, "同步任务数据");
   }

   @PreAuthorize("@ss.hasPermi('sys:record:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.jobRecordService.selectJobRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('sys:record:add')")
   @Log(
      title = "同步任务",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody JobRecord jobRecord) {
      return this.toAjax(this.jobRecordService.insertJobRecord(jobRecord));
   }

   @PreAuthorize("@ss.hasPermi('sys:record:edit')")
   @Log(
      title = "同步任务",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody JobRecord jobRecord) {
      return this.toAjax(this.jobRecordService.updateJobRecord(jobRecord));
   }

   @PreAuthorize("@ss.hasPermi('sys:record:remove')")
   @Log(
      title = "同步任务",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.jobRecordService.deleteJobRecordByIds(ids));
   }
}
