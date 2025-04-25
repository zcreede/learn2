package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysDictType;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/system/dict/type"})
public class SysDictTypeController extends BaseController {
   @Autowired
   private ISysDictTypeService dictTypeService;

   @PreAuthorize("@ss.hasPermi('system:dict:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysDictType dictType) {
      this.startPage();
      List<SysDictType> list = this.dictTypeService.selectDictTypeList(dictType);
      return this.getDataTable(list);
   }

   @Log(
      title = "字典类型",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:dict:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysDictType dictType) {
      List<SysDictType> list = this.dictTypeService.selectDictTypeList(dictType);
      ExcelUtil<SysDictType> util = new ExcelUtil(SysDictType.class);
      return util.exportExcel(list, "字典类型");
   }

   @PreAuthorize("@ss.hasPermi('system:dict:query')")
   @GetMapping({"/{dictId}"})
   public AjaxResult getInfo(@PathVariable Long dictId) {
      return AjaxResult.success((Object)this.dictTypeService.selectDictTypeById(dictId));
   }

   @PreAuthorize("@ss.hasPermi('system:dict:add')")
   @Log(
      title = "字典类型",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysDictType dict) {
      if ("1".equals(this.dictTypeService.checkDictTypeUnique(dict))) {
         return AjaxResult.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
      } else {
         dict.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.dictTypeService.insertDictType(dict));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:dict:edit')")
   @Log(
      title = "字典类型",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysDictType dict) {
      if ("1".equals(this.dictTypeService.checkDictTypeUnique(dict))) {
         return AjaxResult.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
      } else {
         dict.setUpdateBy(SecurityUtils.getUsername());
         return this.toAjax(this.dictTypeService.updateDictType(dict));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
   @Log(
      title = "字典类型",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{dictIds}"})
   public AjaxResult remove(@PathVariable Long[] dictIds) {
      this.dictTypeService.deleteDictTypeByIds(dictIds);
      return this.success();
   }

   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
   @Log(
      title = "字典类型",
      businessType = BusinessType.CLEAN
   )
   @DeleteMapping({"/refreshCache"})
   public AjaxResult refreshCache() {
      this.dictTypeService.resetDictCache();
      return AjaxResult.success();
   }

   @GetMapping({"/optionselect"})
   @PreAuthorize("@ss.hasAnyPermi('system:dict:optionselect,system:elem:add,mrhp:set:add')")
   public AjaxResult optionselect() {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<SysDictType> dictTypes = this.dictTypeService.selectDictTypeAll();
         ajaxResult = AjaxResult.success((Object)dictTypes);
      } catch (Exception e) {
         this.log.error("获取字典选择框列表出现异常", e);
         ajaxResult = AjaxResult.error("获取字典选择框列表出现异常");
      }

      return ajaxResult;
   }
}
