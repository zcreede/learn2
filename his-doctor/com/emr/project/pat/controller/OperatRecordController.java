package com.emr.project.pat.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.OperatRecord;
import com.emr.project.pat.service.IOperatRecordService;
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
@RequestMapping({"/pat/record"})
public class OperatRecordController extends BaseController {
   @Autowired
   private IOperatRecordService operatRecordService;

   @PreAuthorize("@ss.hasPermi('pat:record:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(OperatRecord operatRecord) {
      this.startPage();
      List<OperatRecord> list = this.operatRecordService.selectOperatRecordList(operatRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:record:export')")
   @Log(
      title = "患者手术记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(OperatRecord operatRecord) {
      List<OperatRecord> list = this.operatRecordService.selectOperatRecordList(operatRecord);
      ExcelUtil<OperatRecord> util = new ExcelUtil(OperatRecord.class);
      return util.exportExcel(list, "患者手术记录数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:record:query')")
   @GetMapping({"/{oprId}"})
   public AjaxResult getInfo(@PathVariable("oprId") String oprId) {
      return AjaxResult.success((Object)this.operatRecordService.selectOperatRecordById(oprId));
   }

   @PreAuthorize("@ss.hasPermi('pat:record:add')")
   @Log(
      title = "患者手术记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody OperatRecord operatRecord) {
      return this.toAjax(this.operatRecordService.insertOperatRecord(operatRecord));
   }

   @PreAuthorize("@ss.hasPermi('pat:record:edit')")
   @Log(
      title = "患者手术记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody OperatRecord operatRecord) {
      return this.toAjax(this.operatRecordService.updateOperatRecord(operatRecord));
   }

   @PreAuthorize("@ss.hasPermi('pat:record:remove')")
   @Log(
      title = "患者手术记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{oprIds}"})
   public AjaxResult remove(@PathVariable String[] oprIds) {
      return this.toAjax(this.operatRecordService.deleteOperatRecordByIds(oprIds));
   }
}
