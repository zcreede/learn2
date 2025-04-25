package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcRuleIterEmr;
import com.emr.project.qc.domain.vo.QcRuleIterEmrVo;
import com.emr.project.qc.service.IQcRuleIterEmrService;
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
@RequestMapping({"/qc/ruleIter"})
public class QcRuleIterEmrController extends BaseController {
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleIterEmrVo qcRuleIterEmrVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleIterEmr> list = this.qcRuleIterEmrService.selectQcRuleIterEmrList(qcRuleIterEmrVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询可替代病历列表分页异常", e);
         tableDataInfo = new TableDataInfo(500, "查询可替代病历列表分页异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleIterEmrService.selectQcRuleIterEmrById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:add')")
   @Log(
      title = "可替代病历关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleIterEmr qcRuleIterEmr) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleIterEmr == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getIterTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("可替代病历编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getIterTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("可替代病历名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识不能为空");
         }

         if (flag) {
            this.qcRuleIterEmrService.insertQcRuleIterEmr(qcRuleIterEmr);
         }
      } catch (Exception e) {
         this.log.error("新增可替代病历出现异常", e);
         ajaxResult = AjaxResult.error("新增可替代病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:edit')")
   @Log(
      title = "可替代病历关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleIterEmr qcRuleIterEmr) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleIterEmr == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleIterEmr.getEmrTypeCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getIterTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("可替代病历编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getIterTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("可替代病历名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识不能为空");
         }

         if (flag) {
            this.qcRuleIterEmrService.updateQcRuleIterEmr(qcRuleIterEmr);
         }
      } catch (Exception e) {
         this.log.error("修改可替代病历出现异常", e);
         ajaxResult = AjaxResult.error("修改可替代病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:remove')")
   @Log(
      title = "可替代病历关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         if (id != null) {
            this.qcRuleIterEmrService.deleteQcRuleIterEmrById(id);
         } else {
            ajaxResult = AjaxResult.error("id不能为空");
         }
      } catch (Exception e) {
         this.log.error("删除可替代病历出现异常", e);
         ajaxResult = AjaxResult.error("删除可替代病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:ruleIter:edit')")
   @Log(
      title = "可替代病历关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleIterEmr qcRuleIterEmr) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleIterEmr == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleIterEmr.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleIterEmr.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcRuleIterEmrService.updateQcRuleIterEmr(qcRuleIterEmr);
         }
      } catch (Exception e) {
         this.log.error("可替代病历启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("可替代病历启用禁用出现异常");
      }

      return ajaxResult;
   }
}
