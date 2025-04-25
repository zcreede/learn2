package com.emr.project.system.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.TdPmNotice;
import com.emr.project.system.service.ITdPmNoticeService;
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
@RequestMapping({"/base/notice"})
public class TdPmNoticeController extends BaseController {
   @Autowired
   private ITdPmNoticeService tdPmNoticeService;

   @PreAuthorize("@ss.hasAnyPermi('base:notice:list,emr:home:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPmNotice tdPmNotice) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TdPmNotice> list = this.tdPmNoticeService.selectTdPmNoticeList(tdPmNotice);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询全部公告出现异常");
         this.log.error("查询全部公告出现异常", e);
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('base:notice:query,emr:home:list')")
   @GetMapping({"/topFiveList"})
   public AjaxResult topFiveList() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<TdPmNotice> list = this.tdPmNoticeService.selectTopFiveList();
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询最近五条公告出现异常");
         this.log.error("查询最近五条公告出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('base:notice:add')")
   @Log(
      title = "公告咨询主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPmNotice tdPmNotice) {
      return this.toAjax(this.tdPmNoticeService.insertTdPmNotice(tdPmNotice));
   }

   @PreAuthorize("@ss.hasPermi('base:notice:edit')")
   @Log(
      title = "公告咨询主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPmNotice tdPmNotice) {
      return this.toAjax(this.tdPmNoticeService.updateTdPmNotice(tdPmNotice));
   }

   @PreAuthorize("@ss.hasPermi('base:notice:remove')")
   @Log(
      title = "公告咨询主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPmNoticeService.deleteTdPmNoticeByIds(ids));
   }
}
