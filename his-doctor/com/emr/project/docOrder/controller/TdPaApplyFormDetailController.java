package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaApplyFormDetail;
import com.emr.project.docOrder.service.ITdPaApplyFormDetailService;
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
@RequestMapping({"/tmpm/detail"})
public class TdPaApplyFormDetailController extends BaseController {
   @Autowired
   private ITdPaApplyFormDetailService tdPaApplyFormDetailService;

   @PreAuthorize("@ss.hasPermi('tmpm:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaApplyFormDetail tdPaApplyFormDetail) {
      this.startPage();
      List<TdPaApplyFormDetail> list = this.tdPaApplyFormDetailService.selectTdPaApplyFormDetailList(tdPaApplyFormDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:detail:export')")
   @Log(
      title = "检查检验申请单项目明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaApplyFormDetail tdPaApplyFormDetail) {
      List<TdPaApplyFormDetail> list = this.tdPaApplyFormDetailService.selectTdPaApplyFormDetailList(tdPaApplyFormDetail);
      ExcelUtil<TdPaApplyFormDetail> util = new ExcelUtil(TdPaApplyFormDetail.class);
      return util.exportExcel(list, "检查检验申请单项目明细数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:detail:add')")
   @Log(
      title = "检查检验申请单项目明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaApplyFormDetail tdPaApplyFormDetail) {
      return this.toAjax(this.tdPaApplyFormDetailService.insertTdPaApplyFormDetail(tdPaApplyFormDetail));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:detail:edit')")
   @Log(
      title = "检查检验申请单项目明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaApplyFormDetail tdPaApplyFormDetail) {
      return this.toAjax(this.tdPaApplyFormDetailService.updateTdPaApplyFormDetail(tdPaApplyFormDetail));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:detail:remove')")
   @Log(
      title = "检查检验申请单项目明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{applyFormNos}"})
   public AjaxResult remove(@PathVariable String[] applyFormNos) {
      return this.toAjax(this.tdPaApplyFormDetailService.deleteTdPaApplyFormDetailByIds(applyFormNos));
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:check:list')")
   @GetMapping({"/applyDetailList"})
   public AjaxResult applyDetailList(TdPaApplyFormDetail tdPaApplyFormDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdPaApplyFormDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyFormDetail.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaApplyFormDetail.getItemSortNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单项目序号不能为空");
         }

         if (flag) {
            List<TdPaApplyFormDetail> list = this.tdPaApplyFormDetailService.selectTdPaApplyFormDetailList(tdPaApplyFormDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询申请单中某个项目详情出现异常", e);
         ajaxResult = AjaxResult.error("查询申请单中某个项目详情出现异常");
      }

      return ajaxResult;
   }
}
