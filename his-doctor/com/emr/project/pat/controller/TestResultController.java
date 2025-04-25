package com.emr.project.pat.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.pat.domain.TestResult;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertResultVo;
import com.emr.project.pat.domain.vo.TestResultVo;
import com.emr.project.pat.service.ITestExamAlertResultService;
import com.emr.project.pat.service.ITestReportService;
import com.emr.project.pat.service.ITestResultService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
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
@RequestMapping({"/pat/result"})
public class TestResultController extends BaseController {
   @Autowired
   private ITestResultService testResultService;
   @Autowired
   private ITestReportService testReportService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private ITestExamAlertResultService testExamAlertResultService;
   @Autowired
   private IHisSyncService hisSyncService;

   @PreAuthorize("@ss.hasAnyPermi('pat:result:list,emr:index:helper')")
   @GetMapping({"/list"})
   public AjaxResult list(TestResult testResult) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(testResult.getReportId())) {
            ajaxResult = AjaxResult.error("报告id不能为空");
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTestResult.toString());
            if (orderFlag.equals("1")) {
               SqlVo sqlVo = new SqlVo();
               sqlVo.setSqlStr(syncDatasource.getQuerySql());
               sqlVo.setReportId(testResult.getReportId());
               List<TestResult> list = this.testResultService.selectHisResultList(sqlVo);
               ajaxResult = AjaxResult.success((Object)list);
            } else {
               List<TestResult> list = this.testResultService.selectTestResultList(testResult);
               ajaxResult = AjaxResult.success((Object)list);
            }
         }
      } catch (Exception e) {
         this.log.error("查询检验结果列表出现异常", e);
         ajaxResult = AjaxResult.error("查询检验结果列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:result:getInfo')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") String id) {
      return AjaxResult.success((Object)this.testResultService.selectTestResultById(id));
   }

   @PreAuthorize("@ss.hasPermi('pat:result:add')")
   @Log(
      title = "检验结果",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TestResult testResult) {
      return this.toAjax(this.testResultService.insertTestResult(testResult));
   }

   @PreAuthorize("@ss.hasPermi('pat:result:edit')")
   @Log(
      title = "检验结果",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TestResult testResult) {
      return this.toAjax(this.testResultService.updateTestResult(testResult));
   }

   @PreAuthorize("@ss.hasPermi('pat:result:remove')")
   @Log(
      title = "检验结果",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable String[] ids) {
      return this.toAjax(this.testResultService.deleteTestResultByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('pat:report:resultQuery')")
   @GetMapping({"/analysisResultList/{patientIds}/{testAim}"})
   public AjaxResult analysisResultList(@PathVariable("patientIds") String[] patientIds, @PathVariable("testAim") String clinItemCd) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<Map<String, Object>> mapList = this.testResultService.analysisResultList(patientIds, clinItemCd);
         List<String> dateList = this.testResultService.selectResultDateList(patientIds, clinItemCd);
         ajaxResult.put("resultList", mapList);
         ajaxResult.put("dateList", dateList);
      } catch (Exception e) {
         this.log.error("查询检验报告结果列表出现异常", e);
         ajaxResult = AjaxResult.error("查询检验报告结果列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:report:list,docOrder:check:list')")
   @GetMapping({"/orderCheckResult"})
   public AjaxResult orderCheckResult(TestResultVo testResultVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(testResultVo.getAppCd())) {
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         } else {
            List<TestResultVo> list = this.testResultService.selectOrderCheckResult(testResultVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询检查检验录入界面检验报告出现异常", e);
         ajaxResult = AjaxResult.error("查询检查检验录入界面检验报告出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:result:list,emr:index:helper')")
   @GetMapping({"/alertList"})
   public TableDataInfo alertList(ExamItemVo examItemVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         Date startDate = examItemVo.getStartDate();
         Date endDate = examItemVo.getEndDate();
         if (startDate != null && endDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(endDate);
            calendar.add(5, 1);
            endDate = calendar.getTime();
            examItemVo.setStartDate(startDate);
            examItemVo.setEndDate(endDate);
         }

         if (StringUtils.isEmpty(examItemVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者就诊id不能为空");
         } else {
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            this.startPage();
            if (orderFlag.equals("1")) {
               List<TestAlertResultVo> list = this.testResultService.selectTestAertList(examItemVo);
               if (!list.isEmpty()) {
                  for(TestAlertResultVo vo : list) {
                     String handleDocName = vo.getHandleDocName();
                     if (StringUtils.isNotEmpty(handleDocName)) {
                        Map<String, Object> staffMap = this.hisSyncService.selectStaffByCode(handleDocName);
                        if (staffMap != null && staffMap.get("xm") != null) {
                           vo.setHandleDocName(staffMap.get("xm").toString());
                        }
                     }
                  }
               }

               tableDataInfo = this.getDataTable(list);
            } else {
               List<TestAlertResultVo> list = this.testExamAlertResultService.selectExamaLertList(examItemVo);
               tableDataInfo = this.getDataTable(list);
            }
         }
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询检验报告危急值列表出现异常，请联系管理员");
         this.log.error("查询检验报告危急值列表出现异常", e);
      }

      return tableDataInfo;
   }
}
