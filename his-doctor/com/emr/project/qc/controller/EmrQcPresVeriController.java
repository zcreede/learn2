package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.EmrQcPresVeri;
import com.emr.project.qc.service.IEmrQcPresVeriService;
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
@RequestMapping({"/qc/veri"})
public class EmrQcPresVeriController extends BaseController {
   @Autowired
   private IEmrQcPresVeriService emrQcPresVeriService;

   @PreAuthorize("@ss.hasPermi('qc:veri:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcPresVeri emrQcPresVeri) {
      this.startPage();
      List<EmrQcPresVeri> list = this.emrQcPresVeriService.selectEmrQcPresVeriList(emrQcPresVeri);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:veri:export')")
   @Log(
      title = "病历时效校验记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcPresVeri emrQcPresVeri) {
      List<EmrQcPresVeri> list = this.emrQcPresVeriService.selectEmrQcPresVeriList(emrQcPresVeri);
      ExcelUtil<EmrQcPresVeri> util = new ExcelUtil(EmrQcPresVeri.class);
      return util.exportExcel(list, "病历时效校验记录数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:veri:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcPresVeriService.selectEmrQcPresVeriById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:veri:add')")
   @Log(
      title = "病历时效校验记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcPresVeri emrQcPresVeri) {
      return this.toAjax(this.emrQcPresVeriService.insertEmrQcPresVeri(emrQcPresVeri));
   }

   @PreAuthorize("@ss.hasPermi('qc:veri:edit')")
   @Log(
      title = "病历时效校验记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcPresVeri emrQcPresVeri) {
      return this.toAjax(this.emrQcPresVeriService.updateEmrQcPresVeri(emrQcPresVeri));
   }

   @PreAuthorize("@ss.hasPermi('qc:veri:remove')")
   @Log(
      title = "病历时效校验记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcPresVeriService.deleteEmrQcPresVeriByIds(ids));
   }
}
