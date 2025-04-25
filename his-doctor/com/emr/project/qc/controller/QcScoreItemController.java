package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreItem;
import com.emr.project.qc.domain.QcScoreScheDed;
import com.emr.project.qc.domain.vo.QcScoreItemVo;
import com.emr.project.qc.service.IQcScoreDedDetailService;
import com.emr.project.qc.service.IQcScoreItemService;
import com.emr.project.qc.service.IQcScoreScheDedService;
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
@RequestMapping({"/qc/item"})
public class QcScoreItemController extends BaseController {
   @Autowired
   private IQcScoreItemService qcScoreItemService;
   @Autowired
   private IQcScoreDedDetailService qcScoreDedDetailService;
   @Autowired
   private IQcScoreScheDedService qcScoreScheDedService;

   @PreAuthorize("@ss.hasAnyPermi('qc:item:list,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreItem qcScoreItem) {
      this.startPage();
      List<QcScoreItemVo> list = null;

      try {
         list = this.qcScoreItemService.selectQcScoreItemList(qcScoreItem);
      } catch (Exception e) {
         this.log.error("查询评分项目维护列表出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:item:list,qc:ded:list')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(QcScoreItem qcScoreItem) {
      List<QcScoreItemVo> list = null;

      try {
         list = this.qcScoreItemService.selectQcScoreItemList(qcScoreItem);
      } catch (Exception e) {
         this.log.error("查询评分项目维护列表出现异常，", e);
      }

      return AjaxResult.success((Object)list);
   }

   @PreAuthorize("@ss.hasPermi('qc:item:export')")
   @Log(
      title = "评分项目维护",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcScoreItem qcScoreItem) {
      List<QcScoreItemVo> list = null;
      ExcelUtil<QcScoreItemVo> util = null;

      try {
         list = this.qcScoreItemService.selectQcScoreItemList(qcScoreItem);
         util = new ExcelUtil(QcScoreItemVo.class);
      } catch (Exception e) {
         this.log.error("导出评分项目维护列表出现异常,", e);
      }

      return util.exportExcel(list, "评分项目维护数据");
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:item:query,qc:item:edit')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      QcScoreItem qcScoreItem = null;

      try {
         this.qcScoreItemService.selectQcScoreItemById(id);
      } catch (Exception e) {
         this.log.error("获取评分项目维护详细信息出现异常,", e);
         ajaxResult = AjaxResult.error("获取评分项目维护详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:item:add')")
   @Log(
      title = "评分项目维护",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreItem qcScoreItem) {
      AjaxResult ajaxResult = AjaxResult.success("新增评分项目维护成功");
      boolean flag = true;

      try {
         if (flag && qcScoreItem == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreItem.getItemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目名称不能为空");
         }

         if (flag && qcScoreItem.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目序号不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreItem.getItemClassCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreItem.getItemClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目分类名称不能为空");
         }

         if (flag && qcScoreItem.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("默认项目总分不能为空");
         }

         if (flag) {
            this.qcScoreItemService.insertQcScoreItem(qcScoreItem);
         }
      } catch (Exception e) {
         this.log.error("新增评分项目维护出现异常,", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:item:edit')")
   @Log(
      title = "评分项目维护",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreItem qcScoreItem) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && qcScoreItem == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreItem.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数id不能为空");
         }

         if (flag && StringUtils.isBlank(qcScoreItem.getItemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目名称不能为空");
         }

         if (flag && qcScoreItem.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("项目序号不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreItem.getItemClassCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目分类编码不能为空");
         }

         if (flag && StringUtils.isEmpty(qcScoreItem.getItemClassName())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目分类名称不能为空");
         }

         if (flag && qcScoreItem.getSort() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("默认项目总分不能为空");
         }

         if (flag) {
            QcScoreItem item = this.qcScoreItemService.selectQcScoreItemById(qcScoreItem.getId());
            if (item == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有项目序号记录，不能编辑");
            }
         }

         if (flag) {
            this.qcScoreItemService.updateQcScoreItem(qcScoreItem);
         }
      } catch (Exception e) {
         this.log.error("修改评分项目维护出现异常，", e);
         ajaxResult = AjaxResult.error("修改评分项目维护出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:item:remove')")
   @Log(
      title = "评分项目维护",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long ids) {
      AjaxResult ajaxResult = AjaxResult.success("删除评分项目维护成功");
      boolean flag = true;
      new StringBuffer("");

      try {
         QcScoreDedDetail qcScoreDedDetail = new QcScoreDedDetail();
         qcScoreDedDetail.setItemCd(ids.toString());
         qcScoreDedDetail.setDelFlag("0");
         List<QcScoreDedDetail> qcScoreDedDetails = this.qcScoreDedDetailService.selectDedDetailList(qcScoreDedDetail);
         if (qcScoreDedDetails != null && !qcScoreDedDetails.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("当前评分项目在【" + ((QcScoreDedDetail)qcScoreDedDetails.get(0)).getDedDesc() + "】评分细则中正在使用，请在评分细则中删除该项目后再试。");
         }

         if (flag) {
            List<QcScoreScheDed> qcScoreScheDedList = this.qcScoreScheDedService.selectScheDedListByItem(ids);
            if (qcScoreScheDedList != null && !qcScoreScheDedList.isEmpty()) {
               flag = false;
               ajaxResult = AjaxResult.error("当前评分项目在【" + ((QcScoreScheDed)qcScoreScheDedList.get(0)).getScheName() + "】评分方案中正在使用，请在评分方案中删除该项目后再试。");
            }
         }

         if (flag) {
            this.qcScoreItemService.changeDelFlag(ids);
         }
      } catch (Exception e) {
         this.log.error("删除评分项目维护出现异常,", e);
      }

      return ajaxResult;
   }
}
