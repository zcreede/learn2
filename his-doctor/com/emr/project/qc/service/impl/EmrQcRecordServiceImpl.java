package com.emr.project.qc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.constant.Constants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpCheckResultVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.pat.domain.TdNaAllergyRecord;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcFlowScore;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.QcRuleResultVo;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckedVo;
import com.emr.project.qc.mapper.EmrQcFlowScoreMapper;
import com.emr.project.qc.mapper.EmrQcRecordMapper;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.qc.service.IEmrQcCommRecordService;
import com.emr.project.qc.service.IEmrQcFlowScoreService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcFlowStatisService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrQcRecordServiceImpl implements IEmrQcRecordService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private EmrQcRecordMapper emrQcRecordMapper;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Value("${token.expireTime}")
   private int expireTime;
   @Autowired
   private IEmrQcCommRecordService emrQcCommRecordService;
   @Autowired
   private IEmrQcFlowScoreService emrQcFlowScoreService;
   @Autowired
   private IEmrQcFlowStatisService emrQcFlowStatisService;
   @Autowired
   private EmrQcFlowScoreMapper emrQcFlowScoreMapper;

   public EmrQcRecord selectEmrQcRecordById(Long id) {
      return this.emrQcRecordMapper.selectEmrQcRecordById(id);
   }

   public EmrQcRecord selectEmrQcRecordByPatientSection(String patientId, String qcSection, Long qcBillNo) {
      return this.emrQcRecordMapper.selectEmrQcRecordByPatientSection(patientId, qcSection, qcBillNo);
   }

   public List selectEmrQcRecordList(EmrQcRecord emrQcRecord) {
      return this.emrQcRecordMapper.selectEmrQcRecordList(emrQcRecord);
   }

   public int insertEmrQcRecord(EmrQcRecord emrQcRecord) {
      return this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
   }

   public int updateEmrQcRecord(EmrQcRecord emrQcRecord) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcRecord.setAltPerCode(basEmployee.getEmplNumber());
      emrQcRecord.setAltPerName(basEmployee.getEmplName());
      return this.emrQcRecordMapper.updateEmrQcRecord(emrQcRecord);
   }

   public int deleteEmrQcRecordByIds(Long[] ids) {
      return this.emrQcRecordMapper.deleteEmrQcRecordByIds(ids);
   }

   public int deleteEmrQcRecordById(Long id) {
      return this.emrQcRecordMapper.deleteEmrQcRecordById(id);
   }

   public List getCheckResultQcList(MrHpVo mrHpVo, List checkSetList, List checkResultVoList, List qcExcepationList) throws Exception {
      List<EmrQcListVo> emrQcListList = new ArrayList(1);
      Map<Long, List<MrHpCheckResultVo>> checkResultVoMap = (Map)checkResultVoList.stream().collect(Collectors.groupingBy((t) -> t.getCheckId()));

      for(MrHpCheckSet mrHpCheckSet : checkSetList) {
         Long checkId = mrHpCheckSet.getId();
         Boolean checkResult = Boolean.parseBoolean(mrHpCheckSet.getCheckResult());
         Boolean checkHasConditions = StringUtils.isNotBlank(mrHpCheckSet.getCheckHasConditions()) && mrHpCheckSet.getCheckHasConditions().equals("1");
         List<MrHpCheckResultVo> setResultVoList = (List)checkResultVoMap.get(checkId);
         Map<String, Object> errorMap = this.getErrorInfo(checkResult, checkHasConditions, setResultVoList, mrHpCheckSet);
         boolean isError = (Boolean)errorMap.get("isError");
         List<MrHpCheckResultVo> contentResList = errorMap.get("contentResList") != null ? (List)errorMap.get("contentResList") : null;
         if (isError) {
            List<EmrQcListVo> emrQcListListTemp = new ArrayList(1);
            this.getEmrQcLists(emrQcListListTemp, mrHpVo, mrHpCheckSet, contentResList, qcExcepationList, checkResult);
            emrQcListList.addAll(emrQcListListTemp);
         }
      }

      return emrQcListList;
   }

   private void getEmrQcLists(List emrQcListListTemp, MrHpVo mrHpVo, MrHpCheckSet mrHpCheckSet, List contentResList, List qcExcepationList, Boolean checkResult) throws Exception {
      if (checkResult) {
         if (!mrHpCheckSet.getCheckClassNo().equals("04") && !mrHpCheckSet.getCheckClassNo().equals("05")) {
            boolean flag = false;

            for(MrHpCheckResultVo mrHpCheckResultVo : contentResList) {
               boolean ErrorFlag = false;

               for(EmrQcListVo emrQcListVo : qcExcepationList) {
                  if (emrQcListVo.getRuleId().equals(mrHpCheckResultVo.getCheckId()) && mrHpCheckResultVo.getErrorColumn().equals(emrQcListVo.getEmrEleId())) {
                     ErrorFlag = true;
                     break;
                  }
               }

               if (!ErrorFlag) {
                  EmrQcListVo emrQcList = this.getEmrQcList(mrHpVo, mrHpCheckSet);
                  switch (mrHpCheckSet.getCheckTypeNo()) {
                     case "0102":
                     case "0302":
                        JSONArray jsonArray = JSONArray.parseArray(mrHpCheckSet.getCheckColumn2());
                        this.getEmrEleIdEleName(jsonArray, emrQcList, mrHpCheckResultVo);
                        break;
                     case "0201":
                     case "0202":
                     case "0203":
                     case "0204":
                     case "0303":
                     case "0101":
                        JSONArray jsonArray02 = JSONArray.parseArray(mrHpCheckSet.getCheckColumn1());
                        this.getEmrEleIdEleName(jsonArray02, emrQcList, mrHpCheckResultVo);
                        break;
                     case "0301":
                        JSONObject checkExp10301 = JSON.parseObject(mrHpCheckSet.getCheckExpression2());
                        JSONArray columnArr0301 = checkExp10301.getJSONArray("checkExpressionVoList");
                        this.getEmrEleIdEleName(columnArr0301, emrQcList, mrHpCheckResultVo);
                        emrQcListListTemp.add(emrQcList);
                        flag = true;
                  }

                  if (flag) {
                     break;
                  }

                  emrQcListListTemp.add(emrQcList);
               }
            }
         } else {
            for(MrHpCheckResultVo mrHpCheckResultVo : contentResList) {
               boolean flag = false;
               EmrQcListVo emrQcList = this.getEmrQcList(mrHpVo, mrHpCheckSet);
               emrQcList.setDefectRow(mrHpCheckResultVo.getErrorColumn());
               JSONObject jsonObject = JSONObject.parseObject(mrHpCheckSet.getCheckExpression2());
               if (mrHpCheckSet.getCheckTypeNo().equals("诊断之间逻辑校验") || mrHpCheckSet.getCheckTypeNo().equals("手术之间逻辑校验")) {
                  jsonObject = JSONObject.parseObject(mrHpCheckSet.getCheckExpression1());
               }

               JSONArray arr = jsonObject.getJSONArray("checkExpressionVoList");
               JSONObject obj = arr.getJSONObject(0);
               String fieldProp = obj.getString("fieldProp");
               String fieldName = obj.getString("fieldName");

               for(EmrQcListVo emrQcListVo : qcExcepationList) {
                  long rule = emrQcListVo.getRuleId();
                  long rule1 = mrHpCheckResultVo.getCheckId();
                  if (rule == rule1 && emrQcListVo.getDefectRow().equals(mrHpCheckResultVo.getErrorColumn()) && emrQcListVo.getEmrEleId().equals(fieldProp)) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  emrQcList.setEmrEleId(fieldProp);
                  emrQcList.setEmrEleName(fieldName);
                  emrQcListListTemp.add(emrQcList);
               }
            }
         }
      } else {
         List<EmrQcListVo> emrQcListVoList = new ArrayList();
         switch (mrHpCheckSet.getCheckClassNo() + mrHpCheckSet.getCheckTypeNo()) {
            case "010102":
            case "030302":
               JSONArray jsonArray = JSONArray.parseArray(mrHpCheckSet.getCheckColumn2());
               this.getReturnFalseElem(jsonArray, emrQcListVoList, contentResList, mrHpVo, mrHpCheckSet, qcExcepationList);
               break;
            case "020201":
            case "020202":
            case "020203":
            case "020204":
            case "010101":
            case "030303":
               JSONArray jsonArray02 = JSONArray.parseArray(mrHpCheckSet.getCheckColumn1());
               this.getReturnFalseElem(jsonArray02, emrQcListVoList, contentResList, mrHpVo, mrHpCheckSet, qcExcepationList);
               break;
            case "030301":
               JSONObject checkExp10301 = JSON.parseObject(mrHpCheckSet.getCheckExpression2());
               JSONArray columnArr0301 = checkExp10301.getJSONArray("checkExpressionVoList");
               this.getReturnFalseElem(columnArr0301, emrQcListVoList, contentResList, mrHpVo, mrHpCheckSet, qcExcepationList);
         }

         emrQcListListTemp.addAll(emrQcListVoList);
      }

   }

   private void getReturnFalseElem(JSONArray columnArr, List emrQcListVoList, List contentResList, MrHpVo mrHpVo, MrHpCheckSet mrHpCheckSet, List qcExcepationList) throws Exception {
      List<String> columnList = (List<String>)(contentResList != null && !contentResList.isEmpty() ? (List)contentResList.stream().map((s) -> s.getErrorColumn()).collect(Collectors.toList()) : new ArrayList(1));

      for(int i = 0; i < columnArr.size(); ++i) {
         JSONObject obj = columnArr.getJSONObject(i);
         String fieldProp = obj.getString("fieldProp");
         String fieldName = obj.getString("fieldName");
         fieldProp = StringUtils.isBlank(fieldProp) ? obj.getString("feeTypeCode") : fieldProp;
         fieldName = StringUtils.isBlank(fieldName) ? obj.getString("feeTypeName") : fieldName;
         if (!columnList.contains(fieldProp)) {
            boolean flag = false;

            for(EmrQcListVo emrQcListVo : qcExcepationList) {
               if (emrQcListVo.getRuleId().equals(mrHpCheckSet.getId()) && emrQcListVo.getEmrEleId().equals(fieldProp)) {
                  flag = true;
                  break;
               }
            }

            if (flag) {
               break;
            }

            EmrQcListVo emrQcListVo = this.getEmrQcList(mrHpVo, mrHpCheckSet);
            emrQcListVo.setEmrEleId(fieldProp);
            emrQcListVo.setEmrEleName(fieldName);
            emrQcListVoList.add(emrQcListVo);
            if (mrHpCheckSet.getCheckTypeNo().equals("0301")) {
               break;
            }
         }
      }

   }

   private void getEmrEleIdEleName(JSONArray columnArr, EmrQcListVo emrQcList, MrHpCheckResultVo mrHpCheckResultVo) {
      for(int i = 0; i < columnArr.size(); ++i) {
         JSONObject obj = columnArr.getJSONObject(i);
         String fieldProp = obj.getString("fieldProp");
         String fieldName = obj.getString("fieldName");
         fieldProp = StringUtils.isBlank(fieldProp) ? obj.getString("feeTypeCode") : fieldProp;
         fieldName = StringUtils.isBlank(fieldName) ? obj.getString("feeTypeName") : fieldName;
         if (fieldProp.equals(mrHpCheckResultVo.getErrorColumn())) {
            emrQcList.setEmrEleId(fieldProp);
            emrQcList.setEmrEleName(fieldName);
            break;
         }
      }

   }

   private EmrQcListVo getEmrQcList(MrHpVo mrHpVo, MrHpCheckSet mrHpCheckSet) throws Exception {
      EmrQcListVo emrQcList = new EmrQcListVo();
      emrQcList.setPatientId(mrHpVo.getPatientId());
      emrQcList.setMrType("61");
      emrQcList.setMrTypeName("病案首页");
      emrQcList.setRuleName(mrHpCheckSet.getCheckName());
      emrQcList.setFlawDesc(mrHpCheckSet.getCheckTips());
      emrQcList.setMrFileId(mrHpVo.getRecordId());
      emrQcList.setMrFileName("病案首页");
      emrQcList.setQcState("0");
      emrQcList.setQcdoctCd("sys");
      emrQcList.setQcdoctName("系统");
      emrQcList.setQcSection("01");
      BasEmployee employee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcList.setCrePerCode(employee.getEmplNumber());
      emrQcList.setCrePerName(employee.getEmplName());
      emrQcList.setDefeLevel(mrHpCheckSet.getCheckLevel());
      emrQcList.setRuleId(mrHpCheckSet.getId());
      emrQcList.setQcDate(this.commonService.getDbSysdate());
      return emrQcList;
   }

   private Map getErrorInfo(Boolean checkResult, Boolean checkHasConditions, List setResultVoList, MrHpCheckSet mrHpCheckSet) {
      Map<String, Object> errorMap = new HashMap(1);
      boolean isError = false;
      List<MrHpCheckResultVo> contentResList = setResultVoList;
      if (checkHasConditions) {
         List<MrHpCheckResultVo> conditionsResList = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getIsConditionsRes() != null && t.getIsConditionsRes()).collect(Collectors.toList()) : null;
         Boolean conditionsRes = conditionsResList != null && !conditionsResList.isEmpty();
         if (conditionsRes) {
            contentResList = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getIsConditionsRes() == null).collect(Collectors.toList()) : null;
            if (!checkResult) {
               isError = true;
            } else {
               isError = contentResList != null && !contentResList.isEmpty();
            }

            if ("0301".equals(mrHpCheckSet.getCheckTypeNo())) {
               contentResList = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getConditionsRes2() == null && t.getIsConditionsRes() == null).collect(Collectors.toList()) : null;
               List<MrHpCheckResultVo> conditionsResList2 = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getConditionsRes2() != null && t.getConditionsRes2()).collect(Collectors.toList()) : null;
               if (checkResult) {
                  isError = conditionsResList2 != null && !conditionsResList2.isEmpty();
               } else {
                  isError = true;
               }
            }
         } else {
            contentResList = null;
         }
      } else if (checkResult) {
         isError = setResultVoList != null && !setResultVoList.isEmpty();
      } else {
         isError = true;
      }

      errorMap.put("isError", isError);
      errorMap.put("contentResList", contentResList);
      return errorMap;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveMrHpQcRecord(MrHpVo mrHpVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrQcRecord emrQcRecord = new EmrQcRecord();
      emrQcRecord.setId(SnowIdUtils.uniqueLong());
      emrQcRecord.setPatientId(mrHpVo.getPatientId());
      emrQcRecord.setPatientName(mrHpVo.getPatientName());
      emrQcRecord.setSex(mrHpVo.getSexCd());
      emrQcRecord.setInpNo(mrHpVo.getInpNo());
      emrQcRecord.setInDeptCd(mrHpVo.getInDeptCd());
      emrQcRecord.setInDeptName(mrHpVo.getInDeptName());
      emrQcRecord.setQcSection("01");
      emrQcRecord.setQcdoctCd("sys");
      emrQcRecord.setQcdoctName("系统");
      emrQcRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcRecord.setCrePerName(basEmployee.getEmplName());
      if (mrHpVo.getEmrQcListList() != null && !mrHpVo.getEmrQcListList().isEmpty()) {
         List<EmrQcList> insertEmrQcListList = new ArrayList(mrHpVo.getEmrQcListList().size());
         List<EmrQcCommRecord> insertQcCommRecords = new ArrayList();

         for(EmrQcListVo emrQcListVo : mrHpVo.getEmrQcListList()) {
            Long id = SnowIdUtils.uniqueLong();
            if (emrQcListVo.getId() == null) {
               emrQcListVo.setMainId(emrQcRecord.getId());
               emrQcListVo.setId(id);
               emrQcListVo.setMrFileId(mrHpVo.getRecordId());
               insertEmrQcListList.add(emrQcListVo);
               if (emrQcListVo.getQcCommRecordList() != null && !emrQcListVo.getQcCommRecordList().isEmpty()) {
                  for(EmrQcCommRecord emrQcCommRecord : emrQcListVo.getQcCommRecordList()) {
                     if (emrQcCommRecord.getId() == null) {
                        emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
                        emrQcCommRecord.setMainId(id);
                        emrQcCommRecord.setMrFileId(mrHpVo.getRecordId());
                        emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
                        emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
                        insertQcCommRecords.add(emrQcCommRecord);
                     }
                  }
               }
            }
         }

         if (insertEmrQcListList != null && insertEmrQcListList.size() > 0) {
            this.emrQcListService.insertEmrQcLists(insertEmrQcListList);
         }

         if (insertQcCommRecords != null && insertQcCommRecords.size() > 0) {
            this.emrQcCommRecordService.insertList(insertQcCommRecords);
         }
      }

      this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
   }

   public List selectRunTimeQcChecked(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception {
      List<RunTimeQcCheckedVo> list = this.emrQcRecordMapper.selectRunTimeQcChecked(runTimeQcCheckedVo);
      if (list != null && !list.isEmpty()) {
         Map<String, List<VisitinfoVo>> visitinfoVoLists = this.visitinfoService.selectColorplan();
         List<VisitinfoVo> visitinfoVoList = (List)visitinfoVoLists.get("4");
         Map<String, VisitinfoVo> visitinfoMap = (Map)visitinfoVoList.stream().collect(Collectors.toMap((t) -> t.getColorTypeCd(), Function.identity()));
         List<VisitinfoVo> colorList = (List)visitinfoVoLists.get("2");
         Map<String, VisitinfoVo> colorMap = (Map)colorList.stream().collect(Collectors.toMap((t) -> t.getColorTypeCd(), Function.identity()));
         List<String> inpNoList = (List)list.stream().map((t) -> t.getInpNo()).collect(Collectors.toList());
         List<TdNaAllergyRecord> alleInfoList = this.alleInfoService.selectAlleInfosByInpNos(inpNoList);
         Map<String, List<TdNaAllergyRecord>> patientAlleInfoMap = (Map<String, List<TdNaAllergyRecord>>)(alleInfoList.isEmpty() ? new HashMap(1) : (Map)alleInfoList.stream().collect(Collectors.groupingBy(TdNaAllergyRecord::getAdmissionNo)));

         for(RunTimeQcCheckedVo rt : list) {
            String age = AgeUtil.getAgeStr(rt.getAgeY(), rt.getAgeM(), rt.getAgeD(), rt.getAgeH(), rt.getAgeMi());
            rt.setAge(age);
            rt.setOperIcon(((VisitinfoVo)visitinfoMap.get("operFlag")).getColorValue());
            rt.setConsIcon(((VisitinfoVo)visitinfoMap.get("consFlag")).getColorValue());
            rt.setDieIcon(((VisitinfoVo)visitinfoMap.get("dieFlag")).getColorValue());
            rt.setBloodIcon(((VisitinfoVo)visitinfoMap.get("bloodTrans")).getColorValue());
            rt.setChangeIcon(((VisitinfoVo)visitinfoMap.get("changeFlag")).getColorValue());
            rt.setInfectIcon(((VisitinfoVo)visitinfoMap.get("infectFlag")).getColorValue());
            rt.setRescueIcon(((VisitinfoVo)visitinfoMap.get("rescueFlag")).getColorValue());
            rt.setDayNumIcon(((VisitinfoVo)visitinfoMap.get("dayNumFlag")).getColorValue());
            rt.setCostSumIcon(((VisitinfoVo)visitinfoMap.get("costSumFlag")).getColorValue());
            rt.setDayNum(DateUtils.getDiffDay(rt.getOutTime() == null ? new Date() : rt.getOutTime(), rt.getInhosTime()));
            rt.setStateName(this.getRunTimeQcCheckStateName(rt.getState()));
            String colorValue = colorMap != null && colorMap.get(rt.getNgCd()) != null ? ((VisitinfoVo)colorMap.get(rt.getNgCd())).getColorValue() : null;
            rt.setColorValue(colorValue);
            List<TdNaAllergyRecord> patientAlleInfoList = (List)patientAlleInfoMap.get(rt.getInpNo());
            List<String> patientAlleInfoStrList = patientAlleInfoList != null && !patientAlleInfoList.isEmpty() ? (List)patientAlleInfoList.stream().map((t) -> t.getAllergyName()).collect(Collectors.toList()) : null;
            String patientAlleInfoStr = patientAlleInfoStrList != null && !patientAlleInfoStrList.isEmpty() ? String.join(",", patientAlleInfoStrList) : null;
            rt.setAlleInfo(patientAlleInfoStr);
         }
      }

      return list;
   }

   private String getRunTimeQcCheckStateName(String state) {
      String stateName = null;
      switch (state) {
         case "0":
            stateName = "未质控";
            break;
         case "1":
            stateName = "质控中";
            break;
         case "2":
            stateName = "质控完成";
            break;
         case "3":
            stateName = "已退回";
            break;
         case "31":
            stateName = "已修改";
      }

      return stateName;
   }

   public List selectByPatientRunTimeQcNum(List patientIdList, String qcSection) {
      return this.emrQcRecordMapper.selectByPatientRunTimeQcNum(patientIdList, qcSection);
   }

   public RunTimeQcCheckedVo selectRunTimeQcCheckedOne(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception {
      return this.emrQcRecordMapper.selectRunTimeQcCheckedOne(runTimeQcCheckedVo);
   }

   public EmrQcRecord randomCheck(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception {
      EmrQcRecord emrQcRecord = this.qcDoing(runTimeQcCheckVo.getPatientId(), "03", runTimeQcCheckVo.getQcBillNo());
      return emrQcRecord;
   }

   public EmrQcRecord qcDoing(String patientId, String qcSection, Long qcBillNo) throws Exception {
      EmrQcRecord emrQcRecord = this.emrQcRecordMapper.selectEmrQcRecordByPatientSection(patientId, qcSection, qcBillNo);
      if (emrQcRecord == null) {
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(patientId);
         BasEmployee employee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
         emrQcRecord = new EmrQcRecord();
         emrQcRecord.setId(SnowIdUtils.uniqueLong());
         emrQcRecord.setPatientId(patientId);
         emrQcRecord.setPatientName(visitinfoVo.getPatientName());
         emrQcRecord.setSex(visitinfoVo.getSex());
         emrQcRecord.setInpNo(visitinfoVo.getInpNo());
         emrQcRecord.setInDeptCd(visitinfoVo.getDeptCd());
         emrQcRecord.setInDeptName(visitinfoVo.getDeptName());
         emrQcRecord.setQcSection(qcSection);
         emrQcRecord.setQcdoctCd(employee.getEmplNumber());
         emrQcRecord.setQcdoctName(employee.getEmplName());
         emrQcRecord.setCrePerCode(employee.getEmplNumber());
         emrQcRecord.setCrePerName(employee.getEmplName());
         emrQcRecord.setState("1");
         emrQcRecord.setStateName("质控中");
         emrQcRecord.setQcBillNo(qcBillNo);
         this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
      }

      return emrQcRecord;
   }

   public void randomVerify(RunTimeQcCheckedVo runTimeQcCheckedVo) throws Exception {
      this.redisCache.setCacheObject("random_verify:" + runTimeQcCheckedVo.getId(), runTimeQcCheckedVo, Constants.LOGIN_ERROR_EXPIRATION, TimeUnit.MINUTES);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public EmrQcRecord randomCheckFinish(EmrQcRecord emrQcRecord, EmrQcFlowScoreVo emrQcFlowScoreVo, String ip) throws Exception {
      this.log.warn("质控保存-抽查质控保存开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<EmrQcList> list = this.emrQcListService.selectNoFinishListByPatients(emrQcRecord.getPatientId(), emrQcRecord.getId());
      emrQcRecord.setState(list.isEmpty() ? "2" : "3");
      emrQcRecord.setStateName(list.isEmpty() ? "质控完成" : "已退回");
      this.log.warn("质控保存-抽查质控更新record表开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.updateEmrQcRecord(emrQcRecord);
      EmrQcFlowScore emrQcFlowScore = new EmrQcFlowScore();
      emrQcFlowScore.setPatientId(emrQcFlowScoreVo.getPatientId());
      emrQcFlowScore.setOrgCd(user.getHospital().getOrgCode());
      emrQcFlowScore.setQcSn(emrQcFlowScoreVo.getRecordId().toString());
      List<EmrQcFlowScore> emrQcFlowScoreList = this.emrQcFlowScoreMapper.selectEmrQcFlowScoreList(emrQcFlowScore);
      if (emrQcFlowScoreList == null || emrQcFlowScoreList.isEmpty()) {
         this.emrQcFlowScoreService.saveEmrQcFlowScoreVo(emrQcFlowScoreVo);
      }

      EmrQcFlowVo emrQcFlowVo = new EmrQcFlowVo(emrQcRecord.getPatientId(), emrQcFlowScoreVo.getLevelCode(), emrQcFlowScoreVo.getLevelName(), emrQcFlowScoreVo.getTotalScore(), "00");
      this.log.warn("质控保存-抽查质控保存flow表开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcFlowService.saveEmrQcFlow(emrQcFlowVo, ip, emrQcRecord.getQcBillNo());
      emrQcRecord.setStateName(this.getRunTimeQcCheckStateName(emrQcRecord.getState()));
      list = (List)list.stream().filter((t) -> t.getMissingFileFlag().equals(0)).collect(Collectors.toList());
      this.log.warn("质控保存-抽查质控保存消息开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrMessageService.getSaveEmrMessageList(emrQcRecord, list);
      List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
      if (messageList != null && !messageList.isEmpty()) {
         String messageKey = "message_key:" + user.getUserName();
         this.redisCache.deleteObject(messageKey);
         this.redisCache.setCacheList(messageKey, messageList);
      }

      return emrQcRecord;
   }

   public void randomVerifyFinish(Long id) throws Exception {
   }

   public List selectListByQcBillNos(List qcBillNoList) throws Exception {
      return this.emrQcRecordMapper.selectListByQcBillNos(qcBillNoList);
   }

   public void insertList(List list) throws Exception {
      this.emrQcRecordMapper.insertList(list);
   }

   public List getEmrCheckResultQcList(IndexSaveVo indexSaveVo, List qcRulesVoList, List qcExcepationList) throws Exception {
      BasEmployee employee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrQcListVo> emrQcListList = new ArrayList(1);
      List<QcRuleResultVo> resultVoList = indexSaveVo.getResultVoList();
      Map<Long, List<QcRuleResultVo>> checkResultVoMap = (Map)resultVoList.stream().collect(Collectors.groupingBy((t) -> t.getCheckId()));

      for(QcRulesVo qcRulesVo : qcRulesVoList) {
         Long checkId = qcRulesVo.getId();
         List<QcRuleResultVo> setResultVoList = (List)checkResultVoMap.get(checkId);
         this.getResultVoList(setResultVoList, qcRulesVo);
         if (setResultVoList != null && setResultVoList.size() > 0) {
            for(QcRuleResultVo qcRuleResultVo : setResultVoList) {
               boolean flag = false;

               for(EmrQcListVo emrQcListVo : qcExcepationList) {
                  long rule = emrQcListVo.getRuleId();
                  long rule1 = qcRuleResultVo.getCheckId();
                  if (rule == rule1 && emrQcListVo.getEmrEleId().equals(qcRuleResultVo.getContElemName())) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  EmrQcListVo emrQcListVo = new EmrQcListVo();
                  emrQcListVo.setPatientId(indexSaveVo.getPatientId());
                  emrQcListVo.setMrType(indexSaveVo.getEmrTypeCode());
                  emrQcListVo.setMrTypeName(indexSaveVo.getEmrTypeName());
                  emrQcListVo.setRuleName(qcRulesVo.getRuleName());
                  emrQcListVo.setFlawDesc(qcRulesVo.getRuleDesc());
                  emrQcListVo.setQcState("0");
                  emrQcListVo.setQcDate(new Date());
                  emrQcListVo.setQcdoctCd("sys");
                  emrQcListVo.setQcdoctName("系统");
                  emrQcListVo.setQcSection("01");
                  emrQcListVo.setDoctCd(employee.getEmplNumber());
                  emrQcListVo.setDoctName(employee.getEmplName());
                  emrQcListVo.setCrePerCode(employee.getEmplNumber());
                  emrQcListVo.setCrePerName(employee.getEmplName());
                  emrQcListVo.setRuleId(qcRulesVo.getId());
                  emrQcListVo.setEmrEleId(qcRuleResultVo.getContElemName());
                  if ("NNNNNN".equals(qcRuleResultVo.getElemId())) {
                     emrQcListVo.setDbEleId(CommonConstants.EMR_QC_FEED.EMR_QC_FEED);
                  } else {
                     emrQcListVo.setDbEleId(qcRuleResultVo.getElemId() != null ? Long.valueOf(qcRuleResultVo.getElemId()) : CommonConstants.EMR_QC_FEED.EMR_QC_FEED);
                  }

                  if ("NNNNNN".equals(qcRuleResultVo.getElemName())) {
                     emrQcListVo.setEmrEleName("人工反馈");
                  } else {
                     emrQcListVo.setEmrEleName(qcRuleResultVo.getElemName() != null ? qcRuleResultVo.getElemName() : "人工反馈");
                  }

                  emrQcListVo.setDefeLevel(qcRulesVo.getDefeLevel());
                  emrQcListVo.setMrFileName(indexSaveVo.getMrFileShowName());
                  emrQcListList.add(emrQcListVo);
               }
            }
         }
      }

      return emrQcListList;
   }

   private void getResultVoList(List setResultVoList, QcRulesVo qcRulesVo) {
      List<QcRuleResultVo> conditionsRes1 = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getIsConditionsRes() != null && t.getIsConditionsRes()).collect(Collectors.toList()) : null;
      List<QcRuleResultVo> conditionsRes2 = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getConditionsRes2() != null && t.getConditionsRes2()).collect(Collectors.toList()) : null;
      List<QcRuleResultVo> resultVoList = setResultVoList != null && !setResultVoList.isEmpty() ? (List)setResultVoList.stream().filter((t) -> t.getConditionsRes2() == null && t.getIsConditionsRes() == null).collect(Collectors.toList()) : null;
      if (qcRulesVo.getRuleType().equals(CommonConstants.QC.RULE_TYPE_CODE_2) && qcRulesVo.getRuleClass().equals("2")) {
         if (conditionsRes1 != null && !conditionsRes1.isEmpty()) {
            setResultVoList.clear();
            if (resultVoList != null && !resultVoList.isEmpty()) {
               setResultVoList.add(resultVoList.get(0));
            }
         } else if (resultVoList != null && !resultVoList.isEmpty()) {
            setResultVoList.clear();
         }
      } else if (qcRulesVo.getRuleType().equals(CommonConstants.QC.RULE_TYPE_CODE_6)) {
         if ((conditionsRes2 == null || conditionsRes2.isEmpty() || conditionsRes1 == null || conditionsRes1.isEmpty()) && conditionsRes1 != null && !conditionsRes1.isEmpty()) {
            if (CollectionUtils.isNotEmpty(resultVoList) && setResultVoList != null) {
               setResultVoList.clear();
               setResultVoList.add(resultVoList.get(0));
            }
         } else if (setResultVoList != null) {
            setResultVoList.clear();
         }
      } else if (resultVoList != null) {
         setResultVoList.clear();
         setResultVoList.addAll(resultVoList);
      }

   }

   public List selectQcReturnList(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<BackLogVo> result = new ArrayList();
      List<EmrQcRecord> list = this.emrQcRecordMapper.selectQcReturnList(basEmployee.getEmplNumber(), patientId);
      if (list != null && !list.isEmpty()) {
         for(EmrQcRecord emrQcRecord : list) {
            BackLogVo backLogVo = new BackLogVo();
            backLogVo.setInpNo(emrQcRecord.getInpNo());
            backLogVo.setPatientMainId(emrQcRecord.getPatientMainId());
            backLogVo.setPatientId(emrQcRecord.getPatientId());
            backLogVo.setType("6");
            String bedNo = emrQcRecord.getBedNo() == null ? "" : emrQcRecord.getBedNo();
            String patientName = emrQcRecord.getPatientName() == null ? "" : emrQcRecord.getPatientName();
            String sex = emrQcRecord.getSex() == null ? "" : emrQcRecord.getSex();
            String caseNo = StringUtils.isNotBlank(emrQcRecord.getRecordNo()) ? emrQcRecord.getRecordNo() : "";
            backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】 患者的病历被质控退回,请及时修改。");
            result.add(backLogVo);
         }
      }

      return result;
   }

   public EmrQcRecord selectEmrQcRecordByQcSection(String qcSection, String patientId) {
      return this.emrQcRecordMapper.selectEmrQcRecordByQcSection(qcSection, patientId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveMrisHpQcRecord(TdCmHpLineVo mrHpVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      EmrQcRecord emrQcRecord = new EmrQcRecord();
      emrQcRecord.setId(SnowIdUtils.uniqueLong());
      emrQcRecord.setPatientId(mrHpVo.getPatient_id());
      emrQcRecord.setPatientName(mrHpVo.getPatient_name());
      emrQcRecord.setSex(mrHpVo.getSex_cd());
      emrQcRecord.setInpNo(mrHpVo.getInp_no());
      emrQcRecord.setInDeptCd(mrHpVo.getIn_dept_cd());
      emrQcRecord.setInDeptName(mrHpVo.getIn_dept_name());
      emrQcRecord.setQcSection("01");
      emrQcRecord.setQcdoctCd("sys");
      emrQcRecord.setQcdoctName("系统");
      emrQcRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcRecord.setCrePerName(basEmployee.getEmplName());
      if (mrHpVo.getEmrQcListList() != null && !mrHpVo.getEmrQcListList().isEmpty()) {
         List<EmrQcList> insertEmrQcListList = new ArrayList(mrHpVo.getEmrQcListList().size());
         List<EmrQcCommRecord> insertQcCommRecords = new ArrayList();

         for(EmrQcListVo emrQcListVo : mrHpVo.getEmrQcListList()) {
            Long id = SnowIdUtils.uniqueLong();
            if (emrQcListVo.getId() == null) {
               emrQcListVo.setMainId(emrQcRecord.getId());
               emrQcListVo.setId(id);
               emrQcListVo.setMrFileId(mrHpVo.getRecord_id());
               insertEmrQcListList.add(emrQcListVo);
               if (emrQcListVo.getQcCommRecordList() != null && !emrQcListVo.getQcCommRecordList().isEmpty()) {
                  for(EmrQcCommRecord emrQcCommRecord : emrQcListVo.getQcCommRecordList()) {
                     if (emrQcCommRecord.getId() == null) {
                        emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
                        emrQcCommRecord.setMainId(id);
                        emrQcCommRecord.setMrFileId(mrHpVo.getRecord_id());
                        emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
                        emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
                        insertQcCommRecords.add(emrQcCommRecord);
                     }
                  }
               }
            }
         }

         if (insertEmrQcListList.size() > 0) {
            this.emrQcListService.insertEmrQcLists(insertEmrQcListList);
         }

         if (insertQcCommRecords.size() > 0) {
            this.emrQcCommRecordService.insertList(insertQcCommRecords);
         }
      }

      this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
   }
}
