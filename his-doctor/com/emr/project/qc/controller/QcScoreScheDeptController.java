package com.emr.project.qc.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcScoreScheDept;
import com.emr.project.qc.domain.vo.QcScoreScheDeptVo;
import com.emr.project.qc.service.IQcScoreScheDeptService;
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
@RequestMapping({"/qc/dept"})
public class QcScoreScheDeptController extends BaseController {
   @Autowired
   private IQcScoreScheDeptService qcScoreScheDeptService;

   @PreAuthorize("@ss.hasPermi('qc:dept:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcScoreScheDept qcScoreScheDept) {
      this.startPage();
      List<QcScoreScheDept> list = this.qcScoreScheDeptService.selectQcScoreScheDeptList(qcScoreScheDept);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:dept:list')")
   @GetMapping({"/list/{scheId}"})
   public TableDataInfo listByScheId(@PathVariable("scheId") Long scheId) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcScoreScheDept> list = this.qcScoreScheDeptService.selectQcScoreScheDeptListScheId(scheId);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询方案适用科室分页异常", e);
         tableDataInfo = new TableDataInfo(500, "查询方案适用科室分页异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:dept:export')")
   @Log(
      title = "评分方案与科室关系",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcScoreScheDept qcScoreScheDept) {
      List<QcScoreScheDept> list = this.qcScoreScheDeptService.selectQcScoreScheDeptList(qcScoreScheDept);
      ExcelUtil<QcScoreScheDept> util = new ExcelUtil(QcScoreScheDept.class);
      return util.exportExcel(list, "评分方案与科室关系数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:dept:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcScoreScheDeptService.selectQcScoreScheDeptById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:dept:add,qc:sche:add')")
   @Log(
      title = "评分方案与科室关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcScoreScheDeptVo qcScoreScheDeptVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (qcScoreScheDeptVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcScoreScheDeptVo.getDeptList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("科室信息不能为空");
         }

         if (flag && qcScoreScheDeptVo.getScheId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分方案id不能为空");
         }

         if (flag) {
            this.qcScoreScheDeptService.insertQcScoreScheDeptList(qcScoreScheDeptVo);
         }
      } catch (Exception e) {
         this.log.error("添加评分方案适用科室出现异常", e);
         ajaxResult = AjaxResult.error("添加评分方案适用科室出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:dept:edit')")
   @Log(
      title = "评分方案与科室关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcScoreScheDept qcScoreScheDept) {
      return this.toAjax(this.qcScoreScheDeptService.updateQcScoreScheDept(qcScoreScheDept));
   }

   @PreAuthorize("@ss.hasPermi('qc:dept:remove')")
   @Log(
      title = "评分方案与科室关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcScoreScheDeptService.deleteQcScoreScheDeptByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:dept:list,qc:sche:add')")
   @GetMapping({"/unDeptList"})
   public AjaxResult unDeptList(QcScoreScheDept qcScoreScheDept) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isNotEmpty(qcScoreScheDept.getAppSeg())) {
            ajaxResult = AjaxResult.success((Object)this.qcScoreScheDeptService.selectUnDeptList(qcScoreScheDept));
         } else {
            ajaxResult = AjaxResult.error("环节编码不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询适用科室列表异常", e);
         ajaxResult = AjaxResult.error("查询适用科室列表异常");
      }

      return ajaxResult;
   }
}
