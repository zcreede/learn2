package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderStatus;
import com.emr.project.docOrder.service.ITdPaOrderStatusService;
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
@RequestMapping({"/docOrder/status"})
public class TdPaOrderStatusController extends BaseController {
   @Autowired
   private ITdPaOrderStatusService tdPaOrderStatusService;

   @PreAuthorize("@ss.hasPermi('docOrder:status:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderStatus tdPaOrderStatus) {
      this.startPage();
      List<TdPaOrderStatus> list = this.tdPaOrderStatusService.selectTdPaOrderStatusList(tdPaOrderStatus);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:status:export')")
   @Log(
      title = "医嘱状态",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderStatus tdPaOrderStatus) {
      List<TdPaOrderStatus> list = this.tdPaOrderStatusService.selectTdPaOrderStatusList(tdPaOrderStatus);
      ExcelUtil<TdPaOrderStatus> util = new ExcelUtil(TdPaOrderStatus.class);
      return util.exportExcel(list, "医嘱状态数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:status:query')")
   @GetMapping({"/{orderNo}"})
   public AjaxResult getInfo(@PathVariable("orderNo") String orderNo) {
      return AjaxResult.success((Object)this.tdPaOrderStatusService.selectTdPaOrderStatusById(orderNo));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:status:add')")
   @Log(
      title = "医嘱状态",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderStatus tdPaOrderStatus) {
      return this.toAjax(this.tdPaOrderStatusService.insertTdPaOrderStatus(tdPaOrderStatus));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:status:edit')")
   @Log(
      title = "医嘱状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderStatus tdPaOrderStatus) {
      return this.toAjax(this.tdPaOrderStatusService.updateTdPaOrderStatus(tdPaOrderStatus));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:status:remove')")
   @Log(
      title = "医嘱状态",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{orderNos}"})
   public AjaxResult remove(@PathVariable String[] orderNos) {
      return this.toAjax(this.tdPaOrderStatusService.deleteTdPaOrderStatusByIds(orderNos));
   }
}
