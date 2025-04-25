package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.service.IElemAttriService;
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
@RequestMapping({"/tmpl/attri"})
public class ElemAttriController extends BaseController {
   @Autowired
   private IElemAttriService elemAttriService;

   @PreAuthorize("@ss.hasPermi('tmpl:attri:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemAttri elemAttri) {
      this.startPage();
      List<ElemAttri> list = this.elemAttriService.selectElemAttriList(elemAttri);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:attri:export')")
   @Log(
      title = "模板元素属性",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemAttri elemAttri) {
      List<ElemAttri> list = this.elemAttriService.selectElemAttriList(elemAttri);
      ExcelUtil<ElemAttri> util = new ExcelUtil(ElemAttri.class);
      return util.exportExcel(list, "模板元素属性数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:attri:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemAttriService.selectElemAttriById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:attri:add')")
   @Log(
      title = "模板元素属性",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemAttri elemAttri) {
      return this.toAjax(this.elemAttriService.insertElemAttri(elemAttri));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:attri:edit')")
   @Log(
      title = "模板元素属性",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemAttri elemAttri) {
      return this.toAjax(this.elemAttriService.updateElemAttri(elemAttri));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:attri:remove')")
   @Log(
      title = "模板元素属性",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemAttriService.deleteElemAttriByIds(ids));
   }
}
