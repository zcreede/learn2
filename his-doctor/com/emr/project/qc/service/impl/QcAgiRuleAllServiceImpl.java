package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.BqBeginEndTimeVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.service.IQcAgiRuleAllService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class QcAgiRuleAllServiceImpl implements IQcAgiRuleAllService {
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrTaskInfoService taskInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   public void agiRuleTaskCreate(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList, List emrSignDataList, List tdCaOperationApplyList) throws Exception {
      List<Index> indexLastFinishTimeList = new ArrayList(1);
      List<SubfileIndex> subIndexLastFinishTimeList = new ArrayList(1);
      List<MrHp> mrHpLastFinishTimeList = new ArrayList(1);
      Map<String, List<EmrTaskInfo>> emrTaskInfoListMap = (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((t) -> t.getBusId()));
      List<EmrTaskInfoVo> taskListAll = new ArrayList(1);
      this.getTaskAndFinishedNewList(qcAgiRule, patEventVoListMap, emrTaskInfoListMap, patEventVoList, taskListAll, indexList, subfileIndexList, mrHpList, indexLastFinishTimeList, subIndexLastFinishTimeList, mrHpLastFinishTimeList, patEventVoListDeadAll, patEventVoListOutAll, emrSignDataList, tdCaOperationApplyList);
      if (!taskListAll.isEmpty()) {
         this.taskInfoService.insertListByGroup(taskListAll);
      }

      if (!indexLastFinishTimeList.isEmpty()) {
         this.indexService.updateLastFinishTimeList(indexLastFinishTimeList);
      }

      if (!subIndexLastFinishTimeList.isEmpty()) {
         this.subfileIndexService.updateLastFinishTimeList(subIndexLastFinishTimeList);
      }

      if (!mrHpLastFinishTimeList.isEmpty()) {
         this.mrHpService.updateLastFinishTimeList(mrHpLastFinishTimeList);
      }

   }

   public void agiRuleTaskCreateByPatientId(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List emrTaskInfoYcjList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList, List emrSignDataList, List tdCaOperationApplyList) throws Exception {
      List<Index> indexLastFinishTimeList = new ArrayList(1);
      List<SubfileIndex> subIndexLastFinishTimeList = new ArrayList(1);
      List<MrHp> mrHpLastFinishTimeList = new ArrayList(1);
      Map<String, List<EmrTaskInfo>> emrTaskInfoListMap = (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((t) -> t.getBusId()));
      Map<String, List<EmrTaskInfo>> emrTaskInfoListYcjMap = (Map)emrTaskInfoYcjList.stream().collect(Collectors.groupingBy((t) -> t.getBusId()));
      List<EmrTaskInfoVo> taskListAll = new ArrayList(1);
      this.getTaskAndFinishedNewList(qcAgiRule, patEventVoListMap, emrTaskInfoListYcjMap, patEventVoList, taskListAll, indexList, subfileIndexList, mrHpList, indexLastFinishTimeList, subIndexLastFinishTimeList, mrHpLastFinishTimeList, patEventVoListDeadAll, patEventVoListOutAll, emrSignDataList, tdCaOperationApplyList);
      if (!CollectionUtils.isNotEmpty(taskListAll)) {
         List<EmrTaskInfo> taskListAdded = (List)emrTaskInfoListMap.get(String.valueOf(qcAgiRule.getId()));
         if (CollectionUtils.isNotEmpty(taskListAdded)) {
            for(EmrTaskInfo emrTaskInfo : taskListAdded) {
               IndexVo indexVo = new IndexVo();
               indexVo.setPatientId(((PatEventVo)patEventVoList.get(0)).getPatientId());
               indexVo.setMrType(qcAgiRule.getEmrTypeCode());
               List<IndexVo> indexVoList = this.indexService.selectAllIndexList(indexVo);
               if (CollectionUtils.isNotEmpty(indexVoList)) {
                  emrTaskInfo.setTreatFlag("1");
                  emrTaskInfo.setMrFileId(((IndexVo)indexVoList.get(0)).getId());
                  this.taskInfoService.updateEmrTaskInfo(emrTaskInfo);
               }
            }
         }
      }

   }

   public void getTaskAndFinishedNewList(QcAgiRule qcAgiRule, Map patEventVoListMap, Map emrTaskInfoListMap, List patEventVoList, List taskListAll, List indexList, List subfileIndexList, List mrHpList, List indexLastFinishTimeList, List subIndexLastFinishTimeList, List mrHpLastFinishTimeList, List patEventVoListDeadAll, List patEventVoListOutAll, List emrSignDataList, List tdCaOperationApplyList) throws Exception {
      String eventCode = qcAgiRule.getAgiEvnt();
      List<PatEventVo> trigPatList = (List)patEventVoListMap.get(eventCode);
      List<EmrTaskInfo> taskListAdded = (List<EmrTaskInfo>)(emrTaskInfoListMap.get(String.valueOf(qcAgiRule.getId())) != null ? (List)emrTaskInfoListMap.get(String.valueOf(qcAgiRule.getId())) : new ArrayList(1));
      List<String> taskListAddedPatId = (List<String>)(taskListAdded != null ? (List)taskListAdded.stream().map((t) -> t.getPatientId()).collect(Collectors.toList()) : new ArrayList(1));
      List<PatEventVo> conditionPatList = (List)patEventVoList.stream().filter((t) -> "01,18,21".contains(t.getEventCode())).collect(Collectors.toList());
      List<PatEventVo> trigPatFilterTaskedList = (List<PatEventVo>)(trigPatList != null ? (List)trigPatList.stream().filter((t) -> !taskListAddedPatId.contains(t.getPatientId())).collect(Collectors.toList()) : new ArrayList(1));
      List<String> ruleCodeType1List = Arrays.asList("R001", "R002", "R003");
      if (ruleCodeType1List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType4List = Arrays.asList("R004");
      if (ruleCodeType4List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType36List = Arrays.asList("R036", "R037");
      if (ruleCodeType36List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType38List = Arrays.asList("R038");
      if (ruleCodeType38List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType28List = Arrays.asList("R028");
      if (ruleCodeType28List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSqtl(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList, tdCaOperationApplyList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType29List = Arrays.asList("R029");
      if (ruleCodeType29List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType30List = Arrays.asList("R030");
      if (ruleCodeType30List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType31List = Arrays.asList("R031");
      if (ruleCodeType31List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType35List = Arrays.asList("R035");
      if (ruleCodeType35List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType32List = Arrays.asList("R032", "R033", "R034");
      if (ruleCodeType32List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForIndex(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule005CodeList = Arrays.asList("R005", "R014");
      if (mainFileRule005CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule006CodeList = Arrays.asList("R006", "R007", "R008", "R015", "R016", "R017");
      if (mainFileRule006CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR006(qcAgiRule, patEventVoList, taskListAdded, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule009CodeList = Arrays.asList("R009", "R010", "R019", "R020", "R022", "R024", "R027");
      if (mainFileRule009CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule018CodeList = Arrays.asList("R018");
      if (mainFileRule018CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule011CodeList = Arrays.asList("R011", "R012", "R013");
      if (mainFileRule011CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleR011(qcAgiRule, trigPatFilterTaskedList, patEventVoListOutAll, patEventVoListDeadAll, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule021CodeList = Arrays.asList("R021");
      if (mainFileRule021CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule023CodeList = Arrays.asList("R023", "R025");
      if (mainFileRule023CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule026CodeList = Arrays.asList("R026");
      if (mainFileRule026CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForSub(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList, emrSignDataList);
         taskListAll.addAll(taskList);
      }

      List<String> mrHpRuleR039List = Arrays.asList("R039", "R040");
      if (mrHpRuleR039List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.createTaskByRuleForMrHp(qcAgiRule, trigPatFilterTaskedList, mrHpList, mrHpLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

   }

   @Async
   public List createTaskByRuleForSqtl(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList, List emrSignDataList, List tdCaOperationApplyList) throws Exception {
      Boolean flag = false;
      List<PatEventVo> taskPatList = new ArrayList(1);
      String sqtlCode = this.sysEmrConfigService.selectSysEmrConfigByKey("0091");
      String ssjbCode = this.sysEmrConfigService.selectSysEmrConfigByKey("009101");
      if (StringUtils.isNotBlank(sqtlCode) && ("1".equals(sqtlCode) || "3".equals(sqtlCode))) {
         for(PatEventVo patEventVo : patEventVoListTemp) {
            if ("3".equals(sqtlCode) && StringUtils.isNotBlank(ssjbCode)) {
               List<String> ssjbs = (List)((List)tdCaOperationApplyList.stream().filter((t) -> StringUtils.isNotBlank(t.getOperLevelCd()) && patEventVo.getPatientId().equals(t.getAdmissionNo()) && "02".equals(t.getStatus())).collect(Collectors.toList())).stream().map((t) -> t.getOperLevelCd()).distinct().collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(ssjbs) && Integer.valueOf((String)ssjbs.get(0)) >= Integer.valueOf(ssjbCode)) {
                  flag = true;
               }
            } else if ("1".equals(sqtlCode)) {
               flag = true;
            }

            if (flag) {
               Date beforeTime = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
               List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getCreDate().compareTo(beforeTime) >= 0 && s.getCreDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
               if (patSubfileList == null || patSubfileList != null && patSubfileList.isEmpty()) {
                  if (CollectionUtils.isNotEmpty(patSubfileList) && "2".equals(qcAgiRule.getTaskFinishFlag()) || "3".equals(qcAgiRule.getTaskFinishFlag())) {
                     patEventVo.setSignFlag("0");
                  }

                  taskPatList.add(patEventVo);
               } else {
                  Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
                  this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
               }
            }
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List createTaskByRuleForMrHp(QcAgiRule qcAgiRule, List patEventVoListTemp, List mrHpList, List mrHpLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = DateUtils.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         List<MrHp> mrHps = (List)mrHpList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrState().equals("03")).collect(Collectors.toList());
         List<MrHp> mrHp02 = (List)mrHpList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrState().equals("02")).collect(Collectors.toList());
         if (mrHps == null || mrHps != null && mrHps.isEmpty()) {
            if (CollectionUtils.isNotEmpty(mrHp02) && "2".equals(qcAgiRule.getTaskFinishFlag()) || "3".equals(qcAgiRule.getTaskFinishFlag())) {
               patEventVo.setSignFlag("0");
            }

            taskPatList.add(patEventVo);
         } else {
            this.getUpdateLastFinishTimeMrHp(qcAgiRule, patEventVo, mrHps, mrHpLastFinishTimeList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   private void getUpdateLastFinishTimeMrHp(QcAgiRule qcAgiRule, PatEventVo patEventVo, List mrHpList, List mrHpLastFinishTimeList) throws Exception {
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         Date lastFinishTime = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());

         for(MrHp t : (List)mrHpList.stream().filter((tx) -> tx.getLastFinishTime() == null).collect(Collectors.toList())) {
            MrHp mrHp = new MrHp();
            mrHp.setRecordId(t.getRecordId());
            mrHp.setLastFinishTime(lastFinishTime);
            mrHp.setLastFinishRuleId(qcAgiRule.getId());
            mrHpLastFinishTimeList.add(mrHp);
         }
      }

   }

   @Async
   public List createTaskByRuleForIndex(QcAgiRule qcAgiRule, List patEventVoListTemp, List patEventVoListDeadAll, List indexList, List indexLastFinishTimeList, List emrSignDataList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      Date currDate = this.commonService.getDbSysdate();

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = DateUtils.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<PatEventVo> patEventVoListDead = (List)patEventVoListDeadAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         if (patEventVoListDead != null && !patEventVoListDead.isEmpty() && !CollectionUtils.isNotEmpty(patEventVoListDeadAll)) {
            break;
         }

         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType())).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            if (!"2".equals(qcAgiRule.getTaskFinishFlag()) && !"3".equals(qcAgiRule.getTaskFinishFlag())) {
               this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
            } else {
               this.doMultipleIterTypeForIndex(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList, emrSignDataList, patIndexList);
            }
         } else {
            this.doMultipleIterTypeForIndex(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList, emrSignDataList, patIndexList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List createTaskByRuleForSub(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList, List emrSignDataList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = DateUtils.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<SubfileIndexVo> patSubIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType())).collect(Collectors.toList());
         if (patSubIndexList != null && (patSubIndexList == null || !patSubIndexList.isEmpty())) {
            if (!"2".equals(qcAgiRule.getTaskFinishFlag()) && !"3".equals(qcAgiRule.getTaskFinishFlag())) {
               Date lastFinishTime = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
               this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubIndexList, subIndexLastFinishTimeList, lastFinishTime);
            } else {
               this.doMultipleIterTypeForSub(iterTypeCodeList, subfileIndexList, patEventVo, qcAgiRule, taskPatList, subIndexLastFinishTimeList, emrSignDataList, patSubIndexList);
            }
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR028(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList, List emrSignDataList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());

      for(PatEventVo patEventVo : patEventVoListTemp) {
         Date beforeTime = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getCreDate().compareTo(beforeTime) >= 0 && s.getCreDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            if (!"2".equals(qcAgiRule.getTaskFinishFlag()) && !"3".equals(qcAgiRule.getTaskFinishFlag())) {
               Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
               this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
            } else {
               this.doMultipleIterTypeForSub(iterTypeCodeList, subfileIndexList, patEventVo, qcAgiRule, taskPatList, subIndexLastFinishTimeList, emrSignDataList, patSubfileList);
            }
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List createTaskByRuleR011(QcAgiRule qcAgiRule, List patEventVoListTemp, List patEventVoListOutAll, List patEventVoListDeadAll, List subfileIndexList, List subIndexLastFinishTimeList, List emrSignDataList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> taskPatList = new ArrayList(1);
      patEventVoListTemp = (List)patEventVoListTemp.stream().filter((s) -> {
         Date beginTime = DateUtils.getEndTime(s.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());

      for(PatEventVo patEventVo : patEventVoListTemp) {
         List<PatEventVo> patEventVoListDead = (List)patEventVoListDeadAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         List<PatEventVo> patEventVoListOut = new ArrayList();
         if (patEventVoListOutAll != null && patEventVoListOutAll.size() > 0) {
            patEventVoListOut = (List)patEventVoListOutAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         }

         if (patEventVoListDead != null && !patEventVoListDead.isEmpty() || patEventVoListOut != null && !patEventVoListOut.isEmpty()) {
            break;
         }

         Date begin = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && DateUtils.isTheSameDay(begin, s.getRecoDate())).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            if (!"2".equals(qcAgiRule.getTaskFinishFlag()) && !"3".equals(qcAgiRule.getTaskFinishFlag())) {
               Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
               this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
            } else {
               this.doMultipleIterTypeForSub(iterTypeCodeList, subfileIndexList, patEventVo, qcAgiRule, taskPatList, subIndexLastFinishTimeList, emrSignDataList, patSubfileList);
            }
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR006(QcAgiRule qcAgiRule, List patEventVoListTempAll, List taskListAdded, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      List<EmrTaskInfoVo> resTaskList = new ArrayList(1);
      String value0026 = this.sysEmrConfigService.selectSysEmrConfigByKey("0026");
      String value0027 = this.sysEmrConfigService.selectSysEmrConfigByKey("0027");
      JSONObject beApartDayNumObj = null;
      if (qcAgiRule.getRuleCode().equals("R006") || qcAgiRule.getRuleCode().equals("R007") || qcAgiRule.getRuleCode().equals("R008")) {
         beApartDayNumObj = JSONObject.parseObject(value0026);
      }

      if (qcAgiRule.getRuleCode().equals("R015") || qcAgiRule.getRuleCode().equals("R016") || qcAgiRule.getRuleCode().equals("R017")) {
         beApartDayNumObj = JSONObject.parseObject(value0027);
      }

      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTempAll.stream().filter((t) -> {
         Date beginTime = DateUtils.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return t.getEventCode().equals(qcAgiRule.getAgiEvnt()) && currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> evenCode1821List = (List)patEventVoListTempAll.stream().filter((t) -> t.getEventCode().equals("18") || t.getEventCode().equals("21")).collect(Collectors.toList());
      Map<String, List<PatEventVo>> evenCode1821Map = (Map)evenCode1821List.stream().collect(Collectors.groupingBy((t) -> t.getPatientId()));
      List<PatEventVo> leaveHosEventList = (List)patEventVoListTempAll.stream().filter((t) -> t.getEventCode().equals("02")).collect(Collectors.toList());
      PatEventVo leaveHosEvent = CollectionUtils.isNotEmpty(leaveHosEventList) ? (PatEventVo)leaveHosEventList.get(0) : null;
      Date leaveHosDate = leaveHosEvent != null ? leaveHosEvent.getBeginTime() : null;

      for(PatEventVo patEventVo : trigPatList) {
         String patientId = patEventVo.getPatientId();
         List<PatEventVo> evenCode1821ListTemp = (List)evenCode1821Map.get(patientId);

         for(BqBeginEndTimeVo bqBeginEndTimeVo : this.getBqBeginEndTimeList(patEventVo, evenCode1821ListTemp, leaveHosDate)) {
            int beApartDayNum = (Integer)beApartDayNumObj.get(bqBeginEndTimeVo.getBqCode());

            for(List dateListTemp : this.getDateList(bqBeginEndTimeVo.getBeginTime(), beApartDayNum, bqBeginEndTimeVo.getEndTime())) {
               List<EmrTaskInfo> taskList = (List)taskListAdded.stream().filter((t) -> dateListTemp.contains(t.getBeginTime())).collect(Collectors.toList());
               if (taskList == null || taskList.isEmpty()) {
                  Date beginTimeTemp = (Date)dateListTemp.get(dateListTemp.size() - 1);
                  Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, beginTimeTemp) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
                  List<SubfileIndexVo> patSubIndexList = new ArrayList(1);

                  for(SubfileIndexVo s : subfileIndexList) {
                     Date recoDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, s.getRecoDate()), new String[]{DateUtils.YYYY_MM_DD});
                     if (s.getPatientId().equals(patientId) && iterTypeCodeList.contains(s.getMrType()) && dateListTemp.contains(recoDate)) {
                        patSubIndexList.add(s);
                     }
                  }

                  if (patSubIndexList == null || patSubIndexList != null && patSubIndexList.isEmpty()) {
                     if ("2".equals(qcAgiRule.getTaskFinishFlag()) || "3".equals(qcAgiRule.getTaskFinishFlag())) {
                        patEventVo.setSignFlag("0");
                     }

                     EmrTaskInfoVo taskInfo = this.getEmrTaskInfo(patEventVo, qcAgiRule, lastFinishTime, currDate, bqBeginEndTimeVo.getBqName(), beApartDayNum + "");
                     resTaskList.add(taskInfo);
                  } else {
                     this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, subfileIndexList, subIndexLastFinishTimeList, lastFinishTime);
                  }
               }
            }
         }
      }

      return resTaskList;
   }

   private void doMultipleIterTypeForIndex(List iterTypeCodeList, List indexList, PatEventVo patEventVo, QcAgiRule qcAgiRule, List taskPatList, List indexLastFinishTimeList, List emrSignDataList, List patIndexList) throws Exception {
      Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(patEventVo.getPatientId());
      if (CollectionUtils.isEmpty(iterTypeCodeList)) {
         taskPatList.add(patEventVo);
      } else if ("2".equals(qcAgiRule.getTaskFinishFlag()) && patIndexList != null && patIndexList.size() > 0) {
         if (CollectionUtils.isNotEmpty(patIndexList)) {
            List<Index> indexList1 = (List)patIndexList.stream().filter((t) -> "03".equals(t.getMrState())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(indexList1)) {
               patEventVo.setSignFlag("0");
               if ("R002".equals(qcAgiRule.getRuleCode())) {
                  List<Baseinfomation> baseinfomationList = this.commonService.findBaseInfoByIdcard(patEventVo.getIdcard());
                  if (baseinfomationList != null && baseinfomationList.size() > 1 && medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
                     taskPatList.add(patEventVo);
                  }
               } else if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
                  taskPatList.add(patEventVo);
               }
            }
         }
      } else if ("3".equals(qcAgiRule.getTaskFinishFlag()) && patIndexList != null && patIndexList.size() > 0) {
         if (CollectionUtils.isNotEmpty(patIndexList)) {
            List<Index> indexList1 = (List)patIndexList.stream().filter((t) -> "08".equals(t.getMrState())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(indexList1)) {
               patEventVo.setSignFlag("0");
               if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
                  taskPatList.add(patEventVo);
               }
            }
         }
      } else {
         List<Index> patIndexList2 = new ArrayList(1);
         List<String> iterTypeFlagList = new ArrayList(iterTypeCodeList.size());

         for(String mrType : iterTypeCodeList) {
            List<Index> patMrTypeIndex = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrType().equals(mrType) && "0".equals(s.getDelFlag())).collect(Collectors.toList());
            if ("2".equals(qcAgiRule.getTaskFinishFlag()) && CollectionUtils.isNotEmpty(patMrTypeIndex)) {
               for(Index index : patMrTypeIndex) {
                  List<EmrSignData> emrSignData = (List)emrSignDataList.stream().filter((t) -> index.getId().equals(t.getSignFileId()) && patEventVo.getResDocCd().equals(t.getSignerCd())).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(emrSignData)) {
                     patEventVo.setSignFlag("1");
                     this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList2, indexLastFinishTimeList, (Date)null);
                  }
               }
            }

            boolean flag = patMrTypeIndex != null && !patMrTypeIndex.isEmpty();
            iterTypeFlagList.add(flag ? "1" : "0");
            patIndexList2.addAll(patMrTypeIndex);
         }

         if (iterTypeFlagList.contains("1")) {
            if (patIndexList2 != null && !patIndexList2.isEmpty()) {
               this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList2, indexLastFinishTimeList, (Date)null);
            }
         } else if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
            taskPatList.add(patEventVo);
         }
      }

   }

   private void doMultipleIterTypeForSub(List iterTypeCodeList, List subfileIndexVoList, PatEventVo patEventVo, QcAgiRule qcAgiRule, List taskPatList, List subLastFinishTimeList, List emrSignDataList, List patSubIndexList) throws Exception {
      Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(patEventVo.getPatientId());
      if (CollectionUtils.isEmpty(iterTypeCodeList)) {
         if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
            taskPatList.add(patEventVo);
         }
      } else if ("2".equals(qcAgiRule.getTaskFinishFlag()) && patSubIndexList != null && patSubIndexList.size() > 0) {
         if (CollectionUtils.isNotEmpty(patSubIndexList)) {
            List<SubfileIndex> indexList1 = (List)patSubIndexList.stream().filter((t) -> !"08".equals(t.getMrState())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(indexList1)) {
               patEventVo.setSignFlag("0");
               if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
                  taskPatList.add(patEventVo);
               }
            }
         }
      } else if ("3".equals(qcAgiRule.getTaskFinishFlag()) && patSubIndexList != null && patSubIndexList.size() > 0) {
         if (CollectionUtils.isNotEmpty(patSubIndexList)) {
            List<SubfileIndex> indexList1 = (List)patSubIndexList.stream().filter((t) -> !"08".equals(t.getMrState())).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(indexList1)) {
               patEventVo.setSignFlag("0");
               if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
                  taskPatList.add(patEventVo);
               }
            }
         }
      } else {
         List<SubfileIndexVo> patIndexList2 = new ArrayList(1);
         List<String> iterTypeFlagList = new ArrayList(iterTypeCodeList.size());

         for(String mrType : iterTypeCodeList) {
            List<SubfileIndexVo> patMrTypeIndex = (List)subfileIndexVoList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrType().equals(mrType) && "0".equals(s.getDelFlag())).collect(Collectors.toList());
            if ("2".equals(qcAgiRule.getTaskFinishFlag()) && CollectionUtils.isNotEmpty(patMrTypeIndex)) {
               for(SubfileIndex subfileIndex : patMrTypeIndex) {
                  List<EmrSignData> emrSignData = (List)emrSignDataList.stream().filter((t) -> subfileIndex.getId().equals(t.getSignFileId()) && patEventVo.getResDocCd().equals(t.getSignerCd())).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(emrSignData)) {
                     patEventVo.setSignFlag("1");
                     this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList2, subLastFinishTimeList, (Date)null);
                  }
               }
            }

            boolean flag = patMrTypeIndex != null && !patMrTypeIndex.isEmpty();
            iterTypeFlagList.add(flag ? "1" : "0");
            patIndexList2.addAll(patMrTypeIndex);
         }

         if (iterTypeFlagList.contains("1")) {
            if (patIndexList2 != null && !patIndexList2.isEmpty()) {
               this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList2, subLastFinishTimeList, (Date)null);
            }
         } else if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getResidentCode())) {
            taskPatList.add(patEventVo);
         }
      }

   }

   private void getUpdateLastFinishTimeIndex(QcAgiRule qcAgiRule, PatEventVo patEventVo, List indexList, List indexLastFinishTimeList, Date lastFinishTime) throws Exception {
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         lastFinishTime = lastFinishTime == null ? DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit()) : lastFinishTime;

         for(Index t : (List)indexList.stream().filter((tx) -> tx.getLastFinishTime() == null).collect(Collectors.toList())) {
            Index index = new Index();
            index.setId(t.getId());
            index.setLastFinishTime(lastFinishTime);
            index.setLastFinishRuleId(qcAgiRule.getId());
            indexLastFinishTimeList.add(index);
         }
      }

   }

   private void getUpdateLastFinishTimeSubIndex(QcAgiRule qcAgiRule, PatEventVo patEventVo, List subfileIndexList, List subIndexLastFinishTimeList, Date lastFinishTime) throws Exception {
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         for(SubfileIndexVo t : (List)subfileIndexList.stream().filter((tx) -> tx.getLastFinishTime() == null).collect(Collectors.toList())) {
            SubfileIndex subIndex = new SubfileIndex();
            subIndex.setId(t.getId());
            subIndex.setLastFinishTime(lastFinishTime);
            subIndex.setLastFinishRuleId(qcAgiRule.getId());
            subIndexLastFinishTimeList.add(subIndex);
         }
      }

   }

   private String getRuleTip(PatEvent patEvent, QcAgiRule qcAgiRule, Date endTime, String bqName, String beApartDayNum) {
      String ruleTip = null;
      if (qcAgiRule != null && StringUtils.isNotBlank(qcAgiRule.getRuleTip())) {
         ruleTip = qcAgiRule.getRuleTip();
      } else {
         ruleTip = MessageUtils.message("qc.agiRuleR001.noCreate.msg");
      }

      ruleTip = ruleTip.replaceAll("@patientName", patEvent.getPatientName()).replaceAll("@inpNo", patEvent.getInpNo()).replaceAll("@finishLimitTime", qcAgiRule.getFinishLimitTime().toString()).replaceAll("@bqName", bqName).replaceAll("@beApartDayNum", beApartDayNum).replaceAll("@endTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime));
      return ruleTip;
   }

   private String getRuleTipNoSign(PatEvent patEvent, QcAgiRule qcAgiRule, Date endTime, String bqName, String beApartDayNum) {
      String ruleTip = null;
      if (qcAgiRule != null && "R001".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR001.noSign.msg");
      } else if (qcAgiRule != null && "R002".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR002.noSign.msg");
      } else if (qcAgiRule != null && "R003".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR003.noSign.msg");
      } else if (qcAgiRule != null && "R004".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR004.noSign.msg");
      } else if (qcAgiRule != null && "R005".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR005.noSign.msg");
      } else if (qcAgiRule != null && "R006".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR006.noSign.msg");
      } else if (qcAgiRule != null && "R007".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR007.noSign.msg");
      } else if (qcAgiRule != null && "R008".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR008.noSign.msg");
      } else if (qcAgiRule != null && "R009".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR009.noSign.msg");
      } else if (qcAgiRule != null && "R010".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR010.noSign.msg");
      } else if (qcAgiRule != null && "R011".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR011.noSign.msg");
      } else if (qcAgiRule != null && "R012".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR012.noSign.msg");
      } else if (qcAgiRule != null && "R013".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR013.noSign.msg");
      } else if (qcAgiRule != null && "R014".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR014.noSign.msg");
      } else if (qcAgiRule != null && "R018".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR019.noSign.msg");
      } else if (qcAgiRule != null && "R019".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR019.noSign.msg");
      } else if (qcAgiRule != null && "R020".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR020.noSign.msg");
      } else if (qcAgiRule != null && "R021".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR021.noSign.msg");
      } else if (qcAgiRule != null && "R022".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR022.noSign.msg");
      } else if (qcAgiRule != null && "R023".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR023.noSign.msg");
      } else if (qcAgiRule != null && "R024".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR024.noSign.msg");
      } else if (qcAgiRule != null && "R025".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR025.noSign.msg");
      } else if (qcAgiRule != null && "R026".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR026.noSign.msg");
      } else if (qcAgiRule != null && "R027".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR027.noSign.msg");
      } else if (qcAgiRule != null && "R028".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR028.noSign.msg");
      } else if (qcAgiRule != null && "R029".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR029.noSign.msg");
      } else if (qcAgiRule != null && "R030".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR030.noSign.msg");
      } else if (qcAgiRule != null && "R031".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR031.noSign.msg");
      } else if (qcAgiRule != null && "R032".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR032.noSign.msg");
      } else if (qcAgiRule != null && "R033".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR033.noSign.msg");
      } else if (qcAgiRule != null && "R034".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR034.noSign.msg");
      } else if (qcAgiRule != null && "R035".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR035.noSign.msg");
      } else if (qcAgiRule != null && "R036".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR036.noSign.msg");
      } else if (qcAgiRule != null && "R037".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR037.noSign.msg");
      } else if (qcAgiRule != null && "R038".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR038.noSign.msg");
      } else if (qcAgiRule != null && "R039".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR039.noSign.msg");
      } else if (qcAgiRule != null && "R040".equals(qcAgiRule.getRuleCode())) {
         ruleTip = MessageUtils.message("qc.agiRuleR040.noSign.msg");
      }

      ruleTip = ruleTip.replaceAll("@patientName", patEvent.getPatientName()).replaceAll("@inpNo", patEvent.getInpNo()).replaceAll("@finishLimitTime", qcAgiRule.getFinishLimitTime().toString()).replaceAll("@bqName", bqName).replaceAll("@beApartDayNum", beApartDayNum).replaceAll("@endTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, endTime));
      return ruleTip;
   }

   private List getEmrTaskInfo(List taskPatList, QcAgiRule qcAgiRule) throws Exception {
      List<EmrTaskInfoVo> resultTaskList = new ArrayList(1);
      Date currDate = this.commonService.getDbSysdate();
      if (taskPatList != null && !taskPatList.isEmpty()) {
         for(PatEventVo patEventVo : taskPatList) {
            EmrTaskInfoVo emrTaskInfo = new EmrTaskInfoVo();
            emrTaskInfo.setId(SnowIdUtils.uniqueLong());
            emrTaskInfo.setInpNo(patEventVo.getInpNo());
            emrTaskInfo.setPatientId(patEventVo.getPatientId());
            emrTaskInfo.setPatientName(patEventVo.getPatientName());
            emrTaskInfo.setDeptCd(patEventVo.getDeptCd());
            emrTaskInfo.setDeptName(patEventVo.getDeptName());
            emrTaskInfo.setTaskType("1");
            emrTaskInfo.setTaskTypeName("病历书写");
            emrTaskInfo.setTaskAppDoc("sys");
            emrTaskInfo.setTaskAppDocName("系统");
            emrTaskInfo.setBeginTime(currDate);
            emrTaskInfo.setBusId(qcAgiRule.getId().toString());
            emrTaskInfo.setBusName(qcAgiRule.getRuleName());
            emrTaskInfo.setDocCd(patEventVo.getResDocCd());
            emrTaskInfo.setDocName(patEventVo.getResDocName());
            emrTaskInfo.setTreatFlag("0");
            emrTaskInfo.setLimitTime(qcAgiRule.getFinishLimitTime());
            emrTaskInfo.setLimitTimeUnit(qcAgiRule.getLimitTimeUnit());
            emrTaskInfo.setEmrTypeName(qcAgiRule.getEmrTypeName());
            emrTaskInfo.setEvenName(patEventVo.getEventName());
            Date endTime = DateUtils.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
            emrTaskInfo.setEndTime(endTime);
            String ruleTip = null;
            if ("0".equals(patEventVo.getSignFlag())) {
               ruleTip = this.getRuleTipNoSign(patEventVo, qcAgiRule, endTime, "", "");
            } else {
               ruleTip = this.getRuleTip(patEventVo, qcAgiRule, endTime, "", "");
            }

            emrTaskInfo.setMark(ruleTip);
            emrTaskInfo.setRuleId(qcAgiRule.getId());
            emrTaskInfo.setRuleName(qcAgiRule.getRuleName());
            emrTaskInfo.setEventNo(patEventVo.getEventNo());
            resultTaskList.add(emrTaskInfo);
         }
      }

      return resultTaskList;
   }

   private EmrTaskInfoVo getEmrTaskInfo(PatEventVo patEventVo, QcAgiRule qcAgiRule, Date endTime, Date currDate, String bqName, String beApartDayNum) {
      EmrTaskInfoVo emrTaskInfo = new EmrTaskInfoVo();
      emrTaskInfo.setId(SnowIdUtils.uniqueLong());
      emrTaskInfo.setInpNo(patEventVo.getInpNo());
      emrTaskInfo.setPatientId(patEventVo.getPatientId());
      emrTaskInfo.setPatientName(patEventVo.getPatientName());
      emrTaskInfo.setDeptCd(patEventVo.getDeptCd());
      emrTaskInfo.setDeptName(patEventVo.getDeptName());
      emrTaskInfo.setTaskType("1");
      emrTaskInfo.setTaskTypeName("病历书写");
      emrTaskInfo.setTaskAppDoc("sys");
      emrTaskInfo.setTaskAppDocName("系统");
      emrTaskInfo.setBeginTime(currDate);
      emrTaskInfo.setBusId(qcAgiRule.getId().toString());
      emrTaskInfo.setBusName(qcAgiRule.getRuleName());
      String ruleTip = null;
      if ("0".equals(patEventVo.getSignFlag())) {
         ruleTip = this.getRuleTipNoSign(patEventVo, qcAgiRule, endTime, bqName, beApartDayNum);
      } else {
         ruleTip = this.getRuleTip(patEventVo, qcAgiRule, endTime, bqName, beApartDayNum);
      }

      emrTaskInfo.setMark(ruleTip);
      emrTaskInfo.setDocCd(patEventVo.getResDocCd());
      emrTaskInfo.setDocName(patEventVo.getResDocName());
      emrTaskInfo.setTreatFlag("0");
      emrTaskInfo.setLimitTime(qcAgiRule.getFinishLimitTime());
      emrTaskInfo.setLimitTimeUnit(qcAgiRule.getLimitTimeUnit());
      emrTaskInfo.setEmrTypeName(qcAgiRule.getEmrTypeName());
      emrTaskInfo.setEvenName(patEventVo.getEventName());
      emrTaskInfo.setEndTime(endTime);
      emrTaskInfo.setRuleId(qcAgiRule.getId());
      emrTaskInfo.setRuleName(qcAgiRule.getRuleName());
      emrTaskInfo.setEventNo(patEventVo.getEventNo());
      return emrTaskInfo;
   }

   public List getDateList(Date beginTime, int beApartDayNum, Date currDate) throws Exception {
      List<List<Date>> dateList = new ArrayList(1);
      List<Date> dateListTemp = new ArrayList(1);

      for(Date tempDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, beginTime), new String[]{DateUtils.YYYY_MM_DD}); tempDate.compareTo(currDate) < 0; tempDate = DateUtils.addDays(tempDate, 1)) {
         if (dateListTemp.size() < beApartDayNum) {
            dateListTemp.add(tempDate);
         }

         if (dateListTemp.size() == beApartDayNum) {
            dateList.add(dateListTemp);
            dateListTemp = new ArrayList(1);
         }
      }

      return dateList;
   }

   public List getBqBeginEndTimeList(PatEventVo patEventVo, List evenCode1821ListTemp, Date leaveHosDate) throws Exception {
      List<BqBeginEndTimeVo> bqBeginEndTimeVoList = new ArrayList(1);
      if (evenCode1821ListTemp == null) {
         evenCode1821ListTemp = new ArrayList(1);
      }

      evenCode1821ListTemp.add(patEventVo);
      evenCode1821ListTemp = (List)evenCode1821ListTemp.stream().sorted(Comparator.comparing(PatEvent::getBeginTime)).collect(Collectors.toList());

      for(int i = 0; i < evenCode1821ListTemp.size(); ++i) {
         PatEventVo eventVo = (PatEventVo)evenCode1821ListTemp.get(i);
         Date endTime = eventVo.getEndTime();
         if (endTime == null) {
            if (i + 1 < evenCode1821ListTemp.size()) {
               endTime = ((PatEventVo)evenCode1821ListTemp.get(i + 1)).getBeginTime();
            } else {
               endTime = leaveHosDate == null ? this.commonService.getDbSysdate() : leaveHosDate;
            }
         }

         String bqName = "";
         switch (eventVo.getEventCode()) {
            case "01":
               bqName = "一般病情";
               break;
            case "18":
               bqName = "病危患者";
               break;
            case "21":
               bqName = "病重患者";
         }

         BqBeginEndTimeVo bqBeginEndTimeVo = new BqBeginEndTimeVo(eventVo.getEventCode(), bqName, eventVo.getBeginTime(), endTime);
         bqBeginEndTimeVoList.add(bqBeginEndTimeVo);
      }

      return bqBeginEndTimeVoList;
   }
}
