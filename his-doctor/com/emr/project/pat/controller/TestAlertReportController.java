package com.emr.project.pat.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.TestAlertReport;
import com.emr.project.pat.service.ITestAlertReportService;
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
@RequestMapping({"/pat/reportAlert"})
public class TestAlertReportController extends BaseController {
   @Autowired
   private ITestAlertReportService testAlertReportService;

   @PreAuthorize("@ss.hasPermi('pat:report:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TestAlertReport testAlertReport) {
      this.startPage();
      List<TestAlertReport> list = this.testAlertReportService.selectTestAlertReportList(testAlertReport);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:report:export')")
   @Log(
      title = "检验危急值",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TestAlertReport testAlertReport) {
      List<TestAlertReport> list = this.testAlertReportService.selectTestAlertReportList(testAlertReport);
      ExcelUtil<TestAlertReport> util = new ExcelUtil(TestAlertReport.class);
      return util.exportExcel(list, "检验危急值数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:report:query')")
   @GetMapping({"/{resultalertid}"})
   public AjaxResult getInfo(@PathVariable("resultalertid") String resultalertid) {
      return AjaxResult.success((Object)this.testAlertReportService.selectTestAlertReportById(resultalertid));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:add')")
   @Log(
      title = "检验危急值",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TestAlertReport testAlertReport) {
      return this.toAjax(this.testAlertReportService.insertTestAlertReport(testAlertReport));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:edit')")
   @Log(
      title = "检验危急值",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TestAlertReport testAlertReport) {
      return this.toAjax(this.testAlertReportService.updateTestAlertReport(testAlertReport));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:remove')")
   @Log(
      title = "检验危急值",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{resultalertids}"})
   public AjaxResult remove(@PathVariable String[] resultalertids) {
      return this.toAjax(this.testAlertReportService.deleteTestAlertReportByIds(resultalertids));
   }
}
