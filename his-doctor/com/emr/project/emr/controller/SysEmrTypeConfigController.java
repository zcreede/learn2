package com.emr.project.emr.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
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
@RequestMapping({"/emr/config"})
public class SysEmrTypeConfigController extends BaseController {
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;

   @PreAuthorize("@ss.hasPermi('emr:config:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysEmrTypeConfig sysEmrTypeConfig) {
      List<SysEmrTypeConfigVo> list = new ArrayList(1);

      try {
         this.startPage();
         list = this.sysEmrTypeConfigService.selectSysEmrTypeConfigList(sysEmrTypeConfig);
      } catch (Exception e) {
         this.log.error("查询病历类型设置列表出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:config:list,emr:index:add')")
   @GetMapping({"/listByClass"})
   public TableDataInfo listByClass(SysEmrTypeConfigVo sysEmrTypeConfig) {
      List<SysEmrTypeConfigVo> list = new ArrayList(1);

      try {
         this.startPage();
         list = this.sysEmrTypeConfigService.selectListByNameAndClass(sysEmrTypeConfig.getEmrClassCode(), sysEmrTypeConfig.getEmrTypeName());
      } catch (Exception e) {
         this.log.error("查询病历类型设置列表出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:config:export')")
   @Log(
      title = "病历类型设置",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysEmrTypeConfig sysEmrTypeConfig) {
      List<SysEmrTypeConfigVo> list = this.sysEmrTypeConfigService.selectSysEmrTypeConfigList(sysEmrTypeConfig);
      ExcelUtil<SysEmrTypeConfigVo> util = new ExcelUtil(SysEmrTypeConfigVo.class);
      return util.exportExcel(list, "病历类型设置数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:config:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysEmrTypeConfigService.selectSysEmrTypeConfigById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:config:add')")
   @Log(
      title = "病历类型设置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysEmrTypeConfig sysEmrTypeConfig) {
      return this.toAjax(this.sysEmrTypeConfigService.insertSysEmrTypeConfig(sysEmrTypeConfig));
   }

   @PreAuthorize("@ss.hasPermi('emr:config:edit')")
   @Log(
      title = "病历类型设置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysEmrTypeConfig sysEmrTypeConfig) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && sysEmrTypeConfig == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysEmrTypeConfig.getEmrTypeCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写归档类型");
         }

         if (flag && sysEmrTypeConfig.getEmrClassCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写病历分类");
         }

         if (flag && sysEmrTypeConfig.getSignFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写签名类型");
         }

         if (flag && sysEmrTypeConfig.getMergeFlag() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写合并打印类型");
         }

         if (flag && sysEmrTypeConfig.getIsOds() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择适用范围");
         }

         if (flag && StringUtils.isEmpty(sysEmrTypeConfig.getReviewedLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择审签类型");
         }

         if (flag && sysEmrTypeConfig.getLimitWriteNum() != null && sysEmrTypeConfig.getLimitWriteNum() <= 0) {
            flag = false;
            ajaxResult = AjaxResult.error("书写数量必须是大于0的正整数，请修改后再试！");
         }

         if (flag && "3".equals(sysEmrTypeConfig.getReviewedLevel()) && StringUtils.isNotEmpty(sysEmrTypeConfig.getSecondDocCode()) && StringUtils.isEmpty(sysEmrTypeConfig.getThirdDocCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("三级审签中，指定二级审签医师后，三级审签医师必须指定！");
         }

         if (flag) {
            this.sysEmrTypeConfigService.updateSysEmrTypeConfig(sysEmrTypeConfig);
         }
      } catch (Exception e) {
         this.log.error("保存病历类型设置出现异常，", e);
         ajaxResult = AjaxResult.error("保存病历类型设置出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:config:remove')")
   @Log(
      title = "病历类型设置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysEmrTypeConfigService.deleteSysEmrTypeConfigByIds(ids));
   }
}
