package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.service.IEmrBinaryService;
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
@RequestMapping({"/emr/binary"})
public class EmrBinaryController extends BaseController {
   @Autowired
   private IEmrBinaryService emrBinaryService;

   @PreAuthorize("@ss.hasPermi('emr:binary:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrBinary emrBinary) {
      this.startPage();
      List<EmrBinary> list = this.emrBinaryService.selectEmrBinaryList(emrBinary);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:binary:export')")
   @Log(
      title = "病历二进制存储",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrBinary emrBinary) {
      List<EmrBinary> list = this.emrBinaryService.selectEmrBinaryList(emrBinary);
      ExcelUtil<EmrBinary> util = new ExcelUtil(EmrBinary.class);
      return util.exportExcel(list, "病历二进制存储数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:binary:query')")
   @GetMapping({"/{mrFileId}"})
   public AjaxResult getInfo(@PathVariable("mrFileId") Long mrFileId) {
      return AjaxResult.success((Object)this.emrBinaryService.selectEmrBinaryById(mrFileId));
   }

   @PreAuthorize("@ss.hasPermi('emr:binary:add')")
   @Log(
      title = "病历二进制存储",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrBinary emrBinary) {
      this.emrBinaryService.insertEmrBinary(emrBinary);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('emr:binary:edit')")
   @Log(
      title = "病历二进制存储",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrBinary emrBinary) {
      this.emrBinaryService.updateEmrBinary(emrBinary);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('emr:binary:remove')")
   @Log(
      title = "病历二进制存储",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{mrFileIds}"})
   public AjaxResult remove(@PathVariable Long[] mrFileIds) {
      return this.toAjax(this.emrBinaryService.deleteEmrBinaryByIds(mrFileIds));
   }
}
