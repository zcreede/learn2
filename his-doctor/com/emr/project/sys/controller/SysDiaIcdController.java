package com.emr.project.sys.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.domain.vo.SysDiaIcdVo;
import com.emr.project.sys.service.ISysDiaIcdService;
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
@RequestMapping({"/sys/diaIcd"})
public class SysDiaIcdController extends BaseController {
   @Autowired
   private ISysDiaIcdService sysDiaIcdService;

   @PreAuthorize("@ss.hasAnyPermi('sys:diaIcd:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/list"})
   public AjaxResult list(SysDiaIcd sysDiaIcd) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<SysDiaIcd> list = this.sysDiaIcdService.selectSysDiaIcdList(sysDiaIcd);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病案首页诊断字典列表", e);
         ajaxResult = AjaxResult.error("查询病案首页诊断字典列表");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:diaIcd:getList,emr:index:helper,pat:diag:write,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/getList"})
   public TableDataInfo getList(SysDiaIcd sysDiaIcd) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysDiaIcdVo> list = this.sysDiaIcdService.selectSysDiaIcdListByType(sysDiaIcd);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询诊断字典列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询诊断字典列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:write,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/commonList"})
   public TableDataInfo commonList(SysDiaIcdVo sysDiaIcd) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysDiaIcdVo> list = this.sysDiaIcdService.selectDiagCommonList(sysDiaIcd);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询常用诊断字典列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询常用诊断字典列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('sys:diaIcd:query')")
   @GetMapping({"/{icdId}"})
   public AjaxResult getInfo(@PathVariable("icdId") Long icdId) {
      return AjaxResult.success((Object)this.sysDiaIcdService.selectSysDiaIcdById(icdId));
   }

   @PreAuthorize("@ss.hasPermi('sys:diaIcd:add')")
   @Log(
      title = "诊断字典",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysDiaIcd sysDiaIcd) {
      return this.toAjax(this.sysDiaIcdService.insertSysDiaIcd(sysDiaIcd));
   }

   @PreAuthorize("@ss.hasPermi('sys:diaIcd:edit')")
   @Log(
      title = "诊断字典",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysDiaIcd sysDiaIcd) {
      return this.toAjax(this.sysDiaIcdService.updateSysDiaIcd(sysDiaIcd));
   }

   @PreAuthorize("@ss.hasPermi('sys:diaIcd:remove')")
   @Log(
      title = "诊断字典",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{icdIds}"})
   public AjaxResult remove(@PathVariable Long[] icdIds) {
      return this.toAjax(this.sysDiaIcdService.deleteSysDiaIcdByIds(icdIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:diaIcd:getAllList,emr:index:helper')")
   @GetMapping({"/getAllList"})
   public TableDataInfo getAllList(SysDiaIcdVo sysDiaIcdVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysDiaIcd> list = this.sysDiaIcdService.selectSysDiaIcdAndOpeIcd(sysDiaIcdVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询诊断字典列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询诊断字典列表出现异常");
      }

      return tableDataInfo;
   }
}
