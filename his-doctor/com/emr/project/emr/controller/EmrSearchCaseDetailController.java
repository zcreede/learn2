package com.emr.project.emr.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.service.IEmrSearchCaseDetailService;
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
@RequestMapping({"/emr/detail"})
public class EmrSearchCaseDetailController extends BaseController {
   @Autowired
   private IEmrSearchCaseDetailService emrSearchCaseDetailService;

   @PreAuthorize("@ss.hasPermi('emr:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSearchCaseDetail emrSearchCaseDetail) {
      this.startPage();
      List<EmrSearchCaseDetail> list = this.emrSearchCaseDetailService.selectEmrSearchCaseDetailList(emrSearchCaseDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:detail:export')")
   @Log(
      title = "病历检索方案详情",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSearchCaseDetail emrSearchCaseDetail) {
      List<EmrSearchCaseDetail> list = this.emrSearchCaseDetailService.selectEmrSearchCaseDetailList(emrSearchCaseDetail);
      ExcelUtil<EmrSearchCaseDetail> util = new ExcelUtil(EmrSearchCaseDetail.class);
      return util.exportExcel(list, "病历检索方案详情数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:case:list')")
   @GetMapping({"/detailList"})
   public AjaxResult getDetailList(EmrSearchCaseDetail emrSearchCaseDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (emrSearchCaseDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrSearchCaseDetail.getCaseId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("方案id不能为空");
         }

         if (flag) {
            List<EmrSearchCaseDetail> list = this.emrSearchCaseDetailService.selectEmrSearchCaseDetailByCaseId(emrSearchCaseDetail.getCaseId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询病历检索方案详情信息出现异常", e);
         ajaxResult = AjaxResult.error("查询病历检索方案详情信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:detail:add')")
   @Log(
      title = "病历检索方案详情",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSearchCaseDetail emrSearchCaseDetail) {
      return this.toAjax(this.emrSearchCaseDetailService.insertEmrSearchCaseDetail(emrSearchCaseDetail));
   }

   @PreAuthorize("@ss.hasPermi('emr:detail:edit')")
   @Log(
      title = "病历检索方案详情",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSearchCaseDetail emrSearchCaseDetail) {
      return this.toAjax(this.emrSearchCaseDetailService.updateEmrSearchCaseDetail(emrSearchCaseDetail));
   }

   @PreAuthorize("@ss.hasPermi('emr:detail:remove')")
   @Log(
      title = "病历检索方案详情",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSearchCaseDetailService.deleteEmrSearchCaseDetailByIds(ids));
   }
}
