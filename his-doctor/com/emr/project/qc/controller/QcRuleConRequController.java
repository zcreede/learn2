package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.vo.QcRuleConRequVo;
import com.emr.project.qc.service.IQcRuleConRequService;
import com.emr.project.qc.service.IQcRuleUtilService;
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
@RequestMapping({"/qc/requ"})
public class QcRuleConRequController extends BaseController {
   @Autowired
   private IQcRuleConRequService qcRuleConRequService;
   @Autowired
   private IQcRuleUtilService qcRuleUtilService;

   @PreAuthorize("@ss.hasPermi('qc:requ:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleConRequVo qcRuleConRequVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleConRequVo> list = this.qcRuleConRequService.selectQcRuleConRequList(qcRuleConRequVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询基础质控必填项规则列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询基础质控必填项规则列表异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:requ:list')")
   @GetMapping({"/getExpressionJson"})
   public AjaxResult getExpressionJson(QcRuleConRequVo qcRuleConRequVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:requ:add')")
   @Log(
      title = "基础质控必填项规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleConRequVo qcRuleConRequVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleConRequVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && qcRuleConRequVo.getElemFlag().equals("2") && (qcRuleConRequVo.getElemVoList() == null || qcRuleConRequVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag) {
            this.qcRuleConRequService.insertQcRuleConRequ(qcRuleConRequVo);
         }
      } catch (Exception e) {
         this.log.error("新增必填项规则出现异常", e);
         ajaxResult = AjaxResult.error("新增必填项规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:requ:edit')")
   @Log(
      title = "基础质控必填项规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleConRequVo qcRuleConRequVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConRequVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && qcRuleConRequVo.getElemFlag().equals("2") && (qcRuleConRequVo.getElemVoList() == null || qcRuleConRequVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag) {
            this.qcRuleConRequService.updateQcRuleConRequ(qcRuleConRequVo);
         }
      } catch (Exception e) {
         this.log.error("修改必填项规则出现异常", e);
         ajaxResult = AjaxResult.error("修改必填项规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:requ:remove')")
   @Log(
      title = "基础质控必填项规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcRuleConRequService.deleteQcRuleConRequById(id);
      } catch (Exception e) {
         this.log.error("删除必填项规则出现异常", e);
         ajaxResult = AjaxResult.error("删除必填项规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:requ:edit')")
   @Log(
      title = "基础质控必填项规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleConRequVo qcRuleConRequVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConRequVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConRequVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConRequVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConRequService.updateValidFlag(qcRuleConRequVo);
         }
      } catch (Exception e) {
         this.log.error("必填项启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("必填项启用禁用出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:requ:list')")
   @PostMapping({"/getExpressJson"})
   public AjaxResult getExpressJson(@RequestBody QcRuleConRequVo qcRuleConRequVo) {
      AjaxResult ajaxResult = AjaxResult.success("获取成功");
      boolean flag = true;

      try {
         if (qcRuleConRequVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConRequVo.getElemExpressionVoList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("表达式集合不能为空");
         }

         if (flag) {
            String json = this.qcRuleUtilService.getExpressionJsonString(qcRuleConRequVo.getElemExpressionVoList());
            ajaxResult.put("data", json);
         }
      } catch (Exception e) {
         this.log.error("获取json出现异常", e);
         ajaxResult = AjaxResult.error("获取json出现异常");
      }

      return ajaxResult;
   }
}
