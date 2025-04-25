package com.emr.project.tmpl.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tmpl.domain.SubTmplText;
import com.emr.project.tmpl.service.ISubTmplTextService;
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
@RequestMapping({"/subTemp/text"})
public class SubTmplTextController extends BaseController {
   @Autowired
   private ISubTmplTextService subTmplTextService;

   @PreAuthorize("@ss.hasAnyPermi('subTemp:text:list,emr:index:helper')")
   @GetMapping({"/list"})
   public TableDataInfo list(SubTmplText subTmplText) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (subTmplText == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getSubTempType())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "子模板类型不能为空");
         }

         if (flag) {
            this.startPage();
            List<SubTmplText> list = this.subTmplTextService.selectSubTmplTextList(subTmplText);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询子模板列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询子模板列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('subTemp:text:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.subTmplTextService.selectSubTmplTextById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('subTemp:text:add,emr:index:helper')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SubTmplText subTmplText) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (subTmplText == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getSubTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getSubTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.subTmplTextService.insertSubTmplText(subTmplText);
         }
      } catch (Exception e) {
         this.log.error("另存为子模板出现异常", e);
         ajaxResult = AjaxResult.error("另存为子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('subTemp:text:edit,emr:index:helper')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SubTmplText subTmplText) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (subTmplText == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && subTmplText.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getSubTempType())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板类型不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getSubTempName())) {
            flag = false;
            ajaxResult = AjaxResult.error("子模板名称不能为空");
         }

         if (flag && StringUtils.isEmpty(subTmplText.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.subTmplTextService.updateSubTmplText(subTmplText);
         }
      } catch (Exception e) {
         this.log.error("修改子模板出现异常", e);
         ajaxResult = AjaxResult.error("修改子模板出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('subTemp:text:remove,emr:index:helper')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.subTmplTextService.deleteSubTmplTextByIds(ids);
      } catch (Exception e) {
         this.log.error("删除子模板出现异常", e);
         ajaxResult = AjaxResult.error("删除子模板出现异常");
      }

      return ajaxResult;
   }
}
