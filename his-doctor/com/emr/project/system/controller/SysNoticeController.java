package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysNotice;
import com.emr.project.system.service.ISysNoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/system/notice"})
public class SysNoticeController extends BaseController {
   @Autowired
   private ISysNoticeService noticeService;

   @PreAuthorize("@ss.hasPermi('system:notice:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysNotice notice) {
      this.startPage();
      List<SysNotice> list = this.noticeService.selectNoticeList(notice);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:notice:query')")
   @GetMapping({"/{noticeId}"})
   public AjaxResult getInfo(@PathVariable Long noticeId) {
      return AjaxResult.success((Object)this.noticeService.selectNoticeById(noticeId));
   }

   @PreAuthorize("@ss.hasPermi('system:notice:add')")
   @Log(
      title = "通知公告",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysNotice notice) {
      notice.setCreateBy(SecurityUtils.getUsername());
      return this.toAjax(this.noticeService.insertNotice(notice));
   }

   @PreAuthorize("@ss.hasPermi('system:notice:edit')")
   @Log(
      title = "通知公告",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
      notice.setUpdateBy(SecurityUtils.getUsername());
      return this.toAjax(this.noticeService.updateNotice(notice));
   }

   @PreAuthorize("@ss.hasPermi('system:notice:remove')")
   @Log(
      title = "通知公告",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{noticeIds}"})
   public AjaxResult remove(@PathVariable Long[] noticeIds) {
      return this.toAjax(this.noticeService.deleteNoticeByIds(noticeIds));
   }
}
