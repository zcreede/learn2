package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.service.IEmrQcFlowRecordService;
import java.util.ArrayList;
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
@RequestMapping({"/qc/flowRecord"})
public class EmrQcFlowRecordController extends BaseController {
   @Autowired
   private IEmrQcFlowRecordService emrQcFlowRecordService;

   @PreAuthorize("@ss.hasPermi('qc:record:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcFlowRecord emrQcFlowRecord) {
      this.startPage();
      List<EmrQcFlowRecord> list = this.emrQcFlowRecordService.selectEmrQcFlowRecordList(emrQcFlowRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:dept,qc:flow:term')")
   @GetMapping({"/qclist"})
   public TableDataInfo qclist(EmrQcFlowRecord emrQcFlowRecord) {
      this.startPage();
      List<EmrQcFlowRecord> list = new ArrayList();

      try {
         list = this.emrQcFlowRecordService.selectEmrQcFlowRecordList(emrQcFlowRecord);
      } catch (Exception e) {
         this.log.error("查询病历质控流程操作记录列表", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:record:export')")
   @Log(
      title = "病历质控流程操作记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcFlowRecord emrQcFlowRecord) {
      List<EmrQcFlowRecord> list = this.emrQcFlowRecordService.selectEmrQcFlowRecordList(emrQcFlowRecord);
      ExcelUtil<EmrQcFlowRecord> util = new ExcelUtil(EmrQcFlowRecord.class);
      return util.exportExcel(list, "病历质控流程操作记录数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:record:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcFlowRecordService.selectEmrQcFlowRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:record:add')")
   @Log(
      title = "病历质控流程操作记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcFlowRecord emrQcFlowRecord) {
      return this.toAjax(this.emrQcFlowRecordService.insertEmrQcFlowRecord(emrQcFlowRecord));
   }

   @PreAuthorize("@ss.hasPermi('qc:record:edit')")
   @Log(
      title = "病历质控流程操作记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcFlowRecord emrQcFlowRecord) {
      return this.toAjax(this.emrQcFlowRecordService.updateEmrQcFlowRecord(emrQcFlowRecord));
   }

   @PreAuthorize("@ss.hasPermi('qc:record:remove')")
   @Log(
      title = "病历质控流程操作记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcFlowRecordService.deleteEmrQcFlowRecordByIds(ids));
   }
}
