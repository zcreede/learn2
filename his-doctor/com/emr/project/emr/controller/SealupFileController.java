package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.SealupFile;
import com.emr.project.emr.service.ISealupFileService;
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
@RequestMapping({"/emr/file"})
public class SealupFileController extends BaseController {
   @Autowired
   private ISealupFileService sealupFileService;

   @PreAuthorize("@ss.hasPermi('emr:file:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SealupFile sealupFile) {
      this.startPage();
      List<SealupFile> list = this.sealupFileService.selectSealupFileList(sealupFile);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:file:export')")
   @Log(
      title = "病历封存文档列",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SealupFile sealupFile) {
      List<SealupFile> list = this.sealupFileService.selectSealupFileList(sealupFile);
      ExcelUtil<SealupFile> util = new ExcelUtil(SealupFile.class);
      return util.exportExcel(list, "病历封存文档列数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:file:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sealupFileService.selectSealupFileById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:file:add')")
   @Log(
      title = "病历封存文档列",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SealupFile sealupFile) {
      return this.toAjax(this.sealupFileService.insertSealupFile(sealupFile));
   }

   @PreAuthorize("@ss.hasPermi('emr:file:edit')")
   @Log(
      title = "病历封存文档列",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SealupFile sealupFile) {
      return this.toAjax(this.sealupFileService.updateSealupFile(sealupFile));
   }

   @PreAuthorize("@ss.hasPermi('emr:file:remove')")
   @Log(
      title = "病历封存文档列",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sealupFileService.deleteSealupFileByIds(ids));
   }
}
