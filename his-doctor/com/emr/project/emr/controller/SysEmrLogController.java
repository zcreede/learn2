package com.emr.project.emr.controller;

import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.InsertIndexLogVo;
import com.emr.project.emr.domain.vo.SysEmrLogVo;
import com.emr.project.emr.service.ISysEmrLogService;
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
@RequestMapping({"/emr/log"})
public class SysEmrLogController extends BaseController {
   @Autowired
   private ISysEmrLogService sysEmrLogService;

   @PreAuthorize("@ss.hasPermi('emr:log:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysEmrLogVo sysEmrLogVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysEmrLog> list = this.sysEmrLogService.selectSysEmrLogList(sysEmrLogVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历操作日志列表分页异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历操作日志列表分页异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:log:export,emr:log:list')")
   @Log(
      title = "病历操作日志",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysEmrLogVo sysEmrLogVo) {
      List<SysEmrLog> list = this.sysEmrLogService.selectSysEmrLogList(sysEmrLogVo);
      ExcelUtil<SysEmrLog> util = new ExcelUtil(SysEmrLog.class);
      return util.exportExcel(list, "病历操作日志数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:log:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysEmrLogService.selectSysEmrLogById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:log:edit')")
   @Log(
      title = "病历操作日志",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysEmrLog sysEmrLog) {
      return this.toAjax(this.sysEmrLogService.updateSysEmrLog(sysEmrLog));
   }

   @PreAuthorize("@ss.hasPermi('emr:log:remove')")
   @Log(
      title = "病历操作日志",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysEmrLogService.deleteSysEmrLogByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('emr:index:list')")
   @GetMapping({"/emrLogList"})
   public AjaxResult emrLogList(SysEmrLogVo sysEmrLog) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (sysEmrLog.getMrFileId() == null) {
            ajaxResult = AjaxResult.error("病历文件id不能为空");
         } else {
            List<SysEmrLog> list = this.sysEmrLogService.selectSysEmrLogByMrFileId(sysEmrLog.getMrFileId(), sysEmrLog.getOptTypeList());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询病历操作记录出现异常", e);
         ajaxResult = AjaxResult.error("查询病历操作记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:save')")
   @PostMapping({"/insertIndexLog"})
   public AjaxResult insertIndexLog(@RequestBody InsertIndexLogVo vo, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Long id = vo.getId();
      if (id == null) {
         ajaxResult = AjaxResult.success("病历id不能为空");
         return ajaxResult;
      } else if (StringUtils.isEmpty(vo.getTypeCode())) {
         ajaxResult = AjaxResult.success("操作类型不能为空！");
         return ajaxResult;
      } else {
         try {
            this.sysEmrLogService.insertSysLog(vo, IPAddressUtil.getIPAddress(httpServletRequest));
         } catch (Exception var6) {
            ajaxResult = AjaxResult.success("保存日志出现异常");
         }

         return ajaxResult;
      }
   }
}
