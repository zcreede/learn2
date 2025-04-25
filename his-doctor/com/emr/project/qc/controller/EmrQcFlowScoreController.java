package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.EmrQcFlowScore;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.service.IEmrQcFlowScoreService;
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
@RequestMapping({"/qc/score"})
public class EmrQcFlowScoreController extends BaseController {
   @Autowired
   private IEmrQcFlowScoreService emrQcFlowScoreService;

   @PreAuthorize("@ss.hasPermi('qc:score:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcFlowScore emrQcFlowScore) {
      this.startPage();
      List<EmrQcFlowScore> list = this.emrQcFlowScoreService.selectEmrQcFlowScoreList(emrQcFlowScore);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:score:export')")
   @Log(
      title = "病历质控评分明细记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcFlowScore emrQcFlowScore) {
      List<EmrQcFlowScore> list = this.emrQcFlowScoreService.selectEmrQcFlowScoreList(emrQcFlowScore);
      ExcelUtil<EmrQcFlowScore> util = new ExcelUtil(EmrQcFlowScore.class);
      return util.exportExcel(list, "病历质控评分明细记录数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:score:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcFlowScoreService.selectEmrQcFlowScoreById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:score:add')")
   @Log(
      title = "病历质控评分明细记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcFlowScoreVo emrQcFlowScoreVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = false;

      try {
         this.emrQcFlowScoreService.saveEmrQcFlowScoreVo(emrQcFlowScoreVo);
      } catch (Exception e) {
         this.log.error("新增病历质控评分明细记录出现异常", e);
         ajaxResult = AjaxResult.error("新增病历质控评分明细记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:score:edit')")
   @Log(
      title = "病历质控评分明细记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcFlowScore emrQcFlowScore) {
      return this.toAjax(this.emrQcFlowScoreService.updateEmrQcFlowScore(emrQcFlowScore));
   }

   @PreAuthorize("@ss.hasPermi('qc:score:remove')")
   @Log(
      title = "病历质控评分明细记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcFlowScoreService.deleteEmrQcFlowScoreByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:score:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/createScore"})
   public AjaxResult createScore(EmrQcFlowScoreVo emrQcFlowScoreVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (emrQcFlowScoreVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowScoreVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && emrQcFlowScoreVo.getRecordId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控id不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowScoreVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag) {
            if (!emrQcFlowScoreVo.getAppSeg().equals("03")) {
               List<String> list = new ArrayList();
               list.add("0");
               list.add("3");
               emrQcFlowScoreVo.setQcStateList(list);
            }

            ajaxResult = AjaxResult.success((Object)this.emrQcFlowScoreService.createScoreRecordList(emrQcFlowScoreVo));
         }
      } catch (Exception e) {
         this.log.error("生成评分记录出现异常", e);
         ajaxResult = AjaxResult.error("生成评分记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:score:list')")
   @GetMapping({"/createEndScore"})
   public AjaxResult createEndScore(EmrQcFlowScoreVo emrQcFlowScoreVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (emrQcFlowScoreVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowScoreVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && emrQcFlowScoreVo.getRecordId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控id不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlowScoreVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("环节不能为空");
         }

         if (flag) {
            List<String> list = new ArrayList();
            list.add("0");
            list.add("3");
            emrQcFlowScoreVo.setQcStateList(list);
            ajaxResult = AjaxResult.success((Object)this.emrQcFlowScoreService.createScoreRecordList(emrQcFlowScoreVo));
         }
      } catch (Exception e) {
         this.log.error("生成评分记录出现异常", e);
         ajaxResult = AjaxResult.error("生成评分记录出现异常");
      }

      return ajaxResult;
   }
}
