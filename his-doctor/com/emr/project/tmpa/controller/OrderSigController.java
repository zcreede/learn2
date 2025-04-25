package com.emr.project.tmpa.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderSigService;
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
@RequestMapping({"/tmpa/sig"})
public class OrderSigController extends BaseController {
   @Autowired
   private IOrderSigService orderSigService;

   @PreAuthorize("@ss.hasAnyPermi('tmpa:sig:list,docOrder:order:add')")
   @GetMapping({"/list"})
   public TableDataInfo list(OrderSig orderSig) {
      List<OrderSig> list = null;

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         orderSig.setDocCd(user.getUserName());
         orderSig.setEnabled("1");
         this.startPage();
         list = this.orderSigService.selectUseTimeOrderSigList(orderSig);
      } catch (Exception e) {
         this.log.error("查询医嘱用法列表出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpa:sig:export')")
   @Log(
      title = "医嘱用法",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(OrderSig orderSig) {
      List<OrderSig> list = this.orderSigService.selectOrderSigList(orderSig);
      ExcelUtil<OrderSig> util = new ExcelUtil(OrderSig.class);
      return util.exportExcel(list, "医嘱用法数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpa:sig:query')")
   @GetMapping({"/{sigCd}"})
   public AjaxResult getInfo(@PathVariable("sigCd") String sigCd) {
      return AjaxResult.success((Object)this.orderSigService.selectOrderSigById(sigCd));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:sig:add')")
   @Log(
      title = "医嘱用法",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody OrderSig orderSig) {
      return this.toAjax(this.orderSigService.insertOrderSig(orderSig));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:sig:edit')")
   @Log(
      title = "医嘱用法",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody OrderSig orderSig) {
      return this.toAjax(this.orderSigService.updateOrderSig(orderSig));
   }

   @PreAuthorize("@ss.hasPermi('tmpa:sig:remove')")
   @Log(
      title = "医嘱用法",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{sigCds}"})
   public AjaxResult remove(@PathVariable String[] sigCds) {
      return this.toAjax(this.orderSigService.deleteOrderSigByIds(sigCds));
   }
}
