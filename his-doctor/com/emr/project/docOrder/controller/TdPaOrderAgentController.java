package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderAgent;
import com.emr.project.docOrder.service.ITdPaOrderAgentService;
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
@RequestMapping({"/docOrder/agent"})
public class TdPaOrderAgentController extends BaseController {
   @Autowired
   private ITdPaOrderAgentService tdPaOrderAgentService;

   @PreAuthorize("@ss.hasPermi('docOrder:agent:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderAgent tdPaOrderAgent) {
      this.startPage();
      List<TdPaOrderAgent> list = this.tdPaOrderAgentService.selectTdPaOrderAgentList(tdPaOrderAgent);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:agent:export')")
   @Log(
      title = "患者医嘱-毒麻药品代办人信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderAgent tdPaOrderAgent) {
      List<TdPaOrderAgent> list = this.tdPaOrderAgentService.selectTdPaOrderAgentList(tdPaOrderAgent);
      ExcelUtil<TdPaOrderAgent> util = new ExcelUtil(TdPaOrderAgent.class);
      return util.exportExcel(list, "患者医嘱-毒麻药品代办人信息数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:agent:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderAgentService.selectTdPaOrderAgentById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:agent:edit')")
   @Log(
      title = "患者医嘱-毒麻药品代办人信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderAgent tdPaOrderAgent) {
      return this.toAjax(this.tdPaOrderAgentService.updateTdPaOrderAgent(tdPaOrderAgent));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:agent:remove')")
   @Log(
      title = "患者医嘱-毒麻药品代办人信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderAgentService.deleteTdPaOrderAgentByIds(ids));
   }
}
