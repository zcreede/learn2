package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.service.IMrHpOpeService;
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
@RequestMapping({"/mrhp/hp/ope"})
public class MrHpOpeController extends BaseController {
   @Autowired
   private IMrHpOpeService mrHpOpeService;

   @PreAuthorize("@ss.hasPermi('mrhp:ope:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpOpe mrHpOpe) {
      this.startPage();
      List<MrHpOpe> list = this.mrHpOpeService.selectMrHpOpeList(mrHpOpe);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:ope:export')")
   @Log(
      title = "711病案首页-手术",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpOpe mrHpOpe) {
      List<MrHpOpe> list = this.mrHpOpeService.selectMrHpOpeList(mrHpOpe);
      ExcelUtil<MrHpOpe> util = new ExcelUtil(MrHpOpe.class);
      return util.exportExcel(list, "711病案首页-手术数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:ope:query')")
   @GetMapping({"/{opeId}"})
   public AjaxResult getInfo(@PathVariable("opeId") String opeId) {
      return AjaxResult.success((Object)this.mrHpOpeService.selectMrHpOpeById(opeId));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:ope:add')")
   @Log(
      title = "711病案首页-手术",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpOpe mrHpOpe) {
      return this.toAjax(this.mrHpOpeService.insertMrHpOpe(mrHpOpe));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:ope:edit')")
   @Log(
      title = "711病案首页-手术",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpOpe mrHpOpe) {
      return this.toAjax(this.mrHpOpeService.updateMrHpOpe(mrHpOpe));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:ope:remove')")
   @Log(
      title = "711病案首页-手术",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{opeIds}"})
   public AjaxResult remove(@PathVariable String[] opeIds) {
      return this.toAjax(this.mrHpOpeService.deleteMrHpOpeByIds(opeIds));
   }
}
