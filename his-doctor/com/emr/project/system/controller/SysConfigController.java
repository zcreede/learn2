package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysConfig;
import com.emr.project.system.service.ISysConfigService;
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
@RequestMapping({"/system/config"})
public class SysConfigController extends BaseController {
   @Autowired
   private ISysConfigService configService;

   @PreAuthorize("@ss.hasPermi('system:config:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysConfig config) {
      this.startPage();
      List<SysConfig> list = this.configService.selectConfigList(config);
      return this.getDataTable(list);
   }

   @Log(
      title = "参数管理",
      businessType = BusinessType.EXPORT
   )
   @PreAuthorize("@ss.hasPermi('system:config:export')")
   @GetMapping({"/export"})
   public AjaxResult export(SysConfig config) {
      List<SysConfig> list = this.configService.selectConfigList(config);
      ExcelUtil<SysConfig> util = new ExcelUtil(SysConfig.class);
      return util.exportExcel(list, "参数数据");
   }

   @PreAuthorize("@ss.hasPermi('system:config:query')")
   @GetMapping({"/{configId}"})
   public AjaxResult getInfo(@PathVariable Long configId) {
      return AjaxResult.success((Object)this.configService.selectConfigById(configId));
   }

   @GetMapping({"/configKey/{configKey}"})
   public AjaxResult getConfigKey(@PathVariable String configKey) {
      return AjaxResult.success(this.configService.selectConfigByKey(configKey));
   }

   @PreAuthorize("@ss.hasPermi('system:config:add')")
   @Log(
      title = "参数管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@Validated @RequestBody SysConfig config) {
      if ("1".equals(this.configService.checkConfigKeyUnique(config))) {
         return AjaxResult.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
      } else {
         config.setCreateBy(SecurityUtils.getUsername());
         return this.toAjax(this.configService.insertConfig(config));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:config:edit')")
   @Log(
      title = "参数管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@Validated @RequestBody SysConfig config) {
      if ("1".equals(this.configService.checkConfigKeyUnique(config))) {
         return AjaxResult.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
      } else {
         config.setUpdateBy(SecurityUtils.getUsername());
         return this.toAjax(this.configService.updateConfig(config));
      }
   }

   @PreAuthorize("@ss.hasPermi('system:config:remove')")
   @Log(
      title = "参数管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{configIds}"})
   public AjaxResult remove(@PathVariable Long[] configIds) {
      this.configService.deleteConfigByIds(configIds);
      return this.success();
   }

   @PreAuthorize("@ss.hasPermi('system:config:remove')")
   @Log(
      title = "参数管理",
      businessType = BusinessType.CLEAN
   )
   @DeleteMapping({"/refreshCache"})
   public AjaxResult refreshCache() {
      this.configService.resetConfigCache();
      return AjaxResult.success();
   }
}
