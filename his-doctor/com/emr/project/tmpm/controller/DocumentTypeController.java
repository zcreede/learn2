package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.service.IDocumentTypeService;
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
@RequestMapping({"/tmpm/type"})
public class DocumentTypeController extends BaseController {
   @Autowired
   private IDocumentTypeService documentTypeService;

   @PreAuthorize("@ss.hasPermi('tmpm:type:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DocumentType documentType) {
      this.startPage();
      List<DocumentType> list = this.documentTypeService.selectDocumentTypeList(documentType);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:type:export')")
   @Log(
      title = "单据类型",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DocumentType documentType) {
      List<DocumentType> list = this.documentTypeService.selectDocumentTypeList(documentType);
      ExcelUtil<DocumentType> util = new ExcelUtil(DocumentType.class);
      return util.exportExcel(list, "单据类型数据");
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:type:query,tmSet:detail:add')")
   @GetMapping({"/{documentTypeCd}"})
   public AjaxResult getInfo(@PathVariable("documentTypeCd") String documentTypeCd) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.documentTypeService.selectDocumentTypeById(documentTypeCd));
      } catch (Exception e) {
         this.log.error("查询单据类型详细信息出现异常", e);
         ajaxResult = AjaxResult.error("查询单据类型详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:type:add')")
   @Log(
      title = "单据类型",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DocumentType documentType) {
      return this.toAjax(this.documentTypeService.insertDocumentType(documentType));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:type:edit')")
   @Log(
      title = "单据类型",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DocumentType documentType) {
      return this.toAjax(this.documentTypeService.updateDocumentType(documentType));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:type:remove')")
   @Log(
      title = "单据类型",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{hospitalCodes}"})
   public AjaxResult remove(@PathVariable String[] hospitalCodes) {
      return this.toAjax(this.documentTypeService.deleteDocumentTypeByIds(hospitalCodes));
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:type:list,docOrder:check:list')")
   @GetMapping({"/documentList"})
   public AjaxResult documentList(DocumentType documentType) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(documentType.getDocumentClass())) {
            ajaxResult = AjaxResult.error("类型编码不能为空");
         } else {
            List<DocumentType> list = this.documentTypeService.selectDocumentListByType(documentType.getDocumentClass(), (String)null);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据类型查询单据列表出现异常", e);
         ajaxResult = AjaxResult.error("根据类型查询单据列表出现异常");
      }

      return ajaxResult;
   }
}
