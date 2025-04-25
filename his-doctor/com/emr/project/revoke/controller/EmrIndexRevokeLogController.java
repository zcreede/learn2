package com.emr.project.revoke.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.domain.req.EmrIndexRevokeLogReq;
import com.emr.project.revoke.domain.vo.EmrIndexRevokeLogVO;
import com.emr.project.revoke.service.IEmrIndexRevokeLogService;
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
@RequestMapping({"/revoke/log"})
public class EmrIndexRevokeLogController extends BaseController {
   @Autowired
   private IEmrIndexRevokeLogService emrIndexRevokeLogService;

   @PreAuthorize("@ss.hasPermi('revoke:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrIndexRevokeLogReq req) {
      this.startPage();
      List<EmrIndexRevokeLogVO> list = this.emrIndexRevokeLogService.selectEmrIndexRevokeLogList(req);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('revoke:log:export')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrIndexRevokeLogReq req) {
      List<EmrIndexRevokeLogVO> list = this.emrIndexRevokeLogService.selectEmrIndexRevokeLogList(req);
      ExcelUtil<EmrIndexRevokeLogVO> util = new ExcelUtil(EmrIndexRevokeLogVO.class);
      return util.exportExcel(list, "【请填写功能名称】数据");
   }

   @PreAuthorize("@ss.hasPermi('revoke:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrIndexRevokeLogService.selectEmrIndexRevokeLogById(id));
   }

   @PreAuthorize("@ss.hasPermi('revoke:log:add')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrIndexRevokeLog emrIndexRevokeLog) {
      return this.toAjax(this.emrIndexRevokeLogService.insertEmrIndexRevokeLog(emrIndexRevokeLog));
   }

   @PreAuthorize("@ss.hasPermi('revoke:log:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrIndexRevokeLog emrIndexRevokeLog) {
      return this.toAjax(this.emrIndexRevokeLogService.updateEmrIndexRevokeLog(emrIndexRevokeLog));
   }

   @PreAuthorize("@ss.hasPermi('revoke:log:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrIndexRevokeLogService.deleteEmrIndexRevokeLogByIds(ids));
   }
}
