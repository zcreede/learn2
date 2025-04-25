package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderSignDetail;
import com.emr.project.docOrder.service.ITdPaOrderSignDetailService;
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
@RequestMapping({"/docOrder/signdetail"})
public class TdPaOrderSignDetailController extends BaseController {
   @Autowired
   private ITdPaOrderSignDetailService tdPaOrderSignDetailService;

   @PreAuthorize("@ss.hasPermi('docOrder:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderSignDetail tdPaOrderSignDetail) {
      this.startPage();
      List<TdPaOrderSignDetail> list = this.tdPaOrderSignDetailService.selectTdPaOrderSignDetailList(tdPaOrderSignDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:export')")
   @Log(
      title = "医嘱签名明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderSignDetail tdPaOrderSignDetail) {
      List<TdPaOrderSignDetail> list = this.tdPaOrderSignDetailService.selectTdPaOrderSignDetailList(tdPaOrderSignDetail);
      ExcelUtil<TdPaOrderSignDetail> util = new ExcelUtil(TdPaOrderSignDetail.class);
      return util.exportExcel(list, "医嘱签名明细数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderSignDetailService.selectTdPaOrderSignDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:add')")
   @Log(
      title = "医嘱签名明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderSignDetail tdPaOrderSignDetail) {
      return this.toAjax(this.tdPaOrderSignDetailService.insertTdPaOrderSignDetail(tdPaOrderSignDetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:edit')")
   @Log(
      title = "医嘱签名明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderSignDetail tdPaOrderSignDetail) {
      return this.toAjax(this.tdPaOrderSignDetailService.updateTdPaOrderSignDetail(tdPaOrderSignDetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:remove')")
   @Log(
      title = "医嘱签名明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderSignDetailService.deleteTdPaOrderSignDetailByIds(ids));
   }
}
