package com.emr.project.pat.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.Transinfo;
import com.emr.project.pat.service.ITransinfoService;
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
@RequestMapping({"/pat/transinfo"})
public class TransinfoController extends BaseController {
   @Autowired
   private ITransinfoService transinfoService;

   @PreAuthorize("@ss.hasPermi('pat:transinfo:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Transinfo transinfo) {
      this.startPage();
      List<Transinfo> list = this.transinfoService.selectTransinfoList(transinfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:transinfo:export')")
   @Log(
      title = "转科信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Transinfo transinfo) {
      List<Transinfo> list = this.transinfoService.selectTransinfoList(transinfo);
      ExcelUtil<Transinfo> util = new ExcelUtil(Transinfo.class);
      return util.exportExcel(list, "转科信息数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:transinfo:query')")
   @GetMapping({"/{trId}"})
   public AjaxResult getInfo(@PathVariable("trId") Long trId) {
      return AjaxResult.success((Object)this.transinfoService.selectTransinfoById(trId));
   }

   @PreAuthorize("@ss.hasPermi('pat:transinfo:add')")
   @Log(
      title = "转科信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Transinfo transinfo) {
      return this.toAjax(this.transinfoService.insertTransinfo(transinfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:transinfo:edit')")
   @Log(
      title = "转科信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody Transinfo transinfo) {
      return this.toAjax(this.transinfoService.updateTransinfo(transinfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:transinfo:remove')")
   @Log(
      title = "转科信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{trIds}"})
   public AjaxResult remove(@PathVariable Long[] trIds) {
      return this.toAjax(this.transinfoService.deleteTransinfoByIds(trIds));
   }
}
