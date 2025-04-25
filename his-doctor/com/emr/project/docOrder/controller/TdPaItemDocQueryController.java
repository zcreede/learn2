package com.emr.project.docOrder.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
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
@RequestMapping({"/docOrder/query"})
public class TdPaItemDocQueryController extends BaseController {
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;

   @PreAuthorize("@ss.hasPermi('docOrder:query:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaItemDocQuery tdPaItemDocQuery) {
      this.startPage();
      List<TdPaItemDocQuery> list = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:query:export')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaItemDocQuery tdPaItemDocQuery) {
      List<TdPaItemDocQuery> list = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
      ExcelUtil<TdPaItemDocQuery> util = new ExcelUtil(TdPaItemDocQuery.class);
      return util.exportExcel(list, "【请填写功能名称】数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:query:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaItemDocQueryService.selectTdPaItemDocQueryById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:query:add,docOrder:order:list,ope:drug:requisition')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaItemDocQuery tdPaItemDocQuery) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaItemDocQuery == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaItemDocQuery.getQueryStatus())) {
            flag = false;
            ajaxResult = AjaxResult.error("查询状态不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaItemDocQuery.getOrderFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱类型标识不能为空");
         }

         if (flag) {
            this.tdPaItemDocQueryService.insertTdPaItemDocQuery(tdPaItemDocQuery);
            ajaxResult = AjaxResult.success((Object)tdPaItemDocQuery);
         }
      } catch (Exception e) {
         this.log.error("保存医师开立项目查询状态出现异常", e);
         ajaxResult = AjaxResult.error("保存医师开立项目查询状态出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:query:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaItemDocQuery tdPaItemDocQuery) {
      return this.toAjax(this.tdPaItemDocQueryService.updateTdPaItemDocQuery(tdPaItemDocQuery));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:query:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaItemDocQueryService.deleteTdPaItemDocQueryByIds(ids));
   }
}
