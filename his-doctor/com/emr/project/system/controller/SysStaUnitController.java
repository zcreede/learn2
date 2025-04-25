package com.emr.project.system.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysStaUnit;
import com.emr.project.system.domain.vo.SysStaUnitVo;
import com.emr.project.system.service.ISysStaUnitService;
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
@RequestMapping({"/system/staUnit"})
public class SysStaUnitController extends BaseController {
   @Autowired
   private ISysStaUnitService sysStaUnitService;

   @PreAuthorize("@ss.hasAnyPermi('system:staUnit:list,system:elem:add')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysStaUnitVo sysStaUnitVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysStaUnitVo> list = this.sysStaUnitService.selectSysStaUnitList(sysStaUnitVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询标准数据元分页列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询标准数据元分页列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('system:staUnit:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysStaUnitService.selectSysStaUnitById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:staUnit:add')")
   @Log(
      title = "标准数据元",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysStaUnit sysStaUnit) {
      return this.toAjax(this.sysStaUnitService.insertSysStaUnit(sysStaUnit));
   }

   @PreAuthorize("@ss.hasPermi('system:staUnit:edit')")
   @Log(
      title = "标准数据元",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysStaUnit sysStaUnit) {
      return this.toAjax(this.sysStaUnitService.updateSysStaUnit(sysStaUnit));
   }

   @PreAuthorize("@ss.hasPermi('system:staUnit:remove')")
   @Log(
      title = "标准数据元",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysStaUnitService.deleteSysStaUnitByIds(ids));
   }
}
