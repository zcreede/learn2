package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.vo.QcRuleConRangeVo;
import com.emr.project.qc.service.IQcRuleConRangeService;
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
@RequestMapping({"/qc/range"})
public class QcRuleConRangeController extends BaseController {
   @Autowired
   private IQcRuleConRangeService qcRuleConRangeService;

   @PreAuthorize("@ss.hasPermi('qc:range:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleConRangeVo qcRuleConRangeVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleConRangeVo> list = this.qcRuleConRangeService.selectQcRuleConRangeList(qcRuleConRangeVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询数值范围规则列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询数值范围规则列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:range:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleConRangeService.selectQcRuleConRangeById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:range:add')")
   @Log(
      title = "基础质控数值范围规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleConRangeVo qcRuleConRangeVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleConRangeVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleConRangeService.insertQcRuleConRange(qcRuleConRangeVo);
         }
      } catch (Exception e) {
         this.log.error("新增数值范围规则异常", e);
         ajaxResult = AjaxResult.error("新增数值范围规则异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:range:edit')")
   @Log(
      title = "基础质控数值范围规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleConRangeVo qcRuleConRangeVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConRangeVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConRangeVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleConRangeService.updateQcRuleConRange(qcRuleConRangeVo);
         }
      } catch (Exception e) {
         this.log.error("修改数值范围规则出现异常", e);
         ajaxResult = AjaxResult.error("修改数值范围规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:range:remove')")
   @Log(
      title = "基础质控数值范围规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcRuleConRangeService.deleteQcRuleConRangeById(id);
      } catch (Exception e) {
         this.log.error("删除数值范围规则出现异常", e);
         ajaxResult = AjaxResult.error("删除数值范围规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:range:edit')")
   @Log(
      title = "基础质控数值范围规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleConRangeVo qcRuleConRangeVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConRangeVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConRangeVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRangeVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConRangeService.updateValidFlag(qcRuleConRangeVo);
         }
      } catch (Exception e) {
         this.log.error("数值范围规则启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("数值范围规则启用禁用出现异常");
      }

      return ajaxResult;
   }
}
