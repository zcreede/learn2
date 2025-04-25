package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.domain.vo.TmPmSpecVo;
import com.emr.project.tmpm.service.ITmPmSpecService;
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
@RequestMapping({"/tmpm/spec"})
public class TmPmSpecController extends BaseController {
   @Autowired
   private ITmPmSpecService tmPmSpecService;

   @PreAuthorize("@ss.hasAnyPermi('tmpm:spec:list,docOrder:order:list')")
   @GetMapping({"/list"})
   public AjaxResult list(TmPmSpecVo tmPmSpec) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<TmPmSpec> list = this.tmPmSpecService.selectTmPmSpecListByVo(tmPmSpec);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询检验标本维护列表出现异常", e);
         ajaxResult = AjaxResult.error("查询检验标本维护列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpm:spec:export')")
   @Log(
      title = "检验标本维护",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TmPmSpec tmPmSpec) {
      List<TmPmSpec> list = this.tmPmSpecService.selectTmPmSpecList(tmPmSpec);
      ExcelUtil<TmPmSpec> util = new ExcelUtil(TmPmSpec.class);
      return util.exportExcel(list, "检验标本维护数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:spec:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tmPmSpecService.selectTmPmSpecById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:spec:add')")
   @Log(
      title = "检验标本维护",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmPmSpec tmPmSpec) {
      return this.toAjax(this.tmPmSpecService.insertTmPmSpec(tmPmSpec));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:spec:edit')")
   @Log(
      title = "检验标本维护",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TmPmSpec tmPmSpec) {
      return this.toAjax(this.tmPmSpecService.updateTmPmSpec(tmPmSpec));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:spec:remove')")
   @Log(
      title = "检验标本维护",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tmPmSpecService.deleteTmPmSpecByIds(ids));
   }
}
