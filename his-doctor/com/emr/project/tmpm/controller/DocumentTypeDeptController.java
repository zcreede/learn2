package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.DocumentTypeDept;
import com.emr.project.tmpm.service.IDocumentTypeDeptService;
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
@RequestMapping({"/tmpm/dept"})
public class DocumentTypeDeptController extends BaseController {
   @Autowired
   private IDocumentTypeDeptService documentTypeDeptService;

   @PreAuthorize("@ss.hasPermi('tmpm:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DocumentTypeDept documentTypeDept) {
      this.startPage();
      List<DocumentTypeDept> list = this.documentTypeDeptService.selectDocumentTypeDeptList(documentTypeDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:dept:export')")
   @Log(
      title = "单据类型与科室关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DocumentTypeDept documentTypeDept) {
      List<DocumentTypeDept> list = this.documentTypeDeptService.selectDocumentTypeDeptList(documentTypeDept);
      ExcelUtil<DocumentTypeDept> util = new ExcelUtil(DocumentTypeDept.class);
      return util.exportExcel(list, "单据类型与科室关系数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:dept:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.documentTypeDeptService.selectDocumentTypeDeptById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:dept:add')")
   @Log(
      title = "单据类型与科室关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DocumentTypeDept documentTypeDept) {
      return this.toAjax(this.documentTypeDeptService.insertDocumentTypeDept(documentTypeDept));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:dept:edit')")
   @Log(
      title = "单据类型与科室关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DocumentTypeDept documentTypeDept) {
      return this.toAjax(this.documentTypeDeptService.updateDocumentTypeDept(documentTypeDept));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:dept:remove')")
   @Log(
      title = "单据类型与科室关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.documentTypeDeptService.deleteDocumentTypeDeptByIds(ids));
   }
}
