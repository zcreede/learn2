package com.emr.project.dr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverSchemeDept;
import com.emr.project.dr.service.IDrHandoverSchemeDeptService;
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
@RequestMapping({"/handover/dept"})
public class DrHandoverSchemeDeptController extends BaseController {
   @Autowired
   private IDrHandoverSchemeDeptService drHandoverSchemeDeptService;

   @PreAuthorize("@ss.hasPermi('dr:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverSchemeDept drHandoverSchemeDept) {
      this.startPage();
      List<DrHandoverSchemeDept> list = this.drHandoverSchemeDeptService.selectDrHandoverSchemeDeptList(drHandoverSchemeDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('dr:dept:export')")
   @Log(
      title = "交接班班次对应科室",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DrHandoverSchemeDept drHandoverSchemeDept) {
      List<DrHandoverSchemeDept> list = this.drHandoverSchemeDeptService.selectDrHandoverSchemeDeptList(drHandoverSchemeDept);
      ExcelUtil<DrHandoverSchemeDept> util = new ExcelUtil(DrHandoverSchemeDept.class);
      return util.exportExcel(list, "交接班班次对应科室数据");
   }

   @PreAuthorize("@ss.hasPermi('dr:dept:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.drHandoverSchemeDeptService.selectDrHandoverSchemeDeptById(id));
   }

   @PreAuthorize("@ss.hasPermi('dr:dept:add')")
   @Log(
      title = "交接班班次对应科室",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverSchemeDept drHandoverSchemeDept) {
      return this.toAjax(this.drHandoverSchemeDeptService.insertDrHandoverSchemeDept(drHandoverSchemeDept));
   }

   @PreAuthorize("@ss.hasPermi('dr:dept:edit')")
   @Log(
      title = "交接班班次对应科室",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverSchemeDept drHandoverSchemeDept) {
      return this.toAjax(this.drHandoverSchemeDeptService.updateDrHandoverSchemeDept(drHandoverSchemeDept));
   }

   @PreAuthorize("@ss.hasPermi('dr:dept:remove')")
   @Log(
      title = "交接班班次对应科室",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.drHandoverSchemeDeptService.deleteDrHandoverSchemeDeptByIds(ids));
   }
}
