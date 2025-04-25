package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.service.IPatEventService;
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
@RequestMapping({"/qc/event"})
public class PatEventController extends BaseController {
   @Autowired
   private IPatEventService patEventService;

   @PreAuthorize("@ss.hasPermi('qc:event:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(PatEvent patEvent) {
      this.startPage();
      List<PatEvent> list = this.patEventService.selectPatEventList(patEvent);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:event:export')")
   @Log(
      title = "患者事件",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(PatEvent patEvent) {
      List<PatEvent> list = this.patEventService.selectPatEventList(patEvent);
      ExcelUtil<PatEvent> util = new ExcelUtil(PatEvent.class);
      return util.exportExcel(list, "患者事件数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:event:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.patEventService.selectPatEventById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:event:add')")
   @Log(
      title = "患者事件",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody PatEvent patEvent) {
      return this.toAjax(this.patEventService.insertPatEvent(patEvent));
   }

   @PreAuthorize("@ss.hasPermi('qc:event:edit')")
   @Log(
      title = "患者事件",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody PatEvent patEvent) {
      return this.toAjax(this.patEventService.updatePatEvent(patEvent));
   }

   @PreAuthorize("@ss.hasPermi('qc:event:remove')")
   @Log(
      title = "患者事件",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.patEventService.deletePatEventByIds(ids));
   }
}
