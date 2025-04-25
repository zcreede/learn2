package com.emr.project.mrhp.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.TmRsDictTypt;
import com.emr.project.mrhp.service.ITmRsDictTyptService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/escalation/type"})
public class TmRsDictTyptController extends BaseController {
   @Autowired
   private ITmRsDictTyptService tmRsDictTyptService;

   @PreAuthorize("@ss.hasPermi('escalation:hsType:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询上报数据标准字典类型列表",
      notes = "权限制值为：escalation:type:list"
   )
   public TableDataInfo list(TmRsDictTypt req) {
      new TableDataInfo();
      if (req.getDefineId() == null) {
         TableDataInfo var5 = new TableDataInfo(500, "上报数据定义id不能为空！");
         return var5;
      } else {
         TableDataInfo tableDataInfo;
         try {
            this.startPage();
            List<TmRsDictTypt> list = this.tmRsDictTyptService.selectTmRsDictTyptList(req);
            tableDataInfo = this.getDataTable(list);
         } catch (Exception e) {
            this.log.error("查询病案首页模板分页列表出现异常", e);
            tableDataInfo = new TableDataInfo(500, "查询病案首页模板列表出现异常");
         }

         return tableDataInfo;
      }
   }

   @ApiOperation(
      value = "获取单个上报数据标准字典类型详细信息",
      notes = "权限制值为：escalation:type:query"
   )
   @PreAuthorize("@ss.hasPermi('escalation:hsType:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      if (id == null) {
         return AjaxResult.error("主键id不能为空！");
      } else {
         try {
            TmRsDictTypt tmRsDictTypt = this.tmRsDictTyptService.selectTmRsDictTyptById(id);
            return AjaxResult.success((Object)tmRsDictTypt);
         } catch (Exception e) {
            this.log.error("获取单个上报数据标准字典类型详细信息出现异常,", e);
            return AjaxResult.error("获取单个上报数据标准字典类型详细信息出现异常,请联系管理员！");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:hsType:add')")
   @Log(
      title = "上报数据标准字典类型",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   @ApiOperation(
      value = "新增上报数据标准字典类型",
      notes = "权限制值为：escalation:type:add"
   )
   public AjaxResult add(@RequestBody TmRsDictTypt tmRsDictTypt) {
      if (tmRsDictTypt.getDefineId() == null) {
         return AjaxResult.error("上报id不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getDictTypeName())) {
         return AjaxResult.error("字典类型名称不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getDictTypeCode())) {
         return AjaxResult.error("字典类型编码不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getState())) {
         return AjaxResult.error("字典类型状态不能为空！");
      } else {
         List<TmRsDictTypt> dictTypes = this.tmRsDictTyptService.selectDictTypeByTypeCode(tmRsDictTypt.getDictTypeCode());
         if (!dictTypes.isEmpty()) {
            return AjaxResult.error("字典类型编码已存在！");
         } else {
            try {
               this.tmRsDictTyptService.insertTmRsDictTypt(tmRsDictTypt);
               return AjaxResult.success((Object)Boolean.TRUE);
            } catch (Exception e) {
               this.log.error("新增上报数据标准字典类型出现异常，", e);
               return AjaxResult.error("新增上报数据标准字典类型出现异常，请联系管理员！");
            }
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:hsType:edit')")
   @Log(
      title = "上报数据标准字典类型",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   @ApiOperation(
      value = "修改上报数据标准字典类型",
      notes = "权限制值为：escalation:type:edit"
   )
   public AjaxResult edit(@RequestBody TmRsDictTypt tmRsDictTypt) {
      if (tmRsDictTypt.getId() == null) {
         return AjaxResult.error("id不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getDictTypeName())) {
         return AjaxResult.error("字典类型名称不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getDictTypeCode())) {
         return AjaxResult.error("字典类型编码不能为空！");
      } else if (StringUtils.isEmpty(tmRsDictTypt.getState())) {
         return AjaxResult.error("字典类型状态不能为空！");
      } else {
         try {
            this.tmRsDictTyptService.updateTmRsDictTypt(tmRsDictTypt);
            return AjaxResult.success((Object)Boolean.TRUE);
         } catch (Exception e) {
            this.log.error("修改上报数据标准字典类型出现异常", e);
            return AjaxResult.error("修改上报数据标准字典类型出现异常,请联系管理员！");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:hsType:list')")
   @GetMapping({"/allList"})
   @ApiOperation(
      value = "查询上报数据标准字典类型全量列表（不分页）",
      notes = "权限制值为：escalation:hsType:list"
   )
   public AjaxResult allList(TmRsDictTypt req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (req.getDefineId() == null) {
         return AjaxResult.error("上报数据定义id不能为空！");
      } else {
         try {
            List<TmRsDictTypt> list = this.tmRsDictTyptService.selectTmRsDictTyptList(req);
            ajaxResult = AjaxResult.success((Object)list);
            return ajaxResult;
         } catch (Exception e) {
            this.log.error("查询病案首页模板不分页列表出现异常", e);
            return AjaxResult.error("查询病案首页模板列表出现异常,请联系管理员！");
         }
      }
   }
}
