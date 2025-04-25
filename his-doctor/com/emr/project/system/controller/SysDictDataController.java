package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.req.BsDictTypeReq;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.ArrayList;
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
@RequestMapping({"/system/dict/data"})
public class SysDictDataController extends BaseController {
   @Autowired
   private ISysDictDataService dictDataService;
   @Autowired
   private ISysDictTypeService dictTypeService;

   @PreAuthorize("@ss.hasPermi('system:dict:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysDictData dictData) {
      this.startPage();
      List<SysDictData> list = this.dictDataService.selectDictDataList(dictData);
      return this.getDataTable(list);
   }

   @Log(
      title = "字典数据",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:dict:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysDictData dictData) {
      List<SysDictData> list = this.dictDataService.selectDictDataList(dictData);
      ExcelUtil<SysDictData> util = new ExcelUtil(SysDictData.class);
      return util.exportExcel(list, "字典数据");
   }

   @PreAuthorize("@ss.hasPermi('system:dict:query')")
   @GetMapping({"/{dictCode}"})
   public AjaxResult getInfo(@PathVariable Long dictCode) {
      return AjaxResult.success((Object)this.dictDataService.selectDictDataById(dictCode));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dict:queryByType,emr:index:add,tmpl:index:add,tmpl:index:edit,tmpl:index:tempStateSave,tmpl:auditRecord:pageInfo,tmpl:auditRecord:queryList,qc:flow:dept')")
   @GetMapping({"/type/{dictType}"})
   public AjaxResult dictType(@PathVariable String dictType) {
      List<SysDictData> data = this.dictTypeService.selectDictDataByType(dictType);
      if (StringUtils.isNull(data)) {
         data = new ArrayList();
      }

      return AjaxResult.success((Object)data);
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dict:queryByType,emr:index:add,tmpl:index:add,tmpl:index:edit,tmpl:index:tempStateSave,tmpl:auditRecord:pageInfo,tmpl:auditRecord:queryList,qc:flow:dept')")
   @GetMapping({"/type"})
   public AjaxResult dictType(BsDictTypeReq req) {
      List<SysDictData> data = this.dictTypeService.selectBsDictDataByTypeAndSearch(req);
      if (StringUtils.isNull(data)) {
         data = new ArrayList();
      }

      return AjaxResult.success((Object)data);
   }

   @PreAuthorize("@ss.hasPermi('system:dict:add')")
   @Log(
      title = "字典数据",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysDictData dict) {
      dict.setCreateBy(SecurityUtils.getUsername());
      return this.toAjax(this.dictDataService.insertDictData(dict));
   }

   @PreAuthorize("@ss.hasPermi('system:dict:edit')")
   @Log(
      title = "字典数据",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysDictData dict) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isNotBlank(dict.getIsPreset()) && dict.getIsPreset().equals("1")) {
         ajaxResult = AjaxResult.error("预置信息不能修改");
      } else {
         dict.setUpdateBy(SecurityUtils.getUsername());
         this.dictDataService.updateDictData(dict);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:dict:remove')")
   @Log(
      title = "字典类型",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{dictCodes}"})
   public AjaxResult remove(@PathVariable Long[] dictCodes) {
      this.dictDataService.deleteDictDataByIds(dictCodes);
      return this.success();
   }
}
