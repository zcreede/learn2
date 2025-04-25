package com.emr.project.qc.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.qc.domain.QcCheckElem;
import com.emr.project.qc.service.IQcCheckElemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/qc/elem"})
public class QcCheckElemController extends BaseController {
   @Autowired
   private IQcCheckElemService qcCheckElemService;

   @PreAuthorize("@ss.hasPermi('qc:elem:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcCheckElem qcCheckElem) {
      this.startPage();
      List<QcCheckElem> list = this.qcCheckElemService.selectQcCheckElemList(qcCheckElem);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:elem:export')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcCheckElem qcCheckElem) {
      List<QcCheckElem> list = this.qcCheckElemService.selectQcCheckElemList(qcCheckElem);
      ExcelUtil<QcCheckElem> util = new ExcelUtil(QcCheckElem.class);
      return util.exportExcel(list, "【请填写功能名称】数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:elem:query')")
   @GetMapping({"/{checkId}"})
   public AjaxResult getInfo(@PathVariable("checkId") Long checkId) {
      return AjaxResult.success((Object)this.qcCheckElemService.selectQcCheckElemById(checkId));
   }

   @PreAuthorize("@ss.hasPermi('qc:elem:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{checkIds}"})
   public AjaxResult remove(@PathVariable Long[] checkIds) {
      return this.toAjax(this.qcCheckElemService.deleteQcCheckElemByIds(checkIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:elem:checkItems,qc:detail:add,qc:detail:edit')")
   @GetMapping({"/checkItems"})
   public AjaxResult getCheckItems(QcCheckElem checkElem) {
      AjaxResult ajaxResult = AjaxResult.success();
      List<QcCheckElem> list = null;

      try {
         list = this.qcCheckElemService.selectVQcCheckElemList(checkElem);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询质控数据项列表出现异常, ", e);
         ajaxResult = AjaxResult.error("查询质控数据项列表出现异常");
      }

      return ajaxResult;
   }
}
