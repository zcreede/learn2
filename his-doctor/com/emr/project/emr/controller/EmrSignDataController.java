package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.service.IEmrSignDataService;
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
@RequestMapping({"/emr/data"})
public class EmrSignDataController extends BaseController {
   @Autowired
   private IEmrSignDataService emrSignDataService;

   @PreAuthorize("@ss.hasPermi('emr:data:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSignData emrSignData) {
      this.startPage();
      List<EmrSignData> list = this.emrSignDataService.selectEmrSignDataList(emrSignData);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:data:export')")
   @Log(
      title = "病历签名数据",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSignData emrSignData) {
      List<EmrSignData> list = this.emrSignDataService.selectEmrSignDataList(emrSignData);
      ExcelUtil<EmrSignData> util = new ExcelUtil(EmrSignData.class);
      return util.exportExcel(list, "病历签名数据数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:data:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSignDataService.selectEmrSignDataById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:data:add')")
   @Log(
      title = "病历签名数据",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSignData emrSignData) {
      return this.toAjax(this.emrSignDataService.insertEmrSignData(emrSignData));
   }

   @PreAuthorize("@ss.hasPermi('emr:data:edit')")
   @Log(
      title = "病历签名数据",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSignData emrSignData) {
      return this.toAjax(this.emrSignDataService.updateEmrSignData(emrSignData));
   }

   @PreAuthorize("@ss.hasPermi('emr:data:remove')")
   @Log(
      title = "病历签名数据",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSignDataService.deleteEmrSignDataByIds(ids));
   }
}
