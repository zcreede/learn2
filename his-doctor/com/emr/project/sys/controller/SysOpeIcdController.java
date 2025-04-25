package com.emr.project.sys.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.domain.vo.SysOpeIcdVo;
import com.emr.project.sys.service.ISysOpeIcdService;
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
@RequestMapping({"/sys/opeIcd"})
public class SysOpeIcdController extends BaseController {
   @Autowired
   private ISysOpeIcdService sysOpeIcdService;

   @PreAuthorize("@ss.hasAnyPermi('sys:opeIcd:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/list"})
   public AjaxResult list(SysOpeIcd sysOpeIcd) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<SysOpeIcd> list = this.sysOpeIcdService.selectSysOpeIcdList(sysOpeIcd);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病案首页手术icd列表出现异常", e);
         ajaxResult = AjaxResult.error("查询病案首页手术icd列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:opeIcd:getList,emr:index:helper')")
   @GetMapping({"/getList"})
   public AjaxResult getList(SysOpeIcd sysOpeIcd) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         this.startPage();
         List<SysOpeIcd> list = this.sysOpeIcdService.selectOpeIcdList(sysOpeIcd);
         ajaxResult = AjaxResult.success((Object)this.getDataTable(list));
      } catch (Exception e) {
         this.log.error("查询手术icd列表出现异常", e);
         ajaxResult = AjaxResult.error("查询手术icd列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('sys:opeIcd:query')")
   @GetMapping({"/{icdId}"})
   public AjaxResult getInfo(@PathVariable("icdId") Long icdId) {
      return AjaxResult.success((Object)this.sysOpeIcdService.selectSysOpeIcdById(icdId));
   }

   @PreAuthorize("@ss.hasPermi('sys:opeIcd:add')")
   @Log(
      title = "手术/操作代码",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysOpeIcd sysOpeIcd) {
      return this.toAjax(this.sysOpeIcdService.insertSysOpeIcd(sysOpeIcd));
   }

   @PreAuthorize("@ss.hasPermi('sys:opeIcd:edit')")
   @Log(
      title = "手术/操作代码",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysOpeIcd sysOpeIcd) {
      return this.toAjax(this.sysOpeIcdService.updateSysOpeIcd(sysOpeIcd));
   }

   @PreAuthorize("@ss.hasPermi('sys:opeIcd:remove')")
   @Log(
      title = "手术/操作代码",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{icdIds}"})
   public AjaxResult remove(@PathVariable Long[] icdIds) {
      return this.toAjax(this.sysOpeIcdService.deleteSysOpeIcdByIds(icdIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:opeIcd:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/commonList"})
   public TableDataInfo commonList(SysOpeIcdVo sysOpeIcd) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysOpeIcdVo> list = this.sysOpeIcdService.selectDiagCommonList(sysOpeIcd);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询手术icd列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术icd列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('sys:opeIcd:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/getAllList"})
   public TableDataInfo getAllList(SysOpeIcdVo sysOpeIcd) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysOpeIcdVo> list = this.sysOpeIcdService.selectOpeIcdAllList(sysOpeIcd);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询手术icd列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术icd列表出现异常");
      }

      return tableDataInfo;
   }
}
