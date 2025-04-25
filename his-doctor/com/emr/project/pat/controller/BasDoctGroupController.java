package com.emr.project.pat.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.BasDoctGroup;
import com.emr.project.pat.service.IBasDoctGroupService;
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
@RequestMapping({"/pat/doctgroup"})
public class BasDoctGroupController extends BaseController {
   @Autowired
   private IBasDoctGroupService basDoctGroupService;

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(BasDoctGroup basDoctGroup) {
      List<BasDoctGroup> list = null;

      try {
         this.startPage();
         list = this.basDoctGroupService.selectGroupList(basDoctGroup);
      } catch (Exception e) {
         this.log.error("查询科室医疗组列表异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:basDoctGroupList')")
   @GetMapping({"/basDoctGroupList"})
   public TableDataInfo basDoctGroupList(BasDoctGroup basDoctGroup) {
      List<BasDoctGroup> list = null;

      try {
         this.startPage();
         list = this.basDoctGroupService.selectBasDoctGroupList(basDoctGroup);
      } catch (Exception e) {
         this.log.error("查询科室医疗组列表异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:export')")
   @Log(
      title = "科室医疗组",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasDoctGroup basDoctGroup) {
      List<BasDoctGroup> list = this.basDoctGroupService.selectBasDoctGroupList(basDoctGroup);
      ExcelUtil<BasDoctGroup> util = new ExcelUtil(BasDoctGroup.class);
      return util.exportExcel(list, "科室医疗组数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basDoctGroupService.selectBasDoctGroupById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:add')")
   @Log(
      title = "科室医疗组",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasDoctGroup basDoctGroup) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");

      try {
         this.basDoctGroupService.insertBasDoctGroup(basDoctGroup);
      } catch (Exception e) {
         this.log.error("新增医疗组出现异常", e);
         ajaxResult = AjaxResult.error("新增医疗组出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:edit')")
   @Log(
      title = "科室医疗组",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasDoctGroup basDoctGroup) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");

      try {
         this.basDoctGroupService.updateBasDoctGroup(basDoctGroup);
      } catch (Exception e) {
         this.log.error("修改医疗组出现异常", e);
         ajaxResult = AjaxResult.error("修改医疗组出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:remove')")
   @Log(
      title = "科室医疗组",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.basDoctGroupService.deleteBasDoctGroupByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('pat:doctgroup:removeDoctGroup')")
   @Log(
      title = "科室医疗组",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/delete/{id}"})
   public AjaxResult removeDoctGroup(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.basDoctGroupService.deleteBasDoctGroupById(id);
      } catch (Exception e) {
         this.log.error("删除科室医疗组出现异常,", e);
         ajaxResult = AjaxResult.error("删除科室医疗组出现异常");
      }

      return ajaxResult;
   }
}
