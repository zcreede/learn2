package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.TmplQuoteElem;
import com.emr.project.tmpl.service.ITmplQuoteElemService;
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
@RequestMapping({"/tmpl/elem"})
public class TmplQuoteElemController extends BaseController {
   @Autowired
   private ITmplQuoteElemService tmplQuoteElemService;

   @PreAuthorize("@ss.hasPermi('tmpl:elem:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplQuoteElem tmplQuoteElem) {
      this.startPage();
      List<TmplQuoteElem> list = this.tmplQuoteElemService.selectTmplQuoteElemList(tmplQuoteElem);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:elem:export')")
   @Log(
      title = "模板自动引用元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmplQuoteElem tmplQuoteElem) {
      List<TmplQuoteElem> list = this.tmplQuoteElemService.selectTmplQuoteElemList(tmplQuoteElem);
      ExcelUtil<TmplQuoteElem> util = new ExcelUtil(TmplQuoteElem.class);
      return util.exportExcel(list, "模板自动引用元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:elem:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplQuoteElemService.selectTmplQuoteElemById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:elem:add')")
   @Log(
      title = "模板自动引用元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplQuoteElem tmplQuoteElem) {
      return this.toAjax(this.tmplQuoteElemService.insertTmplQuoteElem(tmplQuoteElem));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:elem:edit')")
   @Log(
      title = "模板自动引用元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmplQuoteElem tmplQuoteElem) {
      return this.toAjax(this.tmplQuoteElemService.updateTmplQuoteElem(tmplQuoteElem));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:elem:remove')")
   @Log(
      title = "模板自动引用元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmplQuoteElemService.deleteTmplQuoteElemByIds(ids));
   }
}
