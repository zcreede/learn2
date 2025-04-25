package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
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
@RequestMapping({"/docOrder/detail"})
public class TdPaOrderDetailController extends BaseController {
   @Autowired
   private ITdPaOrderDetailService tdPaOrderDetailService;

   @PreAuthorize("@ss.hasPermi('docOrder:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderDetailVo tdPaOrderDetail) {
      this.startPage();
      List<TdPaOrderDetail> list = this.tdPaOrderDetailService.selectTdPaOrderDetailList(tdPaOrderDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:export')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderDetailVo tdPaOrderDetail) {
      List<TdPaOrderDetail> list = this.tdPaOrderDetailService.selectTdPaOrderDetailList(tdPaOrderDetail);
      ExcelUtil<TdPaOrderDetail> util = new ExcelUtil(TdPaOrderDetail.class);
      return util.exportExcel(list, "医嘱明细数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderDetailService.selectTdPaOrderDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:add')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderDetail tdPaOrderDetail) {
      return this.toAjax(this.tdPaOrderDetailService.insertTdPaOrderDetail(tdPaOrderDetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:edit')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderDetail tdPaOrderDetail) {
      return this.toAjax(this.tdPaOrderDetailService.updateTdPaOrderDetail(tdPaOrderDetail));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:detail:remove')")
   @Log(
      title = "医嘱明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderDetailService.deleteTdPaOrderDetailByIds(ids));
   }
}
