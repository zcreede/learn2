package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysEmrConfig;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
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
@RequestMapping({"/system/emr/config"})
public class SysEmrConfigController extends BaseController {
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasPermi('system:config:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysEmrConfig sysEmrConfig) {
      this.startPage();
      List<SysEmrConfig> list = new ArrayList(1);

      try {
         list = this.sysEmrConfigService.selectSysEmrConfigList(sysEmrConfig);
      } catch (Exception e) {
         this.log.error("查询电子病历配置列表出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:config:query')")
   @GetMapping({"/selectSysEmrConfigById/{id}"})
   public AjaxResult list(@PathVariable("id") Long id) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         SysEmrConfig sysEmrConfig1 = this.sysEmrConfigService.selectSysEmrConfigById(id);
         ajaxResult.put("data", sysEmrConfig1);
      } catch (Exception e) {
         this.log.error("查询电子病历配置列表出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:config:export')")
   @Log(
      title = "电子病历配置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysEmrConfig sysEmrConfig) {
      List<SysEmrConfig> list = new ArrayList(1);

      try {
         list = this.sysEmrConfigService.selectSysEmrConfigList(sysEmrConfig);
      } catch (Exception e) {
         this.log.error("导出电子病历配置列表：", e);
      }

      ExcelUtil<SysEmrConfig> util = new ExcelUtil(SysEmrConfig.class);
      return util.exportExcel(list, "电子病历配置数据");
   }

   @PreAuthorize("@ss.hasPermi('system:config:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      new AjaxResult();
      SysEmrConfig emrConfig = null;

      AjaxResult ajaxResult;
      try {
         emrConfig = this.sysEmrConfigService.selectSysEmrConfigById(id);
         ajaxResult = AjaxResult.success((Object)emrConfig);
      } catch (Exception e) {
         this.log.error("获取电子病历配置详细信息出现异常：", e);
         ajaxResult = AjaxResult.error("获取电子病历配置详细信息出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:config:add')")
   @Log(
      title = "电子病历配置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysEmrConfig sysEmrConfig) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      Boolean flag = true;
      int count = 0;
      if (sysEmrConfig == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数未填写");
      }

      if (StringUtils.isBlank(sysEmrConfig.getConfigKey())) {
         flag = false;
         ajaxResult = AjaxResult.error("config_key不能为空");
      }

      try {
         SysEmrConfig sysEmrConfig1 = this.sysEmrConfigService.selectConfigValueByKey(sysEmrConfig.getConfigKey());
         if (sysEmrConfig.getId() != null && StringUtils.isNotBlank(String.valueOf(sysEmrConfig.getId()))) {
            if (flag) {
               count = this.sysEmrConfigService.updateSysEmrConfig(sysEmrConfig);
               ajaxResult.put("data", count);
            }
         } else {
            if (sysEmrConfig1 != null && StringUtils.isNotBlank(sysEmrConfig1.getConfigValue())) {
               flag = false;
               ajaxResult = AjaxResult.error("config_key不允许重复");
            }

            if (flag) {
               count = this.sysEmrConfigService.insertSysEmrConfig(sysEmrConfig);
               ajaxResult.put("data", count);
            }
         }
      } catch (Exception e) {
         this.log.error("新增电子病历配置出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:config:edit')")
   @Log(
      title = "电子病历配置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysEmrConfig sysEmrConfig) {
      int count = 0;

      try {
         count = this.sysEmrConfigService.updateSysEmrConfig(sysEmrConfig);
      } catch (Exception e) {
         this.log.error("修改电子病历配置出现异常：", e);
      }

      return this.toAjax(count);
   }

   @PreAuthorize("@ss.hasPermi('system:config:remove')")
   @Log(
      title = "电子病历配置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      int count = 0;

      try {
         count = this.sysEmrConfigService.deleteSysEmrConfigByIds(ids);
      } catch (Exception e) {
         this.log.error("删除电子病历配置出现异常：", e);
      }

      return this.toAjax(count);
   }

   @Log(
      title = "查询ca配置信息",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/caInfo"})
   public AjaxResult getCaInfo() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String caFalg = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
         ajaxResult.put("caFalg", caFalg);
         ajaxResult.put("caType", caType);
         String systemName = this.sysEmrConfigService.selectSysEmrConfigByKey("0065");
         ajaxResult.put("systemName", systemName);
      } catch (Exception e) {
         this.log.error("查询ca配置信息出现异常：", e);
         ajaxResult = AjaxResult.error("查询ca配置信息出现异常");
      }

      return ajaxResult;
   }
}
