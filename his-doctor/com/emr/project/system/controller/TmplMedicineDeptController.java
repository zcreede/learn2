package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.TmplMedicineDept;
import com.emr.project.system.service.ITmplMedicineDeptService;
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
@RequestMapping({"/docOrder/dept"})
public class TmplMedicineDeptController extends BaseController {
   @Autowired
   private ITmplMedicineDeptService tmplMedicineDeptService;

   @PreAuthorize("@ss.hasPermi('docOrder:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplMedicineDept tmplMedicineDept) {
      this.startPage();
      List<TmplMedicineDept> list = this.tmplMedicineDeptService.selectTmplMedicineDeptList(tmplMedicineDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:dept:export')")
   @Log(
      title = "病历模板专科(二级)与科室关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmplMedicineDept tmplMedicineDept) {
      List<TmplMedicineDept> list = this.tmplMedicineDeptService.selectTmplMedicineDeptList(tmplMedicineDept);
      ExcelUtil<TmplMedicineDept> util = new ExcelUtil(TmplMedicineDept.class);
      return util.exportExcel(list, "病历模板专科(二级)与科室关系数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:dept:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplMedicineDeptService.selectTmplMedicineDeptById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:dept:add')")
   @Log(
      title = "病历模板专科(二级)与科室关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplMedicineDept tmplMedicineDept) {
      return this.toAjax(this.tmplMedicineDeptService.insertTmplMedicineDept(tmplMedicineDept));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:dept:edit')")
   @Log(
      title = "病历模板专科(二级)与科室关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmplMedicineDept tmplMedicineDept) {
      return this.toAjax(this.tmplMedicineDeptService.updateTmplMedicineDept(tmplMedicineDept));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:dept:remove')")
   @Log(
      title = "病历模板专科(二级)与科室关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmplMedicineDeptService.deleteTmplMedicineDeptByIds(ids));
   }
}
