package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.LisLabResultHis;
import com.emr.project.tmpm.service.ILisLabResultHisService;
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
@RequestMapping({"/his/result"})
public class LisLabResultHisController extends BaseController {
   @Autowired
   private ILisLabResultHisService lisLabResultHisService;

   @PreAuthorize("@ss.hasPermi('his:result:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(LisLabResultHis lisLabResultHis) {
      this.startPage();
      List<LisLabResultHis> list = this.lisLabResultHisService.selectLisLabResultHisList(lisLabResultHis);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('his:result:export')")
   @Log(
      title = "LIS检验结果与HIS结果对应关系.该中没有对应关系的，直接取lis结果，不用转换",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(LisLabResultHis lisLabResultHis) {
      List<LisLabResultHis> list = this.lisLabResultHisService.selectLisLabResultHisList(lisLabResultHis);
      ExcelUtil<LisLabResultHis> util = new ExcelUtil(LisLabResultHis.class);
      return util.exportExcel(list, "LIS检验结果与HIS结果对应关系.该中没有对应关系的，直接取lis结果，不用转换数据");
   }

   @PreAuthorize("@ss.hasPermi('his:result:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.lisLabResultHisService.selectLisLabResultHisById(id));
   }

   @PreAuthorize("@ss.hasPermi('his:result:add')")
   @Log(
      title = "LIS检验结果与HIS结果对应关系.该中没有对应关系的，直接取lis结果，不用转换",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody LisLabResultHis lisLabResultHis) {
      return this.toAjax(this.lisLabResultHisService.insertLisLabResultHis(lisLabResultHis));
   }

   @PreAuthorize("@ss.hasPermi('his:result:edit')")
   @Log(
      title = "LIS检验结果与HIS结果对应关系.该中没有对应关系的，直接取lis结果，不用转换",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody LisLabResultHis lisLabResultHis) {
      return this.toAjax(this.lisLabResultHisService.updateLisLabResultHis(lisLabResultHis));
   }

   @PreAuthorize("@ss.hasPermi('his:result:remove')")
   @Log(
      title = "LIS检验结果与HIS结果对应关系.该中没有对应关系的，直接取lis结果，不用转换",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.lisLabResultHisService.deleteLisLabResultHisByIds(ids));
   }
}
