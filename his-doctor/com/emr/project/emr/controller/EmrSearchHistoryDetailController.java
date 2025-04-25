package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import com.emr.project.emr.service.IEmrSearchHistoryDetailService;
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
@RequestMapping({"/emr/history/detail"})
public class EmrSearchHistoryDetailController extends BaseController {
   @Autowired
   private IEmrSearchHistoryDetailService emrSearchHistoryDetailService;

   @PreAuthorize("@ss.hasPermi('history:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      this.startPage();
      List<EmrSearchHistoryDetail> list = this.emrSearchHistoryDetailService.selectEmrSearchHistoryDetailList(emrSearchHistoryDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('history:detail:export')")
   @Log(
      title = "医师检索条件历史记录详情",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      List<EmrSearchHistoryDetail> list = this.emrSearchHistoryDetailService.selectEmrSearchHistoryDetailList(emrSearchHistoryDetail);
      ExcelUtil<EmrSearchHistoryDetail> util = new ExcelUtil(EmrSearchHistoryDetail.class);
      return util.exportExcel(list, "医师检索条件历史记录详情数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:history:list')")
   @GetMapping({"/detailList"})
   public AjaxResult getDetailList(EmrSearchHistoryDetail emrSearchHistoryDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrSearchHistoryDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrSearchHistoryDetail.getHistoryId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("方案id不能为空");
         }

         if (flag) {
            List<EmrSearchHistoryDetail> list = this.emrSearchHistoryDetailService.selectEmrSearchHistoryDetailByHistoryId(emrSearchHistoryDetail.getHistoryId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("获取医师检索条件历史记录详情详细信息出现异常", e);
         ajaxResult = AjaxResult.error("获取医师检索条件历史记录详情详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('history:detail:add')")
   @Log(
      title = "医师检索条件历史记录详情",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSearchHistoryDetail emrSearchHistoryDetail) {
      return this.toAjax(this.emrSearchHistoryDetailService.insertEmrSearchHistoryDetail(emrSearchHistoryDetail));
   }

   @PreAuthorize("@ss.hasPermi('history:detail:edit')")
   @Log(
      title = "医师检索条件历史记录详情",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSearchHistoryDetail emrSearchHistoryDetail) {
      return this.toAjax(this.emrSearchHistoryDetailService.updateEmrSearchHistoryDetail(emrSearchHistoryDetail));
   }

   @PreAuthorize("@ss.hasPermi('history:detail:remove')")
   @Log(
      title = "医师检索条件历史记录详情",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSearchHistoryDetailService.deleteEmrSearchHistoryDetailByIds(ids));
   }
}
