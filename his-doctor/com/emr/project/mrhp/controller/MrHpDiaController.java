package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.service.IMrHpDiaService;
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
@RequestMapping({"/mrhp/hp/dia"})
public class MrHpDiaController extends BaseController {
   @Autowired
   private IMrHpDiaService mrHpDiaService;

   @PreAuthorize("@ss.hasPermi('mrhp:dia:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpDia mrHpDia) {
      this.startPage();
      List<MrHpDia> list = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:dia:export')")
   @Log(
      title = "病案首页-出院诊断",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpDia mrHpDia) {
      List<MrHpDia> list = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
      ExcelUtil<MrHpDia> util = new ExcelUtil(MrHpDia.class);
      return util.exportExcel(list, "病案首页-出院诊断数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:dia:query')")
   @GetMapping({"/{diaId}"})
   public AjaxResult getInfo(@PathVariable("diaId") String diaId) {
      return AjaxResult.success((Object)this.mrHpDiaService.selectMrHpDiaById(diaId));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:dia:add')")
   @Log(
      title = "病案首页-出院诊断",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpDia mrHpDia) {
      return this.toAjax(this.mrHpDiaService.insertMrHpDia(mrHpDia));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:dia:edit')")
   @Log(
      title = "病案首页-出院诊断",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpDia mrHpDia) {
      return this.toAjax(this.mrHpDiaService.updateMrHpDia(mrHpDia));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:dia:remove')")
   @Log(
      title = "病案首页-出院诊断",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{diaIds}"})
   public AjaxResult remove(@PathVariable String[] diaIds) {
      return this.toAjax(this.mrHpDiaService.deleteMrHpDiaByIds(diaIds));
   }
}
