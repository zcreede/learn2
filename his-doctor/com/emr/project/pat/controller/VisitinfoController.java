package com.emr.project.pat.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.CriticalVo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.PatientInfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckedVo;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webEditor.zb.util.WeekUtil;
import com.emr.project.webEditor.zb.vo.DayVo;
import com.emr.project.webEditor.zb.vo.TimeLineDataVo;
import com.emr.project.webEditor.zb.vo.WeeklyVo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping({"pat/visitinfo"})
public class VisitinfoController extends BaseController {
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private IPersonalinfoService PersonalinfoService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Visitinfo visitinfo) {
      List<Visitinfo> list = null;

      try {
         this.startPage();
         SysUser user = SecurityUtils.getLoginUser().getUser();
         visitinfo.setDeptCd(user.getDept().getDeptCode());
         list = this.visitinfoService.selectVisitinfoList(visitinfo);
      } catch (Exception e) {
         this.log.error("查询患者就诊信息列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:emrPrintList,pat:visitinfo:emrAllList')")
   @GetMapping({"/emrPrintList"})
   public TableDataInfo emrPrintList(VisitinfoPersonalVo visitinfoPersonalVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<VisitinfoPersonalVo> list = this.visitinfoService.selectVisitinfoPersonalList(visitinfoPersonalVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历打印、查看列表出现异常,", e);
         tableDataInfo = new TableDataInfo(500, "查询病历打印、查看列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/{patientId}"})
   public AjaxResult getInfo(@PathVariable("patientId") String patientId) {
      VisitinfoVo visitinfoVo = new VisitinfoVo();

      try {
         visitinfoVo = this.visitinfoService.selectVisitinfoById(patientId);
      } catch (Exception var4) {
         this.log.error("获取患者就诊信息详细信息异常");
      }

      return AjaxResult.success((Object)visitinfoVo);
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:add')")
   @Log(
      title = "患者就诊信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Visitinfo visitinfo) {
      return this.toAjax(this.visitinfoService.insertVisitinfo(visitinfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:edit')")
   @Log(
      title = "患者就诊信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editPatVisitInfo"})
   public AjaxResult edit(@RequestBody VisitinfoVo visitinfo, @RequestBody Personalinfo personalinfo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(visitinfo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), visitinfo.getPatientId()) : null;
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能再编辑患者信息");
         }

         if (flag) {
            this.visitinfoService.updateVisitAndPersonInfo(visitinfo, personalinfo);
         }
      } catch (Exception e) {
         this.log.error("修改患者就诊信息出现异常", e);
         ajaxResult = AjaxResult.error("修改患者就诊信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:remove')")
   @Log(
      title = "患者就诊信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{patientIds}"})
   public AjaxResult remove(@PathVariable String[] patientIds) {
      return this.toAjax(this.visitinfoService.deleteVisitinfoByIds(patientIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:allList,handover:main:info,query:systemQuery:inHospitalizedTableList')")
   @GetMapping({"/browsList"})
   public TableDataInfo selectLeftBrowsList(VisitinfoVo visitinfoVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      new ArrayList(1);
      String dictStr = "";

      try {
         this.startPage();
         List list = this.visitinfoService.selectVisitinfoVoList(visitinfoVo);
         TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
         tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
         tdPaItemDocQuery.setOrderFlag("8");
         List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
         PageDomain pageDomain = TableSupport.buildPageRequest();
         Integer pageNum = pageDomain.getPageNum();
         Integer pageSize = pageDomain.getPageSize();
         if (list != null && !list.isEmpty()) {
            if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
               tableDataInfo = this.getDataTable(list);
            } else {
               tableDataInfo = this.getDataTable(list);
            }
         }

         tableDataInfo.setResult(queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
         if (visitinfoVo.getBrowsType().equals("3")) {
            String orgCode = SecurityUtils.getLoginUser().getUser().getDept().getOrgCode();
            String userCode = SecurityUtils.getUsername();
            String orderFlag = "10-" + visitinfoVo.getBrowsType();
            TdPaItemDocQuery itemDocQuery = new TdPaItemDocQuery(orderFlag, orgCode, userCode);
            List<TdPaItemDocQuery> itemDocQueryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(itemDocQuery);
            Map<String, Object> queryMap = new HashMap(1);
            queryMap.put("outOrder", CollectionUtils.isNotEmpty(itemDocQueryList) ? ((TdPaItemDocQuery)itemDocQueryList.get(0)).getQueryStatus() : null);
            tableDataInfo.setObject(queryMap);
         }
      } catch (Exception e) {
         this.log.error("查询患者浏览左侧列表出现异常,", e);
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfoVo:syncPat')")
   @GetMapping({"/syncPat"})
   public AjaxResult syncPat() {
      AjaxResult ajaxResult = AjaxResult.success("同步成功");

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String deptCode = user.getDept().getDeptCode();
         Date currentDate = new Date();
         String begTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currentDate) + " 00:00:00";
         Date nextDate = DateUtils.addDays(currentDate, 1);
         String endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, nextDate) + " 00:00:00";
         new SqlVo(deptCode, begTimeStr, endTimeStr);
      } catch (Exception e) {
         this.log.error("同步患者出现异常：", e);
         ajaxResult = AjaxResult.error("同步患者出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:colorplan,qc:rt:check,qc:rt:checked')")
   @GetMapping({"/colorplan"})
   public AjaxResult selectColorplan() {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         Map<String, List<VisitinfoVo>> mapList = this.visitinfoService.selectColorplan();
         ajaxResult = AjaxResult.success((Object)mapList);
      } catch (Exception var3) {
         this.log.error("床头卡配色查询出现异常");
         ajaxResult = AjaxResult.error("床头卡配色查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:patientGroupList')")
   @GetMapping({"/patientGroupList"})
   public TableDataInfo patientGroupList(VisitinfoVo visitinfoVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<VisitinfoVo> patientGroupList = this.visitinfoService.selectPatientGroupList(visitinfoVo);
         tableDataInfo = this.getDataTable(patientGroupList);
      } catch (Exception e) {
         this.log.error("查询医疗组患者信息出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询医疗组患者信息出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:timeAxis,pat:examItem:result,qc:flow:term,pat:info:emrAllList')")
   @GetMapping({"/timeAxis/{patientId}"})
   public AjaxResult inspectReportTimes(@PathVariable String patientId) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         VisitinfoVo nowVisit = this.visitinfoService.selectvistDiagInfo(patientId);
         List<VisitinfoVo> oldVisitList = this.visitinfoService.inspectReportTimes(patientId);
         ajaxResult = AjaxResult.success("查询成功");
         ajaxResult.put("nowVisit", nowVisit);
         ajaxResult.put("oldVisitList", oldVisitList);
      } catch (Exception e) {
         this.log.error("查询检查检验时间轴出现异常", e);
         ajaxResult = AjaxResult.error("查询检查检验时间轴出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:personalList')")
   @GetMapping({"/personalList"})
   public TableDataInfo personalList(VisitinfoPersonalVo visitinfoPersonalVo) {
      List<VisitinfoPersonalVo> list = null;

      try {
         this.startPage();
         list = this.visitinfoService.selectVisitinfoPersonalList(visitinfoPersonalVo);
      } catch (Exception e) {
         this.log.error("查询患者就诊信息列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:getSevenDayInfo')")
   @GetMapping({"/getSevenDayInfo"})
   public AjaxResult getSevenDayInfo(String patientId) {
      AjaxResult ajaxResult = new AjaxResult();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("就诊id不能为空");
         }

         Visitinfo visitinfo = null;
         if (flag) {
            visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
            if (visitinfo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个患者的就诊信息");
            }
         }

         if (flag) {
            ajaxResult = AjaxResult.success((Object)this.visitinfoService.selectSevenDayInfo(visitinfo));
         }
      } catch (Exception e) {
         this.log.error("查询诊疗时间轴出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:getTimeInfo')")
   @GetMapping({"/getTimeInfo"})
   public AjaxResult getTimeInfo(VisitinfoPersonalVo visitinfoPersonalVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(visitinfoPersonalVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("就诊id不能为空");
         }

         if (flag && StringUtils.isBlank(visitinfoPersonalVo.getStartTime())) {
            flag = false;
            ajaxResult = AjaxResult.error("周次开始日期不能为空");
         }

         if (flag && StringUtils.isBlank(visitinfoPersonalVo.getEndTime())) {
            flag = false;
            ajaxResult = AjaxResult.error("周次结束日期不能为空");
         }

         Visitinfo visitinfo = null;
         if (flag) {
            visitinfo = this.visitinfoService.selectVisitinfoById(visitinfoPersonalVo.getPatientId());
            if (visitinfo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个患者的就诊信息");
            }
         }

         if (flag) {
            List<WeeklyVo> weeklyVoList = WeekUtil.getWeekList(visitinfoPersonalVo.getStartTime(), visitinfoPersonalVo.getEndTime());
            List<DayVo> dayVoList = ((WeeklyVo)weeklyVoList.get(0)).getDayVoList();
            String startTimeStr = visitinfoPersonalVo.getStartTime() + " 00:00:00";
            String endTimeStr = visitinfoPersonalVo.getEndTime();
            Date endTime = DateUtils.addDays(DateUtils.parseDate(endTimeStr + " 00:00:00", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS}), 1);
            endTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime);
            TimeLineDataVo timeLineDataVo = this.visitinfoService.selectTimeInfo(startTimeStr, endTimeStr, dayVoList, visitinfo);
            ajaxResult = AjaxResult.success((Object)timeLineDataVo);
            ajaxResult.put("dayVoList", dayVoList);
         }
      } catch (Exception e) {
         this.log.error("查询诊疗时间轴出现异常", e);
         ajaxResult = AjaxResult.error("查询诊疗时间轴出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:employee:getUperDoct, emr:index:add')")
   @GetMapping({"/getUperDoct"})
   public AjaxResult getUperDoct(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && (org.apache.commons.lang3.StringUtils.isEmpty(patientId) || org.apache.commons.lang3.StringUtils.isBlank(patientId))) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Visitinfo visitinfo = null;
         if (flag) {
            visitinfo = this.visitinfoService.selectVisitinfoById(patientId);
            if (visitinfo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个患者的就诊信息");
            }
         }

         if (flag) {
            BasEmployee employee = this.visitinfoService.getUperDoct(patientId, visitinfo);
            ajaxResult = AjaxResult.success((Object)employee);
         }
      } catch (Exception e) {
         this.log.error("查询当前登录账号的上级医师：", e);
         ajaxResult = AjaxResult.error("查询当前登录账号的上级医师出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:personList,emr:appl:add')")
   @GetMapping({"/personList"})
   public AjaxResult personList(VisitinfoVo visitinfoVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<VisitinfoVo> list = this.visitinfoService.selectResDocPersonList(sysUser.getUserName());
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询当前登录账号的管辖患者下拉：", e);
         ajaxResult = AjaxResult.error("查询当前登录账号的管辖患者下拉，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:checkpatientList')")
   @GetMapping({"/rtQcCheckPatient"})
   public TableDataInfo runTimeQcCheckPatient(RunTimeQcCheckVo runTimeQcCheckVo) {
      TableDataInfo tableDataInfo = null;
      this.startPage();
      List<RunTimeQcCheckVo> list = new ArrayList(1);
      Integer checkedNum = 0;

      try {
         if (StringUtils.isNotBlank(runTimeQcCheckVo.getBeginDateTime())) {
            runTimeQcCheckVo.setBeginDateTime(runTimeQcCheckVo.getBeginDateTime() + " 00:00:00");
         }

         if (StringUtils.isNotBlank(runTimeQcCheckVo.getEndDateTime())) {
            String endTimeStr = runTimeQcCheckVo.getEndDateTime();
            Date endTime = DateUtils.parseDate(endTimeStr, new String[]{DateUtils.YYYY_MM_DD});
            endTime = DateUtils.addDays(endTime, 1);
            runTimeQcCheckVo.setEndDateTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime));
         }

         runTimeQcCheckVo.setCostSum(runTimeQcCheckVo.getCostSum() != null ? runTimeQcCheckVo.getCostSum() * (double)10000.0F : null);
         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         if (runTimeQcCheckVo.getDayNum() == null) {
            runTimeQcCheckVo.setDayNum(Integer.parseInt(inhosDayLong));
         }

         if (runTimeQcCheckVo.getCostSum() == null) {
            runTimeQcCheckVo.setCostSum(Double.parseDouble(costSumHigh));
         }

         runTimeQcCheckVo.setCheckNumFlag(1);
         list = this.visitinfoService.selectRunTimeQcCheckVo(runTimeQcCheckVo);
         checkedNum = this.visitinfoService.selectRunTimeQcCheckCount(runTimeQcCheckVo);
         checkedNum = checkedNum == null ? 0 : checkedNum;
      } catch (Exception e) {
         this.log.error("患者病历抽查样本查询出现异常, ", e);
      }

      tableDataInfo = this.getDataTable(list);
      Map<String, Object> checkNumMap = new HashMap();
      checkNumMap.put("checkedNum", checkedNum);
      Long noCheckNum = tableDataInfo.getTotal() - (long)checkedNum;
      checkNumMap.put("noCheckNum", noCheckNum);
      tableDataInfo.setObject(checkNumMap);
      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:checkedpatientList')")
   @GetMapping({"/rtQcCheckedPatient"})
   public TableDataInfo runTimeQcCheckedPatient(RunTimeQcCheckedVo runTimeQcCheckedVo) {
      List<RunTimeQcCheckedVo> list = null;

      try {
         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         runTimeQcCheckedVo.setDayNum(Integer.parseInt(inhosDayLong));
         runTimeQcCheckedVo.setCostSum(Double.parseDouble(costSumHigh));
         this.startPage();
         list = this.emrQcRecordService.selectRunTimeQcChecked(runTimeQcCheckedVo);
      } catch (Exception e) {
         this.log.error("患者病历抽查样本查询出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/patNumber"})
   public AjaxResult patNumber() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         VisitinfoVo visitinfoVo = this.visitinfoService.selectResDocPatNumber();
         VisitinfoVo visitinfoVo1 = this.visitinfoService.selectGroupPatNumber();
         VisitinfoVo visitinfoVo2 = this.visitinfoService.selectDeptPatNumber();
         ajaxResult.put("resDoc", visitinfoVo);
         ajaxResult.put("group", visitinfoVo1);
         ajaxResult.put("dept", visitinfoVo2);
      } catch (Exception e) {
         this.log.error("管辖患者数量查询出现异常, ", e);
         ajaxResult = AjaxResult.error("管辖患者数量查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:allList')")
   @GetMapping({"/isPrivacyLevel"})
   public AjaxResult isPrivacyLevel(Visitinfo visitinfo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isEmpty(visitinfo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            Boolean flag = this.visitinfoService.selectIsPrivacyLevel(visitinfo.getPatientId());
            if (!flag) {
               ajaxResult = AjaxResult.error("该患者非当前用户管辖患者，请依照病历使用规定规范使用患者病历资料!");
            }

            ajaxResult.put("flag", flag);
         }
      } catch (Exception e) {
         this.log.error("病历访问权限判断出现异常, ", e);
         ajaxResult = AjaxResult.error("病历访问权限判断出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:info')")
   @GetMapping({"/inHosPersonList"})
   public TableDataInfo inHosPersonList(VisitinfoVo visitinfoVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<VisitinfoVo> list = this.visitinfoService.selectInHosPersonList(visitinfoVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询在院患者列表出现异常,", e);
         tableDataInfo = new TableDataInfo(500, "查询在院患者列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('emr:home:list')")
   @GetMapping({"/inhosMedicalData"})
   public AjaxResult inhosMedicalData() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         VisitinfoVo vo = this.visitinfoService.selectInhosMedicalData();
         ajaxResult = AjaxResult.success((Object)vo);
      } catch (Exception e) {
         this.log.error("查询首页住院医疗数据出现异常,", e);
         ajaxResult = AjaxResult.error(500, "查询首页住院医疗数据出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/homeBacklog"})
   public AjaxResult homeBacklog(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = this.visitinfoService.selectHomeBacklogList(patientId);
      } catch (Exception e) {
         this.log.error("首页待办事项查询出现异常：", e);
         ajaxResult = AjaxResult.error(500, "首页待办事项查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/syncPatData"})
   public AjaxResult syncPatData() {
      AjaxResult ajaxResult = AjaxResult.success("同步成功");
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:patInfo,operation:plan:list')")
   @GetMapping({"/selectPatInfoByOpe"})
   public TableDataInfo selectPatInfoByOpe(OperRoomVisitinfoVo visitinfoVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<OperRoomVisitinfoVo> list = this.visitinfoService.selectPatInfoByOpeList(visitinfoVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询患者信息异常--手术室：", e);
         tableDataInfo = new TableDataInfo(500, "查询患者信息异常--手术室");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:patInfo,query:operation:account:operationPatient,query:operation:refundAccount:operationPatient,docOrder:doctorder:checkPatList,docOrder:doctorder:performPatList,docOrder:orderList:patList')")
   @GetMapping({"/selectPatientInfoByOpe"})
   public AjaxResult selectPatientInfoByOpe(OperRoomVisitinfoVo visitinfoVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;
      if (flag && visitinfoVo == null) {
         flag = false;
         ajaxResult = AjaxResult.error("查询条件不能为空");
      }

      if (flag && StringUtils.isBlank(visitinfoVo.getPatientId())) {
         flag = false;
         ajaxResult = AjaxResult.error("患者住院号不能为空");
      }

      if (flag) {
         try {
            PatientInfoVo patientInfoVo = this.visitinfoService.selectPatientInfoByOpeList(visitinfoVo);
            ajaxResult.put("data", patientInfoVo);
         } catch (Exception e) {
            this.log.error("查询患者信息异常--手术室：", e);
            ajaxResult = AjaxResult.error(500, "查询患者信息异常--手术室");
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:patInfo')")
   @GetMapping({"/getQueryType"})
   public AjaxResult getQueryType() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String result = this.visitinfoService.selectOperRoomQueryType();
         ajaxResult.put("queryType", result);
      } catch (Exception e) {
         this.log.error("查询患者信息异常--手术室：", e);
         ajaxResult = AjaxResult.error("查询患者信息异常--手术室");
      }

      return ajaxResult;
   }

   @GetMapping({"/getCriticalInfo"})
   public AjaxResult getCriticalInfo(String inpNo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isEmpty(inpNo)) {
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         SqlVo sqlVo = new SqlVo();
         sqlVo.setInpNo(inpNo);
         List<CriticalVo> result = this.visitinfoService.selectCriticalInfo(sqlVo);
         ajaxResult.put("criticalInfo", result);
      } catch (Exception e) {
         this.log.error("查询患者重症信息异常：", e);
         ajaxResult = AjaxResult.error("查询患者重症信息异常");
      }

      return ajaxResult;
   }
}
