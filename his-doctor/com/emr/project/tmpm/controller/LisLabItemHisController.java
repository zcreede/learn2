package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.LisLabItemHis;
import com.emr.project.tmpm.service.ILisLabItemHisService;
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
@RequestMapping({"/his/item"})
public class LisLabItemHisController extends BaseController {
   @Autowired
   private ILisLabItemHisService lisLabItemHisService;

   @PreAuthorize("@ss.hasPermi('his:item:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(LisLabItemHis lisLabItemHis) {
      this.startPage();
      List<LisLabItemHis> list = this.lisLabItemHisService.selectLisLabItemHisList(lisLabItemHis);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('his:item:export')")
   @Log(
      title = "HIS检验项目与LIS项目对应关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(LisLabItemHis lisLabItemHis) {
      List<LisLabItemHis> list = this.lisLabItemHisService.selectLisLabItemHisList(lisLabItemHis);
      ExcelUtil<LisLabItemHis> util = new ExcelUtil(LisLabItemHis.class);
      return util.exportExcel(list, "HIS检验项目与LIS项目对应关系数据");
   }

   @PreAuthorize("@ss.hasPermi('his:item:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.lisLabItemHisService.selectLisLabItemHisById(id));
   }

   @PreAuthorize("@ss.hasPermi('his:item:add')")
   @Log(
      title = "HIS检验项目与LIS项目对应关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody LisLabItemHis lisLabItemHis) {
      return this.toAjax(this.lisLabItemHisService.insertLisLabItemHis(lisLabItemHis));
   }

   @PreAuthorize("@ss.hasPermi('his:item:edit')")
   @Log(
      title = "HIS检验项目与LIS项目对应关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody LisLabItemHis lisLabItemHis) {
      return this.toAjax(this.lisLabItemHisService.updateLisLabItemHis(lisLabItemHis));
   }

   @PreAuthorize("@ss.hasPermi('his:item:remove')")
   @Log(
      title = "HIS检验项目与LIS项目对应关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.lisLabItemHisService.deleteLisLabItemHisByIds(ids));
   }
}
