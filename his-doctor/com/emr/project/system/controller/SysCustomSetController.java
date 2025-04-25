package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysCustomSet;
import com.emr.project.system.service.ISysCustomSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

@Api(
   value = "SysCustomSetController",
   tags = {"内部数据集信息管理"}
)
@RestController
@RequestMapping({"/system/set"})
public class SysCustomSetController extends BaseController {
   @Autowired
   private ISysCustomSetService sysCustomSetService;

   @ApiOperation("查询内部数据集列表-分页")
   @PreAuthorize("@ss.hasPermi('system:set:list')")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "setName",
   value = "数据集名称",
   dataType = "String"
), @ApiImplicitParam(
   name = "typeName",
   value = "分类 1=:值域；0：数据集",
   dataType = "String"
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
   @ApiResponse(
      code = 200,
      message = "查询成功",
      response = SysCustomSet.class
   )
   @GetMapping({"/list"})
   public TableDataInfo list(SysCustomSet sysCustomSet) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysCustomSet> list = this.sysCustomSetService.selectSysCustomSetList(sysCustomSet);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询内部数据集分页出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询内部数据集分页出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:set:queryList,system:elem:add,mrhp:set:list,tmpl:index:add,tmpl:index:edit')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(SysCustomSet sysCustomSet) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isNotEmpty(sysCustomSet.getTypeName())) {
            List<SysCustomSet> list = this.sysCustomSetService.selectSysCustomSetByTypeName(sysCustomSet);
            ajaxResult = AjaxResult.success((Object)list);
         } else {
            ajaxResult = AjaxResult.error("分类参数不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询内部数据集列表出现异常", e);
         ajaxResult = AjaxResult.error("查询内部数据集列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:set:export')")
   @Log(
      title = "内部数据集",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysCustomSet sysCustomSet) {
      List<SysCustomSet> list = this.sysCustomSetService.selectSysCustomSetList(sysCustomSet);
      ExcelUtil<SysCustomSet> util = new ExcelUtil(SysCustomSet.class);
      return util.exportExcel(list, "内部数据集数据");
   }

   @ApiOperation("根据ID获取内部数据集详细信息")
   @PreAuthorize("@ss.hasPermi('system:set:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysCustomSetService.selectSysCustomSetById(id));
   }

   @ApiOperation("新增内部数据集")
   @PreAuthorize("@ss.hasPermi('system:set:add')")
   @Log(
      title = "内部数据集",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysCustomSet sysCustomSet) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && sysCustomSet == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据集名称不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getSetDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据集描述不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("状态不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("分类不能为空");
         }

         if (flag) {
            this.sysCustomSetService.insertSysCustomSet(sysCustomSet);
         }
      } catch (Exception e) {
         this.log.error("新增内部数据集出现异常，", e);
         ajaxResult = AjaxResult.error("新增内部数据集出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("修改内部数据集")
   @PreAuthorize("@ss.hasPermi('system:set:edit')")
   @Log(
      title = "内部数据集",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysCustomSet sysCustomSet) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && sysCustomSet == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getSetName())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据集名称不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getSetDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据集描述不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("状态不能为空");
         }

         if (flag && StringUtils.isBlank(sysCustomSet.getTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("分类不能为空");
         }

         if (flag) {
            this.sysCustomSetService.updateSysCustomSet(sysCustomSet);
         }
      } catch (Exception e) {
         this.log.error("修改内部数据集出现异常，", e);
         ajaxResult = AjaxResult.error("修改内部数据集出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("删除内部数据集")
   @PreAuthorize("@ss.hasPermi('system:set:remove')")
   @Log(
      title = "内部数据集",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.sysCustomSetService.deleteSysCustomSetById(id);
      } catch (Exception e) {
         this.log.error("删除内部数据集出现异常，", e);
         ajaxResult = AjaxResult.error("删除内部数据集出现异常");
      }

      return ajaxResult;
   }
}
