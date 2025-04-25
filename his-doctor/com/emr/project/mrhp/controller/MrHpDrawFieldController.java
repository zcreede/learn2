package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.service.IMrHpDrawFieldService;
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
@RequestMapping({"/mrhp/field"})
public class MrHpDrawFieldController extends BaseController {
   @Autowired
   private IMrHpDrawFieldService mrHpDrawFieldService;

   @PreAuthorize("@ss.hasPermi('mrhp:field:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpDrawField mrHpDrawField) {
      this.startPage();
      List<MrHpDrawField> list = this.mrHpDrawFieldService.selectMrHpDrawFieldList(mrHpDrawField);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:field:export')")
   @Log(
      title = "病案提取字段映射",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpDrawField mrHpDrawField) {
      List<MrHpDrawField> list = this.mrHpDrawFieldService.selectMrHpDrawFieldList(mrHpDrawField);
      ExcelUtil<MrHpDrawField> util = new ExcelUtil(MrHpDrawField.class);
      return util.exportExcel(list, "病案提取字段映射数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:field:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.mrHpDrawFieldService.selectMrHpDrawFieldById(id));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:field:add')")
   @Log(
      title = "病案提取字段映射",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpDrawField mrHpDrawField) {
      return this.toAjax(this.mrHpDrawFieldService.insertMrHpDrawField(mrHpDrawField));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:field:edit')")
   @Log(
      title = "病案提取字段映射",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpDrawField mrHpDrawField) {
      return this.toAjax(this.mrHpDrawFieldService.updateMrHpDrawField(mrHpDrawField));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:field:remove')")
   @Log(
      title = "病案提取字段映射",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.mrHpDrawFieldService.deleteMrHpDrawFieldByIds(ids));
   }
}
