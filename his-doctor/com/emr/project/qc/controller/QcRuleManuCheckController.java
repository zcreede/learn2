package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.qc.domain.vo.QcRuleManuCheckVo;
import com.emr.project.qc.service.IQcRuleManuCheckService;
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
@RequestMapping({"/qc/check"})
public class QcRuleManuCheckController extends BaseController {
   @Autowired
   private IQcRuleManuCheckService qcRuleManuCheckService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;

   @PreAuthorize("@ss.hasPermi('qc:check:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcRuleManuCheckVo qcRuleManuCheckVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcRuleManuCheckVo> list = this.qcRuleManuCheckService.selectQcRuleManuCheckList(qcRuleManuCheckVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询人工抽查列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询人工抽查列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:check:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcRuleManuCheckService.selectQcRuleManuCheckById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:check:add')")
   @Log(
      title = "人工抽查质控规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcRuleManuCheckVo qcRuleManuCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcRuleManuCheckVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型名称不能为空");
         }

         if (flag && qcRuleManuCheckVo.getRuleTypeCode().equals("1") && qcRuleManuCheckVo.getElemFlag().equals("2") && (qcRuleManuCheckVo.getElemVoList() == null || qcRuleManuCheckVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleManuCheckService.insertQcRuleManuCheck(qcRuleManuCheckVo);
         }
      } catch (Exception e) {
         this.log.error("新增人工抽查质控规则出现异常", e);
         ajaxResult = AjaxResult.error("新增人工抽查质控规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:check:edit')")
   @Log(
      title = "人工抽查质控规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcRuleManuCheckVo qcRuleManuCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleManuCheckVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleManuCheckVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("规则id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getEmrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型名称不能为空");
         }

         if (flag && qcRuleManuCheckVo.getRuleTypeCode().equals("1") && qcRuleManuCheckVo.getElemFlag().equals("2") && (qcRuleManuCheckVo.getElemVoList() == null || qcRuleManuCheckVo.getElemVoList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("严重程度不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则描述不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标志不能为空");
         }

         if (flag) {
            this.qcRuleManuCheckService.updateQcRuleManuCheck(qcRuleManuCheckVo);
         }
      } catch (Exception e) {
         this.log.error("修改人工抽查质控规则出现异常", e);
         ajaxResult = AjaxResult.error("修改人工抽查质控规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:check:remove')")
   @Log(
      title = "人工抽查质控规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         if (id != null) {
            this.qcRuleManuCheckService.deleteQcRuleManuCheck(id);
         } else {
            ajaxResult = AjaxResult.error("id不能为空");
         }
      } catch (Exception e) {
         this.log.error("删除人工抽查质控规则异常", e);
         ajaxResult = AjaxResult.error("删除人工抽查质控规则异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:check:edit')")
   @Log(
      title = "人工抽查质控规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcRuleManuCheckVo qcRuleManuCheck) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcRuleManuCheck == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcRuleManuCheck.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheck.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识不能为空");
         }

         if (flag) {
            this.qcRuleManuCheckService.updateQcRuleManuCheck(qcRuleManuCheck);
         }
      } catch (Exception e) {
         this.log.error("启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("启用禁用出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:check:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/ruleTypeList"})
   public AjaxResult ruleTypeList(QcRuleManuCheckVo qcRuleManuCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcRuleManuCheckVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型编码不能为空");
         }

         if (flag && qcRuleManuCheckVo.getRuleTypeCode().equals("1")) {
            if (qcRuleManuCheckVo.getElemId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("元素id不能为空");
            }

            if (flag && qcRuleManuCheckVo.getMrFileId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历文件id不能为空");
            }

            Index index = null;
            SubfileIndex subfileIndex = null;
            if (flag) {
               index = this.indexService.selectIndexById(qcRuleManuCheckVo.getMrFileId());
               if (index == null) {
                  subfileIndex = this.subfileIndexService.selectSubfileIndexById(qcRuleManuCheckVo.getMrFileId());
               }

               if (index == null && subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("未找到当前病历文件");
               } else {
                  String emrTypeCode = subfileIndex == null ? index.getMrType() : subfileIndex.getMrType();
                  qcRuleManuCheckVo.setEmrTypeCode(emrTypeCode);
               }
            }
         }

         if (flag) {
            List<QcRuleManuCheckVo> list = this.qcRuleManuCheckService.selectQcRuleTypeList(qcRuleManuCheckVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询人工抽查质控下拉出现异常", e);
         ajaxResult = AjaxResult.error("查询人工抽查质控下拉出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:check:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/ruleTypeListForZk"})
   public AjaxResult ruleTypeListForZk(QcRuleManuCheckVo qcRuleManuCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcRuleManuCheckVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getRuleTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则类型编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcRuleManuCheckVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控阶段不能为空");
         }

         if (flag && qcRuleManuCheckVo.getRuleTypeCode().equals("1")) {
            if (qcRuleManuCheckVo.getElemId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("元素id不能为空");
            }

            if (flag && qcRuleManuCheckVo.getMrFileId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历文件id不能为空");
            }

            Index index = null;
            SubfileIndex subfileIndex = null;
            if (flag) {
               index = this.indexService.selectIndexById(qcRuleManuCheckVo.getMrFileId());
               if (index == null) {
                  subfileIndex = this.subfileIndexService.selectSubfileIndexById(qcRuleManuCheckVo.getMrFileId());
               }

               if (index == null && subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("未找到当前病历文件");
               } else {
                  String emrTypeCode = subfileIndex == null ? index.getMrType() : subfileIndex.getMrType();
                  qcRuleManuCheckVo.setEmrTypeCode(emrTypeCode);
               }
            }
         }

         if (flag) {
            List<QcRuleManuCheckVo> list = this.qcRuleManuCheckService.selectQcRuleTypeForZkList(qcRuleManuCheckVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询人工抽查质控下拉出现异常", e);
         ajaxResult = AjaxResult.error("查询人工抽查质控下拉出现异常");
      }

      return ajaxResult;
   }
}
