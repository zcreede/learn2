package com.emr.project.pat.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.AppClin;
import com.emr.project.pat.service.IAppClinService;
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
@RequestMapping({"/pat/clin"})
public class AppClinController extends BaseController {
   @Autowired
   private IAppClinService appClinService;

   @PreAuthorize("@ss.hasPermi('pat:clin:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(AppClin appClin) {
      this.startPage();
      List<AppClin> list = this.appClinService.selectAppClinList(appClin);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:clin:export')")
   @Log(
      title = "申请单_临床项目",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(AppClin appClin) {
      List<AppClin> list = this.appClinService.selectAppClinList(appClin);
      ExcelUtil<AppClin> util = new ExcelUtil(AppClin.class);
      return util.exportExcel(list, "申请单_临床项目数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:clin:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.appClinService.selectAppClinById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:clin:add')")
   @Log(
      title = "申请单_临床项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody AppClin appClin) {
      return this.toAjax(this.appClinService.insertAppClin(appClin));
   }

   @PreAuthorize("@ss.hasPermi('pat:clin:edit')")
   @Log(
      title = "申请单_临床项目",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody AppClin appClin) {
      return this.toAjax(this.appClinService.updateAppClin(appClin));
   }

   @PreAuthorize("@ss.hasPermi('pat:clin:remove')")
   @Log(
      title = "申请单_临床项目",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.appClinService.deleteAppClinByIds(ids));
   }
}
