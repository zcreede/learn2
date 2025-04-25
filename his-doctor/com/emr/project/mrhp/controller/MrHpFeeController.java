package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.service.IMrHpFeeService;
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
@RequestMapping({"/mrhp/hp/fee"})
public class MrHpFeeController extends BaseController {
   @Autowired
   private IMrHpFeeService mrHpFeeService;

   @PreAuthorize("@ss.hasPermi('mrhp:fee:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpFee mrHpFee) {
      this.startPage();
      List<MrHpFee> list = this.mrHpFeeService.selectMrHpFeeList(mrHpFee);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:fee:export')")
   @Log(
      title = "病案首页-费用",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpFee mrHpFee) {
      List<MrHpFee> list = this.mrHpFeeService.selectMrHpFeeList(mrHpFee);
      ExcelUtil<MrHpFee> util = new ExcelUtil(MrHpFee.class);
      return util.exportExcel(list, "病案首页-费用数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:fee:query')")
   @GetMapping({"/{feeId}"})
   public AjaxResult getInfo(@PathVariable("feeId") String feeId) {
      return AjaxResult.success((Object)this.mrHpFeeService.selectMrHpFeeById(feeId));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:fee:add')")
   @Log(
      title = "病案首页-费用",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpFee mrHpFee) {
      return this.toAjax(this.mrHpFeeService.insertMrHpFee(mrHpFee));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:fee:edit')")
   @Log(
      title = "病案首页-费用",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpFee mrHpFee) {
      return this.toAjax(this.mrHpFeeService.updateMrHpFee(mrHpFee));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:fee:remove')")
   @Log(
      title = "病案首页-费用",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{feeIds}"})
   public AjaxResult remove(@PathVariable String[] feeIds) {
      return this.toAjax(this.mrHpFeeService.deleteMrHpFeeByIds(feeIds));
   }
}
