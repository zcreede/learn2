package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.WorkLoadItem;
import com.emr.project.system.domain.resp.DsPreserveOutResp;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.IWorkLoadItemService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@RequestMapping({"/system/workLoadItem"})
public class WorkLoadItemController extends BaseController {
   @Autowired
   private IWorkLoadItemService workLoadItemService;
   @Autowired
   private ISysDictDataService sysDictDataService;

   @PreAuthorize("@ss.hasPermi('system:item:list')")
   @GetMapping({"/list"})
   public AjaxResult list(WorkLoadItem workLoadItem) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<WorkLoadItem> list = this.workLoadItemService.selectWorkLoadItemList(workLoadItem);
         ajaxResult.put("data", list);
      } catch (Exception e) {
         this.log.error("查询住院工作量项目列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询住院工作量项目列表出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:item:export')")
   @Log(
      title = "住院工作量项目",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(WorkLoadItem workLoadItem) {
      List<WorkLoadItem> list = this.workLoadItemService.selectWorkLoadItemList(workLoadItem);
      ExcelUtil<WorkLoadItem> util = new ExcelUtil(WorkLoadItem.class);
      return util.exportExcel(list, "住院工作量项目数据");
   }

   @PreAuthorize("@ss.hasPermi('system:item:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.workLoadItemService.selectWorkLoadItemById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:item:add')")
   @Log(
      title = "住院工作量项目",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody WorkLoadItem workLoadItem) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isEmpty(workLoadItem.getItemName())) {
         ajaxResult = AjaxResult.error("项目名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeCode())) {
         ajaxResult = AjaxResult.error("类型编码不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeName())) {
         ajaxResult = AjaxResult.error("类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeName())) {
         ajaxResult = AjaxResult.error("类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getSourceFromType())) {
         ajaxResult = AjaxResult.error("数据来源类型不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getSourceFromName())) {
         ajaxResult = AjaxResult.error("数据来源类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getStatus())) {
         ajaxResult = AjaxResult.error("状态不能为空！");
         return ajaxResult;
      } else {
         try {
            this.workLoadItemService.insertWorkLoadItem(workLoadItem);
         } catch (Exception e) {
            this.log.error("新增住院工作量项目出现异常，", e);
            ajaxResult = AjaxResult.error("新增住院工作量项目出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('system:item:edit')")
   @Log(
      title = "住院工作量项目",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody WorkLoadItem workLoadItem) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (workLoadItem.getId() == null) {
         ajaxResult = AjaxResult.error("主键id不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemName())) {
         ajaxResult = AjaxResult.error("项目名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeCode())) {
         ajaxResult = AjaxResult.error("类型编码不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeName())) {
         ajaxResult = AjaxResult.error("类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getItemTypeName())) {
         ajaxResult = AjaxResult.error("类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getSourceFromType())) {
         ajaxResult = AjaxResult.error("数据来源类型不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getSourceFromName())) {
         ajaxResult = AjaxResult.error("数据来源类型名称不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(workLoadItem.getStatus())) {
         ajaxResult = AjaxResult.error("状态不能为空！");
         return ajaxResult;
      } else if (workLoadItem.getSortNo() == null) {
         ajaxResult = AjaxResult.error("排序不能为空！");
         return ajaxResult;
      } else {
         try {
            this.workLoadItemService.updateWorkLoadItem(workLoadItem);
         } catch (Exception e) {
            this.log.error("修改住院工作量项目出现异常，", e);
            ajaxResult = AjaxResult.error("修改住院工作量项目出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasPermi('system:item:remove')")
   @Log(
      title = "住院工作量项目",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      WorkLoadItem workLoadItem = this.workLoadItemService.selectWorkLoadItemById(id);
      if (workLoadItem == null) {
         ajaxResult = AjaxResult.error("住院工作量项目不存在，请刷新后再试！");
         return ajaxResult;
      } else {
         List<String> itemCode = Lists.newArrayList(new String[]{"XM00000001", "XM00000002", "XM00000003", "XM00000004", "XM00000005"});
         if (itemCode.contains(workLoadItem.getItemCode())) {
            ajaxResult = AjaxResult.error("内置项目不允许删除！");
            return ajaxResult;
         } else {
            return this.toAjax(this.workLoadItemService.deleteWorkLoadItemById(id));
         }
      }
   }

   @GetMapping({"/getInfo"})
   public AjaxResult getInfo() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<DsPreserveOutResp> list = this.workLoadItemService.selectDsPreserveOutList();
         ajaxResult.put("data", list);
         String[] dictStr = new String[]{"s150", "s151"};
         List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);
         if (!dictList.isEmpty()) {
            Map<String, List<SysDictData>> collect = (Map)dictList.stream().collect(Collectors.groupingBy(SysDictData::getDictType));

            for(String key : collect.keySet()) {
               ajaxResult.put(key, collect.get(key));
            }
         }
      } catch (Exception e) {
         this.log.error("查询所需数据列表出现异常：", e);
         ajaxResult = AjaxResult.error("查询所需数据列表出现异常,请联系管理员！");
      }

      return ajaxResult;
   }
}
