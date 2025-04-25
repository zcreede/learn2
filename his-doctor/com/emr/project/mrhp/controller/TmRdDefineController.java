package com.emr.project.mrhp.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.mrhp.domain.TmRdDefine;
import com.emr.project.mrhp.service.ITmRdDefineService;
import io.swagger.annotations.Api;
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
@RequestMapping({"/escalation/define"})
@Api(
   tags = {"MRIS0072-数据上报-数据上报定义"}
)
public class TmRdDefineController extends BaseController {
   @Autowired
   private ITmRdDefineService tmRdDefineService;

   @PreAuthorize("@ss.hasPermi('escalation:define:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询上报数据定义列表",
      notes = "权限制值为：escalation:define:list"
   )
   public AjaxResult list(String searchValue) {
      try {
         TmRdDefine tmRdDefine = new TmRdDefine();
         tmRdDefine.setSearchValue(searchValue);
         List<TmRdDefine> list = this.tmRdDefineService.selectTmRdDefineList(tmRdDefine);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询上报数据定义列表出现异常，", e);
         return AjaxResult.error("查询上报数据定义列表出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:define:query')")
   @GetMapping({"/{id}"})
   @ApiOperation(
      value = "获取上报数据定义详细信息",
      notes = "权限制值为：escalation:define:query"
   )
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      if (id == null) {
         return AjaxResult.error("主键id不能为空！");
      } else {
         try {
            TmRdDefine tmRdDefine = this.tmRdDefineService.selectTmRdDefineById(id);
            return AjaxResult.success((Object)tmRdDefine);
         } catch (Exception e) {
            this.log.error("获取上报数据定义详细信息出现异常！", e);
            return AjaxResult.error("获取上报数据定义详细信息出现异常,请联系管理员！");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:define:add')")
   @PostMapping
   @ApiOperation(
      value = "新增上报数据定义",
      notes = "权限制值为：escalation:define:add"
   )
   public AjaxResult add(@RequestBody TmRdDefine tmRdDefine) {
      if (StringUtils.isEmpty(tmRdDefine.getDefineName())) {
         return AjaxResult.error("定义名称不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getDefineDesc())) {
         return AjaxResult.error("定义描述不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getWardCode())) {
         return AjaxResult.error("使用院区编码不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getWardName())) {
         return AjaxResult.error("使用院区不能为空！");
      } else {
         try {
            this.tmRdDefineService.insertTmRdDefine(tmRdDefine);
            return AjaxResult.success((Object)Boolean.TRUE);
         } catch (Exception e) {
            this.log.error("新增上报数据定义出现异常，", e);
            return AjaxResult.error("新增上报数据定义出现异常，请联系管理员！");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:define:edit')")
   @PutMapping
   @ApiOperation(
      value = "修改上报数据定义",
      notes = "权限制值为：escalation:define:edit"
   )
   public AjaxResult edit(@RequestBody TmRdDefine tmRdDefine) {
      if (tmRdDefine.getId() == null) {
         return AjaxResult.error("定义主键不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getDefineName())) {
         return AjaxResult.error("定义名称不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getDefineDesc())) {
         return AjaxResult.error("定义描述不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getWardCode())) {
         return AjaxResult.error("使用院区编码不能为空！");
      } else if (StringUtils.isEmpty(tmRdDefine.getWardName())) {
         return AjaxResult.error("使用院区不能为空！");
      } else {
         try {
            this.tmRdDefineService.updateTmRdDefine(tmRdDefine);
            return AjaxResult.success((Object)Boolean.TRUE);
         } catch (Exception e) {
            this.log.error("修改上报数据定义出现异常，", e);
            return AjaxResult.error("修改上报数据定义出现异常，请联系管理员");
         }
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:define:uselist')")
   @GetMapping({"/useList"})
   @ApiOperation(
      value = "查询启用状态下的上报数据定义列表",
      notes = "权限制值为：escalation:define:useList"
   )
   public AjaxResult useList() {
      try {
         TmRdDefine tmRdDefine = new TmRdDefine();
         tmRdDefine.setState("0");
         List<TmRdDefine> list = this.tmRdDefineService.selectTmRdDefineList(tmRdDefine);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询上报数据定义列表出现异常，", e);
         return AjaxResult.error("查询上报数据定义列表出现异常，请联系管理员！");
      }
   }

   @PreAuthorize("@ss.hasPermi('escalation:define:hoslist')")
   @GetMapping({"/hosList"})
   @ApiOperation(
      value = "根据医院查询上报数据定义列表",
      notes = "权限制值为：escalation:define:hosList"
   )
   public AjaxResult hosList(String orgCode) {
      AjaxResult res = AjaxResult.success();
      if (StringUtils.isEmpty(orgCode)) {
         return AjaxResult.error("医院编码不能为空！");
      } else {
         try {
            TmRdDefine tmRdDefine = new TmRdDefine();
            tmRdDefine.setState("0");
            tmRdDefine.setWardCode(orgCode);
            List<TmRdDefine> list = this.tmRdDefineService.selectTmRdDefineList(tmRdDefine);
            return AjaxResult.success((Object)list);
         } catch (Exception e) {
            this.log.error("查询上报数据定义列表出现异常，", e);
            return AjaxResult.error("查询上报数据定义列表出现异常，请联系管理员！");
         }
      }
   }
}
