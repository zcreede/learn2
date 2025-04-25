package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.EmrQcFlowScoreList;
import com.emr.project.qc.service.IEmrQcFlowScoreListService;
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
@RequestMapping({"/qc/list"})
public class EmrQcFlowScoreListController extends BaseController {
   @Autowired
   private IEmrQcFlowScoreListService emrQcFlowScoreListService;

   @PreAuthorize("@ss.hasPermi('qc:list:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcFlowScoreList emrQcFlowScoreList) {
      this.startPage();
      List<EmrQcFlowScoreList> list = this.emrQcFlowScoreListService.selectEmrQcFlowScoreListList(emrQcFlowScoreList);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:list:export')")
   @Log(
      title = "质控流程评分明细与病历缺陷关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcFlowScoreList emrQcFlowScoreList) {
      List<EmrQcFlowScoreList> list = this.emrQcFlowScoreListService.selectEmrQcFlowScoreListList(emrQcFlowScoreList);
      ExcelUtil<EmrQcFlowScoreList> util = new ExcelUtil(EmrQcFlowScoreList.class);
      return util.exportExcel(list, "质控流程评分明细与病历缺陷关系数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:list:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcFlowScoreListService.selectEmrQcFlowScoreListById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:list:add')")
   @Log(
      title = "质控流程评分明细与病历缺陷关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcFlowScoreList emrQcFlowScoreList) {
      return this.toAjax(this.emrQcFlowScoreListService.insertEmrQcFlowScoreList(emrQcFlowScoreList));
   }

   @PreAuthorize("@ss.hasPermi('qc:list:edit')")
   @Log(
      title = "质控流程评分明细与病历缺陷关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcFlowScoreList emrQcFlowScoreList) {
      return this.toAjax(this.emrQcFlowScoreListService.updateEmrQcFlowScoreList(emrQcFlowScoreList));
   }

   @PreAuthorize("@ss.hasPermi('qc:list:remove')")
   @Log(
      title = "质控流程评分明细与病历缺陷关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcFlowScoreListService.deleteEmrQcFlowScoreListByIds(ids));
   }
}
