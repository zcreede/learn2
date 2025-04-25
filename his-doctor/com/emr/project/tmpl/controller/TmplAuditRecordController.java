package com.emr.project.tmpl.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.TmplAuditRecord;
import com.emr.project.tmpl.service.ITmplAuditRecordService;
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
@RequestMapping({"/tmpl/auditRecord"})
public class TmplAuditRecordController extends BaseController {
   @Autowired
   private ITmplAuditRecordService tmplAuditRecordService;

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TmplAuditRecord tmplAuditRecord) {
      this.startPage();
      List<TmplAuditRecord> list = this.tmplAuditRecordService.selectTmplAuditRecordList(tmplAuditRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:tempStateSave,tmpl:auditRecord:pageInfo,tmpl:auditRecord:queryList,tmpl:index:edit')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(TmplAuditRecord tmplAuditRecord) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (tmplAuditRecord.getTmplId() != null) {
            List<TmplAuditRecord> list = this.tmplAuditRecordService.selectTmplAuditRecordQueryList(tmplAuditRecord);
            ajaxResult = AjaxResult.success((Object)list);
         } else {
            ajaxResult = AjaxResult.error("模板id参数不能为空");
         }
      } catch (Exception var4) {
         this.log.error("查询模板审核记录列表异常");
         ajaxResult = AjaxResult.error("查询模板审核记录列表异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:export')")
   @Log(
      title = "模板审核记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmplAuditRecord tmplAuditRecord) {
      List<TmplAuditRecord> list = this.tmplAuditRecordService.selectTmplAuditRecordList(tmplAuditRecord);
      ExcelUtil<TmplAuditRecord> util = new ExcelUtil(TmplAuditRecord.class);
      return util.exportExcel(list, "模板审核记录数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmplAuditRecordService.selectTmplAuditRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:add')")
   @Log(
      title = "模板审核记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmplAuditRecord tmplAuditRecord) {
      return this.toAjax(this.tmplAuditRecordService.insertTmplAuditRecord(tmplAuditRecord));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:edit')")
   @Log(
      title = "模板审核记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmplAuditRecord tmplAuditRecord) {
      return this.toAjax(this.tmplAuditRecordService.updateTmplAuditRecord(tmplAuditRecord));
   }

   @PreAuthorize("@ss.hasPermi('tmpl:auditRecord:remove')")
   @Log(
      title = "模板审核记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmplAuditRecordService.deleteTmplAuditRecordByIds(ids));
   }
}
