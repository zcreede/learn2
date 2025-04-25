package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.QcScoreScheDedVo;
import com.emr.project.qc.service.IQcScoreScheDedService;
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
@RequestMapping({"/qc/ded"})
public class QcScoreScheDedController extends BaseController {
   @Autowired
   private IQcScoreScheDedService qcScoreScheDedService;

   @PreAuthorize("@ss.hasPermi('qc:ded:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreScheDed qcScoreScheDed) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcScoreScheDed> list = this.qcScoreScheDedService.selectQcScoreScheDedList(qcScoreScheDed);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询评分方案已绑定评分细则分页列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询评分方案已绑定评分细则分页列表异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:ded:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcScoreScheDedService.selectQcScoreScheDedById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:ded:add,qc:sche:add')")
   @Log(
      title = "评分方案与评分细则对应关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreScheDedVo qcScoreScheDedVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (qcScoreScheDedVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreScheDedVo.getScheId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheDedVo.getScheName())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案名称不能为空");
         }

         if (flag && qcScoreScheDedVo.getItemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目id不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreScheDedVo.getItemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目名称不能为空");
         }

         if (flag && qcScoreScheDedVo.getItemSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目排序不能为空");
         }

         if (flag && qcScoreScheDedVo.getItemTotalScore() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目总分不能为空");
         }

         if (flag && (qcScoreScheDedVo.getQcScoreScheDedList() == null || qcScoreScheDedVo.getQcScoreScheDedList().size() < 1)) {
            flag = false;
            ajaxResult = AjaxResult.error("细则集合不能为空");
         }

         if (flag) {
            this.qcScoreScheDedService.insertQcScoreScheDed(qcScoreScheDedVo);
         }
      } catch (Exception e) {
         this.log.error("新增评分方案与评分细则对应关系异常", e);
         ajaxResult = AjaxResult.error("新增评分方案与评分细则对应关系异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:ded:edit')")
   @Log(
      title = "评分方案与评分细则对应关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreScheDed qcScoreScheDed) {
      return this.toAjax(this.qcScoreScheDedService.updateQcScoreScheDed(qcScoreScheDed));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:ded:remove,qc:sche:remove')")
   @Log(
      title = "评分方案与评分细则对应关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/remove"})
   public AjaxResult remove(@RequestBody QcScoreScheDed qcScoreScheDed) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         if (qcScoreScheDed == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreScheDed.getScheId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("方案id不能为空");
         }

         if (flag && qcScoreScheDed.getItemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目id不能为空");
         }

         if (flag) {
            this.qcScoreScheDedService.deleteQcScoreScheDedByScheId(qcScoreScheDed.getScheId(), qcScoreScheDed.getItemId());
         }
      } catch (Exception e) {
         this.log.error("删除评分方案关联细则出现异常", e);
         ajaxResult = AjaxResult.error("删除评分方案关联细则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:ded:edit,qc:sche:add')")
   @GetMapping({"/itemList"})
   public AjaxResult itemList(QcScoreScheDedVo qcScoreScheDedVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcScoreScheDedVo == null) {
            ajaxResult = AjaxResult.error("参数不能为空");
            flag = false;
         }

         if (flag && StringUtils.isEmpty(qcScoreScheDedVo.getItemClassCd())) {
            ajaxResult = AjaxResult.error("项目分类编码不能为空");
            flag = false;
         }

         if (flag && qcScoreScheDedVo.getScheId() == null) {
            ajaxResult = AjaxResult.error("方案id不能为空");
            flag = false;
         }

         if (flag) {
            List<QcScoreScheDedVo> list = this.qcScoreScheDedService.selectQcScoreItemList(qcScoreScheDedVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("获取方案关联细则项目下拉异常", e);
         ajaxResult = AjaxResult.error("获取方案关联细则项目下拉异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:sche:list')")
   @GetMapping({"/dedList"})
   public AjaxResult dedList(QcScoreScheDed qcScoreScheDed) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcScoreScheDed == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreScheDed.getScheId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案id不能为空");
         }

         if (flag) {
            EmrQcFlowScoreVo emrQcFlowScoreVo = this.qcScoreScheDedService.selectScheDedList(qcScoreScheDed);
            ajaxResult = AjaxResult.success((Object)emrQcFlowScoreVo);
         }
      } catch (Exception e) {
         this.log.error("获取预览评分表列表异常", e);
         ajaxResult = AjaxResult.error("获取预览评分表列表异常");
      }

      return ajaxResult;
   }
}
