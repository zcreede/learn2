package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TmPmOrderStatus;
import com.emr.project.docOrder.service.ITmPmOrderStatusService;
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
@RequestMapping({"/tmpm/status"})
public class TmPmOrderStatusController extends BaseController {
   @Autowired
   private ITmPmOrderStatusService tmPmOrderStatusService;

   @PreAuthorize("@ss.hasPermi('tmpm:status:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmPmOrderStatus tmPmOrderStatus) {
      this.startPage();
      List<TmPmOrderStatus> list = this.tmPmOrderStatusService.selectTmPmOrderStatusList(tmPmOrderStatus);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:status:export')")
   @Log(
      title = "医嘱状态维护",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmPmOrderStatus tmPmOrderStatus) {
      List<TmPmOrderStatus> list = this.tmPmOrderStatusService.selectTmPmOrderStatusList(tmPmOrderStatus);
      ExcelUtil<TmPmOrderStatus> util = new ExcelUtil(TmPmOrderStatus.class);
      return util.exportExcel(list, "医嘱状态维护数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:status:query')")
   @GetMapping({"/{codeNo}"})
   public AjaxResult getInfo(@PathVariable("codeNo") String codeNo) {
      return AjaxResult.success((Object)this.tmPmOrderStatusService.selectTmPmOrderStatusById(codeNo));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:status:add')")
   @Log(
      title = "医嘱状态维护",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmPmOrderStatus tmPmOrderStatus) {
      return this.toAjax(this.tmPmOrderStatusService.insertTmPmOrderStatus(tmPmOrderStatus));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:status:edit')")
   @Log(
      title = "医嘱状态维护",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPmOrderStatus tmPmOrderStatus) {
      return this.toAjax(this.tmPmOrderStatusService.updateTmPmOrderStatus(tmPmOrderStatus));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:status:remove')")
   @Log(
      title = "医嘱状态维护",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{codeNos}"})
   public AjaxResult remove(@PathVariable String[] codeNos) {
      return this.toAjax(this.tmPmOrderStatusService.deleteTmPmOrderStatusByIds(codeNos));
   }
}
