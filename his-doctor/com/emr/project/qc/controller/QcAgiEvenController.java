package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcAgiEven;
import com.emr.project.qc.service.IQcAgiEvenService;
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
@RequestMapping({"/qc/even"})
public class QcAgiEvenController extends BaseController {
   @Autowired
   private IQcAgiEvenService qcAgiEvenService;

   @PreAuthorize("@ss.hasPermi('qc:even:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcAgiEven qcAgiEven) {
      this.startPage();
      List<QcAgiEven> list = this.qcAgiEvenService.selectQcAgiEvenList(qcAgiEven);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:even:list,qc:agiRule:list')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(QcAgiEven qcAgiEven) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<QcAgiEven> list = this.qcAgiEvenService.selectQcAgiEvenList(qcAgiEven);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询时效质控事件列表出现异常", e);
         ajaxResult = AjaxResult.error("查询时效质控事件列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:even:export')")
   @Log(
      title = "病历时效质控事件",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcAgiEven qcAgiEven) {
      List<QcAgiEven> list = this.qcAgiEvenService.selectQcAgiEvenList(qcAgiEven);
      ExcelUtil<QcAgiEven> util = new ExcelUtil(QcAgiEven.class);
      return util.exportExcel(list, "病历时效质控事件数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:even:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcAgiEvenService.selectQcAgiEvenById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:even:add')")
   @Log(
      title = "病历时效质控事件",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcAgiEven qcAgiEven) {
      return this.toAjax(this.qcAgiEvenService.insertQcAgiEven(qcAgiEven));
   }

   @PreAuthorize("@ss.hasPermi('qc:even:edit')")
   @Log(
      title = "病历时效质控事件",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcAgiEven qcAgiEven) {
      return this.toAjax(this.qcAgiEvenService.updateQcAgiEven(qcAgiEven));
   }

   @PreAuthorize("@ss.hasPermi('qc:even:remove')")
   @Log(
      title = "病历时效质控事件",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcAgiEvenService.deleteQcAgiEvenByIds(ids));
   }
}
