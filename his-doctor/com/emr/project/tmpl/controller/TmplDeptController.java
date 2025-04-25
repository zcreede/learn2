package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.TmplDept;
import com.emr.project.tmpl.service.ITmplDeptService;
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
@RequestMapping({"/tmpl/dept"})
public class TmplDeptController extends BaseController {
   @Autowired
   private ITmplDeptService tmplDeptService;

   @PreAuthorize("@ss.hasPermi('tmpl:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplDept tmplDept) {
      this.startPage();
      List<TmplDept> list = this.tmplDeptService.selectTmplDeptList(tmplDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:dept:export')")
   @Log(
      title = "病历模板与科室关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmplDept tmplDept) {
      List<TmplDept> list = this.tmplDeptService.selectTmplDeptList(tmplDept);
      ExcelUtil<TmplDept> util = new ExcelUtil(TmplDept.class);
      return util.exportExcel(list, "病历模板与科室关系数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:dept:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplDeptService.selectTmplDeptById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:dept:add')")
   @Log(
      title = "病历模板与科室关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplDept tmplDept) {
      return this.toAjax(this.tmplDeptService.insertTmplDept(tmplDept));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:dept:edit')")
   @Log(
      title = "病历模板与科室关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmplDept tmplDept) {
      return this.toAjax(this.tmplDeptService.updateTmplDept(tmplDept));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:dept:remove')")
   @Log(
      title = "病历模板与科室关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmplDeptService.deleteTmplDeptByIds(ids));
   }
}
