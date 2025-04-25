package com.emr.project.emr.controller;

import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.EmrPrintLog;
import com.emr.project.emr.service.IEmrPrintLogService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/emr/printlog"})
public class EmrPrintLogController extends BaseController {
   @Autowired
   private IEmrPrintLogService emrPrintLogService;

   @PreAuthorize("@ss.hasPermi('emr:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrPrintLog emrPrintLog) {
      this.startPage();
      List<EmrPrintLog> list = this.emrPrintLogService.selectEmrPrintLogList(emrPrintLog);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:log:export')")
   @Log(
      title = "病历打印日志",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrPrintLog emrPrintLog) {
      List<EmrPrintLog> list = this.emrPrintLogService.selectEmrPrintLogList(emrPrintLog);
      ExcelUtil<EmrPrintLog> util = new ExcelUtil(EmrPrintLog.class);
      return util.exportExcel(list, "病历打印日志数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") String id) {
      return AjaxResult.success((Object)this.emrPrintLogService.selectEmrPrintLogById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:printlog:add')")
   @Log(
      title = "病历打印日志",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrPrintLog emrPrintLog, HttpServletRequest request) {
      Boolean flag = true;
      String msg = "新增病历打印日志成功";

      try {
         if (flag && StringUtils.isBlank(emrPrintLog.getFileId())) {
            flag = false;
            msg = "病历文件id不能为空";
         }

         if (flag && StringUtils.isBlank(emrPrintLog.getPatientId())) {
            flag = false;
            msg = "患者id不能为空";
         }

         if (flag && StringUtils.isBlank(emrPrintLog.getFileType())) {
            flag = false;
            msg = "病历文件类型不能为空";
         }

         if (flag && StringUtils.isBlank(emrPrintLog.getPrintType())) {
            flag = false;
            msg = "病历文件打印类型不能为空";
         }

         if (flag && emrPrintLog.getPrintType().equals("2")) {
            if (emrPrintLog.getBeginRow() == null || emrPrintLog.getEndRow() == null) {
               flag = false;
               msg = "病历文件打印续打时，起始行和结束行不能为空，请填写后，再操作";
            }

            if (flag && emrPrintLog.getEndRow() < emrPrintLog.getBeginRow()) {
               flag = false;
               msg = "病历文件打印续打时，结束行的值不能小于起始行，请重新填写后，再操作";
            }
         }

         if (flag) {
            String ip = IPAddressUtil.getIPAddress(request);
            emrPrintLog.setPrintIp(ip);
            this.emrPrintLogService.insertEmrPrintLog(emrPrintLog);
         }
      } catch (Exception e) {
         flag = false;
         msg = "新增病历打印日志出现异常，请联系管理员";
         this.log.error("新增病历打印日志出现异常：", e);
      }

      AjaxResult ajaxResult = flag ? AjaxResult.success(msg) : AjaxResult.error(msg);
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:printlog:add')")
   @Log(
      title = "病历打印日志",
      businessType = BusinessType.INSERT
   )
   @GetMapping({"/beginRow/{id}"})
   public AjaxResult getBeginRow(@PathVariable("id") String fileId) {
      AjaxResult ajaxResult = AjaxResult.success("续打-查询打印的起始行号成功");

      try {
         Integer beginRow = this.emrPrintLogService.getBeginRow(fileId);
         ajaxResult.put("beginRow", beginRow);
      } catch (Exception e) {
         this.log.error("续打-查询打印的起始行号出现异常：", e);
         ajaxResult = AjaxResult.error("续打-查询打印的起始行号出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:log:edit')")
   @Log(
      title = "病历打印日志",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrPrintLog emrPrintLog) {
      return this.toAjax(this.emrPrintLogService.updateEmrPrintLog(emrPrintLog));
   }

   @PreAuthorize("@ss.hasPermi('emr:log:remove')")
   @Log(
      title = "病历打印日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.emrPrintLogService.deleteEmrPrintLogByIds(ids));
   }
}
