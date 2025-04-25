package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.service.IElemGenderService;
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
@RequestMapping({"/tmpl/gender"})
public class ElemGenderController extends BaseController {
   @Autowired
   private IElemGenderService elemGenderService;

   @PreAuthorize("@ss.hasPermi('tmpl:gender:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ElemGender elemGender) {
      this.startPage();
      List<ElemGender> list = this.elemGenderService.selectElemGenderList(elemGender);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpl:gender:export')")
   @Log(
      title = "模板性别元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ElemGender elemGender) {
      List<ElemGender> list = this.elemGenderService.selectElemGenderList(elemGender);
      ExcelUtil<ElemGender> util = new ExcelUtil(ElemGender.class);
      return util.exportExcel(list, "模板性别元素数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:gender:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.elemGenderService.selectElemGenderById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:gender:add')")
   @Log(
      title = "模板性别元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ElemGender elemGender) {
      return this.toAjax(this.elemGenderService.insertElemGender(elemGender));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:gender:edit')")
   @Log(
      title = "模板性别元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ElemGender elemGender) {
      return this.toAjax(this.elemGenderService.updateElemGender(elemGender));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:gender:remove')")
   @Log(
      title = "模板性别元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.elemGenderService.deleteElemGenderByIds(ids));
   }
}
