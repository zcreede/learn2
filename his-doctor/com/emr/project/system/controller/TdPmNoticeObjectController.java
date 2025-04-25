package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.TdPmNoticeObject;
import com.emr.project.system.service.ITdPmNoticeObjectService;
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
@RequestMapping({"/base/object"})
public class TdPmNoticeObjectController extends BaseController {
   @Autowired
   private ITdPmNoticeObjectService tdPmNoticeObjectService;

   @PreAuthorize("@ss.hasPermi('base:object:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPmNoticeObject tdPmNoticeObject) {
      this.startPage();
      List<TdPmNoticeObject> list = this.tdPmNoticeObjectService.selectTdPmNoticeObjectList(tdPmNoticeObject);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('base:object:export')")
   @Log(
      title = "公告接收对象",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPmNoticeObject tdPmNoticeObject) {
      List<TdPmNoticeObject> list = this.tdPmNoticeObjectService.selectTdPmNoticeObjectList(tdPmNoticeObject);
      ExcelUtil<TdPmNoticeObject> util = new ExcelUtil(TdPmNoticeObject.class);
      return util.exportExcel(list, "公告接收对象数据");
   }

   @PreAuthorize("@ss.hasPermi('base:object:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPmNoticeObjectService.selectTdPmNoticeObjectById(id));
   }

   @PreAuthorize("@ss.hasPermi('base:object:add')")
   @Log(
      title = "公告接收对象",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPmNoticeObject tdPmNoticeObject) {
      return this.toAjax(this.tdPmNoticeObjectService.insertTdPmNoticeObject(tdPmNoticeObject));
   }

   @PreAuthorize("@ss.hasPermi('base:object:edit')")
   @Log(
      title = "公告接收对象",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPmNoticeObject tdPmNoticeObject) {
      return this.toAjax(this.tdPmNoticeObjectService.updateTdPmNoticeObject(tdPmNoticeObject));
   }

   @PreAuthorize("@ss.hasPermi('base:object:remove')")
   @Log(
      title = "公告接收对象",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPmNoticeObjectService.deleteTdPmNoticeObjectByIds(ids));
   }
}
