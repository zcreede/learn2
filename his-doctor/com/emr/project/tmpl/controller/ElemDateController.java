package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.service.IElemDateService;
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
@RequestMapping({"/tmpl/date"})
public class ElemDateController extends BaseController {
   @Autowired
   private IElemDateService elemDateService;

   @PreAuthorize("@ss.hasPermi('tmpl:date:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemDate elemDate) {
      this.startPage();
      List<ElemDate> list = this.elemDateService.selectElemDateList(elemDate);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:date:export')")
   @Log(
      title = "模板日期元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemDate elemDate) {
      List<ElemDate> list = this.elemDateService.selectElemDateList(elemDate);
      ExcelUtil<ElemDate> util = new ExcelUtil(ElemDate.class);
      return util.exportExcel(list, "模板日期元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:date:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemDateService.selectElemDateById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:date:add')")
   @Log(
      title = "模板日期元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemDate elemDate) {
      return this.toAjax(this.elemDateService.insertElemDate(elemDate));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:date:edit')")
   @Log(
      title = "模板日期元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemDate elemDate) {
      return this.toAjax(this.elemDateService.updateElemDate(elemDate));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:date:remove')")
   @Log(
      title = "模板日期元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemDateService.deleteElemDateByIds(ids));
   }
}
