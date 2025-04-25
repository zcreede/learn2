package com.emr.project.qc.service;

import com.emr.project.emr.domain.vo.RuleVo;
import java.util.List;

public interface IQcRuleUtilService {
   void getAddQcCheckElemList(List elemVoList, Long checkId, Integer ruleType) throws Exception;

   String getErrorColumnByExpressionVoList(List list) throws Exception;

   String createRuleFile(RuleVo ruleVo) throws Exception;

   String getExpressionJsonString(List elemExpressionVoList) throws Exception;

   String getAggregateByObjectList(List list) throws Exception;
}
