package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.vo.QcRuleConValueVo;
import com.emr.project.qc.service.IQcRuleConValueService;
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
@RequestMapping({"/qc/value"})
public class QcRuleConValueController extends BaseController {
   @Autowired
   private IQcRuleConValueService qcRuleConValueService;

   @PreAuthorize("@ss.hasPermi('qc:value:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleConValueVo qcRuleConValueVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleConValueVo> list = this.qcRuleConValueService.selectQcRuleConValueList(qcRuleConValueVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询值域规则列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询值域规则列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:value:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleConValueService.selectQcRuleConValueById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:value:add')")
   @Log(
      title = "基础质控值域规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleConValueVo qcRuleConValueVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleConValueVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据类型不能为空");
         }

         if (flag) {
            if (qcRuleConValueVo.getRuleType().equals("1")) {
               if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("值域字典类型不能为空");
               }

               if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("值域字典类型名称不能为空");
               }
            } else if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictCon())) {
               flag = false;
               ajaxResult = AjaxResult.error("值域内容不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleConValueService.insertQcRuleConValue(qcRuleConValueVo);
         }
      } catch (Exception e) {
         this.log.error("新增值域规则异常", e);
         ajaxResult = AjaxResult.error("新增值域规则异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:value:edit')")
   @Log(
      title = "基础质控值域规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleConValueVo qcRuleConValueVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConValueVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConValueVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据类型不能为空");
         }

         if (flag) {
            if (qcRuleConValueVo.getRuleType().equals("1")) {
               if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("值域字典类型不能为空");
               }

               if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("值域字典类型名称不能为空");
               }
            } else if (StringUtils.isEmpty(qcRuleConValueVo.getValuDictCon())) {
               flag = false;
               ajaxResult = AjaxResult.error("值域内容不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleConValueService.updateQcRuleConValue(qcRuleConValueVo);
         }
      } catch (Exception e) {
         this.log.error("修改值域规则出现异常", e);
         ajaxResult = AjaxResult.error("修改值域规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:value:remove')")
   @Log(
      title = "基础质控值域规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcRuleConValueService.deleteQcRuleConValueById(id);
      } catch (Exception e) {
         this.log.error("删除值域规则出现异常", e);
         ajaxResult = AjaxResult.error("删除值域规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:value:edit')")
   @Log(
      title = "基础质控值域规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleConValueVo qcRuleConValueVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConValueVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConValueVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConValueVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConValueService.updateValidFlag(qcRuleConValueVo);
         }
      } catch (Exception e) {
         this.log.error("值域规则启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("值域规则启用禁用出现异常");
      }

      return ajaxResult;
   }
}
