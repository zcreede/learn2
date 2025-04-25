package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.vo.QcRuleConContVo;
import com.emr.project.qc.service.IQcRuleConContService;
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
@RequestMapping({"/qc/cont"})
public class QcRuleConContController extends BaseController {
   @Autowired
   private IQcRuleConContService qcRuleConContService;

   @PreAuthorize("@ss.hasPermi('qc:cont:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleConContVo qcRuleConContVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleConContVo> list = this.qcRuleConContService.selectQcRuleConContList(qcRuleConContVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询基础质控内容矛盾规则列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询基础质控内容矛盾规则列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:cont:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleConContService.selectQcRuleConContById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:cont:add')")
   @Log(
      title = "基础质控内容矛盾规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleConContVo qcRuleConContVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleConContVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && qcRuleConContVo.getElemFlag().equals("2") && (qcRuleConContVo.getElemVoList() == null || qcRuleConContVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getKeyType())) {
            flag = false;
            ajaxResult = AjaxResult.error("关键词类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConContService.insertQcRuleConCont(qcRuleConContVo);
         }
      } catch (Exception e) {
         this.log.error("新增基础质控内容出现异常", e);
         ajaxResult = AjaxResult.error("新增基础质控内容出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:cont:edit')")
   @Log(
      title = "基础质控内容矛盾规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleConContVo qcRuleConContVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConContVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConContVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleType())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && qcRuleConContVo.getElemFlag().equals("2") && (qcRuleConContVo.getElemVoList() == null || qcRuleConContVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getKeyType())) {
            flag = false;
            ajaxResult = AjaxResult.error("关键词类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleConContService.updateQcRuleConCont(qcRuleConContVo);
         }
      } catch (Exception e) {
         this.log.error("修改基础指控内容出现异常", e);
         ajaxResult = AjaxResult.error("修改基础指控内容出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:cont:remove')")
   @Log(
      title = "基础质控内容矛盾规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcRuleConContService.deleteQcRuleConContById(id);
      } catch (Exception e) {
         this.log.error("删除基础质控内容矛盾规则异常", e);
         ajaxResult = AjaxResult.error("删除基础质控内容矛盾规则异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:cont:edit')")
   @Log(
      title = "基础质控内容矛盾规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleConContVo qcRuleConContVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleConContVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleConContVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleConContVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识不能为空");
         }

         if (flag) {
            this.qcRuleConContService.updateEditFlag(qcRuleConContVo);
         }
      } catch (Exception e) {
         this.log.error("基础质控内容矛盾规格启用禁用异常", e);
         ajaxResult = AjaxResult.error("基础质控内容矛盾规格启用禁用异常");
      }

      return ajaxResult;
   }
}
