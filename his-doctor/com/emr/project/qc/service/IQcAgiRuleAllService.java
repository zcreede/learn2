package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcAgiRule;
import java.util.List;
import java.util.Map;

public interface IQcAgiRuleAllService {
   void agiRuleTaskCreate(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList, List emrSignDataList, List tdCaOperationApplyList) throws Exception;

   void agiRuleTaskCreateByPatientId(QcAgiRule qcAgiRule, List patEventVoList, Map patEventVoListMap, List indexList, List emrTaskInfoList, List emrTaskInfoYcjList, List patEventVoListDeadAll, List patEventVoListOutAll, List subfileIndexList, List mrHpList, List emrSignDataList, List tdCaOperationApplyList) throws Exception;
}
