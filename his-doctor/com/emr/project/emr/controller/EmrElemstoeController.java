package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.service.IEmrElemstoeService;
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
@RequestMapping({"/emr/elemstoe"})
public class EmrElemstoeController extends BaseController {
   @Autowired
   private IEmrElemstoeService emrElemstoeService;

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrElemstoe emrElemstoe) {
      this.startPage();
      List<EmrElemstoe> list = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:export')")
   @Log(
      title = "病历结构化存储",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrElemstoe emrElemstoe) {
      List<EmrElemstoe> list = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);
      ExcelUtil<EmrElemstoe> util = new ExcelUtil(EmrElemstoe.class);
      return util.exportExcel(list, "病历结构化存储数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrElemstoeService.selectEmrElemstoeById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:add')")
   @Log(
      title = "病历结构化存储",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrElemstoe emrElemstoe) {
      return this.toAjax(this.emrElemstoeService.insertEmrElemstoe(emrElemstoe));
   }

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:edit')")
   @Log(
      title = "病历结构化存储",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrElemstoe emrElemstoe) {
      return this.toAjax(this.emrElemstoeService.updateEmrElemstoe(emrElemstoe));
   }

   @PreAuthorize("@ss.hasPermi('emr:elemstoe:remove')")
   @Log(
      title = "病历结构化存储",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrElemstoeService.deleteEmrElemstoeByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:elemstoe:elemstoeList,emr:index:helper')")
   @GetMapping({"/elemstoeList"})
   public TableDataInfo elemstoeList(EmrElemstoe emrElemstoe) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isBlank(emrElemstoe.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者主索引id参数不能为空");
         } else if (StringUtils.isBlank(emrElemstoe.getMrTypeCd())) {
            tableDataInfo = new TableDataInfo(500, "病历文件分类编号参数不能为空");
         } else {
            this.startPage();
            List<EmrElemstoe> list = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询病历结构化存储列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历结构化存储列表异常");
      }

      return tableDataInfo;
   }
}
