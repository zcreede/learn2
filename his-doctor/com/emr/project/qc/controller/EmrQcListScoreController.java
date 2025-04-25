package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.EmrQcListScore;
import com.emr.project.qc.service.IEmrQcListScoreService;
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
@RequestMapping({"/docOrder/score"})
public class EmrQcListScoreController extends BaseController {
   @Autowired
   private IEmrQcListScoreService emrQcListScoreService;

   @PreAuthorize("@ss.hasPermi('docOrder:score:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcListScore emrQcListScore) {
      this.startPage();
      List<EmrQcListScore> list = this.emrQcListScoreService.selectEmrQcListScoreList(emrQcListScore);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:score:export')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcListScore emrQcListScore) {
      List<EmrQcListScore> list = this.emrQcListScoreService.selectEmrQcListScoreList(emrQcListScore);
      ExcelUtil<EmrQcListScore> util = new ExcelUtil(EmrQcListScore.class);
      return util.exportExcel(list, "【请填写功能名称】数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:score:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcListScoreService.selectEmrQcListScoreById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:score:add')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcListScore emrQcListScore) {
      return this.toAjax(this.emrQcListScoreService.insertEmrQcListScore(emrQcListScore));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:score:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcListScore emrQcListScore) {
      return this.toAjax(this.emrQcListScoreService.updateEmrQcListScore(emrQcListScore));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:score:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcListScoreService.deleteEmrQcListScoreByIds(ids));
   }
}
