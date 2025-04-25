package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.Orderdetail;
import com.emr.project.docOrder.service.IOrderdetailService;
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
@RequestMapping({"/docOrder/orderdetail"})
public class OrderdetailController extends BaseController {
   @Autowired
   private IOrderdetailService orderdetailService;

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Orderdetail orderdetail) {
      this.startPage();
      List<Orderdetail> list = this.orderdetailService.selectOrderdetailList(orderdetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:export')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Orderdetail orderdetail) {
      List<Orderdetail> list = this.orderdetailService.selectOrderdetailList(orderdetail);
      ExcelUtil<Orderdetail> util = new ExcelUtil(Orderdetail.class);
      return util.exportExcel(list, "医嘱明细数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") String id) {
      return AjaxResult.success((Object)this.orderdetailService.selectOrderdetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:add')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Orderdetail orderdetail) {
      return this.toAjax(this.orderdetailService.insertOrderdetail(orderdetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:edit')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody Orderdetail orderdetail) {
      return this.toAjax(this.orderdetailService.updateOrderdetail(orderdetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:orderdetail:remove')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.orderdetailService.deleteOrderdetailByIds(ids));
   }
}
