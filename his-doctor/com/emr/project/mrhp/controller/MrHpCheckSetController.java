package com.emr.project.mrhp.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.vo.MrHpCheckSetVo;
import com.emr.project.mrhp.service.IMrHpCheckSetService;
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
@RequestMapping({"/mrhp/set"})
public class MrHpCheckSetController extends BaseController {
   @Autowired
   private IMrHpCheckSetService mrHpCheckSetService;

   @PreAuthorize("@ss.hasPermi('mrhp:set:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpCheckSetVo mrHpCheckSetVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<MrHpCheckSetVo> list = this.mrHpCheckSetService.selectMrHpCheckSetVoList(mrHpCheckSetVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病案首页质控规则配置列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病案首页质控规则配置列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:add')")
   @Log(
      title = "病案首页质控规则配置",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpCheckSetVo mrHpCheckSetVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (mrHpCheckSetVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag) {
            MrHpCheckSet mrHpCheckSet = this.mrHpCheckSetService.selectMrHpCheckSetByCheckName(mrHpCheckSetVo.getCheckName(), (Long)null);
            if (mrHpCheckSet != null) {
               flag = false;
               ajaxResult = AjaxResult.error("规则名称已存在");
            }
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckMrhpClassNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("信息分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckMrhpClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("信息分类名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckClassNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则分类名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTypeNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckActMrType())) {
            flag = false;
            ajaxResult = AjaxResult.error("作用域不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckEventNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("触发环节不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重级别不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckEnable())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTips())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则提示信息不能为空");
         }

         if (flag) {
            this.mrHpCheckSetService.insertMrHpCheckSet(mrHpCheckSetVo);
         }
      } catch (Exception e) {
         this.log.error("新增病案首页质控规则出现异常", e);
         ajaxResult = AjaxResult.error("新增病案首页质控规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:edit')")
   @Log(
      title = "病案首页质控规则配置",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpCheckSetVo mrHpCheckSetVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (mrHpCheckSetVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && mrHpCheckSetVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag) {
            MrHpCheckSet mrHpCheckSet = this.mrHpCheckSetService.selectMrHpCheckSetByCheckName(mrHpCheckSetVo.getCheckName(), mrHpCheckSetVo.getId());
            if (mrHpCheckSet != null) {
               flag = false;
               ajaxResult = AjaxResult.error("规则名称已存在");
            }
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckMrhpClassNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("信息分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckMrhpClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("信息分类名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckClassNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则分类名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTypeNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控规则类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckActMrType())) {
            flag = false;
            ajaxResult = AjaxResult.error("作用域不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckEventNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("触发环节不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重级别不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckEnable())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTips())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则提示信息不能为空");
         }

         if (flag) {
            this.mrHpCheckSetService.updateMrHpCheckSet(mrHpCheckSetVo);
         }
      } catch (Exception e) {
         this.log.error("修改病案首页质控规则出现异常", e);
         ajaxResult = AjaxResult.error("修改病案首页质控规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:remove')")
   @Log(
      title = "病案首页质控规则配置",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.mrHpCheckSetService.deleteMrHpCheckSetByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:set:tableField,mrhp:set:list')")
   @GetMapping({"/tableField"})
   public TableDataInfo tableField(MrHpCheckSetVo mrHpCheckSetVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (mrHpCheckSetVo == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && mrHpCheckSetVo.getSetId() == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "表名id不能为空");
         }

         if (flag) {
            this.startPage();
            tableDataInfo = this.getDataTable(this.mrHpCheckSetService.selectTableField(mrHpCheckSetVo));
         }
      } catch (Exception e) {
         this.log.error("查询检验表字段信息出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询检验表字段信息出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:list')")
   @GetMapping({"/queryInfo/{id}"})
   public AjaxResult queryInfo(@PathVariable String id) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         MrHpCheckSetVo mrHpCheckSetVo = this.mrHpCheckSetService.selectMrHpCheckSetById(id);
         ajaxResult = AjaxResult.success((Object)mrHpCheckSetVo);
      } catch (Exception e) {
         this.log.error("查询单个规则具体信息出现异常", e);
         ajaxResult = AjaxResult.error("查询单个规则具体信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:add')")
   @PostMapping({"/createJson"})
   public AjaxResult createJson(@RequestBody MrHpCheckSetVo mrHpCheckSetVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (mrHpCheckSetVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getCheckTypeNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSetVo.getTableName())) {
            flag = false;
            ajaxResult = AjaxResult.error("表名不能为空");
         }

         if (flag && !mrHpCheckSetVo.getCheckTypeNo().equals("0303")) {
            if (mrHpCheckSetVo.getCheckExpression1List() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("表达式集合不能为空");
            }
         } else if (flag && mrHpCheckSetVo.getCheckFeeVoList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("费用表达式集合不能为空");
         }

         if (flag) {
            String json = this.mrHpCheckSetService.createExpressionJson(mrHpCheckSetVo);
            ajaxResult.put("data", json);
         }
      } catch (Exception e) {
         this.log.error("生成校验表达式json出现异常", e);
         ajaxResult = AjaxResult.error("生成校验表达式json出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:set:edit')")
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody MrHpCheckSet mrHpCheckSet) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (mrHpCheckSet == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && mrHpCheckSet.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(mrHpCheckSet.getCheckEnable())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.mrHpCheckSetService.updateEditFlag(mrHpCheckSet);
         }
      } catch (Exception e) {
         this.log.error("修改启用状态出现异常", e);
         ajaxResult = AjaxResult.error("修改启用状态出现异常");
      }

      return ajaxResult;
   }
}
