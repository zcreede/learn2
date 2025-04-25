package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.service.IQcScoreDedRuleService;
import java.util.ArrayList;
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
@RequestMapping({"/qc/rule"})
public class QcScoreDedRuleController extends BaseController {
   @Autowired
   private IQcScoreDedRuleService qcScoreDedRuleService;

   @PreAuthorize("@ss.hasAnyPermi('qc:rule:list,qc:detail:list,qc:detail:add,qc:detail:edit')")
   @GetMapping({"/list"})
   public AjaxResult list(QcScoreDedRule qcScoreDedRule) {
      List<QcRulesVo> list = new ArrayList(1);

      try {
         list = this.qcScoreDedRuleService.selectDetailQcRules(qcScoreDedRule.getDedId());
      } catch (Exception e) {
         this.log.error("查询评分细则与质控规则对应关系列表出现异常", e);
      }

      return AjaxResult.success((Object)list);
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rule:ruleList,qc:detail:add,qc:detail:edit')")
   @GetMapping({"/ruleList"})
   public TableDataInfo ruleList(QcRulesVo qcRulesVo) {
      this.startPage();
      List<QcRulesVo> list = new ArrayList(1);

      try {
         list = this.qcScoreDedRuleService.selectQcRules(qcRulesVo);
      } catch (Exception e) {
         this.log.error("查询未绑定规则列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:rule:export')")
   @Log(
      title = "评分细则与质控规则对应关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcScoreDedRule qcScoreDedRule) {
      List<QcScoreDedRule> list = this.qcScoreDedRuleService.selectQcScoreDedRuleList(qcScoreDedRule);
      ExcelUtil<QcScoreDedRule> util = new ExcelUtil(QcScoreDedRule.class);
      return util.exportExcel(list, "评分细则与质控规则对应关系数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:rule:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcScoreDedRuleService.selectQcScoreDedRuleById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:rule:add')")
   @Log(
      title = "评分细则与质控规则对应关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreDedRule qcScoreDedRule) {
      return this.toAjax(this.qcScoreDedRuleService.insertQcScoreDedRule(qcScoreDedRule));
   }

   @PreAuthorize("@ss.hasPermi('qc:rule:edit')")
   @Log(
      title = "评分细则与质控规则对应关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreDedRule qcScoreDedRule) {
      return this.toAjax(this.qcScoreDedRuleService.updateQcScoreDedRule(qcScoreDedRule));
   }

   @PreAuthorize("@ss.hasPermi('qc:rule:remove')")
   @Log(
      title = "评分细则与质控规则对应关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcScoreDedRuleService.deleteQcScoreDedRuleByIds(ids));
   }
}
