package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderOperationSign;
import com.emr.project.docOrder.service.ITdPaOrderOperationSignService;
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
@RequestMapping({"/docOrder/sign"})
public class TdPaOrderOperationSignController extends BaseController {
   @Autowired
   private ITdPaOrderOperationSignService tdPaOrderOperationSignService;

   @PreAuthorize("@ss.hasPermi('docOrder:sign:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderOperationSign tdPaOrderOperationSign) {
      this.startPage();
      List<TdPaOrderOperationSign> list = this.tdPaOrderOperationSignService.selectTdPaOrderOperationSignList(tdPaOrderOperationSign);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:sign:export')")
   @Log(
      title = "医嘱项目操作签名记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderOperationSign tdPaOrderOperationSign) {
      List<TdPaOrderOperationSign> list = this.tdPaOrderOperationSignService.selectTdPaOrderOperationSignList(tdPaOrderOperationSign);
      ExcelUtil<TdPaOrderOperationSign> util = new ExcelUtil(TdPaOrderOperationSign.class);
      return util.exportExcel(list, "医嘱项目操作签名记录数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:sign:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderOperationSignService.selectTdPaOrderOperationSignById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:sign:add')")
   @Log(
      title = "医嘱项目操作签名记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderOperationSign tdPaOrderOperationSign) {
      return this.toAjax(this.tdPaOrderOperationSignService.insertTdPaOrderOperationSign(tdPaOrderOperationSign));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:sign:edit')")
   @Log(
      title = "医嘱项目操作签名记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderOperationSign tdPaOrderOperationSign) {
      return this.toAjax(this.tdPaOrderOperationSignService.updateTdPaOrderOperationSign(tdPaOrderOperationSign));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:sign:remove')")
   @Log(
      title = "医嘱项目操作签名记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderOperationSignService.deleteTdPaOrderOperationSignByIds(ids));
   }
}
