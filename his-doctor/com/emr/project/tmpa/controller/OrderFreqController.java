package com.emr.project.tmpa.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.service.IOrderFreqService;
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
@RequestMapping({"/tmpa/freq"})
public class OrderFreqController extends BaseController {
   @Autowired
   private IOrderFreqService orderFreqService;

   @PreAuthorize("@ss.hasPermi('tmpa:freq:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(OrderFreq orderFreq) {
      this.startPage();
      List<OrderFreq> list = this.orderFreqService.selectOrderFreqList(orderFreq);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpa:freq:export')")
   @Log(
      title = "医嘱频次",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(OrderFreq orderFreq) {
      List<OrderFreq> list = this.orderFreqService.selectOrderFreqList(orderFreq);
      ExcelUtil<OrderFreq> util = new ExcelUtil(OrderFreq.class);
      return util.exportExcel(list, "医嘱频次数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpa:freq:query')")
   @GetMapping({"/{hospitalCode}"})
   public AjaxResult getInfo(@PathVariable("hospitalCode") String hospitalCode) {
      return AjaxResult.success((Object)this.orderFreqService.selectOrderFreqById(hospitalCode));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:freq:add')")
   @Log(
      title = "医嘱频次",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody OrderFreq orderFreq) {
      return this.toAjax(this.orderFreqService.insertOrderFreq(orderFreq));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:freq:edit')")
   @Log(
      title = "医嘱频次",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody OrderFreq orderFreq) {
      return this.toAjax(this.orderFreqService.updateOrderFreq(orderFreq));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:freq:remove')")
   @Log(
      title = "医嘱频次",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{hospitalCodes}"})
   public AjaxResult remove(@PathVariable String[] hospitalCodes) {
      return this.toAjax(this.orderFreqService.deleteOrderFreqByIds(hospitalCodes));
   }
}
