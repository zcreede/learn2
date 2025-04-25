package com.emr.project.qc.controller;

import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.QcScoreDedDetailVo;
import com.emr.project.qc.service.IQcScoreDedDetailService;
import com.emr.project.qc.service.IQcScoreScheDedService;
import java.util.ArrayList;
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
@RequestMapping({"/qc/detail"})
public class QcScoreDedDetailController extends BaseController {
   @Autowired
   private IQcScoreDedDetailService qcScoreDedDetailService;
   @Autowired
   private IQcScoreScheDedService qcScoreScheDedService;

   @PreAuthorize("@ss.hasPermi('qc:detail:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreDedDetailVo qcScoreDedDetailVo) {
      this.startPage();
      List<QcScoreDedDetailVo> list = new ArrayList(1);

      try {
         list = this.qcScoreDedDetailService.selectQcScoreDedDetailList(qcScoreDedDetailVo);
      } catch (Exception e) {
         this.log.error("查询病历评分细则列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:detail:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcScoreDedDetailService.selectQcScoreDedDetailById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:detail:add')")
   @Log(
      title = "病历评分细则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreDedDetailVo qcScoreDedDetail) {
      AjaxResult ajaxResult = AjaxResult.success("新增病历评分细则成功");
      boolean flag = true;

      try {
         if (flag && qcScoreDedDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (StringUtils.isBlank(qcScoreDedDetail.getItemCd()) || StringUtils.isBlank(qcScoreDedDetail.getItemName()))) {
            flag = false;
            ajaxResult = AjaxResult.error("所属项目不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getDedDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则描述不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getDedType())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则扣分类型不能为空");
         }

         if (flag && qcScoreDedDetail.getDedScoreSingle() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则单项扣分值不能为空");
         }

         if (flag && qcScoreDedDetail.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则排序不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则启用状态不能为空");
         }

         if (flag) {
            this.qcScoreDedDetailService.insertQcScoreDedDetail(qcScoreDedDetail);
         }
      } catch (Exception e) {
         this.log.error("新增病历评分细则出现异常,", e);
         ajaxResult = AjaxResult.error("新增病历评分细则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:detail:edit')")
   @Log(
      title = "病历评分细则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreDedDetailVo qcScoreDedDetail) {
      AjaxResult ajaxResult = AjaxResult.success("修改病历评分细则成功");
      boolean flag = true;

      try {
         if (flag && qcScoreDedDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreDedDetail.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (StringUtils.isBlank(qcScoreDedDetail.getItemCd()) || StringUtils.isBlank(qcScoreDedDetail.getItemName()))) {
            flag = false;
            ajaxResult = AjaxResult.error("所属项目不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getDedDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则描述不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getDedType())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则扣分类型不能为空");
         }

         if (flag && qcScoreDedDetail.getDedScoreSingle() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则单项扣分值不能为空");
         }

         if (flag && qcScoreDedDetail.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则排序不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("评分细则启用状态不能为空");
         }

         if (flag) {
            QcScoreDedDetail dedDetail = this.qcScoreDedDetailService.selectQcScoreDedDetailById(qcScoreDedDetail.getId());
            if (dedDetail == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此评分细则记录，不能编辑");
            }
         }

         if (flag) {
            this.qcScoreDedDetailService.updateQcScoreDedDetail(qcScoreDedDetail);
         }
      } catch (Exception e) {
         this.log.error("修改病历评分细则出现异常,", e);
         ajaxResult = AjaxResult.error("修改病历评分细则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:detail:remove')")
   @Log(
      title = "病历评分细则",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除病历评分细则成功");

      try {
         List<QcScoreScheDed> qcScoreScheDedList = this.qcScoreScheDedService.selectScheDedListByDedId(ids);
         if (qcScoreScheDedList != null && !qcScoreScheDedList.isEmpty()) {
            ajaxResult = AjaxResult.error("当前评分细则在【" + ((QcScoreScheDed)qcScoreScheDedList.get(0)).getScheName() + "】评分方案中正在使用，请在评分方案中删除该细则后再试。");
         } else {
            this.qcScoreDedDetailService.changeDelFlag(ids);
         }
      } catch (Exception e) {
         this.log.error("删除病历评分细则出现异常,", e);
         ajaxResult = AjaxResult.error("删除病历评分细则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:detail:change')")
   @Log(
      title = "修改病历评分细则启用状态",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/change"})
   public AjaxResult changeValidFlag(@RequestBody QcScoreDedDetailVo qcScoreDedDetail) {
      AjaxResult ajaxResult = AjaxResult.success("修改病历评分细则启用状态成功");
      boolean flag = true;

      try {
         if (flag && qcScoreDedDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreDedDetail.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreDedDetail.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用状态不能为空");
         }

         if (flag) {
            QcScoreDedDetail qcScoreDedDetailParam = new QcScoreDedDetail();
            qcScoreDedDetailParam.setId(qcScoreDedDetail.getId());
            qcScoreDedDetailParam.setValidFlag(qcScoreDedDetail.getValidFlag());
            this.qcScoreDedDetailService.updateQcScoreDedDetail(qcScoreDedDetail);
         }
      } catch (Exception e) {
         this.log.error("修改病历评分细则启用状态出现异常,", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:sche:edit')")
   @GetMapping({"/unScheDedList"})
   public TableDataInfo unScheDedList(QcScoreDedDetailVo qcScoreDedDetail) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (qcScoreDedDetail.getScheId() == null) {
            tableDataInfo = new TableDataInfo(500, "方案id不能为空");
         } else {
            this.startPage();
            List<QcScoreDedDetail> list = this.qcScoreDedDetailService.selectUnScheDedList(qcScoreDedDetail);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询未绑定的评分细则出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询未绑定的评分细则出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:ded:edit,qc:sche:add')")
   @GetMapping({"/scheDedList"})
   public AjaxResult scheDedList(QcScoreDedDetailVo qcScoreDedDetail) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (qcScoreDedDetail == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreDedDetail.getScheId())) {
            flag = false;
            ajaxResult = AjaxResult.error("方案id不能为空");
         }

         if (flag && qcScoreDedDetail.getItemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目id不能为空");
         }

         if (flag) {
            List<QcScoreDedDetail> list = this.qcScoreDedDetailService.selectScheDedList(qcScoreDedDetail);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询已绑定的评分细则出现异常", e);
         ajaxResult = AjaxResult.error("查询已绑定的评分细则出现异常");
      }

      return ajaxResult;
   }
}
