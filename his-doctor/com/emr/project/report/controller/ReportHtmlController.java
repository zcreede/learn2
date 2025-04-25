package com.emr.project.report.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.report.domain.vo.FeeItemVo;
import com.emr.project.report.domain.vo.FeeItemVoParam;
import com.emr.project.report.domain.vo.FeeWorkloadVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVo;
import com.emr.project.report.domain.vo.PatientInLeaveInfoVoParam;
import com.emr.project.report.service.IReportHtmlService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/reportHtml"})
public class ReportHtmlController extends BaseController {
   private static Logger log = LoggerFactory.getLogger(ReportHtmlController.class);
   @Autowired
   private IReportHtmlService reportHtmlService;

   @PreAuthorize("@ss.hasAnyPermi('reportHtml:patient:list')")
   @PostMapping({"/queryPatient"})
   public TableDataInfo queryPatient(@RequestBody PatientInLeaveInfoVoParam param) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (flag && param == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQueryType())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag) {
            if (StringUtils.isNotBlank(param.getHospitalizedDateEnd())) {
               String hospitalizedDateEnd = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(param.getHospitalizedDateEnd()), 1));
               param.setHospitalizedDateEnd(hospitalizedDateEnd);
            }

            this.startPage();
            List<PatientInLeaveInfoVo> list = this.reportHtmlService.queryPatient(param);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         log.error("查询患者信息报表出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询患者信息报表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('reportHtml:patient:list')")
   @PostMapping({"/exportPatient"})
   public AjaxResult exportQueryPatient(@RequestBody PatientInLeaveInfoVoParam param) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && param == null) {
            ajaxResult = AjaxResult.error("参数不能为空");
            flag = false;
         }

         if (flag && StringUtils.isBlank(param.getQueryType())) {
            ajaxResult = AjaxResult.error("参数不能为空");
            flag = false;
         }

         if (flag) {
            if (StringUtils.isNotBlank(param.getHospitalizedDateEnd())) {
               String hospitalizedDateEnd = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(param.getHospitalizedDateEnd()), 1));
               param.setHospitalizedDateEnd(hospitalizedDateEnd);
            }

            List<PatientInLeaveInfoVo> list = this.reportHtmlService.queryPatient(param);
            ExcelUtil<PatientInLeaveInfoVo> util = new ExcelUtil(PatientInLeaveInfoVo.class);
            ajaxResult = util.exportExcel(list, "患者出入院信息");
         }
      } catch (Exception e) {
         log.error("导出患者信息报表出现异常：", e);
         ajaxResult = AjaxResult.error("导出患者信息报表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('reportHtml:feeItem:list')")
   @GetMapping({"/queryFeeItem"})
   public TableDataInfo queryFeeItem(FeeItemVoParam param) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (flag && param == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQueryType())) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (flag) {
            if (StringUtils.isNotBlank(param.getEndDateTime())) {
               String endDateTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(param.getEndDateTime()), 1));
               param.setEndDateTime(endDateTime);
            }

            this.startPage();
            List<FeeItemVo> list = this.reportHtmlService.queryFeeItemList(param);
            tableDataInfo = this.getDataTable(list);
            Double total = this.reportHtmlService.queryFeeItemTotal(param);
            tableDataInfo.setResult(String.valueOf(total));
         }
      } catch (Exception e) {
         log.error("单个计费项目查询出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "单个计费项目查询出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('reportHtml:workloadFee:list')")
   @GetMapping({"/queryWorkloadFee"})
   public AjaxResult queryWorkloadFee(FeeWorkloadVo param) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQueryType())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getDepartmentType())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室类型不能为空");
         }

         if (flag && (StringUtils.isBlank(param.getBeginDateTime()) || StringUtils.isBlank(param.getEndDateTime()))) {
            flag = false;
            ajaxResult = AjaxResult.error("日期不能为空");
         }

         if (StringUtils.isNotBlank(param.getEndDateTime())) {
            String endDateTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(param.getEndDateTime()), 1));
            param.setEndDateTime(endDateTime);
         }

         if (flag) {
            ajaxResult = this.reportHtmlService.selectFeeWorkloadList(param);
         }
      } catch (Exception e) {
         log.error("查询医师费用工作量出现异常：", e);
         ajaxResult = AjaxResult.error("查询医师费用工作量出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('reportHtml:workloadFee:list')")
   @PostMapping({"/feeWorkloadExcel"})
   public AjaxResult feeWorkloadExcel(@RequestBody FeeWorkloadVo param, HttpServletResponse response) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && param == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(param.getQueryType())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (StringUtils.isBlank(param.getBeginDateTime()) || StringUtils.isBlank(param.getEndDateTime()))) {
            flag = false;
            ajaxResult = AjaxResult.error("日期不能为空");
         }

         if (flag) {
            if (StringUtils.isNotBlank(param.getEndDateTime())) {
               String endDateTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, DateUtils.addDays(DateUtils.parseDate(param.getEndDateTime()), 1));
               param.setEndDateTime(endDateTime);
            }

            return this.reportHtmlService.feeWorkloadListExcel(param, response);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出医师费用工作量出现异常");
         log.error("导出医师费用工作量出现异常，", e);
      }

      return ajaxResult;
   }
}
