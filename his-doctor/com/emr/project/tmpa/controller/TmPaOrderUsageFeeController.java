package com.emr.project.tmpa.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpa.domain.TmPaOrderUsageFee;
import com.emr.project.tmpa.service.ITmPaOrderUsageFeeService;
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
@RequestMapping({"/tmpa/fee"})
public class TmPaOrderUsageFeeController extends BaseController {
   @Autowired
   private ITmPaOrderUsageFeeService tmPaOrderUsageFeeService;

   @PreAuthorize("@ss.hasPermi('tmpa:fee:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmPaOrderUsageFee tmPaOrderUsageFee) {
      this.startPage();
      List<TmPaOrderUsageFee> list = this.tmPaOrderUsageFeeService.selectTmPaOrderUsageFeeList(tmPaOrderUsageFee);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpa:fee:export')")
   @Log(
      title = "医嘱用法收费标准维护",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmPaOrderUsageFee tmPaOrderUsageFee) {
      List<TmPaOrderUsageFee> list = this.tmPaOrderUsageFeeService.selectTmPaOrderUsageFeeList(tmPaOrderUsageFee);
      ExcelUtil<TmPaOrderUsageFee> util = new ExcelUtil(TmPaOrderUsageFee.class);
      return util.exportExcel(list, "医嘱用法收费标准维护数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpa:fee:query')")
   @GetMapping({"/{no}"})
   public AjaxResult getInfo(@PathVariable("no") Long no) {
      return AjaxResult.success((Object)this.tmPaOrderUsageFeeService.selectTmPaOrderUsageFeeById(no));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:fee:add')")
   @Log(
      title = "医嘱用法收费标准维护",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmPaOrderUsageFee tmPaOrderUsageFee) {
      return this.toAjax(this.tmPaOrderUsageFeeService.insertTmPaOrderUsageFee(tmPaOrderUsageFee));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:fee:edit')")
   @Log(
      title = "医嘱用法收费标准维护",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPaOrderUsageFee tmPaOrderUsageFee) {
      return this.toAjax(this.tmPaOrderUsageFeeService.updateTmPaOrderUsageFee(tmPaOrderUsageFee));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:fee:remove')")
   @Log(
      title = "医嘱用法收费标准维护",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{nos}"})
   public AjaxResult remove(@PathVariable Long[] nos) {
      return this.toAjax(this.tmPaOrderUsageFeeService.deleteTmPaOrderUsageFeeByIds(nos));
   }
}
