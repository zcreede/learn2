package com.emr.project.docOrder.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderOperationLog;
import com.emr.project.docOrder.service.ITdPaOrderOperationLogService;
import com.emr.project.system.domain.SysUser;
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
@RequestMapping({"/docOrder/log"})
public class TdPaOrderOperationLogController extends BaseController {
   @Autowired
   private ITdPaOrderOperationLogService tdPaOrderOperationLogService;
   @Autowired
   private ICommonService commonService;

   @PreAuthorize("@ss.hasPermi('docOrder:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderOperationLog tdPaOrderOperationLog) {
      this.startPage();
      List<TdPaOrderOperationLog> list = this.tdPaOrderOperationLogService.selectTdPaOrderOperationLogList(tdPaOrderOperationLog);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:log:export')")
   @Log(
      title = "医嘱操作日志",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderOperationLog tdPaOrderOperationLog) {
      List<TdPaOrderOperationLog> list = this.tdPaOrderOperationLogService.selectTdPaOrderOperationLogList(tdPaOrderOperationLog);
      ExcelUtil<TdPaOrderOperationLog> util = new ExcelUtil(TdPaOrderOperationLog.class);
      return util.exportExcel(list, "医嘱操作日志数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderOperationLogService.selectTdPaOrderOperationLogById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:log:add,docOrder:order:add')")
   @Log(
      title = "医嘱操作日志",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderOperationLog tdPaOrderOperationLog) {
      try {
         tdPaOrderOperationLog.setId(SnowIdUtils.uniqueLong());
         this.log.warn("医嘱操作日志开始：-------" + tdPaOrderOperationLog.getId() + " : " + tdPaOrderOperationLog.getOperationName() + " :  " + tdPaOrderOperationLog.getOperatorDesc());
         if (StringUtils.getStringLengthByByte(tdPaOrderOperationLog.getOperatorDesc()) > 200) {
            String operatorDesc = tdPaOrderOperationLog.getOperatorDesc().substring(0, 90);
            tdPaOrderOperationLog.setOperatorDesc(operatorDesc);
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         tdPaOrderOperationLog.setOperatorCode(sysUser.getUserName());
         tdPaOrderOperationLog.setOperatorNo(sysUser.getUserName());
         tdPaOrderOperationLog.setOperatorName(sysUser.getNickName());
         tdPaOrderOperationLog.setOperationTime(this.commonService.getDbSysdate());
         this.tdPaOrderOperationLogService.insertTdPaOrderOperationLog(tdPaOrderOperationLog);
      } catch (Exception e) {
         this.log.error("增加医嘱操作日志出现异常：", e);
      }

      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('docOrder:log:edit')")
   @Log(
      title = "医嘱操作日志",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderOperationLog tdPaOrderOperationLog) {
      return this.toAjax(this.tdPaOrderOperationLogService.updateTdPaOrderOperationLog(tdPaOrderOperationLog));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:log:remove')")
   @Log(
      title = "医嘱操作日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderOperationLogService.deleteTdPaOrderOperationLogByIds(ids));
   }
}
