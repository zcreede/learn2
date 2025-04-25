package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.List;
import java.util.Map;
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
@RequestMapping({"/system/datasource"})
public class SyncDatasourceController extends BaseController {
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   @PreAuthorize("@ss.hasPermi('system:datasource:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SyncDatasource syncDatasource) {
      this.startPage();
      List<SyncDatasource> list = this.syncDatasourceService.selectSyncDatasourceList(syncDatasource);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:datasource:export')")
   @Log(
      title = "同步信息数据源配置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SyncDatasource syncDatasource) {
      List<SyncDatasource> list = this.syncDatasourceService.selectSyncDatasourceList(syncDatasource);
      ExcelUtil<SyncDatasource> util = new ExcelUtil(SyncDatasource.class);
      return util.exportExcel(list, "同步信息数据源配置数据");
   }

   @PreAuthorize("@ss.hasPermi('system:datasource:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.syncDatasourceService.selectSyncDatasourceById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:datasource:add')")
   @Log(
      title = "同步信息数据源配置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SyncDatasource syncDatasource) {
      return this.toAjax(this.syncDatasourceService.insertSyncDatasource(syncDatasource));
   }

   @PreAuthorize("@ss.hasPermi('system:datasource:edit')")
   @Log(
      title = "同步信息数据源配置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SyncDatasource syncDatasource) {
      return this.toAjax(this.syncDatasourceService.updateSyncDatasource(syncDatasource));
   }

   @PreAuthorize("@ss.hasPermi('system:datasource:remove')")
   @Log(
      title = "同步信息数据源配置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.syncDatasourceService.deleteSyncDatasourceByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:datasource:query,monitor:job:add')")
   @GetMapping({"/syncData/{syncCode}"})
   public AjaxResult syncDataByCode(@PathVariable("syncCode") String syncCode) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<Map<String, Object>> list = this.syncDatasourceService.syncDataByCode(syncCode);
         ajaxResult.put("list", list);
      } catch (Exception e) {
         this.log.error("同步数据出现异常", e);
         ajaxResult = AjaxResult.error("同步数据出现异常");
      }

      return ajaxResult;
   }
}
