package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderSignMain;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
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
@RequestMapping({"/docOrder/signmain"})
public class TdPaOrderSignMainController extends BaseController {
   @Autowired
   private ITdPaOrderSignMainService tdPaOrderSignMainService;

   @PreAuthorize("@ss.hasPermi('docOrder:main:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderSignMain tdPaOrderSignMain) {
      this.startPage();
      List<TdPaOrderSignMain> list = this.tdPaOrderSignMainService.selectTdPaOrderSignMainList(tdPaOrderSignMain);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:main:export')")
   @Log(
      title = "医嘱签名记录主",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderSignMain tdPaOrderSignMain) {
      List<TdPaOrderSignMain> list = this.tdPaOrderSignMainService.selectTdPaOrderSignMainList(tdPaOrderSignMain);
      ExcelUtil<TdPaOrderSignMain> util = new ExcelUtil(TdPaOrderSignMain.class);
      return util.exportExcel(list, "医嘱签名记录主数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:main:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderSignMainService.selectTdPaOrderSignMainById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:main:add')")
   @Log(
      title = "医嘱签名记录主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderSignMain tdPaOrderSignMain) {
      return this.toAjax(this.tdPaOrderSignMainService.insertTdPaOrderSignMain(tdPaOrderSignMain));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:main:edit')")
   @Log(
      title = "医嘱签名记录主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderSignMain tdPaOrderSignMain) {
      return this.toAjax(this.tdPaOrderSignMainService.updateTdPaOrderSignMain(tdPaOrderSignMain));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:main:remove')")
   @Log(
      title = "医嘱签名记录主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderSignMainService.deleteTdPaOrderSignMainByIds(ids));
   }
}
