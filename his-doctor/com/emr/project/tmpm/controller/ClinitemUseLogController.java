package com.emr.project.tmpm.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.tmpm.domain.ClinitemUseLog;
import com.emr.project.tmpm.service.IClinitemUseLogService;
import java.util.Date;
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
@RequestMapping({"/tmpm/log"})
public class ClinitemUseLogController extends BaseController {
   @Autowired
   private IClinitemUseLogService clinitemUseLogService;
   @Autowired
   private ITdPaOrderItemService orderItemService;
   @Autowired
   private ITdPaOrderDetailService orderDetailService;
   @Autowired
   private ICommonService commonService;

   @PreAuthorize("@ss.hasPermi('tmpm:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(ClinitemUseLog clinitemUseLog) {
      this.startPage();
      List<ClinitemUseLog> list = this.clinitemUseLogService.selectClinitemUseLogList(clinitemUseLog);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpm:log:export')")
   @Log(
      title = "临床项目使用记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(ClinitemUseLog clinitemUseLog) {
      List<ClinitemUseLog> list = this.clinitemUseLogService.selectClinitemUseLogList(clinitemUseLog);
      ExcelUtil<ClinitemUseLog> util = new ExcelUtil(ClinitemUseLog.class);
      return util.exportExcel(list, "临床项目使用记录数据");
   }

   @PreAuthorize("@ss.hasPermi('tmpm:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.clinitemUseLogService.selectClinitemUseLogById(id));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:log:add')")
   @Log(
      title = "临床项目使用记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody ClinitemUseLog clinitemUseLog) {
      return this.toAjax(this.clinitemUseLogService.insertClinitemUseLog(clinitemUseLog));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:log:edit')")
   @Log(
      title = "临床项目使用记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody ClinitemUseLog clinitemUseLog) {
      return this.toAjax(this.clinitemUseLogService.updateClinitemUseLog(clinitemUseLog));
   }

   @PreAuthorize("@ss.hasPermi('tmpm:log:remove')")
   @Log(
      title = "临床项目使用记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.clinitemUseLogService.deleteClinitemUseLogByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpm:log:updateUseTimes,docOrder:order:list')")
   @GetMapping({"/updateUseTimes"})
   public AjaxResult updateUseTimes(Date lastUpdateDate, Date lastUpdateDateEnd) {
      try {
         lastUpdateDate = lastUpdateDate == null ? this.clinitemUseLogService.selectMaxAltDate() : lastUpdateDate;
         lastUpdateDate = lastUpdateDate == null ? this.commonService.getDbSysdate() : lastUpdateDate;
         List<TdPaOrderDetailVo> orderDetailVoList = this.orderDetailService.selectListByTime(lastUpdateDate, lastUpdateDateEnd);
         if (orderDetailVoList != null && !orderDetailVoList.isEmpty()) {
            this.clinitemUseLogService.updateClinitemUseLogTask(orderDetailVoList);
         }
      } catch (Exception e) {
         this.log.error("更新医嘱项目使用次数出现异常：", e);
         return AjaxResult.error("更新医嘱项目使用次数出现异常");
      }

      return AjaxResult.success("更新医嘱项目使用次数成功");
   }
}
