package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysCustomUnit;
import com.emr.project.system.service.ISysCustomUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

@Api(
   value = "SysCustomUnitController",
   tags = {"内部数据集字段信息管理"}
)
@RestController
@RequestMapping({"/system/unit"})
public class SysCustomUnitController extends BaseController {
   @Autowired
   private ISysCustomUnitService sysCustomUnitService;

   @ApiOperation("查询内部数据集字段列表-分页")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "setId",
   value = "数据集ID",
   dataType = "Long"
), @ApiImplicitParam(
   name = "pageNum",
   value = "当前记录起始索引",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "pageSize",
   value = "每页显示记录数",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "orderByColumn",
   value = "排序列",
   dataType = "String"
), @ApiImplicitParam(
   name = "isAsc",
   value = "isAsc",
   dataType = "String"
)})
   @PreAuthorize("@ss.hasPermi('system:unit:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysCustomUnit sysCustomUnit) {
      this.startPage();
      List<SysCustomUnit> list = this.sysCustomUnitService.selectSysCustomUnitList(sysCustomUnit);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('system:unit:queryList,system:elem:add')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(SysCustomUnit sysCustomUnit) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (sysCustomUnit.getSetId() == null) {
            ajaxResult = AjaxResult.error("数据集id参数不能为空");
         } else {
            List<SysCustomUnit> list = this.sysCustomUnitService.selectSysCustomUnitListBySetId(sysCustomUnit.getSetId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询内部数据集字段列表出现异常", e);
         ajaxResult = AjaxResult.error("查询内部数据集字段列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:unit:export')")
   @Log(
      title = "内部数据集字段",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysCustomUnit sysCustomUnit) {
      List<SysCustomUnit> list = this.sysCustomUnitService.selectSysCustomUnitList(sysCustomUnit);
      ExcelUtil<SysCustomUnit> util = new ExcelUtil(SysCustomUnit.class);
      return util.exportExcel(list, "内部数据集字段数据");
   }

   @PreAuthorize("@ss.hasPermi('system:unit:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysCustomUnitService.selectSysCustomUnitById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:unit:add')")
   @Log(
      title = "内部数据集字段",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysCustomUnit sysCustomUnit) {
      return this.toAjax(this.sysCustomUnitService.insertSysCustomUnit(sysCustomUnit));
   }

   @ApiOperation("修改内部数据集字段")
   @PreAuthorize("@ss.hasPermi('system:unit:edit')")
   @Log(
      title = "内部数据集字段",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysCustomUnit sysCustomUnit) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && sysCustomUnit == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && sysCustomUnit.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomUnit.getFieldName())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据元中文名不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomUnit.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag) {
            this.sysCustomUnitService.updateSysCustomUnit(sysCustomUnit);
         }
      } catch (Exception e) {
         this.log.error("修改内部数据集字段出现异常,", e);
         ajaxResult = AjaxResult.error("修改内部数据集字段出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:unit:remove')")
   @Log(
      title = "内部数据集字段",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysCustomUnitService.deleteSysCustomUnitByIds(ids));
   }

   @ApiOperation("修改内部数据集字段启用状态")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "id",
   value = "内部数据集字段Id",
   dataType = "Long",
   required = true
), @ApiImplicitParam(
   name = "validFlag",
   value = "启用状态 0=：未启用；1：启用",
   dataType = "String",
   required = true
)})
   @PreAuthorize("@ss.hasPermi('system:unit:changeValidFlag')")
   @PostMapping({"/changeValidFlag"})
   public AjaxResult changeValidFlag(Long id, String validFlag) {
      AjaxResult ajaxResult = AjaxResult.success("启用状态修改成功");
      boolean flag = true;

      try {
         if (flag && id == null) {
            ajaxResult = AjaxResult.error("内部数据集字段id不能为空");
            flag = false;
         }

         if (flag && StringUtils.isBlank(validFlag)) {
            ajaxResult = AjaxResult.error("内部数据集字段validFlag不能为空");
            flag = false;
         }

         if (flag) {
            SysCustomUnit sysCustomUnit = this.sysCustomUnitService.selectSysCustomUnitById(id);
            if (sysCustomUnit == null) {
               ajaxResult = AjaxResult.error("内部数据集字段validFlag不能为空");
               flag = false;
            }
         }

         if (flag) {
            this.sysCustomUnitService.changeValidFlag(id, validFlag);
         }
      } catch (Exception e) {
         this.log.error("修改内部数据集字段启用状态出现异常", e);
         ajaxResult = AjaxResult.error("修改内部数据集字段启用状态出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:unit:queryList,system:elem:add')")
   @GetMapping({"/updateCustomUnitList"})
   public AjaxResult updateCustomUnitList(SysCustomUnit sysCustomUnit) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (sysCustomUnit.getSetId() == null) {
            ajaxResult = AjaxResult.error("数据集id参数不能为空");
         } else {
            List<SysCustomUnit> list = this.sysCustomUnitService.updateCustomUnitList(sysCustomUnit.getSetId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据数据集更新数据集类型数据出现异常", e);
         ajaxResult = AjaxResult.error("根据数据集更新数据集类型数据出现异常");
      }

      return ajaxResult;
   }
}
