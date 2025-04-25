package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.service.IElemMacroService;
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
@RequestMapping({"/tmpl/macro"})
public class ElemMacroController extends BaseController {
   @Autowired
   private IElemMacroService elemMacroService;

   @PreAuthorize("@ss.hasPermi('tmpl:macro:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemMacro elemMacro) {
      this.startPage();
      List<ElemMacro> list = this.elemMacroService.selectElemMacroList(elemMacro);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:macro:export')")
   @Log(
      title = "模板宏元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemMacro elemMacro) {
      List<ElemMacro> list = this.elemMacroService.selectElemMacroList(elemMacro);
      ExcelUtil<ElemMacro> util = new ExcelUtil(ElemMacro.class);
      return util.exportExcel(list, "模板宏元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:macro:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemMacroService.selectElemMacroById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:macro:add')")
   @Log(
      title = "模板宏元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemMacro elemMacro) {
      return this.toAjax(this.elemMacroService.insertElemMacro(elemMacro));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:macro:edit')")
   @Log(
      title = "模板宏元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemMacro elemMacro) {
      return this.toAjax(this.elemMacroService.updateElemMacro(elemMacro));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:macro:remove')")
   @Log(
      title = "模板宏元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemMacroService.deleteElemMacroByIds(ids));
   }
}
