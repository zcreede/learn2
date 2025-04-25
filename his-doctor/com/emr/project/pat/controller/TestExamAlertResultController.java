package com.emr.project.pat.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.mapper.CommonMapper;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.pat.domain.TestExamAlertResult;
import com.emr.project.pat.domain.vo.ExamAlertResultVo;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestAlertReportVo;
import com.emr.project.pat.domain.vo.TestAlertResultVo;
import com.emr.project.pat.domain.vo.TestExamAlertMsgVo;
import com.emr.project.pat.service.ITestExamAlertResultService;
import com.emr.project.qc.domain.PopTime;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.sys.domain.TmBsDefineConfigureP;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.service.ITmplIndexService;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping({"/pat/alertResult"})
public class TestExamAlertResultController extends BaseController {
   @Autowired
   private ITestExamAlertResultService testExamAlertResultService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Autowired
   private CommonMapper commonMapper;

   @PreAuthorize("@ss.hasAnyPermi('pat:result:testlist, pat:result:list')")
   @GetMapping({"/testlist"})
   public TableDataInfo testResultlist(TestAlertReportVo testAlertReportVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         TmBsDefineConfigureP overtimeConfigure = this.commonService.selectDefineConfigureByCode("00010010");
         String overtime = null;
         if (overtimeConfigure != null && overtimeConfigure.getDefaultValue() != null) {
            testAlertReportVo.setOvertimeConfigure(Integer.valueOf(overtimeConfigure.getDefaultValue()));
            overtime = overtimeConfigure.getDefaultValue();
         } else {
            testAlertReportVo.setOvertimeConfigure(0);
         }

         if (StringUtils.isNotBlank(testAlertReportVo.getAlertdtEnd())) {
            Date alertdtEnd = DateUtils.parseDate(testAlertReportVo.getAlertdtEnd(), new String[]{DateUtils.YYYY_MM_DD});
            Date alertdtEndNew = DateUtils.addDays(alertdtEnd, 1);
            testAlertReportVo.setAlertdtEnd(DateUtils.formatYMD(alertdtEndNew));
         }

         this.startPage();
         List<TestAlertResultVo> list = this.testExamAlertResultService.testResultlist(testAlertReportVo, overtime);
         tableDataInfo = this.getDataTable(list);
         Map<String, Object> resMap = new HashMap(1);
         resMap.put("overtimeConfigure", overtime);
         tableDataInfo.setObject(resMap);
      } catch (Exception e) {
         this.log.error("查询检验危急值列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询检验危急值列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:result:examlist, pat:result:list')")
   @GetMapping({"/examlist"})
   public TableDataInfo examResultlist(ExamItemVo examItemVo) {
      new TableDataInfo();
      if (StringUtils.isNotBlank(examItemVo.getExamRepDateEnd())) {
         examItemVo.setExamRepDateEnd(DateUtils.addDay((String)examItemVo.getExamRepDateEnd(), 1));
      }

      TableDataInfo tableDataInfo;
      try {
         TmBsDefineConfigureP overtimeConfigure = this.commonService.selectDefineConfigureByCode("00010010");
         String overtime = null;
         if (overtimeConfigure != null && overtimeConfigure.getDefaultValue() != null) {
            examItemVo.setOvertimeConfigure(Integer.valueOf(overtimeConfigure.getDefaultValue()));
            overtime = overtimeConfigure.getDefaultValue();
         } else {
            examItemVo.setOvertimeConfigure(0);
         }

         this.startPage();
         List<ExamAlertResultVo> list = this.testExamAlertResultService.examResultlist(examItemVo, overtime);
         tableDataInfo = this.getDataTable(list);
         Map<String, Object> resMap = new HashMap(1);
         resMap.put("overtimeConfigure", overtime);
         tableDataInfo.setObject(resMap);
      } catch (Exception e) {
         this.log.error("查询检查危急值列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询检查危急值列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:result:exportTest, pat:result:testlist, pat:result:list')")
   @Log(
      title = "导出危急值列表",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/exportTest"})
   public AjaxResult exportTest(TestAlertReportVo testAlertReportVo) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功！");

      try {
         TmBsDefineConfigureP overtimeConfigure = this.commonService.selectDefineConfigureByCode("00010010");
         String overtime = null;
         if (overtimeConfigure != null && overtimeConfigure.getDefaultValue() != null) {
            testAlertReportVo.setOvertimeConfigure(Integer.valueOf(overtimeConfigure.getDefaultValue()));
            overtime = overtimeConfigure.getDefaultValue();
         } else {
            testAlertReportVo.setOvertimeConfigure(0);
         }

         if (StringUtils.isNotBlank(testAlertReportVo.getAlertdtEnd())) {
            Date alertdtEnd = DateUtils.parseDate(testAlertReportVo.getAlertdtEnd(), new String[]{DateUtils.YYYY_MM_DD});
            Date alertdtEndNew = DateUtils.addDays(alertdtEnd, 1);
            testAlertReportVo.setAlertdtEnd(DateUtils.formatYMD(alertdtEndNew));
         }

         ExcelUtil<TestAlertResultVo> util = new ExcelUtil(TestAlertResultVo.class);
         List<TestAlertResultVo> list = this.testExamAlertResultService.testResultlist(testAlertReportVo, overtime);
         ajaxResult = util.exportExcel(list, "检验危急值");
      } catch (Exception e) {
         this.log.error("导出出现异常，", e);
         ajaxResult = AjaxResult.error("导出出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:result:exportTest, pat:result:examlist, pat:result:list')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/exportExam"})
   public AjaxResult exportExam(ExamItemVo examItemVo) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功！");

      try {
         TmBsDefineConfigureP overtimeConfigure = this.commonService.selectDefineConfigureByCode("00010010");
         String overtime = null;
         if (overtimeConfigure != null && overtimeConfigure.getDefaultValue() != null) {
            examItemVo.setOvertimeConfigure(Integer.valueOf(overtimeConfigure.getDefaultValue()));
            overtime = overtimeConfigure.getDefaultValue();
         } else {
            examItemVo.setOvertimeConfigure(0);
         }

         ExcelUtil<ExamAlertResultVo> util = new ExcelUtil(ExamAlertResultVo.class);
         List<ExamAlertResultVo> list = this.testExamAlertResultService.examResultlist(examItemVo, overtime);
         ajaxResult = util.exportExcel(list, "检查危急值");
      } catch (Exception e) {
         this.log.error("导出出现异常，", e);
         ajaxResult = AjaxResult.error("导出出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:alertResult:add')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TestExamAlertMsgVo alertMsgVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增危急值处理措施成功！");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(alertMsgVo.getId())) {
            flag = false;
            ajaxResult = AjaxResult.error("危急值id不能为空！");
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         TestExamAlertResult result = flag ? this.testExamAlertResultService.selectTestExamAlertResultByReportId(alertMsgVo.getId()) : null;
         if (flag && result != null && StringUtils.isNotBlank(result.getHandleDocContent())) {
            flag = false;
            ajaxResult = AjaxResult.error("此条危急值已经被处理，不能再次处理！");
            ajaxResult.put("closeFlag", "true");
            String lisPacsAlertKey = "lis_pacs_alert_key:" + sysUser.getUserName();
            List<TestExamAlertMsgVo> testExamAlertMsgList = this.redisCache.getCacheList(lisPacsAlertKey);
            if (CollectionUtils.isNotEmpty(testExamAlertMsgList)) {
               testExamAlertMsgList = (List)testExamAlertMsgList.stream().filter((t) -> !t.getId().equals(result.getReportId())).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(testExamAlertMsgList)) {
                  this.redisCache.setCacheList(lisPacsAlertKey, testExamAlertMsgList);
               } else {
                  this.redisCache.deleteObject(lisPacsAlertKey);
               }
            }

            ajaxResult.put("testExamAlertList", testExamAlertMsgList);
            boolean bolDoct = testExamAlertMsgList.stream().anyMatch((testExamAlert) -> sysUser.getUserName().equals(testExamAlert.getResDocCd()));
            if (bolDoct) {
               bolDoct = this.queryPop();
            }

            ajaxResult.put("bolDoct", bolDoct);
         }

         if (flag && StringUtils.isBlank(alertMsgVo.getConfirmInfo())) {
            flag = false;
            ajaxResult = AjaxResult.error("处理措施不能为空！");
         }

         if (flag) {
            TestExamAlertResult testExamAlertResult = this.testExamAlertResultService.insertTestExamAlertResult(alertMsgVo, result);
            ajaxResult.put("resultId", testExamAlertResult.getId());
            String mesId = alertMsgVo.getId();
            String messageKey = "message_key:" + sysUser.getUserName();
            List<EmrMessageVo> allMsglist = this.redisCache.getCacheList(messageKey);
            if (CollectionUtils.isNotEmpty(allMsglist)) {
               List<EmrMessageVo> currMsgList = (List)allMsglist.stream().filter((t) -> StringUtils.isNotBlank(t.getBusId()) && t.getBusId().equals(mesId)).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(currMsgList)) {
                  EmrMessageVo currMsg = (EmrMessageVo)currMsgList.get(0);
                  currMsg.setMsgState(1);
                  currMsgList = (List)allMsglist.stream().filter((t) -> StringUtils.isNotBlank(t.getBusId()) && !t.getBusId().equals(mesId)).collect(Collectors.toList());
                  currMsgList.add(currMsg);
                  this.redisCache.deleteObject(messageKey);
                  this.redisCache.setCacheList(messageKey, currMsgList);
               } else {
                  this.redisCache.deleteObject(messageKey);
               }
            }

            String lisPacsAlertKey = "lis_pacs_alert_key:" + sysUser.getUserName();
            List<TestExamAlertMsgVo> testExamAlertMsgList = this.redisCache.getCacheList(lisPacsAlertKey);
            if (CollectionUtils.isNotEmpty(testExamAlertMsgList)) {
               List<TestExamAlertMsgVo> currAlertMsgList = (List)testExamAlertMsgList.stream().filter((t) -> t.getId() != null && !t.getId().equals(mesId)).collect(Collectors.toList());
               this.redisCache.deleteObject(lisPacsAlertKey);
               if (CollectionUtils.isNotEmpty(currAlertMsgList)) {
                  this.redisCache.setCacheList(lisPacsAlertKey, currAlertMsgList);
               }

               ajaxResult.put("testExamAlertList", currAlertMsgList);
               boolean bolDoct = currAlertMsgList.stream().anyMatch((testExamAlert) -> sysUser.getUserName().equals(testExamAlert.getResDocCd()));
               if (bolDoct) {
                  bolDoct = this.queryPop();
               }

               ajaxResult.put("bolDoct", bolDoct);
            }
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("新增危急值处理措施出现异常，请联系管理员！");
         this.log.error("新增危急值处理措施出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:result:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TestExamAlertResult testExamAlertResult) {
      return this.toAjax(this.testExamAlertResultService.updateTestExamAlertResult(testExamAlertResult));
   }

   @PreAuthorize("@ss.hasPermi('pat:result:remove')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.testExamAlertResultService.deleteTestExamAlertResultByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('pat:result:refMrFile')")
   @Log(
      title = "【查询待关联的病历列表】",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/getRefMrFile"})
   public AjaxResult getEmrFileList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (StringUtils.isNotBlank(patientId)) {
            List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubFileByMrType(patientId, "9");
            subfileIndexList.stream().forEach((t) -> t.setMrType("危急值病程记录"));
            ajaxResult = AjaxResult.success("查询成功", subfileIndexList);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询待关联的病历列表出现异常");
         this.log.error("查询待关联的病历列表出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:result:refMrFile')")
   @Log(
      title = "【关联病历给危急值结果】",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/doRefMrFile"})
   public AjaxResult doRefMrFile(String id, String mrFileId) {
      AjaxResult ajaxResult = AjaxResult.success("关联病历成功");
      boolean flag = true;

      try {
         TestExamAlertResult result = new TestExamAlertResult();
         if (flag && StringUtils.isBlank(id)) {
            flag = false;
            ajaxResult = AjaxResult.error("危急值处理结果id不能为空");
         }

         if (flag && StringUtils.isBlank(mrFileId)) {
            flag = false;
            ajaxResult = AjaxResult.error("危急值处理结果关联病历id不能为空");
         }

         if (flag) {
            result.setId(Long.valueOf(id));
            result.setMrFileId(Long.valueOf(mrFileId));
            this.testExamAlertResultService.updateMrFileId(result);
         }
      } catch (Exception e) {
         this.log.error("关联病历给危急值结果出现异常：", e);
         ajaxResult = AjaxResult.error("关联病历给危急值结果出现异常");
      }

      return ajaxResult;
   }

   @Log(
      title = "【给危急值结果写病历】",
      businessType = BusinessType.DELETE
   )
   @GetMapping({"/newMrFile"})
   public AjaxResult toNewEmrFile() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         String newEmrFileFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0061");
         if (StringUtils.isNotBlank(newEmrFileFlag) && newEmrFileFlag.equals("1")) {
            String newEmrFileTempId = this.sysEmrConfigService.selectSysEmrConfigByKey("006101");
            ajaxResult = AjaxResult.success("查询成功", newEmrFileTempId);
            TmplIndex tmplIndex = StringUtils.isNotBlank(newEmrFileTempId) ? this.tmplIndexService.selectTmplIndexById(Long.valueOf(newEmrFileTempId)) : null;
            if (tmplIndex != null) {
               ajaxResult.put("tempName", tmplIndex.getTempName());
            }
         }

         Boolean isQCDept = this.commonService.isQCDept();
         ajaxResult.put("isQCDept", isQCDept);
      } catch (Exception e) {
         this.log.error("危急值结果写病历查询模板出现异常：", e);
         ajaxResult = AjaxResult.error("危急值结果写病历查询模板出现异常");
      }

      return ajaxResult;
   }

   public boolean queryPop() throws Exception {
      boolean pop = false;
      String jsonStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0100");
      PopTime resp = (PopTime)JSONObject.parseObject(jsonStr, PopTime.class);
      String currDateStr = this.commonMapper.getSysdate();
      Date currDate = DateUtils.parseDate(currDateStr, new String[]{"yyyy-MM-dd HH:mm:ss.S"});
      LocalTime localDateTime = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      LocalTime amStart = LocalTime.parse(resp.getAmBeginTime().trim(), formatter);
      LocalTime amEnd = LocalTime.parse(resp.getAmEndTime().trim(), formatter);
      LocalTime pmStart = LocalTime.parse(resp.getPmBeginTime().trim(), formatter);
      LocalTime pmEnd = LocalTime.parse(resp.getPmEndTime().trim(), formatter);
      pop = localDateTime.isAfter(amStart) && localDateTime.isBefore(amEnd) || localDateTime.isAfter(pmStart) && localDateTime.isBefore(pmEnd);
      return pop;
   }
}
