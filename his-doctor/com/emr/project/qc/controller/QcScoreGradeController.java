package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreGrade;
import com.emr.project.qc.service.IQcScoreGradeService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping({"/qc/grade"})
public class QcScoreGradeController extends BaseController {
   @Autowired
   private IQcScoreGradeService qcScoreGradeService;

   @PreAuthorize("@ss.hasAnyPermi('qc:grade:list,qc:flow:term,qc:flow:dept')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreGrade qcScoreGrade) {
      this.startPage();
      List<QcScoreGrade> list = null;

      try {
         list = this.qcScoreGradeService.selectQcScoreGradeList(qcScoreGrade);
      } catch (Exception e) {
         this.log.error("查询病历评分等级列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:grade:export')")
   @Log(
      title = "病历评分等级",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcScoreGrade qcScoreGrade) {
      List<QcScoreGrade> list = this.qcScoreGradeService.selectQcScoreGradeList(qcScoreGrade);
      ExcelUtil<QcScoreGrade> util = new ExcelUtil(QcScoreGrade.class);
      return util.exportExcel(list, "病历评分等级数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:grade:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcScoreGradeService.selectQcScoreGradeById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:grade:add')")
   @Log(
      title = "病历评分等级",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreGrade qcScoreGrade) {
      return this.toAjax(this.qcScoreGradeService.insertQcScoreGrade(qcScoreGrade));
   }

   @PreAuthorize("@ss.hasPermi('qc:grade:edit')")
   @Log(
      title = "病历评分等级",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody List qcScoreGradeList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && (qcScoreGradeList == null || qcScoreGradeList != null && qcScoreGradeList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            for(QcScoreGrade qcScoreGrade : qcScoreGradeList) {
               if (flag && qcScoreGrade.getId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("参数不能为空");
                  break;
               }

               if (flag && StringUtils.isBlank(qcScoreGrade.getGradeCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("参数不能为空");
                  break;
               }

               if (flag && StringUtils.isBlank(qcScoreGrade.getGradeName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("参数不能为空");
                  break;
               }

               if (flag && qcScoreGrade.getGradeCd().equals("1") && qcScoreGrade.getScoreMin() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error(qcScoreGrade.getGradeName() + "，最低分不能为空");
                  break;
               }

               if (flag && qcScoreGrade.getGradeCd().equals("2")) {
                  if (qcScoreGrade.getScoreMax() == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error(qcScoreGrade.getGradeName() + "，最高分不能为空");
                     break;
                  }

                  if (qcScoreGrade.getScoreMin() == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error(qcScoreGrade.getGradeName() + "，最低分不能为空");
                     break;
                  }
               }

               if (flag && qcScoreGrade.getGradeCd().equals("3") && qcScoreGrade.getScoreMax() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error(qcScoreGrade.getGradeName() + "，最高分不能为空");
                  break;
               }
            }
         }

         if (flag) {
            this.qcScoreGradeService.updateQcScoreGrade(qcScoreGradeList);
         }
      } catch (Exception e) {
         this.log.error("保存病历评分等级出现异常,", e);
         ajaxResult = AjaxResult.error("保存病历评分等级出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:grade:remove')")
   @Log(
      title = "病历评分等级",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcScoreGradeService.deleteQcScoreGradeByIds(ids));
   }
}
