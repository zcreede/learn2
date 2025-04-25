package com.emr.project.tmpm.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.vo.ClinItemDetailVo;
import com.emr.project.tmpm.service.IClinItemDetailService;
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
@RequestMapping({"/clin/detail"})
public class ClinItemDetailController extends BaseController {
   @Autowired
   private IClinItemDetailService clinItemDetailService;

   @PreAuthorize("@ss.hasPermi('clin:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ClinItemDetail clinItemDetail) {
      this.startPage();
      List<ClinItemDetail> list = this.clinItemDetailService.selectClinItemDetailList(clinItemDetail);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('clin:detail:export')")
   @Log(
      title = "医嘱项目明细",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ClinItemDetail clinItemDetail) {
      List<ClinItemDetail> list = this.clinItemDetailService.selectClinItemDetailList(clinItemDetail);
      ExcelUtil<ClinItemDetail> util = new ExcelUtil(ClinItemDetail.class);
      return util.exportExcel(list, "医嘱项目明细数据");
   }

   @PreAuthorize("@ss.hasPermi('clin:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.clinItemDetailService.selectClinItemDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('clin:detail:add')")
   @Log(
      title = "医嘱项目明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ClinItemDetail clinItemDetail) {
      return this.toAjax(this.clinItemDetailService.insertClinItemDetail(clinItemDetail));
   }

   @PreAuthorize("@ss.hasPermi('clin:detail:edit')")
   @Log(
      title = "医嘱项目明细",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ClinItemDetail clinItemDetail) {
      return this.toAjax(this.clinItemDetailService.updateClinItemDetail(clinItemDetail));
   }

   @PreAuthorize("@ss.hasPermi('clin:detail:remove')")
   @Log(
      title = "医嘱项目明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.clinItemDetailService.deleteClinItemDetailByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:check:list')")
   @GetMapping({"/detailList"})
   public AjaxResult detailList(ClinItemDetailVo clinItemDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (clinItemDetailVo.getItemCdList() == null) {
            ajaxResult = AjaxResult.error("项目编码集合不能为空");
         } else {
            List<ClinItemDetail> detailList = this.clinItemDetailService.selectClinItemDetailByItemCds(clinItemDetailVo.getItemCdList());
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("根据项目编码查询医嘱项目明细出现异常", e);
         ajaxResult = AjaxResult.error("根据项目编码查询医嘱项目明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/clinDetailList"})
   public AjaxResult clinDetailList(ClinItemDetailVo clinItemDetailVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (clinItemDetailVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemDetailVo.getItemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目编码不能为空");
         }

         if (flag && StringUtils.isEmpty(clinItemDetailVo.getItemClassCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目类型编码不能为空");
         }

         if (flag) {
            List<ClinItemDetailVo> detailList = this.clinItemDetailService.selectOrderClinDetailList(clinItemDetailVo);
            ajaxResult = AjaxResult.success((Object)detailList);
         }
      } catch (Exception e) {
         this.log.error("查询开立项目详细信息--医嘱录入出现异常", e);
         ajaxResult = AjaxResult.error("查询开立项目详细信息--医嘱录入出现异常");
      }

      return ajaxResult;
   }
}
