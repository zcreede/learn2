package com.emr.project.qc.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.domain.vo.QcAgiRuleVo;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@RequestMapping({"/qc/agiRule"})
public class QcAgiRuleController extends BaseController {
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IEmrTaskInfoService taskInfoService;

   @PreAuthorize("@ss.hasPermi('qc:agiRule:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcAgiRuleVo qcAgiRuleVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<QcAgiRule> list = this.qcAgiRuleService.selectQcAgiRuleList(qcAgiRuleVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历时效质控规则列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历时效质控规则列表");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcAgiRuleService.selectQcAgiRuleById(id));
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:add')")
   @Log(
      title = "病历时效质控规则",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcAgiRule qcAgiRule) {
      return this.toAjax(this.qcAgiRuleService.insertQcAgiRule(qcAgiRule));
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:edit')")
   @Log(
      title = "病历时效质控规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcAgiRule qcAgiRule) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (qcAgiRule == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcAgiRule.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id参数不能为空");
         }

         if (flag) {
            ajaxResult = this.toAjax(this.qcAgiRuleService.updateQcAgiRule(qcAgiRule));
         }
      } catch (Exception e) {
         this.log.error("修改病历时效质控规则出现异常", e);
         ajaxResult = AjaxResult.error("修改病历时效质控规则出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:remove')")
   @Log(
      title = "病历时效质控规则",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcAgiRuleService.deleteQcAgiRuleByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:edit')")
   @Log(
      title = "病历时效质控规则",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editFlag"})
   public AjaxResult editFlag(@RequestBody QcAgiRule qcAgiRule) {
      AjaxResult ajaxResult = AjaxResult.success("操作成功");
      boolean flag = true;

      try {
         if (qcAgiRule == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && qcAgiRule.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag) {
            this.qcAgiRuleService.updateQcAgiRuleFlag(qcAgiRule);
         }
      } catch (Exception e) {
         this.log.error("启用禁用出现异常", e);
         ajaxResult = AjaxResult.error("启用禁用出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:edit')")
   @Log(
      title = "病历时效质控规则R001",
      businessType = BusinessType.OTHER
   )
   @GetMapping({"/genTask"})
   public AjaxResult genTask(String ruleCode) {
      this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      AjaxResult ajaxResult = AjaxResult.success("执行成功");

      try {
         this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         List<String> ruleCodeList = Arrays.asList(ruleCode.split(","));
         List<QcAgiRule> qcAgiRuleList = this.qcAgiRuleService.selectQcAgiRuleListByCode(ruleCodeList);
         String afterOutTimeDayNum = this.sysEmrConfigService.selectSysEmrConfigByKey("0024");
         List<String> eventCodeList = (List)qcAgiRuleList.stream().map((t) -> t.getAgiEvnt()).distinct().collect(Collectors.toList());
         PatEventVo eventParam = new PatEventVo();
         eventParam.setAfterOutTimeDayNum(Integer.valueOf(afterOutTimeDayNum));
         eventParam.setEventCodeList(eventCodeList);
         eventCodeList.add("03");
         eventCodeList.add("02");
         List<PatEventVo> patEventVoList = this.patEventService.selectListByEventCode(eventParam);
         Map<String, List<PatEventVo>> patEventVoListMap = (Map)patEventVoList.stream().collect(Collectors.groupingBy((t) -> t.getEventCode()));
         List<PatEventVo> patEventVoListDeadAll = (List<PatEventVo>)(patEventVoListMap.get("03") != null ? (List)patEventVoListMap.get("03") : new ArrayList(1));
         List<PatEventVo> patEventVoListOutAll = (List<PatEventVo>)(patEventVoListMap.get("02") != null ? (List)patEventVoListMap.get("02") : new ArrayList(1));
         List<Long> ruleIdList = (List)qcAgiRuleList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
         eventParam.setRuleIdList(ruleIdList);
         List<SubfileIndexVo> subfileIndexList = this.subfileIndexService.selectPatIndexList(eventParam);
         List<MrHp> mrHpList = this.mrHpService.selectPatMrHpList(eventParam);
         List<Index> indexList = this.indexService.selectPatIndexList(eventParam);
         List<EmrTaskInfo> emrTaskInfoList = this.taskInfoService.selectAgiRuleTaskList(eventParam);

         for(QcAgiRule qcAgiRule : qcAgiRuleList) {
            try {
               this.qcAgiRuleService.agiRuleTaskDoing(qcAgiRule, patEventVoList, patEventVoListMap, indexList, emrTaskInfoList, patEventVoListDeadAll, patEventVoListOutAll, subfileIndexList, mrHpList);
            } catch (Exception e) {
               this.log.error("时效质控" + ruleCode + "生成提醒任务出现异常", e);
            }
         }

         this.log.debug("========时效质控" + ruleCode + "结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      } catch (Exception e) {
         this.log.error("时效质控" + ruleCode + "出现异常：", e);
      }

      this.log.debug("========时效质控" + ruleCode + "结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:agiRule:edit')")
   @Log(
      title = "病历时效质控规则R001",
      businessType = BusinessType.OTHER
   )
   @GetMapping({"/genTask2"})
   public AjaxResult genTask2(String ruleCode, String beginTimeStr) {
      this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      AjaxResult ajaxResult = AjaxResult.success("执行成功");

      try {
         QcAgiRule qcAgiRule = this.qcAgiRuleService.selectQcAgiRuleByCode(ruleCode);
         if (qcAgiRule != null) {
            Date currDate = this.commonService.getDbSysdate();
            Date beginTime = DateUtils.addHours(currDate, -1);
            beginTime = StringUtils.isNotBlank(beginTimeStr) ? DateUtils.parseDate(beginTimeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS}) : beginTime;
            List<PatEventVo> patEventVoList = this.qcAgiRuleService.getRuleData(qcAgiRule, (String)null, beginTime);
            Map<String, List<PatEventVo>> patientIndexListMap = (Map)patEventVoList.stream().collect(Collectors.groupingBy((t) -> t.getPatientId()));

            for(String patientId : patientIndexListMap.keySet()) {
               List<PatEventVo> patEventIndexList = (List)patientIndexListMap.get(patientId);
               PatEventVo patientVo = (PatEventVo)patEventVoList.get(0);

               try {
                  this.qcAgiRuleService.agiRuleDo(qcAgiRule, patEventIndexList);
               } catch (Exception e) {
                  this.log.error("时效质控" + ruleCode + "生成提醒任务出现异常，患者id：{}，患者住院号：{}，患者姓名：{}，时间id：{}，报错信息：{}", new Object[]{patientVo.getPatientId(), patientVo.getInpNo(), patientVo.getPatientName(), patientVo.getId(), e});
                  ajaxResult = AjaxResult.error("时效质控" + ruleCode + "出现异常");
               }
            }
         }
      } catch (Exception e) {
         this.log.error("时效质控" + ruleCode + "出现异常：", e);
         ajaxResult = AjaxResult.error("时效质控" + ruleCode + "出现异常");
      }

      this.log.debug("========时效质控" + ruleCode + "结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      return ajaxResult;
   }
}
