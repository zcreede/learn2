package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpIcu;
import com.emr.project.mrhp.service.IMrHpIcuService;
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
@RequestMapping({"/mrhp/icu"})
public class MrHpIcuController extends BaseController {
   @Autowired
   private IMrHpIcuService mrHpIcuService;

   @PreAuthorize("@ss.hasPermi('mrhp:icu:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpIcu mrHpIcu) {
      this.startPage();
      List<MrHpIcu> list = this.mrHpIcuService.selectMrHpIcuList(mrHpIcu);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:icu:export')")
   @Log(
      title = "病案附页-重症监护室",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpIcu mrHpIcu) {
      List<MrHpIcu> list = this.mrHpIcuService.selectMrHpIcuList(mrHpIcu);
      ExcelUtil<MrHpIcu> util = new ExcelUtil(MrHpIcu.class);
      return util.exportExcel(list, "病案附页-重症监护室数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:icu:query')")
   @GetMapping({"/{recordId}"})
   public AjaxResult getInfo(@PathVariable("recordId") String recordId) {
      return AjaxResult.success((Object)this.mrHpIcuService.selectMrHpIcuById(recordId));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:icu:add')")
   @Log(
      title = "病案附页-重症监护室",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpIcu mrHpIcu) {
      return this.toAjax(this.mrHpIcuService.insertMrHpIcu(mrHpIcu));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:icu:edit')")
   @Log(
      title = "病案附页-重症监护室",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpIcu mrHpIcu) {
      return this.toAjax(this.mrHpIcuService.updateMrHpIcu(mrHpIcu));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:icu:remove')")
   @Log(
      title = "病案附页-重症监护室",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{recordIds}"})
   public AjaxResult remove(@PathVariable String[] recordIds) {
      return this.toAjax(this.mrHpIcuService.deleteMrHpIcuByIds(recordIds));
   }
}
