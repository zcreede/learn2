package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcRules;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.service.IQcRulesService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qc/rules"})
public class QcRulesController extends BaseController {
   @Autowired
   private IQcRulesService qcRulesService;

   @PreAuthorize("@ss.hasPermi('qc:rules:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRules qcRules) {
      this.startPage();
      List<QcRules> list = this.qcRulesService.selectQcRulesList(qcRules);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rules:list,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/listTop20"})
   public AjaxResult listTop20(QcRules qcRules) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<QcRules> list = this.qcRulesService.selectQcRulesListTop20(qcRules);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception var4) {
         ajaxResult = AjaxResult.error("查询质控规则列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rules:export')")
   @Log(
      title = "质控规则",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcRules qcRules) {
      List<QcRules> list = this.qcRulesService.selectQcRulesList(qcRules);
      ExcelUtil<QcRules> util = new ExcelUtil(QcRules.class);
      return util.exportExcel(list, "质控规则数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:rules:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRulesService.selectQcRulesById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/selectScoreRules"})
   public AjaxResult s(QcRulesVo qcRulesVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (qcRulesVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRulesVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRulesVo.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag) {
            List<QcRulesVo> qcRulesVos = this.qcRulesService.selectScoreRulesList(qcRulesVo);
            ajaxResult = AjaxResult.success((Object)qcRulesVos);
         }
      } catch (Exception e) {
         this.log.error("查询人工质控规则下拉出现异常", e);
         ajaxResult = AjaxResult.error("查询人工质控规则下拉出现异常");
      }

      return ajaxResult;
   }
}
