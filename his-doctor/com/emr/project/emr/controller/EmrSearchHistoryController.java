package com.emr.project.emr.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.emr.domain.EmrSearchHistory;
import com.emr.project.emr.domain.vo.EmrSearchHistoryVo;
import com.emr.project.emr.service.IEmrSearchHistoryService;
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
@RequestMapping({"/emr/history"})
public class EmrSearchHistoryController extends BaseController {
   @Autowired
   private IEmrSearchHistoryService emrSearchHistoryService;

   @PreAuthorize("@ss.hasPermi('emr:history:list')")
   @GetMapping({"/list"})
   public AjaxResult list(EmrSearchHistory emrSearchHistory) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<EmrSearchHistoryVo> list = this.emrSearchHistoryService.selectEmrSearchHistoryList(emrSearchHistory);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询医师检索条件历史记录列表出现异常", e);
         ajaxResult = AjaxResult.error("查询医师检索条件历史记录列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:history:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSearchHistoryService.selectEmrSearchHistoryById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:history:add')")
   @Log(
      title = "医师检索条件历史记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSearchHistory emrSearchHistory) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");

      try {
         this.emrSearchHistoryService.insertEmrSearchHistory(emrSearchHistory);
      } catch (Exception var4) {
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:history:edit')")
   @Log(
      title = "医师检索条件历史记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSearchHistory emrSearchHistory) {
      return this.toAjax(this.emrSearchHistoryService.updateEmrSearchHistory(emrSearchHistory));
   }

   @PreAuthorize("@ss.hasPermi('emr:history:remove')")
   @Log(
      title = "医师检索条件历史记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSearchHistoryService.deleteEmrSearchHistoryByIds(ids));
   }
}
