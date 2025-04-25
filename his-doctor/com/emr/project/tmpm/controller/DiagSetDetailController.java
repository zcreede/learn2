package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.DiagSetDetail;
import com.emr.project.tmpm.service.IDiagSetDetailService;
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
@RequestMapping({"/diagSet/detail"})
public class DiagSetDetailController extends BaseController {
   @Autowired
   private IDiagSetDetailService diagSetDetailService;

   @PreAuthorize("@ss.hasPermi('diagSet:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DiagSetDetail diagSetDetail) {
      this.startPage();
      List<DiagSetDetail> list = this.diagSetDetailService.selectDiagSetDetailList(diagSetDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('diagSet:detail:export')")
   @Log(
      title = "诊断套餐明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(DiagSetDetail diagSetDetail) {
      List<DiagSetDetail> list = this.diagSetDetailService.selectDiagSetDetailList(diagSetDetail);
      ExcelUtil<DiagSetDetail> util = new ExcelUtil(DiagSetDetail.class);
      return util.exportExcel(list, "诊断套餐明细数据");
   }

   @PreAuthorize("@ss.hasPermi('diagSet:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.diagSetDetailService.selectDiagSetDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('diagSet:detail:save')")
   @Log(
      title = "诊断套餐明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/save"})
   public AjaxResult save(@RequestBody List diagSetDetailList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (diagSetDetailList == null || diagSetDetailList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            for(DiagSetDetail detail : diagSetDetailList) {
               if (flag && StringUtils.isEmpty(detail.getSetCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("组套编码不能为空");
               }

               if (flag && StringUtils.isEmpty(detail.getDiagCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断编码不能为空");
               }

               if (flag && StringUtils.isEmpty(detail.getDiagName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断名称不能为空");
               }
            }
         }

         if (flag) {
            this.diagSetDetailService.saveDiagSetDetail(diagSetDetailList);
         }
      } catch (Exception e) {
         this.log.error("保存诊断套餐明细出现异常", e);
         ajaxResult = AjaxResult.error("保存诊断套餐明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('diagSet:detail:edit')")
   @Log(
      title = "诊断套餐明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DiagSetDetail diagSetDetail) {
      return this.toAjax(this.diagSetDetailService.updateDiagSetDetail(diagSetDetail));
   }

   @PreAuthorize("@ss.hasPermi('diagSet:detail:remove')")
   @Log(
      title = "诊断套餐明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.diagSetDetailService.deleteDiagSetDetailByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('diagSet:main:list,pat:diag:write')")
   @GetMapping({"/listBySetCd"})
   public AjaxResult listBySetCd(DiagSetDetail diagSetDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(diagSetDetail.getSetCd())) {
            ajaxResult = AjaxResult.error("套餐编码不能为空");
         } else {
            ajaxResult = AjaxResult.success((Object)this.diagSetDetailService.selectDetailListBySetCd(diagSetDetail.getSetCd()));
         }
      } catch (Exception e) {
         this.log.error("根据组套编码查询详情集合出现异常", e);
         ajaxResult = AjaxResult.error("根据组套编码查询详情集合出现异常");
      }

      return ajaxResult;
   }
}
