package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcRuleConLogic;
import com.emr.project.qc.domain.vo.QcRuleConLogicVo;
import com.emr.project.qc.service.IQcRuleConLogicService;
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
@RequestMapping({"/qc/logic"})
public class QcRuleConLogicController extends BaseController {
   @Autowired
   private IQcRuleConLogicService qcRuleConLogicService;

   @PreAuthorize("@ss.hasPermi('qc:logic:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleConLogic qcRuleConLogic) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleConLogicVo> list = this.qcRuleConLogicService.selectQcRuleConLogicList(qcRuleConLogic);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询逻辑一致性逻辑关系出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询逻辑一致性逻辑关系出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:logic:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleConLogicService.selectQcRuleConLogicById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:logic:add')")
   @Log(
      title = "基础质控逻辑一致性逻辑关系 ",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleConLogic qcRuleConLogic) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleConLogic == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres1())) {
            flag = false;
            ajaxResult = AjaxResult.error("校验表达式不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres1Desc())) {
            flag = false;
            ajaxResult = AjaxResult.error("校验表达式描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres2())) {
            flag = false;
            ajaxResult = AjaxResult.error("关联表达式不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres2Desc())) {
            flag = false;
            ajaxResult = AjaxResult.error("关联表达式描述不能为空");
         }

         if (flag) {
            this.qcRuleConLogicService.insertQcRuleConLogic(qcRuleConLogic);
         }
      } catch (Exception e) {
         this.log.error("新增逻辑一致性逻辑关系出现异常", e);
         ajaxResult = AjaxResult.error("新增逻辑一致性逻辑关系出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:logic:edit')")
   @Log(
      title = "基础质控逻辑一致性逻辑关系 ",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleConLogic qcRuleConLogic) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConLogic == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConLogic.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres1())) {
            flag = false;
            ajaxResult = AjaxResult.error("校验表达式不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres1Desc())) {
            flag = false;
            ajaxResult = AjaxResult.error("校验表达式描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres2())) {
            flag = false;
            ajaxResult = AjaxResult.error("关联表达式不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getExpres2Desc())) {
            flag = false;
            ajaxResult = AjaxResult.error("关联表达式描述不能为空");
         }

         if (flag) {
            this.qcRuleConLogicService.updateQcRuleConLogic(qcRuleConLogic);
         }
      } catch (Exception e) {
         this.log.error("修改逻辑一致性逻辑关系出现异常", e);
         ajaxResult = AjaxResult.error("修改逻辑一致性逻辑关系出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:logic:remove')")
   @Log(
      title = "基础质控逻辑一致性逻辑关系 ",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcRuleConLogicService.deleteQcRuleConLogicById(id);
      } catch (Exception e) {
         this.log.error("删除逻辑一致性逻辑关系异常", e);
         ajaxResult = AjaxResult.error("删除逻辑一致性逻辑关系异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:logic:remove')")
   @Log(
      title = "基础质控逻辑一致性逻辑关系 ",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleConLogic qcRuleConLogic) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConLogic == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConLogic.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConLogic.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConLogicService.updateEditFlag(qcRuleConLogic);
         }
      } catch (Exception e) {
         this.log.error("逻辑一致性逻辑关系启用禁用异常", e);
         ajaxResult = AjaxResult.error("启用禁用异常");
      }

      return ajaxResult;
   }
}
