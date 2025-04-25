package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.service.IMrHpAttachService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mrhp/attach"})
public class MrHpAttachController extends BaseController {
   @Autowired
   private IMrHpAttachService mrHpAttachService;

   @PreAuthorize("@ss.hasPermi('mrhp:attach:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpAttach mrHpAttach) {
      this.startPage();
      List<MrHpAttach> list = this.mrHpAttachService.selectMrHpAttachList(mrHpAttach);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:attach:export')")
   @Log(
      title = "病案附页",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpAttach mrHpAttach) {
      List<MrHpAttach> list = this.mrHpAttachService.selectMrHpAttachList(mrHpAttach);
      ExcelUtil<MrHpAttach> util = new ExcelUtil(MrHpAttach.class);
      return util.exportExcel(list, "病案附页数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:attach:query')")
   @GetMapping({"/{recordId}"})
   public AjaxResult getInfo(@PathVariable("recordId") String recordId) {
      return AjaxResult.success((Object)this.mrHpAttachService.selectMrHpAttachById(recordId));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:attach:edit')")
   @Log(
      title = "病案附页",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpAttach mrHpAttach) {
      return this.toAjax(this.mrHpAttachService.updateMrHpAttach(mrHpAttach));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:attach:remove')")
   @Log(
      title = "病案附页",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{recordIds}"})
   public AjaxResult remove(@PathVariable String[] recordIds) {
      return this.toAjax(this.mrHpAttachService.deleteMrHpAttachByIds(recordIds));
   }
}
