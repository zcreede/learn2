package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemPhysign;
import com.emr.project.tmpl.service.IElemPhysignService;
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
@RequestMapping({"/tmpl/physign"})
public class ElemPhysignController extends BaseController {
   @Autowired
   private IElemPhysignService elemPhysignService;

   @PreAuthorize("@ss.hasPermi('tmpl:physign:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemPhysign elemPhysign) {
      this.startPage();
      List<ElemPhysign> list = this.elemPhysignService.selectElemPhysignList(elemPhysign);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:physign:export')")
   @Log(
      title = "模板体征元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemPhysign elemPhysign) {
      List<ElemPhysign> list = this.elemPhysignService.selectElemPhysignList(elemPhysign);
      ExcelUtil<ElemPhysign> util = new ExcelUtil(ElemPhysign.class);
      return util.exportExcel(list, "模板体征元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:physign:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemPhysignService.selectElemPhysignById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:physign:add')")
   @Log(
      title = "模板体征元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemPhysign elemPhysign) {
      return this.toAjax(this.elemPhysignService.insertElemPhysign(elemPhysign));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:physign:edit')")
   @Log(
      title = "模板体征元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemPhysign elemPhysign) {
      return this.toAjax(this.elemPhysignService.updateElemPhysign(elemPhysign));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:physign:remove')")
   @Log(
      title = "模板体征元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemPhysignService.deleteElemPhysignByIds(ids));
   }
}
