package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.BqBeginEndTimeVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.domain.vo.QcAgiRuleVo;
import com.emr.project.qc.mapper.QcAgiRuleMapper;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.qc.service.IQcAgiEvenService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QcAgiRuleServiceImpl implements IQcAgiRuleService {
   @Autowired
   private QcAgiRuleMapper qcAgiRuleMapper;
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IPatEventService patEventService;
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
   private IQcAgiEvenService qcAgiEvenService;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public QcAgiRule selectQcAgiRuleById(Long id) {
      return this.qcAgiRuleMapper.selectQcAgiRuleById(id);
   }

   public QcAgiRule selectQcAgiRuleByCode(String ruleCode) throws Exception {
      return this.qcAgiRuleMapper.selectQcAgiRuleByCode(ruleCode);
   }

   public QcAgiRule selectYxQcAgiRuleByCode(String ruleCode) throws Exception {
      return this.qcAgiRuleMapper.selectYxQcAgiRuleByCode(ruleCode);
   }

   public List selectQcAgiRuleListByCode(List ruleCodeList) throws Exception {
      return this.qcAgiRuleMapper.selectQcAgiRuleListByCode(ruleCodeList);
   }

   public List selectQcAgiRuleListByIds(List list) throws Exception {
      return this.qcAgiRuleMapper.selectQcAgiRuleListByIds(list);
   }

   public List selectQcAgiRuleList(QcAgiRuleVo qcAgiRuleVo) throws Exception {
      return this.qcAgiRuleMapper.selectQcAgiRuleList(qcAgiRuleVo);
   }

   public int insertQcAgiRule(QcAgiRule qcAgiRule) {
      return this.qcAgiRuleMapper.insertQcAgiRule(qcAgiRule);
   }

   public int updateQcAgiRule(QcAgiRule qcAgiRule) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcAgiRule.setAltPerCode(basEmployee.getEmplNumber());
      qcAgiRule.setAltPerName(basEmployee.getEmplName());
      return this.qcAgiRuleMapper.updateQcAgiRule(qcAgiRule);
   }

   public void updateQcAgiRuleFlag(QcAgiRule qcAgiRule) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      qcAgiRule.setAltPerCode(basEmployee.getEmplNumber());
      qcAgiRule.setAltPerName(basEmployee.getEmplName());
      this.qcAgiRuleMapper.updateQcAgiRule(qcAgiRule);
   }

   public int deleteQcAgiRuleByIds(Long[] ids) {
      return this.qcAgiRuleMapper.deleteQcAgiRuleByIds(ids);
   }

   public int deleteQcAgiRuleById(Long id) {
      return this.qcAgiRuleMapper.deleteQcAgiRuleById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void agiRuleDoing(String ruleCode) throws Exception {
      List<String> ruleCodeList = Arrays.asList(ruleCode.split(","));
      List<QcAgiRule> qcAgiRuleList = this.selectQcAgiRuleListByCode(ruleCodeList);
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
      List<Index> indexList = this.indexService.selectPatIndexList(eventParam);
      List<SubfileIndexVo> subfileIndexList = this.subfileIndexService.selectPatIndexList(eventParam);
      List<MrHp> mrHpList = this.mrHpService.selectPatMrHpList(eventParam);
      List<Index> indexLastFinishTimeList = new ArrayList(1);
      List<SubfileIndex> subIndexLastFinishTimeList = new ArrayList(1);
      List<MrHp> mrHpLastFinishTimeList = new ArrayList(1);
      List<EmrTaskInfo> emrTaskInfoList = this.taskInfoService.selectAgiRuleTaskList(eventParam);
      Map<String, List<EmrTaskInfo>> emrTaskInfoListMap = (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((t) -> t.getBusId()));
      List<EmrTaskInfoVo> taskListAll = new ArrayList(1);

      for(QcAgiRule qcAgiRule : qcAgiRuleList) {
         this.getTaskAndFinishedList(qcAgiRule, patEventVoListMap, emrTaskInfoListMap, patEventVoList, taskListAll, indexList, subfileIndexList, mrHpList, indexLastFinishTimeList, subIndexLastFinishTimeList, mrHpLastFinishTimeList, patEventVoListDeadAll, patEventVoListOutAll);
      }

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

   public void agiRuleTaskDoing(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList) throws Exception {
      List<Index> indexLastFinishTimeList = new ArrayList(1);
      List<SubfileIndex> subIndexLastFinishTimeList = new ArrayList(1);
      List<MrHp> mrHpLastFinishTimeList = new ArrayList(1);
      Map<String, List<EmrTaskInfo>> emrTaskInfoListMap = (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((t) -> t.getBusId()));
      List<EmrTaskInfoVo> taskListAll = new ArrayList(1);
      this.getTaskAndFinishedList(qcAgiRule, patEventVoListMap, emrTaskInfoListMap, patEventVoList, taskListAll, indexList, subfileIndexList, mrHpList, indexLastFinishTimeList, subIndexLastFinishTimeList, mrHpLastFinishTimeList, patEventVoListDeadAll, patEventVoListOutAll);
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

   private void getTaskAndFinishedList(QcAgiRule qcAgiRule, Map patEventVoListMap, Map emrTaskInfoListMap, List patEventVoList, List taskListAll, List indexList, List subfileIndexList, List mrHpList, List indexLastFinishTimeList, List subIndexLastFinishTimeList, List mrHpLastFinishTimeList, List patEventVoListDeadAll, List patEventVoListOutAll) throws Exception {
      String eventCode = qcAgiRule.getAgiEvnt();
      List<PatEventVo> trigPatList = (List)patEventVoListMap.get(eventCode);
      List<EmrTaskInfo> taskListAdded = (List<EmrTaskInfo>)(emrTaskInfoListMap.get(String.valueOf(qcAgiRule.getId())) != null ? (List)emrTaskInfoListMap.get(String.valueOf(qcAgiRule.getId())) : new ArrayList(1));
      List<String> taskListAddedPatId = (List<String>)(taskListAdded != null ? (List)taskListAdded.stream().map((t) -> t.getPatientId()).collect(Collectors.toList()) : new ArrayList(1));
      List<PatEventVo> conditionPatList = (List)patEventVoList.stream().filter((t) -> "01,18,21".contains(t.getEventCode())).collect(Collectors.toList());
      List<PatEventVo> trigPatFilterTaskedList = (List<PatEventVo>)(trigPatList != null ? (List)trigPatList.stream().filter((t) -> !taskListAddedPatId.contains(t.getPatientId())).collect(Collectors.toList()) : new ArrayList(1));
      List<String> ruleCodeType1List = Arrays.asList("R001", "R002");
      if (ruleCodeType1List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR001(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType3List = Arrays.asList("R003");
      if (ruleCodeType3List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR003(qcAgiRule, trigPatFilterTaskedList, patEventVoListDeadAll, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType4List = Arrays.asList("R004");
      if (ruleCodeType4List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR004(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType36List = Arrays.asList("R036", "R037");
      if (ruleCodeType36List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR036(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType38List = Arrays.asList("R038");
      if (ruleCodeType38List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR038(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType28List = Arrays.asList("R028");
      if (ruleCodeType28List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR028(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType29List = Arrays.asList("R029");
      if (ruleCodeType29List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR029(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType30List = Arrays.asList("R030");
      if (ruleCodeType30List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR030(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType31List = Arrays.asList("R031");
      if (ruleCodeType31List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR031(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType35List = Arrays.asList("R035");
      if (ruleCodeType35List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR035(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> ruleCodeType32List = Arrays.asList("R032", "R033", "R034");
      if (ruleCodeType32List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR032(qcAgiRule, trigPatFilterTaskedList, indexList, indexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule005CodeList = Arrays.asList("R005", "R014");
      if (mainFileRule005CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR005(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule006CodeList = Arrays.asList("R006", "R007", "R008", "R015", "R016", "R017");
      if (mainFileRule006CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR006(qcAgiRule, patEventVoList, taskListAdded, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule009CodeList = Arrays.asList("R009", "R010", "R019", "R020", "R022", "R024", "R027");
      if (mainFileRule009CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR009(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule018CodeList = Arrays.asList("R018");
      if (mainFileRule018CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR018(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule011CodeList = Arrays.asList("R011", "R012", "R013");
      if (mainFileRule011CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR011(qcAgiRule, trigPatFilterTaskedList, patEventVoListOutAll, patEventVoListDeadAll, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule021CodeList = Arrays.asList("R021");
      if (mainFileRule021CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR021(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule023CodeList = Arrays.asList("R023", "R025");
      if (mainFileRule023CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR023(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mainFileRule026CodeList = Arrays.asList("R026");
      if (mainFileRule026CodeList.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR026(qcAgiRule, trigPatFilterTaskedList, subfileIndexList, subIndexLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

      List<String> mrHpRuleR039List = Arrays.asList("R039", "R040");
      if (mrHpRuleR039List.contains(qcAgiRule.getRuleCode())) {
         List<EmrTaskInfoVo> taskList = this.ruleDoR039(qcAgiRule, trigPatFilterTaskedList, mrHpList, mrHpLastFinishTimeList);
         taskListAll.addAll(taskList);
      }

   }

   @Async
   public List ruleDoR001(QcAgiRule qcAgiRule, List patEventVoListTemp, List patEventVoListDeadAll, List indexList, List indexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      Date currDate = this.commonService.getDbSysdate();

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<PatEventVo> patEventVoListDead = (List)patEventVoListDeadAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         if (patEventVoListDead != null && !patEventVoListDead.isEmpty()) {
            break;
         }

         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType())).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            this.doMultipleIterType(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   public List ruleDoR003(QcAgiRule qcAgiRule, List patEventVoListTemp, List patEventVoListDeadAll, List indexList, List indexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      Date currDate = this.commonService.getDbSysdate();

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<PatEventVo> patEventVoListDead = (List)patEventVoListDeadAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         if (patEventVoListDead != null && !patEventVoListDead.isEmpty()) {
            break;
         }

         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrType().equals(qcAgiRule.getEmrTypeCode())).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            this.doMultipleIterType(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   public List ruleDoR004(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      Date currDate = this.commonService.getDbSysdate();

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType())).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            this.doMultipleIterType(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   private void doMultipleIterType(List iterTypeCodeList, List indexList, PatEventVo patEventVo, QcAgiRule qcAgiRule, List taskPatList, List indexLastFinishTimeList) throws Exception {
      if (CollectionUtils.isEmpty(iterTypeCodeList)) {
         taskPatList.add(patEventVo);
      } else {
         List<Index> patIndexList2 = new ArrayList(1);
         List<String> iterTypeFlagList = new ArrayList(iterTypeCodeList.size());

         for(String mrType : iterTypeCodeList) {
            List<Index> patMrTypeIndex = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrType().equals(mrType)).collect(Collectors.toList());
            boolean flag = patMrTypeIndex != null && !patMrTypeIndex.isEmpty();
            iterTypeFlagList.add(flag ? "1" : "0");
            patIndexList2.addAll(patMrTypeIndex);
         }

         if (iterTypeFlagList.contains("1")) {
            if (patIndexList2 != null && !patIndexList2.isEmpty()) {
               this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList2, indexLastFinishTimeList, (Date)null);
            }
         } else {
            taskPatList.add(patEventVo);
         }
      }

   }

   @Async
   public List ruleDoR036(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType()) && DateUtils.isTheSameDay(patEventVo.getBeginTime(), s.getCreateTime())).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR038(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         List<Index> patSubfileList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType()) && DateUtils.isTheSameDay(patEventVo.getBeginTime(), s.getCreateTime())).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patSubfileList, indexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   private void getUpdateLastFinishTimeIndex(QcAgiRule qcAgiRule, PatEventVo patEventVo, List indexList, List indexLastFinishTimeList, Date lastFinishTime) throws Exception {
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         lastFinishTime = lastFinishTime == null ? this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit()) : lastFinishTime;

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

   private void getUpdateLastFinishTimeMrHp(QcAgiRule qcAgiRule, PatEventVo patEventVo, List mrHpList, List mrHpLastFinishTimeList) throws Exception {
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());

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
   public List ruleDoR028(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         Date beforeTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getCreDate().compareTo(beforeTime) >= 0 && s.getCreDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR029(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         Date beforeTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<Index> patSubfileList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getCreDate().compareTo(beforeTime) >= 0 && s.getCreDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patSubfileList, indexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR030(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         Date beforeTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getRecoDate().compareTo(beforeTime) >= 0 && s.getRecoDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR031(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         Date endTime = this.getEndTime(patEventVo.getEndTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getRecoDate().compareTo(endTime) <= 0 && s.getRecoDate().compareTo(patEventVo.getEndTime()) >= 0).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR035(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         Date beginTime = this.getEndTime(patEventVo.getBeginTime(), 1, "D");
         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getRecoDate().compareTo(beginTime) >= 0 && s.getRecoDate().compareTo(patEventVo.getBeginTime()) <= 0).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR032(QcAgiRule qcAgiRule, List patEventVoListTemp, List indexList, List indexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<Index> patIndexList = (List)indexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType())).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            this.getUpdateLastFinishTimeIndex(qcAgiRule, patEventVo, patIndexList, indexLastFinishTimeList, (Date)null);
         } else {
            this.doMultipleIterType(iterTypeCodeList, indexList, patEventVo, qcAgiRule, taskPatList, indexLastFinishTimeList);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR005(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<SubfileIndexVo> patSubIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType())).collect(Collectors.toList());
         if (patSubIndexList != null && (patSubIndexList == null || !patSubIndexList.isEmpty())) {
            Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubIndexList, subIndexLastFinishTimeList, lastFinishTime);
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
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
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

                  if (patSubIndexList != null && (patSubIndexList == null || !patSubIndexList.isEmpty())) {
                     this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, subfileIndexList, subIndexLastFinishTimeList, lastFinishTime);
                  } else {
                     EmrTaskInfoVo taskInfo = this.getEmrTaskInfo(patEventVo, qcAgiRule, lastFinishTime, currDate, bqBeginEndTimeVo.getBqName(), beApartDayNum + "");
                     resTaskList.add(taskInfo);
                  }
               }
            }
         }
      }

      return resTaskList;
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

   public PatEventVo getCurrBq(PatEventVo patEventVo, List evenCode1821ListTemp) {
      PatEventVo resPatEventVo = patEventVo;
      List<PatEventVo> evenCode18 = (List<PatEventVo>)(evenCode1821ListTemp != null ? (List)evenCode1821ListTemp.stream().filter((t) -> t.getEventCode().equals("18") && t.getEndTime() == null).collect(Collectors.toList()) : new ArrayList(1));
      List<PatEventVo> evenCode21 = (List<PatEventVo>)(evenCode1821ListTemp != null ? (List)evenCode1821ListTemp.stream().filter((t) -> t.getEventCode().equals("21") && t.getEndTime() == null).collect(Collectors.toList()) : new ArrayList(1));
      boolean evenCode18Flag = evenCode18 != null && !evenCode18.isEmpty();
      boolean evenCode21Flag = evenCode21 != null && !evenCode21.isEmpty();
      if (evenCode18Flag) {
         evenCode18 = (List)evenCode18.stream().sorted(Comparator.comparing(PatEvent::getBeginTime)).collect(Collectors.toList());
         resPatEventVo = (PatEventVo)evenCode18.get(evenCode18.size() - 1);
      } else if (evenCode21Flag) {
         evenCode21 = (List)evenCode21.stream().sorted(Comparator.comparing(PatEvent::getBeginTime)).collect(Collectors.toList());
         resPatEventVo = (PatEventVo)evenCode21.get(evenCode18.size() - 1);
      }

      return resPatEventVo;
   }

   public Date getCurrBqBeginTime(PatEventVo patEventVo, List evenCode1821ListTemp) {
      Date bqBeginTime = patEventVo.getBeginTime();
      if (evenCode1821ListTemp != null && !evenCode1821ListTemp.isEmpty()) {
         evenCode1821ListTemp = (List)evenCode1821ListTemp.stream().sorted(Comparator.comparing(PatEvent::getEndTime)).collect(Collectors.toList());
         PatEventVo resPatEventVo = (PatEventVo)evenCode1821ListTemp.get(evenCode1821ListTemp.size() - 1);
         bqBeginTime = resPatEventVo.getEndTime();
      }

      return bqBeginTime;
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

   @Async
   public List ruleDoR009(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && DateUtils.isTheSameDay(patEventVo.getBeginTime(), s.getCreDate())).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR011(QcAgiRule qcAgiRule, List patEventVoListTemp, List patEventVoListOutAll, List patEventVoListDeadAll, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : (List)patEventVoListTemp.stream().filter((s) -> {
         Date beginTime = this.getEndTime(s.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList())) {
         List<PatEventVo> patEventVoListDead = (List)patEventVoListDeadAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         List<PatEventVo> patEventVoListOut = (List)patEventVoListOutAll.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId())).collect(Collectors.toList());
         if (patEventVoListDead != null && !patEventVoListDead.isEmpty() || patEventVoListOut != null && !patEventVoListOut.isEmpty()) {
            break;
         }

         Date begin = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         List<SubfileIndexVo> patSubfileList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && DateUtils.isTheSameDay(begin, s.getCreateTime())).collect(Collectors.toList());
         if (patSubfileList != null && (patSubfileList == null || !patSubfileList.isEmpty())) {
            Date lastFinishTime = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, patEventVo.getBeginTime()) + " 23:59:59", new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patSubfileList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR018(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date endTime = this.getEndTime(t.getEndTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(endTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         Date endTime = this.getEndTime(patEventVo.getEndTime(), 12, "H");
         List<SubfileIndexVo> patIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getRecoDate().compareTo(endTime) <= 0 && s.getRecoDate().compareTo(patEventVo.getEndTime()) >= 0).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR021(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         Date endTime = this.getEndTime(patEventVo.getEndTime(), 6, "H");
         List<SubfileIndexVo> patIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getCreDate().compareTo(endTime) <= 0 && s.getCreDate().compareTo(patEventVo.getBeginTime()) >= 0).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR023(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         Date endTime = this.getEndTime(patEventVo.getEndTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
         List<SubfileIndexVo> patIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && qcAgiRule.getEmrTypeCode().equals(s.getMrType()) && s.getRecoDate().compareTo(endTime) <= 0 && s.getRecoDate().compareTo(patEventVo.getBeginTime()) >= 0).collect(Collectors.toList());
         if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
            Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList, subIndexLastFinishTimeList, lastFinishTime);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR026(QcAgiRule qcAgiRule, List patEventVoListTemp, List subfileIndexList, List subIndexLastFinishTimeList) throws Exception {
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(qcAgiRule.getEmrTypeCode());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : patEventVoListTemp) {
         Date endTime = this.getEndTime(patEventVo.getEndTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
         Date dbDate = this.commonService.getDbSysdate();
         if (dbDate.compareTo(endTime) > 0) {
            List<SubfileIndexVo> patIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType()) && s.getRecoDate().compareTo(endTime) <= 0 && s.getRecoDate().compareTo(patEventVo.getBeginTime()) >= 0).collect(Collectors.toList());
            if (patIndexList != null && (patIndexList == null || !patIndexList.isEmpty())) {
               Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
               this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList, subIndexLastFinishTimeList, lastFinishTime);
            } else {
               taskPatList.add(patEventVo);
            }
         }

         List<SubfileIndexVo> patIndexList = (List)subfileIndexList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && iterTypeCodeList.contains(s.getMrType())).collect(Collectors.toList());
         if (CollectionUtils.isNotEmpty(patIndexList)) {
            Date lastFinishTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getTrigTimeUnit());
            this.getUpdateLastFinishTimeSubIndex(qcAgiRule, patEventVo, patIndexList, subIndexLastFinishTimeList, lastFinishTime);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   @Async
   public List ruleDoR039(QcAgiRule qcAgiRule, List patEventVoListTemp, List mrHpList, List mrHpLastFinishTimeList) throws Exception {
      Date currDate = this.commonService.getDbSysdate();
      List<PatEventVo> trigPatList = (List)patEventVoListTemp.stream().filter((t) -> {
         Date beginTime = this.getEndTime(t.getBeginTime(), qcAgiRule.getTrigTime(), qcAgiRule.getTrigTimeUnit());
         return currDate.compareTo(beginTime) >= 0;
      }).collect(Collectors.toList());
      List<PatEventVo> taskPatList = new ArrayList(1);

      for(PatEventVo patEventVo : trigPatList) {
         List<MrHp> mrHps = (List)mrHpList.stream().filter((s) -> s.getPatientId().equals(patEventVo.getPatientId()) && s.getMrState().equals("03")).collect(Collectors.toList());
         if (mrHps != null && (mrHps == null || !mrHps.isEmpty())) {
            this.getUpdateLastFinishTimeMrHp(qcAgiRule, patEventVo, mrHps, mrHpLastFinishTimeList);
         } else {
            taskPatList.add(patEventVo);
         }
      }

      List<EmrTaskInfoVo> taskInfoList = this.getEmrTaskInfo(taskPatList, qcAgiRule);
      return taskInfoList;
   }

   public List getRuleData(QcAgiRule qcAgiRule, String patientId, Date beginTime) throws Exception {
      String emrTypeCode = qcAgiRule.getEmrTypeCode();
      List<String> iterTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(emrTypeCode);
      iterTypeCodeList.add(emrTypeCode);
      PatEventVo param = new PatEventVo();
      param.setEventCode(qcAgiRule.getAgiEvnt());
      param.setBeginTime(beginTime);
      param.setMrTypeList(iterTypeCodeList);
      param.setPatientId(patientId);
      List<PatEventVo> patIndexList = this.patEventService.selectListByEventCodeAndBeginTime(param);
      return patIndexList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void agiRuleDo(QcAgiRule qcAgiRule, List patEventIndexList) throws Exception {
      PatEventVo patientVo = (PatEventVo)patEventIndexList.get(0);
      Date currDate = this.commonService.getDbSysdate();
      Date endTime = this.getEndTime(currDate, qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
      String ruleTip = this.getRuleTip(patientVo, qcAgiRule, endTime, "", "");
      List<PatEventVo> tempList = (List)patEventIndexList.stream().filter((t) -> t.getIndexId() != null).collect(Collectors.toList());
      if (tempList == null || tempList != null && tempList.isEmpty()) {
         EmrTaskInfo taskInfo = new EmrTaskInfo();
         taskInfo.setInpNo(patientVo.getInpNo());
         taskInfo.setPatientId(patientVo.getPatientId());
         taskInfo.setPatientName(patientVo.getPatientName());
         taskInfo.setDeptCd(patientVo.getDeptCd());
         taskInfo.setDeptName(patientVo.getDeptName());
         taskInfo.setTaskType("1");
         taskInfo.setTaskTypeName("病历书写");
         taskInfo.setTaskAppDoc("sys");
         taskInfo.setTaskAppDocName("系统");
         taskInfo.setBeginTime(currDate);
         taskInfo.setLimitTime(qcAgiRule.getFinishLimitTime());
         taskInfo.setLimitTimeUnit(qcAgiRule.getLimitTimeUnit());
         taskInfo.setEndTime(endTime);
         taskInfo.setBusId(qcAgiRule.getId().toString());
         taskInfo.setBusName(qcAgiRule.getRuleName());
         taskInfo.setMark(ruleTip);
         taskInfo.setDocCd(patientVo.getResDocCd());
         taskInfo.setDocName(patientVo.getResDocName());
         taskInfo.setTreatFlag("0");
         taskInfo.setEventNo(patientVo.getEventNo());
         this.taskInfoService.insertEmrTaskInfo(taskInfo);
      }

   }

   public EmrTaskInfo getIndexLastFinishTime(String patientId, String emrTypeCode, boolean mainFileFlag) throws Exception {
      List<EmrTaskInfo> list = new ArrayList(1);
      EmrTaskInfo emrTaskInfoRes = null;
      List<QcAgiRuleVo> ruleEventVoList = this.qcAgiRuleMapper.selectQcAgiRuleEventVoList(patientId, emrTypeCode);
      ruleEventVoList = mainFileFlag ? (List)ruleEventVoList.stream().filter((t) -> t.getLimitTimeUnit().equals("D")).collect(Collectors.toList()) : ruleEventVoList;
      if (ruleEventVoList != null && !ruleEventVoList.isEmpty()) {
         for(QcAgiRuleVo qcAgiRuleVo : ruleEventVoList) {
            EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
            Date endTime = this.getEndTime(qcAgiRuleVo.getBeginTime(), qcAgiRuleVo.getFinishLimitTime(), qcAgiRuleVo.getLimitTimeUnit());
            if (mainFileFlag) {
               if (qcAgiRuleVo.getLimitTimeUnit().equals("D")) {
                  emrTaskInfo.setEndTime(endTime);
                  emrTaskInfo.setBusId(String.valueOf(qcAgiRuleVo.getId()));
                  list.add(emrTaskInfo);
               }
            } else {
               emrTaskInfo.setEndTime(endTime);
               emrTaskInfo.setBusId(String.valueOf(qcAgiRuleVo.getId()));
               list.add(emrTaskInfo);
            }
         }

         list = (List)list.stream().sorted(Comparator.comparing(EmrTaskInfo::getEndTime)).collect(Collectors.toList());
         emrTaskInfoRes = (EmrTaskInfo)list.get(list.size() - 1);
      }

      return emrTaskInfoRes;
   }

   public List getUnWriteList(String orgCd, String patientId) throws Exception {
      List<EmrTaskInfoVo> taskListAll = new ArrayList(1);
      PatEventVo patEventParam = new PatEventVo(orgCd, patientId);
      List<PatEventVo> patEventVoList = this.patEventService.selectListByPatient(patEventParam);
      QcAgiRule agiRuleParam = new QcAgiRule("1", "1", "0");
      List<QcAgiRule> agiRuleList = this.qcAgiRuleMapper.selectList(agiRuleParam);
      if (patEventVoList != null && !patEventVoList.isEmpty() && agiRuleList != null && !agiRuleList.isEmpty()) {
         Map<String, List<PatEventVo>> patEventVoListMap = (Map)patEventVoList.stream().collect(Collectors.groupingBy((t) -> t.getEventCode()));
         List<PatEventVo> patEventVoListDeadAll = (List<PatEventVo>)(patEventVoListMap.get("03") != null ? (List)patEventVoListMap.get("03") : new ArrayList(1));
         List<PatEventVo> patEventVoListOutAll = (List<PatEventVo>)(patEventVoListMap.get("02") != null ? (List)patEventVoListMap.get("02") : new ArrayList(1));
         Map<String, List<EmrTaskInfo>> emrTaskInfoListMap = new HashMap(1);
         List<Index> indexLastFinishTimeList = new ArrayList(1);
         List<SubfileIndex> subIndexLastFinishTimeList = new ArrayList(1);
         List<MrHp> mrHpLastFinishTimeList = new ArrayList(1);
         PatEventVo eventParam = new PatEventVo();
         eventParam.setOrgCd(orgCd);
         eventParam.setPatientId(patientId);
         List<Index> indexList = this.indexService.selectPatIndexList(eventParam);
         List<SubfileIndexVo> subfileIndexList = this.subfileIndexService.selectPatIndexList(eventParam);
         List<MrHp> mrHpList = this.mrHpService.selectPatMrHpList(eventParam);

         for(QcAgiRule qcAgiRule : agiRuleList) {
            this.getTaskAndFinishedList(qcAgiRule, patEventVoListMap, emrTaskInfoListMap, patEventVoList, taskListAll, indexList, subfileIndexList, mrHpList, indexLastFinishTimeList, subIndexLastFinishTimeList, mrHpLastFinishTimeList, patEventVoListDeadAll, patEventVoListOutAll);
         }
      }

      List var21 = (List)taskListAll.stream().sorted(Comparator.comparing(EmrTaskInfo::getBeginTime)).collect(Collectors.toList());
      return var21;
   }

   private Date getEndTime(Date currDate, Integer finishLimitTime, String limitTimeUnit) {
      Date resDate = null;
      switch (limitTimeUnit) {
         case "H":
            resDate = DateUtils.addHours(currDate, finishLimitTime);
            break;
         case "D":
            resDate = DateUtils.addDays(currDate, finishLimitTime);
      }

      return resDate;
   }

   private String getRuleTip(PatEvent patEvent, QcAgiRule qcAgiRule, Date endTime, String bqName, String beApartDayNum) {
      String ruleTip = qcAgiRule.getRuleTip();
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
            Date endTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
            emrTaskInfo.setEndTime(endTime);
            String ruleTip = this.getRuleTip(patEventVo, qcAgiRule, endTime, "", "");
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
      String ruleTip = this.getRuleTip(patEventVo, qcAgiRule, endTime, bqName, beApartDayNum);
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

   private List getUpdateLastFinishTimeIndex(List lftIndexPatEventList, QcAgiRule qcAgiRule, List hasMrTypeIndexList) {
      List<Index> indexList = new ArrayList(1);
      if (qcAgiRule.getLimitTimeVeriValid().equals("1")) {
         Map<String, List<Index>> hasMrTypeIndexListMap = (Map)hasMrTypeIndexList.stream().collect(Collectors.groupingBy((t) -> t.getPatientId()));

         for(PatEventVo patEventVo : lftIndexPatEventList) {
            Date endTime = this.getEndTime(patEventVo.getBeginTime(), qcAgiRule.getFinishLimitTime(), qcAgiRule.getLimitTimeUnit());
            String patientId = patEventVo.getPatientId();
            List<Index> tempIndexList = (List)hasMrTypeIndexListMap.get(patientId);
            if (tempIndexList != null && !tempIndexList.isEmpty()) {
               tempIndexList.forEach((t) -> {
                  Index index = new Index();
                  index.setId(t.getId());
                  index.setLastFinishTime(endTime);
                  indexList.add(index);
               });
            }
         }
      }

      return indexList;
   }

   public List selectOperBeforeList(String eventCd) throws Exception {
      return this.qcAgiRuleMapper.selectOperBeforeList(eventCd);
   }
}
