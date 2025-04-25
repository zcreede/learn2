package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.vo.QcScoreScheVo;
import com.emr.project.qc.service.IQcScoreScheService;
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
@RequestMapping({"/qc/sche"})
public class QcScoreScheController extends BaseController {
   @Autowired
   private IQcScoreScheService qcScoreScheService;

   @PreAuthorize("@ss.hasPermi('qc:sche:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreScheVo qcScoreScheVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcScoreScheVo> list = this.qcScoreScheService.selectQcScoreScheList(qcScoreScheVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询评分方案列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询评分方案列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:list')")
   @GetMapping({"queryInfo/{scheId}"})
   public AjaxResult getInfo(@PathVariable("scheId") Long scheId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         QcScoreScheVo qcScoreScheVo = this.qcScoreScheService.selectQcScoreScheById(scheId);
         ajaxResult = AjaxResult.success((Object)qcScoreScheVo);
      } catch (Exception e) {
         this.log.error("获取单个评分方案信息异常", e);
         ajaxResult = AjaxResult.error("获取单个评分方案信息异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:add')")
   @Log(
      title = "评分方案",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreScheVo qcScoreScheVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (qcScoreScheVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getScheName())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用环节不能为空");
         }

         if (flag && qcScoreScheVo.getScore() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("方案总分不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcScoreScheService.insertQcScoreSche(qcScoreScheVo);
         }
      } catch (Exception e) {
         this.log.error("新增评分方案异常", e);
         ajaxResult = AjaxResult.error("新增评分方案异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:edit')")
   @Log(
      title = "评分方案",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreScheVo qcScoreScheVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcScoreScheVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreScheVo.getScheId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getScheName())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案名称不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用环节不能为空");
         }

         if (flag && qcScoreScheVo.getScore() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("方案总分不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            this.qcScoreScheService.updateQcScoreSche(qcScoreScheVo);
         }
      } catch (Exception e) {
         this.log.error("修改评分方案出现异常", e);
         ajaxResult = AjaxResult.error("修改评分方案出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:remove')")
   @Log(
      title = "评分方案",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{scheId}"})
   public AjaxResult remove(@PathVariable Long scheId) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");

      try {
         this.qcScoreScheService.deleteQcScoreScheById(scheId);
      } catch (Exception e) {
         this.log.error("删除评分方案出现异常", e);
         ajaxResult = AjaxResult.error("删除评分方案出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:edit')")
   @GetMapping({"/dedEditSearch"})
   public AjaxResult dedEditSearch(Long scheId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (scheId == null) {
            return AjaxResult.error("方案id不能为空");
         }

         QcScoreScheVo qcScoreScheVo = this.qcScoreScheService.selectDedEditSearch(scheId);
         ajaxResult = AjaxResult.success((Object)qcScoreScheVo);
      } catch (Exception e) {
         this.log.error("方案关联细则编辑查询异常", e);
         ajaxResult = AjaxResult.error("方案关联细则编辑查询异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:edit')")
   @GetMapping({"/isChooseAllDept"})
   public AjaxResult isChooseAllDept(QcScoreScheVo qcScoreScheVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcScoreScheVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheVo.getAppSeg())) {
            flag = false;
            ajaxResult = AjaxResult.error("适用环节不能为空");
         }

         if (flag) {
            Boolean bool = this.qcScoreScheService.selectIsChooseAllDept(qcScoreScheVo);
            ajaxResult.put("flag", bool);
         }
      } catch (Exception e) {
         this.log.error("判断是否可选全部科室异常", e);
         ajaxResult = AjaxResult.error("判断是否可选全部科室异常");
      }

      return ajaxResult;
   }
}
