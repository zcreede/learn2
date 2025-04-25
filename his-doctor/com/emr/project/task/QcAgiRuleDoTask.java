package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.EmrSignDataVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.qc.service.IQcAgiRuleAllService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping({"/qcAgiRule"})
public class QcAgiRuleDoTask {
   protected final Logger log = LoggerFactory.getLogger(QcAgiRuleDoTask.class);
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IQcAgiRuleAllService qcAgiRuleAllService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IEmrTaskInfoService taskInfoService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;

   public void R001Task(String ruleCode) {
      try {
         this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         List<String> ruleCodeList = Arrays.asList(ruleCode.split(";"));
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

   }

   @GetMapping({"/AgingTask"})
   public void AgingTask(String ruleCode) {
      try {
         this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         if (StringUtils.isBlank(ruleCode)) {
            ruleCode = this.sysEmrConfigService.selectSysEmrConfigByKey("0101");
         }

         new ArrayList();
         List e;
         if (StringUtils.isNotBlank(ruleCode)) {
            List<String> ruleCodeList = Arrays.asList(ruleCode.split(";"));
            e = this.qcAgiRuleService.selectQcAgiRuleListByCode(ruleCodeList);
         } else {
            e = this.qcAgiRuleService.selectQcAgiRuleListByCode((List)null);
         }

         String afterOutTimeDayNum = this.sysEmrConfigService.selectSysEmrConfigByKey("0024");
         List<String> eventCodeList = (List)e.stream().map((t) -> t.getAgiEvnt()).distinct().collect(Collectors.toList());
         PatEventVo eventParam = new PatEventVo();
         eventParam.setAfterOutTimeDayNum(Integer.valueOf(afterOutTimeDayNum));
         eventParam.setEventCodeList(eventCodeList);
         eventCodeList.add("03");
         eventCodeList.add("02");
         List<PatEventVo> patEventVoList = this.patEventService.selectListByEventCodeNew(eventParam);
         Map<String, List<PatEventVo>> patEventVoListMap = (Map)patEventVoList.stream().collect(Collectors.groupingBy((t) -> t.getEventCode()));
         List<PatEventVo> patEventVoListDeadAll = (List<PatEventVo>)(patEventVoListMap.get("03") != null ? (List)patEventVoListMap.get("03") : new ArrayList(1));
         List<PatEventVo> patEventVoListOutAll = (List<PatEventVo>)(patEventVoListMap.get("02") != null ? (List)patEventVoListMap.get("02") : new ArrayList(1));
         List<Long> ruleIdList = (List)e.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
         eventParam.setRuleIdList(ruleIdList);
         List<SubfileIndexVo> subfileIndexList = this.subfileIndexService.selectPatIndexList(eventParam);
         List<MrHp> mrHpList = this.mrHpService.selectPatMrHpNewList(eventParam);
         List<Index> indexList = this.indexService.selectPatIndexList(eventParam);
         EmrSignDataVo emrSignDataVo = new EmrSignDataVo();
         if (CollectionUtils.isNotEmpty(patEventVoList)) {
            List<String> resDocCdList = (List)patEventVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getResDocCd())).map((t) -> t.getResDocCd()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(patEventVoList)) {
               emrSignDataVo.setResDocCdList(resDocCdList);
            }
         }

         List<Long> indexAllIdList = new ArrayList();
         if (CollectionUtils.isNotEmpty(subfileIndexList)) {
            List<Long> subIdList = (List)subfileIndexList.stream().filter((t) -> t.getId() != null).map((t) -> t.getId()).collect(Collectors.toList());
            indexAllIdList.addAll(subIdList);
         }

         if (CollectionUtils.isNotEmpty(indexList)) {
            List<Long> indexIdList = (List)indexList.stream().filter((t) -> t.getId() != null).map((t) -> t.getId()).collect(Collectors.toList());
            indexAllIdList.addAll(indexIdList);
         }

         emrSignDataVo.setIdList(indexAllIdList);
         List<EmrSignData> emrSignDataList = this.emrSignDataService.selectEmrSignDataListByMrFileIds(emrSignDataVo);
         List<EmrTaskInfo> emrTaskInfoList = this.taskInfoService.selectAgiRuleTaskList(eventParam);
         List<TdCaOperationApply> tdCaOperationApplyList = new ArrayList();
         if (CollectionUtils.isNotEmpty(patEventVoList)) {
            new ArrayList();
            List admissionNos = (List)patEventVoList.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
            tdCaOperationApplyList = this.tdCaOperationApplyService.selectTdCaOperationApplyByIds(admissionNos);
         }

         for(QcAgiRule qcAgiRule : e) {
            try {
               this.qcAgiRuleAllService.agiRuleTaskCreate(qcAgiRule, patEventVoList, patEventVoListMap, indexList, emrTaskInfoList, patEventVoListDeadAll, patEventVoListOutAll, subfileIndexList, mrHpList, emrSignDataList, tdCaOperationApplyList);
            } catch (Exception e) {
               this.log.error("时效质控" + ruleCode + "生成提醒任务出现异常", e);
            }
         }

         this.log.debug("========时效质控" + ruleCode + "结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
      } catch (Exception e) {
         this.log.error("时效质控" + ruleCode + "出现异常：", e);
      }

   }

   @GetMapping({"/AgingTaskByPatientId"})
   public AjaxResult AgingTaskByPatientId(String ruleCode, String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;
      if (flag && !StringUtils.isNotBlank(patientId)) {
         flag = false;
         ajaxResult = AjaxResult.error("患者住院号未传递");
      }

      try {
         if (flag) {
            this.log.debug("========时效质控" + ruleCode + "开始执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
            ruleCode = this.sysEmrConfigService.selectSysEmrConfigByKey("0101");
            new ArrayList();
            List e;
            if (StringUtils.isNotBlank(ruleCode)) {
               List<String> ruleCodeList = Arrays.asList(ruleCode.split(","));
               e = this.qcAgiRuleService.selectQcAgiRuleListByCode(ruleCodeList);
            } else {
               e = this.qcAgiRuleService.selectQcAgiRuleListByCode((List)null);
            }

            String afterOutTimeDayNum = this.sysEmrConfigService.selectSysEmrConfigByKey("0024");
            List<String> eventCodeList = (List)e.stream().map((t) -> t.getAgiEvnt()).distinct().collect(Collectors.toList());
            PatEventVo eventParam = new PatEventVo();
            eventParam.setAfterOutTimeDayNum(Integer.valueOf(afterOutTimeDayNum));
            eventParam.setEventCodeList(eventCodeList);
            eventParam.setPatientId(patientId);
            eventCodeList.add("03");
            eventCodeList.add("02");
            List<PatEventVo> patEventVoList = this.patEventService.selectListByEventCodeNew(eventParam);
            Map<String, List<PatEventVo>> patEventVoListMap = (Map)patEventVoList.stream().collect(Collectors.groupingBy((t) -> t.getEventCode()));
            List<PatEventVo> patEventVoListDeadAll = (List<PatEventVo>)(patEventVoListMap.get("03") != null ? (List)patEventVoListMap.get("03") : new ArrayList(1));
            List<PatEventVo> patEventVoListOutAll = (List<PatEventVo>)(patEventVoListMap.get("02") != null ? (List)patEventVoListMap.get("02") : new ArrayList(1));
            List<Long> ruleIdList = (List)e.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
            eventParam.setRuleIdList(ruleIdList);
            List<SubfileIndexVo> subfileIndexList = this.subfileIndexService.selectPatIndexList(eventParam);
            List<MrHp> mrHpList = this.mrHpService.selectPatMrHpNewList(eventParam);
            List<Index> indexList = this.indexService.selectPatIndexList(eventParam);
            EmrSignDataVo emrSignDataVo = new EmrSignDataVo();
            if (CollectionUtils.isNotEmpty(patEventVoList)) {
               List<String> resDocCdList = (List)patEventVoList.stream().filter((t) -> StringUtils.isNotBlank(t.getResDocCd())).map((t) -> t.getResDocCd()).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(patEventVoList)) {
                  emrSignDataVo.setResDocCdList(resDocCdList);
               }
            }

            List<Long> indexAllIdList = new ArrayList();
            if (CollectionUtils.isNotEmpty(subfileIndexList)) {
               List<Long> subIdList = (List)subfileIndexList.stream().filter((t) -> t.getId() != null).map((t) -> t.getId()).collect(Collectors.toList());
               indexAllIdList.addAll(subIdList);
            }

            if (CollectionUtils.isNotEmpty(indexList)) {
               List<Long> indexIdList = (List)indexList.stream().filter((t) -> t.getId() != null).map((t) -> t.getId()).collect(Collectors.toList());
               indexAllIdList.addAll(indexIdList);
            }

            emrSignDataVo.setIdList(indexAllIdList);
            List<EmrSignData> emrSignDataList = new ArrayList();
            if (emrSignDataVo != null && CollectionUtils.isNotEmpty(emrSignDataVo.getIdList())) {
               emrSignDataList = this.emrSignDataService.selectEmrSignDataListByMrFileIds(emrSignDataVo);
            }

            List<EmrTaskInfo> emrTaskInfoList = this.taskInfoService.selectAgiRuleTaskList(eventParam);
            List<EmrTaskInfo> emrTaskInfoYcjList = this.taskInfoService.selectAgiRuleTaskYcjList(eventParam);
            List<TdCaOperationApply> tdCaOperationApplyList = new ArrayList();
            if (CollectionUtils.isNotEmpty(patEventVoList)) {
               new ArrayList();
               List admissionNos = (List)patEventVoList.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
               tdCaOperationApplyList = this.tdCaOperationApplyService.selectTdCaOperationApplyByIds(admissionNos);
            }

            for(QcAgiRule qcAgiRule : e) {
               try {
                  this.qcAgiRuleAllService.agiRuleTaskCreateByPatientId(qcAgiRule, patEventVoList, patEventVoListMap, indexList, emrTaskInfoList, emrTaskInfoYcjList, patEventVoListDeadAll, patEventVoListOutAll, subfileIndexList, mrHpList, emrSignDataList, tdCaOperationApplyList);
               } catch (Exception e) {
                  this.log.error("时效质控" + ruleCode + "生成提醒任务出现异常", e);
               }
            }

            this.log.debug("========时效质控" + ruleCode + "结束执行： " + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.SSS", new Date()));
         }
      } catch (Exception e) {
         this.log.error("时效质控" + ruleCode + "出现异常：", e);
         ajaxResult = AjaxResult.error("查询单人超时病历出现异常", e);
      }

      return ajaxResult;
   }
}
