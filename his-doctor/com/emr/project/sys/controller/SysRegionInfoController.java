package com.emr.project.sys.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.sys.domain.SysRegionInfo;
import com.emr.project.sys.service.ISysRegionInfoService;
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
@RequestMapping({"/sys/region"})
public class SysRegionInfoController extends BaseController {
   @Autowired
   private ISysRegionInfoService sysRegionInfoService;

   @PreAuthorize("@ss.hasAnyPermi('sys:region:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/list"})
   public AjaxResult list(SysRegionInfo sysRegionInfo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(sysRegionInfo.getSuperCode())) {
            ajaxResult = AjaxResult.error("上级代码参数不能为空");
         } else {
            List<SysRegionInfo> list = this.sysRegionInfoService.selectSysRegionInfoList(sysRegionInfo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询行政区域划分列表异常", e);
         ajaxResult = AjaxResult.error("查询行政区域划分列表异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('sys:region:query')")
   @GetMapping({"/{code}"})
   public AjaxResult getInfo(@PathVariable("code") String code) {
      return AjaxResult.success((Object)this.sysRegionInfoService.selectSysRegionInfoById(code));
   }

   @PreAuthorize("@ss.hasPermi('sys:region:add')")
   @Log(
      title = "行政区域划分",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysRegionInfo sysRegionInfo) {
      return this.toAjax(this.sysRegionInfoService.insertSysRegionInfo(sysRegionInfo));
   }

   @PreAuthorize("@ss.hasPermi('sys:region:edit')")
   @Log(
      title = "行政区域划分",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysRegionInfo sysRegionInfo) {
      return this.toAjax(this.sysRegionInfoService.updateSysRegionInfo(sysRegionInfo));
   }

   @PreAuthorize("@ss.hasPermi('sys:region:remove')")
   @Log(
      title = "行政区域划分",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{codes}"})
   public AjaxResult remove(@PathVariable String[] codes) {
      return this.toAjax(this.sysRegionInfoService.deleteSysRegionInfoByIds(codes));
   }
}
