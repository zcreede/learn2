package com.emr.project.qc.service;

import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.vo.QcAgiRuleVo;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IQcAgiRuleService {
   QcAgiRule selectQcAgiRuleById(Long id);

   QcAgiRule selectQcAgiRuleByCode(String ruleCode) throws Exception;

   QcAgiRule selectYxQcAgiRuleByCode(String ruleCode) throws Exception;

   List selectQcAgiRuleListByCode(List ruleCodeList) throws Exception;

   List selectQcAgiRuleListByIds(List list) throws Exception;

   List selectQcAgiRuleList(QcAgiRuleVo qcAgiRule) throws Exception;

   int insertQcAgiRule(QcAgiRule qcAgiRule);

   int updateQcAgiRule(QcAgiRule qcAgiRule) throws Exception;

   void updateQcAgiRuleFlag(QcAgiRule qcAgiRule) throws Exception;

   int deleteQcAgiRuleByIds(Long[] ids);

   int deleteQcAgiRuleById(Long id);

   void agiRuleDoing(String ruleCode) throws Exception;

   void agiRuleTaskDoing(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList) throws Exception;

   List getRuleData(QcAgiRule qcAgiRule, String patientId, Date beginTime) throws Exception;

   void agiRuleDo(QcAgiRule qcAgiRule, List patEventIndexList) throws Exception;

   EmrTaskInfo getIndexLastFinishTime(String patientId, String emrTypeCode, boolean mainFileFlag) throws Exception;

   List getUnWriteList(String orgCd, String patientId) throws Exception;

   List selectOperBeforeList(String eventCd) throws Exception;
}
