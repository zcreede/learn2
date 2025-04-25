package com.emr.project.mrhp.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.service.IMrHpDrawApiService;
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
@RequestMapping({"/mrhp/api"})
public class MrHpDrawApiController extends BaseController {
   @Autowired
   private IMrHpDrawApiService mrHpDrawApiService;

   @PreAuthorize("@ss.hasPermi('mrhp:api:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpDrawApi mrHpDrawApi) {
      this.startPage();
      List<MrHpDrawApi> list = this.mrHpDrawApiService.selectMrHpDrawApiList(mrHpDrawApi);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:api:export')")
   @Log(
      title = "病案提取三方API请求参数",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpDrawApi mrHpDrawApi) {
      List<MrHpDrawApi> list = this.mrHpDrawApiService.selectMrHpDrawApiList(mrHpDrawApi);
      ExcelUtil<MrHpDrawApi> util = new ExcelUtil(MrHpDrawApi.class);
      return util.exportExcel(list, "病案提取三方API请求参数数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:api:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.mrHpDrawApiService.selectMrHpDrawApiById(id));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:api:add')")
   @Log(
      title = "病案提取三方API请求参数",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpDrawApi mrHpDrawApi) {
      return this.toAjax(this.mrHpDrawApiService.insertMrHpDrawApi(mrHpDrawApi));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:api:edit')")
   @Log(
      title = "病案提取三方API请求参数",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpDrawApi mrHpDrawApi) {
      return this.toAjax(this.mrHpDrawApiService.updateMrHpDrawApi(mrHpDrawApi));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:api:remove')")
   @Log(
      title = "病案提取三方API请求参数",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.mrHpDrawApiService.deleteMrHpDrawApiByIds(ids));
   }
}
