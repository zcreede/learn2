package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.service.IElemSignService;
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
@RequestMapping({"/tmpl/sign"})
public class ElemSignController extends BaseController {
   @Autowired
   private IElemSignService elemSignService;

   @PreAuthorize("@ss.hasPermi('tmpl:sign:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemSign elemSign) {
      this.startPage();
      List<ElemSign> list = this.elemSignService.selectElemSignList(elemSign);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:sign:export')")
   @Log(
      title = "模板签名元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemSign elemSign) {
      List<ElemSign> list = this.elemSignService.selectElemSignList(elemSign);
      ExcelUtil<ElemSign> util = new ExcelUtil(ElemSign.class);
      return util.exportExcel(list, "模板签名元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:sign:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemSignService.selectElemSignById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:sign:add')")
   @Log(
      title = "模板签名元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemSign elemSign) {
      return this.toAjax(this.elemSignService.insertElemSign(elemSign));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:sign:edit')")
   @Log(
      title = "模板签名元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemSign elemSign) {
      return this.toAjax(this.elemSignService.updateElemSign(elemSign));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:sign:remove')")
   @Log(
      title = "模板签名元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemSignService.deleteElemSignByIds(ids));
   }
}
